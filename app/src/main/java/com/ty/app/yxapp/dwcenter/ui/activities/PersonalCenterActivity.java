package com.ty.app.yxapp.dwcenter.ui.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.ty.app.yxapp.dwcenter.R;
import com.ty.app.yxapp.dwcenter.bean.User;
import com.ty.app.yxapp.dwcenter.network.Result;
import com.ty.app.yxapp.dwcenter.network.RetrofitHelper;
import com.ty.app.yxapp.dwcenter.ui.activities.base.BaseActivity;
import com.ty.app.yxapp.dwcenter.ui.activities.base.Constants;
import com.ty.app.yxapp.dwcenter.utils.AndroidUtils;
import com.ty.app.yxapp.dwcenter.utils.SPManager;

import java.io.File;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Random;

public class PersonalCenterActivity extends BaseActivity {

    private LinearLayout personalLayout;
    private TextView nameTv;
    private TextView accountTv;
    private Button logoutBtn;
    private ImageView qrcodeIv;

    @Override
    public void onBeforeCreate() {

    }

    @Override
    public View onCreate() {
        actionBar.setVisibility(View.VISIBLE);
        actionBar.setCenterView(AndroidUtils.getString(R.string.personal_center));
        actionBar.setLeftView("", R.mipmap.back, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });
        View view = getLayoutInflater().inflate(R.layout.activity_personal_center, null);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        SPManager manager = new SPManager();
        String name = manager.readSp(Constants.SP_USER_NAME);
        personalLayout = (LinearLayout) view.findViewById(R.id.layout_personal);
        nameTv = (TextView) view.findViewById(R.id.tv_personal_name);
        accountTv = (TextView) view.findViewById(R.id.tv_personal_account);
        qrcodeIv = (ImageView) view.findViewById(R.id.qrcode_iv);
        Bitmap bitmap = createImage(Constants.BASE_SEVICE_ADDRESS);
        qrcodeIv.setImageBitmap(bitmap);
        accountTv.setText(name);
        nameTv.setText(name);
        logoutBtn = (Button) view.findViewById(R.id.btn_logout);
        personalLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonalCenterActivity.this, SetActivity.class);
                startActivity(intent);
            }
        });
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonalCenterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        RetrofitHelper.getInstance().getUserInfo("", name, new RetrofitHelper.OnResultListener() {
            @Override
            public void onResult(Result result) {
                if (result != null && result.isOK()) {
                    final String username = ((User) result.getData()).getUserName();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            nameTv.setText(username);
                        }
                    });
                }
            }
        });
    }

    int QR_WIDTH = 300;
    int QR_HEIGHT = 300;

    private Bitmap createImage(String text) {
        try {

            if (TextUtils.isEmpty(text)) {
                return null;
            }
            QRCodeWriter writer = new QRCodeWriter();
            BitMatrix martix = writer.encode(text, BarcodeFormat.QR_CODE,
                    QR_WIDTH, QR_HEIGHT);

            Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
            BitMatrix bitMatrix = new QRCodeWriter().encode(text,
                    BarcodeFormat.QR_CODE, QR_WIDTH, QR_HEIGHT, hints);
            int[] pixels = new int[QR_WIDTH * QR_HEIGHT];
            for (int y = 0; y < QR_HEIGHT; y++) {
                for (int x = 0; x < QR_WIDTH; x++) {
                    if (bitMatrix.get(x, y)) {
                        pixels[y * QR_WIDTH + x] = 0xff000000;
                    } else {
                        pixels[y * QR_WIDTH + x] = 0xffffffff;
                    }
                }
            }

            Bitmap bitmap = Bitmap.createBitmap(QR_WIDTH, QR_HEIGHT,
                    Bitmap.Config.ARGB_8888);

            bitmap.setPixels(pixels, 0, QR_WIDTH, 0, 0, QR_WIDTH, QR_HEIGHT);
            return bitmap;
        } catch (WriterException e) {
            e.printStackTrace();
            return null;
        }
    }
}