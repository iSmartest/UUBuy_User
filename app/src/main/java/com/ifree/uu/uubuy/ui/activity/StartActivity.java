package com.ifree.uu.uubuy.ui.activity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.app.MyApplication;
import com.ifree.uu.uubuy.config.Constant;
import com.ifree.uu.uubuy.uitls.SPUtil;
import com.ifree.uu.uubuy.uitls.StatusBarUtil;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/17.
 * Description:
 */
public class StartActivity extends AppCompatActivity {
    private static final int SHOW_TIME_MIN = 1000;// 最小显示时间
    private long mStartTime;// 开始时间
    private boolean IsFirst;//第一次进入应用
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 1:
                    long loadingTime = System.currentTimeMillis() - mStartTime;// 计算一下总共花费的时间
                    if (loadingTime < SHOW_TIME_MIN) {
                        // 如果比最小显示时间还短，就延时进入MainActivity，否则直接进入
                        mHandler.postDelayed(goToMainActivity, SHOW_TIME_MIN - loadingTime);
                    } else {
                        mHandler.post(goToMainActivity);
                    }
                    break;
                default:
                    break;
            }
        }
    };

    //进入下一个Activity
    Runnable goToMainActivity = new Runnable() {
        @Override
        public void run() {
            IsFirst = SPUtil.getBoolean(StartActivity.this, Constant.FIRST_COME, true);
            if (IsFirst) {
                startActivity(new Intent(StartActivity.this,
                        MainActivity.class));
                SPUtil.putBoolean(StartActivity.this, Constant.FIRST_COME, false);
                finish();
            } else {
                MyApplication.openActivity(StartActivity.this, MainActivity.class);
                finish();
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getWindow().getDecorView().setBackgroundResource(R.mipmap.splash);
        super.onCreate(savedInstanceState);
        StatusBarUtil.fullScreen(StartActivity.this);
        mStartTime = System.currentTimeMillis();//记录开始时间1
        mHandler.sendEmptyMessage(1);
    }


    private void saveTag() {
        SPUtil.putBoolean(StartActivity.this, Constant.FIRST_COME, false);
    }

    @Override
    public void finish() {
        super.finish();
        saveTag();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mHandler.removeCallbacks(goToMainActivity);//移除回调
    }

}
