package com.stackroute.moneynmonetary.otpservice.model;

import java.util.UUID;

public class OtpVerification {
    private int otp;
    private UUID custId;

    public int getOtp() {
        return otp;
    }

    public void setOtp(int otp) {
        this.otp = otp;
    }

    public UUID getCustId() {
        return custId;
    }

    public void setCustId(UUID custId) {
        this.custId = custId;
    }

    public OtpVerification(int otp, UUID custId) {
        this.otp = otp;
        this.custId = custId;
    }
    public OtpVerification() {
    }
}
