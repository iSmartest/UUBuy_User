package com.ifree.uu.uubuy.ui.activity;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.service.entity.UserInfoEntity;
import com.ifree.uu.uubuy.service.presenter.ModifyPhonePresenter;
import com.ifree.uu.uubuy.service.presenter.SendCodePresenter;
import com.ifree.uu.uubuy.service.view.ProjectView;
import com.ifree.uu.uubuy.ui.base.BaseActivity;
import com.ifree.uu.uubuy.uitls.StringUtils;
import com.ifree.uu.uubuy.uitls.TimerUtil;
import com.ifree.uu.uubuy.uitls.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2018/9/21 0021
 * Description:
 */
public class ModifyPhoneActivity extends BaseActivity {
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
    protected void loadData() {
        mSendCodePresenter = new SendCodePresenter(context);
        mModifyPhonePresenter = new ModifyPhonePresenter(context);
    }

    @Override
    protected void initView() {
        hideBack(5);
        setTitleText("更换手机号");
    }

    @OnClick({R.id.tv_modify_phone_code,R.id.tv_modify_phone_sure})
    public void onClickView(View view){
        String userPhone = mNewPhone.getText().toString().trim();//电话号码
        String oldPhone = mOldPhone.getText().toString().trim();//电话号码
        switch (view.getId()){
            case R.id.tv_modify_phone_code:
                //验证电话号码不能为空
                if (TextUtils.isEmpty(userPhone)){
                    ToastUtils.makeText(context,"请输入新手机号！");
                    return;
                }
                //验证手机号是否正确
                if (!StringUtils.isMatchesPhone(userPhone)){
                    ToastUtils.makeText(context,"你输入的新手机号格式不正确");
                    return;
                }
                TimerUtil mTimerUtil = new TimerUtil(mSendPhoneCode);
                mTimerUtil.timers();
                sendSMS(userPhone);
                break;
            case R.id.tv_modify_phone_sure:
                if (TextUtils.isEmpty(oldPhone)) {
                    ToastUtils.makeText(context, "原手机号不能为空");
                    return;
                }
                if (!StringUtils.isMatchesPhone(oldPhone)) {
                    ToastUtils.makeText(context, "原手机号不正确，请核对后重新输入");
                    return;
                }
                if (TextUtils.isEmpty(userPhone)) {
                    ToastUtils.makeText(context, "新手机号不能为空");
                    return;
                }
                if (!StringUtils.isMatchesPhone(userPhone)) {
                    ToastUtils.makeText(context, "新手机号不正确，请核对后重新输入");
                    return;
                }
                
                String inviteCode = mPhoneCode.getText().toString().trim();
                if (TextUtils.isEmpty(inviteCode)){
                    ToastUtils.makeText(context, "验证码不能为空");
                    return;
                }
                String password = mPassword.getText().toString().trim();
                if (TextUtils.isEmpty(password)) {
                    ToastUtils.makeText(context, "密码不能为空");
                    return;
                }
                modifyPhone(userPhone,password,inviteCode);
                break;
        }
    }

    private void modifyPhone(String userPhone, String password, String inviteCode) {
        mModifyPhonePresenter.onCreate();
        mModifyPhonePresenter.attachView(mModifyPhoneView);
        mModifyPhonePresenter.getSearchModifyPhone(userPhone,password,inviteCode,sessionId,uid,"更改中...");
    }

    private ProjectView<UserInfoEntity> mModifyPhoneView = new ProjectView<UserInfoEntity>() {
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
        mSendCodePresenter.getSearchSendCode(userPhone,"7","获取中...");
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
            Log.i("TAG", "onSuccess: " + mCode + sessionId);
        }

        @Override
        public void onError(String result) {
            ToastUtils.makeText(context,result);
        }
    };

}
