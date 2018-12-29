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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.app.MyApplication;
import com.ifree.uu.uubuy.mvp.entity.AroundEntity;
import com.ifree.uu.uubuy.ui.activity.ShoppingMallActivity;
import com.ifree.uu.uubuy.ui.activity.ShopActivity;
import com.ifree.uu.uubuy.uitls.GlideImageLoader;
import com.ifree.uu.uubuy.uitls.SPUtil;
import com.ifree.uu.uubuy.uitls.TimeFormatUtils;

import java.util.List;
import java.util.Map;

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
    public void onBindViewHolder(@NonNull final AroundViewHolder holder, final int position) {
        final AroundEntity.DataBean.ActivitiesList activitiesList = mList.get(position);
        Map<String,String> spMap = SPUtil.getMap(context,"key");
        holder.name.setText(activitiesList.getActivitiesName());
        holder.time.setText("活动时间：" + TimeFormatUtils.modifyDataFormat2(activitiesList.getActivitiesTime()));
        holder.address.setText("活动地点：" + activitiesList.getActivitiesAdAddress());
        GlideImageLoader.imageLoader(context,activitiesList.getActivitiesPic(),holder.icon);
        holder.signUp.setText("报名：" + activitiesList.getSignUp() + "人");
        if (spMap == null || spMap.size() == 0){
            holder.browsingVolume.setText("浏览：" + activitiesList.getBrowsing()  + "人");
        }else {
            if (spMap.containsKey(activitiesList.getaId())){
                int temp = activitiesList.getBrowsing() + Integer.valueOf(spMap.get(activitiesList.getaId()));
                holder.browsingVolume.setText("浏览：" + temp + "人");
            }else {
                holder.browsingVolume.setText("浏览：" + activitiesList.getBrowsing()  + "人");
            }
        }
        holder.rl_activities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,String> currentMap = SPUtil.getMap(context,"key");
                if (currentMap == null || currentMap.size() == 0){
                    currentMap.put(activitiesList.getaId(), 1 + "");
                }else {
                    if (currentMap.containsKey(activitiesList.getaId())){
                        currentMap.put(activitiesList.getaId(), (Integer.valueOf(currentMap.get(activitiesList.getaId())) + 1)+"");
                    }else {
                        currentMap.put(activitiesList.getaId(), 1 + "");
                    }
                }
                int temp = activitiesList.getBrowsing() + Integer.valueOf(currentMap.get(activitiesList.getaId()));
                holder.browsingVolume.setText("浏览：" + temp + "人");
                SPUtil.putMap(context,"key",currentMap);
                Bundle bundle = new Bundle();
                bundle.putString("fristActivitiesId",activitiesList.getActivitiesId());
                bundle.putString("fristActivitiesType",activitiesList.getType());
                bundle.putString("fristActivitiesName",activitiesList.getActivitiesName());
                switch (activitiesList.getType()){// 1 商城 2 超市 3 建材 4 车 5 品牌 6 教育
                    case "1":
                        if (activitiesList.getActivitiesType().equals("1")){
                            MyApplication.openActivity(context, ShopActivity.class,bundle);
                        }else {
                            MyApplication.openActivity(context,ShoppingMallActivity.class,bundle);
                        }
                        break;
                }
            }
        });
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
        @BindView(R.id.tv_home_main_browsing_volume)
        TextView browsingVolume;
        @BindView(R.id.tv_home_main_sign_up)
        TextView signUp;
        @BindView(R.id.rl_activities)
        RelativeLayout rl_activities;
        public AroundViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
