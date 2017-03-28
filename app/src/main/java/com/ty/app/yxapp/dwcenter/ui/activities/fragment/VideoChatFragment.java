package com.ty.app.yxapp.dwcenter.ui.activities.fragment;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ty.app.yxapp.dwcenter.R;
import com.ty.app.yxapp.dwcenter.ui.activities.SelectAreaActivity;
import com.ty.app.yxapp.dwcenter.ui.activities.base.BaseFragment;

/**
 * Created by kss on 2017/3/26.
 */

public class VideoChatFragment extends BaseFragment implements View.OnClickListener{
    private static final String TAG = VideoChatFragment.class.getSimpleName();
    private static final int AREA_RETURN = 0;
    private static final int STREET_RETURN = 1;
    private static final int VILLAGE_RETURN = 2;
    private Context context;
    private TextView areaTv;
    private TextView streetTv;
    private TextView villageTv;
    private String areaName;
    private String streetName;
    private String villageName;

    private int returnFlag = -1;

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
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        LinearLayout areaLayout = (LinearLayout) view.findViewById(R.id.area_layout);
        LinearLayout streetLayout = (LinearLayout) view.findViewById(R.id.street_layout);
        LinearLayout villageLayout = (LinearLayout) view.findViewById(R.id.village_layout);
        areaTv = (TextView) view.findViewById(R.id.area_name_tv);
        streetTv = (TextView) view.findViewById(R.id.street_name_tv);
        villageTv = (TextView) view.findViewById(R.id.village_name_tv);
        areaLayout.setOnClickListener(this);
        streetLayout.setOnClickListener(this);
        villageLayout.setOnClickListener(this);
    }

    @Override
    public void onBeforeCreate() {

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, SelectAreaActivity.class);
        switch (v.getId()){
            case R.id.area_layout:
                returnFlag = 0;
                intent.putExtra("from","area");
                break;
            case R.id.street_layout:
                returnFlag = 1;
                intent.putExtra("from","street");
                break;
            case R.id.village_layout:
                returnFlag = 2;
                intent.putExtra("from","village");
                break;
            default:
                break;
        }
        startActivityForResult(intent,0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e(TAG,"onActivityResult = " + data.getStringExtra("return"));
        String retStr = data.getStringExtra("return");
        switch (returnFlag){
            case AREA_RETURN:
                areaTv.setText(retStr);
                break;
            case STREET_RETURN:
                streetTv.setText(retStr);
                break;
            case VILLAGE_RETURN:
                villageTv.setText(retStr);
                break;
            default:
                break;
        }
    }
}
