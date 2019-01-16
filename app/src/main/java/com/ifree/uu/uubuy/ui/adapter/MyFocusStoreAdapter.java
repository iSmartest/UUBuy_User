package com.ifree.uu.uubuy.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
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
import com.ifree.uu.uubuy.mvp.modle.HomeBean;
import com.ifree.uu.uubuy.ui.activity.StoreActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2019/1/8 0008
 * Description:
 */
public class MyFocusStoreAdapter extends RecyclerView.Adapter<MyFocusStoreAdapter.MyFocusStoreViewHolder> {
    private Context context;
    public MyFocusStoreAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyFocusStoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_mall_store,parent,false);
        MyFocusStoreViewHolder viewHolder = new MyFocusStoreViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyFocusStoreViewHolder holder, final int position) {
        holder.llStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyApplication.openActivity(context, StoreActivity.class);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 20;
//        return 30;
    }

    static class MyFocusStoreViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ll_item_mall_store)
        LinearLayout llStore;
        @BindView(R.id.iv_store_picture)
        ImageView ivPicture;
        @BindView(R.id.tv_mall_store_browse_volume)
        TextView tvBrowseVolume;
        @BindView(R.id.iv_is_yes)
        ImageView ivYesOrNo;
        @BindView(R.id.tv_mall_store_name)
        TextView tvName;
        @BindView(R.id.tv_mall_store_address)
        TextView tvAddress;
        public MyFocusStoreViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
