package com.ifree.uu.uubuy.ui.fragment;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.common.CommonLazyFragment;
import com.ifree.uu.uubuy.mvp.modle.HomeBean;
import com.ifree.uu.uubuy.ui.adapter.MyFocusMallAdapter;
import com.ifree.uu.uubuy.ui.adapter.MyFocusStoreAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2019/1/7 0007
 * Description: 我的关注—商场页面
 */
public class MyFocusMallFragment extends CommonLazyFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    private List<HomeBean.DataBean.ActivitiesList> mList = new ArrayList<>();
    private MyFocusMallAdapter mAdapter;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my_focus_mall;
    }

    @Override
    protected int getTitleBarId() {
        return 0;
    }

    @Override
    protected void initView() {
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        mAdapter = new MyFocusMallAdapter(context,mList);
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
