package com.ty.app.yxapp.dwcenter.ui.activities.base;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.ty.app.yxapp.dwcenter.utils.AndroidUtils;

public class LayoutHelper {

    public static final int MATCH_PARENT = -1;
    public static final int WRAP_CONTENT = -2;

    private static int getSize(float size) {
        return (int) (size < 0 ? size : AndroidUtils.dp(size));
    }

    public static void setBounds(TextView view, int left, int top, int right, int bottom) {
        view.setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom);
    }

    public static void setBounds(TextView view, Drawable left, Drawable top, Drawable right, Drawable bottom) {
        view.setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom);
    }

    public static void setPadding(TextView view, int left, int top, int right, int bottom) {
        view.setPadding(left, top, right, bottom);
    }

    public static void setPadding(View view, int left, int top, int right, int bottom) {
        view.setPadding(left, top, right, bottom);
    }

    public static void setMargins(FrameLayout.LayoutParams layoutParams, int left, int top, int right, int bottom) {
        layoutParams.setMargins(left, top, right, bottom);
    }

    public static void setMargins(LinearLayout.LayoutParams layoutParams, int left, int top, int right, int bottom) {
        layoutParams.setMargins(left, top, right, bottom);
    }

    public static void setMargins(RelativeLayout.LayoutParams layoutParams, int left, int top, int right, int bottom) {
        layoutParams.setMargins(left, top, right, bottom);

    }

    public static FrameLayout.LayoutParams createScroll(int width, int height, int gravity) {
        return new ScrollView.LayoutParams(getSize(width), getSize(height), gravity);
    }

    public static FrameLayout.LayoutParams createFrame(int width, float height, int gravity, float leftMargin, float topMargin, float rightMargin, float bottomMargin) {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(getSize(width), getSize(height), gravity);
        layoutParams.setMargins(AndroidUtils.dp(leftMargin), AndroidUtils.dp(topMargin), AndroidUtils.dp(rightMargin), AndroidUtils.dp(bottomMargin));

        return layoutParams;
    }

    public static FrameLayout.LayoutParams createFrame(int width, int height, int gravity) {
        return new FrameLayout.LayoutParams(getSize(width), getSize(height), gravity);
    }

    public static FrameLayout.LayoutParams createFrame(int width, float height) {
        return new FrameLayout.LayoutParams(getSize(width), getSize(height));
    }

    public static RelativeLayout.LayoutParams createRelative(float width, float height, int leftMargin, int topMargin, int rightMargin, int bottomMargin, int alignParent, int alignRelative, int anchorRelative) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(getSize(width), getSize(height));
        if (alignParent >= 0) {
            if (alignParent == RelativeLayout.ALIGN_PARENT_LEFT) {
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            } else if (alignParent == RelativeLayout.ALIGN_PARENT_RIGHT) {
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            } else {
                layoutParams.addRule(alignParent);
            }
        }
        if (alignRelative >= 0 && anchorRelative >= 0) {
            layoutParams.addRule(alignRelative, anchorRelative);
        }

        layoutParams.topMargin = AndroidUtils.dp(topMargin);
        layoutParams.bottomMargin = AndroidUtils.dp(bottomMargin);
        layoutParams.leftMargin = AndroidUtils.dp(leftMargin);
        layoutParams.rightMargin = AndroidUtils.dp(rightMargin);
        return layoutParams;
    }

    public static RelativeLayout.LayoutParams createRelative(int width, int height, int leftMargin, int topMargin, int rightMargin, int bottomMargin) {
        return createRelative(width, height, leftMargin, topMargin, rightMargin, bottomMargin, -1, -1, -1);
    }

    public static RelativeLayout.LayoutParams createRelative(int width, int height, int leftMargin, int topMargin, int rightMargin, int bottomMargin, int alignParent) {
        return createRelative(width, height, leftMargin, topMargin, rightMargin, bottomMargin, alignParent, -1, -1);
    }

    public static RelativeLayout.LayoutParams createRelative(float width, float height, int leftMargin, int topMargin, int rightMargin, int bottomMargin, int alignRelative, int anchorRelative) {
        return createRelative(width, height, leftMargin, topMargin, rightMargin, bottomMargin, -1, alignRelative, anchorRelative);
    }

    public static RelativeLayout.LayoutParams createRelative(int width, int height, int alignParent, int alignRelative, int anchorRelative) {
        return createRelative(width, height, 0, 0, 0, 0, alignParent, alignRelative, anchorRelative);
    }

    public static RelativeLayout.LayoutParams createRelative(int width, int height) {
        return createRelative(width, height, 0, 0, 0, 0, -1, -1, -1);
    }

    public static RelativeLayout.LayoutParams createRelative(int width, int height, int alignParent) {
        return createRelative(width, height, 0, 0, 0, 0, alignParent, -1, -1);
    }

    public static RelativeLayout.LayoutParams createRelative(int width, int height, int alignRelative, int anchorRelative) {
        return createRelative(width, height, 0, 0, 0, 0, -1, alignRelative, anchorRelative);
    }

    public static LinearLayout.LayoutParams createLinear(int width, int height, float weight, int gravity, int leftMargin, int topMargin, int rightMargin, int bottomMargin) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(getSize(width), getSize(height), weight);
        layoutParams.setMargins(AndroidUtils.dp(leftMargin), AndroidUtils.dp(topMargin), AndroidUtils.dp(rightMargin), AndroidUtils.dp(bottomMargin));

        layoutParams.gravity = gravity;
        return layoutParams;
    }

    public static LinearLayout.LayoutParams createLinear(int width, int height, float weight, int leftMargin, int topMargin, int rightMargin, int bottomMargin) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(getSize(width), getSize(height), weight);
        layoutParams.setMargins(AndroidUtils.dp(leftMargin), AndroidUtils.dp(topMargin), AndroidUtils.dp(rightMargin), AndroidUtils.dp(bottomMargin));

        return layoutParams;
    }

    public static LinearLayout.LayoutParams createLinear(int width, int height, int gravity, int leftMargin, int topMargin, int rightMargin, int bottomMargin) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(getSize(width), getSize(height));
        layoutParams.setMargins(AndroidUtils.dp(leftMargin), AndroidUtils.dp(topMargin), AndroidUtils.dp(rightMargin), AndroidUtils.dp(bottomMargin));
        layoutParams.gravity = gravity;
        return layoutParams;
    }

    public static LinearLayout.LayoutParams createLinear(int width, int height, float leftMargin, float topMargin, float rightMargin, float bottomMargin) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(getSize(width), getSize(height));
        layoutParams.setMargins(AndroidUtils.dp(leftMargin), AndroidUtils.dp(topMargin), AndroidUtils.dp(rightMargin), AndroidUtils.dp(bottomMargin));
        return layoutParams;
    }

    public static LinearLayout.LayoutParams createLinear(int width, int height, float weight, int gravity) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(getSize(width), getSize(height), weight);
        layoutParams.gravity = gravity;
        return layoutParams;
    }

    public static LinearLayout.LayoutParams createLinear(int width, int height, int gravity) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(getSize(width), getSize(height));
        layoutParams.gravity = gravity;
        return layoutParams;
    }

    public static LinearLayout.LayoutParams createLinear(int width, int height, float weight) {
        return new LinearLayout.LayoutParams(getSize(width), getSize(height), weight);
    }

    public static LinearLayout.LayoutParams createLinear(int width, int height) {
        return new LinearLayout.LayoutParams(getSize(width), getSize(height));
    }
}