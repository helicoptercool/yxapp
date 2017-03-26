package com.ty.app.yxapp.dwcenter.ui.Widget;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ty.app.yxapp.dwcenter.R;
import com.ty.app.yxapp.dwcenter.Utils.AndroidUtils;

/**
 * Created by kss on 2017/3/26.
 */

public class TabView extends LinearLayout {
    private Context context;
    private TabItem[] items = new TabItem[4];
    private int current = -1;
    private OnSelectorListener mListener;

    public TabView(Context context) {
        super(context);
        this.context = context;
        setOrientation(HORIZONTAL);
        setBackgroundColor(Color.GRAY);
        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        TabItem firstPager = new TabItem(context,R.mipmap.ic_launcher,R.mipmap.ic_launcher, "首页");
        firstPager.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                firePosition(0);
            }
        });
        LinearLayout.LayoutParams frl = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        frl.weight = 1;
        items[0] = firstPager;
        addView(firstPager, frl);

        TabItem secondPager = new TabItem(context, R.mipmap.ic_launcher,  R.mipmap.ic_launcher, "二页");
        secondPager.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                firePosition(1);
            }
        });
        LinearLayout.LayoutParams srl = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        srl.weight = 1;
        items[1] = secondPager;
        addView(secondPager, srl);

        TabItem ThirdPager = new TabItem(context,  R.mipmap.ic_launcher, R.mipmap.ic_launcher, "我");
        ThirdPager.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                firePosition(2);
            }
        });
        LinearLayout.LayoutParams trl = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        trl.weight = 1;
        items[2] = ThirdPager;
        addView(ThirdPager, trl);

        TabItem FourPager = new TabItem(context,  R.mipmap.ic_launcher,  R.mipmap.ic_launcher, "视频");
        FourPager.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                firePosition(3);
            }
        });
        LinearLayout.LayoutParams forl = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        forl.weight = 1;
        items[3] = FourPager;
        addView(FourPager, forl);
    }

    public void setOnSelectorListener(OnSelectorListener mListener) {
        this.mListener = mListener;
    }

    private void firePosition(int position) {
        if (current == position || position >= items.length) return;
        if (current >= 0)
            items[current].setCurrent(false);
        items[position].setCurrent(true);
        current = position;
        if (mListener != null) {
            mListener.onSelect(current);
        }
    }

    public void setCurrent(int position) {
        if (current == position || position >= items.length) return;
        if (current >= 0)
            items[current].setCurrent(false);
        items[position].setCurrent(true);
        current = position;
    }

    public interface OnSelectorListener {
        void onSelect(int position);
    }


    private class TabItem extends RelativeLayout {
        private final ImageView imageView;
        private final TextView textView;
        private int defaultRes;
        private int pressRes;

        public TabItem(Context context, int defaultRes, int pressRes, String tabName) {
            super(context);
            this.defaultRes = defaultRes;
            this.pressRes = pressRes;
            setGravity(Gravity.CENTER);

            LinearLayout container = new LinearLayout(context);
            container.setOrientation(VERTICAL);
            container.setGravity(Gravity.CENTER_HORIZONTAL);
            addView(container);

            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setBackgroundResource(defaultRes);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(AndroidUtils.dp(38), AndroidUtils.dp(38)));
            container.addView(imageView);

            textView = new TextView(context);
            textView.setTextSize(10);
            textView.setTextColor(Color.BLACK);
            textView.setText(tabName);
            textView.setGravity(Gravity.CENTER_HORIZONTAL);
            container.addView(textView,new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
        }

        public void setCurrent(boolean focus) {
            imageView.setBackgroundResource(focus ? pressRes : defaultRes);
            textView.setTextColor(focus ? 0x56000000 : 0xFF000000);
        }
    }
}
