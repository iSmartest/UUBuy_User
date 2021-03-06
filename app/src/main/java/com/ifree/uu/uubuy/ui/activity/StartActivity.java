package com.ifree.uu.uubuy.ui.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;

import com.gyf.barlibrary.BarHide;
import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.common.CommonActivity;
import com.ifree.uu.uubuy.config.Constant;
import com.ifree.uu.uubuy.listener.GaoDeLocationListener;
import com.hjq.toast.ToastUtils;
import com.ifree.uu.uubuy.mvp.modle.UserInfoBean;
import com.ifree.uu.uubuy.mvp.persenter.UpLoadUUIdPresenter;
import com.ifree.uu.uubuy.mvp.view.ProjectView;
import com.ifree.uu.uubuy.utils.SPUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/17.
 * Description:
 */
public class StartActivity extends CommonActivity {
    private UpLoadUUIdPresenter mUpLoadUUIdPresenter;
    private static final int SHOW_TIME_MIN = 1000;// 最小显示时间
    private long mStartTime;// 开始时间
    private boolean IsFirst;//第一次进入应用
    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;
    protected Context context;
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
                startActivity(new Intent(StartActivity.this, GuideActivity.class));
                SPUtil.putBoolean(StartActivity.this, Constant.FIRST_COME, false);
                finish();
            } else {
                startActivity(new Intent(StartActivity.this, MainActivity.class));
                finish();
            }
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_start;
    }

    @Override
    protected int getTitleBarId() {
        return 0;
    }

    @Override
    protected void initView() {
        mStartTime = System.currentTimeMillis();//记录开始时间1
        context = this;
        mUpLoadUUIdPresenter = new UpLoadUUIdPresenter(context);
        //设置状态栏和导航栏参数
        getStatusBarConfig()
                .fullScreen(true)//有导航栏的情况下，activity全屏显示，也就是activity最下面被导航栏覆盖，不写默认非全屏
                .hideBar(BarHide.FLAG_HIDE_STATUS_BAR)//隐藏状态栏
                .transparentNavigationBar()//透明导航栏，不写默认黑色(设置此方法，fullScreen()方法自动为true)
                .init();
    }

    @Override
    protected void initData() {
        checkPermission();
    }

    private void checkPermission() {
        //判断Android版本   获取需要的权限
        if (Build.VERSION.SDK_INT >= 23) {
            List<String> permissionStrs = new ArrayList<>();
            int hasWriteSdcardPermission = ContextCompat.checkSelfPermission(StartActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (hasWriteSdcardPermission != PackageManager.PERMISSION_GRANTED) {
                permissionStrs.add(Manifest.permission.ACCESS_FINE_LOCATION);
                permissionStrs.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
                permissionStrs.add(Manifest.permission.READ_EXTERNAL_STORAGE);
                permissionStrs.add(Manifest.permission.CALL_PRIVILEGED);
                permissionStrs.add(Manifest.permission.MODIFY_AUDIO_SETTINGS);
                permissionStrs.add(Manifest.permission.READ_PHONE_STATE);
                permissionStrs.add(Manifest.permission.READ_CONTACTS);
                permissionStrs.add(Manifest.permission.RECORD_AUDIO);
                permissionStrs.add(Manifest.permission.VIBRATE);
                permissionStrs.add(Manifest.permission.CAMERA);
            }
            String[] stringArray = permissionStrs.toArray(new String[0]);
            if (permissionStrs.size() > 0) {
                requestPermissions(stringArray, REQUEST_CODE_ASK_PERMISSIONS);
                return;
            }
            mHandler.sendEmptyMessage(1);
        } else {
            mHandler.sendEmptyMessage(1);
            initLocation();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                mHandler.sendEmptyMessage(1);
                initLocation();
                break;
        }
    }



    private ProjectView<UserInfoBean> mUpLoadUUIdView = new ProjectView<UserInfoBean>() {
        @Override
        public void onSuccess(UserInfoBean userInfoBean) {
            if (userInfoBean.getResultCode().equals("1")) {
                ToastUtils.show(userInfoBean.getMsg());
                return;
            }
        }

        @Override
        public void onError(String result) {

        }
    };

    private void initLocation() {
        GaoDeLocationListener gaoDeLocationListener = new GaoDeLocationListener(StartActivity.this, new GaoDeLocationListener.OnQuestResultListener() {
            @Override
            public void success(String result) {

            }

            @Override
            public void error(String result) {

            }
        });
        gaoDeLocationListener.startLocation();
    }

    @Override
    public void finish() {
        super.finish();
        saveTag();
    }

    private void saveTag() {
        SPUtil.putBoolean(StartActivity.this, Constant.FIRST_COME, false);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mHandler.removeCallbacks(goToMainActivity);//移除回调
    }

}
