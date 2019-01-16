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
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2019/1/10 0010
 * Description:
 */
public class MessageInfoAdapter extends RecyclerView.Adapter<MessageInfoAdapter.MessageInfoViewHolder>{
    private Context context;
    public MessageInfoAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MessageInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_message_info,parent,false);
        MessageInfoViewHolder viewHolder = new MessageInfoViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MessageInfoViewHolder messageInfoViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public class MessageInfoViewHolder extends RecyclerView.ViewHolder{
        public MessageInfoViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
