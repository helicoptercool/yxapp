package com.ty.app.yxapp.dwcenter.ui.Activity;

import android.app.Application;
import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.ty.app.yxapp.dwcenter.ui.Activity.base.BaseActivity;
import com.ty.app.yxapp.dwcenter.ui.Activity.base.MyApplication;

/**
 * Created by kss on 2017/3/26.
 *
 * 引导页
 */

public class GuideActivity extends BaseActivity {
    @Override
    public void onBeforeCreate() {
        requestWindowFeature(true);
    }

    @Override
    public View onCreate() {
        ImageView imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setBackgroundResource(android.R.mipmap.sym_def_app_icon);
        FrameLayout.LayoutParams fl = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        fl.gravity = Gravity.CENTER;
        imageView.setLayoutParams(fl);

        MyApplication.handler.postDelayed(toMainRun,1000);
        return imageView;
    }

    private Runnable toMainRun = new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(GuideActivity.this,MainActivity.class);
            startActivity(intent);
            GuideActivity.this.finish();
        }
    };

    @Override
    protected void onDestroy() {
        MyApplication.handler.removeCallbacks(toMainRun);
        super.onDestroy();
    }
}
