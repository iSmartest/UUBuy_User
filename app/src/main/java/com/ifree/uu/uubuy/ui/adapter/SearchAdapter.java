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
 * Created by 2019/1/9 0009
 * Description:
 */
public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder>{
    private Context context;
    public SearchAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_search,parent,false);
        SearchViewHolder viewHolder = new SearchViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder searchViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder{

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
