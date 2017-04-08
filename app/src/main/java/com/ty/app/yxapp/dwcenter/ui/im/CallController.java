package com.ty.app.yxapp.dwcenter.ui.im;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Build;
import android.os.Vibrator;
import android.util.Log;

import com.hyphenate.chat.EMCallManager;
import com.hyphenate.util.EMLog;
import com.ty.app.yxapp.dwcenter.ui.activities.base.MyApplication;

import java.lang.reflect.Field;

/**
 * Created by kss on 2016/12/13.
 */

public class CallController {
    private static final String TAG = "CallController";
    private static volatile CallController Instance = null;
    private AudioManager audioManager;
    private SoundPool soundPool;
    private Ringtone ringtone;
    private Vibrator vibrator;

    public interface OnListener {
        void listener(int id);
    }

    public static CallController getInstance() {
        CallController localInstance = Instance;
        if (localInstance == null) {
            synchronized (CallController.class) {
                localInstance = Instance;
                if (localInstance == null) {
                    Instance = localInstance = new CallController();
                }
            }
        }
        return localInstance;
    }


    /**
     * 播放拨号响铃
     */
    public void playMakeCallSounds(Context context, int resId, final int loop, final OnListener onListener) {
        audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        try {
            final int currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

            audioManager.setMode(AudioManager.MODE_RINGTONE);
            audioManager.setSpeakerphoneOn(false);

            soundPool = new SoundPool(1, AudioManager.STREAM_SYSTEM, 0);
            soundPool.load(context, resId, 1);
            soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                @Override
                public void onLoadComplete(final SoundPool sound, final int sampleId, int status) {
                    int id = sound.play(sampleId, currentVolume, currentVolume, 1, loop, 1);
                    if (onListener != null) {
                        onListener.listener(id);
                    }
                }
            });
        } catch (Exception e) {
        }
    }


    public void stopCallSounds(int streamID) {
        try {
            if (soundPool != null) {
                soundPool.stop(streamID);
                soundPool.release();
            }
        } catch (Exception e) {
        }
    }

    public void openSpeakerOn(Context context) {
        try {
            if(audioManager == null){
                audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
            }
            if (!audioManager.isSpeakerphoneOn())
                audioManager.setSpeakerphoneOn(true);
            audioManager.setMode(AudioManager.MODE_IN_COMMUNICATION);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeSpeakerOn(Context context) {

        try {
            if(audioManager == null){
                audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
            }
            if (audioManager != null) {
                if (audioManager.isSpeakerphoneOn())
                    audioManager.setSpeakerphoneOn(false);
                audioManager.setMode(AudioManager.MODE_IN_COMMUNICATION);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void playCallMeSounds(Context context) {
        playTone(context);
        viberate(context);
    }


    public void viberate(Context context) {
        try {
            vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
            if(audioManager == null){
                audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
            }
            // 判断是否处于静音模式
            if (audioManager.getRingerMode() == AudioManager.RINGER_MODE_SILENT) {
                EMLog.e(TAG, "in slient mode now");
                return;
            }
            long[] pattern = new long[]{0, 180, 80, 120};
            vibrator.vibrate(pattern, -1);
        } catch (Exception e) {
            Log.e(TAG, "viberate",new Throwable(e));
        }
    }

    public void stopCallMeSounds() {
        try {
            if (ringtone != null && ringtone.isPlaying()) {
                ringtone.stop();
            }
        } catch (Exception e) {
        }

    }


    private void playTone(Context appContext) {
        try {
            if(audioManager == null){
                audioManager = (AudioManager) appContext.getSystemService(Context.AUDIO_SERVICE);
            }
            // 判断是否处于静音模式
            if (audioManager.getRingerMode() == AudioManager.RINGER_MODE_SILENT) {
                Log.d(TAG, "in slient mode now");
                return;
            }

            if (ringtone == null) {
                Uri notificationUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                audioManager.setMode(AudioManager.MODE_RINGTONE);
                audioManager.setSpeakerphoneOn(true);
                ringtone = RingtoneManager.getRingtone(appContext, notificationUri);

                Class<Ringtone> clazz = Ringtone.class;
                try {
                    Field audio = clazz.getDeclaredField("mLocalPlayer");
                    audio.setAccessible(true);
                    MediaPlayer target = (MediaPlayer) audio.get(ringtone);
                    target.setLooping(true);
                } catch (NoSuchFieldException e) {
                } catch (IllegalArgumentException e) {
                } catch (IllegalAccessException e) {
                }

                if (ringtone == null) {
                    return;
                }
            }

            if (!ringtone.isPlaying()) {
                String vendor = Build.MANUFACTURER;
                ringtone.play();

                if (vendor != null && vendor.toLowerCase().contains("samsung")) {
                    Thread ctlThread = new Thread() {
                        public void run() {
                            try {
                                Thread.sleep(3000);
                                if (ringtone.isPlaying()) {
                                    ringtone.stop();
                                }
                            } catch (Exception e) {
                                Log.e(TAG, "playTone",new Throwable(e));
                            }
                        }
                    };
                    ctlThread.run();
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "playTone",new Throwable(e));
        }
    }


    public void endCall() {
        CallManager.getInstance().endCall();
    }

    public void answerCall() {
        CallManager.getInstance().answerCall();
    }

    public void rejectCall() {
        CallManager.getInstance().rejectCall();
    }

    public void switchCamera() {
        CallManager.getInstance().switchCamera();
    }

    public void makeVideoCall(String username) {
        CallManager.getInstance().makeVideoCall(username);
    }

    public void makeVoiceCall(String username) {
        CallManager.getInstance().makeVoiceCall(username);
    }

    public void resumeVoiceTransfer() {
        CallManager.getInstance().resumeVoiceTransfer();
    }

    public void pauseVoiceTransfer() {
        CallManager.getInstance().pauseVoiceTransfer();
    }

    public void setIsSendPushIfOffline(boolean set) {
        CallManager.getInstance().setIsSendPushIfOffline(set);
    }

    public void setVideoResolution(int w, int h) {
        CallManager.getInstance().setVideoResolution(w, h);
    }

    public void setCameraFacing(int face) {
        CallManager.getInstance().setCameraFacing(face);
    }

    public void setCameraDataProcessor(EMCallManager.EMCameraDataProcessor var1) {
        if (var1 != null)
            CallManager.getInstance().setCameraDataProcessor(var1);
    }


    public void setMaxVideoKbps(int arg){
        CallManager.getInstance().setMaxVideoKbps(arg);
    }

}
