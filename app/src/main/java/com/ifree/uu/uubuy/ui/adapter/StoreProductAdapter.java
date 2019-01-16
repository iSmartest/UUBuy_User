package com.ifree.uu.uubuy.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.app.MyApplication;
import com.ifree.uu.uubuy.ui.activity.ProductActivity;
import com.ifree.uu.uubuy.ui.activity.ProductFineFoodActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2019/1/4 0004
 * Description:
 */
public class StoreProductAdapter extends RecyclerView.Adapter<StoreProductAdapter.StoreProductViewHolder>{
    private Context context;
    public StoreProductAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public StoreProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_store_product, parent, false);
        StoreProductViewHolder viewHolder = new StoreProductViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StoreProductViewHolder viewHolder, final int position) {
        viewHolder.llStoreProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position == 0){
                    MyApplication.openActivity(context,ProductFineFoodActivity.class);
                }else {
                    MyApplication.openActivity(context,ProductActivity.class);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public class StoreProductViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.ll_store_product)
        LinearLayout llStoreProduct;
        public StoreProductViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
