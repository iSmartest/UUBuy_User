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
public class SelectHotAreaAdapter extends RecyclerView.Adapter<SelectHotAreaAdapter.SelectHotAreaViewHolder>{

    private Context context;
    private ArrayList<? extends CityInfoEntity.DataBean.HotCity.TownList> mList;

    public SelectHotAreaAdapter(Context context, ArrayList<? extends CityInfoEntity.DataBean.HotCity.TownList> mList) {
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public SelectHotAreaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_province_city_area,parent,false);
        SelectHotAreaViewHolder viewHolder = new SelectHotAreaViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SelectHotAreaViewHolder holder, int position) {
        CityInfoEntity.DataBean.HotCity.TownList townList = mList.get(position);
        holder.mArea.setText(townList.getTown());
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public class SelectHotAreaViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.text_province_city_area)
        TextView mArea;
        public SelectHotAreaViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
