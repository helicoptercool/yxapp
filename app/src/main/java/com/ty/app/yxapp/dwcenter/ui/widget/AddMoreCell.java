package com.ty.app.yxapp.dwcenter.ui.widget;

import android.content.Context;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ty.app.yxapp.dwcenter.R;

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
        selfView.setImageResource(res);
    }

}
