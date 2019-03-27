/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unionbankng.ussd.db;

import com.unionbankng.ussd.converters.JsonConverter;
import com.unionbankng.ussd.dao.Datasource;
import com.unionbankng.ussd.dao.DatasourceSqlServer;
import com.unionbankng.ussd.dtos.ResponseDto;
import com.unionbankng.ussd.dtos.TransactionDetailDto;
import com.unionbankng.ussd.dtos.TransactionSummaryDto;
import com.unionbankng.ussd.dtos.UssdDetails;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author oawe
 */
public class DbImpl {

	static Logger log = LogManager.getLogger(DbImpl.class);

	Datasource connMgr = new Datasource();

	public List<TransactionSummaryDto> getPendingTransactionsSummary(int partitionId) {
		List<TransactionSummaryDto> res = new ArrayList();

		Connection conn = connMgr.getConnection();

		if (conn != null) {
			try {
				CallableStatement stmt = conn.prepareCall("{call proc_getNapsPendingTransactionsSummary(?)}");
				int i = 0;
				stmt.setInt(++i, partitionId);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					TransactionSummaryDto tns = new TransactionSummaryDto();
					tns.setId(rs.getInt("Id"));
					tns.setCompletedDate(rs.getDate("COMPLETEDDATE"));
					//tns.setFailedCount(rs.getInt("FAILEDCOUNT"));
					tns.setInitiatedDate(rs.getDate("INITIATEDDATE"));
					tns.setSessionNumber(rs.getString("SESSIONNO"));
					tns.setStatus(rs.getString("STATUS"));
					tns.setTotalCount(rs.getInt("TOTALCOUNT"));
					res.add(tns);
				}

				rs.close();
				stmt.close();

			} catch (SQLException ex) {
				log.error(ex);
			} catch (Exception ex) {
				log.error(ex);
			} finally {
				try {
					conn.close();
				} catch (SQLException ex) {
					log.error(ex);
				}
			}
		}
		return res;
	}

	public List<UssdDetails> getPendingTransactionsDetail() {
		List<UssdDetails> res = new ArrayList();

		Connection conn = connMgr.getConnection();
		ResultSet rs = null;
		if (conn != null) {
			try {
				CallableStatement stmt = conn.prepareCall("{? = call USSDSessionRecoup_pkg.getPendingList()}");
				int i = 0;
				stmt.registerOutParameter(++i, oracle.jdbc.OracleTypes.CURSOR);

				if (stmt.executeUpdate() != Statement.EXECUTE_FAILED) {
					rs = (ResultSet) stmt.getObject(1);
					while (rs.next()) {
						UssdDetails tns = new UssdDetails();

						tns.setTarget_tbl(rs.getString("TARGET_TBL"));
						tns.setPaymet_ref_no(rs.getString("PAYMET_REF_NO"));
						tns.setBranch_code(rs.getString("BRANCH_CODE"));
						tns.setAmount(rs.getString("AMOUNT"));
						tns.setFee(rs.getString("FEE"));
						tns.setServicecode(rs.getString("SERVICECODE"));
						tns.setChannel(rs.getString("CHANNEL"));
						tns.setServiceid(rs.getString("SERVICEID"));
						tns.setAcccountnumber(rs.getString("ACCCOUNTNUMBER"));
						tns.setUsername(rs.getString("USERNAME"));
						tns.setCallerdesc(rs.getString("CALLERDESC"));
						tns.setStatus(rs.getString("STATUS"));
						tns.setFlow_id(rs.getString("FLOW_ID"));
						tns.setOtype(rs.getString("OTYPE"));
						tns.setReceived_from(rs.getString("RECEIVED_FROM"));
					}
				}
				rs.close();
				stmt.close();

			} catch (SQLException ex) {
				log.error(ex);
			} catch (Exception ex) {
				log.error(ex);
			} finally {
				try {
					conn.close();
				} catch (SQLException ex) {
					log.error(ex);
				}
			}
		}

		return res;
	}

	public ResponseDto updateTransactionResponse(UssdDetails d) {

		Connection conn = connMgr.getConnection();
		int res = 99;
		if (conn != null) {
			try {
				//conn.setAutoCommit(false);
				CallableStatement stmt = conn.prepareCall("{? = call USSDSessionRecoup_pkg.updateTransaction(?,?,?,?)}");
				int i = 0;
				stmt.registerOutParameter(++i, java.sql.Types.INTEGER);
				stmt.setString(++i, d.getTarget_tbl());
				stmt.setString(++i, d.getRescode());
				stmt.setString(++i, d.getResdesc());
				stmt.setString(++i, d.getPaymet_ref_no());
				stmt.execute();
				res = stmt.getInt(1);
				//conn.commit();
				stmt.close();
				//conn.setAutoCommit(true);
			} catch (SQLException ex) {
				ex.printStackTrace();
				log.error(ex);
			} catch (Exception ex) {
				log.error(ex);
			} finally {
				try {
					conn.close();
				} catch (SQLException ex) {
					log.error(ex);
				}
			}
		}
		ResponseDto r = new ResponseDto();
		r.setCode(res == 200 ? "00" : res == 101 ? "96" : "97");
		return r;

	}

	public ResponseDto updateTransactionSummary(TransactionSummaryDto s) {
		Connection conn = connMgr.getConnection();
		int res = 99;
		if (conn != null) {
			try {
				//conn.setAutoCommit(false);
				CallableStatement stmt = conn.prepareCall("{?=call proc_NapsUpdateSummary(?)}");
				int i = 0;
				stmt.registerOutParameter(++i, java.sql.Types.INTEGER);
				stmt.setString(++i, s.getSessionNumber());
				stmt.execute();
				res = stmt.getInt(1);
				//conn.commit();
				stmt.close();
				//conn.setAutoCommit(true);
			} catch (SQLException ex) {
				ex.printStackTrace();
				log.error(ex);
			} catch (Exception ex) {
				log.error(ex);
			} finally {
				try {
					conn.close();
				} catch (SQLException ex) {
					log.error(ex);
				}
			}
		}
		ResponseDto r = new ResponseDto();
		r.setCode(res == 200 ? "00" : res == 101 ? "96" : "97");
		return r;
	}

	public void updateSummary() {
		for (TransactionSummaryDto summary : this.getPendingTransactionsSummary(999)) {
			try {
				log.info("Database update call>> session..." + summary.getSessionNumber() + "...item count: " + summary.getTotalCount());
				ResponseDto res = this.updateTransactionSummary(summary);
				log.info("Database update response>> session..." + summary.getSessionNumber() + " >> Code:" + res.getCode() + "; Message:" + res.getMessage());
			} catch (Exception ex) {
				log.error(ex);
			}
		}
	}
}
