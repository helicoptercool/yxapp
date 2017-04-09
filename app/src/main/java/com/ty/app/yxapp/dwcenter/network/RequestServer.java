package com.ty.app.yxapp.dwcenter.network;

import com.ty.app.yxapp.dwcenter.bean.Event;
import com.ty.app.yxapp.dwcenter.bean.EventReport;
import com.ty.app.yxapp.dwcenter.bean.FileUpload;
import com.ty.app.yxapp.dwcenter.bean.OrgDataInfo;
import com.ty.app.yxapp.dwcenter.bean.StringResult;
import com.ty.app.yxapp.dwcenter.bean.UserInfo;

import java.io.File;
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
    Call<StringResult> getLoginStatus(@Query("name") String username, @Query("password") String password);

    @GET("caseplatform/mobile/system-eventapp!setUserPassword.action")
    Call<StringResult> getResetPwdStatus(@Query("name") String username, @Query("newPassword") String newPwd, @Query("oldPassword") String oldPwd);

    @GET("caseplatform/mobile/system-eventapp!getEventInfo.action")
    Call<Event> getEvents(@Query("account") String account, @Query("eventType") String eventType);

    @GET("caseplatform/mobile/system-eventapp!reportEventInfo.action ")
    Call<EventReport> reportEvent(
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

    @GET("caseplatform/mobile/system-eventapp!setUserPassword.action")
    Call<StringResult> setPassword(@Query("account") String account,
                                   @Query("newPassword") String newPassword,
                                   @Query("oldPassword") String oldPassword);

    @GET("caseplatform/mobile/video-upload!uplodVideo.action")
    Call<FileUpload> uploadVideo(@Query("video")File video, @Query("videoFileName")String videoFileName);

    @GET("caseplatform/mobile/video -upload!uplodAudio.action")
    Call<FileUpload> uploadAudio(@Query("audio")File audio, @Query("audioFileName")String audioFileName);


/*    @Multipart
    @POST("uploadServlet")
    Call<FileUpload> uploadFile(@PartMap Map<String, RequestBody> params);*/
}
