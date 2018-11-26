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

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.app.MyApplication;
import com.ifree.uu.uubuy.custom.rounded.RoundedImageView;
import com.ifree.uu.uubuy.dialog.LogOutDialog;
import com.ifree.uu.uubuy.dialog.ProgressDialog;
import com.ifree.uu.uubuy.ui.base.BaseActivity;
import com.ifree.uu.uubuy.uitls.DataCleanManager;
import com.ifree.uu.uubuy.uitls.GlideImageLoader;
import com.ifree.uu.uubuy.uitls.SPUtil;
import com.ifree.uu.uubuy.uitls.ToastUtils;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.shareboard.ShareBoardConfig;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/22.
 * Description:
 */
public class MySettingActivity extends BaseActivity {
    @BindView(R.id.my_iconImgview)
    RoundedImageView mIcon;
    @BindView(R.id.my_icon_Name)
    TextView mName;
    @BindView(R.id.myPhones)
    TextView mPhone;
    @BindView(R.id.ll_account_security)
    LinearLayout mSecurity;
    @BindView(R.id.ll_binding_phone)
    LinearLayout mBindingPhone;
    @BindView(R.id.ll_common_question)
    LinearLayout mCommonQuestion;
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
    @BindView(R.id.ll_share)
    LinearLayout mShare;
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
    protected void loadData() {
        userName = SPUtil.getString(context,"userName");
        userIcon = SPUtil.getString(context,"userIcon");
        userPhone = SPUtil.getString(context,"userPhone");
        GlideImageLoader.headerImageLoader(context,userIcon,mIcon);
        mName.setText(userName);
        mPhone.setText("账户：" + userPhone);
    }

    @Override
    protected void initView() {
        hideBack(5);
        setTitleText("设置");
        dialog = ProgressDialog.createLoadingDialog(context,"清理中...");
        try {
            mCacheSize.setText(DataCleanManager.getTotalCacheSize(context));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @OnClick({R.id.ll_account_security, R.id.ll_binding_phone, R.id.ll_common_question, R.id.ll_feedback,
            R.id.linear_my_setting_clear_cache,R.id.ll_share,R.id.ll_picture_quality, R.id.linear_my_setting_privacy,
            R.id.linear_my_setting_update, R.id.text_my_setting_log_out})
    public void onClickView(View view){
        Bundle bundle = new Bundle();
        switch (view.getId()){
            case R.id.ll_account_security:
                MyApplication.openActivity(context,AccountSecurityActivity.class);
                break;
            case R.id.ll_binding_phone:
                MyApplication.openActivity(context,BindingPhoneActivity.class);
                break;
            case R.id.ll_common_question:

                bundle.putString("title","常见问题");
                bundle.putString("type","1");
                MyApplication.openActivity(context,ProtocolActivity.class,bundle);
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
            case R.id.ll_share:
                ShareBoardConfig config = new ShareBoardConfig();
                ShareAction mShareAction = new ShareAction(this);
                config.setMenuItemBackgroundShape(ShareBoardConfig.BG_SHAPE_CIRCULAR);// 圆角背景
                config.setCancelButtonVisibility(false);
                config.setTitleVisibility(true);
                config.setTitleText("— 分享到 —");
                mShareAction.setDisplayList(SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE)
                        .withTitle("您的好友"+SPUtil.getString(context,"nickName") +"邀请您加入【UU购】")
                        .withText("赶快下载体验【UU购】APP！")
                        .withMedia(new UMImage(context, R.mipmap.app_icon))
                        .withTargetUrl("http://m.uugood.cn:8081/download.html")
                        .setCallback(umShareListener)
                        .open(config);
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
                        ToastUtils.makeText(context, "已安全退出账号");
                        SPUtil.putString(context, "nickName","");
                        SPUtil.putString(context, "userIcon","");
                        SPUtil.putString(context, "thirdType", "");
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
                    ToastUtils.makeText(context, "清理完成");
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
            ToastUtils.makeText(context, "分享成功啦");
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            ToastUtils.makeText(context, "分享失败啦！");
            if (t != null) {
                Log.d("throw", "throw:" + t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            ToastUtils.makeText(context, "分享已取消了");
        }
    };

}
