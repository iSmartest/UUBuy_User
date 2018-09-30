package com.ifree.uu.uubuy.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.app.MyApplication;
import com.ifree.uu.uubuy.service.entity.ActivitiesDetailsEntity;
import com.ifree.uu.uubuy.service.entity.UserInfoEntity;
import com.ifree.uu.uubuy.service.presenter.GetCouponPresenter;
import com.ifree.uu.uubuy.service.view.ProjectView;
import com.ifree.uu.uubuy.ui.activity.BindingPhoneActivity;
import com.ifree.uu.uubuy.uitls.SPUtil;
import com.ifree.uu.uubuy.uitls.ToastUtils;

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
    private GetCouponPresenter mGetCouponPresenter;
    public ActivitiesDetailsAdapter(Context context, List<ActivitiesDetailsEntity.DataBean.CouponList> mList) {
        this.context = context;
        this.mList = mList;
        mGetCouponPresenter = new GetCouponPresenter(context);
    }

    @NonNull
    @Override
    public ActivitiesDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_activities_coupon, parent, false);
        ActivitiesDetailsViewHolder viewHolder = new ActivitiesDetailsViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ActivitiesDetailsViewHolder holder, int position) {
        final ActivitiesDetailsEntity.DataBean.CouponList couponList = mList.get(position);
        holder.mReducePrice.setText(String.valueOf(couponList.getCouponValue()));
        holder.mAllPrice.setText(couponList.getCouponCondition());
        holder.mType.setText(couponList.getCouponType());
        switch (couponList.getIsUse()){
            case "0":
                holder.mGet.setText("点击领取");
                holder.mGet.setBackgroundResource(R.drawable.shape_background_coupon_sure);
                holder.mGet.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String uid = SPUtil.getString(context,"uid");
                        if (TextUtils.isEmpty(uid)){
                            ToastUtils.makeText(context,"用户未登录，请登录");
                            return;
                        }
                        if (SPUtil.getString(context,"isPhone").equals("0")){
                            ToastUtils.makeText(context,"请绑定手机号");
                            MyApplication.openActivity(context,BindingPhoneActivity.class);
                            return;
                        }
                        mGetCouponPresenter.onCreate();
                        mGetCouponPresenter.getCoupon(uid,couponList.getCouponId(),"领取中...");
                        mGetCouponPresenter.attachView(new ProjectView<UserInfoEntity>() {
                            @Override
                            public void onSuccess(UserInfoEntity mUserInfoEntity) {
                                if (mUserInfoEntity.getResultCode().equals("1")){
                                    ToastUtils.makeText(context,mUserInfoEntity.getMsg());
                                    return;
                                }
                                ToastUtils.makeText(context,mUserInfoEntity.getMsg());
                                holder.mGet.setText("已领取");
                                couponList.setIsUse("1");
                                holder.mGet.setBackgroundResource(R.drawable.shape_background_coupon_used);
                                notifyDataSetChanged();
                            }

                            @Override
                            public void onError(String result) {
                                ToastUtils.makeText(context,result);
                            }
                        });
                    }
                });
                break;
            case "1":
                holder.mGet.setText("已领取");
                holder.mGet.setBackgroundResource(R.drawable.shape_background_coupon_used);
              break;
        }
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public static class ActivitiesDetailsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_reduce_price)
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
