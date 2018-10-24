package com.ifree.uu.uubuy.ui.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.mvp.entity.CommodityListEntity;
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
public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.StoreViewHolder>{
    private Context context;
    private List<CommodityListEntity.DataBean.CommodityList> mList;

    public StoreAdapter(Context context, List<CommodityListEntity.DataBean.CommodityList> mList) {
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public StoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_store,parent,false);
        StoreViewHolder viewHolder = new StoreViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StoreViewHolder holder, int position) {
        CommodityListEntity.DataBean.CommodityList commodityList = mList.get(position);
        holder.mName.setText(commodityList.getCommodityName());
        GlideImageLoader.imageLoader(context,commodityList.getCommodityPic(),holder.mPicture);
        holder.mPrice.setText("￥"+commodityList.getCommodityNowPrice());
        holder.mOldPrice.setText("￥"+commodityList.getCommodityOriginalPrice());
        holder.mOldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        holder.mSurplus.setText(commodityList.getCommodityStock());
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class StoreViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.iv_store_commodity_picture)
        ImageView mPicture;
        @BindView(R.id.tv_store_commodity_name)
        TextView mName;
        @BindView(R.id.tv_store_commodity_price)
        TextView mPrice;
        @BindView(R.id.tv_store_commodity_old_price)
        TextView mOldPrice;
        @BindView(R.id.tv_store_commodity_surplus)
        TextView mSurplus;
        @BindView(R.id.tv_now_reserve)
        TextView mReserve;
        public StoreViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
