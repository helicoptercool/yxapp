package com.ty.app.yxapp.dwcenter.ui.activities.base;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.ty.app.yxapp.dwcenter.ui.im.ChatController;

import java.util.List;
//import com.crashlytics.android.Crashlytics;
//import io.fabric.sdk.android.Fabric;

/**
 * Created by kss on 2017/3/26.
 */

public class MyApplication extends Application {
    public static Handler handler;
    public static Context context;
    private static String mCurrentProcessName;
    private static String UI_PROCESS_NAME = "com.ty.app.yxapp.dwcenter";

    @Override
    public void onCreate() {
        super.onCreate();
//        Fabric.with(this, new Crashlytics());
        init();
    }

    private void init(){
        handler = new Handler();
        context = getApplicationContext();

        if(isUIApplication(context)){
            ChatController.getIntance().init(context);
        }
    }

    public static boolean isUIApplication(Context context) {
        return UI_PROCESS_NAME.equals(getCurrentProcessName(context));
    }

    private static String getCurrentProcessName(Context context) {
        if (mCurrentProcessName == null) {
            synchronized (Application.class) {
                if (mCurrentProcessName == null) {
                    int myPid = android.os.Process.myPid();
                    ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
                    List<ActivityManager.RunningAppProcessInfo> listProcessInfo = activityManager.getRunningAppProcesses();
                    if (listProcessInfo != null) {
                        for (ActivityManager.RunningAppProcessInfo appProcessInfo : listProcessInfo) {
                            if (appProcessInfo.pid == myPid) {
                                mCurrentProcessName = appProcessInfo.processName;
                                break;
                            }
                        }
                    } else {
                        mCurrentProcessName = "";//赋值"" 防止空异常
                    }
                }
            }
        }
        return mCurrentProcessName;
    }

}
