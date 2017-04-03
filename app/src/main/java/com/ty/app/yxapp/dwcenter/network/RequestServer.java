package com.ty.app.yxapp.dwcenter.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by heli on 2017/4/3.
 */
public interface RequestServer {
    @GET("caseplatform/mobile/login-app!login.action")
    Call<String> getLoginStatus(@Query("name")String username, @Query("password")String password);
}
