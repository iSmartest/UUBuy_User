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
import com.ifree.uu.uubuy.ui.activity.MyOrderInfoActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2019/1/10 0010
 * Description:
 */
public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.MyOrderViewHolder>{
    private Context context;
    public MyOrderAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_my_order,parent,false);
        MyOrderViewHolder viewHolder = new MyOrderViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyOrderViewHolder viewHolder, int position) {
        viewHolder.llOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyApplication.openActivity(context,MyOrderInfoActivity.class);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public class MyOrderViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.ll_my_order)
        LinearLayout llOrder;
        public MyOrderViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}
