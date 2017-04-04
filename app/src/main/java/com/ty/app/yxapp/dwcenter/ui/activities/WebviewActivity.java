package com.ty.app.yxapp.dwcenter.ui.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ty.app.yxapp.dwcenter.R;
import com.ty.app.yxapp.dwcenter.ui.activities.base.BaseActivity;
import com.ty.app.yxapp.dwcenter.ui.activities.base.Constants;
import com.ty.app.yxapp.dwcenter.utils.AndroidUtils;

public class WebviewActivity extends BaseActivity {

    private WebView webView;

    @Override
    public void onBeforeCreate() {

    }

    @Override
    public View onCreate() {
        actionBar.setVisibility(View.VISIBLE);
        actionBar.setCenterView(AndroidUtils.getString(R.string.app_name));
        actionBar.setLeftView("", R.mipmap.back, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        View view = getLayoutInflater().inflate(R.layout.activity_webview, null);
        webView = (WebView) view.findViewById(R.id.web_view);
        WebSettings settings = webView.getSettings();
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        int urlAdd = getIntent().getIntExtra("url",4);
        switch (urlAdd){
            case Constants.EMS_INDEX:

                break;
            case Constants.PHONE_BILL_INDEX:

                break;
            case Constants.ILLEGAL_INDEX:

                break;
            case Constants.SEARCH_INDEX:
                webView.loadUrl(Constants.URL_BAIDU);
                break;
            case Constants.US_GROUP_INDEX:

                break;
            case Constants.CTRIP_INDEX:

                break;
            case Constants.SAME_CITY_INDEX:

                break;
            case Constants.BAIDU_YUN:

                break;
            default:
                break;
        }
        return view;
    }
}
