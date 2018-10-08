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
import com.ifree.uu.uubuy.mvp.entity.HomeEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/20.
 * Description:
 */
public class AdTypeAdapter extends RecyclerView.Adapter<AdTypeAdapter.AdTypeViewHolder>{
    private Context context;
    private List<HomeEntity.DataBean.AdTypeList> mAdTypeList;
    public AdTypeAdapter(Context context, List<HomeEntity.DataBean.AdTypeList> mAdTypeList) {
        this.context = context;
        this.mAdTypeList = mAdTypeList;
    }

    @NonNull
    @Override
    public AdTypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_ad_type,parent,false);
        AdTypeViewHolder viewHolder = new AdTypeViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdTypeViewHolder holder, int position) {
        HomeEntity.DataBean.AdTypeList mList = mAdTypeList.get(position);
        holder.mName.setText(mList.getAdTypeTitle());
        switch (position){
            case 0:
                holder.icon.setImageResource(R.drawable.zongheshangchang_home);
                break;case 1:
                holder.icon.setImageResource(R.drawable.zonghechangshi_home);
                break;case 2:
                holder.icon.setImageResource(R.drawable.jiajujiancai_home);
                break;case 3:
                holder.icon.setImageResource(R.drawable.qichezhanting_home);
                break;case 4:
                holder.icon.setImageResource(R.drawable.pinpaizhanshi_home);
                break;case 5:
                holder.icon.setImageResource(R.drawable.jiaoyu_home);
                break;
        }


//        GlideImageLoader.imageLoader(context,mList.getAdTypeIcon(),holder.icon);
    }

    @Override
    public int getItemCount() {
        return mAdTypeList == null ? 6 : mAdTypeList.size();
    }

    public class AdTypeViewHolder extends RecyclerView.ViewHolder{
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
