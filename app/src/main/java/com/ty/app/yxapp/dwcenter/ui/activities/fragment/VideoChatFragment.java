package com.ty.app.yxapp.dwcenter.ui.activities.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.ty.app.yxapp.dwcenter.R;
import com.ty.app.yxapp.dwcenter.bean.OrgDataInfo;
import com.ty.app.yxapp.dwcenter.bean.StringResult;
import com.ty.app.yxapp.dwcenter.bean.User;
import com.ty.app.yxapp.dwcenter.network.Result;
import com.ty.app.yxapp.dwcenter.network.RetrofitHelper;
import com.ty.app.yxapp.dwcenter.ui.activities.SelectAreaActivity;
import com.ty.app.yxapp.dwcenter.ui.activities.base.BaseFragment;
import com.ty.app.yxapp.dwcenter.ui.activities.base.Constants;
import com.ty.app.yxapp.dwcenter.ui.im.VideoChatActivity;
import com.ty.app.yxapp.dwcenter.ui.widget.DividerSmallCell;
import com.ty.app.yxapp.dwcenter.ui.widget.EditeItemCell;
import com.ty.app.yxapp.dwcenter.ui.widget.EmptyView;
import com.ty.app.yxapp.dwcenter.utils.AndroidUtils;
import com.ty.app.yxapp.dwcenter.utils.SPManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by kss on 2017/3/26.
 */

public class VideoChatFragment extends BaseFragment implements View.OnClickListener {
    private static final String TAG = VideoChatFragment.class.getSimpleName();
    public static final int AREA_RETURN = 0;
    public static final int STREET_RETURN = 1;
    public static final int VILLAGE_RETURN = 2;
    private Context context;
    private int returnFlag = -1;
    private EditeItemCell areaCon;
    private EditeItemCell street;
    private EditeItemCell village;

    private Set<String> peopleList = new HashSet<>();
    private MyAdapter myAdapter;
    private int areaCode = 0;
    private int streetCode = 0;
    private int villageCode = 0;
    private SVProgressHUD loading;

//    private EmptyView emptyView;
    private ListView listView;
/*    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    listView.setVisibility(View.VISIBLE);
                    emptyView.setVisibility(View.GONE);
                    myAdapter.notifyDataSetChanged();
                    break;
                case 1:
                    listView.setVisibility(View.GONE);
                    emptyView.setVisibility(View.VISIBLE);
                    emptyView.setLoadingText(AndroidUtils.getString(R.string.no_data));
                    break;
            }
        }
    };*/


    @Override
    public void onBeforeCreate() {

    }

    @Override
    public View onCreate() {
        context = getContext();
        actionBar.setVisibility(View.VISIBLE);
        actionBar.setCenterView(getString(R.string.video_chat));
        loading = new SVProgressHUD(context);

        LinearLayout container = new LinearLayout(context);
        container.setOrientation(LinearLayout.VERTICAL);


        LinearLayout headView = new LinearLayout(context);
        headView.setOrientation(LinearLayout.VERTICAL);
//        container.addView(headView);


        areaCon = new EditeItemCell(context, AndroidUtils.getString(R.string.area));
        areaCon.setOnClickListener(this);
        areaCon.setValue("大洼区");
        headView.addView(areaCon);

        street = new EditeItemCell(context, AndroidUtils.getString(R.string.street));
        street.setOnClickListener(this);
        street.setValue("大洼街道");
        headView.addView(street);

        village = new EditeItemCell(context, AndroidUtils.getString(R.string.village));
        village.setOnClickListener(this);
        village.setValue("新兴社区");
        headView.addView(village);


        listView = new ListView(context);
        listView.setDivider(null);
        listView.setDividerHeight(0);
        myAdapter = new MyAdapter();
        listView.setAdapter(myAdapter);
        listView.addHeaderView(headView);
        container.addView(listView);

//        emptyView = new EmptyView(context);
//        container.addView(emptyView,new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.MATCH_PARENT));
        initData();
        return container;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, SelectAreaActivity.class);
        if (v == areaCon) {
            intent.putExtra("from", "area");
        } else if (v == street) {
            intent.putExtra("from", "street");
            if (areaCon.getValue().equals("大洼区")) {
                intent.putExtra("index", 0);
            } else {
                intent.putExtra("index", 1);
            }

        } else if (v == village) {
            intent.putExtra("from", "village");
            intent.putExtra("areaStreet", areaCon.getValue() + "," + street.getValue());
        }
        startActivityForResult(intent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (null == data || null == data.getStringExtra("return")) {
            return;
        }
        if (peopleList.size() > 0) peopleList.clear();
//        mHandler.sendEmptyMessage(1);
        loading.showWithStatus(AndroidUtils.getString(R.string.requesting));
        SPManager spManager = new SPManager();
        final String username = spManager.readSp(Constants.SP_USER_NAME);
        if (!village.getValue().equals("暂无三级部门")) {
            RetrofitHelper.getInstance().getOrgData("0001", username, new RetrofitHelper.OnResultListener() {
                @Override
                public void onResult(Result result) {
                    loading.dismissImmediately();
                    if (result != null) {
                        if (result.isOK()) {
                            List<OrgDataInfo.OrgDataBody> orgDataList = (List<OrgDataInfo.OrgDataBody>) result.getData();
                            if (orgDataList != null) {
                                for (OrgDataInfo.OrgDataBody body : orgDataList) {
                                    if (body.getName().equals(village.getValue())) {
                                        for (User user : body.getUsers()) {
                                            peopleList.add(user.getUserName());
                                        }
                                    }
                                }
                            }
                        }
//                        mHandler.sendEmptyMessage(0);
                        myAdapter.notifyDataSetChanged();
                    }
                }
            });
        } else {
            RetrofitHelper.getInstance().getOrgData("0001", username, new RetrofitHelper.OnResultListener() {
                @Override
                public void onResult(Result result) {
                    loading.dismissImmediately();
                    if (result != null) {
                        if (result.isOK()) {
                            List<OrgDataInfo.OrgDataBody> orgDataList = (List<OrgDataInfo.OrgDataBody>) result.getData();
                            if (orgDataList != null) {
                                for (OrgDataInfo.OrgDataBody body : orgDataList) {
                                    if (body.getName().equals(street.getValue())) {
                                        for (User user : body.getUsers()) {
                                            peopleList.add(user.getUserName());
                                        }
                                    }
                                }
                            }
//                            mHandler.sendEmptyMessage(0);
                            myAdapter.notifyDataSetChanged();
                        }
                    }
                }
            });
        }

        switch (resultCode) {
            case AREA_RETURN:

                if (data.getStringExtra("return").equals("大洼职能部门")) {
                    areaCode = 1;
                    areaCon.setValue(data.getStringExtra("return"));
                    street.setValue("发改局");
                    village.setValue("暂无三级部门");
                } else {
                    areaCode = 0;
                    areaCon.setValue(data.getStringExtra("return"));
                    street.setValue("大洼街道");
                    village.setValue("新兴社区");
                }
                break;
            case STREET_RETURN:
                String str = data.getStringExtra("return");
                street.setValue(str);
                if (areaCode == 1) {
                    switch (str) {
                        case "发改局":
                            streetCode = 0;
                            village.setValue("无下属部门");
                            break;
                        case "经信局":
                            streetCode = 1;
                            village.setValue("大洼街道、向海街道、榆树镇工业企业");
                            break;
                        case "教育局":
                            streetCode = 2;
                            village.setValue("大洼区城郊学校");
                            break;
                        case "公安局":
                            streetCode = 3;
                            village.setValue("治安大队");
                            break;
                        case "司法局":
                            streetCode = 4;
                            village.setValue("大洼司法所");
                            break;
                        case "财政局":
                            streetCode = 5;
                            village.setValue("农业综合开发办");
                            break;
                        case "人社局":
                            streetCode = 6;
                            village.setValue("福利待遇、工资、工龄、参保、退休、特殊工种");
                            break;
                        case "国土资源局":
                            streetCode = 7;
                            village.setValue("大洼镇");
                            break;
                        case "交通局":
                            streetCode = 8;
                            village.setValue("无下属部门");
                            break;
                        case "农经局":
                            streetCode = 9;
                            village.setValue("西安镇");
                            break;
                        case "水利局":
                            streetCode = 10;
                            village.setValue("无下属部门");
                            break;
                        case "服务业局":
                            streetCode = 11;
                            village.setValue("成品油管理");
                            break;
                        case "安监局":
                            streetCode = 12;
                            village.setValue("无下属部门");
                            break;
                        case "规划局":
                            streetCode = 13;
                            village.setValue("无下属部门");
                            break;
                        case "征收办":
                            streetCode = 14;
                            village.setValue("第一征收中心");
                            break;
                        case "宜居办":
                            streetCode = 15;
                            village.setValue("城市网格");
                            break;
                        case "国税局":
                            streetCode = 16;
                            village.setValue("唐家镇、新开镇、新立镇");
                            break;
                        case "地税局":
                            streetCode = 17;
                            village.setValue("无下属部门");
                            break;
                        case "气象局":
                            streetCode = 18;
                            village.setValue("大洼镇");
                            break;
                        case "人民银行":
                            streetCode = 19;
                            village.setValue("无下属部门");
                            break;
                        case "移动公司":
                            streetCode = 20;
                            village.setValue("无下属部门");
                            break;
                        case "联通公司":
                            streetCode = 21;
                            village.setValue("无下属部门");
                            break;
                        case "社保分局":
                            streetCode = 22;
                            village.setValue("无下属部门");
                            break;
                        case "电信公司":
                            streetCode = 23;
                            village.setValue("无下属部门");
                            break;
                        case "政法委":
                            streetCode = 24;
                            village.setValue("无下属部门");
                            break;
                        case "统计局":
                            streetCode = 25;
                            village.setValue("无下属部门");
                            break;
                        case "市场监管局":
                            streetCode = 26;
                            village.setValue("大洼监督管理所");
                            break;
                        case "文旅局":
                            streetCode = 27;
                            village.setValue("文化建设");
                            break;
                        case "卫计局":
                            streetCode = 28;
                            village.setValue("区局");
                            break;
                        case "民政局":
                            streetCode = 29;
                            village.setValue("大洼街道");
                            break;
                        case "供电分公司":
                            streetCode = 30;
                            village.setValue("大洼镇");
                            break;
                        case "环保局":
                            streetCode = 31;
                            village.setValue("新开环保所");
                            break;
                        case "城市建设（集团）":
                            streetCode = 32;
                            village.setValue("无下属部门");
                            break;
                        case "住建局":
                            streetCode = 33;
                            village.setValue("小区物业管理（供暖、维修资金）");
                            break;
                        case "综合执法局":
                            streetCode = 34;
                            village.setValue("大洼街道执法大队");
                            break;
                        case "公交公司":
                            streetCode = 35;
                            village.setValue("无下属部门");
                            break;
                    }
                } else {
                    switch (str) {
                        case "大洼街道":
                            streetCode = 35;
                            village.setValue("无下属部门");
                            break;
                        case "田家街道":
                            streetCode = 35;
                            village.setValue("毛家社区");
                            break;
                        case "榆树街道":
                            streetCode = 35;
                            village.setValue("曾家村");
                            break;
                        case "前进街道":
                            streetCode = 35;
                            village.setValue("润田社区");
                            break;
                        case "向海街道":
                            streetCode = 35;
                            village.setValue("东三社区");
                            break;
                        case "西安镇":
                            streetCode = 35;
                            village.setValue("桃园村");
                            break;
                        case "新兴镇":
                            streetCode = 35;
                            village.setValue("王家村");
                            break;
                        case "东风镇":
                            streetCode = 35;
                            village.setValue("大岗子村");
                            break;
                        case "新开镇":
                            streetCode = 35;
                            village.setValue("曲家村");
                            break;
                        case "新立镇":
                            streetCode = 35;
                            village.setValue("史家村");
                            break;
                        case "清水镇":
                            streetCode = 35;
                            village.setValue("立新村");
                            break;
                        case "唐家镇":
                            streetCode = 35;
                            village.setValue("北窑村");
                            break;
                        case "赵圈河镇":
                            streetCode = 35;
                            village.setValue("红塔村");
                            break;
                        case "平安镇":
                            streetCode = 35;
                            village.setValue("小房村");
                            break;
                        case "三角洲开发区":
                            streetCode = 35;
                            village.setValue("无下属部门");
                            break;
                        case "网格中心":
                            streetCode = 35;
                            village.setValue("办公室");
                            break;
                    }
                }
                break;
            case VILLAGE_RETURN:
                village.setValue(data.getStringExtra("return"));
                break;
            default:
                break;

        }
    }

    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return peopleList.size();
        }

        @Override
        public Object getItem(int i) {

            return peopleList.toArray()[i];
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
                    intent.putExtra("userName", (String) peopleList.toArray()[i]);
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
            titleView.setText((String) peopleList.toArray()[i]);
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
        final String username = spManager.readSp(Constants.SP_USER_NAME);
//        loading.showWithStatus(AndroidUtils.getString(R.string.requesting));
        RetrofitHelper.getInstance().getOrgData("0001", username, new RetrofitHelper.OnResultListener() {
            @Override
            public void onResult(Result result) {
                Log.e(TAG, result.getMessage());
//                loading.dismissImmediately();
                if (result.isOK()) {
                    List<OrgDataInfo.OrgDataBody> orgDataList = (List<OrgDataInfo.OrgDataBody>) result.getData();
                    if (orgDataList != null) {
                        for (OrgDataInfo.OrgDataBody orgDataBody : orgDataList) {

                            if (orgDataBody.getName().equals("新兴社区")) {
                                List<User> userList = orgDataBody.getUsers();
                                for (User user : userList) {
                                    peopleList.add(user.getUserName());
                                }
                            }
                        }
//                        mHandler.sendEmptyMessage(0);
                        myAdapter.notifyDataSetChanged();
                    }
                }
            }
        });

    }
}
