package com.ty.app.yxapp.dwcenter.ui.activities;

import android.content.Context;
import android.util.Log;
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
import com.ty.app.yxapp.dwcenter.ui.activities.base.Constants;
import com.ty.app.yxapp.dwcenter.ui.widget.EmptyView;
import com.ty.app.yxapp.dwcenter.utils.AndroidUtils;
import com.ty.app.yxapp.dwcenter.utils.EventAdapter;
import com.ty.app.yxapp.dwcenter.utils.SPManager;

import java.util.List;

public class EventActivity extends BaseActivity implements AdapterView.OnItemClickListener{
    private static final String TAG = EventActivity.class.getSimpleName();

    private Context context;
    private ListView eventLv;
    private EventAdapter mAdapter;
    public static List<Event.EventBody> allEvents;
    private RelativeLayout view;
    private EmptyView emptyView;


    @Override
    public void onBeforeCreate() {

    }

    @Override
    public View onCreate() {
        int eventType = getIntent().getIntExtra("eventType",0);
        actionBar.setVisibility(View.VISIBLE);
        switch (eventType){
            case Constants.EVENT_TO_DO_INDEX:
                actionBar.setCenterView(AndroidUtils.getString(R.string.to_do_event));
                break;
            case Constants.EVENT_ALREADY_COMPLETED:
                actionBar.setCenterView(AndroidUtils.getString(R.string.already_completed_event));
                break;
            case Constants.EVENT_ALL_INDEX:
                actionBar.setCenterView(AndroidUtils.getString(R.string.all_event));
                break;
        }
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

        getData(eventType);
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    public void getData(int eventType){
        SPManager spManager = new SPManager();
        String userName = spManager.readSp(Constants.SP_USER_NAME);

        RetrofitHelper.getInstance().getEvents(userName,""+eventType,new RetrofitHelper.OnResultListener() {
            @Override
            public void onResult(final Result result) {
                Log.e(TAG,result.getCode()+","+result.getMessage()+","+result.getData());
                if(result.isOK()){
                    allEvents = (List<Event.EventBody>) result.getData();
                    if(allEvents != null && !allEvents.isEmpty()){
                        EventActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                eventLv.setVisibility(View.VISIBLE);
                                emptyView.setVisibility(View.GONE);
                                mAdapter.setList(allEvents);
                            }
                        });
                    }else{
                        EventActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                eventLv.setVisibility(View.GONE);
                                emptyView.setVisibility(View.VISIBLE);
                                emptyView.setLoadingText(AndroidUtils.getString(R.string.no_data));
                            }
                        });
                    }
                }else{
                    EventActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            eventLv.setVisibility(View.GONE);
                            emptyView.setVisibility(View.VISIBLE);
                            emptyView.setLoadingText(AndroidUtils.getString(R.string.no_network));
                            Toast.makeText(context,result.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });
    }

   /* private void getData(int eventType){
        Retrofit retrofit = RetrofitHelper.getInstance().getRetrofit();
        RequestServer server =  retrofit.create(RequestServer.class);
        Call<Event> call = server.getEvents("wangjie",""+eventType);
        call.enqueue(new Callback<Event>() {
            @Override
            public void onResponse(Call<Event> call, Response<Event> response) {
                Log.e(TAG,call.toString()+"--"+response.code()+",,"+response.message()+",,"+response.body());
            }

            @Override
            public void onFailure(Call<Event> call, Throwable t) {
                Log.e(TAG,t.toString());
            }
        });

    }*/
}
