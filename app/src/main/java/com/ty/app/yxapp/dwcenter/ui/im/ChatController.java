package com.ty.app.yxapp.dwcenter.ui.im;

import android.content.Context;
import android.util.Log;
import com.hyphenate.EMCallBack;
import com.hyphenate.EMConnectionListener;
import com.hyphenate.EMError;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.exceptions.HyphenateException;

/**
 * Created by kss on 2017/4/8.
 */

public class ChatController {
    private static final String TAG = "ChatController";
    private static ChatController instance;


    public static ChatController getIntance(){
        if(instance == null){
            synchronized (ChatController.class){
                if(instance == null){
                    instance = new ChatController();
                }
            }
        }
        return instance;
    }

    public void init(Context context) {
        Log.d(TAG,"huanxin init...");
        EMOptions options = new EMOptions();
        options.setAcceptInvitationAlways(true);
        options.setRequireAck(true);
        options.setRequireDeliveryAck(true);

        EMClient.getInstance().init(context, options);
        EMClient.getInstance().setDebugMode(true);

        registerListener();

//        IntentFilter callFilter = new IntentFilter(EMClient.getInstance().callManager().getIncomingCallBroadcastAction());
//        CallReceiver callReceiver = new CallReceiver();
//        context.registerReceiver(callReceiver, callFilter);

    }




    private void registerListener() {
        EMConnectionListener connectionListener = new EMConnectionListener() {

            @Override
            public void onDisconnected(int error) {
                Log.d(TAG,"huanxin onDisconnected :"+error);
                if (error == EMError.USER_REMOVED || error == EMError.USER_LOGIN_ANOTHER_DEVICE) {

                } else {

                }
            }

            @Override
            public void onConnected() {
                Log.d(TAG,"huanxin onConnected ");
            }
        };

        EMClient.getInstance().addConnectionListener(connectionListener);
    }

    public void createAccount(final String username,final String pwd){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    EMClient.getInstance().createAccount(username, pwd);//同步方法
                } catch (HyphenateException e) {
                    Log.e(TAG,"createAccount :",new Throwable(e));
                    e.printStackTrace();
                }
            }
        }).start();

    }


    public void login(String id, String pwd, final Callback callback) {
        EMClient.getInstance().login(id, pwd, new EMCallBack() {
            @Override
            public void onSuccess() {
                if (callback != null) callback.success();
            }

            @Override
            public void onProgress(int progress, String status) {

            }

            @Override
            public void onError(int code, String message) {
                if (callback != null) callback.failure(code,message);
            }
        });
    }

    public void logout(final Callback callback) {
        EMClient.getInstance().logout(true, new EMCallBack() {

            @Override
            public void onSuccess() {
                if (callback != null)
                    callback.success();
            }

            @Override
            public void onProgress(int progress, String status) {
            }

            @Override
            public void onError(int code, String message) {
                if (callback != null)
                    callback.failure(code,message);
            }
        });
    }

    public interface Callback{
        void success();
        void failure(int code , String message);
    }



}
