package com.ifree.uu.uubuy.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ifree.uu.uubuy.R;

/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2019/1/8 0008
 * Description:
 */
public class MySignUpAdapter extends RecyclerView.Adapter<MySignUpAdapter.MySignUpViewHolder>{
    private Context context;
    public MySignUpAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MySignUpViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_my_sign_up,parent,false);
        MySignUpViewHolder viewHolder = new MySignUpViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MySignUpViewHolder mySignUpViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public class MySignUpViewHolder extends RecyclerView.ViewHolder{

        public MySignUpViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
