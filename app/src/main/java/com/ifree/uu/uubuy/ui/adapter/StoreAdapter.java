package com.ifree.uu.uubuy.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/31.
 * Description:
 */
public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.StoreViewHolder>{

    @NonNull
    @Override
    public StoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull StoreViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class StoreViewHolder extends RecyclerView.ViewHolder{

        public StoreViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
