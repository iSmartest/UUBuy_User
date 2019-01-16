package com.ifree.uu.uubuy.ui.adapter;

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
import com.ifree.uu.uubuy.ui.activity.MallActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2019/1/4 0004
 * Description:
 */
public class GoShoppingAdapter extends RecyclerView.Adapter<GoShoppingAdapter.GoShoppingViewHolder>{
    private Context context;
    public GoShoppingAdapter(Context context) {
        this.context = context;

    }

    @NonNull
    @Override
    public GoShoppingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_go_shopping,parent,false);
        GoShoppingViewHolder viewHolder = new GoShoppingViewHolder(view);
        return viewHolder;    }

    @Override
    public void onBindViewHolder(@NonNull GoShoppingViewHolder viewHolder, int position) {
        viewHolder.llMall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyApplication.openActivity(context,MallActivity.class);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class GoShoppingViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.ll_item_mall)
        LinearLayout llMall;
        @BindView(R.id.iv_mall_icon)
        ImageView icon;
        @BindView(R.id.tv_mall_name)
        TextView name;
        @BindView(R.id.tv_mall_address)
        TextView address;
        public GoShoppingViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
