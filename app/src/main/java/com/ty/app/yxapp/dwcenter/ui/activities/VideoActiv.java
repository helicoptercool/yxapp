package com.ty.app.yxapp.dwcenter.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.VideoView;


import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.ty.app.yxapp.dwcenter.R;
import com.ty.app.yxapp.dwcenter.ui.activities.base.BaseActivity;
import com.ty.app.yxapp.dwcenter.ui.activities.base.LayoutHelper;
import com.ty.app.yxapp.dwcenter.ui.activities.base.MyApplication;
import com.ty.app.yxapp.dwcenter.ui.widget.DownVideoCell;
import com.ty.app.yxapp.dwcenter.utils.AndroidUtils;

/**
 * Created by kss on 2017/4/19.
 */

public class VideoActiv extends BaseActivity implements View.OnClickListener {

    public static final String TAG = "MomentVideoActivity";
    private Context context;
    private VideoView videoView;
    private ImageView close;
    private ImageView playBtn;
    private String uri;
    private DownVideoCell loading;
    private ProgressBar progressBar;
    private int maxProgress;

    @Override
    public void onBeforeCreate() {
        Intent arguments = getIntent();
        uri = arguments.getStringExtra("uri");
    }



    @Override
    public View onCreate() {
        this.context = getBaseContext();
        actionBar.setVisibility(View.GONE);
        RelativeLayout container = new RelativeLayout(context);
        container.setBackgroundColor(0xFF202026);

        videoView = new VideoView(context.getApplicationContext());
        container.addView(videoView, LayoutHelper.createRelative(LayoutHelper.MATCH_PARENT, LayoutHelper.WRAP_CONTENT, RelativeLayout.CENTER_IN_PARENT));

        close = new ImageView(context);
        close.setVisibility(View.GONE);
        close.setImageResource(R.mipmap.ic_camera_close);
        close.setOnClickListener(this);
        close.setScaleType(ImageView.ScaleType.CENTER);
        RelativeLayout.LayoutParams closeRl = LayoutHelper.createRelative(36, 36);
        closeRl.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        closeRl.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        LayoutHelper.setMargins(closeRl, AndroidUtils.dp(5), AndroidUtils.dp(5), 0, 0);
        container.addView(close, closeRl);

        loading = new DownVideoCell(context);
        container.addView(loading, LayoutHelper.createRelative(LayoutHelper.WRAP_CONTENT, LayoutHelper.WRAP_CONTENT, RelativeLayout.CENTER_IN_PARENT));

        playBtn = new ImageView(context);
        playBtn.setImageResource(R.mipmap.ic_moment_play);
        playBtn.setOnClickListener(this);
        playBtn.setVisibility(View.GONE);
        container.addView(playBtn, LayoutHelper.createRelative(LayoutHelper.WRAP_CONTENT, LayoutHelper.WRAP_CONTENT, RelativeLayout.CENTER_IN_PARENT));

        LayoutInflater inflater = LayoutInflater.from(context);
        LinearLayout progress = (LinearLayout) inflater.inflate(R.layout.progress_bar, null);
        progressBar = (ProgressBar) progress.findViewById(R.id.my_progress);
        progressBar.setVisibility(View.GONE);
        container.addView(progress, LayoutHelper.createRelative(LayoutHelper.MATCH_PARENT, LayoutHelper.WRAP_CONTENT, RelativeLayout.ALIGN_PARENT_BOTTOM));

        init();
        return container;
    }


    private void init() {
        loading.show();
        try {
            videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mp, int what, int extra) {
                    Toast.makeText(context,"视频加载失败",Toast.LENGTH_SHORT).show();
                    return true;
                }
            });

            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    loading.dismiss();
                    close.setVisibility(View.VISIBLE);
                   MyApplication.handler.removeCallbacks(runnable);
                    MyApplication.handler.postDelayed(runnable, 100);
                    if (progressBar != null) {
                        progressBar.setMax(mp.getDuration());
                        progressBar.setVisibility(View.VISIBLE);
                    }
                    maxProgress = mp.getDuration();
                }
            });
            videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    Log.e(TAG, "播放完成");
                    progressBar.setProgress(maxProgress);
                    MyApplication.handler.removeCallbacks(runnable);
                    playBtn.setVisibility(View.VISIBLE);
                }
            });
            if (!TextUtils.isEmpty(uri)) {
                playVideo();
            }
        } catch (Exception e) {
            Log.e(TAG, new Throwable(e).toString());
        }
    }

    private void playVideo() {
        try {
            if (!TextUtils.isEmpty(uri)) {
                videoView.setVideoURI(Uri.parse(uri));
                videoView.start();
            }
        } catch (Exception e) {
        }
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Log.e(TAG, "current position: " + videoView.getCurrentPosition());
            progressBar.setProgress(videoView.getCurrentPosition());
            MyApplication.handler.postDelayed(runnable, 100);
        }
    };

    @Override
    public void onClick(View v) {
        if (v == close) {
            if (videoView != null) {
                videoView.stopPlayback();
                finish();
            }
        } else if (v == playBtn) {
            videoView.start();
            MyApplication.handler.removeCallbacks(runnable);
            MyApplication.handler.postDelayed(runnable, 100);
            playBtn.setVisibility(View.GONE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (videoView != null) {
            videoView.resume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (videoView != null) {
            videoView.pause();
        }
    }


}


