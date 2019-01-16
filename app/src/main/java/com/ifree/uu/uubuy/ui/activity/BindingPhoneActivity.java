package com.ifree.uu.uubuy.ui.activity;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.hjq.baselibrary.base.BaseActivity;
import com.hjq.toast.ToastUtils;
import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.common.CommonActivity;
import com.ifree.uu.uubuy.mvp.modle.UserInfoBean;
import com.ifree.uu.uubuy.mvp.persenter.BindPhonePresenter;
import com.ifree.uu.uubuy.mvp.persenter.SendCodePresenter;
import com.ifree.uu.uubuy.mvp.view.ProjectView;
import com.ifree.uu.uubuy.utils.SPUtil;
import com.ifree.uu.uubuy.utils.StringUtils;
import com.ifree.uu.uubuy.utils.TimerUtil;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2018/9/19 0019
 * Description:
 */
public class BindingPhoneActivity extends CommonActivity {
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
    TextView mBindSure;
    private String mCode ="";
    private String sessionId = "";
    private String thirdType = "",uid = "";
    @Override
    protected int getLayoutId() {
        return R.layout.activity_binding_phone;
    }

    @Override
    protected int getTitleBarId() {
        return R.id.tb_bind_phone_title;
    }

    @Override
    protected void initView() {
        mSendCodePresenter = new SendCodePresenter(context);
        mBindPhonePresenter = new BindPhonePresenter(context);
    }

    @Override
    protected void initData() {
        thirdType = getIntent().getStringExtra("thirdType");
        uid = getIntent().getStringExtra("uid");
    }

    @OnClick({R.id.tv_bind_code,R.id.tv_bind_sure})
    public void onClickView(View view) {
        String userPhone = mBindPhone.getText().toString().trim();//电话号码
        switch (view.getId()) {
            case R.id.tv_bind_code:
                //验证电话号码不能为空
                if (TextUtils.isEmpty(userPhone)) {
                    ToastUtils.show("请输入手机号！");
                    return;
                }
                //验证手机号是否正确
                if (!StringUtils.isMatchesPhone(userPhone)) {
                    ToastUtils.show("你输入的手机号格式不正确！");
                    return;
                }
                TimerUtil mTimerUtil = new TimerUtil(mSendCode);
                mTimerUtil.timers();
                sendSMS(userPhone);
                break;
            case R.id.tv_bind_sure:
                if (TextUtils.isEmpty(userPhone)) {
                    ToastUtils.show("电话号码不能为空！");
                    return;
                }
                if (!StringUtils.isMatchesPhone(userPhone)) {
                    ToastUtils.show("电话号码不正确，请核对后重新输入！");
                    return;
                }
                String inviteCode = mBindCode.getText().toString().trim();
                if (TextUtils.isEmpty(inviteCode)) {
                    ToastUtils.show("验证码不能为空");
                    return;
                }
//                //验证验证码是否正确
//                if (!inviteCode.equals(mCode)) {
//                    ToastUtils.makeText(context, "验证码不正确");
//                    return;
//                }
                //验证密码不能为空
                String password = mBindPassword.getText().toString().trim();
                if (TextUtils.isEmpty(password)) {
                    ToastUtils.show("密码不能为空");
                    return;
                }
                sure(userPhone, password, inviteCode);
                break;

        }
    }

    private void sure(String userPhone, String password, String inviteCode) {
        mBindPhonePresenter.onCreate();
        mBindPhonePresenter.attachView(mBindPhoneView);
        mBindPhonePresenter.getSearchBindPhone(userPhone,password,inviteCode,sessionId,uid,thirdType,"绑定中...");
    }

    private ProjectView<UserInfoBean> mBindPhoneView = new ProjectView<UserInfoBean>() {
        @Override
        public void onSuccess(UserInfoBean mUserInfoBean) {
            if (mUserInfoBean.getResultCode().equals("1")){
                ToastUtils.show(mUserInfoBean.getMsg());
                return;
            }
            ToastUtils.show(mUserInfoBean.getMsg());
            SPUtil.putString(context, "isPhone", "1");
            SPUtil.putString(context, "uid", mUserInfoBean.getData().getId());
            SPUtil.putString(context, "userPhone", mUserInfoBean.getData().getUserPhone());
            setResult(Activity.RESULT_OK);
            finish();
        }

        @Override
        public void onError(String result) {
            ToastUtils.show(result);
        }
    };


    private void sendSMS(String userPhone) {
        mSendCodePresenter.onCreate();
        mSendCodePresenter.attachView(mSendCodeView);
        mSendCodePresenter.getSearchSendCode(userPhone,"5","获取中...");
    }

    private ProjectView<UserInfoBean> mSendCodeView = new ProjectView<UserInfoBean>() {
        @Override
        public void onSuccess(UserInfoBean mUserInfoBean) {
            if (mUserInfoBean.getResultCode().equals("1")){
                ToastUtils.show(mUserInfoBean.getMsg());
                return;
            }
            mCode = mUserInfoBean.getData().getCode();
            sessionId = mUserInfoBean.getData().getSessionId();
            Log.i("TAG", "onSuccess: " + mCode);
        }

        @Override
        public void onError(String result) {
            ToastUtils.show(result);
            }
    };
}
