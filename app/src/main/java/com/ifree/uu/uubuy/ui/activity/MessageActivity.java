package com.ifree.uu.uubuy.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.ui.base.BaseActivity;
import com.ifree.uu.uubuy.ui.fragment.MessageListFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/23.
 * Description:
 */
public class MessageActivity extends BaseActivity {

    @BindView(R.id.message_tabs)
    TabLayout tabLayout;
    @BindView(R.id.message_viewpager)
    ViewPager viewpager;
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> tabList = new ArrayList<>();
    private String[] strings = {"通知","活动"};
    @Override
    protected int getLayoutId() {
        return R.layout.activity_message;
    }

    @Override
    protected void loadData() {
        NewsAdapter fragmentAdapter = new NewsAdapter(getSupportFragmentManager(), fragmentList, tabList);
        viewpager.setAdapter(fragmentAdapter);//给ViewPager设置适配器
        tabLayout.setupWithViewPager(viewpager);//将TabLayout和ViewPager关联起来。
        tabLayout.setTabsFromPagerAdapter(fragmentAdapter);//给Tabs设置适配器
        tabLayout.setTabMode(TabLayout.MODE_FIXED);//设置tab模式，当前为系统默认模式
    }

    @Override
    protected void initView() {
        hideBack(5);
        setTitleText("消息");
        for (int i = 0; i <strings.length ; i++) {
            tabList.add(strings[i]);
            MessageListFragment fragment = new MessageListFragment();
            Bundle bundle = new Bundle();
            bundle.putString("type",i+"");
            fragment.setArguments(bundle);
            fragmentList.add(fragment);
        }
    }

    public class NewsAdapter extends FragmentPagerAdapter {
        private List<Fragment> mFragments;
        private List<String> mTitles;

        public NewsAdapter(FragmentManager fm, List<Fragment> fragments, List<String> titles) {
            super(fm);
            this.mFragments = fragments;
            this.mTitles = titles;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles.get(position);
        }

    }
}
