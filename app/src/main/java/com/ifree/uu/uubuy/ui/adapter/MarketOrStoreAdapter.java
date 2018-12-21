package com.ifree.uu.uubuy.ui.adapter;

import android.content.Context;
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
import com.ifree.uu.uubuy.custom.RCRelativeLayout;
import com.ifree.uu.uubuy.mvp.entity.SecondActivitiesEntity;
import com.ifree.uu.uubuy.ui.activity.ShopActivity;
import com.ifree.uu.uubuy.uitls.GlideImageLoader;
import com.ifree.uu.uubuy.uitls.TimeFormatUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/25.
 * Description:
 */
public class MarketOrStoreAdapter extends RecyclerView.Adapter<MarketOrStoreAdapter.MarketOrStoreViewHolder> {
    private Context context;
    private List<SecondActivitiesEntity.DataBean.BandCommodityList> mList;

    public MarketOrStoreAdapter(Context context, List<SecondActivitiesEntity.DataBean.BandCommodityList> mList) {
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public MarketOrStoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_market_or_store,parent,false);
        MarketOrStoreViewHolder viewHolder = new MarketOrStoreViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MarketOrStoreViewHolder holder, final int position) {
        final SecondActivitiesEntity.DataBean.BandCommodityList sList = mList.get(position);
        holder.mName.setText(sList.getSecondActivitiesName());
        holder.mContent.setText(TimeFormatUtils.modifyDataFormat2(sList.getSecondActivitiesTime()));
        holder.mContent.setVisibility(View.GONE);
        GlideImageLoader.imageLoader(context,sList.getSecondActivitiesPic(),holder.mPicture);
        holder.mStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("fristActivitiesId", sList.getSecondActivitiesId());
                bundle.putString("fristActivitiesType", sList.getSecondActivitiesType());
                bundle.putString("fristActivitiesName", sList.getSecondActivitiesName());
                MyApplication.openActivity(context, ShopActivity.class, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
//        return 30;
    }

    static class MarketOrStoreViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.rc_store)
        CardView mStore;
        @BindView(R.id.iv_picture)
        ImageView mPicture;
        @BindView(R.id.tv_name)
        TextView mName;
        @BindView(R.id.tv_content_time)
        TextView mContent;
        public MarketOrStoreViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
