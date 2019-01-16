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
import com.ifree.uu.uubuy.ui.adapter.SelectProvinceAdapter;

import java.io.Serializable;
import java.util.ArrayList;

import butterknife.BindView;

import static com.ifree.uu.uubuy.config.Constant.SELECT_CITY_REQUEST;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/9/6.
 * Description:
 */
public class SelectProvinceActivity extends CommonActivity {
    @BindView(R.id.select_list)
    RecyclerView recyclerView;
    private ArrayList<? extends CityInfoBean.DataBean.ProvinceList> mList = new ArrayList<>();
    private SelectProvinceAdapter mAdapter;
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
        mList = getIntent().getParcelableArrayListExtra("province");
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new SelectProvinceAdapter(context,mList);
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
                bundle.putSerializable("city",(Serializable) mList.get(position).getCityList());
                MyApplication.openActivityForResult(SelectProvinceActivity.this, SelectCityActivity.class, bundle,SELECT_CITY_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_CITY_REQUEST && resultCode == Activity.RESULT_OK){
            setResult(Activity.RESULT_OK);
            finish();
        }
    }
}
