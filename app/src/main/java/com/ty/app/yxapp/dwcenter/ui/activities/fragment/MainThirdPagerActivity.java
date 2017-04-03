package com.ty.app.yxapp.dwcenter.ui.activities.fragment;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.ty.app.yxapp.dwcenter.R;
import com.ty.app.yxapp.dwcenter.utils.AndroidUtils;
import com.ty.app.yxapp.dwcenter.ui.activities.base.BaseFragment;
import com.ty.app.yxapp.dwcenter.ui.widget.LooperImgCell;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kss on 2017/3/26.
 */

public class MainThirdPagerActivity extends BaseFragment {
    private Context context;
    private LooperImgCell looperImgCell;

    @Override
    public void onBeforeCreate() {

    }

    @Override
    public View onCreate() {
        context = getContext();
        ScrollView scrollView = new ScrollView(context);

        LinearLayout cont = new LinearLayout(context);
        cont.setOrientation(LinearLayout.VERTICAL);
        scrollView.addView(cont,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        actionBar.setVisibility(View.VISIBLE);
        actionBar.setCenterView(AndroidUtils.getString(R.string.user_center));
        actionBar.setRightView("", R.mipmap.right_top_set, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"饿呢奥尔够昂偶", Toast.LENGTH_SHORT).show();
            }
        });

        looperImgCell = new LooperImgCell(context);
        cont.addView(looperImgCell,new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                AndroidUtils.dp(200)));

        init();
        return scrollView;
    }

    private void init(){
        List<Integer> resList = new ArrayList<>();
        for(int i=0;i<3;i++){
            resList.add(R.drawable.timg);
        }
        looperImgCell.setResList(resList);
    }

}
