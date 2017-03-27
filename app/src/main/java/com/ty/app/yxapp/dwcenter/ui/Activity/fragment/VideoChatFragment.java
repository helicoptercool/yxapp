package com.ty.app.yxapp.dwcenter.ui.Activity.fragment;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.ty.app.yxapp.dwcenter.R;
import com.ty.app.yxapp.dwcenter.ui.Activity.base.BaseFragment;

/**
 * Created by kss on 2017/3/26.
 */

public class VideoChatFragment extends BaseFragment {
    private Context context;

    @Override
    public View onCreate() {
        context = getContext();
        actionBar.setVisibility(View.VISIBLE);
        actionBar.setCenterView(getString(R.string.video_chat));
        actionBar.setLeftView("", R.mipmap.right_arrow, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        View view = getLayoutInflater(null).inflate(R.layout.layout_video_chat_fragment,null);
        return view;
    }

    @Override
    public void onBeforeCreate() {

    }
}
