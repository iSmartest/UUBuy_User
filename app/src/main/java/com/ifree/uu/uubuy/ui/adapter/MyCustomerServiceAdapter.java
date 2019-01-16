package com.ifree.uu.uubuy.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.app.MyApplication;
import com.ifree.uu.uubuy.ui.activity.HelpCenterActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2019/1/10 0010
 * Description:
 */
public class MyCustomerServiceAdapter extends BaseAdapter {
    private Context context;
    public MyCustomerServiceAdapter(Context context) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_help_center, parent, false);
            viewHolder = new SecondClassifyViewHolder(convertView);
            convertView.setTag(viewHolder);
        }

        viewHolder.tvHelpCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyApplication.openActivity(context,HelpCenterActivity.class);
            }
        });
        return convertView;
    }

    static class SecondClassifyViewHolder {
        @BindView(R.id.ll_help_center)
        LinearLayout tvHelpCenter;
        public SecondClassifyViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
