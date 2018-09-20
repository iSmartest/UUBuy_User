package com.ifree.uu.uubuy.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.multidex.MultiDex;

import com.ifree.uu.uubuy.uitls.CrashHandler;
import com.ifree.uu.uubuy.uitls.DensityUtils;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import cn.jpush.android.api.JPushInterface;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/14.
 * Description:
 */

public class MyApplication extends Application {
    public static Context CONTEXT;
    private static MyApplication myApplication;
    public static int defaultItem = 0;
    public static int shopPay = 0;
    public static int evaluate = 0;
    @Override
    public void onCreate() {
        super.onCreate();
        CONTEXT = getApplicationContext();
        UMShareAPI.get(this);
        JPushInterface.setDebugMode(true);//如果时正式版就改成false
        JPushInterface.init(this);
        PlatformConfig.setWeixin("wx70539901dcad8e3d","e97ee6fac5e3d9e9da31b02450273f55");
        PlatformConfig.setQQZone("1107863232","GLfhGhsWkLDSGpC2");
        myApplication = this;
        //崩溃错误日志写入本地文档
        DensityUtils.setDensity(this);
        CrashHandler catchExcep = new CrashHandler(this);
        Thread.setDefaultUncaughtExceptionHandler(catchExcep);
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }
    public static Context getContext(){
        return CONTEXT;
    }
    public static MyApplication getApplication() {
        return myApplication;
    }

    /**
     * 通过类名启动Activity
     *
     * @param targetClass
     */
    public static void openActivity(Context context, Class<?> targetClass) {
        openActivity(context, targetClass, null);
    }

    /**
     * 通过类名启动Activity，并且含有Bundle数据
     *
     * @param targetClass
     * @param extras
     */
    public static void openActivity(Context context, Class<?> targetClass,
                                    Bundle extras) {
        Intent intent = new Intent(context, targetClass);
        if (extras != null) {
            intent.putExtras(extras);
        }
        context.startActivity(intent);
    }

    public static void openActivityForResult(Activity activity,
                                             Class<?> targetClass, Bundle extras, int requestCode) {
        Intent intent = new Intent(activity, targetClass);
        if (extras != null) {
            intent.putExtras(extras);
        }
        activity.startActivityForResult(intent, requestCode);
    }

    public static void openActivityForResult(Activity activity,
                                             Class<?> targetClass, int requestCode) {
        openActivityForResult(activity, targetClass, null, requestCode);
    }

    /**
     * 通过Action启动Activity
     *
     * @param action
     */
    public static void openActivity(Context context, String action) {
        openActivity(context, action, null);
    }

    /**
     * 通过Action启动Activity，并且含有Bundle数据
     *
     * @param action
     * @param extras
     */
    public static void openActivity(Context context, String action,
                                    Bundle extras) {
        Intent intent = new Intent(action);
        if (extras != null) {
            intent.putExtras(extras);
        }
        context.startActivity(intent);
    }
}
