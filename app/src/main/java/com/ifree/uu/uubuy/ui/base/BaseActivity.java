package com.ifree.uu.uubuy.ui.base;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.dialog.ProgressDialog;
import com.ifree.uu.uubuy.ui.activity.CheckPermissionsActivity;
import com.ifree.uu.uubuy.uitls.AppManager;
import com.ifree.uu.uubuy.uitls.SPUtil;
import com.ifree.uu.uubuy.uitls.StatusBarUtil;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/15.
 * Description:
 */
public abstract class BaseActivity extends CheckPermissionsActivity{
    protected Context context;
    private BaseFragment lastFragment;
    public static boolean anInt = true;
    protected String latitude;
    protected String longitude;
    protected String townAdCode;
    protected String uid;
    @BindView(R.id.lay_bg)
    RelativeLayout lay_bg;
    @BindView(R.id.ly_base_left)
    LinearLayout mLeft;
    @BindView(R.id.iv_base_back)
    ImageView ivBack;
    @BindView(R.id.ly_base_location)
    LinearLayout mLocation;
    @BindView(R.id.tv_city_location)
    TextView mCityLocation;
    @BindView(R.id.ly_base_center)
    LinearLayout mCenter;
    @BindView(R.id.tv_base_titleText)
    TextView mTitleText;
    @BindView(R.id.ly_base_search)
    LinearLayout mBaseSearch;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.edt_a_key_search)
    EditText edtKeyword;
    @BindView(R.id.ly_base_right)
    LinearLayout mRight;
    @BindView(R.id.tv_base_rightText)
    TextView mRightText;
    @BindView(R.id.iv_base_message)
    ImageView mMessage;
    @BindView(R.id.iv_base_question)
    ImageView mQuestion;
    @BindView(R.id.iv_base_setting)
    ImageView mSetting;
    @BindView(R.id.ly_restart_location)
    LinearLayout mRestartLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        context = this;
        ButterKnife.bind(this);
        StatusBarUtil.transparencyBar(this); //设置状态栏全透明
        StatusBarUtil.StatusBarLightMode(this); //设置白底黑字
        AppManager.addActivity(this);
        latitude = SPUtil.getString(context,"latitude");
        longitude = SPUtil.getString(context,"longitude");
        townAdCode = SPUtil.getString(context,"townAdCode");
        uid = SPUtil.getString(context,"uid");
        initView();//实例化
        loadData();//加载数据
    }

    protected abstract int getLayoutId();

    @Override
    protected void onPause() {
        super.onPause();
        anInt = false;
    }
    @Override
    protected void onStart() {
        super.onStart();
        anInt = true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        anInt = false;

    }
    @Override
    protected void onResume() {
        super.onResume();
//        if (badgeCount != 0){//去除角标
//            badgeCount = 0;
//            ShortcutBadger.removeCount(context);
//        }
        anInt = true;
    }

    protected abstract void loadData();

    protected abstract void initView();


    /**
     * 切换Fragment的方法
     *
     * @param fragmentClass 要跳转的Fragment
     * @param containId     容器ID
     * @param isHidden      是否隐藏
     * @param bundle        参数
     * @param isBack        是否添加到回退栈
     * @return
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public BaseFragment changeFragment(Class<? extends BaseFragment> fragmentClass, int containId, boolean isHidden, Bundle bundle, boolean isBack) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        //获取Fragment的类名，用类名当做Tag
        String fragmentName = fragmentClass.getSimpleName();
        //根据tag来查找当前Fragment，如果不为null 就代表当前Fragment已经被加载过至少一次
        BaseFragment currentFragment = (BaseFragment) manager.findFragmentByTag(fragmentName);
        if (currentFragment == null) {
            //如果Fragment为null 就创建Fragment对象，添加到FragmentManager中
            try {
                //通过Java动态代理创建的对象
                currentFragment = fragmentClass.newInstance();
                //添加到FragmentManager中
                transaction.add(containId, currentFragment, fragmentName);

            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        if (isHidden) {
            //隐藏上一个Fragment
            if (lastFragment != null)
                transaction.hide(lastFragment);
            //显示当前Fragment
            transaction.show(currentFragment);
        } else {
            //替换上一个Fragment
            transaction.replace(containId, currentFragment, fragmentName);
        }
        //传递参数
        if (bundle != null) {
            Objects.requireNonNull(currentFragment).setBundle(bundle);
        }

        if (isBack) {
            transaction.addToBackStack(fragmentName);
        }

        transaction.commit();

        lastFragment = currentFragment;

        return lastFragment;

    }

    public void hideBack(int position) {
        Resources resource = context.getResources();
        ColorStateList csl1 = resource.getColorStateList(R.color.app_main_default);
        ColorStateList csl2 = resource.getColorStateList(R.color.title_color);
        if (position==4){
            lay_bg.setBackgroundColor(ContextCompat.getColor(context,R.color.text_green));
            mTitleText.setTextColor(csl1);
            mMessage.setImageResource(R.drawable.message_write);
        }else if (position==7){
            lay_bg.setBackgroundColor(ContextCompat.getColor(context,R.color.text_green));
            mTitleText.setTextColor(csl1);
            mMessage.setImageResource(R.drawable.base_message);
        }else {
            lay_bg.setBackgroundColor(ContextCompat.getColor(context,R.color.app_main_default));
            mTitleText.setTextColor(csl2);
            mMessage.setImageResource(R.drawable.base_message);
        }
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        switch (position){
            case 1://定位、搜索、消息
                mLeft.setVisibility(View.VISIBLE);
                ivBack.setVisibility(View.GONE);
                mLocation.setVisibility(View.VISIBLE);
                mCenter.setVisibility(View.VISIBLE);
                mTitleText.setVisibility(View.GONE);
                mBaseSearch.setVisibility(View.VISIBLE);
                mRight.setVisibility(View.VISIBLE);
                mRightText.setVisibility(View.GONE);
                mMessage.setVisibility(View.VISIBLE);
                mSetting.setVisibility(View.GONE);
                mQuestion.setVisibility(View.GONE);
                mRestartLocation.setVisibility(View.GONE);
                break;
            case 2://定位、搜索、重新定位
                mLeft.setVisibility(View.VISIBLE);
                ivBack.setVisibility(View.GONE);
                mLocation.setVisibility(View.VISIBLE);
                mCenter.setVisibility(View.VISIBLE);
                mTitleText.setVisibility(View.GONE);
                mBaseSearch.setVisibility(View.VISIBLE);
                mRight.setVisibility(View.VISIBLE);
                mRightText.setVisibility(View.GONE);
                mMessage.setVisibility(View.GONE);
                mSetting.setVisibility(View.GONE);
                mQuestion.setVisibility(View.GONE);
                mRestartLocation.setVisibility(View.VISIBLE);
                break;
            case 3://标题
                mLeft.setVisibility(View.GONE);
                mCenter.setVisibility(View.VISIBLE);
                mTitleText.setVisibility(View.VISIBLE);
                mBaseSearch.setVisibility(View.GONE);
                mRight.setVisibility(View.VISIBLE);
                mMessage.setVisibility(View.VISIBLE);
                mSetting.setVisibility(View.GONE);
                mRestartLocation.setVisibility(View.GONE);
                mRightText.setVisibility(View.GONE);
                mQuestion.setVisibility(View.GONE);
                break;
            case 4://设置、消息
                mLeft.setVisibility(View.GONE);
                mCenter.setVisibility(View.GONE);
                mRight.setVisibility(View.VISIBLE);
                mMessage.setVisibility(View.VISIBLE);
                mSetting.setVisibility(View.VISIBLE);
                mRestartLocation.setVisibility(View.GONE);
                mRightText.setVisibility(View.GONE);
                mQuestion.setVisibility(View.GONE);
                break;
            case 5://返回键、标题、标题颜色改变
                mLeft.setVisibility(View.VISIBLE);
                ivBack.setVisibility(View.VISIBLE);
                mLocation.setVisibility(View.GONE);
                mCenter.setVisibility(View.VISIBLE);
                mTitleText.setVisibility(View.VISIBLE);
                mBaseSearch.setVisibility(View.GONE);
                mRight.setVisibility(View.GONE);
                break;
            case 6://返回键、标题、右文字
                mLeft.setVisibility(View.VISIBLE);
                ivBack.setVisibility(View.VISIBLE);
                mLocation.setVisibility(View.GONE);
                mCenter.setVisibility(View.VISIBLE);
                mTitleText.setVisibility(View.VISIBLE);
                mBaseSearch.setVisibility(View.GONE);
                mRight.setVisibility(View.VISIBLE);
                mMessage.setVisibility(View.GONE);
                mSetting.setVisibility(View.GONE);
                mRestartLocation.setVisibility(View.GONE);
                mRightText.setVisibility(View.VISIBLE);
                mQuestion.setVisibility(View.VISIBLE);
                break;
             case 7://返回键、标题、问题，标题颜色改变
                mLeft.setVisibility(View.VISIBLE);
                ivBack.setVisibility(View.VISIBLE);
                mLocation.setVisibility(View.GONE);
                mCenter.setVisibility(View.VISIBLE);
                mTitleText.setVisibility(View.VISIBLE);
                mBaseSearch.setVisibility(View.GONE);
                mRight.setVisibility(View.VISIBLE);
                mMessage.setVisibility(View.GONE);
                mSetting.setVisibility(View.GONE);
                mRestartLocation.setVisibility(View.GONE);
                mRightText.setVisibility(View.GONE);
                mQuestion.setVisibility(View.VISIBLE);
                break;
        }
    }

    public void setTitleText(String string) {
        TextView titleTv = findViewById(R.id.tv_base_titleText);
        titleTv.setText(string);
    }
    public void setLocation(String string) {
        TextView tvLocation = findViewById(R.id.tv_city_location);
        tvLocation.setText(string);
    }

    public void setRightText(String string) {
        TextView tvRightText = findViewById(R.id.tv_base_rightText);
        tvRightText.setText(string);
    }
}
