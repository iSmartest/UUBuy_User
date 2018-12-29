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
import com.ifree.uu.uubuy.ui.activity.CarCommodityActivity;
import com.ifree.uu.uubuy.ui.activity.CommodityActivity;
import com.ifree.uu.uubuy.ui.activity.ShopActivity;
import com.ifree.uu.uubuy.ui.activity.ShoppingMallActivity;
import com.ifree.uu.uubuy.uitls.GlideImageLoader;
import com.ifree.uu.uubuy.uitls.SPUtil;
import com.ifree.uu.uubuy.uitls.TimeFormatUtils;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.ifree.uu.uubuy.uitls.GlobalMethod.getMapIsEmpty;

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
    public void onBindViewHolder(@NonNull final SearchViewHolder holder, int position) {
        final SearchEntity.DataBean.ActivitiesList activitiesList = mList.get(position);
        Map<String,String> spMap = SPUtil.getMap(context,"key");
        holder.tvIsOver.setVisibility(View.GONE);
        switch (activitiesList.getActivitiesType()){
            case "0":
                holder.mMarket.setVisibility(View.VISIBLE);
                holder.mStore.setVisibility(View.GONE);
                holder.mCommodity.setVisibility(View.GONE);
                holder.address.setText(activitiesList.getActivitiesAdAddress());
                holder.name.setText(activitiesList.getActivitiesName());
                holder.time.setText("活动时间：" + TimeFormatUtils.modifyDataFormat2(activitiesList.getActivitiesTime()));
                GlideImageLoader.imageLoader(context,activitiesList.getActivitiesPic(),holder.icon);
                holder.signUp.setText("报名：" + activitiesList.getSignUp() + "人");
                if (spMap == null || spMap.size() == 0){
                    holder.browsingVolume.setText("浏览：" + activitiesList.getBrowsing()  + "人");
                }else {
                    if (spMap.containsKey(activitiesList.getaId())){
                        int temp = activitiesList.getBrowsing() + Integer.valueOf(spMap.get(activitiesList.getaId()));
                        holder.browsingVolume.setText("浏览：" + temp + "人");
                    }else {
                        holder.browsingVolume.setText("浏览：" + activitiesList.getBrowsing()  + "人");
                    }
                }

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
                    case "4":
                        MyApplication.openActivity(context,ShoppingMallActivity.class,bundle);
                        break;
                    case "5":
                        MyApplication.openActivity(context,ShoppingMallActivity.class,bundle);
                        break;
                }
                Map<String,String> currentMap = SPUtil.getMap(context,"key");
                if (getMapIsEmpty(currentMap)){
                    currentMap.put(activitiesList.getaId(), 1 + "");
                }else {
                    if (currentMap.containsKey(activitiesList.getaId())){
                        currentMap.put(activitiesList.getaId(), (Integer.valueOf(currentMap.get(activitiesList.getaId())) + 1)+"");
                    }else {
                        currentMap.put(activitiesList.getaId(), 1 + "");
                    }
                }
                int temp = activitiesList.getBrowsing() + Integer.valueOf(currentMap.get(activitiesList.getaId()));
                holder.browsingVolume.setText("浏览：" + temp + "人");
                SPUtil.putMap(context,"key",currentMap);
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
                        MyApplication.openActivity(context,ShopActivity.class,bundle);
                        break;
                    case "2"://超市
                        MyApplication.openActivity(context,ShopActivity.class,bundle);
                        break;
                    case "3":
                        MyApplication.openActivity(context,ShopActivity.class,bundle);
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
        @BindView(R.id.tv_home_main_browsing_volume)
        TextView browsingVolume;
        @BindView(R.id.tv_home_main_sign_up)
        TextView signUp;
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
        @BindView(R.id.tv_is_over)
        TextView tvIsOver;
        public SearchViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
