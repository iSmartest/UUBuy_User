package com.ifree.uu.uubuy.ui.adapter;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.app.MyApplication;
import com.ifree.uu.uubuy.ui.activity.MallActivitiesDetailsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/12/20.
 * Description:
 */

public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.BannerViewHolder> {
    private Context context;
    public BannerAdapter(Context context) {
        this.context = context;
    }


    @NonNull
    @Override
    public BannerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recyclerview,parent,false);
        BannerViewHolder viewHolder = new BannerViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BannerViewHolder viewHolder, int i) {
        viewHolder.ll_banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyApplication.openActivity(context, MallActivitiesDetailsActivity.class);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 8;
    }

    public class BannerViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.ll_banner)
        LinearLayout ll_banner;
        public BannerViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
