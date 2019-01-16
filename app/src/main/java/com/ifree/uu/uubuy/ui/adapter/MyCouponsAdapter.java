package com.ifree.uu.uubuy.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ifree.uu.uubuy.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2019/1/4 0004
 * Description:
 */
public class MyCouponsAdapter extends RecyclerView.Adapter<MyCouponsAdapter.MyCouponsViewHolder>{
    private Context context;
    private int couponStore = 1;
    public MyCouponsAdapter(Context context) {
        this.context = context;
    }
    public void setCouponState(int couponStore){
        this.couponStore = couponStore;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MyCouponsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_my_coupons, parent, false);
        MyCouponsViewHolder viewHolder = new MyCouponsViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyCouponsViewHolder viewHolder, int position) {
        switch (couponStore){
            case 1:
                viewHolder.tvState.setVisibility(View.GONE);
                break;
            case 2:
                viewHolder.tvState.setVisibility(View.VISIBLE);
                viewHolder.tvState.setText(R.string.coupon_used);
                break;
            case 3:
                viewHolder.tvState.setVisibility(View.VISIBLE);
                viewHolder.tvState.setText(R.string.expired);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public class MyCouponsViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.ll_my_coupons)
        LinearLayout llGetCoupons;
        @BindView(R.id.tv_my_coupon_price)
        TextView tvPrice;
        @BindView(R.id.tv_my_coupons_type)
        TextView tvCouponsType;
        @BindView(R.id.tv_my_coupon_name)
        TextView tvName;
        @BindView(R.id.tv_my_coupon_condition)
        TextView tvCondition;
        @BindView(R.id.tv_my_coupon_term_of_validity)
        TextView tvTime;
        @BindView(R.id.tv_my_check_get)
        TextView tvCheck;
        @BindView(R.id.tv_my_coupon_state)
        TextView tvState;
        public MyCouponsViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
