package com.ty.app.yxapp.dwcenter.ui.widget;


import android.content.Context;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kss on 2017/4/4.
 */
public class MenuDialog extends BaseDialog {

    private LinearLayout container;

    private TextView title;

    public MenuDialog(Context context) {
        super(context);
    }

    private List<TextView> listView = new ArrayList<>();

    @Override
    protected View onCreateView(Context context) {
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);

        title = new TextView(context);
        title.setTextSize(16);
        title.setTextColor(0xff2D2D34);
        title.setBackgroundResource(R.drawable.bg_dialog_title);
        title.setPadding(AndroidUtils.dp(16), 0, 0, 0);
        title.setGravity(Gravity.CENTER_VERTICAL | Gravity.START);
        title.setVisibility(View.GONE);
        LinearLayout.LayoutParams ll = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, AndroidUtils.dp(46));
        layout.addView(title, ll);

        container = new LinearLayout(context);
        container.setOrientation(LinearLayout.VERTICAL);
        layout.addView(container, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        return layout;
    }

    @Override
    protected void onDismissed() {

    }

    public MenuDialog setTitle(String text) {
        if (!TextUtils.isEmpty(text)) {
            title.setText(text);
            title.setVisibility(View.VISIBLE);
        } else {
            title.setVisibility(View.GONE);
        }
        return this;
    }

    public interface OnItemClickListener {
        void onItemClick(int id);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public void addSubItem(int id, String text, int icon) {
        RelativeLayout layout = new RelativeLayout(mContext);
        layout.setBackgroundDrawable(AndroidUtils.createListSelectorDrawable(mContext));
        layout.setTag(id);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isShowing()) {
                    dismiss();
                }
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick((Integer) view.getTag());
                }
            }
        });
        container.addView(layout,new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));

        TextView textView = new TextView(mContext);
        textView.setTextColor(0xff333333);
        textView.setTag(id);
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setPadding(AndroidUtils.dp(16), 0, AndroidUtils.dp(16), 0);
        textView.setTextSize(16);
        textView.setText(text);
        if (icon != 0) {
            textView.setCompoundDrawablePadding(AndroidUtils.dp(12));
            textView.setCompoundDrawablesWithIntrinsicBounds(icon, 0, 0, 0);
        }
        RelativeLayout.LayoutParams textParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,AndroidUtils.dp(46));
        textParams.addRule(RelativeLayout.CENTER_VERTICAL);
        layout.addView(textView, textParams);
        listView.add(textView);


        ImageView iconView = new ImageView(mContext);
        iconView.setImageResource(R.mipmap.ic_me_select);
        iconView.setVisibility(View.GONE);
        RelativeLayout.LayoutParams iconParams =  new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        iconParams.setMargins(0,0,AndroidUtils.dp(10),0);
        iconParams.addRule(RelativeLayout.CENTER_VERTICAL);
        layout.addView(iconView, iconParams);

        if (title.isShown() || listView.size() > 1) {
            layout.addView(new DividerSmallCell(mContext), new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, AndroidUtils.dp(1)));
        }
    }

    public void setChecked(int id) {
        for (int i = 0; i < container.getChildCount(); i++) {
            ViewGroup view = (ViewGroup) container.getChildAt(i);
            if ((Integer) view.getTag() == id) {
                view.getChildAt(1).setVisibility(View.VISIBLE);
            } else {
                view.getChildAt(1).setVisibility(View.GONE);
            }
        }
    }

    public void toggleItem(int id, boolean b) {
        for (int i = 0; i < container.getChildCount(); i++) {
            ViewGroup view = (ViewGroup) container.getChildAt(i);
            if ((Integer) view.getTag() == id) {
                view.setVisibility(b ? View.VISIBLE : View.GONE);
            }
        }
    }

    public void clear() {
        container.removeAllViews();
    }

    public void updateItemText(int id, String text) {
        if (listView.size() >= 1) {
            for (int i = 0; i < listView.size(); i++) {
                if (id == (int) listView.get(i).getTag()) {
                    listView.get(i).setText(text);
                    return;
                }
            }
        }
    }
}
