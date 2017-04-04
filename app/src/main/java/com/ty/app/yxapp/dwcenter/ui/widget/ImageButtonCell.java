package com.ty.app.yxapp.dwcenter.ui.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ty.app.yxapp.dwcenter.R;
import com.ty.app.yxapp.dwcenter.utils.AndroidUtils;

/**
 * Created by kss on 2017/3/26.
 */

public class ImageButtonCell extends RelativeLayout {
    private final ImageView imgView;
    private final TextView nameView;
    private Context context;

    public ImageButtonCell(Context context,String name) {
        super(context);
        this.context = context;

        LinearLayout container = new LinearLayout(context);
        container.setOrientation(LinearLayout.VERTICAL);
        container.setGravity(Gravity.CENTER_HORIZONTAL);
        RelativeLayout.LayoutParams rl = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT);
        rl.addRule(RelativeLayout.CENTER_IN_PARENT);
        addView(container, rl);

        imgView = new ImageView(context);
        imgView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        container.addView(imgView, new LinearLayout.LayoutParams(AndroidUtils.dp(80), AndroidUtils.dp(80)));

        nameView = new TextView(context);
        nameView.setText(name);
        nameView.setTextSize(16);
        nameView.setSingleLine(true);
        nameView.setEllipsize(TextUtils.TruncateAt.END);
        nameView.setGravity(Gravity.CENTER_HORIZONTAL);
        container.addView(nameView,new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
    }

    public void setPicture(int res){
        imgView.setBackgroundResource(res);
    }

    public void setPicture(int res,int w,int h){
        LinearLayout.LayoutParams rl = (LinearLayout.LayoutParams) imgView.getLayoutParams();
        rl.width = AndroidUtils.dp(w);
        rl.height = AndroidUtils.dp(h);
        imgView.setLayoutParams(rl);
        imgView.setBackgroundResource(res);
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void setTextType(){
//        nameView.setTextColor(Color.BLACK);
        nameView.setTextSize(14);
    }
}
