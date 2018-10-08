package com.ifree.uu.uubuy.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.app.MyApplication;
import com.ifree.uu.uubuy.mvp.entity.HomeEntity;
import com.ifree.uu.uubuy.ui.activity.BrandActivity;
import com.ifree.uu.uubuy.ui.activity.FurnitureMarketActivity;
import com.ifree.uu.uubuy.ui.activity.MarketActivity;
import com.ifree.uu.uubuy.ui.activity.ShoppingMallActivity;
import com.ifree.uu.uubuy.ui.activity.StoreActivity;
import com.ifree.uu.uubuy.uitls.GlideImageLoader;
import com.ifree.uu.uubuy.uitls.TimeFormatUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/20.
 * Description:
 */
public class CityADAdapter extends RecyclerView.Adapter<CityADAdapter.CityADADViewHolder>{
    private Context context;
    private List<HomeEntity.DataBean.CityADList> mCityADList;
    public CityADAdapter(Context context, List<HomeEntity.DataBean.CityADList> mCityADList) {
        this.context = context;
        this.mCityADList = mCityADList;
    }

    @NonNull
    @Override
    public CityADADViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_city_ad,parent,false);
        CityADADViewHolder viewHolder = new CityADADViewHolder(view);
        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CityADADViewHolder holder, final int position) {
        final HomeEntity.DataBean.CityADList cityADList = mCityADList.get(position);
        holder.name.setText(cityADList.getCityADName());
        holder.time.setText(TimeFormatUtils.modifyDataFormat(cityADList.getCityADStartTime()) + "—" +TimeFormatUtils.modifyDataFormat(cityADList.getCityADEndTime()));
        GlideImageLoader.imageLoader(context,cityADList.getCityADPic(),holder.icon);
        holder.llAD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("fristActivitiesId", cityADList.getCityADId());
                bundle.putString("fristActivitiesType", cityADList.getType());
                bundle.putString("fristActivitiesName", cityADList.getCityADName());
                switch (cityADList.getType()) {// 1 商城 2 超市 3 建材 4 车 5 品牌 6 教育
                    case "1":
                        if (cityADList.getCityADType().equals("1")) {
                            MyApplication.openActivity(context, StoreActivity.class, bundle);
                        } else {
                            MyApplication.openActivity(context, ShoppingMallActivity.class, bundle);
                        }
                        break;
                    case "2"://超市
                        if (cityADList.getCityADType().equals("1")) {
                            MyApplication.openActivity(context, StoreActivity.class, bundle);
                        } else {
                            MyApplication.openActivity(context, MarketActivity.class, bundle);
                        }
                        break;
                    case "3":
                        if (cityADList.getCityADType().equals("1")) {
                            MyApplication.openActivity(context, StoreActivity.class, bundle);
                        } else {
                            MyApplication.openActivity(context, FurnitureMarketActivity.class, bundle);
                        }
                        break;
                    case "4":
                        if (cityADList.getCityADType().equals("1")) {
                            MyApplication.openActivity(context, BrandActivity.class, bundle);
                        } else {
                            MyApplication.openActivity(context, ShoppingMallActivity.class, bundle);
                        }
                        break;
                    case "5":
                        if (cityADList.getCityADType().equals("1")) {
                            MyApplication.openActivity(context, BrandActivity.class, bundle);
                        } else {
                            MyApplication.openActivity(context, ShoppingMallActivity.class, bundle);
                        }
                        break;
                    case "6":
                        MyApplication.openActivity(context, BrandActivity.class, bundle);
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCityADList == null ? 4 : mCityADList.size();
    }

    public class CityADADViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.ll_item_city_ad)
        LinearLayout llAD;
        @BindView(R.id.iv_city_ad_icon)
        ImageView icon;
        @BindView(R.id.tv_city_ad_name)
        TextView name;
        @BindView(R.id.tv_city_ad_time)
        TextView time;
        public CityADADViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
