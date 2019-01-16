package com.ifree.uu.uubuy.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.mvp.modle.HomeBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2019/1/8 0008
 * Description:
 */
public class MyFocusMallAdapter extends RecyclerView.Adapter<MyFocusMallAdapter.MyFocusMallViewHolder> {
    private Context context;
    private List<HomeBean.DataBean.ActivitiesList> mList;

    public MyFocusMallAdapter(Context context, List<HomeBean.DataBean.ActivitiesList> mList) {
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public MyFocusMallViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_main, parent, false);
        MyFocusMallViewHolder viewHolder = new MyFocusMallViewHolder(view);
        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyFocusMallViewHolder viewHolder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class MyFocusMallViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_home_main_icon)
        ImageView icon;
        @BindView(R.id.tv_home_main_name)
        TextView name;
        @BindView(R.id.tv_home_main_time)
        TextView time;
        @BindView(R.id.tv_home_main_address)
        TextView address;
        @BindView(R.id.tv_home_main_browsing_volume)
        TextView browsingVolume;
        @BindView(R.id.tv_home_main_sign_up)
        TextView signUp;

        public MyFocusMallViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
