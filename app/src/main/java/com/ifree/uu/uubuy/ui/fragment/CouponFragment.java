package com.ifree.uu.uubuy.ui.fragment;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.common.CommonLazyFragment;
import com.ifree.uu.uubuy.ui.adapter.GetCouponsAdapter;
import com.ifree.uu.uubuy.ui.adapter.MyCouponsAdapter;
import com.ifree.uu.uubuy.widget.XCollapsingToolbarLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2019/1/3 0003
 * Description: 我的优惠券页面
 */
public class CouponFragment extends CommonLazyFragment implements XCollapsingToolbarLayout.OnScrimsListener {
    @BindView(R.id.tv_activities_market)
    TextView mMarket;
    @BindView(R.id.tv_activities_store)
    TextView mStore;
    @BindView(R.id.tv_my_coupon_not_used)
    TextView tvCouponNotUsed;
    @BindView(R.id.tv_my_coupon_used)
    TextView tvCouponUsed;
    @BindView(R.id.tv_is_expired)
    TextView tvExpired;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    private ColorStateList csl1,csl2,csl3;
    private MyCouponsAdapter mAdapter;
    public static CouponFragment newInstance() {
        return new CouponFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_coupon;
    }

    @Override
    protected int getTitleBarId() {
        return R.id.tb_my_coupon_title;
    }

    @Override
    protected void initView() {
        Resources resource = context.getResources();
        csl1 = resource.getColorStateList(R.color.text_type_red);
        csl2 = resource.getColorStateList(R.color.text_main_color);
        csl3 = resource.getColorStateList(R.color.app_main_default);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        mAdapter = new MyCouponsAdapter(context);
        recyclerView.setAdapter(mAdapter);
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
//                mAdapter.loadMore(buildItems());
                refreshLayout.finishLoadmore();
            }
        });
    }

    @OnClick({R.id.tv_my_coupon_not_used, R.id.tv_my_coupon_used, R.id.tv_is_expired,R.id.tv_activities_market,R.id.tv_activities_store})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_my_coupon_not_used:
                tvCouponNotUsed.setTextColor(csl1);
                tvCouponUsed.setTextColor(csl2);
                tvExpired.setTextColor(csl2);
                mAdapter.setCouponState(1);
                break;
            case R.id.tv_my_coupon_used:
                tvCouponNotUsed.setTextColor(csl2);
                tvCouponUsed.setTextColor(csl1);
                tvExpired.setTextColor(csl2);
                mAdapter.setCouponState(2);
                break;
            case R.id.tv_is_expired:
                tvCouponNotUsed.setTextColor(csl2);
                tvCouponUsed.setTextColor(csl2);
                tvExpired.setTextColor(csl1);
                mAdapter.setCouponState(3);
                break;
            case R.id.tv_activities_market:
                mMarket.setBackgroundResource(R.drawable.shape_left_select_background);
                mStore.setBackgroundResource(R.drawable.shape_right_background);
                mMarket.setTextColor(csl3);
                mStore.setTextColor(csl2);
                break;
            case R.id.tv_activities_store:
                mMarket.setBackgroundResource(R.drawable.shape_left_background);
                mStore.setBackgroundResource(R.drawable.shape_right_select_background);
                mMarket.setTextColor(csl2);
                mStore.setTextColor(csl3);
                break;
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onScrimsStateChange(boolean shown) {

    }
}
