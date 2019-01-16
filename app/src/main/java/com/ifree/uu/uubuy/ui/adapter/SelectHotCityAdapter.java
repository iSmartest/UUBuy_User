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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/9/6.
 * Description:
 */
public class SelectHotCityAdapter extends RecyclerView.Adapter<SelectHotCityAdapter.SelectHotCityViewHolder>{
    private Context context;
    private List<CityInfoBean.DataBean.HotCity> mList;

    public SelectHotCityAdapter(Context context, List<CityInfoBean.DataBean.HotCity> mList) {
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public SelectHotCityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_select_hot_city,parent,false);
        SelectHotCityViewHolder viewHolder = new SelectHotCityViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SelectHotCityViewHolder holder, int position) {
        CityInfoBean.DataBean.HotCity hotCity = mList.get(position);
        holder.tvHotCity.setText(hotCity.getCity());
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public class SelectHotCityViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_hot_city)
        TextView tvHotCity;
        public SelectHotCityViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
