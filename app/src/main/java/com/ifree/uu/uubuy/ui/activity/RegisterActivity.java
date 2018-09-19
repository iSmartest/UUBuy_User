package com.ifree.uu.uubuy.ui.activity;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.app.MyApplication;
import com.ifree.uu.uubuy.service.entity.UserInfoEntity;
import com.ifree.uu.uubuy.service.presenter.RegisterPresenter;
import com.ifree.uu.uubuy.service.presenter.SendCodePresenter;
import com.ifree.uu.uubuy.service.view.UserInfoView;
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
public class RegisterActivity extends BaseActivity {
    private SendCodePresenter mSendCodePresenter;
    private RegisterPresenter mRegisterPresenter;
    @BindView(R.id.edit_register_phone)
    EditText mRegisterPhone;
    @BindView(R.id.edit_register_code)
    EditText mRegisterCode;
    @BindView(R.id.tv_register_code)
    TextView mSendCode;
    @BindView(R.id.edit_register_password)
    EditText mRegisterPassword;
    @BindView(R.id.edit_register_sure_password)
    EditText mSurePassword;
    @BindView(R.id.tv_register_sure)
    TextView mSureRegister;
    @BindView(R.id.text_register_go_login)
    TextView mGoLogin;
    private String mCode ="";
    private String sessionId = "";
    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initView() {
        hideBack(5);
        setTitleText("注册");
        mSendCodePresenter = new SendCodePresenter(context);
        mRegisterPresenter = new RegisterPresenter(context);
    }

    @OnClick({R.id.tv_register_code,R.id.tv_register_sure,R.id.text_register_go_login})
    public void onClickView(View view){
        String userPhone = mRegisterPhone.getText().toString().trim();//电话号码
        switch (view.getId()){
            case R.id.tv_register_code:
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
                TimerUtil mTimerUtil = new TimerUtil(mSendCode);
                mTimerUtil.timers();
                sendSMS(userPhone);
                break;
            case R.id.tv_register_sure:
                if (TextUtils.isEmpty(userPhone)) {
                    ToastUtils.makeText(context, "电话号码不能为空");
                    return;
                }
                if (!StringUtils.isMatchesPhone(userPhone)) {
                    ToastUtils.makeText(context, "电话号码不正确，请核对后重新输入");
                    return;
                }
                String inviteCode = mRegisterCode.getText().toString().trim();
                if (TextUtils.isEmpty(inviteCode)){
                    ToastUtils.makeText(context, "验证码不能为空");
                    return;
                }
                //验证验证码是否正确
                if (!inviteCode.equals(mCode)) {
                    ToastUtils.makeText(context, "验证码不正确");
                    return;
                }
                //验证密码不能为空
                String password = mRegisterPassword.getText().toString().trim();
                if (TextUtils.isEmpty(password)) {
                    ToastUtils.makeText(context, "密码不能为空");
                    return;
                }
                //验证确认密码不能为空
                String confirmPassword= mSurePassword.getText().toString().trim();
                if (TextUtils.isEmpty(confirmPassword)) {
                    ToastUtils.makeText(context, "确认密码不能为空");
                    return;
                }
                //验证密码和确认密码是否相同
                if (!password.equals(confirmPassword)) {
                    ToastUtils.makeText(context, "两次输入密码不一致");
                    return;
                }
                passWordLogin(userPhone,password,inviteCode);
                break;
            case R.id.text_register_go_login:
                finish();
                break;
        }
    }

    private void passWordLogin(String userPhone, String password,String inviteCode) {
        mRegisterPresenter.onCreate();
        mRegisterPresenter.attachView(mRegisterView);
        mRegisterPresenter.getSearchRegister(userPhone,password,inviteCode,sessionId,"提交中...");
    }

    private UserInfoView mRegisterView = new UserInfoView() {
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
        mSendCodePresenter.getSearchSendCode(userPhone,"2","获取中...");
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
            Log.i("TAG", "onSuccess: " + mCode);
        }

        @Override
        public void onError(String result) {
            ToastUtils.makeText(context,result);
        }
    };
}
