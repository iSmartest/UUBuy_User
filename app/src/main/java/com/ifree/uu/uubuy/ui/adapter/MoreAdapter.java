package com.ifree.uu.uubuy.ui.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.app.MyApplication;
import com.ifree.uu.uubuy.mvp.entity.MoreEntity;
import com.ifree.uu.uubuy.ui.activity.CommodityActivity;
import com.ifree.uu.uubuy.ui.activity.ShopActivity;
import com.ifree.uu.uubuy.uitls.GlideImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2018/9/18 0018
 * Description:
 */
public class MoreAdapter extends RecyclerView.Adapter<MoreAdapter.MoreViewHolder>{
    private Context context;
    private List<MoreEntity.DataBean.CommodityList> mList;
    private String type;
    public MoreAdapter(Context context, List<MoreEntity.DataBean.CommodityList> mList,String type) {
        this.context = context;
        this.mList = mList;
        this.type = type;
    }

    @NonNull
    @Override
    public MoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_more, parent, false);
        MoreViewHolder viewHolder = new MoreViewHolder(view);
        return viewHolder;
    }
    public void setType(String type) {
        this.type = type;
        notifyDataSetChanged();
    }
    @Override
    public void onBindViewHolder(@NonNull MoreViewHolder viewHolder, int position) {
        final MoreEntity.DataBean.CommodityList commodityList = mList.get(position);
        GlideImageLoader.imageLoader(context,commodityList.getCommodityPic(),viewHolder.mPicture);
        switch (type){
            case "0":
                viewHolder.rl_store.setVisibility(View.VISIBLE);
                viewHolder.ll_commodity.setVisibility(View.GONE);
                viewHolder.mType.setText("专柜");
                viewHolder.mStoreName.setText(commodityList.getCommodityName());
                viewHolder.mStoreDescent.setText(commodityList.getCommodityDescent());
                viewHolder.mStoreTime.setText(commodityList.getCommodityTime());
                viewHolder.rl_store.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putString("fristActivitiesId",commodityList.getCommodityId());
                        bundle.putString("fristActivitiesType",commodityList.getCommodityType());
                        bundle.putString("fristActivitiesName",commodityList.getCommodityName());
                        MyApplication.openActivity(context,ShopActivity.class,bundle);
                    }
                });
                break;
            case "1":
                viewHolder.rl_store.setVisibility(View.GONE);
                viewHolder.ll_commodity.setVisibility(View.VISIBLE);
                viewHolder.mType.setText("自营");
                viewHolder.mCommodityName.setText(commodityList.getCommodityName());
                viewHolder.mCommodityDec.setText(commodityList.getCommodityDec());
                viewHolder.mCommodityNowPrice.setText(commodityList.getCommodityNowPrice());
                viewHolder.mCommodityOriginalPrice.setText(commodityList.getCommodityOriginalPrice());
                viewHolder.ll_commodity.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putString("commodityId",commodityList.getCommodityId());
                        bundle.putString("type","2");
                        bundle.putString("commodityIcon",commodityList.getCommodityPic());
                        MyApplication.openActivity(context, CommodityActivity.class, bundle);
                    }
                });
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public class MoreViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_store_or_commodity_picture)
        ImageView mPicture;
        @BindView(R.id.rl_store)
        LinearLayout rl_store;
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
        public MoreViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
