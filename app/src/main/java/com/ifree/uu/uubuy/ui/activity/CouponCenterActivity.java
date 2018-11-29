package com.ifree.uu.uubuy.ui.activity;

import android.content.res.ColorStateList;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.ui.base.BaseActivity;
import com.ifree.uu.uubuy.ui.fragment.MarketCouponFragment;
import com.ifree.uu.uubuy.ui.fragment.StoreCouponFragment;
import com.ifree.uu.uubuy.ui.fragment.UUCouponFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/21.
 * Description:
 */
public class CouponCenterActivity extends BaseActivity {
    @BindView(R.id.tv_my_coupon_not_used)
    TextView tv_all_order;
    @BindView(R.id.tv_my_coupon_used)
    TextView tv_wait_payment;
    @BindView(R.id.tv_is_expired)
    TextView tv_wait_goods;
    @BindView(R.id.vPager)
    ViewPager viewPager;
    private ColorStateList selectedColor, unSelectedColor;//是否选择显示的颜色
    private List<Fragment> fragments;// Tab页面列表
    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_coupon;
    }

    @Override
    protected void loadData() {
        fragments = new ArrayList<>();
        fragments.add(new UUCouponFragment());
        fragments.add(new MarketCouponFragment());
        fragments.add(new StoreCouponFragment());
        viewPager.setAdapter(new myPagerAdapter(getSupportFragmentManager(),fragments));
        //页面定位
        viewPager.setCurrentItem(0);
        tv_all_order.setTextColor(selectedColor);
        tv_wait_payment.setTextColor(unSelectedColor);
        tv_wait_goods.setTextColor(unSelectedColor);
        tv_all_order.setText("UU券");
        tv_wait_payment.setText("商场券");
        tv_wait_goods.setText("商户券");
        viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
    }

    @Override
    protected void initView() {
        hideBack(5);
        setTitleText("领券中心");
        selectedColor = context.getResources().getColorStateList(R.color.text_green);
        unSelectedColor = context.getResources().getColorStateList(R.color.text_main_color);
    }


    @OnClick({R.id.tv_my_coupon_not_used, R.id.tv_my_coupon_used, R.id.tv_is_expired})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_my_coupon_not_used:
                setCurrentItem(0);
                break;
            case R.id.tv_my_coupon_used:
                setCurrentItem(1);
                break;
            case R.id.tv_is_expired:
                setCurrentItem(2);
                break;
        }
    }

    //头标点击监听
    private void setCurrentItem(int position){
        switch (position) {
            case 0:
                tv_all_order.setTextColor(selectedColor);
                tv_wait_payment.setTextColor(unSelectedColor);
                tv_wait_goods.setTextColor(unSelectedColor);
                break;
            case 1:
                tv_all_order.setTextColor(unSelectedColor);
                tv_wait_payment.setTextColor(selectedColor);
                tv_wait_goods.setTextColor(unSelectedColor);
                break;
            case 2:
                tv_all_order.setTextColor(unSelectedColor);
                tv_wait_payment.setTextColor(unSelectedColor);
                tv_wait_goods.setTextColor(selectedColor);
                break;
        }
        viewPager.setCurrentItem(position);

    }


    //为选项卡绑定监听器
    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        public void onPageScrollStateChanged(int index) {
        }

        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        public void onPageSelected(int index) {
            switch (index) {
                case 0:
                    tv_all_order.setTextColor(selectedColor);
                    tv_wait_payment.setTextColor(unSelectedColor);
                    tv_wait_goods.setTextColor(unSelectedColor);
                    break;
                case 1:
                    tv_all_order.setTextColor(unSelectedColor);
                    tv_wait_payment.setTextColor(selectedColor);
                    tv_wait_goods.setTextColor(unSelectedColor);
                    break;
                case 2:
                    tv_all_order.setTextColor(unSelectedColor);
                    tv_wait_payment.setTextColor(unSelectedColor);
                    tv_wait_goods.setTextColor(selectedColor);
                    break;
            }
        }
    }

    //定义适配器
    class myPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragmentList;

        public myPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
            super(fm);
            this.fragmentList = fragmentList;
        }
        //得到每个页面
        @Override
        public Fragment getItem(int arg0) {
            return (fragmentList == null || fragmentList.size() == 0) ? null
                    : fragmentList.get(arg0);
        }

        //每个页面的title
        @Override
        public CharSequence getPageTitle(int position) {
            return null;
        }


        //页面的总个数
        @Override
        public int getCount() {
            return fragmentList == null ? 0 : fragmentList.size();
        }
    }
}
