package com.gsatechworld.linnaas.utils.profile;

public class ProfileResult {

    private ProfileResp profileResp;

    public ProfileResult(ProfileResp profileResp){
        this.profileResp = profileResp;
    }

    public ProfileResp getProfileResp() {
        return profileResp;
    }

    public void setProfileResp(ProfileResp profileResp) {
        this.profileResp = profileResp;
    }
}
