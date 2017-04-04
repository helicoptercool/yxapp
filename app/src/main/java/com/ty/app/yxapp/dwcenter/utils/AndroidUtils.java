package com.ty.app.yxapp.dwcenter.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.widget.Toast;

import com.ty.app.yxapp.dwcenter.ui.activities.base.MyApplication;

/**
 * Created by kss on 2017/3/26.
 */

public class AndroidUtils {

    private static float density = 1;

    static {
        density = MyApplication.context.getResources().getDisplayMetrics().density;
    }

    public static int dp(float value) {
        if (value == 0) {
            return 0;
        }
        return (int) Math.ceil(density * value);
    }

    public static String getString(int res){
        return MyApplication.context.getString(res);
    }

    public static void ShowToast(String message){
        Toast.makeText(MyApplication.context,message,Toast.LENGTH_SHORT).show();
    }



    public static Drawable createListSelectorDrawable(Context context) {
        if (Build.VERSION.SDK_INT >= 21) {
            int[] attrs = new int[]{android.R.attr.selectableItemBackground};
            TypedArray ta = context.obtainStyledAttributes(attrs);
            Drawable drawableFromTheme = ta.getDrawable(0);
            ta.recycle();
            return drawableFromTheme;
        } else {
            StateListDrawable stateListDrawable = new StateListDrawable();
            stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, new ColorDrawable(0x0f000000));
            stateListDrawable.addState(new int[]{android.R.attr.state_focused}, new ColorDrawable(0x0f000000));
            stateListDrawable.addState(new int[]{android.R.attr.state_selected}, new ColorDrawable(0x0f000000));
            stateListDrawable.addState(new int[]{}, new ColorDrawable(0x00000000));
            return stateListDrawable;
        }
    }

}
