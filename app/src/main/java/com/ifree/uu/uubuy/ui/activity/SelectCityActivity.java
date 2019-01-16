package com.ifree.uu.uubuy.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.app.MyApplication;
import com.ifree.uu.uubuy.common.CommonActivity;
import com.ifree.uu.uubuy.listener.RecyclerItemTouchListener;
import com.ifree.uu.uubuy.mvp.modle.CityInfoBean;
import com.ifree.uu.uubuy.ui.adapter.SelectCityAdapter;

import java.io.Serializable;
import java.util.ArrayList;

import butterknife.BindView;

import static com.ifree.uu.uubuy.config.Constant.SELECT_AREA_REQUEST;

/**
 * Created by 小火
 * Create time on  2017/12/9
 * My mailbox is 1403241630@qq.com
 */

public class SelectCityActivity extends CommonActivity {
    @BindView(R.id.select_list)
    RecyclerView recyclerView;
    private ArrayList<? extends CityInfoBean.DataBean.ProvinceList.CityList> mList;
    private SelectCityAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_select_province_ciry_area;
    }

    @Override
    protected int getTitleBarId() {
        return R.id.tb_select_city_title;
    }


    @Override
    protected void initView() {
        mList = getIntent().getParcelableArrayListExtra("city");
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new SelectCityAdapter(context,mList);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
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
                MyApplication.openActivityForResult(SelectCityActivity.this, SelectAreaActivity.class, bundle,SELECT_AREA_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_AREA_REQUEST && resultCode == Activity.RESULT_OK){
            setResult(Activity.RESULT_OK);
            finish();
        }
    }
}