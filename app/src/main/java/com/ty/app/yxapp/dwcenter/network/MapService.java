package com.ty.app.yxapp.dwcenter.network;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
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
import com.ty.app.yxapp.dwcenter.ui.activities.fragment.MainSecondPagerActivity;
import com.ty.app.yxapp.dwcenter.utils.GetWeatherListener;

public class MapService extends Service implements AMapLocationListener, WeatherSearch.OnWeatherSearchListener {

    private static final String TAG = "MapService";
    private static GetWeatherListener mWeatherListener;
    private static String address = "";
    private String city = "";
    private Handler mHandler;
    private static MainSecondPagerActivity.MyLocationListener myLocationListener;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mHandler = new Handler(getMainLooper());
        new Thread() {
            @Override
            public void run() {
                setUpLocation();
                setUpWeather();
            }
        }.start();
        return super.onStartCommand(intent, flags, startId);
    }

    public static void setGetWeatherListener(GetWeatherListener weatherListener) {
        mWeatherListener = weatherListener;
    }

    public static void setMyLocationListener(MainSecondPagerActivity.MyLocationListener locationListener) {
        myLocationListener = locationListener;
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
            mquery = new WeatherSearchQuery("盘锦市", WeatherSearchQuery.WEATHER_TYPE_LIVE);
        } else {
            mquery = new WeatherSearchQuery(city, WeatherSearchQuery.WEATHER_TYPE_LIVE);
        }
        WeatherSearch mweathersearch = new WeatherSearch(this);
        mweathersearch.setOnWeatherSearchListener(this);
        mweathersearch.setQuery(mquery);
        mweathersearch.searchWeatherAsyn(); //异步搜索
    }

    @Override
    public void onLocationChanged(final AMapLocation aMapLocation) {
        city = aMapLocation.getCity();
        address = aMapLocation.getAddress();
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                Log.e(TAG, "locationChanged-->>" + aMapLocation.getLongitude() + ",," + aMapLocation.getLatitude() + "city = " + aMapLocation.getCity() + ", address=" + aMapLocation.getAddress());
                if (myLocationListener != null) {
                    myLocationListener.getLocation(address + "；" + aMapLocation.getLatitude() + "；" + aMapLocation.getLongitude());
                }
            }
        });
    }

    @Override
    public void onWeatherLiveSearched(LocalWeatherLiveResult localWeatherLiveResult, int rCode) {
        Log.e(TAG, "rcode=" + rCode);
        if (rCode == 1000) {
            if (localWeatherLiveResult != null && localWeatherLiveResult.getLiveResult() != null) {
                LocalWeatherLive weatherlive = localWeatherLiveResult.getLiveResult();

                final String weatherStr = weatherlive.getReportTime().substring(0, weatherlive.getReportTime().length() - 3) + "发布  " + weatherlive.getWeather() + weatherlive.getTemperature() + "°"
                        + weatherlive.getWindDirection() + "风" + weatherlive.getWindPower() + "级"
                        + "  湿度" + weatherlive.getHumidity() + "%";
                Log.e(TAG, "weather" + weatherStr);
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (mWeatherListener != null) {
                            mWeatherListener.onGetWeather(weatherStr);
                        }
                    }
                });
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
