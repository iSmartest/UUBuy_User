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
import com.ifree.uu.uubuy.mvp.entity.UserInfoEntity;
import com.ifree.uu.uubuy.mvp.presenter.GetCouponPresenter;
import com.ifree.uu.uubuy.mvp.view.ProjectView;
import com.ifree.uu.uubuy.uitls.SPUtil;
import com.ifree.uu.uubuy.uitls.ToastUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/21.
 * Description:
 */
public class CouponCenterAdapter extends RecyclerView.Adapter<CouponCenterAdapter.CouponViewHolder>{

    private Context context;
    private List<CouponEntity.DataBean.CouponList> mList;
    private String couponType;
    private GetCouponPresenter mGetCouponPresenter;
    public CouponCenterAdapter(Context context, List<CouponEntity.DataBean.CouponList> mList, String couponType) {
        this.context = context;
        this.mList = mList;
        this.couponType = couponType;
        mGetCouponPresenter = new GetCouponPresenter(context);
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
        final CouponEntity.DataBean.CouponList couponList = mList.get(position);
        switch (couponType){
            case "0":
                holder.linearLayout.setBackgroundResource(R.drawable.coupon_uu);
                break;
            case "1":
                holder.linearLayout.setBackgroundResource(R.drawable.coupon_market);
                break;
            case "2":
                holder.linearLayout.setBackgroundResource(R.drawable.coupon_store);
                break;
        }
        holder.mReducePrice.setText(couponList.getCouponReducePrice() + "");
        holder.mAllPrice.setText("单笔" + couponList.getCondition());
        holder.mTime.setText("有效时间" + couponList.getSecuritiesTimeZone());
        holder.mStore.setText(couponList.getMsName());
        holder.mType.setText(couponList.getType());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uid = SPUtil.getString(context,"uid");
                mGetCouponPresenter.onCreate();
                mGetCouponPresenter.attachView(mGetCouponView);
                mGetCouponPresenter.getCoupon(uid,couponList.getCouponId(),"领取中...");
            }
        });
    }

    private ProjectView<UserInfoEntity> mGetCouponView = new ProjectView<UserInfoEntity>() {
        @Override
        public void onSuccess(UserInfoEntity mUserInfoEntity) {
            if (mUserInfoEntity.getResultCode().equals("1")){
                ToastUtils.makeText(context,mUserInfoEntity.getMsg());
                return;
            }
            ToastUtils.makeText(context,mUserInfoEntity.getMsg());
            notifyDataSetChanged();
        }

        @Override
        public void onError(String result) {
            ToastUtils.makeText(context,result);
        }
    };
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
        public CouponViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
