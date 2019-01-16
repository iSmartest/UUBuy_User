package com.ifree.uu.uubuy.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.common.CommonActivity;
import com.ifree.uu.uubuy.ui.adapter.MallStoreAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2019/1/4 0004
 * Description: 按楼层筛选门店
 */
public class MallFloorActivity extends CommonActivity {
    @BindView(R.id.tl_top_indicator)
    TabLayout tabLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    private boolean isFirst = true;
    private MallStoreAdapter mAdapter;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_mall_floor;
    }

    @Override
    protected int getTitleBarId() {
        return R.id.tb_mall_floor_bar;
    }

    @Override
    protected void initView() {
        initTabLayoutTitle();
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new GridLayoutManager(context,2));
        mAdapter = new MallStoreAdapter(context);
        recyclerView.setAdapter(mAdapter);
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
//                mAdapter.loadMore(buildItems());
                refreshLayout.finishLoadmore();
            }
        });
    }

    private void initTabLayoutTitle() {
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        if (isFirst) {//第一次加入值
            final List<String> list = new ArrayList<>();
            list.add("B1");
            list.add("F1");
            list.add("F2");
            list.add("F3");
            list.add("F4");
            list.add("F5");
            list.add("F6");
            list.add("F7");
            list.add("F8");
            for (int i = 0; i < list.size(); i++) {
                tabLayout.addTab(tabLayout.newTab().setText(list.get(i)), i);
            }
            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
//                    page = 1;
//                    mList.clear();
//                    mAdapter.notifyDataSetChanged();
//                    floor = list.get(tab.getPosition());
//                    loadData();
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });
            isFirst = false;
        }
    }

    @Override
    protected void initData() {

    }
}
