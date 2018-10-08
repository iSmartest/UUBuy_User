package com.ifree.uu.uubuy.ui.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.app.MyApplication;
import com.ifree.uu.uubuy.mvp.entity.SecondActivitiesEntity;
import com.ifree.uu.uubuy.ui.activity.CommodityActivity;
import com.ifree.uu.uubuy.ui.activity.StoreActivity;
import com.ifree.uu.uubuy.uitls.GlideImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/24.
 * Description:
 */
public class MyGridAdapter extends RecyclerView.Adapter<MyGridAdapter.MyGridViewHolder> {
    private Context context;
    private List<SecondActivitiesEntity.DataBean.MarketCommodityList.CommodityList> commodityList;

    public MyGridAdapter(Context context, List<SecondActivitiesEntity.DataBean.MarketCommodityList.CommodityList> commodityList) {
        this.context = context;
        this.commodityList = commodityList;
    }

    @NonNull
    @Override
    public MyGridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_my_gridview, parent, false);
        MyGridViewHolder viewHolder = new MyGridViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyGridViewHolder viewHolder, int position) {
        final SecondActivitiesEntity.DataBean.MarketCommodityList.CommodityList mList = commodityList.get(position);
        GlideImageLoader.imageLoader(context,mList.getCommodityPic(),viewHolder.mPicture);
        switch (mList.getCommodityType()){
            case "0":
                viewHolder.rl_store.setVisibility(View.VISIBLE);
                viewHolder.ll_commodity.setVisibility(View.GONE);
                viewHolder.mType.setText("专柜");
                viewHolder.mStoreName.setText(mList.getCommodityName());
                viewHolder.mStoreDescent.setText(mList.getCommodityDescent());
                viewHolder.mStoreTime.setText(mList.getCommodityTime());
                break;
            case "1":
                viewHolder.rl_store.setVisibility(View.GONE);
                viewHolder.ll_commodity.setVisibility(View.VISIBLE);
                viewHolder.mType.setText("自营");
                viewHolder.mCommodityName.setText(mList.getCommodityName());
                viewHolder.mCommodityDec.setText(mList.getCommodityDec());
                viewHolder.mCommodityNowPrice.setText("￥" + mList.getCommodityNowPrice());
                viewHolder.mCommodityOriginalPrice.setText("￥" + mList.getCommodityOriginalPrice());
                viewHolder.mCommodityOriginalPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
                break;
        }

        viewHolder.mItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                switch (mList.getCommodityType()){
                    case "0":
                        bundle.putString("fristActivitiesId",mList.getCommodityId());
                        bundle.putString("fristActivitiesType",mList.getCommodityType());
                        bundle.putString("fristActivitiesName",mList.getCommodityName());
                        MyApplication.openActivity(context,StoreActivity.class,bundle);
                        break;
                    case "1":
                        bundle.putString("commodityId", mList.getCommodityId());
                        bundle.putString("type", mList.getCommodityType());
                        bundle.putString("commodityIcon", mList.getCommodityPic());
                        MyApplication.openActivity(context, CommodityActivity.class, bundle);
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return commodityList == null ? 0 : commodityList.size();
    }

    class MyGridViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ll_item_commodity)
        LinearLayout mItem;
        @BindView(R.id.tv_picture)
        ImageView mPicture;
        @BindView(R.id.rl_market_store)
        RelativeLayout rl_store;
        @BindView(R.id.tv_store_name)
        TextView mStoreName;
        @BindView(R.id.tv_store_descent)
        TextView mStoreDescent;
        @BindView(R.id.tv_store_time)
        TextView mStoreTime;
        @BindView(R.id.ll_commodity)
        LinearLayout ll_commodity;
        @BindView(R.id.tv_commodity_name)
        TextView mCommodityName;
        @BindView(R.id.tv_commodity_dec)
        TextView mCommodityDec;
        @BindView(R.id.tv_commodity_now_price)
        TextView mCommodityNowPrice;
        @BindView(R.id.tv_commodity_original_price)
        TextView mCommodityOriginalPrice;
        @BindView(R.id.tv_item_type)
        TextView mType;

        public MyGridViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
