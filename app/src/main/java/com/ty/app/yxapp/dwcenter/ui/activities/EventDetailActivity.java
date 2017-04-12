package com.ty.app.yxapp.dwcenter.ui.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.ty.app.yxapp.dwcenter.R;
import com.ty.app.yxapp.dwcenter.bean.Event;
import com.ty.app.yxapp.dwcenter.ui.activities.base.BaseActivity;
import com.ty.app.yxapp.dwcenter.ui.widget.AddMoreCell;
import com.ty.app.yxapp.dwcenter.ui.widget.EditeItemCell;
import com.ty.app.yxapp.dwcenter.ui.widget.MenuDialog;
import com.ty.app.yxapp.dwcenter.ui.widget.SectionView;
import com.ty.app.yxapp.dwcenter.ui.widget.ViewCloud;
import com.ty.app.yxapp.dwcenter.utils.AndroidUtils;
import com.ty.app.yxapp.dwcenter.utils.AudioRecoderUtils;
import com.ty.app.yxapp.dwcenter.utils.PopupWindowFactory;

import java.util.ArrayList;
import java.util.List;

public class EventDetailActivity extends BaseActivity {

    private List<Bitmap> photos = new ArrayList<>();
    private List<Uri> videos = new ArrayList<>();
    private List<String> voices = new ArrayList<>();

    @Override
    public void onBeforeCreate() {
    }

    @Override
    public View onCreate() {
        Intent intent = this.getIntent();
        Event.EventBody eventBody = (Event.EventBody) intent.getSerializableExtra("event");
        String eventName = eventBody != null ? eventBody.getEvent_title() : "";
        String eventDesc = eventBody != null ? eventBody.getEvent_district_code() : "";
//        String eventAddr = (eventBody != null ? eventBody.getEvent_x() : "") + "," + (eventBody != null ? eventBody.getEvent_y() : "");
        String eventAddr = (eventBody != null ? eventBody.getEvent_dz() : "");
        String eventStatus = eventBody != null ? eventBody.getEvent_dlmc() : "";
        String eventTime = eventBody != null ? eventBody.getEvent_creattime() : "";
        List<Event.EventRecord> recordList = eventBody != null ? eventBody.getEvent_record() : null;

        actionBar.setVisibility(View.VISIBLE);
        actionBar.setLeftView("", R.mipmap.back, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        actionBar.setCenterView(AndroidUtils.getString(R.string.event_detail));

        ScrollView scrollView = new ScrollView(this);
        LinearLayout container = new LinearLayout(this);
//        container.setBackgroundColor(0xFFF5F5F5);
        container.setOrientation(LinearLayout.VERTICAL);
        container.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        scrollView.addView(container);

        EditeItemCell name = new EditeItemCell(this, AndroidUtils.getString(R.string.event_name) + ": " + eventName);
        container.addView(name);

        EditeItemCell desc = new EditeItemCell(this, AndroidUtils.getString(R.string.event_desc) + ": " + eventDesc);
        container.addView(desc);

        EditeItemCell address = new EditeItemCell(this, AndroidUtils.getString(R.string.event_address) + ": " + eventAddr);
        container.addView(address);

        EditeItemCell status = new EditeItemCell(this, AndroidUtils.getString(R.string.event_status) + ": " + eventStatus);
        container.addView(status);

        EditeItemCell time = new EditeItemCell(this, AndroidUtils.getString(R.string.event_execute_time) + ": " + eventTime);

        SectionView photoCon = new SectionView(this, AndroidUtils.getString(R.string.image));
        LinearLayout pcon = new LinearLayout(this);
        pcon.setOrientation(LinearLayout.HORIZONTAL);
        pcon.setPadding(AndroidUtils.dp(15), 0, AndroidUtils.dp(15), 0);
        photoCon.addView(pcon, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        ViewCloud photoCloud = new ViewCloud(this);
        photoCloud.postView(photos);
        pcon.addView(photoCloud, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        container.addView(photoCon);

        SectionView voiceCon = new SectionView(this, AndroidUtils.getString(R.string.voice));
        LinearLayout vcon = new LinearLayout(this);
        vcon.setOrientation(LinearLayout.HORIZONTAL);
        vcon.setPadding(AndroidUtils.dp(15), 0, AndroidUtils.dp(15), 0);
        voiceCon.addView(vcon, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        ViewCloud voiceCloud = new ViewCloud(this);
        voiceCloud.postView(voices);
        voiceCloud.setAddMoreText(AndroidUtils.getString(R.string.start_recoder));
        vcon.addView(voiceCloud, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        container.addView(voiceCon);

        SectionView videoCon = new SectionView(this, AndroidUtils.getString(R.string.video));
        LinearLayout videoC = new LinearLayout(this);
        videoC.setOrientation(LinearLayout.HORIZONTAL);
        videoC.setPadding(AndroidUtils.dp(15), 0, AndroidUtils.dp(15), 0);
        videoCon.addView(videoC, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        ViewCloud videoCloud = new ViewCloud(this);
        videoCloud.postView(videos);
        videoC.addView(videoCloud, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        container.addView(videoCon);


        String recordName;
        String recordTime;
        String dealMsg;
        if (recordList != null && recordList.size() != 0) {
            for (Event.EventRecord record : recordList) {
                recordName = record.getRecord_account();
                recordTime = record.getRecord_czsj();
                dealMsg = record.getRecord_clyj().equals("") ? AndroidUtils.getString(R.string.no_deal_msg) : record.getRecord_clyj();

                SectionView recordCon = new SectionView(this, "");
                LinearLayout recordC = new LinearLayout(this);
                recordC.setOrientation(LinearLayout.HORIZONTAL);
                recordC.setPadding(AndroidUtils.dp(15), 0, 0, 0);
                recordCon.addView(recordC, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
                EditeItemCell rdName = new EditeItemCell(this, recordName + " " + recordTime + " " + AndroidUtils.getString(R.string.deal_msg));
                recordCon.addView(rdName);
                EditeItemCell rdMsg = new EditeItemCell(this, dealMsg);
                recordCon.addView(rdMsg);
                container.addView(recordCon);

            }
        }


        View view = new View(this);
        container.addView(view, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                AndroidUtils.dp(30)));
        return scrollView;
    }
}
