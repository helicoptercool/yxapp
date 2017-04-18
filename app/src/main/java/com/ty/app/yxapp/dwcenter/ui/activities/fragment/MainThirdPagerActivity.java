package com.ty.app.yxapp.dwcenter.ui.activities.fragment;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.Space;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.ty.app.yxapp.dwcenter.R;
import com.ty.app.yxapp.dwcenter.ui.activities.WebviewActivity;
import com.ty.app.yxapp.dwcenter.ui.activities.base.Constants;
import com.ty.app.yxapp.dwcenter.ui.widget.ImageButtonCell;
import com.ty.app.yxapp.dwcenter.ui.widget.SectionView;
import com.ty.app.yxapp.dwcenter.utils.AndroidUtils;
import com.ty.app.yxapp.dwcenter.ui.activities.base.BaseFragment;

/**
 * Created by kss on 2017/3/26.
 */

public class MainThirdPagerActivity extends BaseFragment implements View.OnClickListener {
    private Context context;
    private ImageButtonCell ems;
    private ImageButtonCell phoneBill;
    private ImageButtonCell illegal;
    private ImageButtonCell search;
    private ImageButtonCell xinlang;
    private ImageButtonCell sohu;
    private ImageButtonCell tencent;
    private ImageButtonCell wangyi;
    private ImageButtonCell usGroup;
    private ImageButtonCell ctrip;
    private ImageButtonCell sameCity;
    private ImageButtonCell baiduyun;

    private ImageButtonCell tianmao;
    private ImageButtonCell eleme;
    private ImageButtonCell jingdong;
    private ImageButtonCell youku;

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
//        EditeItemCell commonServ = new EditeItemCell(context, AndroidUtils.getString(R.string.common_service));
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
        ems.setPicture(R.mipmap.wuliu, 40, 40);
        commonServiceCon.addView(ems, emsll);

        phoneBill = new ImageButtonCell(context, AndroidUtils.getString(R.string.phone_bill));
        phoneBill.setTextType();
        phoneBill.setOnClickListener(this);
        LinearLayout.LayoutParams phoneBiLL = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        phoneBiLL.weight = 1;
        phoneBill.setPicture(R.mipmap.huafeichongzhi, 40, 40);
        commonServiceCon.addView(phoneBill, phoneBiLL);

        illegal = new ImageButtonCell(context, AndroidUtils.getString(R.string.illeglal));
        illegal.setTextType();
        illegal.setOnClickListener(this);
        LinearLayout.LayoutParams illegalLL = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        illegalLL.weight = 1;
        illegal.setPicture(R.mipmap.weizhang, 40, 40);
        commonServiceCon.addView(illegal, illegalLL);

        search = new ImageButtonCell(context, AndroidUtils.getString(R.string.search));
        search.setTextType();
        search.setOnClickListener(this);
        LinearLayout.LayoutParams searchLL = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        searchLL.weight = 1;
        search.setPicture(R.mipmap.search, 40, 40);
        commonServiceCon.addView(search, searchLL);


        Space space = new Space(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 30);
        space.setLayoutParams(params);
        cont.addView(space);


        LinearLayout commonServiceCon1 = new LinearLayout(context);
        commonServiceCon1.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams bcLL1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        bcLL1.setMargins(0, AndroidUtils.dp(25), 0, 0);
        cont.addView(commonServiceCon1, bcLL1);

        xinlang = new ImageButtonCell(context, AndroidUtils.getString(R.string.xinlang));
        xinlang.setTextType();
        xinlang.setOnClickListener(this);
        LinearLayout.LayoutParams xinlangll = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        xinlangll.weight = 1;
        xinlang.setPicture(R.mipmap.sina_logo, 40, 40);
//        blBtn0.setPicture(R.mipmap.yiwancheng);
        commonServiceCon1.addView(xinlang, xinlangll);

        sohu = new ImageButtonCell(context, AndroidUtils.getString(R.string.sohu));
        sohu.setTextType();
        sohu.setOnClickListener(this);
        LinearLayout.LayoutParams sohull = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        sohull.weight = 1;
        sohu.setPicture(R.mipmap.sohu_logo, 40, 40);
        commonServiceCon1.addView(sohu, sohull);

        tencent = new ImageButtonCell(context, AndroidUtils.getString(R.string.qq));
        tencent.setTextType();
        tencent.setOnClickListener(this);
        LinearLayout.LayoutParams tencentll = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        tencentll.weight = 1;
        tencent.setPicture(R.mipmap.qq, 40, 40);
        commonServiceCon1.addView(tencent, tencentll);

        wangyi = new ImageButtonCell(context, AndroidUtils.getString(R.string.wangyi));
        wangyi.setTextType();
        wangyi.setOnClickListener(this);
        LinearLayout.LayoutParams wangyill = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        wangyill.weight = 1;
        wangyi.setPicture(R.mipmap.wangyi, 40, 40);
        commonServiceCon1.addView(wangyi, wangyill);

        Space space1 = new Space(context);
        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 30);
        space1.setLayoutParams(params1);
        cont.addView(space1);


        SectionView commonSF = new SectionView(context, AndroidUtils.getString(R.string.common_software));
        cont.addView(commonSF);


        LinearLayout commonSfCon = new LinearLayout(context);
        commonSfCon.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams comSfLL = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        comSfLL.setMargins(0, AndroidUtils.dp(25), 0, 0);
        cont.addView(commonSfCon, comSfLL);

        usGroup = new ImageButtonCell(context, AndroidUtils.getString(R.string.us_group));
        usGroup.setTextType();
        usGroup.setOnClickListener(this);
        LinearLayout.LayoutParams usGroupll = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        usGroupll.weight = 1;
        usGroup.setPicture(R.mipmap.mt_logo, 40, 40);
//        blBtn0.setPicture(R.mipmap.yiwancheng);
        commonSfCon.addView(usGroup, usGroupll);

        ctrip = new ImageButtonCell(context, AndroidUtils.getString(R.string.c_trip));
        ctrip.setTextType();
        ctrip.setOnClickListener(this);
        LinearLayout.LayoutParams cTripLL = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        cTripLL.weight = 1;
        ctrip.setPicture(R.mipmap.xc_logo, 40, 40);
        commonSfCon.addView(ctrip, cTripLL);

        sameCity = new ImageButtonCell(context, AndroidUtils.getString(R.string.one_city));
        sameCity.setTextType();
        sameCity.setOnClickListener(this);
        LinearLayout.LayoutParams cityLL = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        cityLL.weight = 1;
        sameCity.setPicture(R.mipmap.samecity_logo, 40, 40);
        commonSfCon.addView(sameCity, cityLL);

        baiduyun = new ImageButtonCell(context, AndroidUtils.getString(R.string.baidu_yun));
        baiduyun.setTextType();
        baiduyun.setOnClickListener(this);
        LinearLayout.LayoutParams baiduyunLL = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        baiduyunLL.weight = 1;
        baiduyun.setPicture(R.mipmap.baidu_logo, 40, 40);
        commonSfCon.addView(baiduyun, baiduyunLL);

        Space space2 = new Space(context);
        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 30);
        space2.setLayoutParams(params2);
        cont.addView(space2);

        LinearLayout commonSfCon1 = new LinearLayout(context);
        commonSfCon1.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams csfLL1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        csfLL1.setMargins(0, AndroidUtils.dp(25), 0, 0);
        cont.addView(commonSfCon1, csfLL1);

        tianmao = new ImageButtonCell(context, AndroidUtils.getString(R.string.tianmao));
        tianmao.setTextType();
        tianmao.setOnClickListener(this);
        LinearLayout.LayoutParams tianmaoll = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        tianmaoll.weight = 1;
        tianmao.setPicture(R.mipmap.tm_logo, 40, 40);
        commonSfCon1.addView(tianmao, tianmaoll);

        eleme = new ImageButtonCell(context, AndroidUtils.getString(R.string.dazhongdianping));
        eleme.setTextType();
        eleme.setOnClickListener(this);
        LinearLayout.LayoutParams elemell = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        elemell.weight = 1;
        eleme.setPicture(R.mipmap.dazhongdianping, 40, 40);
        commonSfCon1.addView(eleme, elemell);

        jingdong = new ImageButtonCell(context, AndroidUtils.getString(R.string.jingdong));
        jingdong.setTextType();
        jingdong.setOnClickListener(this);
        LinearLayout.LayoutParams jingdongll = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        jingdongll.weight = 1;
        jingdong.setPicture(R.mipmap.jd_logo, 40, 40);
        commonSfCon1.addView(jingdong, jingdongll);

        youku = new ImageButtonCell(context, AndroidUtils.getString(R.string.youku));
        youku.setTextType();
        youku.setOnClickListener(this);
        LinearLayout.LayoutParams youkull = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        youkull.weight = 1;
        youku.setPicture(R.mipmap.yk_logo, 40, 40);
        commonSfCon1.addView(youku, youkull);

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
        } else if (v == xinlang) {
            intent.putExtra("url",Constants.SINA_INDEX);
        } else if (v == sohu) {
            intent.putExtra("url",Constants.SOHU_INDEX);
        } else if (v == tencent) {
            intent.putExtra("url",Constants.TECENT_INDEX);
        } else if (v == wangyi) {
            intent.putExtra("url",Constants.WANGYI_INDEX);
        } else if (v == usGroup) {
            intent.putExtra("url",Constants.US_GROUP_INDEX);
        } else if (v == ctrip) {
            intent.putExtra("url",Constants.CTRIP_INDEX);
        } else if (v == sameCity) {
            intent.putExtra("url",Constants.SAME_CITY_INDEX);
        } else if (v == baiduyun) {
            intent.putExtra("url",Constants.BAIDU_YUN_INDEX);
        }else if (v == tianmao){
            intent.putExtra("url",Constants.TMALL_INDEX);
        }else if (v == eleme){
            intent.putExtra("url",Constants.ELEM_INDEX);
        }else if (v == jingdong){
            intent.putExtra("url",Constants.JINGDONG_INDEX);
        }else if (v == youku){
            intent.putExtra("url",Constants.YOUKU_INDEX);
        }
        startActivity(intent);
    }
}
