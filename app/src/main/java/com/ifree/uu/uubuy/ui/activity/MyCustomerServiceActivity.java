package com.ifree.uu.uubuy.ui.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.app.MyApplication;
import com.ifree.uu.uubuy.common.CommonActivity;
import com.ifree.uu.uubuy.ui.adapter.MyCustomerServiceAdapter;

import butterknife.BindView;

/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2019/1/8 0008
 * Description:
 */
public class MyCustomerServiceActivity extends CommonActivity implements View.OnClickListener {
    @BindView(R.id.lv_help_center)
    ListView listView;
    private View headerView;
    private MyCustomerServiceAdapter mAdapter;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_customer_service;
    }

    @Override
    protected int getTitleBarId() {
        return R.id.tb_customer_service_title;
    }

    @Override
    protected void initView() {
        headerView = LayoutInflater.from(context).inflate(R.layout.header_help_center,null);
        headerView.findViewById(R.id.ll_account_appeal).setOnClickListener(this);
        headerView.findViewById(R.id.ll_feedback).setOnClickListener(this);
        if (headerView != null) listView.addHeaderView(headerView);
        mAdapter = new MyCustomerServiceAdapter(context);
        listView.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_account_appeal:
                MyApplication.openActivity(context,AccountAppealActivity.class);
                break;
            case R.id.ll_feedback:
                MyApplication.openActivity(context,FeedbackActivity.class);
                break;
        }
    }
}
