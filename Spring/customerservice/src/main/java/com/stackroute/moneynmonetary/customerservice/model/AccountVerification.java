package com.stackroute.moneynmonetary.customerservice.model;

public class AccountVerification {
	private Long accNo;
	private String ifscCode;
	public AccountVerification(Long accNo, String ifscCode) {
		this.accNo = accNo;
		this.ifscCode = ifscCode;
	}
	public AccountVerification() {
	}
	public Long getAccNo() {
		return accNo;
	}
	public void setAccNo(Long accNo) {
		this.accNo = accNo;
	}
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
}
