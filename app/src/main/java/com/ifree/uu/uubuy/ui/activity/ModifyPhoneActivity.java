package com.ifree.uu.uubuy.ui.activity;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.hjq.toast.ToastUtils;
import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.common.CommonActivity;
import com.ifree.uu.uubuy.mvp.modle.UserInfoBean;
import com.ifree.uu.uubuy.mvp.persenter.ModifyPhonePresenter;
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
 * Created by 2018/9/21 0021
 * Description:
 */
public class ModifyPhoneActivity extends CommonActivity {
    private SendCodePresenter mSendCodePresenter;
    private ModifyPhonePresenter mModifyPhonePresenter;
    @BindView(R.id.edit_modify_old_phone)
    EditText mOldPhone;
    @BindView(R.id.edit_modify_new_phone)
    EditText mNewPhone;
    @BindView(R.id.edit_modify_phone_code)
    EditText mPhoneCode;
    @BindView(R.id.tv_modify_phone_code)
    TextView mSendPhoneCode;
    @BindView(R.id.edit_modify_phone_password)
    EditText mPassword;
    @BindView(R.id.tv_modify_phone_sure)
    TextView mSure;
    private String sessionId = "";
    private String mCode ="";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_modify_phone;
    }

    @Override
    protected int getTitleBarId() {
        return R.id.tb_modify_phone_title;
    }

    @Override
    protected void initView() {
        mSendCodePresenter = new SendCodePresenter(context);
        mModifyPhonePresenter = new ModifyPhonePresenter(context);
    }

    @Override
    protected void initData() {
    }

    @OnClick({R.id.tv_modify_phone_code,R.id.tv_modify_phone_sure})
    public void onClickView(View view){
        String userPhone = mNewPhone.getText().toString().trim();//电话号码
        String oldPhone = mOldPhone.getText().toString().trim();//电话号码
        switch (view.getId()){
            case R.id.tv_modify_phone_code:
                //验证电话号码不能为空
                if (TextUtils.isEmpty(userPhone)){
                    ToastUtils.show("请输入新手机号！");
                    return;
                }
                //验证手机号是否正确
                if (!StringUtils.isMatchesPhone(userPhone)){
                    ToastUtils.show("你输入的新手机号格式不正确！");
                    return;
                }
                TimerUtil mTimerUtil = new TimerUtil(mSendPhoneCode);
                mTimerUtil.timers();
                sendSMS(userPhone);
                break;
            case R.id.tv_modify_phone_sure:
                if (TextUtils.isEmpty(oldPhone)) {
                    ToastUtils.show("原手机号不能为空！");
                    return;
                }
                if (!StringUtils.isMatchesPhone(oldPhone)) {
                    ToastUtils.show("原手机号不正确，请核对后重新输入！");
                    return;
                }
                if (TextUtils.isEmpty(userPhone)) {
                    ToastUtils.show("新手机号不能为空！");
                    return;
                }
                if (!StringUtils.isMatchesPhone(userPhone)) {
                    ToastUtils.show("新手机号不正确，请核对后重新输入");
                    return;
                }
                
                String inviteCode = mPhoneCode.getText().toString().trim();
                if (TextUtils.isEmpty(inviteCode)){
                    ToastUtils.show("验证码不能为空");
                    return;
                }
                String password = mPassword.getText().toString().trim();
                if (TextUtils.isEmpty(password)) {
                    ToastUtils.show("密码不能为空");
                    return;
                }
                modifyPhone(userPhone,password,inviteCode);
                break;
        }
    }

    private void modifyPhone(String userPhone, String password, String inviteCode) {
        mModifyPhonePresenter.onCreate();
        mModifyPhonePresenter.attachView(mModifyPhoneView);
        mModifyPhonePresenter.getSearchModifyPhone(userPhone,password,inviteCode,sessionId,SPUtil.getUid(context),"更改中...");
    }

    private ProjectView<UserInfoBean> mModifyPhoneView = new ProjectView<UserInfoBean>() {
        @Override
        public void onSuccess(UserInfoBean mUserInfoBean) {
            if (mUserInfoBean.getResultCode().equals("1")){
                ToastUtils.show(mUserInfoBean.getMsg());
                return;
            }
            ToastUtils.show(mUserInfoBean.getMsg());
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
        mSendCodePresenter.getSearchSendCode(userPhone,"7","获取中...");
    }

    private ProjectView<UserInfoBean> mSendCodeView = new ProjectView<UserInfoBean>() {
        @Override
        public void onSuccess(UserInfoBean mUserInfoEntity) {
            if (mUserInfoEntity.getResultCode().equals("1")){
                ToastUtils.show(mUserInfoEntity.getMsg());
                return;
            }
            mCode = mUserInfoEntity.getData().getCode();
            sessionId = mUserInfoEntity.getData().getSessionId();
            Log.i("TAG", "onSuccess: " + mCode + sessionId);
        }

        @Override
        public void onError(String result) {
            ToastUtils.show(result);
        }
    };

}
