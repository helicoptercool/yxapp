package com.ty.app.yxapp.dwcenter.ui.Activity.base;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ty.app.yxapp.dwcenter.ui.Widget.ActionBar;

/**
 * Created by kss on 2017/3/27.
 */

public abstract class BaseFragment extends Fragment {

    public ActionBar actionBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup cont, Bundle savedInstanceState) {
        onBeforeCreate();

        LinearLayout container = new LinearLayout(getContext());
        container.setBackgroundColor(Color.WHITE);
        container.setOrientation(LinearLayout.VERTICAL);
        container.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        actionBar = new ActionBar(getContext());
        actionBar.setVisibility(View.GONE);
        container.addView(actionBar, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        FrameLayout frameLayout = new FrameLayout(getContext());
        container.addView(frameLayout, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        View view = onCreate();
        if (view != null) {
            frameLayout.addView(view);

        }
        return container;
    }

    public abstract View onCreate();

    public abstract  void onBeforeCreate();

}
