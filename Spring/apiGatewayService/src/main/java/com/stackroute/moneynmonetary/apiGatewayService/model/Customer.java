package com.stackroute.moneynmonetary.apiGatewayService.model;

import java.util.Date;
import java.util.UUID;

public class Customer {

    private UUID id;
    private String emailId;
    private String mobileNo;
    private String firstName;
    private String lastName;
    private String password;
    private Date dob;
    private String address;
    private String city;
    private String state;
    private String country;
    private String zipcode;
    private Long accNo;
    private String ifscCode;
    private String profileUrl;
    private String currency;
    private double checkinBalance;
    private double savingsBalance;
    private String jwtToken;

    public double getCheckinBalance() {
        return checkinBalance;
    }

    public void setCheckinBalance(double checkinBalance) {
        this.checkinBalance = checkinBalance;
    }

    public double getSavingsBalance() {
        return savingsBalance;
    }

    public void setSavingsBalance(double savingsBalance) {
        this.savingsBalance = savingsBalance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
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

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public Customer(){

    }
    public Customer(String emailId, String mobileNo, String firstName, String lastName, String password, Date dob, String address, String city, String state, String country, String zipcode, Long accNo, String ifscCode, String profileUrl, String currency, double checkinBalance, double savingsBalance, String jwtToken) {
        this.emailId = emailId;
        this.mobileNo = mobileNo;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.dob = dob;
        this.address = address;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipcode = zipcode;
        this.accNo = accNo;
        this.ifscCode = ifscCode;
        this.profileUrl = profileUrl;
        this.currency = currency;
        this.checkinBalance = checkinBalance;
        this.savingsBalance = savingsBalance;
        this.jwtToken = jwtToken;
    }


}