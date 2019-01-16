package com.ifree.uu.uubuy.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.utils.GlideImageLoader;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2018/9/14 0014
 * Description:
 */
public class ProductInfoAdapter extends RecyclerView.Adapter<ProductInfoAdapter.ProductInfoViewHolder>{
    private Context context;
    private List<String> mList;
    private int defItem = 0;//声明默认选中的项

    public ProductInfoAdapter(Context context) {
        this.context = context;

    }

    public void setDefSelect(int position) {
        this.defItem = position;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ProductInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product_icon,parent,false);
        ProductInfoViewHolder viewHolder = new ProductInfoViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductInfoViewHolder holder, int position) {
//        GlideImageLoader.imageLoader(context,mList.get(position),holder.mIcon);
//        if (position == defItem){
//            holder.mll.setBackgroundResource(R.drawable.image_item_select_style);
//        }else {
//            holder.mll.setBackgroundResource(R.color.app_main_default);
//        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class ProductInfoViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.ll_commodity_picture)
        LinearLayout mll;
        @BindView(R.id.iv_commodity_icon)
        ImageView mIcon;
        public ProductInfoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
