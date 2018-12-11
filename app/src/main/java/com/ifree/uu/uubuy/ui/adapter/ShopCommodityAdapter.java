package com.ifree.uu.uubuy.ui.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.app.MyApplication;
import com.ifree.uu.uubuy.mvp.entity.CommodityListEntity;
import com.ifree.uu.uubuy.ui.activity.CommodityActivity;
import com.ifree.uu.uubuy.uitls.GlideImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/31.
 * Description:
 */
public class ShopCommodityAdapter extends RecyclerView.Adapter<ShopCommodityAdapter.StoreViewHolder>{
    private Context context;
    private List<CommodityListEntity.DataBean.CommodityList> mList;

    public ShopCommodityAdapter(Context context, List<CommodityListEntity.DataBean.CommodityList> mList) {
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public StoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_shop_commodity_list,parent,false);
        StoreViewHolder viewHolder = new StoreViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StoreViewHolder holder, final int position) {
        final CommodityListEntity.DataBean.CommodityList commodityList = mList.get(position);
        holder.mCommodityName.setText(commodityList.getCommodityName());
        GlideImageLoader.imageLoader(context,commodityList.getCommodityPic(),holder.mCommodityPicture);
        holder.mCommodityDec.setText(commodityList.getActivityContion());
        holder.mPrice.setText("￥"+commodityList.getCommodityNowPrice());
        holder.mOldPrice.setText("￥"+commodityList.getCommodityOriginalPrice());
        holder.mOldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        holder.mSurplus.setText(commodityList.getCommodityStock());
        holder.mCommodity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("commodityId",commodityList.getCommodityId());
                bundle.putString("type", commodityList.getType());
                bundle.putString("commodityIcon", commodityList.getCommodityPic());
                MyApplication.openActivity(context, CommodityActivity.class, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class StoreViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.rl_foot_print_commodity)
        CardView mCommodity;
        @BindView(R.id.iv_foot_print_commodity_picture)
        ImageView mCommodityPicture;
        @BindView(R.id.tv_foot_print_commodity_name)
        TextView mCommodityName;
        @BindView(R.id.tv_foot_print_commodity_dec)
        TextView mCommodityDec;
        @BindView(R.id.tv_foot_print_commodity_price)
        TextView mPrice;
        @BindView(R.id.tv_foot_print_commodity_old_price)
        TextView mOldPrice;
        @BindView(R.id.tv_foot_print_commodity_surplus)
        TextView mSurplus;
        @BindView(R.id.tv_foot_print_is_over)
        TextView mIsOver;
        public StoreViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
