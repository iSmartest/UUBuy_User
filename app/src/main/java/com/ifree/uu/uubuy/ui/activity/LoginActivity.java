package com.ifree.uu.uubuy.ui.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.custom.LazyViewPager;
import com.ifree.uu.uubuy.listener.LoginCompleteListener;
import com.ifree.uu.uubuy.ui.base.BaseActivity;
import com.ifree.uu.uubuy.ui.fragment.CodeLoginFragment;
import com.ifree.uu.uubuy.ui.fragment.PasswordFragment;
import com.ifree.uu.uubuy.ui.fragment.ThirdFragment;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/23.
 * Description:
 */
public class LoginActivity extends BaseActivity implements LoginCompleteListener{
    @BindView(R.id.tv_code_login)
    TextView tvCode;
    @BindView(R.id.tv_password_login)
    TextView tvPassword;
    @BindView(R.id.tv_third_login)
    TextView tvThird;
    @BindView(R.id.iv_code_select)
    ImageView ivCode;
    @BindView(R.id.iv_password_select)
    ImageView ivPassword;
    @BindView(R.id.iv_third_select)
    ImageView ivThird;
    @BindView(R.id.vPager)
    LazyViewPager viewPager;
    private int selectedColor, unSelectedColor;//是否选择显示的颜色
    private List<Fragment> fragments;// Tab页面列表
    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void loadData() {
        fragments = new ArrayList<>();
        fragments.add(new CodeLoginFragment());
        fragments.add(new PasswordFragment());
        fragments.add(new ThirdFragment());
        viewPager.setAdapter(new myPagerAdapter(getSupportFragmentManager(),fragments));
        //页面定位
        viewPager.setCurrentItem(0);
        tvCode.setTextColor(selectedColor);
        tvPassword.setTextColor(unSelectedColor);
        tvThird.setTextColor(unSelectedColor);
        ivCode.setVisibility(View.VISIBLE);
        ivPassword.setVisibility(View.GONE);
        ivThird.setVisibility(View.GONE);
        viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
    }

    @Override
    protected void initView() {
        hideBack(5);
        setTitleText("登录");
        selectedColor = getResources().getColor(R.color.text_green);
        unSelectedColor = getResources().getColor(R.color.app_main_default);
    }

    @OnClick({R.id.tv_code_login, R.id.tv_password_login, R.id.tv_third_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_code_login:
                setCurrentItem(0);
                break;
            case R.id.tv_password_login:
                setCurrentItem(1);
                break;
            case R.id.tv_third_login:
                setCurrentItem(2);
                break;
        }
    }

    //头标点击监听
    private void setCurrentItem(int position){
        switch (position) {
            case 0:
                tvCode.setTextColor(selectedColor);
                tvPassword.setTextColor(unSelectedColor);
                tvThird.setTextColor(unSelectedColor);
                ivCode.setVisibility(View.VISIBLE);
                ivPassword.setVisibility(View.GONE);
                ivThird.setVisibility(View.GONE);
                break;
            case 1:
                tvCode.setTextColor(unSelectedColor);
                tvPassword.setTextColor(selectedColor);
                tvThird.setTextColor(unSelectedColor);
                ivCode.setVisibility(View.GONE);
                ivPassword.setVisibility(View.VISIBLE);
                ivThird.setVisibility(View.GONE);
                break;
            case 2:
                tvCode.setTextColor(unSelectedColor);
                tvPassword.setTextColor(unSelectedColor);
                tvThird.setTextColor(selectedColor);
                ivCode.setVisibility(View.GONE);
                ivPassword.setVisibility(View.GONE);
                ivThird.setVisibility(View.VISIBLE);
                break;
        }
        viewPager.setCurrentItem(position);

    }

    @Override
    public void SendMessageValue() {
        Intent intent = new Intent();
        intent.setAction("com.ifree.uu.mine.changed");
        getApplicationContext().sendBroadcast(intent);
        finish();
    }


    //为选项卡绑定监听器
    public class MyOnPageChangeListener implements LazyViewPager.OnPageChangeListener {

        public void onPageScrollStateChanged(int index) {
        }

        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        public void onPageSelected(int index) {
            switch (index) {
                case 0:
                    tvCode.setTextColor(selectedColor);
                    tvPassword.setTextColor(unSelectedColor);
                    tvThird.setTextColor(unSelectedColor);
                    ivCode.setVisibility(View.VISIBLE);
                    ivPassword.setVisibility(View.GONE);
                    ivThird.setVisibility(View.GONE);
                    break;
                case 1:
                    tvCode.setTextColor(unSelectedColor);
                    tvPassword.setTextColor(selectedColor);
                    tvThird.setTextColor(unSelectedColor);
                    ivCode.setVisibility(View.GONE);
                    ivPassword.setVisibility(View.VISIBLE);
                    ivThird.setVisibility(View.GONE);
                    break;
                case 2:
                    tvCode.setTextColor(unSelectedColor);
                    tvPassword.setTextColor(unSelectedColor);
                    tvThird.setTextColor(selectedColor);
                    ivCode.setVisibility(View.GONE);
                    ivPassword.setVisibility(View.GONE);
                    ivThird.setVisibility(View.VISIBLE);
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
