package com.ifree.uu.uubuy.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.ifree.uu.uubuy.service.entity.MineEntity;

import java.util.List;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/21.
 * Description:
 */
public class MineAdapter extends RecyclerView.Adapter<MineAdapter.MineViewHolder>{
    private Context context;
    private List<MineEntity.RecommendactivitiesList> mList;
    public MineAdapter(Context context, List<MineEntity.RecommendactivitiesList> mList) {
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public MineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MineViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MineViewHolder extends RecyclerView.ViewHolder{

        public MineViewHolder(View itemView) {
            super(itemView);
        }
    }
}
