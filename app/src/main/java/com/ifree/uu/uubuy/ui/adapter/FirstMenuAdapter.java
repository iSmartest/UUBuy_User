package com.ifree.uu.uubuy.ui.adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.custom.MyListView;
import com.ifree.uu.uubuy.mvp.entity.FirstClassifyEntity;

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
    private firstMenuListener mListener;
    private int defItem = -1;//声明默认选中的项
    public void setFirstMenuListener(firstMenuListener mListener){
        this.mListener = mListener;
    }
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

    public void setDefSelect(int position) {
        this.defItem = position;
        notifyDataSetChanged();
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
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
        secondClassifyAdapter.setSecondMenuListener(new SecondClassifyAdapter.secondMenuListener() {
            @Override
            public void onSecondMenu(int i) {
                mListener.onFirstMenu(position,i);
            }
        });
        viewHolder.secondClassify.setAdapter(secondClassifyAdapter);
        disposalView(position,convertView);
        return convertView;
    }
    private void disposalView(int position, View convertView) {
        Resources resource = context.getResources();
        ColorStateList csl1 = resource.getColorStateList(R.color.text_subtitle_color);
        ColorStateList csl2 = resource.getColorStateList(R.color.text_green);
        if (defItem == -1){
            convertView.setBackgroundResource(R.color.app_main_default);
            FirstMenuViewHolder viewHolder = (FirstMenuViewHolder) convertView.getTag();
            viewHolder.name.setTextColor(csl1);
        }else if (position == defItem){
            FirstMenuViewHolder viewHolder = (FirstMenuViewHolder) convertView.getTag();
            viewHolder.name.setTextColor(csl2);
        }else {
            FirstMenuViewHolder viewHolder = (FirstMenuViewHolder) convertView.getTag();
            viewHolder.name.setTextColor(csl1);
        }
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
    public interface firstMenuListener{
        void onFirstMenu(int position,int i);
    }
}
