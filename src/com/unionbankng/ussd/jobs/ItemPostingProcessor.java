/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unionbankng.ussd.jobs;

import com.unionbankng.ussd.constants.Constants;
import com.unionbankng.ussd.db.DbImpl;
import com.unionbankng.ussd.dtos.FundsTransferRequest;
import com.unionbankng.ussd.dtos.FundsTransferResponse;
import com.unionbankng.ussd.dtos.ResponseDto;
import com.unionbankng.ussd.dtos.TransactionDetailDto;
import com.unionbankng.ussd.dtos.UssdDetails;

import com.unionbankng.ussd.utils.MiServeImpl;
import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author oawe
 */
public class ItemPostingProcessor implements Runnable {

    static Logger log = LogManager.getLogger(ItemPostingProcessor.class);
    private final UssdDetails item;
    private final DbImpl db = new DbImpl();
    private final int partitionId;

    public ItemPostingProcessor(UssdDetails item, int partitionId) {
        this.item = item;
        this.partitionId = partitionId;
    }

    @Override
    public void run() {
        try {
            log.info("Instance" + partitionId + "\tPosting Service call>> item..." + item.getPaymet_ref_no());

            FundsTransferRequest transferRequest = new FundsTransferRequest();

            transferRequest.setAmount(item.getAmount());
            transferRequest.setCreditAccountBranchCode("000");
            transferRequest.setCreditAccountNumber(Constants.CREDIT_ACCOUNT);
            transferRequest.setCreditAccountType("GL");

            transferRequest.setCurrency("NGN");
            transferRequest.setDebitAccountNumber(item.getAcccountnumber());
            transferRequest.setDebitAccountType(transferRequest.getDebitAccountNumber().length() == 10 ? "CASA" : "GL");
            transferRequest.setDebitAccountBranchCode("000");
            transferRequest.setCreditNarration(item.getCallerdesc().replace("\t", ""));
            transferRequest.setDebitNarration(item.getCallerdesc().replace("\t", ""));
            transferRequest.setInitBranchCode("000");
            transferRequest.setPaymentTypeCode("FT");
            transferRequest.setPaymentReference(item.getPaymet_ref_no());
            transferRequest.setValueDate(new Date());

            FundsTransferResponse serviceRes = MiServeImpl.fundsTransfer(transferRequest);

            log.info("Instance" + partitionId + "\tPosting Service response>> item..." + item.getPaymet_ref_no() + " >> Code:" + serviceRes.getCode() + "; Message:" + serviceRes.getMessage());

            //item.setStatus("P");
            if (!"101".equals(serviceRes.getCode())) {
                log.info("Instance" + partitionId + "\tDatabase update call>> item..." + item.getPaymet_ref_no());
                item.setRescode(serviceRes.getCode());
                item.setResdesc(serviceRes.getMessage());
                ResponseDto res = db.updateTransactionResponse(item);
                log.info("Instance" + partitionId + "\tDatabase update response>> item..." + item.getPaymet_ref_no() + " >> Code:" + res.getCode() + "; Message:" + res.getMessage());
            }
        } catch (Exception ex) {
            log.error(ex);
        }
    }

}
