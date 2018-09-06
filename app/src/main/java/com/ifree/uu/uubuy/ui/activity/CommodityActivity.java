package com.ifree.uu.uubuy.ui.activity;

import android.view.View;
import android.widget.TextView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.app.MyApplication;
import com.ifree.uu.uubuy.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/27.
 * Description:
 */
public class CommodityActivity extends BaseActivity {
    @BindView(R.id.tv_commodity_compare)
    TextView mCompare;
    @BindView(R.id.tv_commodity_reserve)
    TextView mReserve;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_commodity;
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initView() {

        hideBack(6);
        setTitleText("商品详情");

    }

    @OnClick({R.id.tv_commodity_compare,R.id.tv_commodity_reserve})
    public void onClickView(View view){
        switch (view.getId()){
            case R.id.tv_commodity_compare:

                break;
            case R.id.tv_commodity_reserve:
                MyApplication.openActivity(context,CommodityReserveActivity.class);
                break;
        }
    }

}
