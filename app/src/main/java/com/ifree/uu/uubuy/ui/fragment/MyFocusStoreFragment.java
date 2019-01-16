package com.ifree.uu.uubuy.ui.fragment;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.common.CommonLazyFragment;
import com.ifree.uu.uubuy.mvp.modle.HomeBean;
import com.ifree.uu.uubuy.ui.adapter.MallStoreAdapter;
import com.ifree.uu.uubuy.ui.adapter.MyCouponsAdapter;
import com.ifree.uu.uubuy.ui.adapter.MyFocusStoreAdapter;
import com.ifree.uu.uubuy.widget.XCollapsingToolbarLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2019/1/3 0003
 * Description: 我的关注—店铺页面
 */
public class MyFocusStoreFragment extends CommonLazyFragment implements XCollapsingToolbarLayout.OnScrimsListener {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    private MyFocusStoreAdapter mAdapter;
    public static MyFocusStoreFragment newInstance() {
        return new MyFocusStoreFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my_focus_store;
    }

    @Override
    protected int getTitleBarId() {
        return 0;
    }

    @Override
    protected void initView() {
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new GridLayoutManager(context,2));
        mAdapter = new MyFocusStoreAdapter(context);
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

    @Override
    public void onScrimsStateChange(boolean shown) {

    }
}
