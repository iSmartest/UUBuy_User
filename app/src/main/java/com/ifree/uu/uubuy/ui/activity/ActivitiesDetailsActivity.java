package com.ifree.uu.uubuy.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.app.MyApplication;
import com.ifree.uu.uubuy.service.entity.ActivitiesDetailsEntity;
import com.ifree.uu.uubuy.service.presenter.ActivitiesDetailsPresenter;
import com.ifree.uu.uubuy.service.view.ActivitiesDetailsView;
import com.ifree.uu.uubuy.ui.adapter.ActivitiesDetailsAdapter;
import com.ifree.uu.uubuy.ui.base.BaseActivity;
import com.ifree.uu.uubuy.uitls.GlideImageLoader;
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
    @BindView(R.id.iv_activities_dec_picture)
    ImageView mPicture;
    @BindView(R.id.re_market_coupon)
    RecyclerView recyclerView;
    @BindView(R.id.tv_activities_dec)
    TextView tvDec;
    @BindView(R.id.tv_enter_for_activities)
    TextView tvEnter;
    private String marketId,marketName,type;
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
        setTitleText(marketName);
        mActivitiesDetailsPresenter = new ActivitiesDetailsPresenter(context);
        recyclerView.setLayoutManager(new GridLayoutManager(context,3));
        mAdapter = new ActivitiesDetailsAdapter(context,mList);
        recyclerView.setAdapter(mAdapter);
    }
    @OnClick({R.id.tv_base_rightText,R.id.tv_enter_for_activities})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.tv_base_rightText:
//                ToastUtils.makeText(context,"你点击了收藏");
                break;
            case R.id.tv_enter_for_activities:
                Bundle bundle = new Bundle();
                bundle.putString("marketId",marketId);
                bundle.putString("type",type);
                MyApplication.openActivity(context,EnterForActivitiesActivity.class,bundle);
                break;
        }
    }

    @Override
    protected void loadData() {
        mActivitiesDetailsPresenter.onCreate();
        mActivitiesDetailsPresenter.attachView(mActivitiesDetailsView);
        mActivitiesDetailsPresenter.getSearchActivitiesInfo("116",marketId,"加载中...");
    }

    private ActivitiesDetailsView mActivitiesDetailsView = new ActivitiesDetailsView() {

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

        }

        @Override
        public void onError(String result) {
            ToastUtils.makeText(context,result);
        }
    };
}
