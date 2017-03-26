package com.ty.app.yxapp.dwcenter.ui.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ty.app.yxapp.dwcenter.R;
import com.ty.app.yxapp.dwcenter.Utils.AndroidUtils;
import com.ty.app.yxapp.dwcenter.ui.Activity.base.BaseActivity;
import com.ty.app.yxapp.dwcenter.ui.Activity.fragment.MainFirstPagerActivity;
import com.ty.app.yxapp.dwcenter.ui.Activity.fragment.MainFourPagerActivity;
import com.ty.app.yxapp.dwcenter.ui.Activity.fragment.MainSecondPagerActivity;
import com.ty.app.yxapp.dwcenter.ui.Activity.fragment.MainThirdPagerActivity;
import com.ty.app.yxapp.dwcenter.ui.Widget.LooperImgCell;
import com.ty.app.yxapp.dwcenter.ui.Widget.TabView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private Context context;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private FragmentAdapter fragmentAdapter;
    private TabView tabView;
    private ViewPager viewPager;

    @Override
    public void onBeforeCreate() {
        requestWindowFeature(false);
        context = getBaseContext();
    }

    @Override
    public View onCreate() {
        LinearLayout container = new LinearLayout(context);
        container.setOrientation(LinearLayout.VERTICAL);

        viewPager = new ViewPager(context);
        viewPager.setId(R.id.main_pager);
        viewPager.setOnPageChangeListener(onPagerChangerListener);
        LinearLayout.LayoutParams pagerLl = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0);
        pagerLl.weight = 1;
        container.addView(viewPager,pagerLl);

        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(fragmentAdapter);

        tabView = new TabView(context);
        tabView.setOnSelectorListener(onSelectorListener);
        container.addView(tabView,new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,AndroidUtils.dp(50)));

        init();
        return container;
    }

    private void init(){
        MainFirstPagerActivity firstPager = new MainFirstPagerActivity();
        fragments.add(firstPager);

        MainSecondPagerActivity secondPager = new MainSecondPagerActivity();
        fragments.add(secondPager);

        MainThirdPagerActivity thirdPager = new MainThirdPagerActivity();
        fragments.add(thirdPager);

        MainFourPagerActivity fourPager = new MainFourPagerActivity();
        fragments.add(fourPager);

        tabView.setCurrent(0);
        fragmentAdapter.notifyDataSetChanged();
    }

    private TabView.OnSelectorListener onSelectorListener = new TabView.OnSelectorListener() {
        @Override
        public void onSelect(int position) {
            viewPager.setCurrentItem(position);
        }
    };

    private ViewPager.OnPageChangeListener onPagerChangerListener  = new ViewPager.OnPageChangeListener() {
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

    private class FragmentAdapter extends FragmentPagerAdapter{

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
