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
import com.ifree.uu.uubuy.service.entity.CommodityInfoEntity;
import com.ifree.uu.uubuy.uitls.GlideImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2018/9/19 0019
 * Description:
 */
public class CarCommodityAdapter extends RecyclerView.Adapter<CarCommodityAdapter.CarCommodityViewHolder>{
    private Context context;
    private List<CommodityInfoEntity.DataBean.CarPointList> mList;
    public CarCommodityAdapter(Context context, List<CommodityInfoEntity.DataBean.CarPointList> mList) {
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public CarCommodityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_car_commodity,parent,false);
        CarCommodityViewHolder viewHolder = new CarCommodityViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CarCommodityViewHolder holder, int position) {
        CommodityInfoEntity.DataBean.CarPointList carPointList = mList.get(position);
        holder.mDec.setText(carPointList.getCarDesc());
        holder.mPoint.setText(carPointList.getCarSellPoint());
        GlideImageLoader.imageLoader(context,carPointList.getCarSellPointPic(),holder.mPicture);
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public class CarCommodityViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_car_picture)
        ImageView mPicture;
        @BindView(R.id.tv_car_point)
        TextView mPoint;
        @BindView(R.id.tv_car_dec)
        TextView mDec;
        public CarCommodityViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
