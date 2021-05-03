package com.gsatechworld.linnaas.core.network;

import com.gsatechworld.linnaas.utils.history.HistoryResp;
import com.gsatechworld.linnaas.utils.homepage.HomeResp;
import com.gsatechworld.linnaas.utils.homepage.UpdateReadResp;
import com.gsatechworld.linnaas.utils.login.LoginResponse;
import com.gsatechworld.linnaas.utils.login.LoginResultResp;
import com.gsatechworld.linnaas.utils.profile.ProfileDetailResp;
import com.gsatechworld.linnaas.utils.profile.ProfileResp;
import com.gsatechworld.linnaas.utils.search.SearchResp;
import com.gsatechworld.linnaas.utils.verifyOtp.VerifyOtpResp;
import com.gsatechworld.linnaas.utils.wallet.Walletresp;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NetworkAPI {
    @POST("login")
    Single<Response<LoginResultResp>> login(@Body LoginResultResp loginResultResp);

    @POST("verifyOtp")
    Single<Response<VerifyOtpResp>> verifyotp(@Body VerifyOtpResp verifyOtpResp);

    @POST("profileInfo")
    Single<Response<ProfileResp>> updateprofile(@Body ProfileResp profileResp);

    @GET("allVerses")
    Single<Response<HomeResp>> getverses(@Query("language") String language);

    @POST("readHistory")
    Single<Response<HistoryResp>> getHistory(@Body HistoryResp historyResp);

    @POST("versesSearch")
    Single<Response<SearchResp>> getSearchResult(@Body SearchResp searchResp);

    @POST("updateReadCounts")
    Single<Response<UpdateReadResp>> updateReadCount(@Body UpdateReadResp updateReadResp);

    @POST("walletHistory")
    Single<Response<Walletresp>> getWalletHistory(@Body Walletresp walletresp);

    @POST("profileDetails")
    Single<Response<ProfileDetailResp>> getProfile(@Body ProfileDetailResp profileDetailResp);
}
