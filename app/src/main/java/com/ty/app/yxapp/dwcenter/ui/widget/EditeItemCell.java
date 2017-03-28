package com.ty.app.yxapp.dwcenter.ui.widget;

import android.content.Context;
import android.os.Build;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ty.app.yxapp.dwcenter.R;
import com.ty.app.yxapp.dwcenter.utils.AndroidUtils;


/**
 * Created by kss on 2017/3/27.
 */
public class EditeItemCell extends RelativeLayout {

    private final TextView titleValue;
    private TextView titleView;
    private TextView subTitleView;

    private TextView valueView;
    private ImageView iconView;
    private ImageView switchView;

    private DividerSmallCell lineView;

    private boolean checked = false;

    public EditeItemCell(Context context, String title) {
        super(context);
        LinearLayout leftContainer = new LinearLayout(context);
        leftContainer.setOrientation(LinearLayout.VERTICAL);
        RelativeLayout.LayoutParams leftRl = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        leftRl.setMargins(AndroidUtils.dp(15),0,AndroidUtils.dp(15),0);
        leftRl.addRule(RelativeLayout.CENTER_VERTICAL);
        leftRl.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        leftContainer.setPadding(0, AndroidUtils.dp(15), 0, AndroidUtils.dp(15));
        addView(leftContainer, leftRl);

        RelativeLayout titleCon = new RelativeLayout(context);
        LinearLayout.LayoutParams titleLL = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        leftContainer.addView(titleCon, titleLL);

        titleView = new TextView(context);
        titleView.setTextColor(0xff2D2D34);
        titleView.setTextSize(16);
        titleView.setText(title);
        RelativeLayout.LayoutParams titleRl = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        titleRl.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        titleRl.addRule(RelativeLayout.CENTER_VERTICAL);
        titleCon.addView(titleView, titleRl);

        titleValue = new TextView(context);
        titleValue.setVisibility(GONE);
        titleValue.setTextColor(0xFF8F9098);
        titleValue.setTextSize(14);
        titleValue.setLineSpacing(2, 1.2f);
        titleValue.setPadding(AndroidUtils.dp(100),0,0,0);
        titleValue.setEllipsize(TextUtils.TruncateAt.END);
        titleValue.setMaxLines(1);
        titleValue.setGravity(Gravity.CENTER_VERTICAL | Gravity.END);
        titleValue.setCompoundDrawablesWithIntrinsicBounds(0,0,R.mipmap.ic_jiantou,0);
        titleValue.setCompoundDrawablePadding(AndroidUtils.dp(5));
        RelativeLayout.LayoutParams titleValueRl = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        if (Build.VERSION.SDK_INT >= 17) {
            titleValueRl.addRule(RelativeLayout.ALIGN_PARENT_END);
        } else {
            titleValueRl.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        }
        titleValueRl.addRule(RelativeLayout.CENTER_VERTICAL);
        titleCon.addView(titleValue, titleValueRl);

        subTitleView = new TextView(context);
        subTitleView.setTextColor(0xFF8F9098);
        subTitleView.setTextSize(14);
        subTitleView.setVisibility(View.GONE);
        subTitleView.setLineSpacing(2, 1.2f);
        LinearLayout.LayoutParams subTitleViewLL = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        subTitleViewLL.setMargins(0,AndroidUtils.dp(10),AndroidUtils.dp(15),0);
        leftContainer.addView(subTitleView, subTitleViewLL);

        valueView = new TextView(context);
        valueView.setTextColor(0xFF8F9098);
        valueView.setTextSize(14);
        valueView.setLineSpacing(2, 1.2f);
        valueView.setGravity(Gravity.CENTER_VERTICAL | Gravity.END);
        valueView.setCompoundDrawablesWithIntrinsicBounds(0,0,R.mipmap.ic_jiantou,0);
        valueView.setCompoundDrawablePadding(AndroidUtils.dp(5));
        RelativeLayout.LayoutParams valueViewRl = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        valueViewRl.setMargins(AndroidUtils.dp(100),AndroidUtils.dp(10),AndroidUtils.dp(15),AndroidUtils.dp(10));
        if (Build.VERSION.SDK_INT >= 17) {
            valueViewRl.addRule(RelativeLayout.ALIGN_PARENT_END);
        } else {
            valueViewRl.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        }
        valueViewRl.addRule(RelativeLayout.CENTER_VERTICAL);
        addView(valueView, valueViewRl);


        lineView = new DividerSmallCell(context);
        RelativeLayout.LayoutParams lineViewRl = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,1);
        lineViewRl.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        addView(lineView, lineViewRl);
    }


    public void setmaxWidth(int width) {
        valueView.setMaxWidth(width);
    }


    public void setValue(String v) {
        valueView.setText(v);
    }

    public void setMaxLengthValue(int lines) {
        valueView.setMaxLines(lines);
        valueView.setEllipsize(TextUtils.TruncateAt.END);
    }

    public String getValue() {
        return valueView.getText().toString();
    }

    public void hideArrow() {
        valueView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
    }

    public void hideTitle() {
        titleView.setVisibility(View.GONE);
    }

    public void setCustomTitle(String str) {
        titleView.setText(str);
    }

    public void showTitle() {
        titleView.setVisibility(View.VISIBLE);
    }

    public void setSubTitle(String sub) {
        subTitleView.setVisibility(View.VISIBLE);
        subTitleView.setText(sub);
    }

    public void clearSubTitle() {
        subTitleView.setText("");
        subTitleView.setVisibility(View.GONE);
    }

    public void setSubTitle(Spanned spanned) {
        subTitleView.setVisibility(View.VISIBLE);
        subTitleView.setText(spanned);
    }

    public boolean getChecked() {
        return checked;
    }

    public void hiddeLine() {
        lineView.setVisibility(View.GONE);
    }


    public void hideRightImg() {
        valueView.setVisibility(GONE);
    }

    public void setTitleValue(String text) {
        titleValue.setVisibility(VISIBLE);
        titleValue.setText(text);
    }

    public void showTitleValue(String text) {
        hideRightImg();
        setTitleValue(text);
    }

    public void hiddenTitleImg() {
        titleValue.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
    }

    public void setSubTitleInitMargin() {
        LinearLayout.LayoutParams subLP = (LinearLayout.LayoutParams) subTitleView.getLayoutParams();
        subLP.setMargins(0, 0, AndroidUtils.dp(15), 0);
        subTitleView.setLayoutParams(subLP);
    }

    public TextView getTitleView() {
        return subTitleView;
    }
}
