package com.ty.app.yxapp.dwcenter.ui.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.ty.app.yxapp.dwcenter.R;
import com.ty.app.yxapp.dwcenter.utils.AndroidUtils;

/**
 * Created by kss on 2017/4/4.
 */
public abstract class BaseDialog extends Dialog {

    protected final FrameLayout container;
    protected Context mContext;


    public BaseDialog(Context context) {
        super(context, R.style.ProgressDialog);
        mContext = context;

        container = new FrameLayout(context);
        FrameLayout.LayoutParams fl = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.WRAP_CONTENT);
        fl.setMargins(AndroidUtils.dp(20),0,AndroidUtils.dp(20),0);
        fl.gravity = Gravity.CENTER;
        container.setLayoutParams(fl);
        container.setBackgroundResource(R.drawable.popup_fixed);

        FrameLayout.LayoutParams confl = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.WRAP_CONTENT);
        confl.gravity = Gravity.TOP;
        confl.setMargins(0,0,0,AndroidUtils.dp(8));
        container.addView(onCreateView(context), confl);

        setContentView(container);

        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        window.setAttributes(lp);
        setCanceledOnTouchOutside(true);

        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                onDismissed();
            }
        });
    }

    protected abstract View onCreateView(Context context);

    protected abstract void onDismissed();

}
