package com.ifree.uu.uubuy.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ifree.uu.uubuy.R;

import butterknife.ButterKnife;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/23.
 * Description:
 */
public class FirstClassifyAdapter extends RecyclerView.Adapter<FirstClassifyAdapter.FirstClassifyViewHolder>{

    private Context context;
    public FirstClassifyAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public FirstClassifyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_first_classify,parent,false);
        FirstClassifyViewHolder viewHolder = new FirstClassifyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FirstClassifyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class FirstClassifyViewHolder extends RecyclerView.ViewHolder{

        public FirstClassifyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
