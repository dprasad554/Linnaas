package com.gsatechworld.linnaas.utils.verifyOtp;

public class VerifyOtpResult {

    private VerifyOtpResp verifyOtpResp;

    public VerifyOtpResult(VerifyOtpResp verifyOtpResp){
        this.verifyOtpResp = verifyOtpResp;
    }
    public VerifyOtpResp getVerifyOtpResp() {
        return verifyOtpResp;
    }

    public void setVerifyOtpResp(VerifyOtpResp verifyOtpResp) {
        this.verifyOtpResp = verifyOtpResp;
    }
}
