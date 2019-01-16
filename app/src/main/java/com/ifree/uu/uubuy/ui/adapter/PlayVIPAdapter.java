package com.ifree.uu.uubuy.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.mvp.modle.GroupBean;

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
    private List<GroupBean.DataBean.SignInList> mList;

    public PlayVIPAdapter(Context context, List<GroupBean.DataBean.SignInList> mList) {
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
        GroupBean.DataBean.SignInList signInList = mList.get(position);
        if (position == 0){
            holder.vLeft.setVisibility(View.GONE);
        }else if (position == 6){
            holder.vRight.setVisibility(View.GONE);
        }else {
            holder.vLeft.setVisibility(View.VISIBLE);
            holder.vRight.setVisibility(View.VISIBLE);
        }
        holder.mDate.setText(signInList.getDate());
        switch (signInList.getIsSignIn()){
            case "0":
                holder.ivStare.setImageResource(R.drawable.sign_in_no);
//                holder.vLeft.setBackgroundColor(ContextCompat.getColor(context, R.color.edit_hint_color));
                break;
            case "1":
                holder.ivStare.setImageResource(R.drawable.sign_in_yes);
//                holder.vLeft.setBackgroundColor(ContextCompat.getColor(context, R.color.text_green));
                break;
        }

    }

    @Override
    public int getItemCount() {
        return mList == null ? 7 : mList.size();
    }

    public class PlayVIPViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.v_vip_left)
        View vLeft;
        @BindView(R.id.v_vip_right)
        View vRight;
        @BindView(R.id.tv_sign_in_date)
        TextView mDate;
        @BindView(R.id.iv_sign_in_state)
        ImageView ivStare;
        public PlayVIPViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
