package com.ifree.uu.uubuy.ui.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.hjq.toast.ToastUtils;
import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.common.CommonActivity;
import com.ifree.uu.uubuy.mvp.modle.UserInfoBean;
import com.ifree.uu.uubuy.mvp.persenter.ModifyPasswordPresenter;
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
public class ModifyPasswordActivity extends CommonActivity {
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
    protected int getTitleBarId() {
        return R.id.tb_modify_password_title;
    }

    @Override
    protected void initView() {
        mSendCodePresenter = new SendCodePresenter(context);
        mModifyPasswordPresenter = new ModifyPasswordPresenter(context);
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.edit_modify_password_code, R.id.tv_modify_sure})
    public void onClickView(View v) {
        String userPhone = mPhone.getText().toString().trim();//电话号码
        switch (v.getId()) {
            case R.id.edit_modify_password_code:
                if (TextUtils.isEmpty(userPhone)) {
                    ToastUtils.show("电话号码不能为空");
                    return;
                }
                //验证电话号码是否正确
                if (!StringUtils.isMatchesPhone(userPhone)) {
                    ToastUtils.show("电话号码不正确，请核对后重新输入");
                    return;
                }
                TimerUtil mTimerUtil = new TimerUtil(mSendCode);
                mTimerUtil.timers();
                sendSMS(userPhone);
                break;
            case R.id.tv_modify_sure:
                if (TextUtils.isEmpty(userPhone)) {
                    ToastUtils.show("电话号码不能为空");
                    return;
                }
                if (!StringUtils.isMatchesPhone(userPhone)) {
                    ToastUtils.show("电话号码不正确，请核对后重新输入");
                    return;
                }
                String inviteCode = mEditCode.getText().toString().trim();
                if (TextUtils.isEmpty(inviteCode)) {
                    ToastUtils.show("验证码不能为空");
                    return;
                }
                String oldPassword = mOldPassword.getText().toString().trim();
                String newPassword = mNewPassword.getText().toString().trim();
                if (TextUtils.isEmpty(oldPassword)) {
                    ToastUtils.show("请输入原密码！");
                    return;
                }

                if (TextUtils.isEmpty(newPassword)) {
                    ToastUtils.show("请输入新密码！");
                    return;
                }

                //验证密码格式是否正确
                if (newPassword.length()<6 || newPassword.length()>16) {
                    ToastUtils.show("密码为6-16位组成");
                    return;
                }
                modifyPassword(userPhone,inviteCode,oldPassword,newPassword);
                break;
        }
    }

    private void modifyPassword(String userPhone, String inviteCode, String oldPassword, String newPassword) {
        mModifyPasswordPresenter.onCreate();
        mModifyPasswordPresenter.attachView(mModifyPasswordView);
        mModifyPasswordPresenter.getSearchModifyPassword(userPhone,oldPassword,newPassword,inviteCode,sessionId,SPUtil.getUid(context),"更改中...");

    }

    private ProjectView<UserInfoBean> mModifyPasswordView = new ProjectView<UserInfoBean>() {
        @Override
        public void onSuccess(UserInfoBean mUserInfoBean) {
            if (mUserInfoBean.getResultCode().equals("1")){
                ToastUtils.show(mUserInfoBean.getMsg());
                return;
            }
            ToastUtils.show(mUserInfoBean.getMsg());
        }

        @Override
        public void onError(String result) {
            ToastUtils.show(result);
        }
    };

    private void sendSMS(String userPhone) {
        mSendCodePresenter.onCreate();
        mSendCodePresenter.attachView(mSendCodeView);
        mSendCodePresenter.getSearchSendCode(userPhone, "6", "获取中...");
    }

    private ProjectView<UserInfoBean> mSendCodeView = new ProjectView<UserInfoBean>() {
        @Override
        public void onSuccess(UserInfoBean mUserInfoBean) {
            if (mUserInfoBean.getResultCode().equals("1")) {
                ToastUtils.show(mUserInfoBean.getMsg());
                return;
            }
            mCode = mUserInfoBean.getData().getCode();
            sessionId = mUserInfoBean.getData().getSessionId();
        }

        @Override
        public void onError(String result) {
            ToastUtils.show(result);
        }
    };
}
