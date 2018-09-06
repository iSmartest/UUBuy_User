package com.ifree.uu.uubuy.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.service.entity.CityInfoEntity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/9/6.
 * Description:
 */
public class SelectCityAdapter extends RecyclerView.Adapter<SelectCityAdapter.SelectCityViewHolder>{
    private Context context;
    private ArrayList<? extends CityInfoEntity.DataBean.ProvinceList.CityList> mList;

    public SelectCityAdapter(Context context, ArrayList<? extends CityInfoEntity.DataBean.ProvinceList.CityList> mList) {
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public SelectCityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_province_city_area,parent,false);
        SelectCityViewHolder viewHolder = new SelectCityViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SelectCityViewHolder holder, int position) {
        CityInfoEntity.DataBean.ProvinceList.CityList cityList = mList.get(position);
        holder.mCity.setText(cityList.getCity());
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public class SelectCityViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.text_province_city_area)
        TextView mCity;
        public SelectCityViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
