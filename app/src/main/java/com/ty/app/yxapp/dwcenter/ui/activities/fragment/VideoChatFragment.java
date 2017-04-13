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

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.ty.app.yxapp.dwcenter.R;
import com.ty.app.yxapp.dwcenter.bean.OrgDataInfo;
import com.ty.app.yxapp.dwcenter.bean.User;
import com.ty.app.yxapp.dwcenter.network.Result;
import com.ty.app.yxapp.dwcenter.network.RetrofitHelper;
import com.ty.app.yxapp.dwcenter.ui.activities.SelectAreaActivity;
import com.ty.app.yxapp.dwcenter.ui.activities.base.BaseFragment;
import com.ty.app.yxapp.dwcenter.ui.activities.base.Constants;
import com.ty.app.yxapp.dwcenter.ui.im.VideoChatActivity;
import com.ty.app.yxapp.dwcenter.ui.widget.DividerSmallCell;
import com.ty.app.yxapp.dwcenter.ui.widget.EditeItemCell;
import com.ty.app.yxapp.dwcenter.ui.widget.SectionView;
import com.ty.app.yxapp.dwcenter.utils.AndroidUtils;
import com.ty.app.yxapp.dwcenter.utils.SPManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kss on 2017/3/26.
 */

public class VideoChatFragment extends BaseFragment {
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

/*        LinearLayout headView = new LinearLayout(context);
        headView.setOrientation(LinearLayout.VERTICAL);

        areaCon = new EditeItemCell(context, AndroidUtils.getString(R.string.area));
        headView.addView(areaCon);

        street = new EditeItemCell(context, AndroidUtils.getString(R.string.street));
        headView.addView(street);

        village = new EditeItemCell(context, AndroidUtils.getString(R.string.village));
        headView.addView(village);*/

        ListView listView = new ListView(context);
        listView.setDivider(null);
        listView.setDividerHeight(0);
        myAdapter = new MyAdapter();
        listView.setAdapter(myAdapter);
//        listView.addHeaderView(headView);
        container.addView(listView);

        initData();
        return container;
    }

    private class MyAdapter extends BaseAdapter {

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
                    intent.putExtra("userName", peopleList.get(i));
                    intent.putExtra("flag", VideoChatActivity.FLAG_OUT);
                    intent.setClass(getActivity(), VideoChatActivity.class);
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
            peopleCon.addView(div, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 1));
            return peopleCon;
        }
    }


    private void initData() {
        if (peopleList.size() > 0) peopleList.clear();
        SPManager spManager = new SPManager();
        String username = spManager.readSp(Constants.SP_USER_NAME);

        RetrofitHelper.getInstance().getOrgData("0001", username, new RetrofitHelper.OnResultListener() {
            @Override
            public void onResult(Result result) {
                Log.e(TAG, result.getMessage());
                if (result.isOK()) {
                    List<OrgDataInfo.OrgDataBody> orgDataList = (List<OrgDataInfo.OrgDataBody>) result.getData();
                    if (orgDataList != null) {
                        for (OrgDataInfo.OrgDataBody orgDataBody : orgDataList) {

                            List<User> userList = orgDataBody.getUsers();
                            if (userList != null && userList.size() != 0) {
                                peopleList.add(userList.get(0).getUserName());
                            }
                        }
                        myAdapter.notifyDataSetChanged();
                    }
                }
            }
        });

    }
}
