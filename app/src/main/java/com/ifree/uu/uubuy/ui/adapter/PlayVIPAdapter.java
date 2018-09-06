package com.ifree.uu.uubuy.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.service.entity.GroupEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/22.
 * Description:
 */
public class PlayVIPAdapter extends RecyclerView.Adapter<PlayVIPAdapter.PlayVIPViewHolder>{
    private Context context;
    private List<GroupEntity.SignInList> mList;
    public PlayVIPAdapter(Context context, List<GroupEntity.SignInList> mList) {
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public PlayVIPViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_play_vip,parent,false);
        PlayVIPViewHolder vipViewHolder = new PlayVIPViewHolder(view);
        return vipViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PlayVIPViewHolder holder, int position) {
        if (position == 0){
            holder.vLeft.setVisibility(View.GONE);
        }else if (position == 6){
            holder.vRight.setVisibility(View.GONE);
        }else {
            holder.vLeft.setVisibility(View.VISIBLE);
            holder.vRight.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return 7;
    }

    public class PlayVIPViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.v_vip_left)
        View vLeft;
        @BindView(R.id.v_vip_right)
        View vRight;
        public PlayVIPViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
