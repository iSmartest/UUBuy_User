package com.ifree.uu.uubuy.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.service.entity.HomeEntity;

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
    private List<HomeEntity.CityADList> mCityADList;
    public CityADAdapter(Context context, List<HomeEntity.CityADList> mCityADList) {
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

    @Override
    public void onBindViewHolder(@NonNull CityADADViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class CityADADViewHolder extends RecyclerView.ViewHolder{
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
