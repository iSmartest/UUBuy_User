package com.ifree.uu.uubuy.ui.activity;

import android.text.TextUtils;
import android.widget.ImageView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.app.MyApplication;
import com.ifree.uu.uubuy.mvp.entity.UserInfoEntity;
import com.ifree.uu.uubuy.mvp.presenter.ElasticFramePresenter;
import com.ifree.uu.uubuy.mvp.presenter.EnrollPresenter;
import com.ifree.uu.uubuy.mvp.view.ProjectView;
import com.ifree.uu.uubuy.ui.base.BaseActivity;
import com.ifree.uu.uubuy.uitls.SPUtil;
import com.ifree.uu.uubuy.uitls.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2018/12/11 0011
 * Description:
 */
public class UUActivitiesActivity extends BaseActivity {
    private ElasticFramePresenter mElasticFramePresenter;
    private EnrollPresenter mEnrollPresenter;
    @BindView(R.id.iv_sign_in_activities)
    ImageView ivSign;
    private String IsEnroll = "0";
    @Override
    protected int getLayoutId() {
        return R.layout.activity_uu_activities;
    }

    @Override
    protected void initView() {
        hideBack(9);
        mEnrollPresenter = new EnrollPresenter(context);
        mElasticFramePresenter = new ElasticFramePresenter(context);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mElasticFramePresenter.onCreate();
        mElasticFramePresenter.attachView(new ProjectView<UserInfoEntity>(){
            @Override
            public void onSuccess(final UserInfoEntity userInfoEntity) {
                if (userInfoEntity.getResultCode().equals("1")){
                    return;
                }
                IsEnroll = userInfoEntity.getData().getIsEnroll();
            }

            @Override
            public void onError(String result) {
                ToastUtils.makeText(context,result);
            }
        });
        mElasticFramePresenter.searchElasticFrame(uid);
    }

    @Override
    protected void loadData() {

    }

    @OnClick({R.id.iv_sign_in_activities})
    public void onViewClicked() {
        if (TextUtils.isEmpty(uid)){
            ToastUtils.makeText(context,"用户未登录，请登录");
            MyApplication.openActivity(context,LoginActivity.class);
            return;
        }
        if (SPUtil.getString(context,"isPhone").equals("0")){
            ToastUtils.makeText(context,"请绑定手机号");
            MyApplication.openActivity(context,BindingPhoneActivity.class);
            return;
        }
        if (IsEnroll.equals("0")){
            submitSign();
        }
    }

    private void submitSign() {
        mEnrollPresenter.onCreate();
        mEnrollPresenter.attachView(mEnrollView);
        mEnrollPresenter.searchEnroll(uid,userPhone,"报名中...");
    }

    private ProjectView<UserInfoEntity> mEnrollView = new ProjectView<UserInfoEntity>() {
        @Override
        public void onSuccess(UserInfoEntity userInfoEntity) {
            if (userInfoEntity.getResultCode().equals("1")){
                ToastUtils.makeText(context,userInfoEntity.getMsg());
                return;
            }
        }

        @Override
        public void onError(String result) {
            ToastUtils.makeText(context,result);
        }
    };
}
