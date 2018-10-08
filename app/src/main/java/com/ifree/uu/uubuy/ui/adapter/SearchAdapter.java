package com.ifree.uu.uubuy.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
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
import com.ifree.uu.uubuy.mvp.entity.SearchEntity;
import com.ifree.uu.uubuy.ui.activity.BrandActivity;
import com.ifree.uu.uubuy.ui.activity.CarCommodityActivity;
import com.ifree.uu.uubuy.ui.activity.CommodityActivity;
import com.ifree.uu.uubuy.ui.activity.FurnitureMarketActivity;
import com.ifree.uu.uubuy.ui.activity.MarketActivity;
import com.ifree.uu.uubuy.ui.activity.ShoppingMallActivity;
import com.ifree.uu.uubuy.ui.activity.StoreActivity;
import com.ifree.uu.uubuy.uitls.GlideImageLoader;
import com.ifree.uu.uubuy.uitls.TimeFormatUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2018/9/19 0019
 * Description:
 */
public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder>{
    private Context context;
    private List<SearchEntity.DataBean.ActivitiesList> mList;
    private String searchType;

    public SearchAdapter(Context context, List<SearchEntity.DataBean.ActivitiesList> mList, String searchType) {
        this.context = context;
        this.mList = mList;
        this.searchType = searchType;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_search,parent,false);
        SearchViewHolder viewHolder = new SearchViewHolder(view);
        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        final SearchEntity.DataBean.ActivitiesList activitiesList = mList.get(position);
        switch (activitiesList.getActivitiesType()){
            case "0":
                holder.mMarket.setVisibility(View.VISIBLE);
                holder.mStore.setVisibility(View.GONE);
                holder.mCommodity.setVisibility(View.GONE);
                holder.address.setText(activitiesList.getActivitiesAdAddress());
                holder.name.setText(activitiesList.getActivitiesName());
                holder.time.setText("活动时间：" + TimeFormatUtils.modifyDataFormat2(activitiesList.getActivitiesTime()));
                GlideImageLoader.imageLoader(context,activitiesList.getActivitiesPic(),holder.icon);
                break;
            case "1":
                holder.mMarket.setVisibility(View.GONE);
                holder.mStore.setVisibility(View.VISIBLE);
                holder.mCommodity.setVisibility(View.GONE);
                holder.mName.setText(activitiesList.getActivitiesName());
                holder.mContent.setText(activitiesList.getActivitiesAdAddress());
                GlideImageLoader.imageLoader(context,activitiesList.getActivitiesPic(),holder.mPicture);
                break;
            case "2":
                holder.mMarket.setVisibility(View.GONE);
                holder.mStore.setVisibility(View.GONE);
                holder.mCommodity.setVisibility(View.VISIBLE);
                holder.mCommodityName.setText(activitiesList.getActivitiesName());
                GlideImageLoader.imageLoader(context,activitiesList.getActivitiesPic(),holder.mCommodityPicture);
                holder.mPrice.setText(activitiesList.getActivitiesPrice());
//                holder.mOldPrice.setText(commodityList.getCommodityOriginalPrice());
//                holder.mSurplus.setText(commodityList.getCommodityStock());
                break;

        }
        holder.mMarket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("fristActivitiesId",activitiesList.getActivitiesId());
                bundle.putString("fristActivitiesType",activitiesList.getType());
                bundle.putString("fristActivitiesName",activitiesList.getActivitiesName());
                switch (activitiesList.getType()){
                    case "1":
                        MyApplication.openActivity(context,ShoppingMallActivity.class,bundle);
                        break;
                    case "2"://超市
                        MyApplication.openActivity(context,MarketActivity.class,bundle);
                        break;
                    case "3":
                        MyApplication.openActivity(context,FurnitureMarketActivity.class,bundle);
                        break;
                    case "4":
                        MyApplication.openActivity(context,ShoppingMallActivity.class,bundle);
                        break;
                    case "5":
                        MyApplication.openActivity(context,ShoppingMallActivity.class,bundle);
                        break;
                    case "6":
                        MyApplication.openActivity(context,BrandActivity.class,bundle);
                        break;
                }
            }
        });
        holder.mStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("fristActivitiesId",activitiesList.getActivitiesId());
                bundle.putString("fristActivitiesType",activitiesList.getType());
                bundle.putString("fristActivitiesName",activitiesList.getActivitiesName());
                switch (activitiesList.getType()){
                    case "1":
                        MyApplication.openActivity(context,StoreActivity.class,bundle);
                        break;
                    case "2"://超市
                        MyApplication.openActivity(context,StoreActivity.class,bundle);
                        break;
                    case "3":
                        MyApplication.openActivity(context,StoreActivity.class,bundle);
                        break;
                    case "4":
                        MyApplication.openActivity(context,BrandActivity.class,bundle);
                        break;
                    case "5":
                        MyApplication.openActivity(context,BrandActivity.class,bundle);
                        break;
                    case "6":
                        MyApplication.openActivity(context,BrandActivity.class,bundle);
                        break;
                }
            }
        });

        holder.mCommodity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("commodityId",activitiesList.getActivitiesId());
                bundle.putString("type",activitiesList.getType());
                bundle.putString("fristActivitiesName",activitiesList.getActivitiesName());
                switch (activitiesList.getType()){
                    case "1":
                        MyApplication.openActivity(context,CommodityActivity.class,bundle);
                        break;
                    case "2"://超市
                        MyApplication.openActivity(context,CommodityActivity.class,bundle);
                        break;
                    case "3":
                        MyApplication.openActivity(context,CommodityActivity.class,bundle);
                        break;
                    case "4":
                        MyApplication.openActivity(context,CarCommodityActivity.class,bundle);
                        break;
                    case "5":
                        MyApplication.openActivity(context,CommodityActivity.class,bundle);
                        break;
                    case "6":
                        MyApplication.openActivity(context,CommodityActivity.class,bundle);
                        break;
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.rl_search_market)
        RelativeLayout mMarket;
        @BindView(R.id.iv_search_market_icon)
        ImageView icon;
        @BindView(R.id.tv_search_market_name)
        TextView name;
        @BindView(R.id.tv_search_market_time)
        TextView time;
        @BindView(R.id.tv_search_market_address)
        TextView address;
        
        @BindView(R.id.ll_search_store)
        LinearLayout mStore;
        @BindView(R.id.iv_search_store_picture)
        ImageView mPicture;
        @BindView(R.id.tv_search_store_name)
        TextView mName;
        @BindView(R.id.tv_search_store_content)
        TextView mContent;

        @BindView(R.id.rl_search_commodity)
        LinearLayout mCommodity;
        @BindView(R.id.iv_search_commodity_picture)
        ImageView mCommodityPicture;
        @BindView(R.id.tv_search_commodity_name)
        TextView mCommodityName;
        @BindView(R.id.tv_search_commodity_price)
        TextView mPrice;
        @BindView(R.id.tv_search_commodity_old_price)
        TextView mOldPrice;
        @BindView(R.id.tv_search_commodity_surplus)
        TextView mSurplus;
        public SearchViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
