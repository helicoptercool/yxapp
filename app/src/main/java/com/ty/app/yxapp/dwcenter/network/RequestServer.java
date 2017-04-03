package com.ty.app.yxapp.dwcenter.network;

import com.ty.app.yxapp.dwcenter.bean.Event;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by heli on 2017/4/3.
 */
public interface RequestServer {
    @GET("caseplatform/mobile/login-app!login.action")
    Call<String> getLoginStatus(@Query("name")String username, @Query("password")String password);

    @GET("caseplatform/mobile/system-eventapp!allThings.action")
    Call<List<Event>> getEvents(@Query("account")String account);
}
