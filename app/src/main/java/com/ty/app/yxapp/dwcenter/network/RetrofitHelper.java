package com.ty.app.yxapp.dwcenter.network;

import android.util.Log;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by heli on 2017/4/3.
 */
public class RetrofitHelper {
    private static final String TAG = "RetrofitHelper";
    private static RetrofitHelper mRetrofitHelper;
    private static Retrofit mRetrofit;
    private RequestServer requestServer;

    private RetrofitHelper(){
        mRetrofit = new Retrofit.Builder()
                .baseUrl("https://cp.dawawg.com/")
                        //增加返回值为String的支持
                .addConverterFactory(ScalarsConverterFactory.create())
                        //增加返回值为Gson的支持(以实体类返回)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestServer();
    }

    public static RetrofitHelper getInstance(){
        if(mRetrofitHelper == null){
            synchronized (RetrofitHelper.class){
                if(mRetrofitHelper == null){
                    mRetrofitHelper = new RetrofitHelper();
                }
            }
        }
        return mRetrofitHelper;
    }

    private void RequestServer(){
        if(mRetrofit != null){
            requestServer = mRetrofit.create(RequestServer.class);
        }
    }


    /**
     * Login
     */
    public  void Login(String name,String psw,OnResultListener onResultListener){
        OnCallBackListener onCallBackListener = new OnCallBackListener("Login",onResultListener);
        Call<String> call = requestServer.getLoginStatus(name, psw);
        if(call != null)  call.enqueue(onCallBackListener);
    }


    public interface OnResultListener{
        void onResult(Result result);
    }

    public class OnCallBackListener implements Callback{
        private OnResultListener onResultListener;
        private String flag;

        public OnCallBackListener(String flag,OnResultListener onResultListener){
            this.flag = flag;
            this.onResultListener = onResultListener;
        }

        @Override
        public void onResponse(Call call, Response response) {
            if(call.isCanceled())return;
            int code = -1;
            String message = "网络异常";
            String  url = call.request().url().toString();

            if(response == null || !response.isSuccess()){
                code = response.code();
                message = response.message();
                if(onResultListener != null){
                    onResultListener.onResult(new Result(code,message));
                }
            }else{
                code = response.code();
                message = response.message();
                String res ;
                try {
                    Result result = new Result(code,message);
                    res = response.body().toString();

                    if(res != null){
                        if("Login".equals(flag)){
                            result = result.LoginResult(res);
                        }
                    }
                    if(onResultListener != null) onResultListener.onResult(result);
                    Log.d(TAG,"url :"+url +" result: "+res);
                }catch (Exception e){
                    Log.e(TAG,"url :"+url + " ,"+ new Throwable(e).toString());
                    if(onResultListener != null){
                        onResultListener.onResult(new Result(code,message));
                    }
                    return;
                }
            }
        }

        @Override
        public void onFailure(Call call, Throwable t) {
            if(call.isCanceled()) return;

            Result result = new Result(-1,"网络异常");
            if(onResultListener != null) onResultListener.onResult(result);
        }
    }
}
