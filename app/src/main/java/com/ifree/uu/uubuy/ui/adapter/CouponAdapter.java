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
import com.ifree.uu.uubuy.mvp.entity.CouponEntity;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/21.
 * Description:
 */
public class CouponAdapter extends RecyclerView.Adapter<CouponAdapter.CouponViewHolder>{

    private Context context;
    private List<CouponEntity.DataBean.CouponList> mList;
    private String couponType;
    public CouponAdapter(Context context, List<CouponEntity.DataBean.CouponList> mList,String couponType) {
        this.context = context;
        this.mList = mList;
        this.couponType = couponType;
    }

    @NonNull
    @Override
    public CouponViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_my_coupon,parent,false);
        CouponViewHolder viewHolder = new CouponViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CouponViewHolder holder, int position) {
        CouponEntity.DataBean.CouponList couponList = mList.get(position);
        switch (couponType){
            case "0":
                holder.linearLayout.setBackgroundResource(R.drawable.coupon_uu);
                break;
            case "1":
                holder.linearLayout.setBackgroundResource(R.drawable.coupon_used_background);
                break;
            case "2":
                holder.linearLayout.setBackgroundResource(R.drawable.coupon_used_background);
                break;
        }
        holder.mCoupon.setVisibility(View.GONE);
        holder.mReducePrice.setText(couponList.getCouponReducePrice() + "");
        holder.mAllPrice.setText(couponList.getCondition());
        holder.mTime.setText("有效时间:" + couponList.getSecuritiesTimeZone());
        holder.mStore.setText(couponList.getMsName());
        holder.mType.setText(couponList.getType());

    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public static class CouponViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.ll_item_coupon_background)
        LinearLayout linearLayout;
        @BindView(R.id.tv_coupon_reduce_price)
        TextView mReducePrice;
        @BindView(R.id.tv_coupon_all_price)
        TextView mAllPrice;
        @BindView(R.id.tv_coupon_type)
        TextView mType;
        @BindView(R.id.tv_coupon_time)
        TextView mTime;
        @BindView(R.id.tv_coupon_for_store)
        TextView mStore;
        @BindView(R.id.tv_my_coupon)
        TextView mCoupon;
        public CouponViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
