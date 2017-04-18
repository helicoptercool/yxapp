package com.ty.app.yxapp.dwcenter.ui.activities;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.util.Log;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.ty.app.yxapp.dwcenter.R;
import com.ty.app.yxapp.dwcenter.ui.activities.base.BaseActivity;
import com.ty.app.yxapp.dwcenter.ui.activities.base.Constants;
import com.ty.app.yxapp.dwcenter.utils.AndroidUtils;

public class WebviewActivity extends BaseActivity {
    private static final String TAG = WebviewActivity.class.getSimpleName();
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
        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        int urlAddr = getIntent().getIntExtra("url",4);
        switch (urlAddr){
            case Constants.EMS_INDEX:
                webView.loadUrl(Constants.URL_EMS);
                break;
            case Constants.PHONE_BILL_INDEX:
                webView.loadUrl(Constants.URL_PHONE_BILL);
                break;
            case Constants.ILLEGAL_INDEX:
                webView.loadUrl(Constants.URL_ILLEGAL);
                break;
            case Constants.SEARCH_INDEX:
                webView.loadUrl(Constants.URL_SEARCH);
                break;
            case Constants.SINA_INDEX:
                Log.e(TAG,"url = "+Constants.URL_SINA);
                webView.loadUrl(Constants.URL_SINA);
                break;
            case Constants.SOHU_INDEX:
                webView.loadUrl(Constants.URL_SOHU);
                break;
            case Constants.TECENT_INDEX:
                webView.loadUrl(Constants.URL_TECENT);
                break;
            case Constants.WANGYI_INDEX:
                webView.loadUrl(Constants.URL_WANGYI);
                break;
            case Constants.US_GROUP_INDEX:
                webView.loadUrl(Constants.URL_US_GROUP);
                break;
            case Constants.CTRIP_INDEX:
                webView.loadUrl(Constants.URL_CTRIP);
                break;
            case Constants.SAME_CITY_INDEX:
                webView.loadUrl(Constants.URL_SAME_CITY);
                break;
            case Constants.BAIDU_YUN_INDEX:
                webView.loadUrl(Constants.URL_BAIDU_YUN);
                break;
            case Constants.TMALL_INDEX:
                webView.loadUrl(Constants.URL_TIANMAO);
                break;
            case Constants.ELEM_INDEX:
                webView.loadUrl(Constants.URL_ELEME);
                break;
            case Constants.JINGDONG_INDEX:
                webView.loadUrl(Constants.URL_JINGDONG);
                break;
            case Constants.YOUKU_INDEX:
                webView.loadUrl(Constants.URL_YOUKU);
                break;
            default:
                break;
        }
        final SVProgressHUD loading = new SVProgressHUD(WebviewActivity.this);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                loading.dismiss();
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                loading.dismiss();
                handler.proceed();
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                loading.showWithStatus(AndroidUtils.getString(R.string.requesting));
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                loading.dismissImmediately();
            }
        });
        return view;
    }
}
