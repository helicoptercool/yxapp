package com.ty.app.yxapp.dwcenter.ui.Widget;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by kss on 2017/3/26.
 */

public class ImageButtonCell extends RelativeLayout {
    private Context context;

    public ImageButtonCell(Context context,int res,String name) {
        super(context);
        this.context = context;

        setBackgroundResource(res);

        TextView nameView = new TextView(context);
        nameView.setText(name);
        nameView.setTextSize(16);
        RelativeLayout.LayoutParams rl = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        rl.addRule(RelativeLayout.CENTER_IN_PARENT);
        addView(nameView,rl);
    }
}
