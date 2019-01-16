package com.ifree.uu.uubuy.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.common.CommonActivity;
import com.ifree.uu.uubuy.ui.adapter.MyFocusPageAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import butterknife.BindView;

/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2019/1/7 0007
 * Description:
 */
public class MyFocusActivity extends CommonActivity {
    @BindView(R.id.tableLayout)
    TabLayout mTabLayout;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_focus;
    }

    @Override
    protected int getTitleBarId() {
        return R.id.tb_mine_focus_title;
    }

    @Override
    protected void initView() {
        mTabLayout.addTab(mTabLayout.newTab().setText("商场"));
        mTabLayout.addTab(mTabLayout.newTab().setText("店铺"));
        mViewPager.setAdapter(new MyFocusPageAdapter(getSupportFragmentManager(), mTabLayout.getTabCount()));
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));

        //绑定tab点击事件
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    protected void initData() {

    }

}
