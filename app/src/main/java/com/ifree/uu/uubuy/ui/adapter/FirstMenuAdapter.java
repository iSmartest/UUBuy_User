package com.ifree.uu.uubuy.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.custom.MyListView;
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
public class FirstMenuAdapter extends BaseAdapter{
    private Context context;
    private List<FirstClassifyEntity.DataBean.MenuList> mMenuList;
    private String type;
    public FirstMenuAdapter(Context context, List<FirstClassifyEntity.DataBean.MenuList> mMenuList,String type) {
        this.context = context;
        this.mMenuList = mMenuList;
        this.type = type;
    }

    @Override
    public int getCount() {
        return mMenuList == null ? 0 : mMenuList.size();
    }

    @Override
    public Object getItem(int position) {
        return mMenuList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final FirstMenuViewHolder viewHolder;
        final FirstClassifyEntity.DataBean.MenuList menuList = mMenuList.get(position);
        if (convertView != null) {
            viewHolder = (FirstMenuViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_first_menu,parent,false);
            viewHolder = new FirstMenuViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        if (type.equals("6")){
            viewHolder.name.setText(menuList.getMenuNameInfo());
            viewHolder.name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (type.equals("6")){
                        if(menuList.isOpen()){
                            viewHolder.secondClassify.setVisibility(View.GONE);
                            menuList.setOpen(false);
                        }else{
                            viewHolder.secondClassify.setVisibility(View.VISIBLE);
                            menuList.setOpen(true);
                        }
                    }
                }
            });
        }else {
            viewHolder.name.setText(menuList.getMenuName());
        }
        SecondClassifyAdapter secondClassifyAdapter = new SecondClassifyAdapter(context,menuList.getSecondList());
        viewHolder.secondClassify.setAdapter(secondClassifyAdapter);
        return convertView;
    }

    public class FirstMenuViewHolder{
        @BindView(R.id.tv_first_classify_name)
        TextView name;
        @BindView(R.id.second_menu_classify)
        MyListView secondClassify;
        public FirstMenuViewHolder(View itemView) {
            ButterKnife.bind(this,itemView);
        }
    }
}
