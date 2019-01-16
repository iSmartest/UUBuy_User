package com.ifree.uu.uubuy.ui.activity;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.app.MyApplication;
import com.ifree.uu.uubuy.common.CommonActivity;
import com.ifree.uu.uubuy.ui.adapter.BannerAdapter;
import com.ifree.uu.uubuy.ui.adapter.MallShoppingGuideAdapter;
import com.ifree.uu.uubuy.widget.ZoomOutPageTransformer;

import butterknife.BindView;


/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2019/1/4 0004
 * Description: 商场
 */
public class MallActivity extends CommonActivity implements View.OnClickListener {
    @BindView(R.id.lv_mall_guide)
    ListView lvMallGuide;
    private View headerView, footerView;
    private BannerAdapter mBannerAdapter;
    RecyclerView recyclerView;
    private MallShoppingGuideAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mall;
    }

    @Override
    protected int getTitleBarId() {
        return R.id.tb_mall_bar;
    }

    @Override
    protected void initView() {
        footerView = LayoutInflater.from(context).inflate(R.layout.footer_mall, null);
        footerView.findViewById(R.id.ll_item_nearby_mall_item1).setOnClickListener(this);
        footerView.findViewById(R.id.ll_item_nearby_mall_item2).setOnClickListener(this);
        headerView = LayoutInflater.from(context).inflate(R.layout.header_mall, null);
        headerView.findViewById(R.id.iv_mall_shopping_guide_clothes).setOnClickListener(this);
        headerView.findViewById(R.id.iv_mall_shopping_guide_play).setOnClickListener(this);
        headerView.findViewById(R.id.iv_mall_shopping_guide_fine_food).setOnClickListener(this);
        headerView.findViewById(R.id.iv_mall_shopping_guide_makeup).setOnClickListener(this);
        headerView.findViewById(R.id.iv_mall_shopping_guide_parenting).setOnClickListener(this);
        headerView.findViewById(R.id.iv_mall_shopping_guide_jewelry).setOnClickListener(this);
        recyclerView = headerView.findViewById(R.id.rv_mall_activities);
        headerView.findViewById(R.id.ll_mall_get_coupons).setOnClickListener(this);
        if (footerView != null) lvMallGuide.addFooterView(footerView);
        if (headerView != null) lvMallGuide.addHeaderView(headerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        mBannerAdapter = new BannerAdapter(context);
        recyclerView.setAdapter(mBannerAdapter);
        mAdapter = new MallShoppingGuideAdapter(context);
        lvMallGuide.setAdapter(mAdapter);
    }


    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_mall_shopping_guide_clothes:
                MyApplication.openActivity(context, MallGuideActivity.class);
                break;
            case R.id.iv_mall_shopping_guide_play:
                MyApplication.openActivity(context, MallGuideActivity.class);
                break;
            case R.id.iv_mall_shopping_guide_fine_food:
                MyApplication.openActivity(context, MallGuideActivity.class);
                break;
            case R.id.iv_mall_shopping_guide_makeup:
                MyApplication.openActivity(context, MallGuideActivity.class);
                break;
            case R.id.iv_mall_shopping_guide_parenting:
                MyApplication.openActivity(context, MallGuideActivity.class);
                break;
            case R.id.iv_mall_shopping_guide_jewelry:
                MyApplication.openActivity(context, MallGuideActivity.class);
                break;
            case R.id.ll_mall_get_coupons:
                MyApplication.openActivity(context, GetCouponsActivity.class);
                break;
            case R.id.ll_item_nearby_mall_item1:
                MyApplication.openActivity(context, MallActivity.class);
                finish();
                break;
            case R.id.ll_item_nearby_mall_item2:
                MyApplication.openActivity(context, MallActivity.class);
                finish();
                break;
        }
    }
}
