package com.ty.app.yxapp.dwcenter.ui.activities.fragment;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.Space;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ActionMenuView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.ty.app.yxapp.dwcenter.R;
import com.ty.app.yxapp.dwcenter.ui.activities.WebviewActivity;
import com.ty.app.yxapp.dwcenter.ui.activities.base.Constants;
import com.ty.app.yxapp.dwcenter.ui.widget.EditeItemCell;
import com.ty.app.yxapp.dwcenter.ui.widget.ImageButtonCell;
import com.ty.app.yxapp.dwcenter.ui.widget.SectionView;
import com.ty.app.yxapp.dwcenter.utils.AndroidUtils;
import com.ty.app.yxapp.dwcenter.ui.activities.base.BaseFragment;
import com.ty.app.yxapp.dwcenter.ui.widget.LooperImgCell;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kss on 2017/3/26.
 */

public class MainThirdPagerActivity extends BaseFragment implements View.OnClickListener {
    private Context context;
    private LooperImgCell looperImgCell;
    private ImageButtonCell ems;
    private ImageButtonCell phoneBill;
    private ImageButtonCell illegal;
    private ImageButtonCell search;

    @Override
    public void onBeforeCreate() {

    }

    @Override
    public View onCreate() {
        context = getContext();
        ScrollView scrollView = new ScrollView(context);

        LinearLayout cont = new LinearLayout(context);
        cont.setOrientation(LinearLayout.VERTICAL);
        scrollView.addView(cont, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        actionBar.setVisibility(View.VISIBLE);
        actionBar.setCenterView(AndroidUtils.getString(R.string.user_center));

        SectionView commonServ = new SectionView(context, AndroidUtils.getString(R.string.common_service));
        EditeItemCell loaction = new EditeItemCell(context, AndroidUtils.getString(R.string.common_service));
//        myLocation.addView(loaction);
        cont.addView(commonServ);


        LinearLayout commonServiceCon = new LinearLayout(context);
        commonServiceCon.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams bcLL = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        bcLL.setMargins(0, AndroidUtils.dp(25), 0, 0);
        cont.addView(commonServiceCon, bcLL);

        ems = new ImageButtonCell(context, AndroidUtils.getString(R.string.ems));
        ems.setTextType();
        ems.setOnClickListener(this);
        LinearLayout.LayoutParams emsll = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        emsll.weight = 1;
        ems.setPicture(R.mipmap.yiwancheng, 40, 40);
//        blBtn0.setPicture(R.mipmap.yiwancheng);
        commonServiceCon.addView(ems, emsll);

        phoneBill = new ImageButtonCell(context, AndroidUtils.getString(R.string.phone_bill));
        phoneBill.setTextType();
        phoneBill.setOnClickListener(this);
        LinearLayout.LayoutParams phoneBiLL = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        phoneBiLL.weight = 1;
        phoneBill.setPicture(R.mipmap.yiwancheng, 40, 40);
        commonServiceCon.addView(phoneBill, phoneBiLL);

        illegal = new ImageButtonCell(context, AndroidUtils.getString(R.string.illeglal));
        illegal.setTextType();
        illegal.setOnClickListener(this);
        LinearLayout.LayoutParams illegalLL = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        illegalLL.weight = 1;
        illegal.setPicture(R.mipmap.yiwancheng, 40, 40);
        commonServiceCon.addView(illegal, illegalLL);

        search = new ImageButtonCell(context, AndroidUtils.getString(R.string.search));
        search.setTextType();
        search.setOnClickListener(this);
        LinearLayout.LayoutParams searchLL = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        searchLL.weight = 1;
        search.setPicture(R.mipmap.yiwancheng, 40, 40);
        commonServiceCon.addView(search, searchLL);


        Space space = new Space(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 30);
        space.setLayoutParams(params);
        cont.addView(space);


        LinearLayout commonServiceCon1 = new LinearLayout(context);
        commonServiceCon1.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams bcLL1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        bcLL.setMargins(0, AndroidUtils.dp(25), 0, 0);
        cont.addView(commonServiceCon1, bcLL1);

        ImageButtonCell ems1 = new ImageButtonCell(context, AndroidUtils.getString(R.string.ems));
        ems1.setTextType();
//        blBtn.setOnClickListener(this);
        LinearLayout.LayoutParams emsll1 = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        emsll1.weight = 1;
        ems1.setPicture(R.mipmap.yiwancheng, 40, 40);
//        blBtn0.setPicture(R.mipmap.yiwancheng);
        commonServiceCon1.addView(ems1, emsll1);

        ImageButtonCell phoneBill1 = new ImageButtonCell(context, AndroidUtils.getString(R.string.phone_bill));
        phoneBill1.setTextType();
//        brBtn.setOnClickListener(this);
        LinearLayout.LayoutParams phoneBiLL1 = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        phoneBiLL1.weight = 1;
        phoneBill1.setPicture(R.mipmap.yiwancheng, 40, 40);
        commonServiceCon1.addView(phoneBill1, phoneBiLL1);

        ImageButtonCell illegal1 = new ImageButtonCell(context, AndroidUtils.getString(R.string.illeglal));
        illegal1.setTextType();
//        brBtn.setOnClickListener(this);
        LinearLayout.LayoutParams illegalLL1 = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        illegalLL1.weight = 1;
        illegal1.setPicture(R.mipmap.yiwancheng, 40, 40);
        commonServiceCon1.addView(illegal1, illegalLL1);

        ImageButtonCell search1 = new ImageButtonCell(context, AndroidUtils.getString(R.string.search));
        search1.setTextType();
//        brBtn.setOnClickListener(this);
        LinearLayout.LayoutParams searchLL1 = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        searchLL1.weight = 1;
        search1.setPicture(R.mipmap.yiwancheng, 40, 40);
        commonServiceCon1.addView(search1, searchLL1);

        Space space1 = new Space(context);
        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 30);
        space.setLayoutParams(params1);
        cont.addView(space1);


        SectionView commonSF = new SectionView(context, AndroidUtils.getString(R.string.common_software));
        cont.addView(commonSF);


        LinearLayout commonSfCon = new LinearLayout(context);
        commonSfCon.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams comSfLL = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        comSfLL.setMargins(0, AndroidUtils.dp(25), 0, 0);
        cont.addView(commonSfCon, comSfLL);

        ImageButtonCell usGroup = new ImageButtonCell(context, AndroidUtils.getString(R.string.us_group));
        usGroup.setTextType();
//        blBtn.setOnClickListener(this);
        LinearLayout.LayoutParams usGroupll = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        usGroupll.weight = 1;
        usGroup.setPicture(R.mipmap.yiwancheng, 40, 40);
//        blBtn0.setPicture(R.mipmap.yiwancheng);
        commonSfCon.addView(usGroup, usGroupll);

        ImageButtonCell ctrip = new ImageButtonCell(context, AndroidUtils.getString(R.string.c_trip));
        ctrip.setTextType();
//        brBtn.setOnClickListener(this);
        LinearLayout.LayoutParams cTripLL = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        cTripLL.weight = 1;
        ctrip.setPicture(R.mipmap.yiwancheng, 40, 40);
        commonSfCon.addView(ctrip, cTripLL);

        ImageButtonCell city = new ImageButtonCell(context, AndroidUtils.getString(R.string.one_city));
        city.setTextType();
//        brBtn.setOnClickListener(this);
        LinearLayout.LayoutParams cityLL = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        cityLL.weight = 1;
        city.setPicture(R.mipmap.yiwancheng, 40, 40);
        commonSfCon.addView(city, cityLL);

        ImageButtonCell baiduyun = new ImageButtonCell(context, AndroidUtils.getString(R.string.baidu_yun));
        baiduyun.setTextType();
//        brBtn.setOnClickListener(this);
        LinearLayout.LayoutParams baiduyunLL = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        baiduyunLL.weight = 1;
        baiduyun.setPicture(R.mipmap.yiwancheng, 40, 40);
        commonSfCon.addView(baiduyun, baiduyunLL);


        init();
        return scrollView;
    }

    private void init() {
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, WebviewActivity.class);
        if (v == ems) {
            intent.putExtra("url", Constants.EMS_INDEX);
        } else if (v == phoneBill) {
            intent.putExtra("url", Constants.PHONE_BILL_INDEX);
        } else if (v == illegal) {
            intent.putExtra("url", Constants.ILLEGAL_INDEX);
        } else if (v == search) {
            intent.putExtra("url", Constants.SEARCH_INDEX);
        }
        startActivity(intent);
    }
}
