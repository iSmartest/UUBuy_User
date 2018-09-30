package com.ifree.uu.uubuy.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.service.entity.AroundEntity;
import com.ifree.uu.uubuy.service.entity.HomeEntity;
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
public class AroundAdapter extends RecyclerView.Adapter<AroundAdapter.AroundViewHolder>{

    private Context context;
    private List<AroundEntity.DataBean.ActivitiesList> mList;

    public AroundAdapter(Context context, List<AroundEntity.DataBean.ActivitiesList> mList) {
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public AroundViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_main,parent,false);
        AroundViewHolder viewHolder = new AroundViewHolder(view);
        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull AroundViewHolder holder, int position) {
        AroundEntity.DataBean.ActivitiesList activitiesList = mList.get(position);
        holder.name.setText(activitiesList.getActivitiesName());
        holder.time.setText("活动时间：" + TimeFormatUtils.modifyDataFormat2(activitiesList.getActivitiesTime()));
        holder.address.setText("活动地点：" + activitiesList.getActivitiesAdAddress());
        GlideImageLoader.imageLoader(context,activitiesList.getActivitiesPic(),holder.icon);
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public class AroundViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.iv_home_main_icon)
        ImageView icon;
        @BindView(R.id.tv_home_main_name)
        TextView name;
        @BindView(R.id.tv_home_main_time)
        TextView time;
        @BindView(R.id.tv_home_main_address)
        TextView address;
        public AroundViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
