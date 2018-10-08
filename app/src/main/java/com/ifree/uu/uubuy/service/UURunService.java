package com.ifree.uu.uubuy.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2018/10/8 0008
 * Description:
 */
public class UURunService extends Service {
    static Handler myHandler = new Handler();
    protected static Context context;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    //服务执行的操作
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //处理具体的逻辑
        myHandler.postDelayed(myRunnable, 3000000);//五分钟
        return super.onStartCommand(intent, flags, startId);
    }

    private Runnable myRunnable = new Runnable() {
        @Override
        public void run() {
            try {
                myHandler.postDelayed(this, 3000000);
                Log.i("TAG", "run: " + "呵呵");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
}
