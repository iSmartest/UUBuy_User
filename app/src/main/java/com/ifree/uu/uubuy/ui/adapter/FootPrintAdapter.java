package com.ifree.uu.uubuy.ui.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.app.MyApplication;
import com.ifree.uu.uubuy.mvp.entity.MyFootPrintEntity;
import com.ifree.uu.uubuy.ui.activity.BrandActivity;
import com.ifree.uu.uubuy.ui.activity.CarCommodityActivity;
import com.ifree.uu.uubuy.ui.activity.CommodityActivity;
import com.ifree.uu.uubuy.ui.activity.FurnitureMarketActivity;
import com.ifree.uu.uubuy.ui.activity.MarketActivity;
import com.ifree.uu.uubuy.ui.activity.ShoppingMallActivity;
import com.ifree.uu.uubuy.ui.activity.StoreActivity;
import com.ifree.uu.uubuy.uitls.GlideImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2018/9/19 0019
 * Description:
 */
public class FootPrintAdapter extends BaseAdapter {
    private Context context;
    private List<MyFootPrintEntity.DataBean.FootprintList.FootprintInfoList> footprintInfoList;

    public FootPrintAdapter(Context context, List<MyFootPrintEntity.DataBean.FootprintList.FootprintInfoList> footprintInfoList) {
        this.context = context;
        this.footprintInfoList = footprintInfoList;

    }

    @Override
    public int getCount() {
        return footprintInfoList == null ? 0 : footprintInfoList.size();
    }

    @Override
    public Object getItem(int position) {
        return footprintInfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final FootPrintViewHolder viewHolder;
        final MyFootPrintEntity.DataBean.FootprintList.FootprintInfoList mList = footprintInfoList.get(position);
        if (convertView != null) {
            viewHolder = (FootPrintViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_search,parent,false);
            viewHolder = new FootPrintViewHolder(convertView);
            convertView.setTag(viewHolder);
        }

        switch (mList.getActivitiesType()){
            case "0":
                viewHolder.mMarket.setVisibility(View.VISIBLE);
                viewHolder.mStore.setVisibility(View.GONE);
                viewHolder.mCommodity.setVisibility(View.GONE);
                viewHolder.address.setText(mList.getActivitiesAdAddress());
                viewHolder.name.setText(mList.getActivitiesName());
//                viewHolder.time.setText("活动时间：" + mList.get());
                GlideImageLoader.imageLoader(context,mList.getActivitiesPic(),viewHolder.icon);
                break;
            case "1":
                viewHolder.mMarket.setVisibility(View.GONE);
                viewHolder.mStore.setVisibility(View.VISIBLE);
                viewHolder.mCommodity.setVisibility(View.GONE);
                viewHolder.mName.setText(mList.getActivitiesName());
                viewHolder.mContent.setText(mList.getActivitiesAdAddress());
                GlideImageLoader.imageLoader(context,mList.getActivitiesPic(),viewHolder.mPicture);
                break;
            case "2":
                viewHolder.mMarket.setVisibility(View.GONE);
                viewHolder.mStore.setVisibility(View.GONE);
                viewHolder.mCommodity.setVisibility(View.VISIBLE);
                viewHolder.mCommodityName.setText(mList.getActivitiesName());
                viewHolder.mCommodityDec.setText(mList.getActivitiesDes());
                GlideImageLoader.imageLoader(context,mList.getActivitiesPic(),viewHolder.mCommodityPicture);
                viewHolder.mPrice.setText("￥"+mList.getActivitiesPresentPrice());
                viewHolder.mOldPrice.setText("￥"+mList.getActivitiesOriginalPrice());
                viewHolder.mOldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
                viewHolder.mSurplus.setText(mList.getActivitiesSurplusNum());
                viewHolder.mAddress.setText(mList.getActivitiesAdAddress());
                break;

        }
        viewHolder.mMarket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("fristActivitiesId",mList.getActivitiesId());
                bundle.putString("fristActivitiesType",mList.getType());
                bundle.putString("fristActivitiesName",mList.getActivitiesName());
                switch (mList.getType()){
                    case "1":
                        MyApplication.openActivity(context,ShoppingMallActivity.class,bundle);
                        break;
                    case "2"://超市
                        MyApplication.openActivity(context,MarketActivity.class,bundle);
                        break;
                    case "3":
                        MyApplication.openActivity(context,FurnitureMarketActivity.class,bundle);
                        break;
                    case "4":
                        MyApplication.openActivity(context,ShoppingMallActivity.class,bundle);
                        break;
                    case "5":
                        MyApplication.openActivity(context,ShoppingMallActivity.class,bundle);
                        break;
                    case "6":
                        MyApplication.openActivity(context,BrandActivity.class,bundle);
                        break;
                }
            }
        });
        viewHolder.mStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("fristActivitiesId",mList.getActivitiesId());
                bundle.putString("fristActivitiesType",mList.getType());
                bundle.putString("fristActivitiesName",mList.getActivitiesName());
                switch (mList.getType()){
                    case "1":
                        MyApplication.openActivity(context,StoreActivity.class,bundle);
                        break;
                    case "2"://超市
                        MyApplication.openActivity(context,StoreActivity.class,bundle);
                        break;
                    case "3":
                        MyApplication.openActivity(context,StoreActivity.class,bundle);
                        break;
                    case "4":
                        MyApplication.openActivity(context,BrandActivity.class,bundle);
                        break;
                    case "5":
                        MyApplication.openActivity(context,BrandActivity.class,bundle);
                        break;
                    case "6":
                        MyApplication.openActivity(context,BrandActivity.class,bundle);
                        break;
                }
            }
        });

        viewHolder.mCommodity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("commodityId",mList.getActivitiesId());
                bundle.putString("type",mList.getType());
                bundle.putString("fristActivitiesName",mList.getActivitiesName());
                switch (mList.getType()){
                    case "1":
                        MyApplication.openActivity(context,CommodityActivity.class,bundle);
                        break;
                    case "2"://超市
                        MyApplication.openActivity(context,CommodityActivity.class,bundle);
                        break;
                    case "3":
                        MyApplication.openActivity(context,CommodityActivity.class,bundle);
                        break;
                    case "4":
                        MyApplication.openActivity(context,CarCommodityActivity.class,bundle);
                        break;
                    case "5":
                        MyApplication.openActivity(context,CommodityActivity.class,bundle);
                        break;
                    case "6":
                        MyApplication.openActivity(context,CommodityActivity.class,bundle);
                        break;
                }
            }
        });

        return convertView;
    }

    public class FootPrintViewHolder{
        @BindView(R.id.rl_search_market)
        RelativeLayout mMarket;
        @BindView(R.id.iv_search_market_icon)
        ImageView icon;
        @BindView(R.id.tv_search_market_name)
        TextView name;
        @BindView(R.id.tv_search_market_time)
        TextView time;
        @BindView(R.id.tv_search_market_address)
        TextView address;

        @BindView(R.id.ll_search_store)
        LinearLayout mStore;
        @BindView(R.id.iv_search_store_picture)
        ImageView mPicture;
        @BindView(R.id.tv_search_store_name)
        TextView mName;
        @BindView(R.id.tv_search_store_content)
        TextView mContent;

        @BindView(R.id.rl_search_commodity)
        LinearLayout mCommodity;
        @BindView(R.id.iv_search_commodity_picture)
        ImageView mCommodityPicture;
        @BindView(R.id.tv_search_commodity_name)
        TextView mCommodityName;
        @BindView(R.id.tv_search_commodity_dec)
        TextView mCommodityDec;
        @BindView(R.id.tv_search_commodity_price)
        TextView mPrice;
        @BindView(R.id.tv_search_commodity_old_price)
        TextView mOldPrice;
        @BindView(R.id.tv_search_commodity_surplus)
        TextView mSurplus;
        @BindView(R.id.tv_commodity_store_address)
        TextView mAddress;
        public FootPrintViewHolder(View itemView) {
            ButterKnife.bind(this,itemView);
        }
    }
}
