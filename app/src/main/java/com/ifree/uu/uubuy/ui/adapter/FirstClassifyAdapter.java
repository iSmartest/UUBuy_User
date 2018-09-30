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
import com.ifree.uu.uubuy.service.entity.FirstClassifyEntity;
import com.ifree.uu.uubuy.uitls.GlideImageLoader;
import com.ifree.uu.uubuy.uitls.TimeFormatUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/23.
 * Description:
 */
public class FirstClassifyAdapter extends RecyclerView.Adapter<FirstClassifyAdapter.FirstClassifyViewHolder>{

    private Context context;
    private List<FirstClassifyEntity.DataBean.FristActivitiesList> mList;

    public FirstClassifyAdapter(Context context, List<FirstClassifyEntity.DataBean.FristActivitiesList> mList) {
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public FirstClassifyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_first_classify, parent, false);
        return new FirstClassifyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull FirstClassifyViewHolder holder, int position) {
        FirstClassifyEntity.DataBean.FristActivitiesList firstActivitiesList = mList.get(position);
        holder.mName.setText(firstActivitiesList.getFristActivitiesName());
        holder.mTime.setText("活动时间 " + TimeFormatUtils.modifyDataFormat2(firstActivitiesList.getFristActivitiesTime()));
        GlideImageLoader.imageLoader(context,firstActivitiesList.getFristActivitiesPic(),holder.mPicture);
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public class FirstClassifyViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_market_or_store_name)
        TextView mName;
        @BindView(R.id.iv_activities_picture)
        ImageView mPicture;
        @BindView(R.id.tv_activities_time)
        TextView mTime;
        public FirstClassifyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
