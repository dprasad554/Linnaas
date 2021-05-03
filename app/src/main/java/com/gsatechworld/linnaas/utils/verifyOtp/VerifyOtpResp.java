package com.gsatechworld.linnaas.utils.verifyOtp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.gsatechworld.linnaas.views.ui.activity.VerifyOtpActivity;

import java.util.List;

public class VerifyOtpResp {

    private String mobile_number;
    private String otp;

    public VerifyOtpResp(String mobile_number,String otp){
        this.mobile_number = mobile_number;
        this.otp = otp;
    }

    @SerializedName("success")
    @Expose
    private String success;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("data")
    @Expose
    private List<Data> data;

    public class Data{
        @SerializedName("id")
        @Expose
        private String id;

        @SerializedName("first_name")
        @Expose
        private String first_name;

        @SerializedName("last_name")
        @Expose
        private String last;

        @SerializedName("email_id")
        @Expose
        private String email_id;

        @SerializedName("country_code")
        @Expose
        private String country_code;

        @SerializedName("mobile_number")
        @Expose
        private String mobile_number;

        @SerializedName("gender")
        @Expose
        private String gender;

        @SerializedName("status")
        @Expose
        private String status;

        @SerializedName("otp_verified")
        @Expose
        private String otp_verified;

        @SerializedName("created_at")
        @Expose
        private String created_at;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getFirst_name() {
            return first_name;
        }

        public void setFirst_name(String first_name) {
            this.first_name = first_name;
        }

        public String getLast() {
            return last;
        }

        public void setLast(String last) {
            this.last = last;
        }

        public String getEmail_id() {
            return email_id;
        }

        public void setEmail_id(String email_id) {
            this.email_id = email_id;
        }

        public String getMobile_number() {
            return mobile_number;
        }

        public void setMobile_number(String mobile_number) {
            this.mobile_number = mobile_number;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getCountry_code() {
            return country_code;
        }

        public void setCountry_code(String country_code) {
            this.country_code = country_code;
        }

        public String getOtp_verified() {
            return otp_verified;
        }

        public void setOtp_verified(String otp_verified) {
            this.otp_verified = otp_verified;
        }
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }
}
