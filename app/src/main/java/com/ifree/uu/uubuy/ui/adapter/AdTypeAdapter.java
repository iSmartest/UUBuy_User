package com.ifree.uu.uubuy.ui.adapter;

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
import com.ifree.uu.uubuy.ui.activity.FirstClassifyActivity;
import com.ifree.uu.uubuy.ui.activity.WaitSettledActivity;
import com.ifree.uu.uubuy.uitls.GlideImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/20.
 * Description:
 */
public class AdTypeAdapter extends RecyclerView.Adapter<AdTypeAdapter.AdTypeViewHolder> {
    private Context context;
    private List<HomeEntity.DataBean.AdTypeList> mAdTypeList;

    public AdTypeAdapter(Context context, List<HomeEntity.DataBean.AdTypeList> mAdTypeList) {
        this.context = context;
        this.mAdTypeList = mAdTypeList;
    }

    @NonNull
    @Override
    public AdTypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_ad_type, parent, false);
        AdTypeViewHolder viewHolder = new AdTypeViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdTypeViewHolder holder, final int position) {
        final HomeEntity.DataBean.AdTypeList mList = mAdTypeList.get(position);
        holder.mName.setText(mList.getAdTypeTitle());
        switch (mList.getType()) {
            case "1":
                GlideImageLoader.adTypeImageLoader(context, mList.getAdTypeIcon(), holder.icon, "1");
                break;
            case "2":
                GlideImageLoader.adTypeImageLoader(context, mList.getAdTypeIcon(), holder.icon, "2");
                break;
            case "3":
                GlideImageLoader.adTypeImageLoader(context, mList.getAdTypeIcon(), holder.icon, "3");
                break;
            case "4":
                GlideImageLoader.adTypeImageLoader(context, mList.getAdTypeIcon(), holder.icon, "4");
                break;
            case "5":
                GlideImageLoader.adTypeImageLoader(context, mList.getAdTypeIcon(), holder.icon, "5");
                break;
            case "6":
                GlideImageLoader.adTypeImageLoader(context, mList.getAdTypeIcon(), holder.icon, "6");
                break;
        }
        holder.mAdType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                if (position == 0){
                    bundle.putString("adTypeId", mList.getAdTypeId());
                    bundle.putString("type", mList.getType());
                    bundle.putString("title", mList.getAdTypeTitle());
                    MyApplication.openActivity(context, FirstClassifyActivity.class, bundle);
                }else {
                    bundle.putString("title", mList.getAdTypeTitle());
                    MyApplication.openActivity(context, WaitSettledActivity.class, bundle);
                }
            }
        });


//        GlideImageLoader.imageLoader(context,mList.getAdTypeIcon(),holder.icon);
    }

    @Override
    public int getItemCount() {
        return mAdTypeList == null ? 6 : mAdTypeList.size();
    }

    public class AdTypeViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ll_ad_type)
        LinearLayout mAdType;
        @BindView(R.id.iv_ad_type)
        ImageView icon;
        @BindView(R.id.tv_ad_type)
        TextView mName;

        public AdTypeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
