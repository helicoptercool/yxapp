package com.ty.app.yxapp.dwcenter.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ty.app.yxapp.dwcenter.R;
import com.ty.app.yxapp.dwcenter.ui.activities.base.Constants;
import com.ty.app.yxapp.dwcenter.ui.im.ChatController;
import com.ty.app.yxapp.dwcenter.utils.AndroidUtils;
import com.ty.app.yxapp.dwcenter.ui.activities.fragment.VideoChatFragment;
import com.ty.app.yxapp.dwcenter.ui.activities.base.BaseActivity;
import com.ty.app.yxapp.dwcenter.ui.activities.fragment.MainFirstPagerActivity;
import com.ty.app.yxapp.dwcenter.ui.activities.fragment.MainSecondPagerActivity;
import com.ty.app.yxapp.dwcenter.ui.activities.fragment.MainThirdPagerActivity;
import com.ty.app.yxapp.dwcenter.ui.widget.TabView;
import com.ty.app.yxapp.dwcenter.network.MapService;
import com.ty.app.yxapp.dwcenter.utils.SPManager;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";
    private Context context;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private FragmentAdapter fragmentAdapter;
    private TabView tabView;
    private ViewPager viewPager;
    private long exitTime = 0;
    private Intent mapServiceIntent;


    @Override
    public void onBeforeCreate() {
        requestWindowFeature(false);
        context = getBaseContext();
    }

    @Override
    public View onCreate() {
        LinearLayout container = new LinearLayout(context);
        container.setOrientation(LinearLayout.VERTICAL);

        viewPager = new MyViewPager(context);
        viewPager.setOffscreenPageLimit(4);
        viewPager.setId(R.id.main_pager);
        viewPager.setOnPageChangeListener(onPagerChangerListener);
        LinearLayout.LayoutParams pagerLl = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        pagerLl.weight = 1;
        container.addView(viewPager, pagerLl);

        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(fragmentAdapter);

        tabView = new TabView(context);
        tabView.setOnSelectorListener(onSelectorListener);
        container.addView(tabView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, AndroidUtils.dp(50)));

        init();
        loginHuanXin();
        return container;
    }


    private void loginHuanXin(){
        SPManager manager = new SPManager();
        final String phone = manager.readSp(Constants.SP_USER_NAME);
        final String password = manager.readSp(Constants.SP_PASSWORD);
        if(!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(password)){
            ChatController.getIntance().login("heli", "123456", new ChatController.Callback() {
                @Override
                public void success() {
                    Log.d(TAG, "huanxin login success");
                }

                @Override
                public void failure(int code, String message) {
                    Log.d(TAG, "huanxin login failure:" + code + " ,message:" + message);
                    if (code == 202) {//当前用户没有环信账号，注册环信账号,注册成功后去登录环信
                        ChatController.getIntance().createAccount(phone, password, new ChatController.Callback() {
                            @Override
                            public void success() {
                                ChatController.getIntance().login(phone, password,null);
                            }

                            @Override
                            public void failure(int code, String message) {
                                Log.d(TAG, "huanxin createAccount failure");
                            }
                        });
                        Log.d(TAG, "huanxin createAccount success");
                    }
                }
            });
        }
    }

    private class MyViewPager extends ViewPager {

        public MyViewPager(Context context) {
            super(context);
        }

        @Override
        public boolean onTouchEvent(MotionEvent ev) {
            return false;
        }

        @Override
        public boolean onInterceptTouchEvent(MotionEvent ev) {
            return false;
        }
    }

    private void init() {
        MainFirstPagerActivity firstPager = new MainFirstPagerActivity();
        fragments.add(firstPager);

        MainSecondPagerActivity secondPager = new MainSecondPagerActivity();
        fragments.add(secondPager);

        MainThirdPagerActivity thirdPager = new MainThirdPagerActivity();
        fragments.add(thirdPager);

        VideoChatFragment fourPager = new VideoChatFragment();
        fragments.add(fourPager);

        tabView.setCurrent(0);
        fragmentAdapter.notifyDataSetChanged();
        mapServiceIntent = new Intent(this, MapService.class);
        getApplicationContext().startService(mapServiceIntent);
        LoginActivity.setFinishListener(new LoginActivity.FinishListener() {
            @Override
            public void onFinish() {
                completeExit();
            }
        });
    }

    private TabView.OnSelectorListener onSelectorListener = new TabView.OnSelectorListener() {
        @Override
        public void onSelect(int position) {
            viewPager.setCurrentItem(position);
        }
    };

    private ViewPager.OnPageChangeListener onPagerChangerListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            tabView.setCurrent(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - exitTime > 2000) {
            AndroidUtils.ShowToast(AndroidUtils.getString(R.string.press_again_to_exit));
            exitTime = System.currentTimeMillis();
        } else {
            completeExit();
        }
    }

    private void completeExit() {
        getApplicationContext().stopService(mapServiceIntent);
        finish();
        System.exit(0);
    }

    private class FragmentAdapter extends FragmentPagerAdapter {

        public FragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }


}
