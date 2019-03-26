/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unionbankng.ussd.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author oawe
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FundsTransferResponse {

    private String message;
    private String code;
    private String reference;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

}
