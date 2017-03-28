package com.ty.app.yxapp.dwcenter.ui.Widget;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ty.app.yxapp.dwcenter.Utils.AndroidUtils;

/**
 * Created by kss on 2017/3/26.
 */

public class ActionBar extends LinearLayout {
    private final TextView leftImg;
    private final TextView centerImg;
    private final TextView rightImg;
    private Context context;

    public ActionBar(Context context) {
        super(context);
        this.context = context;
        setBackgroundColor(0xFFFFFFFF);
        FrameLayout container = new FrameLayout(context);
        addView(container,new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,AndroidUtils.dp(48)));

        leftImg = new TextView(context);
        leftImg.setVisibility(GONE);
        leftImg.setTextColor(0xff2D2D34);
        FrameLayout.LayoutParams lfl = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT);
        lfl.gravity = Gravity.LEFT|Gravity.CENTER_VERTICAL;
        lfl.setMargins(AndroidUtils.dp(10),0,0,0);
        container.addView(leftImg,lfl);

        centerImg = new TextView(context);
        centerImg.setVisibility(GONE);
        centerImg.setTextColor(0xff2D2D34);
        FrameLayout.LayoutParams cfl = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT);
        cfl.gravity = Gravity.CENTER;
        container.addView(centerImg,cfl);

        rightImg = new TextView(context);
        rightImg.setTextColor(0xff2D2D34);
        rightImg.setVisibility(GONE);
        FrameLayout.LayoutParams rfl = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT);
        rfl.gravity = Gravity.CENTER_VERTICAL|Gravity.RIGHT;
        rfl.setMargins(0,0,AndroidUtils.dp(10),0);
        container.addView(rightImg,rfl);
    }

    public void setLeftView(String name,int res,OnClickListener onClick){
        leftImg.setVisibility(VISIBLE);
        leftImg.setCompoundDrawablesWithIntrinsicBounds(res,0,0,0);
        leftImg.setText(name);
        leftImg.setOnClickListener(onClick);
    }

    public void setRightView(String name,int res,OnClickListener onClick){
        rightImg.setVisibility(VISIBLE);
        rightImg.setCompoundDrawablesWithIntrinsicBounds(res,0,0,0);
        rightImg.setText(name);
        rightImg.setOnClickListener(onClick);
    }

    public void setCenterView(String name){
        centerImg.setVisibility(VISIBLE);
        centerImg.setText(name);
    }

}
