package com.ifree.uu.uubuy.ui.activity;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.common.CommonActivity;
import com.ifree.uu.uubuy.ui.adapter.GoShoppingAdapter;
import com.ifree.uu.uubuy.ui.adapter.HomeAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;

import butterknife.BindView;

import static android.support.v7.widget.DividerItemDecoration.VERTICAL;

/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2019/1/4 0004
 * Description:
 */
public class GoShoppingActivity extends CommonActivity {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    private GoShoppingAdapter mAdapter;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_go_shopping;
    }

    @Override
    protected int getTitleBarId() {
        return R.id.tb_go_shopping_title;
    }

    @Override
    protected void initView() {
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.addItemDecoration(new DividerItemDecoration(context, VERTICAL));
        mAdapter = new GoShoppingAdapter(context);
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
