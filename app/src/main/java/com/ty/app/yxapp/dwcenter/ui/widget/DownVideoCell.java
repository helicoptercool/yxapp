package com.ty.app.yxapp.dwcenter.ui.widget;

import android.content.Context;
import android.view.Gravity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ty.app.yxapp.dwcenter.R;
import com.ty.app.yxapp.dwcenter.ui.activities.base.LayoutHelper;
import com.ty.app.yxapp.dwcenter.ui.activities.base.MyApplication;
import com.ty.app.yxapp.dwcenter.utils.AndroidUtils;

/**
 * Created by kss on 16/8/28.
 */
public class DownVideoCell extends FrameLayout{

    private final Animation animation;
    private ImageView icon;
    private TextView textView;

    public DownVideoCell(Context context) {
        super(context);
        animation = AnimationUtils.loadAnimation(context, R.anim.loading);
        animation.setDuration(800);

        LinearLayout container = new LinearLayout(context);
        container.setLayoutParams(LayoutHelper.createRelative(LayoutHelper.MATCH_PARENT, LayoutHelper.MATCH_PARENT));
//        container.setBackgroundResource(R.mipmap.bg_loading);
        container.setGravity(Gravity.CENTER);
        container.setOrientation(LinearLayout.VERTICAL);

        addView(container,LayoutHelper.createFrame(LayoutHelper.WRAP_CONTENT,LayoutHelper.WRAP_CONTENT));

        icon = new ImageView(context);
        icon.setScaleType(ImageView.ScaleType.CENTER);
        LinearLayout.LayoutParams iconLP = LayoutHelper.createLinear(80, 80);
        container.addView(icon, iconLP);

        textView = new TextView(context);
        textView.setText(AndroidUtils.getString(R.string.loading));
        textView.setTextSize(14);
        textView.setTextColor(0xFF444555);
        LinearLayout.LayoutParams textLP = LayoutHelper.createLinear(LayoutHelper.WRAP_CONTENT, LayoutHelper.WRAP_CONTENT, 0, 5, 0, 0);
        container.addView(textView, textLP);

    }

    public void show(){
        icon.setImageResource(R.mipmap.ic_loading);
        icon.startAnimation(animation);
//        ApplicationLoader.glide.load(R.mipmap.ic_loading).into(icon);
    }

    public void show(String text){
        icon.setImageResource(R.mipmap.ic_loading);
        icon.startAnimation(animation);
//        ApplicationLoader.glide.load(R.mipmap.ic_loading).into(icon);
        textView.setText(text);
    }

    public void success(int delay){
        show();
        icon.setImageResource(R.mipmap.ic_successed);
        icon.clearAnimation();
        textView.setText(AndroidUtils.getString(R.string.success));

        MyApplication.handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dismiss();
            }
        }, delay);
    }

    public void progress(int percent) {
        if(percent == 100) percent = 99;
        textView.setText(percent + "%");
    }

    public void dismiss() {
        icon.clearAnimation();
        icon.setImageResource(R.drawable.transparent);
    }



}
