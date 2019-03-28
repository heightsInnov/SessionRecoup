create or replace package USSDSessionRecoup_pkg is

	-- Author  : AOJINADU
	-- Created : 3/26/2019 9:15:04 AM
	-- Purpose : USSD Session Fee RecoupPackage

	-- Public function and procedure declarations
	function checkflags(servicetype varchar2, rec_from varchar2)
		return varchar2;

	function getPendingList return sys_refcursor;

	function updateTransaction(table_tag varchar2,
														 rescode   varchar2,
														 respdesc  varchar2,
														 ref_no    varchar2) return number;

end USSDSessionRecoup_pkg;
/
create or replace package body USSDSessionRecoup_pkg is

	V_ERR_MESSAGE varchar2(1000);
	-- Function and procedure implementations
	function checkflags(servicetype varchar2, rec_from varchar2)
		return varchar2 is
		fee number := 0;
	begin
		if servicetype in ('MINI_STMT',
											 'UPDATE_PRIM_ACC',
											 'CL_OB_GETSTATUS',
											 'CUSTPAYBILL',
											 'BAL_ENQ',
											 'CL_OB_GETTOKEN',
											 'MERCHANT_PAY_OWN',
											 'CL_OB_CANTOKEN',
											 'PIN_CHANGE',
											 'MERCHANT_PAY_OTHER',
											 'FUND_TRANS_OWN',
											 'FUND_TRNS_OTCR',
											 'FUND_TRANS_INTRA') then
			if rec_from = 'F' then
				return 5;
			elsif rec_from = 'S' then
				begin
					select VALUE
						into fee
						from apps.limit_fee_details lfd, apps.limit_fee_master lfm
					 where lfd.REF_NUM = lfm.REF_NUM
						 and lfd.channel = 'USSD'
						 and lfd.OPERATORS = 'OPID4'
						 and lfm.product = 'MOBPRD'
						 and lfd.TXN_CODE = servicetype;
					return NVL(fee, 5);
				exception
					when no_data_found then
						return 5;
				end;
			elsif rec_from = 'D' then
				return 5;
			else
				return 0;
			end if;
		else
			return 0;
		end if;
	
	end checkflags;

	function getPendingList return sys_refcursor is
		pendingList sys_refcursor;
	begin
		begin
			open pendingList for
				SELECT 'CONDITIONAL_DEBIT_TBL' TARGET_TBL,
							 PAYMET_REF_NO,
							 BRANCH_CODE,
							 AMOUNT,
							 ussdsessionrecoup_pkg.checkflags(SERVICECODE, RECEIVED_FROM) FEE,
							 DECODE(SERVICECODE,
											'FUND_TRANS_INTRA',
											'FUND_TRANS_OWN',
											SERVICECODE) SERVICECODE,
							 CHANNEL,
							 SERVICEID,
							 ACCCOUNTNUMBER,
							 USERNAME,
							 CALLERDESC,
							 STATUS,
							 FLOW_ID,
							 OTYPE,
							 RECEIVED_FROM
					FROM apps.CONDITIONAL_DEBIT_TBL
				 WHERE STATUS = 'O'
					 AND OTYPE = 'OPID4'
					 AND SERVICECODE in ('MINI_STMT',
															 'UPDATE_PRIM_ACC',
															 'CL_OB_GETSTATUS',
															 'CUSTPAYBILL',
															 'BAL_ENQ',
															 'CL_OB_GETTOKEN',
															 'MERCHANT_PAY_OWN',
															 'CL_OB_CANTOKEN',
															 'PIN_CHANGE',
															 'MERCHANT_PAY_OTHER',
															 'FUND_TRANS_OWN',
															 'FUND_TRNS_OTCR',
															 'FUND_TRANS_INTRA')
           and RECEIVED_FROM not in ('x', 'X')
					 and rownum <= 5000
           
				
				union all
				
				SELECT 'CONDITIONAL_DEBIT_TBL_BLK' TARGET_TBL,
							 PAYMET_REF_NO,
							 BRANCH_CODE,
							 AMOUNT,
							 FEE,
							 SERVICECODE,
							 CHANNEL,
							 SERVICEID,
							 ACCCOUNTNUMBER,
							 USERNAME,
							 CALLERDESC,
							 STATUS,
							 FLOW_ID,
							 OTYPE,
							 RECEIVED_FROM
					FROM apps.CONDITIONAL_DEBIT_TBL_BLK
				 WHERE STATUS = 'O'
					 AND OTYPE = 'OPID4'
					 AND SERVICECODE in ('MINI_STMT',
															 'UPDATE_PRIM_ACC',
															 'CL_OB_GETSTATUS',
															 'CUSTPAYBILL',
															 'BAL_ENQ',
															 'CL_OB_GETTOKEN',
															 'MERCHANT_PAY_OWN',
															 'CL_OB_CANTOKEN',
															 'PIN_CHANGE',
															 'MERCHANT_PAY_OTHER',
															 'FUND_TRANS_OWN',
															 'FUND_TRNS_OTCR',
															 'FUND_TRANS_INTRA')
           and RECEIVED_FROM not in ('x', 'X')
					 and fee > 0
					 and rownum <= 5000;
		exception
			WHEN no_data_found THEN
				V_ERR_MESSAGE := DBMS_UTILITY.FORMAT_ERROR_BACKTRACE || SQLERRM;
				INSERT INTO GENERAL_ERROR_LOG
				VALUES
					(V_ERR_MESSAGE, SYSDATE, 'USSDSessionRecoup_pkg.getPendingList');
				COMMIT;
		end;
		return pendingList;
	end getPendingList;

	function updateTransaction(table_tag varchar2,
														 rescode   varchar2,
														 respdesc  varchar2,
														 ref_no    varchar2) return number is
		v_status varchar2(2);
	begin
		if rescode = '00' then
			v_status := 'C';
		else
			v_status := 'F';
		end if;
		begin
			if table_tag = 'CONDITIONAL_DEBIT_TBL' then
				update apps.CONDITIONAL_DEBIT_TBL
					 set respcode = rescode, resdesc = respdesc, status = v_status
				 where paymet_ref_no = ref_no;
			elsif table_tag = 'CONDITIONAL_DEBIT_TBL_BLK' then
				update apps.CONDITIONAL_DEBIT_TBL_BLK
					 set respcode = rescode, resdesc = respdesc, status = v_status
				 where paymet_ref_no = ref_no;
			end if;
			return 200;
			commit;
		exception
			when others then
				V_ERR_MESSAGE := DBMS_UTILITY.FORMAT_ERROR_BACKTRACE || SQLERRM;
				INSERT INTO GENERAL_ERROR_LOG
				VALUES
					(V_ERR_MESSAGE,
					 SYSDATE,
					 'USSDSessionRecoup_pkg.updateTransaction');
				COMMIT;
				return 101;
		end;
	end updateTransaction;
end USSDSessionRecoup_pkg;
/
