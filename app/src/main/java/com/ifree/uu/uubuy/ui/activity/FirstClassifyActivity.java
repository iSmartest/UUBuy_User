package com.ifree.uu.uubuy.ui.activity;

import android.app.Activity;
import android.support.v7.view.menu.MenuAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.app.MyApplication;
import com.ifree.uu.uubuy.listener.RecyclerItemTouchListener;
import com.ifree.uu.uubuy.service.entity.MenuClassifyEntity;
import com.ifree.uu.uubuy.ui.adapter.FirstClassifyAdapter;
import com.ifree.uu.uubuy.ui.adapter.FirstMenuAdapter;
import com.ifree.uu.uubuy.ui.base.BaseActivity;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/23.
 * Description:
 */
public class FirstClassifyActivity extends BaseActivity {
    @BindView(R.id.rc_menu)
    RecyclerView rc_menu;
    @BindView(R.id.xr_market)
    XRecyclerView xRecyclerView;
    private FirstMenuAdapter mFirstMenuAdapter;
    private FirstClassifyAdapter mAdapter;
    private List<MenuClassifyEntity.FirstMenuList> mMenuList = new ArrayList<>();
    private int page = 1;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_first_classify;
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initView() {
        hideBack(5);
        setTitleText("综合商场");
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xRecyclerView.setLayoutManager(layoutManager);
        rc_menu.setLayoutManager(new LinearLayoutManager(context));
        xRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        xRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        xRecyclerView.setArrowImageView(R.drawable.iconfont_downgrey);

        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
//                mList.clear();
                mAdapter.notifyDataSetChanged();
                loadData();
                xRecyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                page ++ ;
                loadData();
                xRecyclerView.loadMoreComplete();
                mAdapter.notifyDataSetChanged();
                xRecyclerView.setNoMore(true);
            }
        });

        mAdapter = new FirstClassifyAdapter(context);
        xRecyclerView.setAdapter(mAdapter);
        xRecyclerView.setRefreshing(true);

        mFirstMenuAdapter = new FirstMenuAdapter(context,mMenuList);
        rc_menu.setAdapter(mFirstMenuAdapter);

        xRecyclerView.addOnItemTouchListener(new RecyclerItemTouchListener(xRecyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
//                int position = vh.getAdapterPosition();
//                if (position < 0 | position >= mAdTypeList.size()){
//                    return;
//                }
//                MyApplication.openActivity(context,MarketActivity.class);
                MyApplication.openActivity(context,MarketOrStoreActivity.class);
            }
        });
    }
}
