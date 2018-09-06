package com.ifree.uu.uubuy.service;

import android.content.Context;

import com.google.gson.GsonBuilder;
import com.ifree.uu.uubuy.config.BaseUrl;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/13.
 * Description:
 */
public class RetrofitHelper {

    private Context mCntext;
    OkHttpClient client = new OkHttpClient();

    GsonConverterFactory factory = GsonConverterFactory.create(new GsonBuilder().create());
    private static RetrofitHelper instance = null;
    private Retrofit mRetrofit = null;
    public static RetrofitHelper getInstance(Context context){
        if (instance == null){
            instance = new RetrofitHelper(context);
        }
        return instance;
    }
    private RetrofitHelper(Context mContext){
        mCntext = mContext;
        init();

    }
    private void init() {
        resetApp();
    }
    private void resetApp() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl.HTTP)
                .client(client)
                .addConverterFactory(factory)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public RetrofitService getServer(){
        return mRetrofit.create(RetrofitService.class);
    }
}
