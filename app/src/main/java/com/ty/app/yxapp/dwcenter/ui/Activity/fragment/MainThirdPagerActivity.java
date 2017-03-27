package com.ty.app.yxapp.dwcenter.ui.Activity.fragment;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.ty.app.yxapp.dwcenter.ui.Activity.base.BaseFragment;

/**
 * Created by kss on 2017/3/26.
 */

public class MainThirdPagerActivity extends BaseFragment {
    private Context context;

    @Override
    public View onCreate() {
        context = getContext();
        TextView textView = new TextView(context);
        textView.setText("MainThirdPagerActivity");
        return textView;
    }

    @Override
    public void onBeforeCreate() {

    }
}
