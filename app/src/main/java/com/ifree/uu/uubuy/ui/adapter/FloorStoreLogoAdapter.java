package com.ifree.uu.uubuy.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.app.MyApplication;
import com.ifree.uu.uubuy.ui.activity.StoreActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2019/1/4 0004
 * Description:
 */
public class FloorStoreLogoAdapter extends BaseAdapter {
    private Context context;

    public FloorStoreLogoAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 4;
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
        FloorStoreLogoViewHolder viewHolder;
        if (convertView != null) {
            viewHolder = (FloorStoreLogoViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_floor_store_logo, parent, false);
            viewHolder = new FloorStoreLogoViewHolder(convertView);
            convertView.setTag(viewHolder);
        }

        viewHolder.icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyApplication.openActivity(context, StoreActivity.class);
            }
        });
        return convertView;
    }


    public class FloorStoreLogoViewHolder {
        @BindView(R.id.civ_store_logo)
        CircleImageView icon;

        public FloorStoreLogoViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

