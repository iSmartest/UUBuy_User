package com.ifree.uu.uubuy.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.custom.flowTagLayout.OnInitSelectedPosition;
import com.ifree.uu.uubuy.service.entity.HotKeyWordEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2018/9/19 0019
 * Description:
 */
public class FlowTagAdapter extends BaseAdapter implements OnInitSelectedPosition {

    private final Context mContext;
    private final List<HotKeyWordEntity.DataBean.KeywordList> mDataList;

    public FlowTagAdapter(Context context, List<HotKeyWordEntity.DataBean.KeywordList> hotSearch) {
        this.mContext = context;
        this.mDataList = hotSearch;
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.sku_item, null);
        TextView textView = view.findViewById(R.id.tv_sku);
        HotKeyWordEntity.DataBean.KeywordList keywordList = mDataList.get(position);
        textView.setText(keywordList.getName());
        return view;
    }

    public void onlyAddAll(List<HotKeyWordEntity.DataBean.KeywordList> datas) {
        mDataList.addAll(datas);
        notifyDataSetChanged();
    }

    public void clearAndAddAll(List<HotKeyWordEntity.DataBean.KeywordList> datas) {
        mDataList.clear();
        onlyAddAll(datas);
    }

    @Override
    public boolean isSelectedPosition(int position) {
        if (position % 2 == 0) {
            return true;
        }
        return false;
    }
}
