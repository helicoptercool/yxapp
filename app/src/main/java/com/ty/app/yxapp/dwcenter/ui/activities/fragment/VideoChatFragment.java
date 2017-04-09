package com.ty.app.yxapp.dwcenter.ui.activities.fragment;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ty.app.yxapp.dwcenter.R;
import com.ty.app.yxapp.dwcenter.ui.activities.SelectAreaActivity;
import com.ty.app.yxapp.dwcenter.ui.activities.base.BaseFragment;
import com.ty.app.yxapp.dwcenter.ui.im.VideoChatActivity;
import com.ty.app.yxapp.dwcenter.ui.widget.DividerSmallCell;
import com.ty.app.yxapp.dwcenter.ui.widget.EditeItemCell;
import com.ty.app.yxapp.dwcenter.ui.widget.SectionView;
import com.ty.app.yxapp.dwcenter.utils.AndroidUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kss on 2017/3/26.
 */

public class VideoChatFragment extends BaseFragment implements View.OnClickListener{
    private static final String TAG = VideoChatFragment.class.getSimpleName();
    private static final int AREA_RETURN = 0;
    private static final int STREET_RETURN = 1;
    private static final int VILLAGE_RETURN = 2;
    private Context context;
    private int returnFlag = -1;
    private EditeItemCell areaCon;
    private EditeItemCell street;
    private EditeItemCell village;

    private List<String> peopleList = new ArrayList<>();
    private MyAdapter myAdapter;

    @Override
    public void onBeforeCreate() {

    }

    @Override
    public View onCreate() {
        context = getContext();
        actionBar.setVisibility(View.VISIBLE);
        actionBar.setCenterView(getString(R.string.video_chat));

        LinearLayout container = new LinearLayout(context);
        container.setOrientation(LinearLayout.VERTICAL);

        LinearLayout headView = new LinearLayout(context);
        headView.setOrientation(LinearLayout.VERTICAL);

        areaCon = new EditeItemCell(context,AndroidUtils.getString(R.string.area));
        areaCon.setOnClickListener(this);
        headView.addView(areaCon);

        street = new EditeItemCell(context,AndroidUtils.getString(R.string.street));
        street.setOnClickListener(this);
        headView.addView(street);

        village = new EditeItemCell(context,AndroidUtils.getString(R.string.village));
        village.setOnClickListener(this);
        headView.addView(village);

        ListView listView = new ListView(context);
        listView.setDivider(null);
        listView.setDividerHeight(0);
        myAdapter = new MyAdapter();
        listView.setAdapter(myAdapter);
        listView.addHeaderView(headView);
        container.addView(listView);

        initData();
        return container;
    }

    private class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return peopleList.size();
        }

        @Override
        public Object getItem(int i) {
            return peopleList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            LinearLayout peopleCon = new LinearLayout(context);
            peopleCon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    if(i == 1){
                        intent.putExtra("userName","wangjie123456");
                    }else if(i == 3){
                        intent.putExtra("userName","wangqing");
                    }
                    intent.putExtra("flag", VideoChatActivity.FLAG_OUT);
                    intent.setClass(getActivity(),VideoChatActivity.class);
                    startActivity(intent);
                }
            });
            peopleCon.setOrientation(LinearLayout.VERTICAL);

            TextView titleView = new TextView(context);
            titleView.setPadding(AndroidUtils.dp(15), 0, 0, 0);
            titleView.setBackgroundColor(0xFFFFFFFF);
            titleView.setTextColor(0xFF8F9098);
            titleView.setTextSize(14);
            titleView.setGravity(Gravity.CENTER);
            titleView.setText(peopleList.get(i));
            peopleCon.addView(titleView, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    AndroidUtils.dp(45)));

            DividerSmallCell div = new DividerSmallCell(context);
            peopleCon.addView(div,new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,1));
            return peopleCon;
        }
    }


    private void initData(){
        if(peopleList.size() > 0) peopleList.clear();
        for(int i=0;i<20;i++){
            if(i == 1){
                peopleList.add("kangshengsheng");
            }else if(i == 2){
                peopleList.add("wangqing");
            }
            peopleList.add("people "+i);
        }
        myAdapter.notifyDataSetChanged();
    }



    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, SelectAreaActivity.class);
        if(v == areaCon){
            returnFlag = 0;
            intent.putExtra("from","area");
        }else if(v == street){
            returnFlag = 1;
            intent.putExtra("from","street");
        }else if(v == village){
            returnFlag = 2;
            intent.putExtra("from","village");
        }
        startActivityForResult(intent,0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(data == null){
            return;
        }
        Log.e(TAG,"onActivityResult = " + data.getStringExtra("return"));
        String retStr = data.getStringExtra("return");
        switch (returnFlag){
            case AREA_RETURN:
                areaCon.setTitleValue(retStr);
                break;
            case STREET_RETURN:
                street.setTitleValue(retStr);
                break;
            case VILLAGE_RETURN:
                village.setTitleValue(retStr);
                break;
            default:
                break;
        }
        if(myAdapter != null) myAdapter.notifyDataSetChanged();
    }
}
