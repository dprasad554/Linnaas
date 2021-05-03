package com.gsatechworld.linnaas.utils.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.gsatechworld.linnaas.utils.verifyOtp.VerifyOtpResp;

public class ProfileResp {

    private String first_name;
    private String last_name;
    private String mobile_number;
    private String email_id;
    private String gender;

    public ProfileResp(String first_name,String last_name,String mobile_number,String email_id,String gender){
        this.first_name = first_name;
        this.last_name = last_name;
        this.email_id = email_id;
        this.gender = gender;
        this.mobile_number = mobile_number;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @SerializedName("success")
    @Expose
    private String success;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("data")
    @Expose
    private Data data;

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

        @SerializedName("mobile_number")
        @Expose
        private String mobile_number;

        @SerializedName("gender")
        @Expose
        private String gender;

        @SerializedName("status")
        @Expose
        private String status;

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

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
