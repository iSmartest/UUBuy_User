package com.ifree.uu.uubuy.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.app.MyApplication;
import com.ifree.uu.uubuy.service.entity.SecondActivitiesEntity;
import com.ifree.uu.uubuy.service.entity.UserInfoEntity;
import com.ifree.uu.uubuy.service.presenter.CollectionPresenter;
import com.ifree.uu.uubuy.service.presenter.SecondListPresenter;
import com.ifree.uu.uubuy.service.view.SecondListView;
import com.ifree.uu.uubuy.service.view.UserInfoView;
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
    private CollectionPresenter mCollectionPresenter;
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
    private String isCollection = "0";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_market;
    }

    @Override
    protected void initView() {
        hideBack(6);
        setRightText("收藏");
        mCollectionPresenter = new CollectionPresenter(context);
        fristActivitiesId = getIntent().getStringExtra("fristActivitiesId");
        fristActivitiesType = getIntent().getStringExtra("fristActivitiesType");
        fristActivitiesName = getIntent().getStringExtra("fristActivitiesName");
        setTitleText(fristActivitiesName);
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
            isCollection = mSecondListEntity.getData().getMarketInfo().getIsCollection();
            if (isCollection.equals("0")){
                setRightText("收藏");
            }else {
                setRightText("取消收藏");
            }
        }

        @Override
        public void onError(String result) {
            ToastUtils.makeText(context,result);
        }
    };

    @OnClick({R.id.tv_base_rightText})
    public void onViewClicked() {
        if (TextUtils.isEmpty(uid)){
            ToastUtils.makeText(context,"用户未登录，请登录");
            return;
        }
        mCollectionPresenter.onCreate();
        mCollectionPresenter.attachView(mCollectionView);
        if (isCollection.equals("0")){
            mCollectionPresenter.getSubmitIsCollection(uid,fristActivitiesId,"0","1","处理中...");
        }else {
            mCollectionPresenter.getSubmitIsCollection(uid,fristActivitiesId,"0","0","处理中...");
        }
    }


    private UserInfoView mCollectionView = new UserInfoView() {
        @Override
        public void onSuccess(UserInfoEntity mUserInfoEntity) {
            if (mUserInfoEntity.getResultCode().equals("1")){
                ToastUtils.makeText(context,mUserInfoEntity.getMsg());
                return;
            }
            ToastUtils.makeText(context,mUserInfoEntity.getMsg());
            if (isCollection.equals("0")){
                setRightText("取消收藏");
            }else {
                setRightText("收藏");
            }
        }

        @Override
        public void onError(String result) {
            ToastUtils.makeText(context,result);
        }
    };


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
