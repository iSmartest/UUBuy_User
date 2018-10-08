package com.ifree.uu.uubuy.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.mvp.entity.MessageEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/23.
 * Description:
 */
public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder>{

    private Context context;
    private List<MessageEntity.DataBean.NotifyList> mList;
    private String type;

    public MessageAdapter(Context context, List<MessageEntity.DataBean.NotifyList> mList, String type) {
        this.context = context;
        this.mList = mList;
        this.type = type;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_message,parent,false);
        MessageViewHolder viewHolder = new MessageViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        MessageEntity.DataBean.NotifyList messageList = mList.get(position);
        if (type.equals("0")){
            holder.mSendTime.setVisibility(View.GONE);
            holder.mState.setVisibility(View.GONE);
            holder.mTime.setText(messageList.getMessageSendTime());
        }else if (type.equals("1")){
            holder.mSendTime.setVisibility(View.GONE);
            holder.mState.setVisibility(View.GONE);
            holder.mTime.setText(messageList.getMessageSendTime());
        }else {
            holder.mSendTime.setVisibility(View.VISIBLE);
            holder.mState.setVisibility(View.VISIBLE);
            switch (messageList.getMessageType()){
                case "0":
                    holder.mState.setText("活动开始");
                    break;
                case "1":
                    holder.mState.setText("活动结束");
                    break;
            }
            holder.mSendTime.setText(messageList.getMessageSendTime());
            holder.mTime.setText(messageList.getMessageTime());
        }

        holder.mContent.setText(messageList.getMessageContent());

    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_message_send_time)
        TextView mSendTime;
        @BindView(R.id.tv_message_content)
        TextView mContent;
        @BindView(R.id.tv_message_time)
        TextView mTime;
        @BindView(R.id.tv_message_state)
        TextView mState;
        public MessageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
