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
public class GetCouponsAdapter extends RecyclerView.Adapter<GetCouponsAdapter.GetCouponsViewHolder>{
    private Context context;
    public GetCouponsAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public GetCouponsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_get_coupons, parent, false);
        GetCouponsViewHolder viewHolder = new GetCouponsViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GetCouponsViewHolder getCouponsViewHolder, int position) {

    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public class GetCouponsViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.ll_get_coupons)
        LinearLayout llGetCoupons;
        @BindView(R.id.tv_coupon_price)
        TextView tvPrice;
        @BindView(R.id.tv_get_coupons_type)
        TextView tvCouponsType;
        @BindView(R.id.tv_get_coupon_name)
        TextView tvName;
        @BindView(R.id.tv_get_coupon_condition)
        TextView tvCondition;
        @BindView(R.id.tv_coupon_term_of_validity)
        TextView tvTime;
        @BindView(R.id.tv_check_get)
        TextView tvCheck;
        public GetCouponsViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
