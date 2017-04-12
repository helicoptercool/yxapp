package com.ty.app.yxapp.dwcenter.ui.widget;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
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

public class TabView extends LinearLayout {
    private Context context;
    private TabItem[] items = new TabItem[4];
    private int current = -1;
    private OnSelectorListener mListener;

    public TabView(Context context) {
        super(context);
        this.context = context;
        setOrientation(HORIZONTAL);
        setBackgroundColor(0xFF161616);
        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        TabItem firstPager = new TabItem(context, R.mipmap.home, R.mipmap.home1, AndroidUtils.getString(R.string.my_work));
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

        TabItem secondPager = new TabItem(context, R.mipmap.sjsb, R.mipmap.sjsb1, AndroidUtils.getString(R.string.push_work));
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

        TabItem ThirdPager = new TabItem(context, R.mipmap.shfw, R.mipmap.shfw1, AndroidUtils.getString(R.string.user_center));
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

        TabItem FourPager = new TabItem(context, R.mipmap.spth, R.mipmap.spth1, AndroidUtils.getString(R.string.video_chat));
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
            setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            setGravity(Gravity.CENTER);

            LinearLayout container = new LinearLayout(context);
            container.setOrientation(VERTICAL);
            container.setGravity(Gravity.CENTER_HORIZONTAL);
            RelativeLayout.LayoutParams rl = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            rl.addRule(RelativeLayout.CENTER_IN_PARENT);
            addView(container,rl);

            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setBackgroundResource(defaultRes);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(AndroidUtils.dp(25), AndroidUtils.dp(25)));
            container.addView(imageView);

            textView = new TextView(context);
            textView.setTextSize(12);
            textView.setTextColor(0xFF838288);
            textView.setText(tabName);
            textView.setGravity(Gravity.CENTER_HORIZONTAL);
            LinearLayout.LayoutParams tvll = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
//            tvll.setMargins(0, 0, 0, AndroidUtils.dp(5));
            container.addView(textView, tvll);
        }

        public void setCurrent(boolean focus) {
            imageView.setBackgroundResource(focus ? pressRes : defaultRes);
            textView.setTextColor(focus ? 0xFF4169E1 : 0xFF838288);
        }
    }
}
