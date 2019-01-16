package com.ifree.uu.uubuy.ui.activity;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.app.MyApplication;
import com.ifree.uu.uubuy.common.CommonActivity;
import com.ifree.uu.uubuy.ui.adapter.BannerAdapter;
import com.ifree.uu.uubuy.ui.adapter.MallStoreAdapter;
import com.ifree.uu.uubuy.ui.adapter.StoreProductAdapter;
import com.ifree.uu.uubuy.widget.ZoomOutPageTransformer;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2019/1/4 0004
 * Description: 商场门店
 */
public class StoreActivity extends CommonActivity {
    @BindView(R.id.rv_store_activities)
    RecyclerView rcStoreActivities;
    @BindView(R.id.ll_store_get_coupons)
    LinearLayout llGetCoupons;
    @BindView(R.id.tv_store_activities_product)
    TextView tvActivitiesProduct;
    @BindView(R.id.tv_store_selected_product)
    TextView tvSelectedProduct;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    private ColorStateList csl1,csl2;
    private BannerAdapter mBannerAdapter;
    private StoreProductAdapter mAdapter;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_store;
    }

    @Override
    protected int getTitleBarId() {
        return R.id.tb_store_bar;
    }

    @Override
    protected void initView() {
        Resources resource = context.getResources();
        csl1 = resource.getColorStateList(R.color.text_type_red);
        csl2 = resource.getColorStateList(R.color.text_main_color);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rcStoreActivities.setLayoutManager(linearLayoutManager);
        mBannerAdapter = new BannerAdapter(context);
        rcStoreActivities.setAdapter(mBannerAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new GridLayoutManager(context,2));
        mAdapter = new StoreProductAdapter(context);
        recyclerView.setAdapter(mAdapter);
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
//                mAdapter.loadMore(buildItems());
                refreshLayout.finishLoadmore();
            }
        });
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.ll_store_get_coupons,R.id.tv_store_activities_product,R.id.tv_store_selected_product})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_store_get_coupons:
                MyApplication.openActivity(context,GetCouponsActivity.class);
                break;
            case R.id.tv_store_activities_product:
                tvActivitiesProduct.setTextColor(csl1);
                tvSelectedProduct.setTextColor(csl2);
                break;
            case R.id.tv_store_selected_product:
                tvActivitiesProduct.setTextColor(csl2);
                tvSelectedProduct.setTextColor(csl1);
                break;

        }
    }
}
