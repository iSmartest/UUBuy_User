package com.ifree.uu.uubuy.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.app.MyApplication;
import com.ifree.uu.uubuy.listener.RecyclerItemTouchListener;
import com.ifree.uu.uubuy.service.entity.SecondActivitiesEntity;
import com.ifree.uu.uubuy.service.entity.UserInfoEntity;
import com.ifree.uu.uubuy.service.presenter.CollectionPresenter;
import com.ifree.uu.uubuy.service.presenter.SecondListPresenter;
import com.ifree.uu.uubuy.service.view.ProjectView;
import com.ifree.uu.uubuy.ui.adapter.MarketOrStoreAdapter;
import com.ifree.uu.uubuy.ui.base.BaseActivity;
import com.ifree.uu.uubuy.uitls.GlideImageLoader;
import com.ifree.uu.uubuy.uitls.TimeFormatUtils;
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
 * Created by 2018/8/25.
 * Description:商场
 */
public class ShoppingMallActivity extends BaseActivity implements View.OnClickListener {
    private SecondListPresenter mSecondListPresenter;
    private CollectionPresenter mCollectionPresenter;
    @BindView(R.id.xr_market_store)
    XRecyclerView xRecyclerView;
    private View headView;
    private int page = 1;
    private MarketOrStoreAdapter mAdapter;
    private List<SecondActivitiesEntity.DataBean.BandCommodityList> mList = new ArrayList<>();
    private String fristActivitiesId;
    private String fristActivitiesType;
    private String fristActivitiesName;
    private String isCollection = "0";
    private TextView mName,mTime;
    private ImageView mPicture;
    private String isOver = "0";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_market_store;
    }

    @Override
    protected void initView() {
        hideBack(6);
        setTitleText("综合商场");
        setRightText("收藏");
        mCollectionPresenter = new CollectionPresenter(context);
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
        mName = headView.findViewById(R.id.tv_market_name);
        mPicture = headView.findViewById(R.id.tv_market_picture);
        mTime = headView.findViewById(R.id.tv_market_time);
        headView.findViewById(R.id.tv_market_share).setOnClickListener(this);
        if (headView != null) xRecyclerView.addHeaderView(headView);
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                loadData();
            }

            @Override
            public void onLoadMore() {
                page++;
                loadData();
            }
        });

        mAdapter = new MarketOrStoreAdapter(context, mList);
        xRecyclerView.setAdapter(mAdapter);
        xRecyclerView.addOnItemTouchListener(new RecyclerItemTouchListener(xRecyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
                int position = vh.getAdapterPosition() - 2;
                if (position < 0 | position >= mList.size()){
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putString("fristActivitiesId",mList.get(position).getSecondActivitiesId());
                bundle.putString("fristActivitiesType",mList.get(position).getSecondActivitiesType());
                bundle.putString("fristActivitiesName",mList.get(position).getSecondActivitiesName());
                MyApplication.openActivity(context,StoreActivity.class,bundle);
            }
        });
    }

    @Override
    protected void loadData() {
        mSecondListPresenter.onCreate();
        mSecondListPresenter.attachView(mSecondListView);
        mSecondListPresenter.getSearchSecondListInfo(fristActivitiesId,page,uid,fristActivitiesType,"","加载中...");
    }

    private ProjectView<SecondActivitiesEntity> mSecondListView = new ProjectView<SecondActivitiesEntity>() {
        @Override
        public void onSuccess(SecondActivitiesEntity mSecondListEntity) {
            if (page == 1){
                xRecyclerView.refreshComplete();
                mList.clear();
                mAdapter.notifyDataSetChanged();
            }else {
                xRecyclerView.loadMoreComplete();
            }
            if (mSecondListEntity.getResultCode().equals("1")){
                ToastUtils.makeText(context,mSecondListEntity.getMsg());
                return;
            }
            List<SecondActivitiesEntity.DataBean.BandCommodityList> secondActivitiesList = mSecondListEntity.getData().getBandCommodityList();
            if (secondActivitiesList != null && !secondActivitiesList.isEmpty()){
                mList.addAll(secondActivitiesList);
                mAdapter.notifyDataSetChanged();
                if (secondActivitiesList.size() < 10){
                    xRecyclerView.setNoMore(true);
                }
            }
            fristActivitiesName = mSecondListEntity.getData().getMarketInfo().getMarketName();
            mName.setText(fristActivitiesName);
            mTime.setText(mSecondListEntity.getData().getMarketInfo().getActivitiesTime());
            GlideImageLoader.imageLoader(context,mSecondListEntity.getData().getMarketInfo().getActivitiesPic(),mPicture);
            isCollection = mSecondListEntity.getData().getMarketInfo().getIsCollection();
            isOver = mSecondListEntity.getData().getMarketInfo().getIsOver();

            if (isCollection.equals("0")){
                setRightText("收藏");
            }else {
                setRightText("取消收藏");
            }
            switch (isOver){
                case "0":
                    mTime.setText("活动已结束");
                    break;
                case "1":
                case "2":
                    mTime.setText("活动时间 " + TimeFormatUtils.modifyDataFormat2(mSecondListEntity.getData().getMarketInfo().getActivitiesTime()));
                    break;
                case "3":
                    mTime.setText("暂无活动");
                    break;
            }
        }

        @Override
        public void onError(String result) {
            ToastUtils.makeText(context,result);
            if (page == 1){
                xRecyclerView.refreshComplete();
            }else {
                xRecyclerView.loadMoreComplete();
            }
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

    private ProjectView<UserInfoEntity> mCollectionView = new ProjectView<UserInfoEntity>() {
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
                if (isOver.equals("1") || isOver.equals("2")){
                    Bundle bundle = new Bundle();
                    bundle.putString("marketId",fristActivitiesId);
                    bundle.putString("marketName",fristActivitiesName);
                    bundle.putString("type",fristActivitiesType);
                    MyApplication.openActivity(context,ActivitiesDetailsActivity.class,bundle);
                }
                break;
            case R.id.tv_market_share:
                ToastUtils.makeText(context,"你点击分享");
                break;
        }
    }
}
