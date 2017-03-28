package com.ty.app.yxapp.dwcenter.ui.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ty.app.yxapp.dwcenter.utils.AndroidUtils;

/**
 * Created by kss on 2017/3/27.
 */
public class SectionView extends LinearLayout {

    private final TextView titleView;
    private Context context;

    public SectionView(Context context, String title) {
        super(context);
        this.context = context;

        setOrientation(LinearLayout.VERTICAL);
        setBackgroundColor(0xFFFFFFFF);

        setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.WRAP_CONTENT));

        titleView = new TextView(context);
        titleView.setPadding(AndroidUtils.dp(15), 0, 0, 0);
        titleView.setBackgroundColor(0xFFF5F5F5);
        titleView.setTextColor(0xFF8F9098);
        titleView.setTextSize(14);
        titleView.setGravity(Gravity.CENTER_VERTICAL);
        titleView.setText(title);
        addView(titleView, new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,AndroidUtils.dp(36)));
    }


    public void setTextCol(int color) {
        titleView.setTextColor(color);
    }

    public void setTextsize(int size) {
        titleView.setTextSize(size);
    }

    public void setTextType(Typeface type) {
        titleView.setTypeface(type);
    }

    public void setCustomTitle(String title) {
        titleView.setText(title);
    }
}
