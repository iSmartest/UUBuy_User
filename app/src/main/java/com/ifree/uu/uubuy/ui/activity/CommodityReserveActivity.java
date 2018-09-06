package com.ifree.uu.uubuy.ui.activity;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.ui.base.BaseActivity;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/28.
 * Description:
 */
public class CommodityReserveActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_commodity_reserve;
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initView() {
        hideBack(5);
        setTitleText("商品预定");
    }
}
