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
import com.ifree.uu.uubuy.service.entity.CommodityListEntity;
import com.ifree.uu.uubuy.service.entity.UserInfoEntity;
import com.ifree.uu.uubuy.service.presenter.CollectionPresenter;
import com.ifree.uu.uubuy.service.presenter.CommodityPresenter;
import com.ifree.uu.uubuy.service.view.CommodityListView;
import com.ifree.uu.uubuy.service.view.UserInfoView;
import com.ifree.uu.uubuy.ui.adapter.StoreAdapter;
import com.ifree.uu.uubuy.ui.base.BaseActivity;
import com.ifree.uu.uubuy.uitls.GlideImageLoader;
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
 * Created by 2018/8/30.
 * Description:
 */
public class StoreActivity extends BaseActivity implements View.OnClickListener {
    private CommodityPresenter mCommodityPresenter;
    private CollectionPresenter mCollectionPresenter;
    @BindView(R.id.xr_store)
    XRecyclerView xRecyclerView;
    private View headView;
    private int page = 1;
    private StoreAdapter mAdapter;
    private List<CommodityListEntity.DataBean.CommodityList> mList = new ArrayList<>();
    private String fristActivitiesName;
    private String storeId;
    private String fristActivitiesType;
    private TextView mStoreName,mStoreTime;
    private ImageView mStorePicture;
    private String isCollection = "0";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_store;
    }

    @Override
    protected void initView() {
        hideBack(6);
        setRightText("收藏");
        fristActivitiesName = getIntent().getStringExtra("fristActivitiesName");
        storeId = getIntent().getStringExtra("fristActivitiesId");
        fristActivitiesType = getIntent().getStringExtra("fristActivitiesType");
        setTitleText(fristActivitiesName);
        mCommodityPresenter = new CommodityPresenter(context);
        mCollectionPresenter = new CollectionPresenter(context);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xRecyclerView.setLayoutManager(layoutManager);
        xRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        xRecyclerView.setRefreshProgressStyle(ProgressStyle.BallPulse);
        xRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallPulse);
        xRecyclerView.setArrowImageView(R.drawable.iconfont_downgrey);
        headView = LayoutInflater.from(context).inflate(R.layout.header_store, null);
        mStoreName = headView.findViewById(R.id.tv_store_name);
        mStoreTime = headView.findViewById(R.id.tv_store_time);
        mStorePicture = headView.findViewById(R.id.iv_store_picture);
        headView.findViewById(R.id.tv_brand_coupon).setOnClickListener(this);
        if (headView != null) xRecyclerView.addHeaderView(headView);
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                mList.clear();
                mAdapter.notifyDataSetChanged();
                loadData();
            }

            @Override
            public void onLoadMore() {
                page++;
                loadData();
            }
        });

        mAdapter = new StoreAdapter(context, mList);
        xRecyclerView.setAdapter(mAdapter);
        xRecyclerView.addOnItemTouchListener(new RecyclerItemTouchListener(xRecyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
                int position = vh.getAdapterPosition() - 2;
                if (position < 0 | position >= mList.size()) {
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putString("commodityId",mList.get(position).getCommodityId());
                bundle.putString("type", mList.get(position).getType());
                bundle.putString("commodityIcon", mList.get(position).getCommodityPic());
                MyApplication.openActivity(context, CommodityActivity.class, bundle);
            }
        });
    }

    @Override
    protected void loadData() {
        mCommodityPresenter.onCreate();
        mCommodityPresenter.attachView(mCommodityListView);
        mCommodityPresenter.getSearchCommodityListInfo(storeId, page, SPUtil.getUid(context), "加载中...");
    }

    private CommodityListView mCommodityListView = new CommodityListView() {
        @Override
        public void onSuccess(CommodityListEntity mCommodityListEntity) {
            if (page == 1){
                xRecyclerView.refreshComplete();
            }else {
                xRecyclerView.loadMoreComplete();
            }
            if (mCommodityListEntity.getResultCode().equals("1")) {
                ToastUtils.makeText(context, mCommodityListEntity.getMsg());
                return;
            }
            List<CommodityListEntity.DataBean.CommodityList> commodityLists = mCommodityListEntity.getData().getCommodityList();
            if (commodityLists != null && !commodityLists.isEmpty()) {
                mList.addAll(commodityLists);
                mAdapter.notifyDataSetChanged();
                if (commodityLists.size() < 10){
                    xRecyclerView.setNoMore(true);
                }
            }
            mStoreName.setText(mCommodityListEntity.getData().getStoreName());
            mStoreTime.setText(mCommodityListEntity.getData().getStoreTime());
            GlideImageLoader.imageLoader(context, mCommodityListEntity.getData().getStorePic(), mStorePicture);
            isCollection = mCommodityListEntity.getData().getIsCollection();
            if (isCollection.equals("0")){
                setRightText("收藏");
            }else {
                setRightText("取消收藏");
            }
        }

        @Override
        public void onError(String result) {
            ToastUtils.makeText(context, result);
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
            mCollectionPresenter.getSubmitIsCollection(uid,storeId,"1","1","处理中...");
        }else {
            mCollectionPresenter.getSubmitIsCollection(uid,storeId,"1","0","处理中...");
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
        switch (v.getId()) {
            case R.id.tv_brand_coupon:
                Bundle bundle = new Bundle();
                bundle.putString("storeId",storeId);
                MyApplication.openActivity(context, StoreCouponActivity.class,bundle);
                break;
        }
    }

}