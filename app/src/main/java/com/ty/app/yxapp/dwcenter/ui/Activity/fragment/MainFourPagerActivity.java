package com.ty.app.yxapp.dwcenter.ui.Activity.fragment;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ty.app.yxapp.dwcenter.ui.Activity.base.BaseActivity;

/**
 * Created by kss on 2017/3/26.
 */

public class MainFourPagerActivity extends Fragment {
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = getContext();
        TextView textView = new TextView(context);
        textView.setText("MainFourPagerActivity");
        return textView;
    }
}
