package com.ifree.uu.uubuy.ui.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.app.MyApplication;
import com.ifree.uu.uubuy.mvp.entity.MyFootPrintEntity;
import com.ifree.uu.uubuy.ui.activity.CarCommodityActivity;
import com.ifree.uu.uubuy.ui.activity.CommodityActivity;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_foot_print,parent,false);
            viewHolder = new FootPrintViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        viewHolder.mCommodity.setVisibility(View.VISIBLE);
        viewHolder.mCommodityName.setText(mList.getActivitiesName());
        viewHolder.mCommodityDec.setText(mList.getActivitiesDes());
        GlideImageLoader.imageLoader(context,mList.getActivitiesPic(),viewHolder.mCommodityPicture);
        viewHolder.mPrice.setText("￥"+mList.getActivitiesPresentPrice());
        viewHolder.mOldPrice.setText("￥"+mList.getActivitiesOriginalPrice());
        viewHolder.mOldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        viewHolder.mSurplus.setText(mList.getActivitiesSurplusNum());
        if (mList.getIsOver().equals("0")){
            viewHolder.mIsOver.setVisibility(View.VISIBLE);
        }else {
            viewHolder.mIsOver.setVisibility(View.GONE);
            viewHolder.mCommodity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("commodityId",mList.getActivitiesId());
                    bundle.putString("type",mList.getType());
                    bundle.putString("fristActivitiesName",mList.getActivitiesName());
                    bundle.putString("commodityIcon",mList.getActivitiesPic());
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
        }
        return convertView;
    }

    public class FootPrintViewHolder{
        @BindView(R.id.rl_foot_print_commodity)
        CardView mCommodity;
        @BindView(R.id.iv_foot_print_commodity_picture)
        ImageView mCommodityPicture;
        @BindView(R.id.tv_foot_print_commodity_name)
        TextView mCommodityName;
        @BindView(R.id.tv_foot_print_commodity_dec)
        TextView mCommodityDec;
        @BindView(R.id.tv_foot_print_commodity_price)
        TextView mPrice;
        @BindView(R.id.tv_foot_print_commodity_old_price)
        TextView mOldPrice;
        @BindView(R.id.tv_foot_print_commodity_surplus)
        TextView mSurplus;
        @BindView(R.id.tv_foot_print_is_over)
        TextView mIsOver;
        public FootPrintViewHolder(View itemView) {
            ButterKnife.bind(this,itemView);
        }
    }
}
