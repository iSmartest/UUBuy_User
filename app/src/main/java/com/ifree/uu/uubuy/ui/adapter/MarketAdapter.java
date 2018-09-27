package com.ifree.uu.uubuy.ui.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.app.MyApplication;
import com.ifree.uu.uubuy.listener.RecyclerItemTouchListener;
import com.ifree.uu.uubuy.service.entity.SecondActivitiesEntity;
import com.ifree.uu.uubuy.ui.activity.CommodityActivity;
import com.ifree.uu.uubuy.ui.activity.MoreActivity;
import com.ifree.uu.uubuy.ui.activity.StoreActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/24.
 * Description:
 */
public class MarketAdapter extends RecyclerView.Adapter<MarketAdapter.MarketViewHolder>{

    private Context context;
    private List<SecondActivitiesEntity.DataBean.MarketCommodityList> mList;
    private String fristActivitiesId;
    public MarketAdapter(Context context, List<SecondActivitiesEntity.DataBean.MarketCommodityList> mList,String fristActivitiesId) {
        this.context = context;
        this.mList = mList;
        this.fristActivitiesId = fristActivitiesId;
    }

    @NonNull
    @Override
    public MarketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_market,parent,false);
        MarketViewHolder viewHolder = new MarketViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MarketViewHolder holder, final int position) {
        final SecondActivitiesEntity.DataBean.MarketCommodityList marketCommodityList = mList.get(position);
        holder.mType.setText(marketCommodityList.getCommodityTitle());
        holder.my_gv.setLayoutManager(new GridLayoutManager(context,3));
        holder.my_gv.setNestedScrollingEnabled(false);
        MyGridAdapter myGridAdapter = new MyGridAdapter(context,marketCommodityList.getCommodityList());
        holder.my_gv.setAdapter(myGridAdapter);
//        holder.my_gv.addOnItemTouchListener(new RecyclerItemTouchListener(holder.my_gv) {
//            @Override
//            public void onItemClick(RecyclerView.ViewHolder vh) {
//                int i = vh.getAdapterPosition();
//                if (i < 0 | i >= mList.size()){
//                    return;
//                }
//                Bundle bundle = new Bundle();
//                switch (marketCommodityList.getCommodityList().get(i).getCommodityType()){
//                    case "0":
//                        bundle.putString("fristActivitiesId",marketCommodityList.getCommodityList().get(i).getCommodityId());
//                        bundle.putString("fristActivitiesType",marketCommodityList.getCommodityList().get(i).getCommodityType());
//                        bundle.putString("fristActivitiesName",marketCommodityList.getCommodityList().get(i).getCommodityName());
//                        MyApplication.openActivity(context,StoreActivity.class,bundle);
//                        break;
//                    case "1":
//                        bundle.putString("commodityId",marketCommodityList.getCommodityList().get(i).getCommodityId());
//                        bundle.putString("type", marketCommodityList.getCommodityList().get(i).getCommodityType());
//                        bundle.putString("commodityIcon",marketCommodityList.getCommodityList().get(i).getCommodityPic());
//                        MyApplication.openActivity(context, CommodityActivity.class, bundle);
//                        break;
//                }
//            }
//        });
        holder.mMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("classifyId",marketCommodityList.getClassfyId());
                bundle.putString("fristActivitiesId",fristActivitiesId);
                MyApplication.openActivity(context,MoreActivity.class,bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public class MarketViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_type)
        TextView mType;
        @BindView(R.id.ll_more)
        LinearLayout mMore;
        @BindView(R.id.my_gv)
        RecyclerView my_gv;
        public MarketViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
