package com.ty.app.yxapp.dwcenter.ui.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ty.app.yxapp.dwcenter.R;
import com.ty.app.yxapp.dwcenter.Utils.AndroidUtils;
import com.ty.app.yxapp.dwcenter.ui.Activity.base.BaseActivity;
import com.ty.app.yxapp.dwcenter.ui.Widget.LooperImgCell;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private Context context;
    private LooperImgCell looperImgCell;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        context = getBaseContext();
//        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
//    }

    @Override
    public void onBeforeCreate() {
        requestWindowFeature(false);
        context = getBaseContext();
    }

    @Override
    public View onCreate() {
        LinearLayout container = new LinearLayout(context);
        container.setOrientation(LinearLayout.VERTICAL);

        looperImgCell = new LooperImgCell(context);
        container.addView(looperImgCell,new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                AndroidUtils.dp(200)));

        init();
        return container;
    }

    private void init(){
        List<Integer> resList = new ArrayList<>();
        for(int i=0;i<3;i++){
            resList.add(R.drawable.timg);
        }
        looperImgCell.setResList(resList);
    }

}
