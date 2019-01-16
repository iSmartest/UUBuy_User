package com.ifree.uu.uubuy.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.mvp.modle.CityInfoBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/9/6.
 * Description:
 */
public class SelectProvinceAdapter extends RecyclerView.Adapter<SelectProvinceAdapter.SelectProvinceViewHolder>{
    private Context context;
    private ArrayList<? extends CityInfoBean.DataBean.ProvinceList> mList;

    public SelectProvinceAdapter(Context context, ArrayList<? extends CityInfoBean.DataBean.ProvinceList> mList) {
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public SelectProvinceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_province_city_area,parent,false);
        SelectProvinceViewHolder viewHolder = new SelectProvinceViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SelectProvinceViewHolder holder, int position) {
        CityInfoBean.DataBean.ProvinceList provinceList = mList.get(position);
        holder.mProvince.setText(provinceList.getProvince());
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public class SelectProvinceViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.text_province_city_area)
        TextView mProvince;
        public SelectProvinceViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
