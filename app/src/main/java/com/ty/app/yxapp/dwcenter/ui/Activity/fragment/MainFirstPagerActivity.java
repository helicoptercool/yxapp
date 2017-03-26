package com.ty.app.yxapp.dwcenter.ui.Activity.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ty.app.yxapp.dwcenter.R;
import com.ty.app.yxapp.dwcenter.Utils.AndroidUtils;
import com.ty.app.yxapp.dwcenter.ui.Widget.LooperImgCell;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kss on 2017/3/26.
 */

public class MainFirstPagerActivity extends Fragment {
    private Context context;
    private LooperImgCell looperImgCell;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = getContext();
        LinearLayout cont = new LinearLayout(context);
        cont.setOrientation(LinearLayout.VERTICAL);

        looperImgCell = new LooperImgCell(context);
        cont.addView(looperImgCell,new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                AndroidUtils.dp(200)));

        init();
        return cont;
    }

    private void init(){
        List<Integer> resList = new ArrayList<>();
        for(int i=0;i<3;i++){
            resList.add(R.drawable.timg);
        }
        looperImgCell.setResList(resList);
    }
}
