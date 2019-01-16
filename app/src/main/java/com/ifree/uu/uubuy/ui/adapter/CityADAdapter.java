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
import com.ifree.uu.uubuy.mvp.modle.HomeBean;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/20.
 * Description:
 */
public class CityADAdapter extends RecyclerView.Adapter<CityADAdapter.CityADADViewHolder> {
    private Context context;
    private List<HomeBean.DataBean.CityADList> mList;

    public CityADAdapter(Context context, List<HomeBean.DataBean.CityADList> mCityADList) {
        this.context = context;
        this.mList = mCityADList;
    }

    @NonNull
    @Override
    public CityADADViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_city_ad, parent, false);
        CityADADViewHolder viewHolder = new CityADADViewHolder(view);
        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final CityADADViewHolder holder, final int position) {

    }


    @Override
    public int getItemCount() {
        return 4;
    }

    public class CityADADViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ll_item_city_ad)
        LinearLayout llAD;
        @BindView(R.id.iv_city_ad_icon)
        ImageView icon;
        @BindView(R.id.tv_city_ad_name)
        TextView name;
        @BindView(R.id.tv_city_ad_time)
        TextView time;
        @BindView(R.id.tv_city_sign_up)
        TextView signUp;
        @BindView(R.id.tv_city_browsing)
        TextView browsing;

        public CityADADViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
