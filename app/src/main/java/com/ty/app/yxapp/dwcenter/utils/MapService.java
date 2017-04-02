package com.ty.app.yxapp.dwcenter.utils;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.services.weather.LocalWeatherForecastResult;
import com.amap.api.services.weather.LocalWeatherLive;
import com.amap.api.services.weather.LocalWeatherLiveResult;
import com.amap.api.services.weather.WeatherSearch;
import com.amap.api.services.weather.WeatherSearchQuery;

public class MapService extends IntentService implements AMapLocationListener, WeatherSearch.OnWeatherSearchListener {

    private static final String TAG = "MapService";
    private static GetWeatherListener mWeatherListener;
    private String city = "";

    public MapService() {
        super("MapService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        setUpLocation();
        setUpWeather();
    }

    public static void setGetWeatherListener(GetWeatherListener weatherListener) {
        mWeatherListener = weatherListener;
    }

    private void setUpLocation() {
        AMapLocationClient mLocationClient = new AMapLocationClient(this.getApplicationContext());
        AMapLocationClientOption mLocationOption = new AMapLocationClientOption();
        mLocationOption.setOnceLocation(false);
        mLocationOption.setInterval(10 * 1000);
        mLocationClient.setLocationOption(mLocationOption);
        mLocationClient.setLocationListener(this);
        mLocationClient.startLocation();
    }

    private void setUpWeather() {
        WeatherSearchQuery mquery = null;
        if (city.equals("")) {
            mquery = new WeatherSearchQuery("北京", WeatherSearchQuery.WEATHER_TYPE_LIVE);
        }else {
            mquery = new WeatherSearchQuery(city, WeatherSearchQuery.WEATHER_TYPE_LIVE);
        }
        WeatherSearch mweathersearch = new WeatherSearch(this);
        mweathersearch.setOnWeatherSearchListener(this);
        mweathersearch.setQuery(mquery);
        mweathersearch.searchWeatherAsyn(); //异步搜索
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        city = aMapLocation.getCity();
        Log.e(TAG, "locationChanged-->>" + aMapLocation.getLongitude() + ",," + aMapLocation.getLatitude());
        AndroidUtils.ShowToast("location:" + aMapLocation.getLatitude() + "," + aMapLocation.getLongitude());
    }

    @Override
    public void onWeatherLiveSearched(LocalWeatherLiveResult localWeatherLiveResult, int rCode) {
        Log.e(TAG, "rcode=" + rCode);
        if (rCode == 1000) {
            if (localWeatherLiveResult != null && localWeatherLiveResult.getLiveResult() != null) {
                LocalWeatherLive weatherlive = localWeatherLiveResult.getLiveResult();

                String weatherStr = weatherlive.getReportTime() + "发布  " + weatherlive.getWeather() + weatherlive.getTemperature() + "°"
                        + weatherlive.getWindDirection() + "风" + weatherlive.getWindPower() + "级"
                        + "  湿度" + weatherlive.getHumidity() + "%";
                Log.e(TAG, "weather" + weatherStr);
                mWeatherListener.onGetWeather(weatherStr);
            } else {
                Log.e(TAG, "weather no result");
            }
        } else {
            Log.e(TAG, "weather " + rCode);
        }
    }

    @Override
    public void onWeatherForecastSearched(LocalWeatherForecastResult localWeatherForecastResult, int i) {
        Log.e(TAG, "onWeatherForecastSearched");
    }
}
