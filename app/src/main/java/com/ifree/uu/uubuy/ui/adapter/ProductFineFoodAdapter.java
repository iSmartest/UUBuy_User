package com.ifree.uu.uubuy.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.app.MyApplication;
import com.ifree.uu.uubuy.ui.activity.StoreActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2019/1/11 0011
 * Description:
 */
public class ProductFineFoodAdapter extends RecyclerView.Adapter<ProductFineFoodAdapter.ProductFineFoodViewHolder> {
    private Context context;

    public ProductFineFoodAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ProductFineFoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product_fine_food, parent, false);
        ProductFineFoodViewHolder viewHolder = new ProductFineFoodViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductFineFoodViewHolder productFineFoodViewHolder, int i) {

    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public class ProductFineFoodViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_product_fine_food_icon)
        ImageView icon;


        public ProductFineFoodViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}