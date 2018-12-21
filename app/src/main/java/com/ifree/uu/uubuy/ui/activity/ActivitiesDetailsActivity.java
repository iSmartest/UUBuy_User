package com.ifree.uu.uubuy.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.app.MyApplication;
import com.ifree.uu.uubuy.dialog.LogOutDialog;
import com.ifree.uu.uubuy.mvp.entity.ActivitiesDetailsEntity;
import com.ifree.uu.uubuy.mvp.entity.UserInfoEntity;
import com.ifree.uu.uubuy.mvp.presenter.ActivitiesDetailsPresenter;
import com.ifree.uu.uubuy.mvp.presenter.CancelSignUpPresenter;
import com.ifree.uu.uubuy.mvp.view.ProjectView;
import com.ifree.uu.uubuy.ui.adapter.ActivitiesDetailsAdapter;
import com.ifree.uu.uubuy.ui.base.BaseActivity;
import com.ifree.uu.uubuy.uitls.GlideImageLoader;
import com.ifree.uu.uubuy.uitls.SPUtil;
import com.ifree.uu.uubuy.uitls.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/24.
 * Description:
 */
public class ActivitiesDetailsActivity extends BaseActivity {
    private ActivitiesDetailsPresenter mActivitiesDetailsPresenter;
    private CancelSignUpPresenter mCancelSignUpPresenter;
    @BindView(R.id.iv_activities_dec_picture)
    ImageView mPicture;
    @BindView(R.id.re_market_coupon)
    RecyclerView recyclerView;
    @BindView(R.id.tv_activities_dec)
    TextView tvDec;
    @BindView(R.id.tv_enter_for_activities)
    TextView tvEnter;
    private String marketId,marketName,type,advId,isSingUp = "0";
    private List<ActivitiesDetailsEntity.DataBean.CouponList> mList = new ArrayList<>();
    private ActivitiesDetailsAdapter mAdapter;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_activities_details;
    }

    @Override
    protected void initView() {
        hideBack(5);
        marketId = getIntent().getStringExtra("marketId");
        marketName = getIntent().getStringExtra("marketName");
        type = getIntent().getStringExtra("type");
        advId = getIntent().getStringExtra("advId");
        setTitleText(marketName);
        mActivitiesDetailsPresenter = new ActivitiesDetailsPresenter(context);
        mCancelSignUpPresenter = new CancelSignUpPresenter(context);
        recyclerView.setLayoutManager(new GridLayoutManager(context,3));
        mAdapter = new ActivitiesDetailsAdapter(context,mList);
        recyclerView.setAdapter(mAdapter);
    }
    @OnClick({R.id.tv_enter_for_activities})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.tv_enter_for_activities:
                if (TextUtils.isEmpty(uid)){
                    ToastUtils.makeText(context,"用户未登录，请登录");
                    MyApplication.openActivity(context,LoginActivity.class);
                    return;
                }
                if (SPUtil.getString(context,"isPhone").equals("0")){
                    ToastUtils.makeText(context,"请绑定手机号");
                    return;
                }
                if (isSingUp.equals("0")){
                    Bundle bundle = new Bundle();
                    bundle.putString("marketId",marketId);
                    bundle.putString("type",type);
                    bundle.putString("advId",advId);
                    MyApplication.openActivityForResult(ActivitiesDetailsActivity.this,EnterForActivitiesActivity.class,bundle,1001);
                }else {
                    //取消报名
                    LogOutDialog dialog = new LogOutDialog(context, R.string.cancel_enter, new LogOutDialog.OnSureBtnClickListener() {
                        @Override
                        public void sure() {
                            cancelSingUp();
                        }
                    });
                    dialog.show();
                }
                break;
        }
    }

    private void cancelSingUp() {
        mCancelSignUpPresenter.onCreate();
        mCancelSignUpPresenter.attachView(mCancelSingUpView);
        mCancelSignUpPresenter.getSearchCancelSignUp(uid,marketId,advId,type,"取消中...");
    }

    private ProjectView<UserInfoEntity> mCancelSingUpView = new ProjectView<UserInfoEntity>() {
        @Override
        public void onSuccess(UserInfoEntity mUserInfoEntity) {
            if (mUserInfoEntity.getResultCode().equals("1")){
                ToastUtils.makeText(context,mUserInfoEntity.getMsg());
                return;
            }
            ToastUtils.makeText(context,mUserInfoEntity.getMsg());
            isSingUp = "0";
            tvEnter.setBackgroundResource(R.drawable.shape_green_background);
            tvEnter.setText("报名参加");
        }

        @Override
        public void onError(String result) {
            ToastUtils.makeText(context,result);
        }
    };

    @Override
    protected void loadData() {
        mActivitiesDetailsPresenter.onCreate();
        mActivitiesDetailsPresenter.attachView(mActivitiesDetailsView);
        mActivitiesDetailsPresenter.getSearchActivitiesInfo(uid,marketId,advId,"加载中...");
    }

    private ProjectView<ActivitiesDetailsEntity> mActivitiesDetailsView = new ProjectView<ActivitiesDetailsEntity>() {
        @Override
        public void onSuccess(ActivitiesDetailsEntity mActivitiesDetailsEntity) {
            if (mActivitiesDetailsEntity.getResultCode().equals("1")){
                ToastUtils.makeText(context,mActivitiesDetailsEntity.getMsg());
                return;
            }
            List<ActivitiesDetailsEntity.DataBean.CouponList> couponLists = mActivitiesDetailsEntity.getData().getCouponList();
            if (couponLists != null && !couponLists.isEmpty()){
                mList.addAll(couponLists);
                mAdapter.notifyDataSetChanged();
            }
            GlideImageLoader.imageLoader(context,mActivitiesDetailsEntity.getData().getActivitiesPic(),mPicture);
            tvDec.setText(mActivitiesDetailsEntity.getData().getActivitiesDes());
            isSingUp = mActivitiesDetailsEntity.getData().getIsSingUp();
            if (isSingUp.equals("0")){
                tvEnter.setBackgroundResource(R.drawable.shape_green_background);
                tvEnter.setText("报名参加");
            }else {
                tvEnter.setBackgroundResource(R.drawable.shape_translucent_background);
                tvEnter.setText("取消参加");
            }
        }

        @Override
        public void onError(String result) {
            ToastUtils.makeText(context,result);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1001){
            if (resultCode == 1002){
                tvEnter.setBackgroundResource(R.drawable.shape_translucent_background);
                tvEnter.setText("取消参加");
                isSingUp = "1";
            }
        }
    }
}
