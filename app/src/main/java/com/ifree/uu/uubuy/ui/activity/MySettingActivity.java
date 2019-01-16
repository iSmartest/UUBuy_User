package com.ifree.uu.uubuy.ui.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hjq.toast.ToastUtils;
import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.app.MyApplication;
import com.ifree.uu.uubuy.common.CommonActivity;
import com.ifree.uu.uubuy.dialog.LogOutDialog;
import com.ifree.uu.uubuy.dialog.ProgressDialog;
import com.ifree.uu.uubuy.utils.DataCleanManager;
import com.ifree.uu.uubuy.utils.SPUtil;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/22.
 * Description:
 */
public class MySettingActivity extends CommonActivity {
    @BindView(R.id.ll_account_security)
    LinearLayout mSecurity;
    @BindView(R.id.ll_binding_phone)
    LinearLayout mBindingPhone;
    @BindView(R.id.ll_feedback)
    LinearLayout mFeedBack;
    @BindView(R.id.linear_my_setting_clear_cache)
    RelativeLayout mClearCache;
    @BindView(R.id.text_my_setting_clear_cache_size)
    TextView mCacheSize;
    @BindView(R.id.ll_picture_quality)
    LinearLayout mPictureQuality;
    @BindView(R.id.linear_my_setting_privacy)
    LinearLayout mPrivacy;
    @BindView(R.id.linear_my_setting_update)
    LinearLayout mUpData;
    @BindView(R.id.text_my_setting_log_out)
    TextView mLogOut;
    private String userPhone,userIcon,userName;
    private Dialog dialog;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_setting;
    }

    @Override
    protected int getTitleBarId() {
        return R.id.tb_mine_setting_title;
    }


    @Override
    protected void initView() {
        dialog = ProgressDialog.createLoadingDialog(context,"清理中...");
        try {
            mCacheSize.setText(DataCleanManager.getTotalCacheSize(context));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void initData() {
        userName = SPUtil.getString(context,"userName");
        userIcon = SPUtil.getString(context,"userIcon");
        userPhone = SPUtil.getString(context,"userPhone");
    }

    @OnClick({R.id.ll_account_security, R.id.ll_binding_phone, R.id.ll_feedback, R.id.linear_my_setting_clear_cache,
            R.id.ll_picture_quality, R.id.linear_my_setting_privacy, R.id.linear_my_setting_update,
            R.id.text_my_setting_log_out})
    public void onClickView(View view){
        Bundle bundle = new Bundle();
        switch (view.getId()){
            case R.id.ll_account_security:
                MyApplication.openActivity(context,AccountSecurityActivity.class);
                break;
            case R.id.ll_binding_phone:
                MyApplication.openActivity(context,BindingPhoneActivity.class);
                break;
            case R.id.ll_feedback:
                MyApplication.openActivity(context,FeedbackActivity.class);
                break;
            case R.id.linear_my_setting_clear_cache:
                dialog.show();
                new Thread(new clearCache()).start();
                break;
            case R.id.ll_picture_quality:
                MyApplication.openActivity(context,PictureQualityActivity.class);
                break;
            case R.id.linear_my_setting_privacy:
                bundle.putString("title","隐私条款");
                bundle.putString("type","1");
                MyApplication.openActivity(context,SettingPrivacyActivity.class,bundle);
                break;
            case R.id.linear_my_setting_update:
                MyApplication.openActivity(context, UpdateActivity.class);
                break;
            case R.id.text_my_setting_log_out:
                LogOutDialog dialog = new LogOutDialog(context, R.string.log_out, new LogOutDialog.OnSureBtnClickListener() {
                    @Override
                    public void sure() {
                        SPUtil.putString(context, "uid", "");//用户ID
                        SPUtil.putString(context, "isPhone", "");//手机号码
                        SPUtil.putString(context, "nickName","");
                        SPUtil.putString(context, "userIcon","");
                        SPUtil.putString(context, "thirdType", "");
                        ToastUtils.show("已安全退出账号");
                        finish();
                        Intent intent = new Intent();
                        intent.setAction("com.ifree.uu.mine.changed");
                        getApplicationContext().sendBroadcast(intent);
                        MyApplication.openActivity(context, LoginActivity.class);
                    }
                });
                dialog.show();
                break;
        }
    }

    class clearCache implements Runnable {
        @Override
        public void run() {
            try {
                DataCleanManager.clearAllCache(context);
                Thread.sleep(3000);
                if (DataCleanManager.getTotalCacheSize(context).startsWith("O")) {
                    handler.sendEmptyMessage(0);
                }
            } catch (Exception e) {
                return;
            }
        }
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    dialog.dismiss();
                    ToastUtils.show("清理完成");
                    try {
                        mCacheSize.setText(DataCleanManager.getTotalCacheSize(context));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    break;
            }
        }
    };


    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Log.d("plat", "platform" + platform);
            ToastUtils.show("分享成功啦");
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            ToastUtils.show("分享失败啦！");
            if (t != null) {
                Log.d("throw", "throw:" + t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            ToastUtils.show("分享已取消了");
        }
    };

}
