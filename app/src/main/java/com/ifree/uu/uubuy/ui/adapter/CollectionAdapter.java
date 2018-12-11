package com.ifree.uu.uubuy.ui.adapter;

import android.content.Context;
import android.graphics.Paint;
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
import com.ifree.uu.uubuy.custom.swipeLayout.SwipeLayout;
import com.ifree.uu.uubuy.custom.swipeLayout.SwipeLayoutManager;
import com.ifree.uu.uubuy.dialog.LogOutDialog;
import com.ifree.uu.uubuy.mvp.entity.ActivitiesEntity;
import com.ifree.uu.uubuy.mvp.entity.UserInfoEntity;
import com.ifree.uu.uubuy.mvp.presenter.CollectionPresenter;
import com.ifree.uu.uubuy.mvp.view.ProjectView;
import com.ifree.uu.uubuy.ui.activity.BrandActivity;
import com.ifree.uu.uubuy.ui.activity.CarCommodityActivity;
import com.ifree.uu.uubuy.ui.activity.CommodityActivity;
import com.ifree.uu.uubuy.ui.activity.ShoppingMallActivity;
import com.ifree.uu.uubuy.ui.activity.ShopActivity;
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
public class CollectionAdapter extends RecyclerView.Adapter<CollectionAdapter.ActivitiesViewHolder>{
    private CollectionPresenter mCollectionPresenter;
    private Context context;
    private List<ActivitiesEntity.DataBean.ActivitiesList> mList;
    private String  activitiesType;

    public CollectionAdapter(Context context, List<ActivitiesEntity.DataBean.ActivitiesList> mList, String activitiesType) {
        this.context = context;
        this.mList = mList;
        this.activitiesType = activitiesType;
        mCollectionPresenter = new CollectionPresenter(context);


    }

    public void setType(String activitiesType){
        this.activitiesType = activitiesType;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ActivitiesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_activities_circle,parent,false);
        ActivitiesViewHolder viewHolder = new ActivitiesViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ActivitiesViewHolder holder, int position) {
        final ActivitiesEntity.DataBean.ActivitiesList activitiesList = mList.get(position);
        switch (activitiesType){
            case "0":
                if (activitiesList.getIsOver().equals("0")){
                    holder.mIsOver.setVisibility(View.VISIBLE);
                }else {
                    holder.mIsOver.setVisibility(View.GONE);
                    holder.sl_market.setOnSwipeLayoutClickListener(new SwipeLayout.OnSwipeLayoutClickListener() {
                        @Override
                        public void onClick() {
                            Bundle bundle = new Bundle();
                            bundle.putString("fristActivitiesId", activitiesList.getActivitiesId());
                            bundle.putString("fristActivitiesType", activitiesList.getType());
                            bundle.putString("fristActivitiesName", activitiesList.getActivitiesName());
                            MyApplication.openActivity(context,ShoppingMallActivity.class,bundle);
                        }
                    });
                }
                holder.sl_market.setVisibility(View.VISIBLE);
                holder.sl_store.setVisibility(View.GONE);
                holder.sl_commodity.setVisibility(View.GONE);
                holder.marketName.setText(activitiesList.getActivitiesName());
                GlideImageLoader.imageLoader(context,activitiesList.getActivitiesPic(),holder.marketIcon);
                break;
            case "1":
                holder.sl_market.setVisibility(View.GONE);
                holder.sl_store.setVisibility(View.VISIBLE);
                holder.sl_commodity.setVisibility(View.GONE);
                holder.storeName.setText(activitiesList.getActivitiesName());
                holder.storeAddress.setText(activitiesList.getActivitiesAdAddress());
                GlideImageLoader.imageLoader(context,activitiesList.getActivitiesPic(),holder.storeIcon);
                break;
            case"2":
                holder.sl_market.setVisibility(View.GONE);
                holder.sl_store.setVisibility(View.GONE);
                holder.sl_commodity.setVisibility(View.VISIBLE);
                holder.commodityName.setText(activitiesList.getActivitiesName());
                holder.commoditySurplus.setText(activitiesList.getActivitiesSurplusNum());
                holder.commodityNowPrice.setText("￥"+activitiesList.getActivitiesPresentPrice());
                holder.commodityOriginalPrice.setText("￥"+activitiesList.getActivitiesOriginalPrice());
                holder.commodityOriginalPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
                GlideImageLoader.imageLoader(context,activitiesList.getActivitiesPic(),holder.commodityIcon);
                break;
        }

        holder.marketDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogOutDialog dialog = new LogOutDialog(context, R.string.cancel_collection, new LogOutDialog.OnSureBtnClickListener() {
                    @Override
                    public void sure() {
                        cancelCollection(activitiesList.getActivitiesId(),"0");
                    }
                });
                dialog.show();
            }
        });
        holder.storeDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogOutDialog dialog = new LogOutDialog(context, R.string.cancel_collection, new LogOutDialog.OnSureBtnClickListener() {
                    @Override
                    public void sure() {
                        cancelCollection(activitiesList.getActivitiesId(),"1");
                    }
                });
                dialog.show();
            }
        });
        holder.commodityDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogOutDialog dialog = new LogOutDialog(context, R.string.cancel_collection, new LogOutDialog.OnSureBtnClickListener() {
                    @Override
                    public void sure() {
                        cancelCollection(activitiesList.getActivitiesId(),"2");
                    }
                });
                dialog.show();
            }
        });

        holder.sl_store.setOnSwipeLayoutClickListener(new SwipeLayout.OnSwipeLayoutClickListener() {
            @Override
            public void onClick() {
                Bundle bundle = new Bundle();
                bundle.putString("fristActivitiesId",activitiesList.getActivitiesId());
                bundle.putString("fristActivitiesType",activitiesList.getType());
                bundle.putString("fristActivitiesName",activitiesList.getActivitiesName());
                switch (activitiesList.getType()){
                    case "1":
                    case "2":
                    case "3":
                        MyApplication.openActivity(context, ShopActivity.class,bundle);
                        break;
                    case "4":
                    case "5":
                    case "6":
                        MyApplication.openActivity(context, BrandActivity.class,bundle);
                        break;
                }
            }
        });
        holder.sl_commodity.setOnSwipeLayoutClickListener(new SwipeLayout.OnSwipeLayoutClickListener() {
            @Override
            public void onClick() {
                Bundle bundle = new Bundle();
                bundle.putString("commodityId",activitiesList.getActivitiesId());
                bundle.putString("type", activitiesList.getType());
                if (activitiesList.getType().equals("4")){
                    MyApplication.openActivity(context, CarCommodityActivity.class, bundle);
                }else {
                    MyApplication.openActivity(context, CommodityActivity.class, bundle);
                }

            }
        });
    }

    private void cancelCollection(String activitiesId,String type) {
        mCollectionPresenter.onCreate();
        mCollectionPresenter.getSubmitIsCollection(SPUtil.getUid(context),activitiesId,type,"0","处理中...");
        mCollectionPresenter.attachView(new ProjectView<UserInfoEntity>() {
            @Override
            public void onSuccess(UserInfoEntity mUserInfoEntity) {
                if (mUserInfoEntity.getResultCode().equals("1")){
                    ToastUtils.makeText(context,mUserInfoEntity.getMsg());
                    return;
                }
                ToastUtils.makeText(context,mUserInfoEntity.getMsg());
                SwipeLayoutManager.getInstance().closeOpenInstance();
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

    public class ActivitiesViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.sl_market)
        SwipeLayout sl_market;
        @BindView(R.id.sl_store)
        SwipeLayout sl_store;
        @BindView(R.id.sl_commodity)
        SwipeLayout sl_commodity;
        @BindView(R.id.tv_is_over)
        TextView mIsOver;
        @BindView(R.id.id_item_btn)
        TextView marketDelete;
        @BindView(R.id.tv_activities_circle_name)
        TextView marketName;
        @BindView(R.id.iv_activities_circle_icon)
        ImageView marketIcon;
        @BindView(R.id.id_item_store_btn)
        TextView storeDelete;
        @BindView(R.id.iv_activities_circle_store_icon)
        ImageView storeIcon;
        @BindView(R.id.tv_activities_circle_store_name)
        TextView storeName;
        @BindView(R.id.tv_activities_circle_store_address)
        TextView storeAddress;
        @BindView(R.id.id_item_commodity_btn)
        TextView commodityDelete;
        @BindView(R.id.iv_activities_circle_commodity_icon)
        ImageView commodityIcon;
        @BindView(R.id.iv_activities_circle_commodity_name)
        TextView commodityName;
        @BindView(R.id.iv_activities_circle_commodity_dec)
        TextView commodityDec;
        @BindView(R.id.tv_activities_commodity_surplus)
        TextView commoditySurplus;
        @BindView(R.id.iv_activities_circle_commodity_now_price)
        TextView commodityNowPrice;
        @BindView(R.id.iv_activities_circle_commodity_original_price)
        TextView commodityOriginalPrice;
        public ActivitiesViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
