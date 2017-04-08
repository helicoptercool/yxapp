package com.ty.app.yxapp.dwcenter.ui.im;

import android.content.Context;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ty.app.yxapp.dwcenter.ui.activities.base.LayoutHelper;
import com.ty.app.yxapp.dwcenter.ui.widget.CircleImageView;

/**
 * Created by kss on 2016/12/13.
 */

public class CallCell extends LinearLayout {
    private final CircleImageView circleImageView;
    private final TextView txt;
    private Context context;

    public CallCell(Context context) {
        super(context);
        this.context = context;
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER);

        circleImageView = new CircleImageView(context);
        addView(circleImageView, LayoutHelper.createLinear(60,60));

        txt = new TextView(context);
        txt.setTextColor(0xFFFFFFFF);
        txt.setTextSize(12);
        addView(txt,LayoutHelper.createLinear(LayoutHelper.WRAP_CONTENT,LayoutHelper.WRAP_CONTENT,0,15,0,0));
    }


    public void setImg(int res){
        circleImageView.setImageResource(res);
    }

    public void setText(String str){
        txt.setText(str);
    }

    public void setSelect(int res){
        circleImageView.setBackgroundResource(res);
    }

}
