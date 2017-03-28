package com.ty.app.yxapp.dwcenter.ui.activities.base;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.ty.app.yxapp.dwcenter.ui.widget.ActionBar;

/**
 * Created by kss on 2017/3/26.
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        onBeforeCreate();

        LinearLayout container = new LinearLayout(this);
        container.setBackgroundColor(Color.WHITE);
        container.setOrientation(LinearLayout.VERTICAL);
        container.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        actionBar = new ActionBar(this);
        actionBar.setVisibility(View.GONE);
        container.addView(actionBar,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        FrameLayout frameLayout = new FrameLayout(this);
        container.addView(frameLayout,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        View view = onCreate();
        if(view != null){
            frameLayout.addView(view);
        }
        setContentView(container);
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
