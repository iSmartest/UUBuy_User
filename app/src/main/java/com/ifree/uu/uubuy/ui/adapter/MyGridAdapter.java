package com.ifree.uu.uubuy.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ifree.uu.uubuy.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/24.
 * Description:
 */
public class MyGridAdapter extends RecyclerView.Adapter<MyGridAdapter.MyGridViewHolder> {
    private Context context;
    public MyGridAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyGridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_my_gridview, parent, false);
        MyGridViewHolder viewHolder = new MyGridViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyGridViewHolder viewHolder, int position) {
        if (position == 0 && position == 2 && position == 4){
            viewHolder.rl_store.setVisibility(View.VISIBLE);
            viewHolder.ll_commodity.setVisibility(View.GONE);
            viewHolder.mType.setText("专柜");
        }else {
            viewHolder.rl_store.setVisibility(View.GONE);
            viewHolder.ll_commodity.setVisibility(View.VISIBLE);
            viewHolder.mType.setText("自营");
        }
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    class MyGridViewHolder extends RecyclerView.ViewHolder{
       @BindView(R.id.rl_store)
        RelativeLayout rl_store;
       @BindView(R.id.ll_commodity)
        LinearLayout ll_commodity;
       @BindView(R.id.tv_item_type)
       TextView mType;

        public MyGridViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
