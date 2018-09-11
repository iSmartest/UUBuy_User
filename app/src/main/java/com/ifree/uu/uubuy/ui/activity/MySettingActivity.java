package com.ifree.uu.uubuy.ui.activity;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.custom.rounded.RoundedImageView;
import com.ifree.uu.uubuy.ui.base.BaseActivity;
import com.ifree.uu.uubuy.uitls.GlideImageLoader;
import com.ifree.uu.uubuy.uitls.SPUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/22.
 * Description:
 */
public class MySettingActivity extends BaseActivity {
    @BindView(R.id.my_iconImgview)
    RoundedImageView mIcon;
    @BindView(R.id.my_icon_Name)
    TextView mName;
    @BindView(R.id.myPhones)
    TextView mPhone;
    @BindView(R.id.ll_account_security)
    LinearLayout mSecurity;
    @BindView(R.id.ll_binding_phone)
    LinearLayout mBindingPhone;
    @BindView(R.id.ll_common_question)
    LinearLayout mCommonQuestion;
    @BindView(R.id.ll_feedback)
    LinearLayout mFeedBack;
    @BindView(R.id.linear_my_setting_clear_cache)
    RelativeLayout mClearCache;
    @BindView(R.id.ll_share)
    LinearLayout mShare;
    @BindView(R.id.linear_my_setting_update)
    LinearLayout mUpdata;
    @BindView(R.id.text_my_setting_log_out)
    TextView mLogOut;
    private String userPhone,userIcon,userName;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_setting;
    }

    @Override
    protected void loadData() {
        userName = SPUtil.getString(context,"userName");
        userIcon = SPUtil.getString(context,"userIcon");
        userPhone = SPUtil.getString(context,"userPhone");
        GlideImageLoader.imageLoader(context,userIcon,mIcon);
        mName.setText(userName);
        mPhone.setText("账户：" + userPhone);
    }

    @Override
    protected void initView() {
        hideBack(5);
        setTitleText("设置");
    }
    @OnClick({R.id.ll_account_security, R.id.ll_binding_phone, R.id.ll_common_question, R.id.ll_feedback,
            R.id.linear_my_setting_clear_cache, R.id.text_my_setting_log_out})
    public void onClickView(View view){
        switch (view.getId()){
            case R.id.ll_account_security:

                break;
            case R.id.ll_binding_phone:

                break;
            case R.id.ll_common_question:

                break;
            case R.id.ll_feedback:

                break;
            case R.id.linear_my_setting_clear_cache:

                break;
            case R.id.ll_share:

                break;
            case R.id.linear_my_setting_update:

                break;
            case R.id.text_my_setting_log_out:

                break;
        }
    }
}
