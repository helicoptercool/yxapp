package com.ty.app.yxapp.dwcenter.ui.im;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hyphenate.chat.EMCallManager;
import com.hyphenate.chat.EMCallStateChangeListener;
import com.hyphenate.media.EMLocalSurfaceView;
import com.hyphenate.media.EMOppositeSurfaceView;
import com.superrtc.sdk.VideoView;
import com.ty.app.yxapp.dwcenter.R;
import com.ty.app.yxapp.dwcenter.ui.activities.base.BaseActivity;
import com.ty.app.yxapp.dwcenter.ui.activities.base.LayoutHelper;
import com.ty.app.yxapp.dwcenter.ui.activities.base.MyApplication;
import com.ty.app.yxapp.dwcenter.ui.widget.CircleImageView;
import com.ty.app.yxapp.dwcenter.utils.AndroidUtils;

/**
 * Created by kss on 2017/4/8.
 */

public class VideoChatActivity extends BaseActivity implements View.OnClickListener{
    private static final String TAG = "VideoChatActivity";

    private static final int REQUEST_CAMERA_CODE = 998;
    public static final int FLAG_IN = 1;
    public static final int FLAG_OUT = 2;
    private Context context;
    private String userName;
    private int flag;


    private EMCallStateChangeListener callStateListener;
    private int streamID;
    private EMCallManager.EMVideoCallHelper callHelper;
    private EMOppositeSurfaceView remotePeople;
    private EMLocalSurfaceView local;
    private FrameLayout container;
    private LinearLayout btnCon;
    private LinearLayout btnContainer;
    private CallCell narrow;
    private CallCell sendCancle;
    private CallCell accept;
    private CallCell waitCancle;
    private CallCell acceptEdCancel;
    private CallCell swit;
    private LinearLayout headView;
    private boolean isAnswered;
    private boolean isMeEndCall;
    private TextView callTip;
    private TextView tip;
    private TextView name;
    private TextView time;
    private CircleImageView avatar;
    private Point point;
    private ImageView backImg;
    private ImageView cover;
    private RelativeLayout backCon;
    private boolean isCalled;
    private boolean isShowBtn = true;//是否显示操作按钮
    private boolean isCheck = false;//切换摄像头图片
    private RelativeLayout content;
    private LinearLayout coverLin;

    @Override
    public void onBeforeCreate() {

    }


    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == 0) {
                startTime();
            } else if (msg.what == 1) {
                stopTime();
            }
            return true;
        }
    });

    private int voiceTime = 0;
    private Runnable run;

    private void startTime() {
        voiceTime = 0;
        run = new Runnable() {
            @Override
            public void run() {
                voiceTime++;
                setTime();
                MyApplication.handler.postDelayed(this, 1000);
            }
        };

        MyApplication.handler.postDelayed(run, 1000);
    }

    private int stopTime() {
        int time = voiceTime;
        voiceTime = 0;
        if (this.time != null) {
            this.time.setText("");
            this.time.setVisibility(View.GONE);
            MyApplication.handler.removeCallbacks(run);
        }
        return time;
    }


    private void setTime() {
        int m = voiceTime / 60;
        int s = voiceTime % 60;
        time.setText(String.format("%02d:%02d", m, s));
    }




    @Override
    public View onCreate() {
        actionBar.setVisibility(View.GONE);
        userName = getIntent().getStringExtra("userName");
        flag = getIntent().getIntExtra("flag",0);

        this.context = getBaseContext();
        actionBar.setVisibility(View.GONE);
        container = new FrameLayout(context);
        container.setBackgroundColor(0xFF202026);

        callTip = new TextView(context);
        callTip.setTextColor(0xFFFFFFFF);
        callTip.setTextSize(16);
        callTip.setVisibility(View.GONE);
        container.addView(callTip, LayoutHelper.createFrame(LayoutHelper.WRAP_CONTENT, LayoutHelper.WRAP_CONTENT, Gravity.CENTER));

        remotePeople = new EMOppositeSurfaceView(context);
        remotePeople.setVisibility(View.GONE);
        remotePeople.setScaleMode(VideoView.EMCallViewScaleMode.EMCallViewScaleModeAspectFill);
        remotePeople.setOnClickListener(this);
        container.addView(remotePeople, LayoutHelper.createFrame(0, 0));

        content = new RelativeLayout(context);
        container.addView(content, LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT, LayoutHelper.MATCH_PARENT));

        backCon = new RelativeLayout(context);
        backCon.setVisibility(View.GONE);
        content.addView(backCon, LayoutHelper.createRelative(LayoutHelper.MATCH_PARENT, LayoutHelper.MATCH_PARENT));

        backImg = new ImageView(context);
        backImg.setScaleType(ImageView.ScaleType.CENTER_CROP);
        backCon.addView(backImg, LayoutHelper.createRelative(LayoutHelper.MATCH_PARENT, LayoutHelper.MATCH_PARENT));

        cover = new ImageView(context);
        cover.setScaleType(ImageView.ScaleType.FIT_XY);
        cover.setImageResource(R.mipmap.video_voice_cover);
        backCon.addView(cover, LayoutHelper.createRelative(LayoutHelper.MATCH_PARENT, LayoutHelper.MATCH_PARENT));

        local = new EMLocalSurfaceView(context);
        local.setScaleMode(VideoView.EMCallViewScaleMode.EMCallViewScaleModeAspectFill);
//        local.setOnTouchListener(touchListener);
        RelativeLayout.LayoutParams localRl = LayoutHelper.createRelative(0, 0);
        content.addView(local, localRl);

        coverLin = new LinearLayout(context);
        coverLin.setBackgroundResource(R.mipmap.ic_video_head_cover);
        RelativeLayout.LayoutParams headRl = LayoutHelper.createRelative(LayoutHelper.MATCH_PARENT, LayoutHelper.WRAP_CONTENT);
        headRl.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        content.addView(coverLin, headRl);

        headView = new LinearLayout(context);
        headView.setOrientation(LinearLayout.HORIZONTAL);
        headView.setGravity(Gravity.CENTER_VERTICAL);
        coverLin.addView(headView, LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT, LayoutHelper.WRAP_CONTENT, 0, 40, 0, 0));

        avatar = new CircleImageView(context);
        avatar.setImageResource(R.mipmap.ic_default_avatar);
        headView.addView(avatar, LayoutHelper.createLinear(74, 74, 15, 0, 0, 0));

        LinearLayout headContent = new LinearLayout(context);
        headContent.setOrientation(LinearLayout.VERTICAL);
        headContent.setGravity(Gravity.CENTER_VERTICAL);
        headView.addView(headContent, LayoutHelper.createLinear(LayoutHelper.WRAP_CONTENT, LayoutHelper.WRAP_CONTENT, Gravity.CENTER_VERTICAL, 15, 0, 0, 0));

        name = new TextView(context);
        name.setTextSize(24);
        name.setText(AndroidUtils.getString(R.string.loading));
        name.setTextColor(0xFFFFFFFF);
        name.setSingleLine();
        name.setEllipsize(TextUtils.TruncateAt.END);
        headContent.addView(name, LayoutHelper.createLinear(LayoutHelper.WRAP_CONTENT, LayoutHelper.WRAP_CONTENT, 0, 0, 0, 8));

        tip = new TextView(context);
        tip.setTextSize(14);
        tip.setText(AndroidUtils.getString(R.string.loading));
        tip.setTextColor(0xFFFFFFFF);
        headContent.addView(tip, LayoutHelper.createLinear(LayoutHelper.WRAP_CONTENT, LayoutHelper.WRAP_CONTENT));


        btnCon = new LinearLayout(context);
        btnCon.setId(R.id.btncon);
        btnCon.setOrientation(LinearLayout.VERTICAL);
        RelativeLayout.LayoutParams conRl = LayoutHelper.createRelative(LayoutHelper.MATCH_PARENT, LayoutHelper.WRAP_CONTENT);
        conRl.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        content.addView(btnCon, conRl);

        time = new TextView(context);
        time.setTextColor(0xFFFFFFFF);
        time.setTextSize(16);
        btnCon.addView(time, LayoutHelper.createLinear(LayoutHelper.WRAP_CONTENT, LayoutHelper.WRAP_CONTENT, Gravity.CENTER_HORIZONTAL, 0, 0, 0, 15));

        LinearLayout bottomCover = new LinearLayout(context);
        bottomCover.setBackgroundResource(R.mipmap.ic_video_bottom_cover);
        btnCon.addView(bottomCover, LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT, LayoutHelper.WRAP_CONTENT));

        btnContainer = new LinearLayout(context);
        btnContainer.setGravity(Gravity.CENTER);
        bottomCover.addView(btnContainer, LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT, LayoutHelper.WRAP_CONTENT, Gravity.BOTTOM, 0, 0, 0, 20));

        checkCameraPermissionsOrStart();
        return container;
    }

    private void checkCameraPermissionsOrStart() {
        if (Build.VERSION.SDK_INT >= 23
                && (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                || checkSelfPermission(Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED)) {
            requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO,Manifest.permission.MODIFY_AUDIO_SETTINGS}, REQUEST_CAMERA_CODE);
            return;
        }
        init();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CAMERA_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //同意
                init();
            } else {
                //拒绝
                MyApplication.handler.removeCallbacks(run);
                CallManager.getInstance().removeCallStateChangeListener(callStateListener);
                Toast.makeText(context, AndroidUtils.getString(R.string.err_request_permission), Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }

    private void init() {
        name.setText(userName);
        callHelper = CallManager.getInstance().getVideoCallHelper();
        CallController.getInstance().setIsSendPushIfOffline(false);

        addCallStateListener();
        if (flag == FLAG_OUT) {// 拨打电话
            initSurface();
            tip.setText(AndroidUtils.getString(R.string.call_out_tip));
            addSendView();
            CallController.getInstance().makeVideoCall(userName);
            new Thread((new Runnable() {
                @Override
                public void run() {
                    CallController.getInstance().playMakeCallSounds(context, R.raw.em_outgoing, -1, new CallController.OnListener() {
                        @Override
                        public void listener(int id) {
                            streamID = id;
                        }
                    });
                }
            })).start();

        } else { // 有电话进来
            initLoaclSurface(View.GONE);
            tip.setText(AndroidUtils.getString(R.string.call_in_tip));
            addAcceptViews();
            CallController.getInstance().playCallMeSounds(context);
        }

        CallManager.getInstance().setSurfaceView(local, remotePeople);
    }


    private void initLoaclSurface(int vis) {
        RelativeLayout.LayoutParams localRl = LayoutHelper.createRelative(100, 120);
        localRl.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        localRl.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        local.setLayoutParams(localRl);
        local.setZOrderOnTop(true);
        local.setZOrderMediaOverlay(true);
        local.setVisibility(vis);
    }


    private void initSurface() {
        MyApplication.handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                remotePeople.setLayoutParams(LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT, LayoutHelper.MATCH_PARENT));
                local.setZOrderOnTop(true);
                local.setZOrderMediaOverlay(true);
                local.setLayoutParams(LayoutHelper.createRelative(LayoutHelper.MATCH_PARENT, LayoutHelper.MATCH_PARENT));
                remotePeople.setVisibility(View.INVISIBLE);
                local.setVisibility(View.INVISIBLE);
            }
        }, 100);
    }

    private void connected() {
        isCalled = true;
        headView.setVisibility(View.GONE);
        remotePeople.setVisibility(View.VISIBLE);
        remotePeople.setLayoutParams(LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT, LayoutHelper.MATCH_PARENT));
        initLoaclSurface(View.VISIBLE);
    }


    private void showSurface(){
        if(flag == FLAG_OUT){
            backCon.setVisibility(View.GONE);
            remotePeople.setVisibility(View.VISIBLE);
            local.setVisibility(View.VISIBLE);
        }
    }


    private void addCallStateListener() {
        callStateListener = new EMCallStateChangeListener() {

            @Override
            public void onCallStateChanged(CallState callState, final CallError fError) {
                switch (callState) {
                    case CONNECTING: // 正在连接对方
                        Log.d(TAG, "正在连接对方...");
                        break;
                    case CONNECTED: // 双方已经建立连接
                        MyApplication.handler.post(new Runnable() {
                            @Override
                            public void run() {
                                showSurface();
                            }
                        });
                        Log.d(TAG, "双方已经建立连接,等待对方接受...");
                        break;
                    case ACCEPTED: // 电话接通成功
                        MyApplication.handler.post(new Runnable() {
                            @Override
                            public void run() {
                                connected();
                                addAcceptEdViews();
                                CallController.getInstance().openSpeakerOn(context);
                                CallController.getInstance().stopCallMeSounds();
                                CallController.getInstance().stopCallSounds(streamID);
                            }
                        });
                        handler.sendEmptyMessage(0);
                        Log.d(TAG, "电话接通成功...");
                        break;
                    case NETWORK_UNSTABLE:
                        Log.d(TAG, "NETWORK_UNSTABLE...");
                        if (fError == CallError.ERROR_NO_DATA) { //没有数据
                            setCallTip(AndroidUtils.getString(R.string.no_data));
                        } else { //网络不稳定
                            setCallTip(getString(R.string.no_newwork));
                        }
                        break;
                    case NETWORK_NORMAL:
                        Log.d(TAG, "NETWORK_NORMAL...");
                        break;
                    case DISCONNECTED:
                        Log.d(TAG, "DISCONNECTED...");
                        if (fError == CallError.REJECTED) {//对方拒绝
                            setCallTip(AndroidUtils.getString(R.string.call_other_refuse));
                            Log.d(TAG, "REJECTED...");
                            BuideCallMessage("", AndroidUtils.getString(R.string.call_other_refuse), FLAG_IN);
                        } else if (fError == CallError.ERROR_TRANSPORT) {//连接建立失败
                            setCallTip(AndroidUtils.getString(R.string.call_error));
                        } else if (fError == CallError.ERROR_UNAVAILABLE) {//对方不在线
                            setCallTip(AndroidUtils.getString(R.string.call_other_no_online));
                            BuideCallMessage("", AndroidUtils.getString(R.string.call_other_no_online), flag);
                        } else if (fError == CallError.ERROR_BUSY) {//对方正在通话中
                            setCallTip(AndroidUtils.getString(R.string.call_other_calling));
                        } else if (fError == CallError.ERROR_NORESPONSE) {//对方未接听
                            setCallTip(AndroidUtils.getString(R.string.call_other_no_listener));
                            BuideCallMessage("", AndroidUtils.getString(R.string.call_other_no_listener), flag);
                        } else if (fError == CallError.ERROR_LOCAL_SDK_VERSION_OUTDATED || fError == CallError.ERROR_REMOTE_SDK_VERSION_OUTDATED) {//通话协议版本不一致
                        } else {
                            if (isAnswered) {//已接听
                                if (!isMeEndCall) { //对方挂断
                                    handler.sendEmptyMessage(1);
                                    setCallTip(AndroidUtils.getString(R.string.call_other_hang_up));
                                    BuideCallMessage("", getString(R.string.call_time,time.getText().toString()), FLAG_IN);
                                }
                            } else {
                                if (flag == FLAG_OUT) {
                                    if (isCalled && !isMeEndCall) {
                                        setCallTip(AndroidUtils.getString(R.string.call_other_hang_up));
                                        BuideCallMessage("",getString(R.string.call_time,time.getText().toString()) , FLAG_IN);
                                    }
                                }else{
                                    setCallTip(AndroidUtils.getString(R.string.un_listen));
                                    BuideCallMessage("", getString(R.string.un_listen) , FLAG_IN);
                                }
                            }
                        }
                        MyApplication.handler.post(new Runnable() {
                            @Override
                            public void run() {
                                CallController.getInstance().stopCallMeSounds();
                                CallController.getInstance().stopCallSounds(streamID);
                                CallController.getInstance().closeSpeakerOn(context);
                                finish();
                            }
                        });
                        break;
                    default:
                        break;
                }

            }
        };
        CallManager.getInstance().addCallStateChangeListener(callStateListener);
    }



    private void setCallTip(final String str) {
        MyApplication.handler.post(new Runnable() {
            @Override
            public void run() {
                callTip.setVisibility(View.VISIBLE);
                callTip.setText(str);
                Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        CallController.getInstance().endCall();
        if(isCalled){
            BuideCallMessage("", getString(R.string.call_time, time.getText().toString()), FLAG_OUT);
        }else{
            BuideCallMessage("",getString(R.string.cancle),FLAG_OUT);
        }
        CallManager.getInstance().removeCallStateChangeListener(callStateListener);
        CallController.getInstance().stopCallSounds(streamID);
        CallController.getInstance().stopCallMeSounds();
    }


    private void clearChildren() {
        if (container != null && container.getChildCount() > 0) {
            try {
                container.removeView(remotePeople);
            } catch (Exception e) {
            }
        }
        if (content != null && content.getChildCount() > 0) {
            try {
                content.removeView(local);
            } catch (Exception e) {
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v == narrow) {

        } else if (v == sendCancle) {
            clearChildren();
            CallController.getInstance().endCall();
            CallController.getInstance().stopCallSounds(streamID);
            BuideCallMessage("", AndroidUtils.getString(R.string.cancle), flag);
            MyApplication.handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish ();
                }
            }, 50);
        } else if (v == accept) {
            backCon.setVisibility(View.GONE);
            isAnswered = true;
            CallController.getInstance().answerCall();
            CallController.getInstance().stopCallMeSounds();
        } else if (v == waitCancle) { //拒绝
            clearChildren();
            CallController.getInstance().rejectCall();
            CallController.getInstance().stopCallMeSounds();
            BuideCallMessage("", AndroidUtils.getString(R.string.cancle), FLAG_OUT);
            handler.sendEmptyMessage(1);
            MyApplication.handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            }, 50);
        } else if (v == acceptEdCancel) {
            isMeEndCall = true;
            clearChildren();
            CallController.getInstance().endCall();
            BuideCallMessage("", getString(R.string.call_time, time.getText().toString()), FLAG_OUT);
            MyApplication.handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            }, 50);
        } else if (v == swit) {
            if (!isCheck) {
                swit.setImg(R.mipmap.chat_switch);
                isCheck = true;
            } else {
                isCheck = false;
                swit.setImg(R.mipmap.chat_switch_pressed);
            }
            CallController.getInstance().switchCamera();
        } else if (v == remotePeople) {
            if (isCalled) {
                if (isShowBtn) {
//                    time.setVisibility(View.GONE);
//                    btnContainer.setVisibility(View.GONE);
                    startDownAmin();
                    isShowBtn = false;
                } else {
//                    time.setVisibility(View.GONE);
//                    btnContainer.setVisibility(View.VISIBLE);
                    startUpAnim();
                    isShowBtn = true;
                }
            }
        }
    }


    private float btnCony = 0;

    private void startDownAmin() {
        if (btnCony == 0) {
            btnCony = btnCon.getY();
        }

        Log.d("kss", "translateY:" + btnCony);
        ObjectAnimator anim = ObjectAnimator.ofFloat(btnCon, "y", btnCon.getY(), AndroidUtils.getRealScreenSize().y);

        AnimatorSet set = new AnimatorSet();
        set.setDuration(300);
        set.play(anim);
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                btnCon.setVisibility(View.INVISIBLE);
            }
        });
        set.start();
    }

    private void startUpAnim() {

        ObjectAnimator anim = ObjectAnimator.ofFloat(btnCon, "y", AndroidUtils.getRealScreenSize().y, btnCony);

        AnimatorSet set = new AnimatorSet();
        set.setDuration(300);
        set.play(anim);
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                btnCon.setVisibility(View.VISIBLE);
            }
        });
        set.start();
    }


    private void BuideCallMessage(String user, String message, int dir) {
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }

    private void addSendView() {
        if (btnContainer.getChildCount() > 0) {
            btnContainer.removeAllViews();
        }
        sendCancle = new CallCell(context);
        sendCancle.setSelect(R.drawable.video_cancle_selector);
        sendCancle.setText(getString(R.string.cancel));
        sendCancle.setOnClickListener(this);
        btnContainer.addView(sendCancle, LayoutHelper.createLinear(LayoutHelper.WRAP_CONTENT, LayoutHelper.WRAP_CONTENT, Gravity.CENTER));
    }

    private void addAcceptViews() {
        if (btnContainer.getChildCount() > 0) {
            btnContainer.removeAllViews();
        }
        waitCancle = new CallCell(context);
        waitCancle.setSelect(R.drawable.video_cancle_selector);
        waitCancle.setOnClickListener(this);
        waitCancle.setText(AndroidUtils.getString(R.string.cancel));
        btnContainer.addView(waitCancle, LayoutHelper.createLinear(LayoutHelper.WRAP_CONTENT, LayoutHelper.WRAP_CONTENT, Gravity.CENTER, 0, 0, 40, 0));

        accept = new CallCell(context);
        accept.setOnClickListener(this);
        accept.setText(AndroidUtils.getString(R.string.accept));
        accept.setSelect(R.drawable.video_accept_selector);
        btnContainer.addView(accept, LayoutHelper.createLinear(LayoutHelper.WRAP_CONTENT, LayoutHelper.WRAP_CONTENT, Gravity.CENTER, 40, 0, 0, 0));
    }

    private void addAcceptEdViews() {
        if (backCon.getVisibility() == View.VISIBLE) backCon.setVisibility(View.GONE);
        if (btnContainer.getChildCount() > 0) {
            btnContainer.removeAllViews();
        }

        narrow = new CallCell(context);
        narrow.setImg(R.mipmap.chat_narrow);
        narrow.setText(AndroidUtils.getString(R.string.narrow));
        narrow.setOnClickListener(this);
        narrow.setVisibility(View.GONE);
        btnContainer.addView(narrow, LayoutHelper.createLinear(LayoutHelper.WRAP_CONTENT, LayoutHelper.WRAP_CONTENT, Gravity.CENTER, 0, 0, 50, 0));

        acceptEdCancel = new CallCell(context);
        acceptEdCancel.setText(AndroidUtils.getString(R.string.cancel));
        acceptEdCancel.setOnClickListener(this);
        acceptEdCancel.setSelect(R.drawable.video_cancle_selector);
        btnContainer.addView(acceptEdCancel, LayoutHelper.createLinear(LayoutHelper.WRAP_CONTENT, LayoutHelper.WRAP_CONTENT, Gravity.CENTER));

        swit = new CallCell(context);
        swit.setImg(R.drawable.video_switch_selector);
        swit.setText(AndroidUtils.getString(R.string.swit));
        swit.setOnClickListener(this);
        btnContainer.addView(swit, LayoutHelper.createLinear(LayoutHelper.WRAP_CONTENT, LayoutHelper.WRAP_CONTENT, Gravity.CENTER, 50, 0, 0, 0));
    }


}
