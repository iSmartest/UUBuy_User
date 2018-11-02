package com.ifree.uu.uubuy.ui.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.mvp.entity.OrderEntity;
import com.ifree.uu.uubuy.mvp.presenter.OrderPresenter;
import com.ifree.uu.uubuy.mvp.view.ProjectView;
import com.ifree.uu.uubuy.ui.adapter.OrderAdapter;
import com.ifree.uu.uubuy.ui.base.BaseFragment;
import com.ifree.uu.uubuy.uitls.SPUtil;
import com.ifree.uu.uubuy.uitls.ToastUtils;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/17.
 * Description:
 */
public class OrderFragment extends BaseFragment {
    private OrderPresenter orderPresenter;
    @BindView(R.id.tv_order_all)
    TextView mAll;
    @BindView(R.id.tv_all_complete)
    TextView mComplete;
    @BindView(R.id.tv_all_cancel)
    TextView mCancel;
    @BindView(R.id.xr_order)
    XRecyclerView xRecyclerView;
    @BindView(R.id.iv_order_empty)
    TextView mEmpty;
    private int page = 1;
    private ColorStateList csl1,csl2;
    private String orderState = "0";
    private List<OrderEntity.DataBean.OrderInfoList> mList = new ArrayList<>();
    private OrderAdapter mAdapter;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //注册广播
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.ifree.uu.order.changed");
        getActivity().registerReceiver(mAllBroad, intentFilter);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_order;
    }

    @Override
    protected void initView() {
        orderPresenter = new OrderPresenter(context);
        Resources resource = context.getResources();
        csl1 = resource.getColorStateList(R.color.app_main_default);
        csl2 = resource.getColorStateList(R.color.text_main_color);
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
                initData();
            }

            @Override
            public void onLoadMore() {
                page ++ ;
                initData();
            }
        });
        mAdapter = new OrderAdapter(context,mList,orderState);
        xRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        orderPresenter.onCreate();
        orderPresenter.attachView(mOrderView);
        orderPresenter.getSearchOrders(orderState,page, SPUtil.getUid(context),"加载中...");
    }

    private ProjectView<OrderEntity> mOrderView = new ProjectView<OrderEntity>() {
        @Override
        public void onSuccess(OrderEntity mOrderEntity) {
            if (page == 1){
                xRecyclerView.refreshComplete();
                mList.clear();
                mAdapter.notifyDataSetChanged();
            }else {
                xRecyclerView.loadMoreComplete();
            }
            if (mOrderEntity.getMsg().equals("1")){
                ToastUtils.makeText(context,mOrderEntity.getResultCode());
                return;
            }
            List<OrderEntity.DataBean.OrderInfoList> orderInfoList = mOrderEntity.getData().getOrderInfoList();
            if (orderInfoList != null && !orderInfoList.isEmpty()){
                mEmpty.setVisibility(View.GONE);
                mList.addAll(orderInfoList);
                mAdapter.notifyDataSetChanged();
                if (orderInfoList.size() < 10){
                    xRecyclerView.setNoMore(true);
                }
            }else {
                mEmpty.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onError(String result) {
            ToastUtils.makeText(context,result);
            mEmpty.setVisibility(View.VISIBLE);
            if (page == 1){
                xRecyclerView.refreshComplete();
            }else {
                xRecyclerView.loadMoreComplete();
            }
        }
    };

    @OnClick({R.id.tv_order_all, R.id.tv_all_complete, R.id.tv_all_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_order_all:
                mAll.setBackgroundResource(R.drawable.shape_left_select_background);
                mComplete.setBackgroundResource(R.drawable.shape_center_background);
                mCancel.setBackgroundResource(R.drawable.shape_right_background);
                mAll.setTextColor(csl1);
                mComplete.setTextColor(csl2);
                mCancel.setTextColor(csl2);
                orderState = "0";
                mAdapter.setType(orderState);
                xRecyclerView.setRefreshing(true);
                break;
            case R.id.tv_all_complete:
                mAll.setBackgroundResource(R.drawable.shape_left_background);
                mComplete.setBackgroundResource(R.drawable.shape_center_select_background);
                mCancel.setBackgroundResource(R.drawable.shape_right_background);
                mAll.setTextColor(csl2);
                mComplete.setTextColor(csl1);
                mCancel.setTextColor(csl2);
                orderState = "1";
                mAdapter.setType(orderState);
                xRecyclerView.setRefreshing(true);
                break;
            case R.id.tv_all_cancel:
                mAll.setBackgroundResource(R.drawable.shape_left_background);
                mComplete.setBackgroundResource(R.drawable.shape_center_background);
                mCancel.setBackgroundResource(R.drawable.shape_right_select_background);
                mAll.setTextColor(csl2);
                mComplete.setTextColor(csl2);
                mCancel.setTextColor(csl1);
                orderState = "2";
                mAdapter.setType(orderState);
                xRecyclerView.setRefreshing(true);
                break;
        }
    }
    private BroadcastReceiver mAllBroad = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, final Intent intent) {
            //接到广播通知后刷新数据源
            xRecyclerView.setRefreshing(true);
        }
    };
}
