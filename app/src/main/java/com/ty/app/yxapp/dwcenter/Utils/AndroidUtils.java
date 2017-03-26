package com.ty.app.yxapp.dwcenter.Utils;

import com.ty.app.yxapp.dwcenter.ui.Activity.base.MyApplication;

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
}
