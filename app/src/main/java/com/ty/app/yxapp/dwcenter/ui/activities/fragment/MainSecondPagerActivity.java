package com.ty.app.yxapp.dwcenter.ui.activities.fragment;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.ty.app.yxapp.dwcenter.R;
import com.ty.app.yxapp.dwcenter.ui.widget.ViewCloud;
import com.ty.app.yxapp.dwcenter.utils.AndroidUtils;
import com.ty.app.yxapp.dwcenter.ui.activities.base.BaseFragment;
import com.ty.app.yxapp.dwcenter.ui.widget.AddMoreCell;
import com.ty.app.yxapp.dwcenter.ui.widget.EditeItemCell;
import com.ty.app.yxapp.dwcenter.ui.widget.SectionView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kss on 2017/3/26.
 */

public class MainSecondPagerActivity extends BaseFragment implements View.OnClickListener{
    private Context context;
    private EditeItemCell loaction;
    private EditeItemCell name;
    private EditeItemCell desc;
    private AddMoreCell video;
    private AddMoreCell voice;
    private ViewCloud viewCloud;
    private List<Integer> photos = new ArrayList<>();
    private List<Integer> videos = new ArrayList<>();
    private List<Integer> voices = new ArrayList<>();
    private ViewCloud photoCloud;
    private ViewCloud videoCloud;
    private ViewCloud voiceCloud;

    @Override
    public void onBeforeCreate() {

    }

    @Override
    public View onCreate() {
        context = getContext();
        actionBar.setVisibility(View.VISIBLE);
        actionBar.setCenterView(AndroidUtils.getString(R.string.push_work));

        ScrollView scrollView = new ScrollView(context);
        scrollView.setId(R.id.second_pager_scroll);
        LinearLayout container = new LinearLayout(context);
        container.setOrientation(LinearLayout.VERTICAL);
        container.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        scrollView.addView(container);

        SectionView myLocation = new SectionView(context,AndroidUtils.getString(R.string.my_location));
        loaction = new EditeItemCell(context,AndroidUtils.getString(R.string.my_location));
        loaction.setOnClickListener(this);
        myLocation.addView(loaction);
        container.addView(myLocation);

        SectionView smName = new SectionView(context,AndroidUtils.getString(R.string.sm_name));
        name = new EditeItemCell(context,AndroidUtils.getString(R.string.my_work));
        name.setOnClickListener(this);
        smName.addView(name);
        container.addView(smName);

        SectionView smDesc = new SectionView(context,AndroidUtils.getString(R.string.sm_desc));
        desc = new EditeItemCell(context,AndroidUtils.getString(R.string.sm_desc));
        desc.setOnClickListener(this);
        smDesc.addView(desc);
        container.addView(smDesc);

        SectionView photoCon = new SectionView(context,AndroidUtils.getString(R.string.take_photo));
        LinearLayout pcon =new LinearLayout(context);
        pcon.setOrientation(LinearLayout.HORIZONTAL);
        pcon.setPadding(AndroidUtils.dp(15),0,AndroidUtils.dp(15),0);
        photoCon.addView(pcon,new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        photoCloud = new ViewCloud(context);
        photoCloud.postView(photos,onListener);
        pcon.addView(photoCloud,new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        container.addView(photoCon);

        SectionView voiceCon = new SectionView(context,AndroidUtils.getString(R.string.video_push));
        LinearLayout vcon =new LinearLayout(context);
        vcon.setOrientation(LinearLayout.HORIZONTAL);
        vcon.setPadding(AndroidUtils.dp(15),0,AndroidUtils.dp(15),0);
        voiceCon.addView(vcon,new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        voiceCloud = new ViewCloud(context);
        voiceCloud.postView(voices,onListener);
        vcon.addView(voiceCloud,new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        container.addView(voiceCon);

        SectionView videoCon = new SectionView(context,AndroidUtils.getString(R.string.voice_push));
        LinearLayout videoC =new LinearLayout(context);
        videoC.setOrientation(LinearLayout.HORIZONTAL);
        videoC.setPadding(AndroidUtils.dp(15),0,AndroidUtils.dp(15),0);
        videoCon.addView(videoC,new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        videoCloud = new ViewCloud(context);
        videoCloud.postView(videos,onListener);
        videoC.addView(videoCloud,new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        container.addView(videoCon);

        init();
        return scrollView;
    }

    public void init(){
        loaction.setTitleValue("Loaction");
        name.setTitleValue("name");
        desc.setTitleValue("desc");
    }


    private ViewCloud.OnListener onListener = new ViewCloud.OnListener() {
        @Override
        public void addView(View view) {
            if(view == photoCloud){
                if(!photos.isEmpty()){
                    photos.clear();
                }
                for(int i=0;i<15;i++){
                    photos.add(R.drawable.timg);
                }
                photoCloud.postView(photos,onListener);
            }else if(view == voiceCloud){
                if(!voices.isEmpty()){
                    voices.clear();
                }
                for(int i=0;i<15;i++){
                    voices.add(R.drawable.timg);
                }
                voiceCloud.postView(voices,onListener);
            }else if(view == videoCloud){
                if(!videos.isEmpty()){
                    videos.clear();
                }
                for(int i=0;i<15;i++){
                    videos.add(R.drawable.timg);
                }
                videoCloud.postView(videos,onListener);
            }
        }

        @Override
        public void close(int i,View view) {
            if(view == photoCloud){
                if(!photos.isEmpty()){
                    photos.remove(i);
                }
                photoCloud.postView(photos,onListener);
            }else if(view == voiceCloud){
                if(!voices.isEmpty()){
                    voices.remove(i);
                }
                voiceCloud.postView(voices,onListener);
            }else if(view == videoCloud){
                if(!videos.isEmpty()){
                    videos.remove(i);
                }
                voiceCloud.postView(videos,onListener);
            }
        }
    };


    @Override
    public void onClick(View view) {
        if(view == loaction){
            Toast.makeText(context,AndroidUtils.getString(R.string.my_location),Toast.LENGTH_SHORT).show();
        }else if(view == name){
            Toast.makeText(context,AndroidUtils.getString(R.string.sm_name),Toast.LENGTH_SHORT).show();
        }else if(view == desc){
            Toast.makeText(context,AndroidUtils.getString(R.string.sm_desc),Toast.LENGTH_SHORT).show();
        }else if(view == video){
            Toast.makeText(context,AndroidUtils.getString(R.string.video_push),Toast.LENGTH_SHORT).show();
        }else if(view == voice){
            Toast.makeText(context,AndroidUtils.getString(R.string.voice_push),Toast.LENGTH_SHORT).show();
        }
    }
}
