package com.ifree.uu.uubuy.ui.fragment;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.app.MyApplication;
import com.ifree.uu.uubuy.listener.LoginCompleteListener;
import com.ifree.uu.uubuy.service.entity.UserInfoEntity;
import com.ifree.uu.uubuy.service.presenter.PasswordLoginPresenter;
import com.ifree.uu.uubuy.service.view.UserInfoView;
import com.ifree.uu.uubuy.ui.activity.ForgetPwdActivity;
import com.ifree.uu.uubuy.ui.activity.RegisterActivity;
import com.ifree.uu.uubuy.ui.base.BaseFragment;
import com.ifree.uu.uubuy.uitls.SPUtil;
import com.ifree.uu.uubuy.uitls.StringUtils;
import com.ifree.uu.uubuy.uitls.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/23.
 * Description:
 */
public class PasswordFragment extends BaseFragment {
    private PasswordLoginPresenter mPasswordLoginPresenter;
    @BindView(R.id.edit_user_phone)
    EditText mUserPhone;
    @BindView(R.id.edit_user_password)
    EditText mUserPassword;
    @BindView(R.id.tv_password_login)
    TextView mPasswordLogin;
    @BindView(R.id.tv_go_register)
    TextView mRegister;
    @BindView(R.id.tv_password_forget_password)
    TextView mForgetPassword;
    private LoginCompleteListener completeListener;
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        completeListener = (LoginCompleteListener) getActivity();
    }
    @Override
    protected int getLayout() {
        return R.layout.fragment_password_login;
    }

    @Override
    protected void initView() {
        mPasswordLoginPresenter = new PasswordLoginPresenter(context);
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.tv_password_login,R.id.tv_go_register})
    public void onClickView(View view){
        switch (view.getId()){
            case R.id.tv_password_login:
                String userPhone = mUserPhone.getText().toString().trim();//电话号码
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
                passWordLogin(userPhone,password);
                break;
            case R.id.tv_password_forget_password:
                MyApplication.openActivity(context,ForgetPwdActivity.class);
                break;
            case R.id.tv_go_register:
                MyApplication.openActivity(context,RegisterActivity.class);
                break;
        }
    }

    private void passWordLogin(String userPhone, String password) {
        mPasswordLoginPresenter.onCreate();
        mPasswordLoginPresenter.attachView(mPasswordLoginView);
        mPasswordLoginPresenter.getSearchPassWordLogin(userPhone,password,"登录中...");
    }

    private UserInfoView mPasswordLoginView = new UserInfoView() {
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
