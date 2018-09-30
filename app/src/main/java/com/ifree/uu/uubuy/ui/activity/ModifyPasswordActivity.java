package com.ifree.uu.uubuy.ui.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.service.entity.UserInfoEntity;
import com.ifree.uu.uubuy.service.presenter.ModifyPasswordPresenter;
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
public class ModifyPasswordActivity extends BaseActivity {
    private SendCodePresenter mSendCodePresenter;
    private ModifyPasswordPresenter mModifyPasswordPresenter;
    @BindView(R.id.edit_modify_password_phone)
    EditText mPhone;
    @BindView(R.id.edit_modify_password_code)
    EditText mEditCode;
    @BindView(R.id.tv_modify_password_code)
    TextView mSendCode;
    @BindView(R.id.edit_modify_old_password)
    TextView mOldPassword;
    @BindView(R.id.edit_modify_new_password)
    TextView mNewPassword;
    @BindView(R.id.tv_modify_sure)
    TextView mSure;
    private String mCode = "";
    private String sessionId = "";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_modify_password;
    }

    @Override
    protected void initView() {
        hideBack(5);
        setTitleText("更改登录密码");
    }

    @Override
    protected void loadData() {
        mSendCodePresenter = new SendCodePresenter(context);
        mModifyPasswordPresenter = new ModifyPasswordPresenter(context);
    }

    @OnClick({R.id.edit_modify_password_code, R.id.tv_modify_sure})
    public void onClickView(View v) {
        String userPhone = mPhone.getText().toString().trim();//电话号码
        switch (v.getId()) {
            case R.id.edit_modify_password_code:
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
            case R.id.tv_modify_sure:
                if (TextUtils.isEmpty(userPhone)) {
                    ToastUtils.makeText(context, "电话号码不能为空");
                    return;
                }
                if (!StringUtils.isMatchesPhone(userPhone)) {
                    ToastUtils.makeText(context, "电话号码不正确，请核对后重新输入");
                    return;
                }
                String inviteCode = mEditCode.getText().toString().trim();
                if (TextUtils.isEmpty(inviteCode)) {
                    ToastUtils.makeText(context, "验证码不能为空");
                    return;
                }
                String oldPassword = mOldPassword.getText().toString().trim();
                String newPassword = mNewPassword.getText().toString().trim();
                if (TextUtils.isEmpty(oldPassword)) {
                    ToastUtils.makeText(context, "请输入原密码！");
                    return;
                }
                if (TextUtils.isEmpty(newPassword)) {
                    ToastUtils.makeText(context, "请输入新密码！");
                    return;
                }
                modifyPassword(userPhone,inviteCode,oldPassword,newPassword);
                break;
        }
    }

    private void modifyPassword(String userPhone, String inviteCode, String oldPassword, String newPassword) {
        mModifyPasswordPresenter.onCreate();
        mModifyPasswordPresenter.attachView(mModifyPasswordView);
        mModifyPasswordPresenter.getSearchModifyPassword(userPhone,oldPassword,newPassword,inviteCode,sessionId,uid,"更改中...");

    }

    private ProjectView<UserInfoEntity> mModifyPasswordView = new ProjectView<UserInfoEntity>() {
        @Override
        public void onSuccess(UserInfoEntity mUserInfoEntity) {
            if (mUserInfoEntity.getResultCode().equals("1")){
                ToastUtils.makeText(context,mUserInfoEntity.getMsg());
                return;
            }
            ToastUtils.makeText(context,mUserInfoEntity.getMsg());
        }

        @Override
        public void onError(String result) {
            ToastUtils.makeText(context,result);
        }
    };

    private void sendSMS(String userPhone) {
        mSendCodePresenter.onCreate();
        mSendCodePresenter.attachView(mSendCodeView);
        mSendCodePresenter.getSearchSendCode(userPhone, "6", "获取中...");
    }

    private ProjectView<UserInfoEntity> mSendCodeView = new ProjectView<UserInfoEntity>() {
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
}
