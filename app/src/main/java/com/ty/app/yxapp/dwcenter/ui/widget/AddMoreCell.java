package com.ty.app.yxapp.dwcenter.ui.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ty.app.yxapp.dwcenter.R;

import java.io.File;

/**
 * Created by kss on 2017/3/27.
 */
public class AddMoreCell extends SquareRelativeLayout {

    private final TextView titleView;
    private ImageView selfView;

    public AddMoreCell(Context context) {
        super(context);

        setBackgroundResource(R.drawable.bg_white_gray_border);

        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setGravity(Gravity.CENTER);
        RelativeLayout.LayoutParams rl = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        rl.addRule(RelativeLayout.CENTER_IN_PARENT);
        addView(linearLayout,rl);

        selfView = new ImageView(context);
        selfView.setImageResource(R.mipmap.ic_note_photo);
        linearLayout.addView(selfView, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));

        titleView = new TextView(context);
        titleView.setVisibility(GONE);
        titleView.setTextSize(12);
        titleView.setTextColor(0xFFB8B3B4);
        linearLayout.addView(titleView,new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));

    }

    public void setText(String str) {
        titleView.setVisibility(VISIBLE);
        titleView.setText(str);
    }

    public void setImg(int res){
        LinearLayout.LayoutParams ll = (LinearLayout.LayoutParams) selfView.getLayoutParams();
        ll.weight = LinearLayout.LayoutParams.MATCH_PARENT;
        ll.height = LinearLayout.LayoutParams.MATCH_PARENT;
        selfView.setLayoutParams(ll);
        selfView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        selfView.setImageResource(res);
    }

    public void setImg(Bitmap res){
        LinearLayout.LayoutParams ll = (LinearLayout.LayoutParams) selfView.getLayoutParams();
        ll.weight = LinearLayout.LayoutParams.MATCH_PARENT;
        ll.height = LinearLayout.LayoutParams.MATCH_PARENT;
        selfView.setLayoutParams(ll);
        selfView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        selfView.setImageBitmap(res);
    }

    public void setImageView(ImageView imageView){
        selfView = imageView;
    }

    public void setImg(Uri file){
        LinearLayout.LayoutParams ll = (LinearLayout.LayoutParams) selfView.getLayoutParams();
        ll.weight = LinearLayout.LayoutParams.MATCH_PARENT;
        ll.height = LinearLayout.LayoutParams.MATCH_PARENT;
        selfView.setLayoutParams(ll);
        selfView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        selfView.setImageURI(file);
    }

}
