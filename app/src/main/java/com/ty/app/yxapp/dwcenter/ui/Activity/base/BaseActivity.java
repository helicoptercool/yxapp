package com.ty.app.yxapp.dwcenter.ui.Activity.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

/**
 * Created by kss on 2017/3/26.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        onBeforeCreate();

        View view = onCreate();
        if(view != null){
            FrameLayout container = new FrameLayout(this);
            container.setBackgroundColor(0xFFFFFFFF);
            container.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));

            container.addView(view);
            setContentView(container);
        }
    }

    public abstract void onBeforeCreate();

    public abstract View onCreate();

    public void requestWindowFeature(boolean full){
        if(full){
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }else{
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

    }

}
