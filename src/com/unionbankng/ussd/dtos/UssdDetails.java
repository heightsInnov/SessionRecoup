/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unionbankng.ussd.dtos;

/**
 *
 * @author aojinadu
 */
public class UssdDetails {
	private String target_tbl;
	private String paymet_ref_no;
	private String branch_code;
	private String amount;
	private String fee;
	private String servicecode;
	private String channel;
	private String serviceid;
	private String acccountnumber;
	private String username;
	private String callerdesc;
	private String status;
	private String flow_id;
	private String otype;
	private String received_from;
	private String rescode;
	private String resdesc;

	public String getRescode() {
		return rescode;
	}

	public void setRescode(String rescode) {
		this.rescode = rescode;
	}

	public String getResdesc() {
		return resdesc;
	}

	public void setResdesc(String resdesc) {
		this.resdesc = resdesc;
	}

	public String getTarget_tbl() {
		return target_tbl;
	}

	public void setTarget_tbl(String target_tbl) {
		this.target_tbl = target_tbl;
	}

	public String getPaymet_ref_no() {
		return paymet_ref_no;
	}

	public void setPaymet_ref_no(String paymet_ref_no) {
		this.paymet_ref_no = paymet_ref_no;
	}

	public String getBranch_code() {
		return branch_code;
	}

	public void setBranch_code(String branch_code) {
		this.branch_code = branch_code;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

	public String getServicecode() {
		return servicecode;
	}

	public void setServicecode(String servicecode) {
		this.servicecode = servicecode;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getServiceid() {
		return serviceid;
	}

	public void setServiceid(String serviceid) {
		this.serviceid = serviceid;
	}

	public String getAcccountnumber() {
		return acccountnumber;
	}

	public void setAcccountnumber(String acccountnumber) {
		this.acccountnumber = acccountnumber;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCallerdesc() {
		return callerdesc;
	}

	public void setCallerdesc(String callerdesc) {
		this.callerdesc = callerdesc;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFlow_id() {
		return flow_id;
	}

	public void setFlow_id(String flow_id) {
		this.flow_id = flow_id;
	}

	public String getOtype() {
		return otype;
	}

	public void setOtype(String otype) {
		this.otype = otype;
	}

	public String getReceived_from() {
		return received_from;
	}

	public void setReceived_from(String received_from) {
		this.received_from = received_from;
	}
	
	
}
