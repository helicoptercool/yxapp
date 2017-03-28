package com.ty.app.yxapp.dwcenter.ui.activities.fragment;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.ty.app.yxapp.dwcenter.R;
import com.ty.app.yxapp.dwcenter.utils.AndroidUtils;
import com.ty.app.yxapp.dwcenter.ui.activities.base.BaseFragment;
import com.ty.app.yxapp.dwcenter.ui.widget.ImageButtonCell;
import com.ty.app.yxapp.dwcenter.ui.widget.LooperImgCell;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kss on 2017/3/26.
 */

public class MainFirstPagerActivity extends BaseFragment implements View.OnClickListener{
    private Context context;
    private LooperImgCell looperImgCell;
    private ImageButtonCell tlBtn;
    private ImageButtonCell trBtn;
    private ImageButtonCell blBtn;
    private ImageButtonCell brBtn;


    @Override
    public View onCreate() {
        context = getContext();
        ScrollView scrollView = new ScrollView(context);

        LinearLayout cont = new LinearLayout(context);
        cont.setOrientation(LinearLayout.VERTICAL);
        scrollView.addView(cont,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        actionBar.setVisibility(View.VISIBLE);
        actionBar.setCenterView(AndroidUtils.getString(R.string.first_pager));
        actionBar.setRightView("", R.mipmap.ic_launcher, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"饿呢奥尔够昂偶", Toast.LENGTH_SHORT).show();
            }
        });

        looperImgCell = new LooperImgCell(context);
        cont.addView(looperImgCell,new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                AndroidUtils.dp(200)));

        TextView weather = new TextView(context);
        weather.setText("天气预报");
        weather.setPadding(AndroidUtils.dp(10),AndroidUtils.dp(10),AndroidUtils.dp(10),AndroidUtils.dp(10));
        LinearLayout.LayoutParams weatherLL = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        cont.addView(weather,weatherLL);

        LinearLayout selectCon = new LinearLayout(context);
        selectCon.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams selectConLL = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        selectConLL.setMargins(0,AndroidUtils.dp(20),0,0);
        cont.addView(selectCon,selectConLL);

        LinearLayout topCon = new LinearLayout(context);
        topCon.setOrientation(LinearLayout.HORIZONTAL);
        selectCon.addView(topCon,new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        tlBtn = new ImageButtonCell(context,AndroidUtils.getString(R.string.hander_st));
        tlBtn.setOnClickListener(this);
        LinearLayout.LayoutParams tlbLL = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        tlbLL.weight = 1;
        tlBtn.setPicture(R.mipmap.ic_launcher);
        topCon.addView(tlBtn,tlbLL);

        trBtn = new ImageButtonCell(context,AndroidUtils.getString(R.string.handered_st));
        trBtn.setOnClickListener(this);
        LinearLayout.LayoutParams trbLL = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        trbLL.weight = 1;
        trBtn.setPicture(R.mipmap.ic_launcher);
        topCon.addView(trBtn,trbLL);


        LinearLayout bottomCon = new LinearLayout(context);
        bottomCon.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams bcLL = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        bcLL.setMargins(0,AndroidUtils.dp(25),0,0);
        selectCon.addView(bottomCon,bcLL);

        blBtn = new ImageButtonCell(context,AndroidUtils.getString(R.string.all_st));
        blBtn.setOnClickListener(this);
        LinearLayout.LayoutParams blbLL = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        blbLL.weight = 1;
        blBtn.setPicture(R.mipmap.ic_launcher);
        bottomCon.addView(blBtn,blbLL);

        brBtn = new ImageButtonCell(context,AndroidUtils.getString(R.string.time_location));
        brBtn.setOnClickListener(this);
        LinearLayout.LayoutParams brbLL = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        brbLL.weight = 1;
        brBtn.setPicture(R.mipmap.ic_launcher);
        bottomCon.addView(brBtn,brbLL);

        init();
        return scrollView;
    }

    @Override
    public void onBeforeCreate() {
    }

    private void init(){
        List<Integer> resList = new ArrayList<>();
        for(int i=0;i<3;i++){
            resList.add(R.drawable.timg);
        }
        looperImgCell.setResList(resList);
    }

    @Override
    public void onClick(View view) {
        if(view == tlBtn){
            Toast.makeText(context,"agrargarg",Toast.LENGTH_SHORT).show();
        }else if(view == trBtn){
            Toast.makeText(context,"agrargarg",Toast.LENGTH_SHORT).show();
        }else if(view == blBtn){
            Toast.makeText(context,"agrargarg",Toast.LENGTH_SHORT).show();
        }else if(view == brBtn){
            Toast.makeText(context,"agrargarg",Toast.LENGTH_SHORT).show();
        }
    }
}
