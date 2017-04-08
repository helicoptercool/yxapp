package com.ty.app.yxapp.dwcenter.network;

import com.ty.app.yxapp.dwcenter.bean.Event;

import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

/**
 * Created by heli on 2017/4/3.
 */
public interface RequestServer {
    @GET("caseplatform/mobile/login-app!login.action")
    Call<String> getLoginStatus(@Query("name")String username, @Query("password")String password);

    @GET("caseplatform/mobile/system-eventapp!allThings.action")
    Call<List<Event>> getEvents(@Query("account")String account);

    @Multipart
    @POST("uploadServlet")
    Call<String> uploadFile(@PartMap Map<String, RequestBody> params);
}
