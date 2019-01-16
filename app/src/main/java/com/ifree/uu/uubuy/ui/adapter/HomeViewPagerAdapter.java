package com.ifree.uu.uubuy.ui.adapter;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.hjq.baselibrary.base.BaseFragmentPagerAdapter;
import com.ifree.uu.uubuy.common.CommonLazyFragment;
import com.ifree.uu.uubuy.ui.fragment.CouponFragment;
import com.ifree.uu.uubuy.ui.fragment.HomeFragment;
import com.ifree.uu.uubuy.ui.fragment.MineFragment;

import java.util.List;

/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2019/1/3 0003
 * Description: 主页界面 ViewPager + Fragment 适配器
 */
public class HomeViewPagerAdapter extends BaseFragmentPagerAdapter<CommonLazyFragment> {

    public HomeViewPagerAdapter(FragmentActivity activity) {
        super(activity);
    }

    @Override
    protected void init(FragmentManager fm, List<CommonLazyFragment> list) {
        list.add(HomeFragment.newInstance());
        list.add(CouponFragment.newInstance());
        list.add(MineFragment.newInstance());
    }
}