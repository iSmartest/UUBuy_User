package com.ifree.uu.uubuy.ui.activity;


import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.mvp.entity.UserInfoEntity;
import com.ifree.uu.uubuy.mvp.presenter.ForgetPasswordPresenter;
import com.ifree.uu.uubuy.mvp.presenter.SendCodePresenter;
import com.ifree.uu.uubuy.mvp.view.ProjectView;
import com.ifree.uu.uubuy.ui.base.BaseActivity;
import com.ifree.uu.uubuy.uitls.StringUtils;
import com.ifree.uu.uubuy.uitls.TimerUtil;
import com.ifree.uu.uubuy.uitls.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/23.
 * Description:
 */

public class ForgetPwdActivity extends BaseActivity{
    private SendCodePresenter mSendCodePresenter;
    private ForgetPasswordPresenter mForgetPasswordPresenter;
    @BindView(R.id.edit_forget_phone)
    EditText mForgetPhone;
    @BindView(R.id.edit_forget_code)
    EditText mForgetCode;
    @BindView(R.id.tv_forget_code)
    TextView mSendCode;
    @BindView(R.id.edit_forget_password)
    EditText mForgetPassword;
    @BindView(R.id.edit_forget_sure_password)
    EditText mSurePassword;
    @BindView(R.id.tv_forget_sure)
    TextView mSure;
    private String mCode ="";
    private String sessionId = "";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_forget_password;
    }

    @Override
    protected void loadData() {

    }
    @Override
    protected void initView() {
        hideBack(5);
        setTitleText("找回密码");
        mSendCodePresenter = new SendCodePresenter(context);
        mForgetPasswordPresenter = new ForgetPasswordPresenter(context);
    }

    @OnClick({R.id.tv_forget_code,R.id.tv_forget_sure})
    public void onClickView(View v) {
        String userPhone = mForgetPhone.getText().toString().trim();//电话号码
        switch (v.getId()){
            case R.id.tv_forget_code:
                if (TextUtils.isEmpty(userPhone)) {
                    ToastUtils.makeText(context, "电话号码不能为空");
                    return;
                }
                //验证电话号码是否正确
                if (!StringUtils.isMatchesPhone(userPhone)) {
                    ToastUtils.makeText(context, "电话号码不正确，请核对后重新输入");
                    return;
                }
                TimerUtil mTimerUtil = new TimerUtil(mSendCode);
                mTimerUtil.timers();
                sendSMS(userPhone);
                break;
            case R.id.tv_forget_sure:
                if (TextUtils.isEmpty(userPhone)) {
                    ToastUtils.makeText(context, "电话号码不能为空");
                    return;
                }
                //验证电话号码是否正确
                if (!StringUtils.isMatchesPhone(userPhone)) {
                    ToastUtils.makeText(context, "电话号码不正确，请核对后重新输入");
                    return;
                }
                //验证验证码不能为空
                String passPin = mForgetCode.getText().toString().trim();
                if (TextUtils.isEmpty(passPin)) {
                    ToastUtils.makeText(context, "验证码不能为空");
                    return;
                }
                //验证验证码是否正确
//                if (!passPin.equals(mCode)) {
//                    ToastUtils.makeText(context, "验证码不正确");
//                    return;
//                }
                //验证密码不能为空
                String password = mForgetPassword.getText().toString().trim();
                if (TextUtils.isEmpty(password)) {
                    ToastUtils.makeText(context, "密码不能为空");
                    return;
                }
                //验证确认密码不能为空
                String confirmPassword = mSurePassword.getText().toString().trim();
                if (TextUtils.isEmpty(confirmPassword)) {
                    ToastUtils.makeText(context, "确认密码不能为空");
                    return;
                }
                //验证密码和确认密码是否相同
                if (!password.equals(confirmPassword)) {
                    ToastUtils.makeText(context, "两次输入密码不一致");
                    return;
                }
                //验证密码格式是否正确
                if (!StringUtils.isPwd(password)) {
                    ToastUtils.makeText(context, "密码格式不正确，请核对后重新输入");
                    return;
                }
                findPassword(userPhone, password,passPin);
                break;
        }
    }

    private void findPassword(String userPhone, String password,String code) {
        mForgetPasswordPresenter.onCreate();
        mForgetPasswordPresenter.attachView(mForgetPasswordView);
        mForgetPasswordPresenter.getSearchForgetPassword(userPhone,password,code,sessionId,"提交中...");
    }

    private ProjectView<UserInfoEntity> mForgetPasswordView = new ProjectView<UserInfoEntity>() {
        @Override
        public void onSuccess(UserInfoEntity mUserInfoEntity) {
            if (mUserInfoEntity.getResultCode().equals("1")){
                ToastUtils.makeText(context,mUserInfoEntity.getMsg());
                return;
            }
            ToastUtils.makeText(context,mUserInfoEntity.getMsg());
            finish();
        }

        @Override
        public void onError(String result) {
            ToastUtils.makeText(context,result);
        }
    };

    private void sendSMS(String userPhone) {
        mSendCodePresenter.onCreate();
        mSendCodePresenter.attachView(mSendCodeView);
        mSendCodePresenter.getSearchSendCode(userPhone,"4","获取中...");
    }

    private ProjectView<UserInfoEntity> mSendCodeView = new ProjectView<UserInfoEntity>() {
        @Override
        public void onSuccess(UserInfoEntity mUserInfoEntity) {
            if (mUserInfoEntity.getResultCode().equals("1")){
                ToastUtils.makeText(context,mUserInfoEntity.getMsg());
                return;
            }
            mCode = mUserInfoEntity.getData().getCode();
            sessionId = mUserInfoEntity.getData().getSessionId();
        }

        @Override
        public void onError(String result) {
            ToastUtils.makeText(context,result);
        }
    };
}


