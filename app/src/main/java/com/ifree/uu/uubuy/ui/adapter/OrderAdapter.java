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
import com.ifree.uu.uubuy.service.entity.OrderEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/20.
 * Description:
 */
public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder>{
    private Context context;
    private List<OrderEntity.OrderInfoList> mList;
    private String orderState;
    public OrderAdapter(Context context, List<OrderEntity.OrderInfoList> mList, String orderState) {
        this.context = context;
        this.mList = mList;
        this.orderState = orderState;
    }

    public void setType(String orderState) {
        this.orderState = orderState;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order,parent,false);
        OrderViewHolder viewHolder = new OrderViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        switch (orderState){
            case "0":
                holder.orderState.setText("已预定");
                holder.commodityAgain.setText("取消预定");
                break;
            case "1":
                holder.orderState.setText("已完成");
                holder.commodityAgain.setText("删除订单");
                break;
            case "2":
                holder.orderState.setText("已取消");
                holder.commodityAgain.setText("再次下单");
                break;
        }
    }

    @Override
    public int getItemCount() {
        return 10;
    }



    public class OrderViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_order_orderId)
        TextView orderId;
        @BindView(R.id.tv_order_orderState)
        TextView orderState;
        @BindView(R.id.tv_order_commodity_name)
        TextView commodityName;
        @BindView(R.id.tv_order_commodity_is_over)
        TextView isOver;
        @BindView(R.id.iv_order_commodity_icon)
        ImageView commodityIcon;
        @BindView(R.id.tv_order_commodity_dec)
        TextView commodityDec;
        @BindView(R.id.tv_order_commodity_price)
        TextView commodityPrice;
        @BindView(R.id.tv_order_store_address)
        TextView commodityAddress;
        @BindView(R.id.tv_order_again)
        TextView commodityAgain;
        public OrderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
