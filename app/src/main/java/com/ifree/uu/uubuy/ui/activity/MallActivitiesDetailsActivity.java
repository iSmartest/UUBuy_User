package com.ifree.uu.uubuy.ui.activity;

import android.view.View;
import android.widget.ImageView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.app.MyApplication;
import com.ifree.uu.uubuy.common.CommonActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2019/1/4 0004
 * Description: 活动详情
 */
public class MallActivitiesDetailsActivity extends CommonActivity {
    @BindView(R.id.iv_mall_get_coupons)
    ImageView ivGetCoupons;
    @BindView(R.id.iv_mall_activities_picture)
    ImageView ivPicture;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_mall_activities_details;
    }

    @Override
    protected int getTitleBarId() {
        return R.id.tb_activities_title;
    }

    @Override
    protected void initView() {
    }

    @OnClick({R.id.iv_mall_get_coupons})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_mall_get_coupons:
                MyApplication.openActivity(context,GetCouponsActivity.class);
                break;
        }
    }

    @Override
    protected void initData() {

    }
}
