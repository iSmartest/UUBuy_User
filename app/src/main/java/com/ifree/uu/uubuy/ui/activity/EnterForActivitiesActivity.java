package com.ifree.uu.uubuy.ui.activity;

import android.graphics.Color;
import android.widget.LinearLayout;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.ui.base.BaseActivity;

import butterknife.BindView;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/24.
 * Description:
 */
public class EnterForActivitiesActivity extends BaseActivity {
    @BindView(R.id.ll_enter)
    LinearLayout ll_enter;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_enter_for_activities;
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initView() {
        hideBack(5);
        setTitleText("活动报名");
        ll_enter.setBackgroundColor(Color.parseColor(getIntent().getStringExtra("color")));
    }
}
