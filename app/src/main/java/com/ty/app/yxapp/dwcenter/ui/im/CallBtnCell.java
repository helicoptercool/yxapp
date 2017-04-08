package com.ty.app.yxapp.dwcenter.ui.im;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import com.ty.app.yxapp.dwcenter.R;
import com.ty.app.yxapp.dwcenter.ui.activities.base.LayoutHelper;
import com.ty.app.yxapp.dwcenter.utils.AndroidUtils;

/**
 * Created by kss on 2016/12/13.
 */

public class CallBtnCell extends LinearLayout {
    private Context context;
    public static final int flag_send = 1;
    public static final int flag_accept = 2;
    public static final int flag_accepted = 3;

    public interface OnListener{
        void leftClick(int flag);
        void centerClick(int flag);
        void rightClick(int flag);
    }


    public CallBtnCell(Context context,int flag,OnListener onClick) {
        super(context);
        this.context = context;
        setOrientation(HORIZONTAL);
        setBackgroundColor(Color.TRANSPARENT);

        if(flag == flag_send){
            addSendView(onClick,flag);
        }else if(flag == flag_accept){
            addAcceptViews(onClick,flag);
        }else if(flag == flag_accepted){
            addAcceptEdViews(onClick,flag);
        }
    }


    private void addSendView(final OnListener onClick,final int flag){
        if(this.getChildCount() > 0){
            this.removeAllViews();
        }
        CallCell cancle = new CallCell(context);
        cancle.setImg(R.mipmap.ic_default_avatar);
        cancle.setText(AndroidUtils.getString(R.string.cancel));
        cancle.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onClick != null) onClick.centerClick(flag);
            }
        });
        addView(cancle, LayoutHelper.createLinear(LayoutHelper.WRAP_CONTENT,LayoutHelper.WRAP_CONTENT, Gravity.CENTER));
    }


    private void addAcceptViews(final OnListener onClick,final int flag){
        if(this.getChildCount() > 0){
            this.removeAllViews();
        }
        LinearLayout cancleCon = new LinearLayout(context);
        addView(cancleCon,LayoutHelper.createLinear(0,LayoutHelper.WRAP_CONTENT,1));

        CallCell cancle = new CallCell(context);
        cancle.setImg(R.mipmap.ic_default_avatar);
        cancle.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onClick != null) onClick.leftClick(flag);
            }
        });
        cancle.setText(AndroidUtils.getString(R.string.cancel));
        cancleCon.addView(cancle, LayoutHelper.createLinear(LayoutHelper.WRAP_CONTENT,LayoutHelper.WRAP_CONTENT, Gravity.CENTER));

        LinearLayout acceptCon = new LinearLayout(context);
        addView(acceptCon,LayoutHelper.createLinear(0,LayoutHelper.WRAP_CONTENT,1));

        CallCell accept = new CallCell(context);
        accept.setImg(R.mipmap.ic_default_avatar);
        accept.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onClick != null) onClick.rightClick(flag);
            }
        });
        accept.setText(AndroidUtils.getString(R.string.accept));
        acceptCon.addView(accept,LayoutHelper.createLinear(LayoutHelper.WRAP_CONTENT,LayoutHelper.WRAP_CONTENT, Gravity.CENTER));
    }

    private void addAcceptEdViews(final OnListener onClick,final int flag){
        if(this.getChildCount() > 0){
            this.removeAllViews();
        }

        LinearLayout leftCon = new LinearLayout(context);
        addView(leftCon,LayoutHelper.createLinear(0,LayoutHelper.WRAP_CONTENT,1));

        CallCell left = new CallCell(context);
        left.setImg(R.mipmap.ic_default_avatar);
        left.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onClick != null) onClick.leftClick(flag);
            }
        });
        left.setText(AndroidUtils.getString(R.string.narrow));
        leftCon.addView(left, LayoutHelper.createLinear(LayoutHelper.WRAP_CONTENT,LayoutHelper.WRAP_CONTENT, Gravity.CENTER));

        LinearLayout centerCon = new LinearLayout(context);
        addView(centerCon,LayoutHelper.createLinear(0,LayoutHelper.WRAP_CONTENT,1));

        CallCell center = new CallCell(context);
        center.setImg(R.mipmap.ic_default_avatar);
        center.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onClick != null) onClick.centerClick(flag);
            }
        });
        center.setText(AndroidUtils.getString(R.string.cancel));
        centerCon.addView(center, LayoutHelper.createLinear(LayoutHelper.WRAP_CONTENT,LayoutHelper.WRAP_CONTENT, Gravity.CENTER));

        LinearLayout rightCon = new LinearLayout(context);
        addView(rightCon,LayoutHelper.createLinear(0,LayoutHelper.WRAP_CONTENT,1));

        CallCell right = new CallCell(context);
        right.setImg(R.mipmap.ic_default_avatar);
        right.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onClick != null) onClick.rightClick(flag);
            }
        });
        right.setText(AndroidUtils.getString(R.string.swit));
        centerCon.addView(right, LayoutHelper.createLinear(LayoutHelper.WRAP_CONTENT,LayoutHelper.WRAP_CONTENT, Gravity.CENTER));
    }


}
