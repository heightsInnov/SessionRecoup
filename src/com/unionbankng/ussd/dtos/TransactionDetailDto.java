/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unionbankng.ussd.dtos;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author oawe
 */
public class TransactionDetailDto {

    private int id;
    private String narration;
    private String payerAccount;
    private String payerAddress;
    private String payerName;
    private String originalDebitRefNumber;
    private String responseCode;
    private String responseDtStamp;
    private String sessionNumber;
    private String transactionRefNumber;
    private BigDecimal amount;
    private String creditAccountNumber;
    private String debitAccountNumber;
    private String createDtStamp;
    private String currencyCode;
    private String responseReference;
    private String responseMessage;
    private String debitRefNumber;
    private String status;
    private int bankRetryCount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public String getPayerAccount() {
        return payerAccount;
    }

    public void setPayerAccount(String payerAccount) {
        this.payerAccount = payerAccount;
    }

    public String getPayerAddress() {
        return payerAddress;
    }

    public void setPayerAddress(String payerAddress) {
        this.payerAddress = payerAddress;
    }

    public String getPayerName() {
        return payerName;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }

    public String getOriginalDebitRefNumber() {
        return originalDebitRefNumber;
    }

    public void setOriginalDebitRefNumber(String originalDebitRefNumber) {
        this.originalDebitRefNumber = originalDebitRefNumber;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseDtStamp() {
        return responseDtStamp;
    }

    public void setResponseDtStamp(String responseDtStamp) {
        this.responseDtStamp = responseDtStamp;
    }

    public String getSessionNumber() {
        return sessionNumber;
    }

    public void setSessionNumber(String sessionNumber) {
        this.sessionNumber = sessionNumber;
    }

    public String getTransactionRefNumber() {
        return transactionRefNumber;
    }

    public void setTransactionRefNumber(String transactionRefNumber) {
        this.transactionRefNumber = transactionRefNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCreditAccountNumber() {
        return creditAccountNumber;
    }

    public void setCreditAccountNumber(String creditAccountNumber) {
        this.creditAccountNumber = creditAccountNumber;
    }

    public String getDebitAccountNumber() {
        return debitAccountNumber;
    }

    public void setDebitAccountNumber(String debitAccountNumber) {
        this.debitAccountNumber = debitAccountNumber;
    }

    public String getCreateDtStamp() {
        return createDtStamp;
    }

    public void setCreateDtStamp(String createDtStamp) {
        this.createDtStamp = createDtStamp;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getResponseReference() {
        return responseReference;
    }

    public void setResponseReference(String responseReference) {
        this.responseReference = responseReference;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getDebitRefNumber() {
        return debitRefNumber;
    }

    public void setDebitRefNumber(String debitRefNumber) {
        this.debitRefNumber = debitRefNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getBankRetryCount() {
        return bankRetryCount;
    }

    public void setBankRetryCount(int bankRetryCount) {
        this.bankRetryCount = bankRetryCount;
    }
}