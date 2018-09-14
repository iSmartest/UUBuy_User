package com.ifree.uu.uubuy.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.app.MyApplication;
import com.ifree.uu.uubuy.listener.RecyclerItemTouchListener;
import com.ifree.uu.uubuy.service.entity.CommodityInfoEntity;
import com.ifree.uu.uubuy.service.presenter.CommodityInfoPresenter;
import com.ifree.uu.uubuy.service.view.CommodityInfoView;
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
    @BindView(R.id.iv_commodity_picture)
    ImageView mCommodityPicture;
    @BindView(R.id.rc_picture)
    RecyclerView mRecyclerView;
    private String commodityId,type;
    private List<String> mList = new ArrayList<>();
    private CommodityInfoAdapter mAdapter;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_commodity;
    }

    @Override
    protected void initView() {
        hideBack(6);
        setTitleText("商品详情");
        commodityId = getIntent().getStringExtra("commodityId");
        type = getIntent().getStringExtra("type");
        if (type.equals("3")){
            mCompare.setVisibility(View.VISIBLE);
        }else {
            mCompare.setVisibility(View.GONE);
        }
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
                GlideImageLoader.imageLoader(context,mList.get(position),mCommodityPicture);
            }
        });
    }


    @Override
    protected void loadData() {
        mCommodityInfoPresenter.onCreate();
        mCommodityInfoPresenter.attachView(mCommodityInfoView);
        mCommodityInfoPresenter.getSearchCommodityInfo(commodityId,type,"1","加载中...");
        GlideImageLoader.imageLoader(context,mPicture.get(0),mCommodityPicture);
    }

    private CommodityInfoView mCommodityInfoView = new CommodityInfoView() {
        @Override
        public void onSuccess(CommodityInfoEntity mCommodityInfoEntity) {
            if (mCommodityInfoEntity.getResultCode().equals("1")){
                ToastUtils.makeText(context,mCommodityInfoEntity.getMsg());
                return;
            }
            List<String> mPicture = mCommodityInfoEntity.getData().getBannerPic();
            if (mPicture != null && !mPicture.isEmpty()){
                mList.addAll(mPicture);
            }
            mBrandName.setText(mCommodityInfoEntity.getData().getCommodityBrandName());
            mSurplusNum.setText(mCommodityInfoEntity.getData().getCommoditySurplusNum() + "%");
            mCommodityName.setText(mCommodityInfoEntity.getData().getCommodityName());
            mCommodityDes.setText(mCommodityInfoEntity.getData().getCommodityDes());
            mPresentPrice.setText(mCommodityInfoEntity.getData().getCommodityPresentPrice());
            mAddress.setText(mCommodityInfoEntity.getData().getActivitiesStoreAddress());
        }

        @Override
        public void onError(String result) {
            ToastUtils.makeText(context,result);
        }
    };


    @OnClick({R.id.tv_commodity_compare,R.id.tv_commodity_reserve})
    public void onClickView(View view){
        switch (view.getId()){
            case R.id.tv_commodity_compare:

                break;
            case R.id.tv_commodity_reserve:
                MyApplication.openActivity(context,CommodityReserveActivity.class);
                break;
        }
    }

}
