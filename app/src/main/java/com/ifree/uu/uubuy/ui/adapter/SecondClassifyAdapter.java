package com.ifree.uu.uubuy.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ifree.uu.uubuy.R;

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

    public SecondClassifyAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SecondClassifyViewHolder viewHolder;
        if (convertView != null) {
            viewHolder = (SecondClassifyViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_second_classify, parent, false);
            viewHolder = new SecondClassifyViewHolder(convertView);
            convertView.setTag(viewHolder);
        }

        viewHolder.mName.setText("追不上我吧");
        return convertView;
    }

    static class SecondClassifyViewHolder {
        @BindView(R.id.tv_second_classify_name)
        TextView mName;
        public SecondClassifyViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
