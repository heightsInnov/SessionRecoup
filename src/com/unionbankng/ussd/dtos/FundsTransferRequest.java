/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unionbankng.ussd.dtos;

import java.util.Date;

/**
 *
 * @author oawe
 */
public class FundsTransferRequest {
    private String debitInstrumentNumber;
    private String debitAccountName;
    private String creditAccountNumber;
    private Date valueDate;
    private String paymentReference;
    private String creditAccountBankCode;
    private String debitAccountBranchCode;
    private String initBranchCode;
    private String feeEntryMode;
    private String paymentTypeCode;
    private String currency;
    private String amount;
    private String creditAccountName;
    private String creditAccountType;
    private String creditNarration;
    private String creditAccountBranchCode;
    private String debitNarration;
    private String debitAccountType;
    private String debitAccountBankCode;
    private String debitAccountNumber;

    public String getDebitInstrumentNumber() {
        return debitInstrumentNumber;
    }

    public void setDebitInstrumentNumber(String debitInstrumentNumber) {
        this.debitInstrumentNumber = debitInstrumentNumber;
    }

    public String getDebitAccountName() {
        return debitAccountName;
    }

    public void setDebitAccountName(String debitAccountName) {
        this.debitAccountName = debitAccountName;
    }

    public String getCreditAccountNumber() {
        return creditAccountNumber;
    }

    public void setCreditAccountNumber(String creditAccountNumber) {
        this.creditAccountNumber = creditAccountNumber;
    }

    public Date getValueDate() {
        return valueDate;
    }

    public void setValueDate(Date valueDate) {
        this.valueDate = valueDate;
    }

    public String getPaymentReference() {
        return paymentReference;
    }

    public void setPaymentReference(String paymentReference) {
        this.paymentReference = paymentReference;
    }

    public String getCreditAccountBankCode() {
        return creditAccountBankCode;
    }

    public void setCreditAccountBankCode(String creditAccountBankCode) {
        this.creditAccountBankCode = creditAccountBankCode;
    }

    public String getDebitAccountBranchCode() {
        return debitAccountBranchCode;
    }

    public void setDebitAccountBranchCode(String debitAccountBranchCode) {
        this.debitAccountBranchCode = debitAccountBranchCode;
    }

    public String getInitBranchCode() {
        return initBranchCode;
    }

    public void setInitBranchCode(String initBranchCode) {
        this.initBranchCode = initBranchCode;
    }

    public String getFeeEntryMode() {
        return feeEntryMode;
    }

    public void setFeeEntryMode(String feeEntryMode) {
        this.feeEntryMode = feeEntryMode;
    }

    public String getPaymentTypeCode() {
        return paymentTypeCode;
    }

    public void setPaymentTypeCode(String paymentTypeCode) {
        this.paymentTypeCode = paymentTypeCode;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCreditAccountName() {
        return creditAccountName;
    }

    public void setCreditAccountName(String creditAccountName) {
        this.creditAccountName = creditAccountName;
    }

    public String getCreditAccountType() {
        return creditAccountType;
    }

    public void setCreditAccountType(String creditAccountType) {
        this.creditAccountType = creditAccountType;
    }

    public String getCreditNarration() {
        return creditNarration;
    }

    public void setCreditNarration(String creditNarration) {
        this.creditNarration = creditNarration;
    }

    public String getCreditAccountBranchCode() {
        return creditAccountBranchCode;
    }

    public void setCreditAccountBranchCode(String creditAccountBranchCode) {
        this.creditAccountBranchCode = creditAccountBranchCode;
    }

    public String getDebitNarration() {
        return debitNarration;
    }

    public void setDebitNarration(String debitNarration) {
        this.debitNarration = debitNarration;
    }

    public String getDebitAccountType() {
        return debitAccountType;
    }

    public void setDebitAccountType(String debitAccountType) {
        this.debitAccountType = debitAccountType;
    }

    public String getDebitAccountBankCode() {
        return debitAccountBankCode;
    }

    public void setDebitAccountBankCode(String debitAccountBankCode) {
        this.debitAccountBankCode = debitAccountBankCode;
    }

    public String getDebitAccountNumber() {
        return debitAccountNumber;
    }

    public void setDebitAccountNumber(String debitAccountNumber) {
        this.debitAccountNumber = debitAccountNumber;
    }
    
    
}
