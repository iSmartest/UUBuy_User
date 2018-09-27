package com.ifree.uu.uubuy.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.app.MyApplication;
import com.ifree.uu.uubuy.listener.RecyclerItemTouchListener;
import com.ifree.uu.uubuy.service.entity.CityInfoEntity;
import com.ifree.uu.uubuy.ui.adapter.SelectProvinceAdapter;
import com.ifree.uu.uubuy.ui.base.BaseActivity;

import java.io.Serializable;
import java.util.ArrayList;
import butterknife.BindView;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/9/6.
 * Description:
 */
public class SelectProvinceActivity extends BaseActivity {
    @BindView(R.id.select_list)
    RecyclerView recyclerView;
    private ArrayList<? extends CityInfoEntity.DataBean.ProvinceList> mList = new ArrayList<>();
    private SelectProvinceAdapter mAdapter;
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
                bundle.putSerializable("city",(Serializable) mList.get(position).getCityList());
                MyApplication.openActivity(context,SelectCityActivity.class,bundle);
            }
        });
    }

    @Override
    protected void initView() {
        hideBack(5);
        setTitleText("选择城市");
        MyApplication.addActivity(SelectProvinceActivity.this);
        mList = getIntent().getParcelableArrayListExtra("province");
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new SelectProvinceAdapter(context,mList);
        recyclerView.setAdapter(mAdapter);
    }
}
