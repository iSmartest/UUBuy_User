package com.ifree.uu.uubuy.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.app.MyApplication;
import com.ifree.uu.uubuy.listener.LoginCompleteListener;
import com.ifree.uu.uubuy.service.entity.UserInfoEntity;
import com.ifree.uu.uubuy.service.presenter.CodeLoginPresenter;
import com.ifree.uu.uubuy.service.presenter.SendCodePresenter;
import com.ifree.uu.uubuy.service.view.UserInfoView;
import com.ifree.uu.uubuy.ui.activity.ForgetPwdActivity;
import com.ifree.uu.uubuy.ui.activity.RegisterActivity;
import com.ifree.uu.uubuy.ui.base.BaseFragment;
import com.ifree.uu.uubuy.uitls.SPUtil;
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
public class CodeLoginFragment extends BaseFragment {
    private SendCodePresenter mSendCodePresenter;
    private CodeLoginPresenter mCodeLoginPresenter;
    @BindView(R.id.tv_edit_user_phone)
    EditText tvUserPhone;
    @BindView(R.id.tv_edit_verification_code)
    EditText tvEditCode;
    @BindView(R.id.tv_send_verification_code)
    TextView tvSendCode;
    @BindView(R.id.tv_code_login)
    TextView tvCodeLogin;
    @BindView(R.id.tv_go_register)
    TextView mRegister;
    @BindView(R.id.tv_forget_password)
    TextView mForgetPassword;
    private String mCode ="";
    private String sessionId = "";
    private LoginCompleteListener completeListener;
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        completeListener = (LoginCompleteListener) getActivity();
    }
    @Override
    protected int getLayout() {
        return R.layout.fragment_code_login;
    }

    @Override
    protected void initView() {
        mSendCodePresenter = new SendCodePresenter(context);
        mCodeLoginPresenter = new CodeLoginPresenter(context);
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.tv_send_verification_code,R.id.tv_code_login,R.id.tv_go_register,R.id.tv_forget_password})
    public void onClickView(View view){
        String userPhone = tvUserPhone.getText().toString().trim();
        switch (view.getId()){
            case R.id.tv_send_verification_code:
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
                TimerUtil mTimerUtil = new TimerUtil(tvSendCode);
                mTimerUtil.timers();
                sendSMS(userPhone);
                break;
            case R.id.tv_code_login:
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
                break;
            case R.id.tv_go_register:
                MyApplication.openActivity(context,RegisterActivity.class);
                break;
            case R.id.tv_forget_password:
                MyApplication.openActivity(context,ForgetPwdActivity.class);
                break;
        }
    }

    private void codeLogin(String userPhone, String mCode) {
        mCodeLoginPresenter.onCreate();
        mCodeLoginPresenter.attachView(mCodeLoginView);
        mCodeLoginPresenter.getSearchPhoneCodeLogin(userPhone,mCode,sessionId,"登录中...");
    }


    private void sendSMS(String userPhone) {
        mSendCodePresenter.onCreate();
        mSendCodePresenter.attachView(mSendCodeView);
        mSendCodePresenter.getSearchSendCode(userPhone,"3","获取中...");
    }

    private UserInfoView mSendCodeView = new UserInfoView() {
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

    private UserInfoView mCodeLoginView = new UserInfoView() {
        @Override
        public void onSuccess(UserInfoEntity mUserInfoEntity) {
            if (mUserInfoEntity.getResultCode().equals("1")){
                ToastUtils.makeText(context,mUserInfoEntity.getMsg());
                return;
            }
            SPUtil.putString(context,"uid",mUserInfoEntity.getData().getUid());
            SPUtil.putString(context,"isPhone",mUserInfoEntity.getData().getIsPhone());
            completeListener.SendMessageValue();
        }

        @Override
        public void onError(String result) {
            ToastUtils.makeText(context,result);
        }
    };
}
