package com.ifree.uu.uubuy.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.common.CommonActivity;
import com.ifree.uu.uubuy.listener.RecyclerItemTouchListener;
import com.ifree.uu.uubuy.mvp.modle.CityInfoBean;
import com.ifree.uu.uubuy.ui.adapter.SelectAreaAdapter;
import com.ifree.uu.uubuy.utils.SPUtil;
import java.util.ArrayList;

import butterknife.BindView;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/9/6.
 * Description:
 */
public class SelectAreaActivity extends CommonActivity {
    @BindView(R.id.select_list)
    RecyclerView recyclerView;
    private ArrayList<? extends CityInfoBean.DataBean.ProvinceList.CityList.TownList> mList;
    private SelectAreaAdapter mAdapter;
    private String city;
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
        mList = getIntent().getParcelableArrayListExtra("area");
        city = getIntent().getStringExtra("city");
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new SelectAreaAdapter(context,mList);
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
                SPUtil.putString(context, "city",city);
                SPUtil.putString(context, "district",mList.get(position).getTown());
                SPUtil.putString(context, "townAdCode", mList.get(position).getTownAdCode());
                SPUtil.putString(context, "latitude", mList.get(position).getLatitude());
                SPUtil.putString(context, "longitude", mList.get(position).getLongitude());
                Intent intent = new Intent();
                intent.setAction("com.ifree.uu.location.changed");
                getApplicationContext().sendBroadcast(intent);
                setResult(Activity.RESULT_OK);
                finish();
            }
        });
    }
}
