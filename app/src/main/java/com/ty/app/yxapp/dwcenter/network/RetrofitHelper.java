package com.ty.app.yxapp.dwcenter.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by heli on 2017/4/3.
 */
public class RetrofitHelper {
    private static RetrofitHelper mRetrofitHelper = new RetrofitHelper();
    private Retrofit mRetrofit;
    private RetrofitHelper(){
        mRetrofit = new Retrofit.Builder()
                .baseUrl("https://cp.dawawg.com/")
                        //增加返回值为String的支持
                .addConverterFactory(ScalarsConverterFactory.create())
                        //增加返回值为Gson的支持(以实体类返回)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static RetrofitHelper getInstance(){
        return mRetrofitHelper;
    }

    public Retrofit getRetrofit(){
        return mRetrofit;
    }
}
