package com.ty.app.yxapp.dwcenter.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ty.app.yxapp.dwcenter.R;
import com.ty.app.yxapp.dwcenter.bean.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by heli on 2017/4/3.
 */
public class EventAdapter extends BaseAdapter {
    private static final String TAG = EventAdapter.class.getSimpleName();
    private Context mContext;
    private List<Event.EventBody> mList;
    private LayoutInflater mInflater;
    public EventAdapter(Context context){
        mContext = context;
        mList = new ArrayList<>();
        mInflater = LayoutInflater.from(mContext);
    }

    public void setList( List<Event.EventBody> list){
        if(mList != null && !mList.isEmpty()) mList.clear();
        if(list != null && !list.isEmpty()){
            mList = list;
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.layout_event_item,null);
            viewHolder = new ViewHolder();
            viewHolder.timeTv = (TextView) convertView.findViewById(R.id.tv_title);
            viewHolder.timeTv = (TextView) convertView.findViewById(R.id.tv_time);
            viewHolder.reporterTv = (TextView) convertView.findViewById(R.id.tv_reporter);
            viewHolder.effectiveTv = (TextView) convertView.findViewById(R.id.tv_effective);
            convertView.setTag(viewHolder);
        }
        viewHolder = (ViewHolder) convertView.getTag();
//        viewHolder.titleTv.setText(mList.get(position).getF396());
        viewHolder.timeTv.setText(mList.get(position).getEvent_creattime());
        viewHolder.reporterTv.setText(mList.get(position).getEvent_account());
        viewHolder.effectiveTv.setText(mList.get(position).getEvent_district_code());
//        viewHolder.contentTv.setText(mList.get(position).getF397());
        return convertView;
    }

    private class ViewHolder{
        private TextView titleTv;
        private TextView timeTv;
        private TextView reporterTv;
        private TextView effectiveTv;
        private TextView contentTv;

    }
}
