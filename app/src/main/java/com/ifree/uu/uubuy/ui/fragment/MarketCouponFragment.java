package com.ifree.uu.uubuy.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.service.entity.CouponEntity;
import com.ifree.uu.uubuy.ui.adapter.CouponAdapter;
import com.ifree.uu.uubuy.ui.adapter.CouponCenterAdapter;
import com.ifree.uu.uubuy.ui.base.BaseFragment;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/21.
 * Description:
 */
public class MarketCouponFragment extends BaseFragment {
    @BindView(R.id.xr_coupon)
    XRecyclerView xRecyclerView;
    private int page = 1;
    private CouponCenterAdapter mAdapter;
    private List<CouponEntity.CouponList> mList = new ArrayList<>();
    private String couponType = "1";
    @Override
    protected int getLayout() {
        return R.layout.fragment_coupon;
    }

    @Override
    protected void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xRecyclerView.setLayoutManager(layoutManager);
        xRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        xRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        xRecyclerView.setArrowImageView(R.drawable.iconfont_downgrey);
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                mList.clear();
                mAdapter.notifyDataSetChanged();
                initData();
                xRecyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                page ++ ;
                initData();
                xRecyclerView.loadMoreComplete();
                mAdapter.notifyDataSetChanged();
                xRecyclerView.setNoMore(true);
            }
        });

        mAdapter = new CouponCenterAdapter(context,mList,couponType);
        xRecyclerView.setAdapter(mAdapter);
        xRecyclerView.setRefreshing(true);

    }

    @Override
    protected void initData() {

    }
}
