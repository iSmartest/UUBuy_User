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
import com.ifree.uu.uubuy.service.entity.CommodityListEntity;
import com.ifree.uu.uubuy.service.entity.SecondActivitiesEntity;
import com.ifree.uu.uubuy.uitls.GlideImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2018/9/14 0014
 * Description:
 */
public class BrandAdapter extends RecyclerView.Adapter<BrandAdapter.BrandViewHolder>{
    private Context context;
    private List<CommodityListEntity.DataBean.CommodityList> mList;
    public BrandAdapter(Context context, List<CommodityListEntity.DataBean.CommodityList> mList) {
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public BrandViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_market_or_store,parent,false);
        BrandViewHolder viewHolder = new BrandViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BrandViewHolder holder, int position) {
        CommodityListEntity.DataBean.CommodityList commodityList = mList.get(position);
        holder.mName.setText(commodityList.getCommodityName());
        GlideImageLoader.imageLoader(context,commodityList.getCommodityPic(),holder.mPicture);
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class BrandViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_name)
        TextView mName;
        @BindView(R.id.tv_content)
        TextView mContent;
        @BindView(R.id.iv_picture)
        ImageView mPicture;
        public BrandViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
