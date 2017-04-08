package com.ty.app.yxapp.dwcenter.ui.im;

import android.text.TextUtils;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMCallManager;
import com.hyphenate.chat.EMCallStateChangeListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.exceptions.EMNoActiveCallException;
import com.hyphenate.exceptions.EMServiceNotReadyException;
import com.hyphenate.exceptions.HyphenateException;
import com.hyphenate.media.EMLocalSurfaceView;
import com.hyphenate.media.EMOppositeSurfaceView;

import java.util.UUID;

/**
 * Created by kss on 2016/12/12.
 */

public class CallManager {
    private static volatile CallManager Instance = null;

    public static final int FLAG_CALL_IN = 1;
    public static final int FLAG_CALL_OUT = 2;

    private EMCallManager callManager;

    private void getCallManager() {
        callManager = EMClient.getInstance().callManager();
    }


    public static CallManager getInstance() {
        CallManager localInstance = Instance;
        if (localInstance == null) {
            synchronized (CallManager.class) {
                localInstance = Instance;
                if (localInstance == null) {
                    Instance = localInstance = new CallManager();
                }
            }
        }
        return localInstance;
    }

    public void addCallStateChangeListener(EMCallStateChangeListener listener) {
        if (callManager == null) {
            getCallManager();
        }
        if (listener != null)
            callManager.addCallStateChangeListener(listener);
    }

    public void removeCallStateChangeListener(EMCallStateChangeListener listener) {
        if (callManager == null) {
            getCallManager();
        }
        if (listener != null)
            callManager.removeCallStateChangeListener(listener);
    }

    public void answerCall() {
        if (callManager == null) {
            getCallManager();
        }
        try {
            callManager.answerCall();
        } catch (EMNoActiveCallException e) {
        }
    }

    public void endCall() {
        if (callManager == null) {
            getCallManager();
        }
        try {
            callManager.endCall();
        } catch (EMNoActiveCallException e) {
        }
    }

    public void makeVideoCall(String userName) {
        if (callManager == null) {
            getCallManager();
        }
        try {
            callManager.makeVideoCall(userName);
        } catch (EMServiceNotReadyException e) {
        }
    }

    public void makeVoiceCall(String userName) {
        if (callManager == null) {
            getCallManager();
        }
        try {
            callManager.makeVoiceCall(userName);
        } catch (EMServiceNotReadyException e) {
        }
    }

    /**
     * 实时通话时停止数据传输
     */
    public void pauseVideoTransfer() {
        if (callManager == null) {
            getCallManager();
        }
        try {
            callManager.pauseVideoTransfer();
        } catch (HyphenateException e) {
        }
    }

    public void pauseVoiceTransfer() {
        if (callManager == null) {
            getCallManager();
        }
        try {
            callManager.pauseVoiceTransfer();
        } catch (HyphenateException e) {
        }
    }

    /**
     * 拒绝接听
     */
    public void rejectCall() {
        if (callManager == null) {
            getCallManager();
        }
        try {
            callManager.rejectCall();
        } catch (EMNoActiveCallException e) {
        }
    }

    /**
     * 恢复数据传输
     */
    public void resumeVideoTransfer() {
        if (callManager == null) {
            getCallManager();
        }
        try {
            callManager.resumeVideoTransfer();
        } catch (HyphenateException e) {
        }
    }

    public void resumeVoiceTransfer() {
        if (callManager == null) {
            getCallManager();
        }
        try {
            callManager.resumeVoiceTransfer();
        } catch (HyphenateException e) {
        }
    }


    /**
     * 开启相机拍摄
     *
     * @param face facing	参数可以是CameraInfo.CAMERA_FACING_BACK, 或者CameraInfo.CAMERA_FACING_FRONT
     */
    public void setCameraFacing(int face) {
        if (callManager == null) {
            getCallManager();
        }
        try {
            callManager.setCameraFacing(face);
        } catch (HyphenateException e) {
        }
    }

    public void setSurfaceView(EMLocalSurfaceView localSurface, EMOppositeSurfaceView oppositeSurface) {
        if (callManager == null) {
            getCallManager();
        }
        try {
            callManager.setSurfaceView(localSurface, oppositeSurface);
        } catch (Exception e) {
        }
    }


    public void switchCamera() {
        if (callManager == null) {
            getCallManager();
        }
        try {
            callManager.switchCamera();
        } catch (Exception e) {
        }
    }


    public EMCallManager.EMVideoCallHelper getVideoCallHelper() {
        return EMClient.getInstance().callManager().getVideoCallHelper();
    }

    public void setCameraDataProcessor(EMCallManager.EMCameraDataProcessor var1) {
        if (var1 != null)
            EMClient.getInstance().callManager().setCameraDataProcessor(var1);
    }

    public void setMaxVideoKbps(int arg){
        if (callManager == null) {
            getCallManager();
        }
        try {
            callManager.getCallOptions().setMaxVideoKbps(arg);
        } catch (Exception e) {
        }
    }


    /**
     * 音视频呼叫对方，如果对方不在线，则发送一条离线消息通知对方（true推送，false不推送）
     */
    public void setIsSendPushIfOffline(boolean set){
        if (callManager == null) {
            getCallManager();
        }
        try {
            callManager.getCallOptions().setIsSendPushIfOffline(set);
        } catch (Exception e) {
        }
    }

    /**
     * 设置视频通话分辨率 默认是(640, 480)
     */
    public void setVideoResolution(int w,int h){
        if (callManager == null) {
            getCallManager();
        }
        try {
            callManager.getCallOptions().setVideoResolution(w, h);
        } catch (Exception e) {
        }
    }
}
