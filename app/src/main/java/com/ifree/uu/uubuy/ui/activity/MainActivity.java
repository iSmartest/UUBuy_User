package com.ifree.uu.uubuy.ui.activity;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.app.MyApplication;
import com.ifree.uu.uubuy.listener.GaoDeLocationListener;
import com.ifree.uu.uubuy.ui.base.BaseActivity;
import com.ifree.uu.uubuy.ui.fragment.ActivitiesFragment;
import com.ifree.uu.uubuy.ui.fragment.AroundFragment;
import com.ifree.uu.uubuy.ui.fragment.HomeFragment;
import com.ifree.uu.uubuy.ui.fragment.MineFragment;
import com.ifree.uu.uubuy.ui.fragment.OrderFragment;
import com.ifree.uu.uubuy.uitls.AppManager;
import com.ifree.uu.uubuy.uitls.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;


public class MainActivity extends BaseActivity {
    @BindView(R.id.ly_base_search)
    LinearLayout mBaseSearch;
    @BindView(R.id.edt_a_key_search)
    EditText keyWord;
    @BindView(R.id.iv_main_home)
    RadioButton mHome;
    @BindView(R.id.iv_main_around)
    RadioButton mAround;
    @BindView(R.id.iv_main_activities)
    RadioButton mActivities;
    @BindView(R.id.iv_main_order)
    RadioButton mOrder;
    @BindView(R.id.iv_main_mine)
    RadioButton mMine;
    // 定义一个变量，来标识是否退出
    private static boolean isExit = false;
    @SuppressLint("HandlerLeak")
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void loadData() {
        keyWord.setCursorVisible(false);
        keyWord.setFocusable(false);
        keyWord.setFocusableInTouchMode(false);
    }

    @Override
    protected void initView() {
        changeFragment(HomeFragment.class, R.id.linear_main_layout_content, true, null, true);
        initLocation();
        hideBack(1);
    }


    @OnClick({R.id.iv_main_home, R.id.iv_main_around, R.id.iv_main_activities, R.id.iv_main_order, R.id.iv_main_mine
            , R.id.ly_base_location, R.id.ly_restart_location, R.id.iv_base_message, R.id.iv_base_setting, R.id.ly_base_search,R.id.edt_a_key_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_main_home:
                changeFragment(HomeFragment.class, R.id.linear_main_layout_content, true, null, false);
                hideBack(1);
                break;
            case R.id.iv_main_around:
                changeFragment(AroundFragment.class, R.id.linear_main_layout_content, true, null, false);
                hideBack(2);
                break;
            case R.id.iv_main_activities:
                changeFragment(ActivitiesFragment.class, R.id.linear_main_layout_content, true, null, false);
                hideBack(3);
                setTitleText("活动圈");
                break;
            case R.id.iv_main_order:
                changeFragment(OrderFragment.class, R.id.linear_main_layout_content, true, null, false);
                hideBack(3);
                setTitleText("订单");
                break;
            case R.id.iv_main_mine:
                changeFragment(MineFragment.class, R.id.linear_main_layout_content, true, null, false);
                hideBack(4);
                break;
            case R.id.ly_base_location:
                MyApplication.openActivity(context, SelectHotCityActivity.class);
                ToastUtils.makeText(context, "选择城市页面");
                break;
            case R.id.ly_restart_location:
                initLocation();
                break;
            case R.id.iv_base_message:
                MyApplication.openActivity(context, MessageActivity.class);
                break;
            case R.id.iv_base_setting:
                MyApplication.openActivity(context, MySettingActivity.class);
                break;
            case R.id.ly_base_search:
                MyApplication.openActivity(context, SearchActivity.class);
                break;
            case R.id.edt_a_key_search:
                MyApplication.openActivity(context, SearchActivity.class);
                break;
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            mHandler.sendEmptyMessageDelayed(0, 3000);
        } else {
            AppManager.finishAllActivity();
            System.exit(0);
        }
    }

    private void initLocation() {
        GaoDeLocationListener gaoDeLocationListener = new GaoDeLocationListener(context, new GaoDeLocationListener.OnQuestResultListener() {
            @Override
            public void success(String result) {
                setLocation(result);
                Log.i("TAG", "success: " + result);
            }

            @Override
            public void error(String result) {
                setLocation(result);
            }
        });
        gaoDeLocationListener.startLocation();
    }
}
