package com.ifree.uu.uubuy.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.app.MyApplication;
import com.ifree.uu.uubuy.custom.MyGridView;
import com.ifree.uu.uubuy.listener.RecyclerItemTouchListener;
import com.ifree.uu.uubuy.service.entity.SecondActivitiesEntity;
import com.ifree.uu.uubuy.ui.activity.MarketActivity;
import com.ifree.uu.uubuy.uitls.ToastUtils;

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
    public MarketAdapter(Context context, List<SecondActivitiesEntity.DataBean.MarketCommodityList> mList) {
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public MarketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_market,parent,false);
        MarketViewHolder viewHolder = new MarketViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MarketViewHolder holder, int position) {
        holder.my_gv.setLayoutManager(new GridLayoutManager(context,3));
        holder.my_gv.setNestedScrollingEnabled(false);
        MyGridAdapter myGridAdapter = new MyGridAdapter(context);
        holder.my_gv.setAdapter(myGridAdapter);
        holder.my_gv.addOnItemTouchListener(new RecyclerItemTouchListener(holder.my_gv) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
//                MyApplication.openActivity(context,MarketActivity.class);
                ToastUtils.makeText(context,"hhhhh");
            }
        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class MarketViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.my_gv)
        RecyclerView my_gv;
        public MarketViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
