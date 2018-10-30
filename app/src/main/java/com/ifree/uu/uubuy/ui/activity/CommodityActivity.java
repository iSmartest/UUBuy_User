package com.ifree.uu.uubuy.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.app.MyApplication;
import com.ifree.uu.uubuy.dialog.SeePictureDialog;
import com.ifree.uu.uubuy.listener.RecyclerItemTouchListener;
import com.ifree.uu.uubuy.mvp.entity.CommodityInfoEntity;
import com.ifree.uu.uubuy.mvp.entity.UserInfoEntity;
import com.ifree.uu.uubuy.mvp.presenter.CollectionPresenter;
import com.ifree.uu.uubuy.mvp.presenter.CommodityInfoPresenter;
import com.ifree.uu.uubuy.mvp.view.ProjectView;
import com.ifree.uu.uubuy.ui.adapter.CommodityInfoAdapter;
import com.ifree.uu.uubuy.ui.base.BaseActivity;
import com.ifree.uu.uubuy.uitls.GlideImageLoader;
import com.ifree.uu.uubuy.uitls.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/27.
 * Description:
 */
public class CommodityActivity extends BaseActivity {
    private CollectionPresenter mCollectionPresenter;
    private CommodityInfoPresenter mCommodityInfoPresenter;
    @BindView(R.id.tv_commodity_compare)
    TextView mCompare;
    @BindView(R.id.tv_commodity_reserve)
    TextView mReserve;
    @BindView(R.id.tv_commodity_brand_name)
    TextView mBrandName;
    @BindView(R.id.tv_commodity_surplus_num)
    TextView mSurplusNum;
    @BindView(R.id.tv_commodity_name)
    TextView mCommodityName;
    @BindView(R.id.tv_commodity_des)
    TextView mCommodityDes;
    @BindView(R.id.tv_present_price)
    TextView mPresentPrice;
    @BindView(R.id.tv_store_commodity_address)
    TextView mAddress;
    @BindView(R.id.tv_store_commodity_condition)
    TextView mCondition;
    @BindView(R.id.iv_commodity_picture)
    ImageView mCommodityPicture;
    @BindView(R.id.rc_picture)
    RecyclerView mRecyclerView;
    private String commodityId,type,commodityType,commodityIcon,commodityBrandName,
            commodityName,commodityPrice,commodityAddress,shopId;
    private List<String> mList = new ArrayList<>();
    private CommodityInfoAdapter mAdapter;
    private String isCollection = "0";
    private SeePictureDialog seePictureDialog;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_commodity;
    }

    @Override
    protected void initView() {
        hideBack(6);
        setTitleText("商品详情");
        setRightText("收藏");
        commodityId = getIntent().getStringExtra("commodityId");
        type = getIntent().getStringExtra("type");
        commodityIcon = getIntent().getStringExtra("commodityIcon");
        if (type.equals("3")){
            mCompare.setVisibility(View.VISIBLE);
        }else {
            mCompare.setVisibility(View.GONE);
        }
        mCollectionPresenter = new CollectionPresenter(context);
        mCommodityInfoPresenter = new CommodityInfoPresenter(context);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context, OrientationHelper.HORIZONTAL,false));
        mAdapter = new CommodityInfoAdapter(context,mList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnItemTouchListener(new RecyclerItemTouchListener(mRecyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
                int position = vh.getAdapterPosition();
                if (position < 0 | position >= mList.size()){
                    return;
                }
                mAdapter.setDefSelect(position);
                GlideImageLoader.imageLoader(context,mList.get(position),mCommodityPicture);
            }
        });
        mCommodityPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seePictureDialog = new SeePictureDialog(context,mList);
                seePictureDialog.show();
            }
        });

    }


    @Override
    protected void loadData() {
        mCommodityInfoPresenter.onCreate();
        mCommodityInfoPresenter.attachView(mCommodityInfoView);
        mCommodityInfoPresenter.getSearchCommodityInfo(commodityId,type,uid,"加载中...");
    }

    private ProjectView<CommodityInfoEntity> mCommodityInfoView = new ProjectView<CommodityInfoEntity>() {
        @Override
        public void onSuccess(CommodityInfoEntity mCommodityInfoEntity) {
            if (mCommodityInfoEntity.getResultCode().equals("1")){
                ToastUtils.makeText(context,mCommodityInfoEntity.getMsg());
                return;
            }
            List<String> mPicture = mCommodityInfoEntity.getData().getBannerPic();
            if (mPicture != null && !mPicture.isEmpty()){
                mList.clear();
                mList.addAll(mPicture);
                mAdapter.notifyDataSetChanged();
                GlideImageLoader.imageLoader(context,mPicture.get(0),mCommodityPicture);
            }
            commodityBrandName = mCommodityInfoEntity.getData().getCommodityBrandName();
            mBrandName.setText(commodityBrandName);
            mSurplusNum.setText(mCommodityInfoEntity.getData().getCommoditySurplusNum() + "%");
            commodityName = mCommodityInfoEntity.getData().getCommodityName();
            mCommodityName.setText(commodityName);
            mCommodityDes.setText(mCommodityInfoEntity.getData().getCommodityDes());
            commodityPrice = mCommodityInfoEntity.getData().getCommodityPresentPrice();
            mPresentPrice.setText("￥" + commodityPrice);
            commodityAddress = mCommodityInfoEntity.getData().getActivitiesStoreAddress();
            mAddress.setText(commodityAddress);
            mCondition.setText(mCommodityInfoEntity.getData().getCommodityCondition());
            commodityType = mCommodityInfoEntity.getData().getType();
            shopId = mCommodityInfoEntity.getData().getCommodityShopId();
            isCollection = mCommodityInfoEntity.getData().getIsCollection();
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


    @OnClick({R.id.tv_commodity_compare,R.id.tv_commodity_reserve,R.id.tv_base_rightText})
    public void onClickView(View view){
        Bundle bundle = new Bundle();
        switch (view.getId()){
            case R.id.tv_commodity_compare:
                bundle.putString("commodityId",commodityId);
                MyApplication.openActivityForResult(CommodityActivity.this,CommodityCompareActivity.class,bundle,1001);
                break;
            case R.id.tv_commodity_reserve:
                bundle.putString("commodityIcon",commodityIcon);
                bundle.putString("commodityName",commodityName);
                bundle.putString("commodityBrandName",commodityBrandName);
                bundle.putString("commodityPrice",commodityPrice);
                bundle.putString("commodityAddress",commodityAddress);
                bundle.putString("commodityId",commodityId);
                bundle.putString("commodityType",commodityType);
                bundle.putString("shopId",shopId);
                MyApplication.openActivity(context,CommodityReserveActivity.class,bundle);
                break;
            case R.id.tv_base_rightText:
                if (TextUtils.isEmpty(uid)){
                    ToastUtils.makeText(context,"用户未登录，请登录");
                    return;
                }
                mCollectionPresenter.onCreate();
                mCollectionPresenter.attachView(mCollectionView);
                if (isCollection.equals("0")){
                    mCollectionPresenter.getSubmitIsCollection(uid,commodityId,"2","1","处理中...");
                }else {
                    mCollectionPresenter.getSubmitIsCollection(uid,commodityId,"2","0","处理中...");
                }
                break;
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1001 ){
            if ( resultCode == 1002){
                commodityId = data.getStringExtra("commodityId");
                type = data.getStringExtra("type");
                commodityIcon = data.getStringExtra("commodityIcon");
                loadData();
            }
        }
    }
}
