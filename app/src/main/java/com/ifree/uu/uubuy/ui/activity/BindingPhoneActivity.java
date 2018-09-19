package com.ifree.uu.uubuy.ui.activity;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.service.entity.UserInfoEntity;
import com.ifree.uu.uubuy.service.presenter.BindPhonePresenter;
import com.ifree.uu.uubuy.service.presenter.SendCodePresenter;
import com.ifree.uu.uubuy.service.view.UserInfoView;
import com.ifree.uu.uubuy.ui.base.BaseActivity;
import com.ifree.uu.uubuy.uitls.StringUtils;
import com.ifree.uu.uubuy.uitls.TimerUtil;
import com.ifree.uu.uubuy.uitls.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2018/9/19 0019
 * Description:
 */
public class BindingPhoneActivity extends BaseActivity {
    private SendCodePresenter mSendCodePresenter;
    private BindPhonePresenter mBindPhonePresenter;
    @BindView(R.id.edit_bind_phone)
    EditText mBindPhone;
    @BindView(R.id.edit_bind_code)
    EditText mBindCode;
    @BindView(R.id.tv_bind_code)
    TextView mSendCode;
    @BindView(R.id.edit_bind_password)
    EditText mBindPassword;
    @BindView(R.id.tv_bind_sure)
    EditText mBindSure;
    private String mCode ="";
    private String sessionId = "";
    @Override
    protected int getLayoutId() {
        return R.layout.activity_binding_phone;
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initView() {
        mSendCodePresenter = new SendCodePresenter(context);
        mBindPhonePresenter = new BindPhonePresenter(context);
    }

    @OnClick({R.id.tv_bind_code,R.id.tv_bind_sure})
    public void onClickView(View view) {
        String userPhone = mBindPhone.getText().toString().trim();//电话号码
        switch (view.getId()) {
            case R.id.tv_bind_code:
                //验证电话号码不能为空
                if (TextUtils.isEmpty(userPhone)) {
                    ToastUtils.makeText(context, "请输入手机号！");
                    return;
                }
                //验证手机号是否正确
                if (!StringUtils.isMatchesPhone(userPhone)) {
                    ToastUtils.makeText(context, "你输入的手机号格式不正确");
                    return;
                }
                TimerUtil mTimerUtil = new TimerUtil(mSendCode);
                mTimerUtil.timers();
                sendSMS(userPhone);
                break;
            case R.id.tv_bind_sure:
                if (TextUtils.isEmpty(userPhone)) {
                    ToastUtils.makeText(context, "电话号码不能为空");
                    return;
                }
                if (!StringUtils.isMatchesPhone(userPhone)) {
                    ToastUtils.makeText(context, "电话号码不正确，请核对后重新输入");
                    return;
                }
                String inviteCode = mBindCode.getText().toString().trim();
                if (TextUtils.isEmpty(inviteCode)) {
                    ToastUtils.makeText(context, "验证码不能为空");
                    return;
                }
                //验证验证码是否正确
                if (!inviteCode.equals(mCode)) {
                    ToastUtils.makeText(context, "验证码不正确");
                    return;
                }
                //验证密码不能为空
                String password = mBindPassword.getText().toString().trim();
                if (TextUtils.isEmpty(password)) {
                    ToastUtils.makeText(context, "密码不能为空");
                    return;
                }
                sure(userPhone, password, inviteCode);
                break;
        }
    }

    private void sure(String userPhone, String password, String inviteCode) {
        mBindPhonePresenter.onCreate();
        mBindPhonePresenter.attachView(mBindPhoneView);
        mBindPhonePresenter.getSearchBindPhone(userPhone,password,inviteCode,sessionId,uid,"绑定中...");
    }

    private UserInfoView mBindPhoneView = new UserInfoView() {
        @Override
        public void onSuccess(UserInfoEntity mUserInfoEntity) {
            if (mUserInfoEntity.getResultCode().equals("1")){
                ToastUtils.makeText(context,mUserInfoEntity.getMsg());
                return;
            }
            ToastUtils.makeText(context,mUserInfoEntity.getMsg());
            finish();
            Log.i("TAG", "onSuccess: " + mCode);
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
