package com.ifree.uu.uubuy.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.app.MyApplication;
import com.ifree.uu.uubuy.mvp.entity.OrderEntity;
import com.ifree.uu.uubuy.mvp.entity.UserInfoEntity;
import com.ifree.uu.uubuy.mvp.presenter.OperationOrderPresenter;
import com.ifree.uu.uubuy.mvp.view.ProjectView;
import com.ifree.uu.uubuy.ui.activity.CommodityReserveActivity;
import com.ifree.uu.uubuy.uitls.GlideImageLoader;
import com.ifree.uu.uubuy.uitls.SPUtil;
import com.ifree.uu.uubuy.uitls.ToastUtils;

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
    private List<OrderEntity.DataBean.OrderInfoList> mList;
    private String orderState;
    private OperationOrderPresenter mOperationOrderPresenter;
    public OrderAdapter(Context context, List<OrderEntity.DataBean.OrderInfoList> mList, String orderState) {
        this.context = context;
        this.mList = mList;
        this.orderState = orderState;
        mOperationOrderPresenter = new OperationOrderPresenter(context);
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
        final OrderEntity.DataBean.OrderInfoList orderInfoList = mList.get(position);
        switch (orderState){
            case "0":
                switch (orderInfoList.getIsOver()){
                    case "0":
                        holder.isOver.setVisibility(View.VISIBLE);
                        holder.commodityAgain.setVisibility(View.GONE);
                        break;
                    case "1":
                        holder.isOver.setVisibility(View.GONE);
                        holder.commodityAgain.setVisibility(View.VISIBLE);
                        holder.commodityAgain.setText("取消预定");
                        break;
                }
                holder.orderDelete.setVisibility(View.GONE);
                holder.commodityAgain.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        submitOperationOrder(orderInfoList.getOrderId(),"0");
                    }
                });
                break;
            case "1":
                holder.orderDelete.setVisibility(View.VISIBLE);
                holder.commodityAgain.setText("再次下单");
                holder.commodityAgain.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putString("commodityIcon",orderInfoList.getCommodityIcon());
                        bundle.putString("commodityName",orderInfoList.getCommodityDec());
                        bundle.putString("commodityBrandName",orderInfoList.getCommodityTitle());
                        bundle.putString("commodityPrice",orderInfoList.getCommodityPresentPrice());
                        bundle.putString("commodityAddress",orderInfoList.getStoreAddress());
                        bundle.putString("commodityId",orderInfoList.getCommodityid());
                        bundle.putString("commodityType",orderInfoList.getType());
                        bundle.putString("shopId",orderInfoList.getShopId());
                        MyApplication.openActivity(context,CommodityReserveActivity.class,bundle);
                    }
                });
                break;
            case "2":
                holder.orderDelete.setVisibility(View.VISIBLE);
                holder.commodityAgain.setText("恢复预订");
                holder.commodityAgain.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        submitOperationOrder(orderInfoList.getOrderId(),"2");
                    }
                });
                break;
        }
        holder.orderId.setText("订单号" + orderInfoList.getOrderId());
        holder.commodityName.setText(orderInfoList.getCommodityTitle());
        GlideImageLoader.imageLoader(context,orderInfoList.getCommodityIcon(),holder.commodityIcon);
        holder.commodityDec.setText(orderInfoList.getCommodityDec());
        holder.commodityNum.setText("x" + orderInfoList.getCommodityNum());
        holder.commodityPrice.setText("￥" + orderInfoList.getCommodityPresentPrice());
        holder.commodityAddress.setText(orderInfoList.getStoreAddress());
        holder.orderTime.setText("下单时间：" + orderInfoList.getOrderTime());
        holder.orderDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitOperationOrder(orderInfoList.getOrderId(),"1");
            }
        });
    }

    private void submitOperationOrder(String orderId, String type) {
        mOperationOrderPresenter.onCreate();
        mOperationOrderPresenter.getSubmitOperationOrder(orderId,type,SPUtil.getUid(context),"提交中...");
        mOperationOrderPresenter.attachView(new ProjectView<UserInfoEntity>() {
            @Override
            public void onSuccess(UserInfoEntity mUserInfoEntity) {
                if (mUserInfoEntity.getResultCode().equals("1")){
                    ToastUtils.makeText(context,mUserInfoEntity.getMsg());
                    return;
                }

                ToastUtils.makeText(context,mUserInfoEntity.getMsg());
                Intent intent = new Intent();
                intent.setAction("com.ifree.uu.order.changed");
                context.sendBroadcast(intent);
            }

            @Override
            public void onError(String result) {
                ToastUtils.makeText(context,result);
            }
        });
    }


    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }


    public class OrderViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_order_orderId)
        TextView orderId;
        @BindView(R.id.tv_order_delete)
        TextView orderDelete;
        @BindView(R.id.tv_order_commodity_name)
        TextView commodityName;
        @BindView(R.id.tv_order_commodity_is_over)
        TextView isOver;
        @BindView(R.id.iv_order_commodity_icon)
        ImageView commodityIcon;
        @BindView(R.id.tv_order_commodity_dec)
        TextView commodityDec;
        @BindView(R.id.tv_commodity_num)
        TextView commodityNum;
        @BindView(R.id.tv_order_commodity_price)
        TextView commodityPrice;
        @BindView(R.id.tv_order_time)
        TextView orderTime;
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
