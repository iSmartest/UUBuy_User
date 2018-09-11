package com.ifree.uu.uubuy.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.custom.swipeLayout.SwipeLayout;
import com.ifree.uu.uubuy.service.entity.ActivitiesEntity;
import com.ifree.uu.uubuy.service.entity.HomeEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/20.
 * Description:
 */
public class ActivitiesAdapter extends RecyclerView.Adapter<ActivitiesAdapter.ActivitiesViewHolder>{

    private Context context;
    private List<ActivitiesEntity.DataBean.ActivitiesList> mList;
    private String  activitiesType;

    public ActivitiesAdapter(Context context, List<ActivitiesEntity.DataBean.ActivitiesList> mList, String activitiesType) {
        this.context = context;
        this.mList = mList;
        this.activitiesType = activitiesType;
    }

    public void setType(String activitiesType){
        this.activitiesType = activitiesType;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ActivitiesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_activities_circle,parent,false);
        ActivitiesViewHolder viewHolder = new ActivitiesViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ActivitiesViewHolder holder, int position) {
        switch (activitiesType){
            case "0":
                holder.sl_market.setVisibility(View.VISIBLE);
                holder.sl_store.setVisibility(View.GONE);
                holder.sl_commodity.setVisibility(View.GONE);
                break;
            case "1":
                holder.sl_market.setVisibility(View.GONE);
                holder.sl_store.setVisibility(View.VISIBLE);
                holder.sl_commodity.setVisibility(View.GONE);
                break;
            case"2":
                holder.sl_market.setVisibility(View.GONE);
                holder.sl_store.setVisibility(View.GONE);
                holder.sl_commodity.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ActivitiesViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.iv_activities_circle_icon)
        ImageView icon;
        @BindView(R.id.tv_activities_circle_name)
        TextView name;
        @BindView(R.id.sl_market)
        SwipeLayout sl_market;
        @BindView(R.id.sl_store)
        SwipeLayout sl_store;
        @BindView(R.id.sl_commodity)
        SwipeLayout sl_commodity;
        public ActivitiesViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
