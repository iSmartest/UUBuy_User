package com.ifree.uu.uubuy.ui.activity;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.common.CommonActivity;
import com.ifree.uu.uubuy.ui.adapter.BannerAdapter;
import com.ifree.uu.uubuy.ui.adapter.ProductFineFoodAdapter;
import com.ifree.uu.uubuy.ui.adapter.StoreProductAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;

import butterknife.BindView;

/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2019/1/11 0011
 * Description: 美食商品详情页
 */
public class ProductFineFoodActivity extends CommonActivity {
    @BindView(R.id.fine_food)
    RecyclerView rcFineFood;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    private StoreProductAdapter mAdapter;
    private ProductFineFoodAdapter fineFoodAdapter;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_product_fine_food;
    }

    @Override
    protected int getTitleBarId() {
        return R.id.tb_product_fine_food_info_title;
    }

    @Override
    protected void initView() {
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

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rcFineFood.setLayoutManager(linearLayoutManager);
        fineFoodAdapter = new ProductFineFoodAdapter(context);
        rcFineFood.setAdapter(fineFoodAdapter);
    }

    @Override
    protected void initData() {

    }
}
