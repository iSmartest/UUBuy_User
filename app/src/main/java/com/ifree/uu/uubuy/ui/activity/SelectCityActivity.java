package com.ifree.uu.uubuy.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.app.MyApplication;
import com.ifree.uu.uubuy.listener.RecyclerItemTouchListener;
import com.ifree.uu.uubuy.mvp.entity.CityInfoEntity;
import com.ifree.uu.uubuy.ui.adapter.SelectCityAdapter;
import com.ifree.uu.uubuy.ui.base.BaseActivity;
import java.io.Serializable;
import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by 小火
 * Create time on  2017/12/9
 * My mailbox is 1403241630@qq.com
 */

public class SelectCityActivity extends BaseActivity {
    @BindView(R.id.select_list)
    RecyclerView recyclerView;
    private ArrayList<? extends CityInfoEntity.DataBean.ProvinceList.CityList> mList;
    private SelectCityAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_select_province_ciry_area;
    }

    @Override
    protected void loadData() {
        recyclerView.addOnItemTouchListener(new RecyclerItemTouchListener(recyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
                int position = vh.getAdapterPosition();
                if (position < 0 | position >= mList.size()){
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putSerializable("area",(Serializable) mList.get(position).getTownList());
                bundle.putString("city",mList.get(position).getCity());
                MyApplication.openActivity(context,SelectAreaActivity.class,bundle);
            }
        });
    }

    @Override
    protected void initView() {
        hideBack(5);
        setTitleText("选择城市");
        MyApplication.addActivity(SelectCityActivity.this);
        mList = getIntent().getParcelableArrayListExtra("city");
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new SelectCityAdapter(context,mList);
        recyclerView.setAdapter(mAdapter);
    }
}