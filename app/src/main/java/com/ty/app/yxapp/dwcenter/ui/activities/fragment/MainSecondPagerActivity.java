package com.ty.app.yxapp.dwcenter.ui.activities.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
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
import com.ty.app.yxapp.dwcenter.ui.activities.BasicMapActivity;
import com.ty.app.yxapp.dwcenter.ui.activities.EditeActivity;
import com.ty.app.yxapp.dwcenter.ui.widget.MenuDialog;
import com.ty.app.yxapp.dwcenter.ui.widget.ViewCloud;
import com.ty.app.yxapp.dwcenter.utils.AndroidUtils;
import com.ty.app.yxapp.dwcenter.ui.activities.base.BaseFragment;
import com.ty.app.yxapp.dwcenter.ui.widget.AddMoreCell;
import com.ty.app.yxapp.dwcenter.ui.widget.EditeItemCell;
import com.ty.app.yxapp.dwcenter.ui.widget.SectionView;
import com.ty.app.yxapp.dwcenter.utils.AudioRecoderUtils;
import com.ty.app.yxapp.dwcenter.utils.PopupWindowFactory;
import com.ty.app.yxapp.dwcenter.utils.TimeUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by kss on 2017/3/26.
 */

public class MainSecondPagerActivity extends BaseFragment implements View.OnClickListener {
    private static final String TAG = "MainSecondPagerActivity";
    private Context context;
    private EditeItemCell loaction;
    private EditeItemCell name;
    private EditeItemCell desc;
    private AddMoreCell video;
    private AddMoreCell voice;
    private ViewCloud viewCloud;
    private List<Bitmap> photos = new ArrayList<>();
    private List<Uri> videos = new ArrayList<>();
    private List<String> voices = new ArrayList<>();
    private ViewCloud photoCloud;
    private ViewCloud videoCloud;
    private ViewCloud voiceCloud;

    private static final int GET_PICTURE = 1;
    private static final int TAKE_PICTURE = 2;
    private static final int TAKE_VIDEO_RESULT = 3;
    private static final int VOICE_REQUEST_CODE = 66;
    private static final int TAKE_PHOTO = 67;
    private static final int SELECT_PHOTO = 68;
    private static final int TAKE_VIDEO = 69;


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
        actionBar.setRightView(AndroidUtils.getString(R.string.submit), 0, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"提交...",Toast.LENGTH_SHORT).show();
            }
        });
        actionBar.setCenterView(AndroidUtils.getString(R.string.push_work));

        ScrollView scrollView = new ScrollView(context);
        scrollView.setId(R.id.second_pager_scroll);
        container = new LinearLayout(context);
        container.setBackgroundColor(0xFFF5F5F5);
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
        photoCloud.postView(photos);
        photoCloud.setOnListener(onListener);
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
        voiceCloud.postView(voices);
        voiceCloud.setOnListener(onListener);
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
        videoCloud.postView(videos);
        videoCloud.setOnListener(onListener);
        videoC.addView(videoCloud, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        container.addView(videoCon);

        View view = new View(context);
        container.addView(view,new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                AndroidUtils.dp(30)));

        init();
        return scrollView;
    }

    public void init() {
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
                voices.add(filePath);
                voiceCloud.postView(voices);
                Toast.makeText(context, "录音保存在：" + filePath, Toast.LENGTH_SHORT).show();
                mTextView.setText(TimeUtils.long2String(0));
            }
        });
    }

    private MenuDialog dialog;
    private ViewCloud.OnListener onListener = new ViewCloud.OnListener() {

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (view == photoCloud) {

                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_UP:
                        if (dialog == null) {
                            dialog = new MenuDialog(getContext());
                            dialog.addSubItem(2, AndroidUtils.getString(R.string.take_pic), 0);
                            dialog.addSubItem(1, AndroidUtils.getString(R.string.select_pic), 0);
                        }

                        dialog.setOnItemClickListener(new MenuDialog.OnItemClickListener() {
                            @Override
                            public void onItemClick(int id) {
                                takePicture(id);
                            }
                        });
                        dialog.show();
                        return true;
                }
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
                        voiceCloud.postView(voices);
                        voiceCloud.setAddMoreText(AndroidUtils.getString(R.string.start_recoder));
                        return true;
                    case MotionEvent.ACTION_OUTSIDE:
                        voiceCloud.setAddMoreText(AndroidUtils.getString(R.string.save_recoder));
                        return true;
                }
            } else if (view == videoCloud) {
                switch (motionEvent.getAction()) {

                    case MotionEvent.ACTION_UP:
                        if ((ContextCompat.checkSelfPermission(context,
                                Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) &&
                                (ContextCompat.checkSelfPermission(context,
                                        Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)) {

                            Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                            try {
                                Uri fileUri = Uri.fromFile(createMediaFile());
                                intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                                intent.addCategory("android.intent.category.DEFAULT");
                                startActivityForResult(intent, TAKE_VIDEO);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            //请求获取摄像头权限
                            ActivityCompat.requestPermissions((Activity) context,
                                    new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE}, TAKE_VIDEO);
                        }
                        return true;
                }
            }
            return true;
        }

        @Override
        public void close(int i, View view) {
            if (view == photoCloud) {
                if (!photos.isEmpty()) {
                    photos.remove(i);
                }
                photoCloud.postView(photos);
            } else if (view == voiceCloud) {
                if (!voices.isEmpty()) {
                    if (!TextUtils.isEmpty(voices.get(i))) {
                        File file = new File(voices.get(i));
                        if (file.exists()) {
                            file.delete();
                        }
                    }
                    voices.remove(i);
                }
                voiceCloud.setAddMoreText(AndroidUtils.getString(R.string.start_recoder));
                voiceCloud.postView(voices);
            } else if (view == videoCloud) {
                if (!videos.isEmpty()) {
                    if (videos.get(i) != null) {
                        String filePath = AndroidUtils.getPath(context, videos.get(i));
                        File file = new File(filePath);
                        if (file != null && file.exists()) {
                            file.delete();
                        }
                    }
                    videos.remove(i);
                }
                videoCloud.postView(videos);
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
                Toast.makeText(context, AndroidUtils.getString(R.string.per_tip), Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == SELECT_PHOTO) {
            if ((grantResults[0] == PackageManager.PERMISSION_GRANTED) && (grantResults[1] == PackageManager.PERMISSION_GRANTED)) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, GET_PICTURE);
            } else {
                Toast.makeText(context, AndroidUtils.getString(R.string.per_tip), Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == TAKE_PHOTO) {
            if ((grantResults[0] == PackageManager.PERMISSION_GRANTED) && (grantResults[1] == PackageManager.PERMISSION_GRANTED)) {
                Intent intent = new Intent();
                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, TAKE_PICTURE);
            } else {
                Toast.makeText(context, AndroidUtils.getString(R.string.per_tip), Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == TAKE_VIDEO_RESULT) {
            if ((grantResults[0] == PackageManager.PERMISSION_GRANTED) && (grantResults[1] == PackageManager.PERMISSION_GRANTED)) {
                Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                try {
                    Uri fileUri = Uri.fromFile(createMediaFile());
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                    intent.addCategory("android.intent.category.DEFAULT");
                    startActivityForResult(intent, TAKE_VIDEO);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(context, AndroidUtils.getString(R.string.per_tip), Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    public void onClick(View view) {

        if (view == loaction) {
            //TODO:
            Intent intent = new Intent(context, BasicMapActivity.class);
            startActivity(intent);
        } else if (view == name) {
            Intent intent = new Intent(context, EditeActivity.class);
            intent.putExtra("title",AndroidUtils.getString(R.string.sm_name));
            intent.putExtra("value",name.getTitle());
            startActivityForResult(intent,101);
        } else if (view == desc) {
            Intent intent = new Intent(context, EditeActivity.class);
            intent.putExtra("title",AndroidUtils.getString(R.string.sm_desc));
            intent.putExtra("value",desc.getTitle());
            startActivityForResult(intent,102);
        }
    }

    private void takePicture(int id) {
        Intent intent = new Intent();
        if ((ContextCompat.checkSelfPermission(context,
                Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED)) {

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
        } else {
            //请求获取摄像头权限
            if (id == 1) {
                ActivityCompat.requestPermissions((Activity) context,
                        new String[]{Manifest.permission.CAMERA}, SELECT_PHOTO);
            } else if (id == 2) {
                ActivityCompat.requestPermissions((Activity) context,
                        new String[]{Manifest.permission.CAMERA}, TAKE_PHOTO);
            }
        }
    }

    private File createMediaFile() throws IOException {
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_MOVIES), "CameraDemo");
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(TAG, "failed to create directory");
                return null;
            }
        }
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "VID_" + timeStamp;
        String suffix = ".mp4";
        File mediaFile = new File(mediaStorageDir + File.separator + imageFileName + suffix);
        return mediaFile;
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
                            photos.add(bitmap);
                            photoCloud.postView(photos);
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case TAKE_PICTURE:
                if (data != null) {
                    Bundle bundle = data.getExtras();
                    Bitmap bitmap = (Bitmap) bundle.get("data");
                    photos.add(bitmap);
                    photoCloud.postView(photos);
                }
                break;
            case TAKE_VIDEO:
                if (data != null) {
                    Bundle b = data.getExtras();
                    if (b != null) {
                        Uri uri = (Uri) b.get("data");
                        videos.add(uri);
                        videoCloud.postView(videos);
                    }
                }
                break;
            case 101:
                String work = data.getStringExtra("reValue");
                name.setTitle(work);
                break;
            case 102:
                String desc = data.getStringExtra("reValue");
                this.desc.setTitle(desc);
                break;
        }
    }

}
