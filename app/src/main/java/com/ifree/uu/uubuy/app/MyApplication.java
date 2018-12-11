package com.ifree.uu.uubuy.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.multidex.MultiDex;

import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import java.util.ArrayList;
import java.util.List;

import cn.jpush.android.api.JPushInterface;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/14.
 * Description:
 */

public class MyApplication extends Application {
    private static MyApplication myApplication;
    private static List<Activity> lists = new ArrayList<>();
    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        UMShareAPI.get(this);
        initJPush();
        PlatformConfig.setWeixin("wx70539901dcad8e3d","e97ee6fac5e3d9e9da31b02450273f55");
        PlatformConfig.setQQZone("1107863232","GLfhGhsWkLDSGpC2");
//        CrashHandler catchException = new CrashHandler(this);
//        Thread.setDefaultUncaughtExceptionHandler(catchException);
    }

    public static  void initJPush(){
        JPushInterface.setDebugMode(Config.DEBUG);
        JPushInterface.init(myApplication);
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
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

    public static void addActivity(Activity activity) {
        lists.add(activity);
    }

    public static void clearActivity() {
        if (lists != null) {
            for (Activity activity : lists) {
                activity.finish();
            }
            lists.clear();
        }
    }
}
