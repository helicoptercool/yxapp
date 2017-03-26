package com.ty.app.yxapp.dwcenter.ui.Widget;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ty.app.yxapp.dwcenter.R;
import com.ty.app.yxapp.dwcenter.Utils.AndroidUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kss on 2017/3/26.
 */

public class LooperImgCell extends FrameLayout {
    private final SlidePoint slidePoint;
    private Context context;
    private List<Integer> resList;

    public LooperImgCell(Context context) {
        super(context);
        this.context = context;

        ViewPager viewPager = new ViewPager(context);
        addView(viewPager, new ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT));
        viewPager.setOnPageChangeListener(onPagerChangeListenr);
        viewPager.setAdapter(mPagerAdapter);

        slidePoint = new SlidePoint(context);
        FrameLayout.LayoutParams sfl = new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        sfl.gravity = Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL;
        sfl.setMargins(0,0,0,AndroidUtils.dp(5));
        addView(slidePoint,sfl);

    }

    public void setResList(List<Integer> ress){
        if(ress != null && !ress.isEmpty()){
            if(resList == null) resList = new ArrayList<>();
            else resList.clear();

            resList.addAll(ress);
            slidePoint.setNumber(resList.size());
            slidePoint.setCurrent(0);
            if(mPagerAdapter != null){
                mPagerAdapter.notifyDataSetChanged();
            }
        }
    }

    private ViewPager.OnPageChangeListener onPagerChangeListenr = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            slidePoint.setCurrent(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


    private PagerAdapter mPagerAdapter = new PagerAdapter() {
        @Override
        public int getCount() {
            return resList == null ? 0 : resList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            LinearLayout con = new LinearLayout(context);

            ImageView imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));

            imageView.setBackgroundResource(resList.get(position));
            con.addView(imageView);
            container.addView(con);
            return con;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ViewGroup view = (ViewGroup) object;
            container.removeView(view);
        }
    };

    public class SlidePoint extends LinearLayout {

        private Context mContext;
        private int focusIcon;
        private int defaultIcon;

        private List<ImageView> views;

        public SlidePoint(Context context) {
            super(context);
            this.mContext = context;
            this.setOrientation(LinearLayout.HORIZONTAL);
            views = new ArrayList<>();
        }

        public void setNumber(int number) {
            removeAllViews();
            views.clear();

            if (number <= 1) return;

            for (int i = 0; i < number; i++) {
                ImageView imageView = new ImageView(mContext);
                LinearLayout.LayoutParams ll = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
                        LayoutParams.WRAP_CONTENT);
                if (focusIcon == 0)
                    imageView.setImageResource(R.mipmap.ic_slide_defaullt);
                else{
                    imageView.setImageResource(focusIcon);
                }
                if(i != 0) ll.setMargins(AndroidUtils.dp(5),0,0,0);
                imageView.setLayoutParams(ll);
                addView(imageView);
                views.add(imageView);
            }
        }

        public void setCurrent(int current) {
            for (int i = 0; i < views.size(); i++) {
                if (i == current) {
                    if (focusIcon == 0)
                        views.get(i).setImageResource(R.mipmap.ic_slide_focus);
                    else
                        views.get(i).setImageResource(focusIcon);
                } else {
                    if (defaultIcon == 0)
                        views.get(i).setImageResource(R.mipmap.ic_slide_defaullt);
                    else
                        views.get(i).setImageResource(defaultIcon);

                }
            }
        }

        public void setFocusIcon(int res) {
            this.focusIcon = res;
        }

        public void setDefaultIcon(int defaultIcon) {
            this.defaultIcon = defaultIcon;
        }

    }

}
