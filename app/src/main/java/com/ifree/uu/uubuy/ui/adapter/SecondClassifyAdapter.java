package com.ifree.uu.uubuy.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.service.entity.FirstClassifyEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/23.
 * Description:
 */
public class SecondClassifyAdapter extends BaseAdapter {
    private Context context;
    private List<FirstClassifyEntity.DataBean.MenuList.SecondList> mList;
    private secondMenuListener mListener;
    public SecondClassifyAdapter(Context context, List<FirstClassifyEntity.DataBean.MenuList.SecondList> mList) {
        this.context = context;
        this.mList = mList;
    }
    public void setSecondMenuListener(secondMenuListener mListener){
        this.mListener = mListener;
    }
    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        SecondClassifyViewHolder viewHolder;
        FirstClassifyEntity.DataBean.MenuList.SecondList secondList = mList.get(position);
        if (convertView != null) {
            viewHolder = (SecondClassifyViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_second_classify, parent, false);
            viewHolder = new SecondClassifyViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        viewHolder.mName.setText(secondList.getMenuName());
        viewHolder.mName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onSecondMenu(position);
            }
        });
        return convertView;
    }

    static class SecondClassifyViewHolder {
        @BindView(R.id.tv_second_classify_name)
        TextView mName;
        public SecondClassifyViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public interface secondMenuListener{
        void onSecondMenu(int position);
    }
}
