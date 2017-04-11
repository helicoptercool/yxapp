package com.ty.app.yxapp.dwcenter.network;

import com.ty.app.yxapp.dwcenter.bean.Event;
import com.ty.app.yxapp.dwcenter.bean.EventUpload;
import com.ty.app.yxapp.dwcenter.bean.FileUpload;
import com.ty.app.yxapp.dwcenter.bean.OrgDataInfo;
import com.ty.app.yxapp.dwcenter.bean.StringResult;
import com.ty.app.yxapp.dwcenter.bean.UserInfo;

import java.io.File;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by heli on 2017/4/3.
 */
public interface RequestServer {

    @GET("caseplatform/mobile/system-eventapp!register.action")
    Call<StringResult> register(@Query("account") String account, @Query("password") String password, @Query("name") String name,
                                @Query("mobilephone") String mobilephone, @Query("identityNum") String identityNum);

    @GET("caseplatform/mobile/login-app!login.action")
    Call<String> login(@Query("name") String username, @Query("password") String password);

    @GET("caseplatform/mobile/system-eventapp!setUserPassword.action")
    Call<StringResult> setPassword(@Query("account") String account,
                                   @Query("newPassword") String newPassword,
                                   @Query("oldPassword") String oldPassword);

    @GET("caseplatform/mobile/system-eventapp!getEventInfo.action")
    Call<Event> getEvents(@Query("account") String account, @Query("eventType") String eventType);

    @GET("caseplatform/mobile/system-eventapp!reportEventInfo.action ")
    Call<EventUpload> reportEvent(
            @Query("account") String account,
            @Query("eventType") String eventType,
            @Query("address") String address,
            @Query("eventX") String eventX,
            @Query("eventY") String eventY,
            @Query("eventMs") String eventMs,
            @Query("eventMc") String eventMc,
            @Query("eventId") String eventId,
            @Query("eventClyj") String eventClyj);

    @GET("caseplatform/mobile/system-eventapp!getUserInfo.action")
    Call<UserInfo> getUserInfo(@Query("orgNo") String orgNo, @Query("account") String account);

    @GET("caseplatform/mobile/system-eventapp!getOrgDataInfo.action")
    Call<OrgDataInfo> getOrgData(@Query("orgNo") String orgNo, @Query("account") String account);

    @GET("caseplatform/mobile/video-upload!uplodVideo.action")
    Call<FileUpload> uploadVideo(@Query("video") File video, @Query("videoFileName") String videoFileName);

    @GET("caseplatform/mobile/video -upload!uplodAudio.action")
    Call<FileUpload> uploadAudio(@Query("audio") File audio, @Query("audioFileName") String audioFileName);

    @GET("caseplatform/mobile/file -upload!uplodFile.action")
    Call<FileUpload> uploadImage(@Query("img") File img, @Query("imgFileName") String imgFileName);

    @GET("caseplatform/mobile/system-eventapp!jobgps.action")
    Call<StringResult> setCoordinate(@Query("account") String account,
                                     @Query("address") String address,
                                     @Query("x") String x,
                                     @Query("y") String y,
                                     @Query("coordsType") String coordsType,
                                     @Query("device_id") String device_id
                                     );

    @FormUrlEncoded
    @POST("v2/sms/single_send.json")
    Call<String> postSMS(@FieldMap Map<String,String> params);

    @FormUrlEncoded
    @POST("v2/sms/single_send.json")
    Call<String> postSMS(@Field("apikey")String apikey, @Field("text")String text,@Field("mobile")String mobile);
/*    @Multipart
    @POST("uploadServlet")
    Call<FileUpload> uploadFile(@PartMap Map<String, RequestBody> params);*/
}
