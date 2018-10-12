package com.ifree.uu.uubuy.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.Gson;
import com.ifree.uu.uubuy.mvp.entity.UserInfoEntity;
import com.ifree.uu.uubuy.mvp.presenter.OnclickCountPresenter;
import com.ifree.uu.uubuy.mvp.view.ProjectView;
import com.ifree.uu.uubuy.uitls.SPUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2018/10/8 0008
 * Description:
 */
public class UURunService extends Service {
    private OnclickCountPresenter mOnclickCountPresenter;
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
        myHandler.postDelayed(myRunnable, 300000);//五分钟
        return super.onStartCommand(intent, flags, startId);
    }

    private Runnable myRunnable = new Runnable() {
        @Override
        public void run() {
            try {
                Map<String,String> currentMap = SPUtil.getMap(context,"key");
                Log.i("fff", "onSuccess: " + new Gson().toJson(currentMap));
                if (currentMap == null || currentMap.size() == 0){
                    submit();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    private void submit() {
        myHandler.removeCallbacks(myRunnable);
        myHandler.postDelayed(myRunnable, 300000);//300000
        String json = new Gson().toJson(SPUtil.getMap(context,"key"));
        mOnclickCountPresenter = new OnclickCountPresenter(context);
        mOnclickCountPresenter.onCreate();
        mOnclickCountPresenter.attachView(new ProjectView<UserInfoEntity>() {
            @Override
            public void onSuccess(UserInfoEntity userInfoEntity) {
                if (userInfoEntity.getResultCode().equals("0")){
                    SPUtil.putMap(context,"key",new HashMap<String, String>());
                }
            }
            @Override
            public void onError(String result) {
            }
        });
        mOnclickCountPresenter.getClickCount(json);
    }
}
