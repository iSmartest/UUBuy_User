package com.ifree.uu.uubuy.ui.activity;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.ui.base.BaseActivity;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/22.
 * Description:
 */
public class MyFootprintActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_footprint;
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initView() {
        hideBack(5);
        setTitleText("我的足迹");
    }
}
