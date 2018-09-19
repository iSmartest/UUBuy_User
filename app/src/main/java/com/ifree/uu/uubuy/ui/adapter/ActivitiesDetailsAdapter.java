package com.ifree.uu.uubuy.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.service.entity.ActivitiesDetailsEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2018/9/19 0019
 * Description:
 */
public class ActivitiesDetailsAdapter extends RecyclerView.Adapter<ActivitiesDetailsAdapter.ActivitiesDetailsViewHolder> {
    private Context context;
    private List<ActivitiesDetailsEntity.DataBean.CouponList> mList;

    public ActivitiesDetailsAdapter(Context context, List<ActivitiesDetailsEntity.DataBean.CouponList> mList) {
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public ActivitiesDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_activities_coupon, parent, false);
        ActivitiesDetailsViewHolder viewHolder = new ActivitiesDetailsViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ActivitiesDetailsViewHolder holder, int position) {
        ActivitiesDetailsEntity.DataBean.CouponList couponList = mList.get(position);
        holder.mReducePrice.setText(couponList.getCouponValue());
        holder.mAllPrice.setText(couponList.getCouponCondition());
        holder.mType.setText(couponList.getCouponType());
        switch (couponList.getIsUse()){
            case "0":
                holder.mGet.setText("点击领取");
                break;
            case "1":
              holder.mGet.setText("已领取");
              break;
        }
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public static class ActivitiesDetailsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_coupon_reduce_price)
        TextView mReducePrice;
        @BindView(R.id.tv_coupon_all_price)
        TextView mAllPrice;
        @BindView(R.id.iv_get)
        TextView mGet;
        @BindView(R.id.tv_coupon_type)
        TextView mType;

        public ActivitiesDetailsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
