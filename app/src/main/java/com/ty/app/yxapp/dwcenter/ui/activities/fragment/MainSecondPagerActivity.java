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
import com.ty.app.yxapp.dwcenter.ui.widget.AddMoreCell;
import com.ty.app.yxapp.dwcenter.ui.widget.EditeItemCell;
import com.ty.app.yxapp.dwcenter.ui.widget.SectionView;

/**
 * Created by kss on 2017/3/26.
 */

public class MainSecondPagerActivity extends BaseFragment implements View.OnClickListener{
    private Context context;
    private EditeItemCell loaction;
    private EditeItemCell name;
    private EditeItemCell desc;
    private AddMoreCell video;
    private AddMoreCell photo;
    private AddMoreCell voice;

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

        SectionView myLocation = new SectionView(context,AndroidUtils.getString(R.string.my_work));
        loaction = new EditeItemCell(context,AndroidUtils.getString(R.string.my_work));
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
        photo = new AddMoreCell(context);
        photo.setOnClickListener(this);
        LinearLayout.LayoutParams photoll = new LinearLayout.LayoutParams(AndroidUtils.dp(70),AndroidUtils.dp(70));
        photoll.setMargins(AndroidUtils.dp(10),AndroidUtils.dp(10),AndroidUtils.dp(10),AndroidUtils.dp(10));
        photoCon.addView(photo,photoll);
        container.addView(photoCon);

        SectionView voiceCon = new SectionView(context,AndroidUtils.getString(R.string.voice_push));
        voice = new AddMoreCell(context);
        voice.setOnClickListener(this);
        LinearLayout.LayoutParams voicell = new LinearLayout.LayoutParams(AndroidUtils.dp(70),AndroidUtils.dp(70));
        voicell.setMargins(AndroidUtils.dp(10),AndroidUtils.dp(10),AndroidUtils.dp(10),AndroidUtils.dp(10));
        voiceCon.addView(voice,voicell);
        container.addView(voiceCon);

        SectionView videoCon = new SectionView(context,AndroidUtils.getString(R.string.voice_push));
        video = new AddMoreCell(context);
        video.setOnClickListener(this);
        LinearLayout.LayoutParams videoll = new LinearLayout.LayoutParams(AndroidUtils.dp(70),AndroidUtils.dp(70));
        videoll.setMargins(AndroidUtils.dp(10),AndroidUtils.dp(10),AndroidUtils.dp(10),AndroidUtils.dp(10));
        videoCon.addView(video,videoll);
        container.addView(videoCon);

        init();
        return scrollView;
    }

    public void init(){
        loaction.setTitleValue("Loaction");
        name.setTitleValue("name");
        desc.setTitleValue("desc");
    }


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
        }else if(view == photo){
            Toast.makeText(context,AndroidUtils.getString(R.string.take_photo),Toast.LENGTH_SHORT).show();
        }else if(view == voice){
            Toast.makeText(context,AndroidUtils.getString(R.string.voice_push),Toast.LENGTH_SHORT).show();
        }
    }
}
