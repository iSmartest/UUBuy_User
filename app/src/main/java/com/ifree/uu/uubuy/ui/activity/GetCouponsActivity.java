package com.ifree.uu.uubuy.ui.activity;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.common.CommonActivity;
import com.ifree.uu.uubuy.ui.adapter.GetCouponsAdapter;
import com.ifree.uu.uubuy.ui.adapter.MallStoreAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;

import butterknife.BindView;

/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2019/1/4 0004
 * Description: 领取优惠券
 */
public class GetCouponsActivity extends CommonActivity {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    private GetCouponsAdapter mAdapter;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_get_coupons;
    }

    @Override
    protected int getTitleBarId() {
        return R.id.tb_get_coupons_title;
    }

    @Override
    protected void initView() {
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        mAdapter = new GetCouponsAdapter(context);
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
}
