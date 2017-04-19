package com.ty.app.yxapp.dwcenter.ui.activities;

import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.ty.app.yxapp.dwcenter.R;
import com.ty.app.yxapp.dwcenter.ui.activities.base.BaseActivity;
import com.ty.app.yxapp.dwcenter.ui.activities.base.MyApplication;
import com.ty.app.yxapp.dwcenter.utils.SPManager;

/**
 * Created by kss on 2017/3/26.
 *
 * 引导页
 */

public class GuideActivity extends BaseActivity {

    private boolean isLogin;

    @Override
    public void onBeforeCreate() {
        requestWindowFeature(true);
    }

    @Override
    public View onCreate() {
        ImageView imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setBackgroundResource(R.mipmap.splash);
        FrameLayout.LayoutParams fl = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        fl.gravity = Gravity.CENTER;
        imageView.setLayoutParams(fl);

        MyApplication.handler.postDelayed(toMainRun,1000);
        SPManager spManager = new SPManager();
        if(spManager.isLogin()){
            isLogin = true;
        }
        return imageView;
    }

    private Runnable toMainRun = new Runnable() {
        @Override
        public void run() {
            Intent intent;
            if(isLogin){
                intent = new Intent(GuideActivity.this,MainActivity.class);
            }else {
                intent = new Intent(GuideActivity.this,LoginActivity.class);
            }
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
