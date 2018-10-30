package com.ifree.uu.uubuy.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.custom.MyListView;
import com.ifree.uu.uubuy.mvp.entity.MyFootPrintEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2018/9/19 0019
 * Description:
 */
public class MyFootprintAdapter extends RecyclerView.Adapter<MyFootprintAdapter.MyFootprintViewHolder>{
    private Context context;
    private List<MyFootPrintEntity.DataBean.FootprintList> mList;
    public MyFootprintAdapter(Context context, List<MyFootPrintEntity.DataBean.FootprintList> mList) {
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public MyFootprintViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_my_foot_print,parent,false);
        MyFootprintViewHolder viewHolder = new MyFootprintViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyFootprintViewHolder holder, int position) {
        MyFootPrintEntity.DataBean.FootprintList footprintList = mList.get(position);
        holder.mTime.setText(footprintList.getRecordTime());
        FootPrintAdapter footPrintAdapter = new FootPrintAdapter(context,footprintList.getFootprintInfoList());
        holder.mFootList.setAdapter(footPrintAdapter);
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public static class MyFootprintViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_foot_time)
        TextView mTime;
        @BindView(R.id.list_foot)
        MyListView mFootList;
        public MyFootprintViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
