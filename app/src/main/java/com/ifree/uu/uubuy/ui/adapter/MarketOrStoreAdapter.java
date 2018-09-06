package com.ifree.uu.uubuy.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.service.entity.SecondActivitiesEntity;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/25.
 * Description:
 */
public class MarketOrStoreAdapter extends RecyclerView.Adapter<MarketOrStoreAdapter.MarketOrStoreViewHolder> {
    private Context context;
    private List<SecondActivitiesEntity.SecondActivitiesList> mList;

    public MarketOrStoreAdapter(Context context, List<SecondActivitiesEntity.SecondActivitiesList> mList) {
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
    public void onBindViewHolder(@NonNull MarketOrStoreViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    static class MarketOrStoreViewHolder extends RecyclerView.ViewHolder {

        public MarketOrStoreViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
