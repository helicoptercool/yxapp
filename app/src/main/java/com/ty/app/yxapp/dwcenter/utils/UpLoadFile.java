package com.ty.app.yxapp.dwcenter.utils;

import android.text.BidiFormatter;
import android.text.TextUtils;
import android.util.Log;

import com.ty.app.yxapp.dwcenter.network.Result;
import com.ty.app.yxapp.dwcenter.network.RetrofitHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by urp on 2017/4/16.
 */

public class UpLoadFile {
    private static UpLoadFile updateFile;
    private List<String> videoList = new ArrayList<>();
    private List<String> voiceList = new ArrayList<>();
    private List<String> imgList = new ArrayList<>();

    private List<String> videoSuccessList = new ArrayList<>();
    private List<String> voiceSuccessList = new ArrayList<>();
    private List<String> imgSuccessList = new ArrayList<>();

    private List<String> videoErrorList = new ArrayList<>();
    private List<String> voiceErrorList = new ArrayList<>();
    private List<String> imgErrorList = new ArrayList<>();


    public static UpLoadFile getInstance() {
        if (updateFile == null) {
            synchronized (RetrofitHelper.class) {
                if (updateFile == null) {
                    updateFile = new UpLoadFile();
                }
            }
        }
        return updateFile;
    }


    public void upLoadVideo(final List<String> videos, final OnListener onListener){
        if(videos == null || videos.size() == 0){
            if(onListener != null) onListener.onSuccess(Collections.<String>emptyList());
            return;
        }
        videoList.clear();
        videoList = videos;
        if(!videoList.isEmpty()){
            Iterator<String> iterator = videoList.iterator();
            while (iterator.hasNext()){
                String path = iterator.next();
                File videoF =  new File(path);
                if(!TextUtils.isEmpty(path) && videoF.exists()){
                    RetrofitHelper.getInstance().uploadVideo(videoF, videoF.getPath(), new RetrofitHelper.OnResultListener() {
                        @Override
                        public void onResult(Result result) {
                            if(result.isOK()){
                                videoSuccessList.add((String) result.getData());
                            }else{
                                videoErrorList.add(String.valueOf(result.getCode())+",");
                            }

                            if(videoList.size() == videoSuccessList.size() + videoErrorList.size()){
                                Log.d("MainSecondPagerActivity","视频上传成功.... ,"+videoSuccessList.toString());
                                if(onListener != null) onListener.onSuccess(videoSuccessList);
                            }

                        }
                    });
                }
            }
        }
    }


    public void upLoadVoice(List<String> voices, final OnListener onListener){
        if(voices == null || voices.size() == 0){
            if(onListener != null) onListener.onSuccess(Collections.<String>emptyList());
            return;
        }
        voiceList.clear();
        voiceList = voices;
        if(!voiceList.isEmpty()){
            Iterator<String> iterator = voiceList.iterator();
            while (iterator.hasNext()){
                String path = iterator.next();
                File videoF =  new File(path);
                if(!TextUtils.isEmpty(path) && videoF.exists()){
                    RetrofitHelper.getInstance().uploadVideo(videoF, videoF.getPath(), new RetrofitHelper.OnResultListener() {
                        @Override
                        public void onResult(Result result) {
                            if(result.isOK()){
                                voiceSuccessList.add((String) result.getData());
                            }else{
                                voiceErrorList.add(String.valueOf(result.getCode())+",");
                            }

                            if(voiceList.size() == voiceSuccessList.size() + voiceErrorList.size()){
                                Log.d("MainSecondPagerActivity","语音上传成功.... ,"+voiceSuccessList.toString());
                                if(onListener != null) onListener.onSuccess(voiceSuccessList);
                            }
                        }
                    });
                }
            }
        }
    }

    public void upLoadImg(List<String> imgs, final OnListener onListener){
        if(imgs == null || imgs.size() == 0){
            if(onListener != null) onListener.onSuccess(Collections.<String>emptyList());
            return;
        }
        imgList.clear();
        imgList = imgs;
        if(!imgList.isEmpty()){
            Iterator<String> iterator = imgList.iterator();
            while (iterator.hasNext()){
                String path = iterator.next();
                File videoF =  new File(path);
                if(!TextUtils.isEmpty(path) && videoF.exists()){
                    RetrofitHelper.getInstance().uploadVideo(videoF, videoF.getPath(), new RetrofitHelper.OnResultListener() {
                        @Override
                        public void onResult(Result result) {
                            if(result.isOK()){
                                imgSuccessList.add((String) result.getData());
                            }else{
                                imgErrorList.add(String.valueOf(result.getCode())+",");
                            }
                            if(imgList.size() == imgSuccessList.size() + imgErrorList.size()){
                                Log.d("MainSecondPagerActivity","图片上传成功.... ,"+imgSuccessList.toString());
                                if(onListener != null) onListener.onSuccess(imgSuccessList);
                            }
                        }
                    });
                }
            }
        }
    }

    public interface OnListener{
       void onSuccess(List<String> list);
    }

}
