package com.ifree.uu.uubuy.ui.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.service.entity.UserInfoEntity;
import com.ifree.uu.uubuy.service.presenter.ActivitiesSignUpPresenter;
import com.ifree.uu.uubuy.service.view.ProjectView;
import com.ifree.uu.uubuy.ui.base.BaseActivity;
import com.ifree.uu.uubuy.uitls.SPUtil;
import com.ifree.uu.uubuy.uitls.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/24.
 * Description:
 */
public class EnterForActivitiesActivity extends BaseActivity {
    private ActivitiesSignUpPresenter mActivitiesSignUpPresenter;
    @BindView(R.id.tv_enter_for_sure)
    TextView mEnter;
    @BindView(R.id.edit_real_name)
    EditText mName;
    @BindView(R.id.edit_real_phone)
    EditText mPhone;
    @BindView(R.id.edit_real_id_cart)
    EditText mIdCart;
    private String marketId,type;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_enter_for_activities;
    }


    @Override
    protected void initView() {
        hideBack(5);
        setTitleText("活动报名");
        mIdCart.setText(SPUtil.getString(context,"userIdCard"));
        mPhone.setText(SPUtil.getString(context,"userPhone"));
        mName.setText(SPUtil.getString(context,"userName"));
        marketId = getIntent().getStringExtra("marketId");
        type = getIntent().getStringExtra("type");
        mActivitiesSignUpPresenter = new ActivitiesSignUpPresenter(context);
    }

    @OnClick({R.id.tv_enter_for_sure})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.tv_enter_for_sure:
                String name = mName.getText().toString().trim();
                String phone = mPhone.getText().toString().trim();
                String idCard = mIdCart.getText().toString().trim();
                if (TextUtils.isEmpty(name)){
                    ToastUtils.makeText(context,"姓名不能为空");
                    return;
                }
                if (TextUtils.isEmpty(phone)){
                    ToastUtils.makeText(context,"手机号不可为空");
                    return;
                }
                submit(name,phone,idCard);
                break;
        }
    }

    private void submit(String name, String phone, String idCard) {
        mActivitiesSignUpPresenter.onCreate();
        mActivitiesSignUpPresenter.attachView(mActivitiesSignUpView);
        mActivitiesSignUpPresenter.getSearchActivitiesSignUp(uid,marketId,name,phone,idCard,type,"提交中...");
    }

    private ProjectView<UserInfoEntity> mActivitiesSignUpView = new ProjectView<UserInfoEntity>() {
        @Override
        public void onSuccess(UserInfoEntity mUserInfoEntity) {
            if (mUserInfoEntity.getResultCode().equals("1")){
                ToastUtils.makeText(context,mUserInfoEntity.getMsg());
                return;
            }
            ToastUtils.makeText(context,mUserInfoEntity.getMsg());
            setResult(1002);
            finish();
        }

        @Override
        public void onError(String result) {
            ToastUtils.makeText(context,result);
        }
    };

    @Override
    protected void loadData() {

    }
}
