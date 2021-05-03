package com.gsatechworld.linnaas.utils.homepage;

public class HomeResult {

    private HomeResp homeResp;
    public HomeResult(HomeResp homeResp){
        this.homeResp = homeResp;
    }

    public HomeResp getHomeResp() {
        return homeResp;
    }

    public void setHomeResp(HomeResp homeResp) {
        this.homeResp = homeResp;
    }
}
