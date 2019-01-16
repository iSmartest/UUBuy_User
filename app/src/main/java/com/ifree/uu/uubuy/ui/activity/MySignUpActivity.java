package com.ifree.uu.uubuy.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.common.CommonActivity;
import com.ifree.uu.uubuy.ui.adapter.MySignUpAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2019/1/8 0008
 * Description: 我的报名
 */
public class MySignUpActivity extends CommonActivity {
    @BindView(R.id.tl_top_indicator)
    TabLayout tabLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    private MySignUpAdapter mAdapter;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_sign_up;
    }

    @Override
    protected int getTitleBarId() {
        return R.id.tb_sign_up_title;
    }

    @Override
    protected void initView() {
        initTabLayoutTitle();
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(context,DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        mAdapter = new MySignUpAdapter(context);
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

    private void initTabLayoutTitle() {
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
            final List<String> list = new ArrayList<>();
            list.add("已报名");
            list.add("已取消");
            list.add("已结束");
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


    }

}
