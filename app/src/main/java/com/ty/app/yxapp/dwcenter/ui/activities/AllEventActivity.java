package com.ty.app.yxapp.dwcenter.ui.activities;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.ty.app.yxapp.dwcenter.R;
import com.ty.app.yxapp.dwcenter.bean.Event;
import com.ty.app.yxapp.dwcenter.network.Result;
import com.ty.app.yxapp.dwcenter.network.RetrofitHelper;
import com.ty.app.yxapp.dwcenter.ui.activities.base.BaseActivity;
import com.ty.app.yxapp.dwcenter.ui.widget.EmptyView;
import com.ty.app.yxapp.dwcenter.utils.AndroidUtils;
import com.ty.app.yxapp.dwcenter.utils.EventAdapter;

import java.util.List;

public class AllEventActivity extends BaseActivity implements AdapterView.OnItemClickListener{
    private static final String TAG = AllEventActivity.class.getSimpleName();

    private Context context;
    private ListView eventLv;
    private EventAdapter mAdapter;
    public static List<Event> allEvents;
    private RelativeLayout view;
    private EmptyView emptyView;


    @Override
    public void onBeforeCreate() {

    }

    @Override
    public View onCreate() {
        actionBar.setVisibility(View.VISIBLE);
        actionBar.setCenterView(AndroidUtils.getString(R.string.all_event));
        actionBar.setLeftView("", R.mipmap.back, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        context = getBaseContext();
        view = (RelativeLayout) getLayoutInflater().inflate(R.layout.activity_all_event, null);
        eventLv = (ListView) view.findViewById(R.id.listview_event);
        eventLv.setVisibility(View.GONE);
        mAdapter = new EventAdapter(this);
        eventLv.setAdapter((ListAdapter) mAdapter);
        eventLv.setOnItemClickListener(this);

        emptyView = new EmptyView(context);
        view.addView(emptyView,new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        getData();
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    public void getData(){
        RetrofitHelper.getInstance().getEvents("wangjie", new RetrofitHelper.OnResultListener() {
            @Override
            public void onResult(final Result result) {
                if(result.isOK()){
                    allEvents = (List<Event>) result.getData();
                    if(allEvents != null && !allEvents.isEmpty()){
                        AllEventActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                eventLv.setVisibility(View.VISIBLE);
                                emptyView.setVisibility(View.GONE);
                                mAdapter.setList(allEvents);
                            }
                        });
                    }else{
                        AllEventActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                eventLv.setVisibility(View.GONE);
                                emptyView.setVisibility(View.VISIBLE);
                                emptyView.setLoadingText(AndroidUtils.getString(R.string.no_data));
                            }
                        });
                    }
                }else{
                    AllEventActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            eventLv.setVisibility(View.GONE);
                            emptyView.setVisibility(View.VISIBLE);
                            emptyView.setLoadingText(AndroidUtils.getString(R.string.no_newwork));
                            Toast.makeText(context,result.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });
    }
}
