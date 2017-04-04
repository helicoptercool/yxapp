package com.ty.app.yxapp.dwcenter.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.ty.app.yxapp.dwcenter.R;
import com.ty.app.yxapp.dwcenter.ui.activities.base.BaseActivity;
import com.ty.app.yxapp.dwcenter.ui.widget.RoundButton;
import com.ty.app.yxapp.dwcenter.utils.AndroidUtils;

import static android.R.style.Theme;

/**
 * Created by kss on 2017/4/4.
 */

public class EditeActivity extends BaseActivity {
    private static final String TAG = "EditeActivity";
    private Context context;
    private EditText edtText;

    @Override
    public void onBeforeCreate() {

    }

    @Override
    public View onCreate() {
        this.context = getBaseContext();
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String value = intent.getStringExtra("value");

        actionBar.setVisibility(View.VISIBLE);
        actionBar.setCenterView(TextUtils.isEmpty(title) ? AndroidUtils.getString(R.string.choice):title);

        LinearLayout container = new LinearLayout(context);
        container.setBackgroundColor(0xFFF5F5F5);
        container.setPadding(0, AndroidUtils.dp(10), 0, 0);
        container.setOrientation(LinearLayout.VERTICAL);
        container.setGravity(Gravity.CENTER_HORIZONTAL);
        container.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT));


        edtText = new EditText(context);
        edtText.setBackgroundColor(0xFFFFFFFF);
        edtText.setTextSize(16);
        edtText.setPadding(AndroidUtils.dp(13), AndroidUtils.dp(15), AndroidUtils.dp(10), AndroidUtils.dp(15));
        edtText.setTextColor(0xFF2D2D34);
        edtText.setGravity(Gravity.TOP);
        edtText.setLineSpacing(2, 1.2f);
        edtText.setHint(AndroidUtils.getString(R.string.input));
        edtText.setHintTextColor(0xFFb8b3b4);
        edtText.setText(TextUtils.isEmpty(value) ? AndroidUtils.getString(R.string.choice) : value);
        edtText.setSelection(edtText.getText().length());
        edtText.setLines(4);
        container.addView(edtText, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));


        RoundButton button = new RoundButton(context, 0xFFff237d, 0xFFCD1964, 0x00000000);
        button.setOnClickListener(onClick);
        button.setText(AndroidUtils.getString(R.string.submit));
        button.setTextSize(16);
        button.setTextColor(0xffffffff);
        LinearLayout.LayoutParams ll = new LinearLayout.LayoutParams(AndroidUtils.dp(150),AndroidUtils.dp(42));
        ll.setMargins(0,AndroidUtils.dp(30),0,AndroidUtils.dp(30));
        container.addView(button, ll);

        return container;
    }

    private View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent();
            intent.putExtra("reValue",edtText.getText().toString());
            setResult(0,intent);
            finish();
        }
    };
}
