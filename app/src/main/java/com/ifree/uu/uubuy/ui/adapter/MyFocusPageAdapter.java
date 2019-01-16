package com.ifree.uu.uubuy.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ifree.uu.uubuy.ui.fragment.MyFocusMallFragment;
import com.ifree.uu.uubuy.ui.fragment.MyFocusStoreFragment;

import java.util.HashMap;

/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2019/1/7 0007
 * Description:
 */
public class MyFocusPageAdapter extends FragmentPagerAdapter {

    private int num;
    private HashMap<Integer, Fragment> mFragmentHashMap = new HashMap<>();

    public MyFocusPageAdapter(FragmentManager fm, int num) {
        super(fm);
        this.num = num;
    }

    @Override
    public Fragment getItem(int position) {

        return createFragment(position);
    }

    @Override
    public int getCount() {
        return num;
    }

    private Fragment createFragment(int pos) {
        Fragment fragment = mFragmentHashMap.get(pos);

        if (fragment == null) {
            switch (pos) {
                case 0:
                    fragment = new MyFocusMallFragment();
                    break;
                case 1:
                    fragment = new MyFocusStoreFragment();
                    break;

            }
            mFragmentHashMap.put(pos, fragment);
        }
        return fragment;
    }
}
