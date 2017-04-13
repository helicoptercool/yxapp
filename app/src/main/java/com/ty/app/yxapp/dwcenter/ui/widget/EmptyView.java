package com.ty.app.yxapp.dwcenter.ui.widget;

import android.content.Context;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ty.app.yxapp.dwcenter.R;
import com.ty.app.yxapp.dwcenter.utils.AndroidUtils;

/**
 * Created by kss on 2017/4/3.
 */

public class EmptyView extends RelativeLayout {
    private final TextView textView;
    private Context context;

    public EmptyView(Context context) {
        super(context);
        this.context = context;

        textView = new TextView(context);
        textView.setTextSize(16);
        textView.setTextColor(0xff2D2D34);
        textView.setText(AndroidUtils.getString(R.string.loading));
        RelativeLayout.LayoutParams rl = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        rl.addRule(RelativeLayout.CENTER_IN_PARENT);
        addView(textView,rl);
    }

    public void setText(String text){
        textView.setText(text);
    }

    public void setLoadingText(String str){
        textView.setText(str);
    }
}
