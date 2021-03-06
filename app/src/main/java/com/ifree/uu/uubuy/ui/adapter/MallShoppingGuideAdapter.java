package com.ifree.uu.uubuy.ui.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.app.MyApplication;
import com.ifree.uu.uubuy.ui.activity.MallFloorActivity;
import com.ifree.uu.uubuy.widget.MyGridView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2019/1/11 0011
 * Description:
 */
public class MallShoppingGuideAdapter extends BaseAdapter {
    private Context context;
    public MallShoppingGuideAdapter(Context context) {
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
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SecondClassifyViewHolder viewHolder;
        if (convertView != null) {
            viewHolder = (SecondClassifyViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_mall_shopping_guide, parent, false);
            viewHolder = new SecondClassifyViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        FloorStoreLogoAdapter mFloorStoreLogoAdapter = new FloorStoreLogoAdapter(context);
        viewHolder.rcFloorStore.setAdapter(mFloorStoreLogoAdapter);
        viewHolder.llFloor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyApplication.openActivity(context,MallFloorActivity.class);
            }
        });
        return convertView;    }

    static class SecondClassifyViewHolder {
        @BindView(R.id.ll_mall_floor)
        RelativeLayout llFloor;
        @BindView(R.id.tv_mall_floor_guide)
        TextView mFloorName;
        @BindView(R.id.rc_floor_store)
        MyGridView rcFloorStore;
        public SecondClassifyViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
