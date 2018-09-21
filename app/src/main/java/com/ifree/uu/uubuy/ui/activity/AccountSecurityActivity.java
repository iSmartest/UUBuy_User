package com.ifree.uu.uubuy.ui.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.app.MyApplication;
import com.ifree.uu.uubuy.ui.base.BaseActivity;
import com.ifree.uu.uubuy.uitls.SPUtil;
import com.ifree.uu.uubuy.uitls.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2018/9/21 0021
 * Description:
 */
public class AccountSecurityActivity extends BaseActivity {
    @BindView(R.id.rl_modify_password)
    RelativeLayout mModifyPassword;
    @BindView(R.id.rl_modify_phone)
    RelativeLayout mModifyPhone;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_account_security;
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initView() {
        hideBack(5);
        setTitleText("账户安全");
    }

    @OnClick({R.id.rl_modify_password,R.id.rl_modify_phone})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.rl_modify_password:
                if (TextUtils.isEmpty(uid)){
                    ToastUtils.makeText(context,"用户未登录，请登录");
                    return;
                }
                if (SPUtil.getString(context,"isPhone").equals("0")){
                    ToastUtils.makeText(context,"请绑定手机号");
                    MyApplication.openActivity(context,BindingPhoneActivity.class);
                    return;
                }
                MyApplication.openActivity(context,ModifyPasswordActivity.class);
                break;
            case R.id.rl_modify_phone:
                if (TextUtils.isEmpty(uid)){
                    ToastUtils.makeText(context,"用户未登录，请登录");
                    return;
                }
                if (SPUtil.getString(context,"isPhone").equals("0")){
                    ToastUtils.makeText(context,"请先绑定手机号");
                    MyApplication.openActivity(context,BindingPhoneActivity.class);
                    return;
                }
                MyApplication.openActivity(context,ModifyPhoneActivity.class);
                break;
        }
    }
}
