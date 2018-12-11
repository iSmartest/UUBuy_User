package com.ifree.uu.uubuy.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.mvp.entity.CompareCommodityEntity;
import com.ifree.uu.uubuy.uitls.GlideImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2018/9/19 0019
 * Description:
 */
public class CommodityCompareAdapter extends RecyclerView.Adapter<CommodityCompareAdapter.CommodityCompareViewHolder>{
    private Context context;
    private List<CompareCommodityEntity.DataBean.PList> mList;
    public CommodityCompareAdapter(Context context, List<CompareCommodityEntity.DataBean.PList> mList) {
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public CommodityCompareViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_shop_commodity_list,parent,false);
        CommodityCompareViewHolder viewHolder = new CommodityCompareViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CommodityCompareViewHolder holder, int position) {
        CompareCommodityEntity.DataBean.PList commodityList = mList.get(position);
        holder.mCommodityName.setText(commodityList.getCommodityName());
        GlideImageLoader.imageLoader(context,commodityList.getBannerPic(),holder.mCommodityPicture);
        holder.mPrice.setText(commodityList.getCommodityPrice());
        holder.mOldPrice.setText(commodityList.getCommodityPresentPrice());
        holder.mSurplus.setText(commodityList.getCommoditySurplusNum());
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public class CommodityCompareViewHolder extends RecyclerView.ViewHolder{
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
        public CommodityCompareViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
