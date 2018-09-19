package com.ifree.uu.uubuy.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.app.MyApplication;
import com.ifree.uu.uubuy.service.entity.SecondActivitiesEntity;
import com.ifree.uu.uubuy.service.presenter.SecondListPresenter;
import com.ifree.uu.uubuy.service.view.SecondListView;
import com.ifree.uu.uubuy.ui.adapter.MarketAdapter;
import com.ifree.uu.uubuy.ui.base.BaseActivity;
import com.ifree.uu.uubuy.uitls.GlideImageLoader;
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
 * Created by 2018/8/23.
 * Description:超市，类型：专柜、商品
 */
public class MarketActivity extends BaseActivity implements View.OnClickListener {
    private SecondListPresenter mSecondListPresenter;
    @BindView(R.id.xr_market)
    XRecyclerView xRecyclerView;
    private View headView;
    private int page = 1;
    private MarketAdapter mAdapter;
    private List<SecondActivitiesEntity.DataBean.MarketCommodityList> mList = new ArrayList<>();
    private String fristActivitiesId;
    private String fristActivitiesType;
    private String fristActivitiesName;
    private TextView mName,mTime;
    private ImageView mPicture;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_market;
    }

    @Override
    protected void initView() {
        hideBack(6);
        setTitleText("综合超市");
        setRightText("收藏");
        fristActivitiesId = getIntent().getStringExtra("fristActivitiesId");
        fristActivitiesType = getIntent().getStringExtra("fristActivitiesType");
        mSecondListPresenter = new SecondListPresenter(context);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xRecyclerView.setLayoutManager(layoutManager);
        xRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        xRecyclerView.setRefreshProgressStyle(ProgressStyle.BallPulse);
        xRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallPulse);
        xRecyclerView.setArrowImageView(R.drawable.iconfont_downgrey);
        headView = LayoutInflater.from(context).inflate(R.layout.header_market, null);
        headView.findViewById(R.id.ll_market_activities).setOnClickListener(this);
        headView.findViewById(R.id.tv_market_share).setOnClickListener(this);
        mName = headView.findViewById(R.id.tv_market_name);
        mPicture = headView.findViewById(R.id.tv_market_picture);
        mTime = headView.findViewById(R.id.tv_market_time);
        if (headView != null) xRecyclerView.addHeaderView(headView);
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                mList.clear();
                mAdapter.notifyDataSetChanged();
                loadData();
                xRecyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                page++;
                loadData();
                xRecyclerView.loadMoreComplete();
                mAdapter.notifyDataSetChanged();
                xRecyclerView.setNoMore(true);
            }
        });

        mAdapter = new MarketAdapter(context,mList,fristActivitiesId);
        xRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void loadData() {
        mSecondListPresenter.onCreate();
        mSecondListPresenter.attachView(mSecondListView);
        mSecondListPresenter.getSearchSecondListInfo(fristActivitiesId,page,uid,fristActivitiesType,"","加载中...");

    }
    private SecondListView mSecondListView = new SecondListView() {
        @Override
        public void onSuccess(SecondActivitiesEntity mSecondListEntity) {
            if (mSecondListEntity.getResultCode().equals("1")){
                ToastUtils.makeText(context,mSecondListEntity.getMsg());
                return;
            }
            List<SecondActivitiesEntity.DataBean.MarketCommodityList> marketCommodityLists = mSecondListEntity.getData().getMarketCommodityList();
            if (marketCommodityLists != null && !marketCommodityLists.isEmpty()){
                mList.addAll(marketCommodityLists);
                mAdapter.notifyDataSetChanged();
            }
            fristActivitiesName = mSecondListEntity.getData().getMarketInfo().getMarketName();
            mName.setText(fristActivitiesName);
            mTime.setText(mSecondListEntity.getData().getMarketInfo().getActivitiesTime());
            GlideImageLoader.imageLoader(context,mSecondListEntity.getData().getMarketInfo().getActivitiesPic(),mPicture);
        }

        @Override
        public void onError(String result) {
            ToastUtils.makeText(context,result);
        }
    };

    @OnClick({R.id.tv_base_rightText})
    public void onViewClicked() {
        ToastUtils.makeText(context, "收藏");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_market_activities:
                Bundle bundle = new Bundle();
                bundle.putString("marketId",fristActivitiesId);
                bundle.putString("marketName",fristActivitiesName);
                bundle.putString("type",fristActivitiesType);
                MyApplication.openActivity(context,ActivitiesDetailsActivity.class,bundle);
                break;
            case R.id.tv_market_share:
                ToastUtils.makeText(context,"你点击分享");
                break;
        }
    }
}
