package com.ifree.uu.uubuy.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.custom.MyListView;
import com.ifree.uu.uubuy.service.entity.MenuClassifyEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/23.
 * Description:
 */
public class FirstMenuAdapter extends RecyclerView.Adapter<FirstMenuAdapter.FirstMenuViewHolder>{

    private Context context;
    private List<MenuClassifyEntity.FirstMenuList> mMenuList;
    private boolean open;
    public FirstMenuAdapter(Context context, List<MenuClassifyEntity.FirstMenuList> mMenuList) {
        this.context = context;
        this.mMenuList = mMenuList;
    }

    @NonNull
    @Override
    public FirstMenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_first_menu,parent,false);
        FirstMenuViewHolder viewHolder = new FirstMenuViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final FirstMenuViewHolder holder, int position) {
//        final MenuClassifyEntity.FirstMenuList firstMenuList = mMenuList.get(position);
        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (open){
                    holder.secondClassify.setVisibility(View.GONE);
                    open = false;
                }else {
                    holder.secondClassify.setVisibility(View.VISIBLE);
                    open = true;
                }

//                if(firstMenuList.isOpen()){
//                    holder.secondClassify.setVisibility(View.GONE);
//                    firstMenuList.setOpen(false);
//                }else{
//                    holder.secondClassify.setVisibility(View.VISIBLE);
//                    firstMenuList.setOpen(true);
//                }
            }
        });
        SecondClassifyAdapter secondClassifyAdapter = new SecondClassifyAdapter(context);
        holder.secondClassify.setAdapter(secondClassifyAdapter);

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class FirstMenuViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tv_first_classify_name)
        TextView name;
        @BindView(R.id.second_menu_classify)
        MyListView secondClassify;
        public FirstMenuViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
