package com.ty.app.yxapp.dwcenter.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.hyphenate.chat.EMMessage;
import com.squareup.picasso.Picasso;
import com.ty.app.yxapp.dwcenter.R;
import com.ty.app.yxapp.dwcenter.bean.Event;
import com.ty.app.yxapp.dwcenter.ui.activities.base.BaseActivity;
import com.ty.app.yxapp.dwcenter.ui.im.ChatController;
import com.ty.app.yxapp.dwcenter.ui.im.VideoChatActivity;
import com.ty.app.yxapp.dwcenter.ui.widget.EditeItemCell;
import com.ty.app.yxapp.dwcenter.ui.widget.SectionView;
import com.ty.app.yxapp.dwcenter.ui.widget.ViewCloud;
import com.ty.app.yxapp.dwcenter.utils.AndroidUtils;
import com.ty.app.yxapp.dwcenter.utils.UpLoadFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static android.R.id.icon;
import static android.R.id.message;

public class EventDetailActivity extends BaseActivity {

    private ArrayList<CharSequence> photos = new ArrayList<>();
    private List<String> videos = new ArrayList<>();
    private List<String> voices = new ArrayList<>();
    private ViewCloud photoCloud;
    private ViewCloud voiceCloud;
    private ViewCloud videoCloud;
    private MediaPlayer mediaPlayer;
    private Context context;

    @Override
    public void onBeforeCreate() {
    }

    @Override
    public View onCreate() {
        context = getBaseContext();
        Intent intent = this.getIntent();
        Event.EventBody eventBody = (Event.EventBody) intent.getSerializableExtra("event");
        String eventName = eventBody != null ? eventBody.getEvent_title() : "";
        String eventDesc = eventBody != null ? eventBody.getEvent_district_code() : "";
//        String eventAddr = (eventBody != null ? eventBody.getEvent_x() : "") + "," + (eventBody != null ? eventBody.getEvent_y() : "");
        String eventAddr = (eventBody != null ? eventBody.getEvent_dz() : "");
        String eventStatus = eventBody != null ? eventBody.getEvent_sjzt() : "";
        String eventTime = eventBody != null ? eventBody.getEvent_creattime() : "";
        String photoUrl = eventBody.getEvent_tplj();
        String voiceUrl = eventBody.getEvent_yylj();
        String videoUrl = eventBody.getEvent_splj();
        List<Event.EventRecord> recordList = eventBody.getEvent_record();
        if(photoUrl.contains(",")){
            String[] p = photoUrl.split(",");
            if(p!= null && p.length > 0){
                for(String s: p){
                    photos.add("https://cp.dawawg.com/caseplatform/file-down?id="+s);
                }
            }
        }else{
            photos.add("https://cp.dawawg.com/caseplatform/file-down?id="+photoUrl);
        }

        if(voiceUrl.contains(",")){
            String[] p = voiceUrl.split(",");
            if(p!= null && p.length > 0){
                for(String s: p){
                    voices.add("https://cp.dawawg.com/caseplatform/file-down?id="+s);
                }
            }
        }else{
            voices.add("https://cp.dawawg.com/caseplatform/file-down?id="+voiceUrl);
        }

        if(videoUrl.contains(",")){
            String[] p = videoUrl.split(",");
            if(p!= null && p.length > 0){
                for(String s: p){
                    videos.add("https://cp.dawawg.com/caseplatform/file-down?id="+s);
                }
            }
        }else{
            videos.add("https://cp.dawawg.com/caseplatform/file-down?id="+videoUrl);
        }


        actionBar.setVisibility(View.VISIBLE);
        actionBar.setLeftView("", R.mipmap.back, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        actionBar.setCenterView(AndroidUtils.getString(R.string.event_detail));
/*        actionBar.setRightView(AndroidUtils.getString(R.string.feedback_list), 0, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent feedBackIntent = new Intent(EventDetailActivity.this, FeedBackListActivity.class);
                startActivity(feedBackIntent);
            }
        });*/

        ScrollView scrollView = new ScrollView(this);
        LinearLayout container = new LinearLayout(this);
//        container.setBackgroundColor(0xFFF5F5F5);
        container.setOrientation(LinearLayout.VERTICAL);
        container.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        scrollView.addView(container);

        EditeItemCell name = new EditeItemCell(this, AndroidUtils.getString(R.string.event_name) + ": " + eventName);
        name.hideRightImg();
        container.addView(name);

        EditeItemCell desc = new EditeItemCell(this, AndroidUtils.getString(R.string.event_desc) + ": " + eventDesc);
        desc.hideRightImg();
        container.addView(desc);

        EditeItemCell address = new EditeItemCell(this, AndroidUtils.getString(R.string.event_address) + ": " + eventAddr);
        address.hideRightImg();
        container.addView(address);

        EditeItemCell status = new EditeItemCell(this, AndroidUtils.getString(R.string.event_status) + ": " + eventStatus);
        status.hideRightImg();
        container.addView(status);

        EditeItemCell time = new EditeItemCell(this, AndroidUtils.getString(R.string.event_execute_time) + ": " + eventTime);

        SectionView photoCon = new SectionView(this, AndroidUtils.getString(R.string.image));
        LinearLayout pcon = new LinearLayout(this);
        pcon.setOrientation(LinearLayout.HORIZONTAL);
        pcon.setPadding(AndroidUtils.dp(15), 0, AndroidUtils.dp(15), 0);
        photoCon.addView(pcon, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        photoCloud = new ViewCloud(this);
        photoCloud.postView(photos,true);
        photoCloud.setOnItemClickListener(onItemClick);
        pcon.addView(photoCloud, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        container.addView(photoCon);

        SectionView voiceCon = new SectionView(this, AndroidUtils.getString(R.string.voice));
        LinearLayout vcon = new LinearLayout(this);
        vcon.setOrientation(LinearLayout.HORIZONTAL);
        vcon.setPadding(AndroidUtils.dp(15), 0, AndroidUtils.dp(15), 0);
        voiceCon.addView(vcon, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        voiceCloud = new ViewCloud(this);
        voiceCloud.setOnItemClickListener(onItemClick);
        voiceCloud.postView(voices,true);
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
        videoCloud = new ViewCloud(this);
        videoCloud.postView(videos,true);
        videoCloud.setOnItemClickListener(onItemClick);
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
                rdName.hideRightImg();
                recordCon.addView(rdName);
                EditeItemCell rdMsg = new EditeItemCell(this, dealMsg);
                rdMsg.hideRightImg();
                recordCon.addView(rdMsg);
                container.addView(recordCon);

            }
        }


        View view = new View(this);
        container.addView(view, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                AndroidUtils.dp(30)));
        return scrollView;
    }


    private ViewCloud.OnItemClickListener onItemClick = new ViewCloud.OnItemClickListener() {
        @Override
        public void onClick(int pos,View v) {
            if(v == photoCloud){
                if(!photos.isEmpty()){
                    Intent intent = new Intent();
                    intent.putExtra("cur_position",0);
                    intent.putCharSequenceArrayListExtra("items",photos);
                    intent.setClass(EventDetailActivity.this,PhotoActivity.class);
                    startActivity(intent);
                }
            }else if(v == voiceCloud){
                if(!voices.isEmpty() && voices.size() > 1){
                    playVoice(voices.get(pos));
                }else if(voices.size() == 1 && voices.get(0).equals("https://cp.dawawg.com/caseplatform/file-down?id=")){
                    Toast.makeText(context,"没有上传语音",Toast.LENGTH_SHORT).show();
                }
            }else if(v == videoCloud){
                if(!videos.isEmpty() && videos.size() > 1){
                    Intent intent = new Intent();
                    intent.putExtra("uri",videos.get(pos));
                    intent.setClass(EventDetailActivity.this,VideoActiv.class);
                    startActivity(intent);
                }else if(videos.size() == 1 && videos.get(0).equals("https://cp.dawawg.com/caseplatform/file-down?id=")){
                    Toast.makeText(context,"没有上传视频",Toast.LENGTH_SHORT).show();
                }
            }
        }
    };



    public void playVoice(String filePath) {
        if (!(new File(filePath).exists())) {
            return;
        }
        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        mediaPlayer = new MediaPlayer();
        boolean isSpeakerOpened = true;
        //TODO: 判断是否为电话听筒
        if (isSpeakerOpened) {
            audioManager.setMode(AudioManager.MODE_NORMAL);
            audioManager.setSpeakerphoneOn(true);
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_RING);
        } else {
            audioManager.setSpeakerphoneOn(false);// 关闭扬声器
            // 把声音设定成Earpiece（听筒）出来，设定为正在通话中
            audioManager.setMode(AudioManager.MODE_IN_CALL);
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_VOICE_CALL);
        }
        try {
            mediaPlayer.setDataSource(filePath);
            mediaPlayer.prepare();
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                @Override
                public void onCompletion(MediaPlayer mp) {
                    // TODO Auto-generated method stub
                    mediaPlayer.release();
                    mediaPlayer = null;
                    stopPlayVoice(); // stop animation
                }

            });
            mediaPlayer.start();

        } catch (Exception e) {
            System.out.println();
        }
    }

    public void stopPlayVoice() {
        try{
            if (mediaPlayer != null) {
                mediaPlayer.stop();
                mediaPlayer.release();
            }
        }catch (Exception e){}
    }

}
