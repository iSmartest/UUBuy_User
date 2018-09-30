package com.ifree.uu.uubuy.ui.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.app.MyApplication;
import com.ifree.uu.uubuy.listener.RecyclerItemTouchListener;
import com.ifree.uu.uubuy.service.entity.CityInfoEntity;
import com.ifree.uu.uubuy.ui.adapter.SelectHotAreaAdapter;
import com.ifree.uu.uubuy.ui.base.BaseActivity;
import com.ifree.uu.uubuy.uitls.SPUtil;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/9/6.
 * Description:
 */
public class SelectHotAreaActivity extends BaseActivity {
    @BindView(R.id.select_list)
    RecyclerView recyclerView;
    private ArrayList<? extends CityInfoEntity.DataBean.HotCity.TownList> mList;
    private SelectHotAreaAdapter mAdapter;
    private String city;
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
                SPUtil.putString(context, "city",city);
                SPUtil.putString(context, "district",mList.get(position).getTown());
                SPUtil.putString(context, "townAdCode", mList.get(position).getTownAdCode());
                SPUtil.putString(context, "latitude", mList.get(position).getLatitude());
                SPUtil.putString(context, "longitude", mList.get(position).getLongitude());
                MyApplication.clearActivity();
                Intent intent = new Intent();
                intent.setAction("com.ifree.uu.location.changed");
                getApplicationContext().sendBroadcast(intent);
            }
        });
    }

    @Override
    protected void initView() {
        hideBack(5);
        setTitleText("选择城市");
        MyApplication.addActivity(SelectHotAreaActivity.this);
        mList = getIntent().getParcelableArrayListExtra("area");
        city = getIntent().getStringExtra("city");
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new SelectHotAreaAdapter(context,mList);
        recyclerView.setAdapter(mAdapter);
    }
}
