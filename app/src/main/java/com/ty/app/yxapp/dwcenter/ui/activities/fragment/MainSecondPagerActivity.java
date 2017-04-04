package com.ty.app.yxapp.dwcenter.ui.activities.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.ty.app.yxapp.dwcenter.R;
import com.ty.app.yxapp.dwcenter.ui.widget.ViewCloud;
import com.ty.app.yxapp.dwcenter.utils.AndroidUtils;
import com.ty.app.yxapp.dwcenter.ui.activities.base.BaseFragment;
import com.ty.app.yxapp.dwcenter.ui.widget.AddMoreCell;
import com.ty.app.yxapp.dwcenter.ui.widget.EditeItemCell;
import com.ty.app.yxapp.dwcenter.ui.widget.SectionView;
import com.ty.app.yxapp.dwcenter.utils.AudioRecoderUtils;
import com.ty.app.yxapp.dwcenter.utils.PopupWindowFactory;
import com.ty.app.yxapp.dwcenter.utils.TimeUtils;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by kss on 2017/3/26.
 */

public class MainSecondPagerActivity extends BaseFragment implements View.OnClickListener {
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

    private static final int GET_PICTURE = 1;
    private static final int TAKE_PICTURE = 2;
    private static final int VOICE_REQUEST_CODE = 66;


    //    private Button mButton;
    private ImageView mImageView;
    private TextView mTextView;
    private AudioRecoderUtils mAudioRecoderUtils;
    private PopupWindowFactory mPop;
    //    private RelativeLayout rl;
    LinearLayout container;


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
        container = new LinearLayout(context);
        container.setOrientation(LinearLayout.VERTICAL);
        container.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        scrollView.addView(container);

        SectionView myLocation = new SectionView(context, AndroidUtils.getString(R.string.my_location));
        loaction = new EditeItemCell(context, AndroidUtils.getString(R.string.my_location));
        loaction.setOnClickListener(this);
        myLocation.addView(loaction);
        container.addView(myLocation);

        SectionView smName = new SectionView(context, AndroidUtils.getString(R.string.sm_name));
        name = new EditeItemCell(context, AndroidUtils.getString(R.string.my_work));
        name.setOnClickListener(this);
        smName.addView(name);
        container.addView(smName);

        SectionView smDesc = new SectionView(context, AndroidUtils.getString(R.string.sm_desc));
        desc = new EditeItemCell(context, AndroidUtils.getString(R.string.sm_desc));
        desc.setOnClickListener(this);
        smDesc.addView(desc);
        container.addView(smDesc);

        SectionView photoCon = new SectionView(context, AndroidUtils.getString(R.string.take_photo));
        LinearLayout pcon = new LinearLayout(context);
        pcon.setOrientation(LinearLayout.HORIZONTAL);
        pcon.setPadding(AndroidUtils.dp(15), 0, AndroidUtils.dp(15), 0);
        photoCon.addView(pcon, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        photoCloud = new ViewCloud(context);
        photoCloud.postView(photos, onListener);
        pcon.addView(photoCloud, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        container.addView(photoCon);

        SectionView voiceCon = new SectionView(context, AndroidUtils.getString(R.string.voice_push));
        LinearLayout vcon = new LinearLayout(context);
        vcon.setOrientation(LinearLayout.HORIZONTAL);
        vcon.setPadding(AndroidUtils.dp(15), 0, AndroidUtils.dp(15), 0);
        voiceCon.addView(vcon, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        voiceCloud = new ViewCloud(context);
        voiceCloud.postView(voices, onListener);
        voiceCloud.setAddMoreText(AndroidUtils.getString(R.string.start_recoder));
        vcon.addView(voiceCloud, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        container.addView(voiceCon);

        SectionView videoCon = new SectionView(context, AndroidUtils.getString(R.string.video_push));
        LinearLayout videoC = new LinearLayout(context);
        videoC.setOrientation(LinearLayout.HORIZONTAL);
        videoC.setPadding(AndroidUtils.dp(15), 0, AndroidUtils.dp(15), 0);
        videoCon.addView(videoC, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        videoCloud = new ViewCloud(context);
        videoCloud.postView(videos, onListener);
        videoC.addView(videoCloud, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        container.addView(videoCon);

        init();
        return scrollView;
    }

    public void init() {
        loaction.setTitleValue("Loaction");
        name.setTitleValue("name");
        desc.setTitleValue("desc");


        final View view = View.inflate(context, R.layout.layout_microphone, null);
        mPop = new PopupWindowFactory(context, view);

        //PopupWindow布局文件里面的控件
        mImageView = (ImageView) view.findViewById(R.id.iv_recording_icon);
        mTextView = (TextView) view.findViewById(R.id.tv_recording_time);

        mAudioRecoderUtils = new AudioRecoderUtils();

        //录音回调
        mAudioRecoderUtils.setOnAudioStatusUpdateListener(new AudioRecoderUtils.OnAudioStatusUpdateListener() {

            //录音中....db为声音分贝，time为录音时长
            @Override
            public void onUpdate(double db, long time) {
                mImageView.getDrawable().setLevel((int) (3000 + 6000 * db / 100));
                mTextView.setText(TimeUtils.long2String(time));
            }

            //录音结束，filePath为保存路径
            @Override
            public void onStop(String filePath) {
                //TODO:获取保存的录音文件
                Toast.makeText(context, "录音保存在：" + filePath, Toast.LENGTH_SHORT).show();
                mTextView.setText(TimeUtils.long2String(0));
            }
        });
    }

    private ViewCloud.OnListener onListener = new ViewCloud.OnListener() {

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (view == photoCloud) {
                if (!photos.isEmpty()) {
                    photos.clear();
                }
                for (int i = 0; i < 15; i++) {
                    photos.add(R.drawable.timg);
                }
                photoCloud.postView(photos, onListener);
            } else if (view == voiceCloud) {
                switch (motionEvent.getAction()) {

                    case MotionEvent.ACTION_DOWN:
                        //6.0以上需要权限申请
                        requestPermissions();
                        break;
                    case MotionEvent.ACTION_UP:
                        mAudioRecoderUtils.stopRecord();        //结束录音（保存录音文件）
//                        mAudioRecoderUtils.cancelRecord();    //取消录音（不保存录音文件）
                        mPop.dismiss();
                        voices.add(R.drawable.timg);
                        voiceCloud.postView(voices, onListener);
                        voiceCloud.setAddMoreText(AndroidUtils.getString(R.string.start_recoder));
                        return true;
                    case MotionEvent.ACTION_OUTSIDE:
                        voiceCloud.setAddMoreText(AndroidUtils.getString(R.string.save_recoder));
                        return true;
                }
            } else if (view == videoCloud) {
                if (!videos.isEmpty()) {
                    videos.clear();
                }
                for (int i = 0; i < 15; i++) {
                    videos.add(R.drawable.timg);
                }
                videoCloud.postView(videos, onListener);
            }
            return true;
        }

        @Override
        public void close(int i, View view) {
            if (view == photoCloud) {
                if (!photos.isEmpty()) {
                    photos.remove(i);
                }
                photoCloud.postView(photos, onListener);
            } else if (view == voiceCloud) {
                if (!voices.isEmpty()) {
                    voices.remove(i);
                }
                voiceCloud.postView(voices, onListener);
            } else if (view == videoCloud) {
                if (!videos.isEmpty()) {
                    videos.remove(i);
                }
                voiceCloud.setAddMoreText(AndroidUtils.getString(R.string.start_recoder));
                voiceCloud.postView(videos, onListener);
            }
        }
    };


    private void requestPermissions() {
        //判断是否开启摄像头权限
        if ((ContextCompat.checkSelfPermission(context,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) &&
                (ContextCompat.checkSelfPermission(context,
                        Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED)) {

            mPop.showAtLocation(container, Gravity.CENTER, 0, 0);
            mAudioRecoderUtils.startRecord();

        } else {
            //请求获取摄像头权限
            ActivityCompat.requestPermissions((Activity) context,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO}, VOICE_REQUEST_CODE);
        }

    }


    /**
     * 请求权限回调
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == VOICE_REQUEST_CODE) {
            if ((grantResults[0] == PackageManager.PERMISSION_GRANTED) && (grantResults[1] == PackageManager.PERMISSION_GRANTED)) {

                mPop.showAtLocation(container, Gravity.CENTER, 0, 0);
                mAudioRecoderUtils.startRecord();

            } else {
                Toast.makeText(context, "已拒绝权限！", Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    public void onClick(View view) {
        if (view == loaction) {
            Toast.makeText(context, AndroidUtils.getString(R.string.my_location), Toast.LENGTH_SHORT).show();
        } else if (view == name) {
            Toast.makeText(context, AndroidUtils.getString(R.string.sm_name), Toast.LENGTH_SHORT).show();
        } else if (view == desc) {
            Toast.makeText(context, AndroidUtils.getString(R.string.sm_desc), Toast.LENGTH_SHORT).show();
        } else if (view == video) {
            Toast.makeText(context, AndroidUtils.getString(R.string.video_push), Toast.LENGTH_SHORT).show();
        } else if (view == voice) {
            Toast.makeText(context, AndroidUtils.getString(R.string.voice_push), Toast.LENGTH_SHORT).show();
            if (view == loaction) {
                Toast.makeText(context, AndroidUtils.getString(R.string.my_location), Toast.LENGTH_SHORT).show();
            } else if (view == name) {
                Toast.makeText(context, AndroidUtils.getString(R.string.sm_name), Toast.LENGTH_SHORT).show();
            } else if (view == desc) {
                Toast.makeText(context, AndroidUtils.getString(R.string.sm_desc), Toast.LENGTH_SHORT).show();
            } else if (view == video) {
                Toast.makeText(context, AndroidUtils.getString(R.string.video_push), Toast.LENGTH_SHORT).show();
            }
//            else if (view == photo) {
//                takePicture(view.getId());
//                Toast.makeText(context, AndroidUtils.getString(R.string.take_photo), Toast.LENGTH_SHORT).show();
//            }
            else if (view == voice) {
                Toast.makeText(context, AndroidUtils.getString(R.string.voice_push), Toast.LENGTH_SHORT).show();

            }
        }
    }

    private void takePicture(int id) {
        Intent intent = new Intent();
        switch (id) {
            case GET_PICTURE:
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, GET_PICTURE);
                break;
            case TAKE_PICTURE:
                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, TAKE_PICTURE);
                break;
            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case GET_PICTURE:
                if (data != null) {
                    Uri uri = data.getData();
                    try {
                        InputStream inputStream = getContext().getContentResolver().openInputStream(uri);
                        if (inputStream != null) {
                            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                            inputStream.close();
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case TAKE_PICTURE:

                break;
            default:
                break;
        }
    }

    //录音
    private void record() {

    }
}
