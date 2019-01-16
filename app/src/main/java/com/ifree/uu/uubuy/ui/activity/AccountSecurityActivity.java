package com.ifree.uu.uubuy.ui.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;

import com.hjq.toast.ToastUtils;
import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.app.MyApplication;
import com.ifree.uu.uubuy.common.CommonActivity;
import com.ifree.uu.uubuy.utils.SPUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2018/9/21 0021
 * Description:
 */
public class AccountSecurityActivity extends CommonActivity {
    @BindView(R.id.rl_modify_password)
    RelativeLayout mModifyPassword;
    @BindView(R.id.rl_modify_phone)
    RelativeLayout mModifyPhone;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_account_security;
    }

    @Override
    protected int getTitleBarId() {
        return R.id.tb_account_security_title;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.rl_modify_password,R.id.rl_modify_phone})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.rl_modify_password:
                if (TextUtils.isEmpty(SPUtil.getUid(context))){
                    ToastUtils.show("用户未登录，请登录");
                    return;
                }
                if (SPUtil.getString(context,"isPhone").equals("0")){
                    ToastUtils.show("请绑定手机号");
                    MyApplication.openActivity(context,BindingPhoneActivity.class);
                    return;
                }
                MyApplication.openActivity(context,ModifyPasswordActivity.class);
                break;
            case R.id.rl_modify_phone:
                if (TextUtils.isEmpty(SPUtil.getUid(context))){
                    ToastUtils.show("用户未登录，请登录");
                    return;
                }
                if (SPUtil.getString(context,"isPhone").equals("0")){
                    ToastUtils.show("请先绑定手机号");
                    MyApplication.openActivity(context,BindingPhoneActivity.class);
                    return;
                }
                MyApplication.openActivity(context,ModifyPhoneActivity.class);
                break;
        }
    }
}
