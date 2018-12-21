package com.ifree.uu.uubuy.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
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
import com.ifree.uu.uubuy.mvp.entity.MineEntity;
import com.ifree.uu.uubuy.ui.activity.ActivitiesDetailsActivity;
import com.ifree.uu.uubuy.uitls.GlideImageLoader;
import com.ifree.uu.uubuy.uitls.TimeFormatUtils;

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

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MineViewHolder holder, final int position) {
        final MineEntity.DataBean.RecommendactivitiesList reList = mList.get(position);
        GlideImageLoader.imageLoader(context,reList.getActivitiesPic(),holder.mMarketPicture);
        holder.mMarketName.setText(reList.getActivitiesName());
        holder.mMarketTime.setText("活动时间：" + TimeFormatUtils.modifyDataFormat2(reList.getActivitiesTime()));
        ColorStateList cls1 = context.getResources().getColorStateList(R.color.text_green);
        ColorStateList cls2 = context.getResources().getColorStateList(R.color.text_type_red);
        ColorStateList cls3 = context.getResources().getColorStateList(R.color.silver_medal);
        switch (reList.getIsOver()){
            case "0"://活动结束
                holder.mMarketState.setText("已结束");
                holder.mMarketState.setTextColor(cls2);
                break;
            case "1"://活动进行中
                holder.mMarketState.setText("进行中");
                holder.mMarketState.setTextColor(cls1);
                break;
            case "2"://活动未开始
                holder.mMarketState.setText("未开始");
                holder.mMarketState.setTextColor(cls3);
                break;
        }
        holder.llMarket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("marketId", reList.getActivitiesId());
                bundle.putString("marketName", reList.getActivitiesName());
                bundle.putString("type", reList.getType());
                bundle.putString("advId", reList.getaId());
                MyApplication.openActivity(context, ActivitiesDetailsActivity.class, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public class MineViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_mine_market_state)
        TextView mMarketState;
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
