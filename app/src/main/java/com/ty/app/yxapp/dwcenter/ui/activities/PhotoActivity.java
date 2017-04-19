package com.ty.app.yxapp.dwcenter.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import com.ty.app.yxapp.dwcenter.R;
import com.ty.app.yxapp.dwcenter.ui.activities.base.BaseActivity;
import com.ty.app.yxapp.dwcenter.ui.activities.base.LayoutHelper;
import com.ty.app.yxapp.dwcenter.ui.widget.ImageViewPager;
import com.ty.app.yxapp.dwcenter.ui.widget.photoview.PhotoView;
import com.ty.app.yxapp.dwcenter.ui.widget.photoview.PhotoViewAttacher;
import com.ty.app.yxapp.dwcenter.utils.AndroidUtils;
import java.util.ArrayList;



public class PhotoActivity extends BaseActivity {

    public static String TAG = "PhotoActivity";
    private Context mContext;

    //控件
    private RelativeLayout container;//父容器
    private ImageViewPager mViewPager;
    private ViewGroup mTopContainer;
    private TextView mNumberView;
    private int curPos;

    //适配器
    private PhotoPagerAdapter mAdapter;

    //数据
    private ArrayList<String> photoItems;//图片数据

    //对象
    private int currentPosition = 0;
    private final static int MENU_SAVE = 101;


    @Override
    public void onBeforeCreate() {
        Intent arguments = getIntent();
        curPos = arguments.getIntExtra("cur_position",0);
        photoItems = arguments.getStringArrayListExtra("items");
        if (photoItems == null) {
            photoItems = new ArrayList<>();
        }
    }


    @Override
    public View onCreate() {
        mContext = getBaseContext();
        actionBar.setVisibility(View.GONE);

        //parent
        container = new RelativeLayout(mContext);
        container.setLayoutParams(LayoutHelper.createRelative(LayoutHelper.MATCH_PARENT, LayoutHelper.MATCH_PARENT));

        //viewPager
        mViewPager = new ImageViewPager(mContext);
        mViewPager.setLayoutParams(LayoutHelper.createRelative(LayoutHelper.MATCH_PARENT, LayoutHelper.MATCH_PARENT));
        mViewPager.setBackgroundColor(0xFF000000);
        mViewPager.addOnPageChangeListener(pageChangeListener);
        container.addView(mViewPager);

        //top
        mTopContainer = new RelativeLayout(mContext);
        mTopContainer.setLayoutParams(LayoutHelper.createRelative(LayoutHelper.MATCH_PARENT, 76));
        container.addView(mTopContainer);


        //close
        ImageButton btnClose = new ImageButton(mContext);
        btnClose.setImageResource(R.mipmap.ic_camera_close);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        RelativeLayout.LayoutParams lpClose = LayoutHelper.createRelative(LayoutHelper.WRAP_CONTENT, LayoutHelper.WRAP_CONTENT);
        lpClose.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        lpClose.setMargins(AndroidUtils.dp(5), AndroidUtils.dp(5), 0, 0);
        mTopContainer.addView(btnClose, lpClose);

        //number
        mNumberView = new TextView(mContext);
        mNumberView.setText(AndroidUtils.getString(R.string.loading));
        mNumberView.setTextColor(0xFFFFFFFF);
        mNumberView.setTextSize(16);
        RelativeLayout.LayoutParams lpNumber = LayoutHelper.createRelative(LayoutHelper.WRAP_CONTENT, LayoutHelper.WRAP_CONTENT, 0, 16, 0, 0);
        lpNumber.addRule(RelativeLayout.CENTER_HORIZONTAL);
        mTopContainer.addView(mNumberView, lpNumber);

        mAdapter = new PhotoPagerAdapter(mContext);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(curPos);
        setPhotoInfo();

        return container;
    }


    //设置图片信息信息
    private void setPhotoInfo() {
        if (photoItems.size() > 1) {
            mNumberView.setText((currentPosition + 1) + " / " + photoItems.size());
            mNumberView.setVisibility(View.VISIBLE);
        } else {
            mNumberView.setVisibility(View.GONE);
        }
    }

    private ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            currentPosition = position;
            setPhotoInfo();
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };




    public class PhotoPagerAdapter extends PagerAdapter {
        private Context mContext;

        public PhotoPagerAdapter(Context context) {
            mContext = context;
        }

        public SparseArray<ViewGroup> views = new SparseArray<ViewGroup>();


        @Override
        public int getCount() {
            return photoItems.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ViewGroup view = (ViewGroup) object;
            container.removeView(view);
            views.remove(position);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            RelativeLayout view = new RelativeLayout(mContext);

            //显示的图片
            final PhotoView image = new PhotoView(mContext);
            image.setScaleType(ImageView.ScaleType.FIT_CENTER);
            image.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            image.setOnViewTapListener(new PhotoViewAttacher.OnViewTapListener() {
                @Override
                public void onViewTap(View view, float x, float y) {
                }
            });

            view.addView(image);
            container.addView(view);
            views.put(position, view);
            Picasso.with(mContext).load(photoItems.get(position).toString()).into(image);
            return view;
        }


    }
}
