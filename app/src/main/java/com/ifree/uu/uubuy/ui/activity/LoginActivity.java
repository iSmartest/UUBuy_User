package com.ifree.uu.uubuy.ui.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.app.MyApplication;
import com.ifree.uu.uubuy.config.CommonLog;
import com.ifree.uu.uubuy.dialog.ProgressDialog;
import com.ifree.uu.uubuy.listener.LoginCompleteListener;
import com.ifree.uu.uubuy.service.entity.UserInfoEntity;
import com.ifree.uu.uubuy.service.presenter.CodeLoginPresenter;
import com.ifree.uu.uubuy.service.presenter.PasswordLoginPresenter;
import com.ifree.uu.uubuy.service.presenter.SendCodePresenter;
import com.ifree.uu.uubuy.service.presenter.ThirdLoginPresenter;
import com.ifree.uu.uubuy.service.view.UserInfoView;
import com.ifree.uu.uubuy.ui.base.BaseActivity;
import com.ifree.uu.uubuy.uitls.SPUtil;
import com.ifree.uu.uubuy.uitls.StringUtils;
import com.ifree.uu.uubuy.uitls.TimerUtil;
import com.ifree.uu.uubuy.uitls.ToastUtils;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/23.
 * Description:
 */
public class LoginActivity extends BaseActivity {
    private PasswordLoginPresenter mPasswordLoginPresenter;
    private SendCodePresenter mSendCodePresenter;
    private CodeLoginPresenter mCodeLoginPresenter;
    private ThirdLoginPresenter mThirdLoginPresenter;
    @BindView(R.id.ll_code_login)
    LinearLayout llCodeLogin;
    @BindView(R.id.tv_password_login)
    TextView tvPasswordLogin;
    @BindView(R.id.tv_code_login)
    TextView tvCodeLogin;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.edit_user_phone)
    EditText mUserPhone;
    @BindView(R.id.edit_user_password)
    EditText mUserPassword;
    @BindView(R.id.tv_send_verification_code)
    TextView tvSendCode;
    @BindView(R.id.tv_go_register)
    TextView mRegister;
    @BindView(R.id.tv_password_forget_password)
    TextView mForgetPassword;
    @BindView(R.id.tv_edit_verification_code)
    EditText tvEditCode;
    @BindView(R.id.iv_wx_login)
    ImageView mWXLogin;
    @BindView(R.id.iv_qq_login)
    ImageView mQQLogin;


    private Dialog progressDlg;
    private String type = null;
    private String nickName = null, userIcon = null, thirdUid = null, phoneNum = null;
    private UMShareAPI mShareAPI;
    private SHARE_MEDIA platform;
    private String mCode = "";
    private String sessionId = "";
    private int isCodeOrPassword = 1;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void loadData() {
        if (isCodeOrPassword == 1) {
            mUserPassword.setVisibility(View.VISIBLE);
            llCodeLogin.setVisibility(View.GONE);
            tvPasswordLogin.setVisibility(View.GONE);
            tvCodeLogin.setVisibility(View.VISIBLE);
        } else {
            mUserPassword.setVisibility(View.GONE);
            llCodeLogin.setVisibility(View.VISIBLE);
            tvCodeLogin.setVisibility(View.GONE);
            tvPasswordLogin.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void initView() {
        hideBack(5);
        setTitleText("登录");
        mPasswordLoginPresenter = new PasswordLoginPresenter(context);
        mSendCodePresenter = new SendCodePresenter(context);
        mCodeLoginPresenter = new CodeLoginPresenter(context);
        mShareAPI = UMShareAPI.get(context);
        mThirdLoginPresenter = new ThirdLoginPresenter(context);
    }

    @OnClick({R.id.tv_go_register, R.id.tv_password_login, R.id.tv_code_login, R.id.tv_login,
            R.id.tv_send_verification_code,R.id.tv_password_forget_password,R.id.iv_wx_login
    ,R.id.iv_qq_login})
    public void onViewClicked(View view) {
        String userPhone = mUserPhone.getText().toString().trim();//电话号码
        switch (view.getId()) {
            case R.id.tv_go_register:
                MyApplication.openActivity(context, RegisterActivity.class);
                break;
            case R.id.tv_password_login:
                mUserPassword.setVisibility(View.VISIBLE);
                llCodeLogin.setVisibility(View.GONE);
                tvCodeLogin.setVisibility(View.VISIBLE);
                tvPasswordLogin.setVisibility(View.GONE);
                isCodeOrPassword = 1;
                break;
            case R.id.tv_code_login:
                mUserPassword.setVisibility(View.GONE);
                llCodeLogin.setVisibility(View.VISIBLE);
                tvCodeLogin.setVisibility(View.GONE);
                tvPasswordLogin.setVisibility(View.VISIBLE);
                isCodeOrPassword = 0;
                break;
            case R.id.tv_login:
                if (isCodeOrPassword == 1) {
                    if (TextUtils.isEmpty(userPhone)) {
                        ToastUtils.makeText(context, "电话号码不能为空");
                        return;
                    }
                    if (!StringUtils.isMatchesPhone(userPhone)) {
                        ToastUtils.makeText(context, "电话号码不正确，请核对后重新输入");
                        return;
                    }
                    //验证密码是否为空
                    String password = mUserPassword.getText().toString().trim();//密码
                    if (TextUtils.isEmpty(password)) {
                        ToastUtils.makeText(context, "密码不能为空");
                        return;
                    }
                    //验证密码格式是否正确
                    if (!StringUtils.isPwd(password)) {
                        ToastUtils.makeText(context, "密码格式不正确，请核对后重新输入");
                        return;
                    }
                    passWordLogin(userPhone, password);
                }else {
                    String inviteCode = tvEditCode.getText().toString().trim();
                //验证电话号码不能为空
                if (TextUtils.isEmpty(userPhone)){
                    ToastUtils.makeText(context,"请输入手机号！");
                    return;
                }
                //验证手机号是否正确
                if (!StringUtils.isMatchesPhone(userPhone)){
                    ToastUtils.makeText(context,"你输入的手机号格式不正确");
                    return;
                }
                if (TextUtils.isEmpty(inviteCode)){
                    ToastUtils.makeText(context, "验证码不能为空");
                    return;
                }
//                //验证验证码是否正确
//                if (!inviteCode.equals(mCode)) {
//                    ToastUtils.makeText(context, "验证码不正确");
//                    return;
//                }
                codeLogin(userPhone,inviteCode);
                }
                break;
            case R.id.tv_send_verification_code:
               // 验证电话号码不能为空
                if (TextUtils.isEmpty(userPhone)){
                    ToastUtils.makeText(context,"请输入手机号！");
                    return;
                }
                //验证手机号是否正确
                if (!StringUtils.isMatchesPhone(userPhone)){
                    ToastUtils.makeText(context,"你输入的手机号格式不正确");
                    return;
                }
                TimerUtil mTimerUtil = new TimerUtil(tvSendCode);
                mTimerUtil.timers();
                sendSMS(userPhone);
                break;
            case R.id.tv_password_forget_password:
                MyApplication.openActivity(context,ForgetPwdActivity.class);
                break;

            case R.id.iv_wx_login:
                type = "0";
                if (!isWeixinAvilible(context)) {
                    ToastUtils.makeText(context, "请安装微信客户端");
                    return;
                }
                progressDlg = ProgressDialog.createLoadingDialog(context, "登录跳转中...");
                progressDlg.show();
                ToastUtils.makeText(context, "正在跳转微信登录,请稍后...");
                mShareAPI.isInstall(LoginActivity.this, SHARE_MEDIA.WEIXIN);
                mShareAPI.getPlatformInfo(LoginActivity.this, SHARE_MEDIA.WEIXIN, umAuthListener);
                break;
            case R.id.iv_qq_login:
                type = "1";
                progressDlg = ProgressDialog.createLoadingDialog(context, "登录跳转中...");
                progressDlg.show();
                ToastUtils.makeText(context, "正在跳转QQ登录,请稍后...");
                mShareAPI.isInstall(LoginActivity.this, SHARE_MEDIA.QQ);
                mShareAPI.getPlatformInfo(LoginActivity.this, SHARE_MEDIA.QQ, umAuthListener);
                break;
        }
    }


    private void passWordLogin(String userPhone, String password) {
        mPasswordLoginPresenter.onCreate();
        mPasswordLoginPresenter.attachView(mPasswordLoginView);
        mPasswordLoginPresenter.getSearchPassWordLogin(userPhone, password, "登录中...");
    }

    private UserInfoView mPasswordLoginView = new UserInfoView() {
        @Override
        public void onSuccess(UserInfoEntity mUserInfoEntity) {
            if (mUserInfoEntity.getResultCode().equals("1")) {
                ToastUtils.makeText(context, mUserInfoEntity.getMsg());
                return;
            }
            SPUtil.putString(context, "uid", mUserInfoEntity.getData().getUid());
            SPUtil.putString(context, "isPhone", mUserInfoEntity.getData().getIsPhone());
            Intent intent = new Intent();
            intent.setAction("com.ifree.uu.mine.changed");
            getApplicationContext().sendBroadcast(intent);
            finish();
        }

        @Override
        public void onError(String result) {
            ToastUtils.makeText(context, result);
        }
    };


    private void codeLogin(String userPhone, String mCode) {
        mCodeLoginPresenter.onCreate();
        mCodeLoginPresenter.attachView(mCodeLoginView);
        mCodeLoginPresenter.getSearchPhoneCodeLogin(userPhone, mCode, sessionId, "登录中...");
    }

    private UserInfoView mCodeLoginView = new UserInfoView() {
        @Override
        public void onSuccess(UserInfoEntity mUserInfoEntity) {
            if (mUserInfoEntity.getResultCode().equals("1")) {
                ToastUtils.makeText(context, mUserInfoEntity.getMsg());
                return;
            }
            SPUtil.putString(context, "uid", mUserInfoEntity.getData().getUid());
            SPUtil.putString(context, "isPhone", mUserInfoEntity.getData().getIsPhone());
            Intent intent = new Intent();
            intent.setAction("com.ifree.uu.mine.changed");
            getApplicationContext().sendBroadcast(intent);
            finish();
        }

        @Override
        public void onError(String result) {
            ToastUtils.makeText(context, result);
        }
    };


    private void sendSMS(String userPhone) {
        mSendCodePresenter.onCreate();
        mSendCodePresenter.attachView(mSendCodeView);
        mSendCodePresenter.getSearchSendCode(userPhone, "3", "获取中...");
    }

    private UserInfoView mSendCodeView = new UserInfoView() {
        @Override
        public void onSuccess(UserInfoEntity mUserInfoEntity) {
            if (mUserInfoEntity.getResultCode().equals("1")) {
                ToastUtils.makeText(context, mUserInfoEntity.getMsg());
                return;
            }
            mCode = mUserInfoEntity.getData().getCode();
            sessionId = mUserInfoEntity.getData().getSessionId();
        }

        @Override
        public void onError(String result) {
            ToastUtils.makeText(context, result);
        }
    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mShareAPI.onActivityResult(requestCode, resultCode, data);
        if (progressDlg != null && progressDlg.isShowing()) {
            progressDlg.dismiss();
        }
    }

    private UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
            if (SHARE_MEDIA.QQ.equals(share_media)) {
                nickName = map.get("screen_name");//昵称
                userIcon = map.get("profile_image_url");//头像
                thirdUid = map.get("openid");//第三方平台id
            } else if (SHARE_MEDIA.WEIXIN.equals(share_media)) {
                CommonLog.i("onComplete: " + map);
                nickName = map.get("screen_name");//昵称
                userIcon = map.get("profile_image_url");//头像
                thirdUid = map.get("openid");//第三方平台id
                phoneNum = map.get("phoneNum");
                Log.i("TAG", "thirdLogin: " + nickName);
                Log.i("ssss", "onComplete: " + "授权成功了");
            }
            thirdLogin(thirdUid, nickName, userIcon);
        }

        @Override
        public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
            Log.i("TAG", "onError: " + "授权失败");
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media, int i) {
            Log.i("TAG", "onCancel: " + "授权取消");
        }
    };

    private void thirdLogin(String thirdUid, String nickName, String userIcon) {
        mThirdLoginPresenter.onCreate();
        mThirdLoginPresenter.attachView(mThirdLoginView);
        mThirdLoginPresenter.getSearchThirdLogin(thirdUid, nickName, userIcon, type, "登录中...");
    }

    private UserInfoView mThirdLoginView = new UserInfoView() {
        @Override
        public void onSuccess(UserInfoEntity mUserInfoEntity) {
            if (mUserInfoEntity.getResultCode().equals("1")) {
                ToastUtils.makeText(context, mUserInfoEntity.getMsg());
                return;
            }
            SPUtil.putString(context, "uid", mUserInfoEntity.getData().getUid());
            SPUtil.putString(context, "isPhone", mUserInfoEntity.getData().getIsPhone());
            SPUtil.putString(context, "nickName", mUserInfoEntity.getData().getNickName());
            SPUtil.putString(context, "userIcon", mUserInfoEntity.getData().getUserIcon());
            SPUtil.putString(context, "thirdType", type);
            Intent intent = new Intent();
            intent.setAction("com.ifree.uu.mine.changed");
            getApplicationContext().sendBroadcast(intent);
            finish();
        }

        @Override
        public void onError(String result) {
            ToastUtils.makeText(context, result);
        }
    };

    /**
     * 判断 用户是否安装微信客户端
     */
    public static boolean isWeixinAvilible(Context context) {
        final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.tencent.mm")) {
                    return true;
                }
            }
        }
        return false;
    }
}
