package com.ifree.uu.uubuy.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.service.entity.MineEntity;
import com.ifree.uu.uubuy.uitls.GlideImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/21.
 * Description:
 */
public class MineAdapter extends RecyclerView.Adapter<MineAdapter.MineViewHolder>{
    private Context context;
    private List<MineEntity.DataBean.RecommendactivitiesList> mList;
    public MineAdapter(Context context, List<MineEntity.DataBean.RecommendactivitiesList> mList) {
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public MineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_mine_activities,parent,false);
        MineViewHolder viewHolder = new MineViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MineViewHolder holder, int position) {
        MineEntity.DataBean.RecommendactivitiesList reList = mList.get(position);
        switch (reList.getType()){
            case "1":
            case "2":
            case "3":
                holder.llMarket.setVisibility(View.VISIBLE);
                holder.llStore.setVisibility(View.GONE);
                GlideImageLoader.imageLoader(context,reList.getActivitiesPic(),holder.mMarketPicture);
                holder.mMarketName.setText(reList.getActivitiesName());
                holder.mMarketTime.setText("活动时间：" + reList.getActivitiesTime());
                break;
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
                holder.llMarket.setVisibility(View.GONE);
                holder.llStore.setVisibility(View.VISIBLE);
                GlideImageLoader.imageLoader(context,reList.getActivitiesPic(),holder.mStorePicture);
                holder.mStoreName.setText(reList.getActivitiesName());
                holder.mStoreAddress.setText(reList.getActivitiesAdAddress());
                break;

        }
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public class MineViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.ll_mine_store)
        LinearLayout llStore;
        @BindView(R.id.iv_mine_store_picture)
        ImageView mStorePicture;
        @BindView(R.id.tv_mine_store_name)
        TextView mStoreName;
        @BindView(R.id.tv_mine_store_address)
        TextView mStoreAddress;
        @BindView(R.id.tv_mine_market_name)
        TextView mMarketName;
        @BindView(R.id.iv_mine_market_picture)
        ImageView mMarketPicture;
        @BindView(R.id.tv_mine_market_time)
        TextView mMarketTime;
        @BindView(R.id.ll_mine_market)
        LinearLayout llMarket;
        public MineViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
