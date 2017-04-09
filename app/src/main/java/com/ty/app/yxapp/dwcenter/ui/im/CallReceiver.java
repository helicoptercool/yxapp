package com.ty.app.yxapp.dwcenter.ui.im;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.hyphenate.util.EasyUtils;

/**
 * Created by kss on 2016/12/12.
 */

public class CallReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (!EasyUtils.isAppRunningForeground(context)){
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            String from = intent.getStringExtra("from");
            String type = intent.getStringExtra("type");
            if ("video".equals(type)) { //视频通话
                intent.putExtra("userName",from);
                intent.putExtra("flag",VideoChatActivity.FLAG_IN);
                intent.setClass(context,VideoChatActivity.class);
                context.startActivity(intent);
            }
        }

    }
}
