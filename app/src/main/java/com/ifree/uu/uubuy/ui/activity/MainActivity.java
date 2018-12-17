package com.ifree.uu.uubuy.ui.activity;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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
import com.ifree.uu.uubuy.dialog.OpenActivitiesDialog;
import com.ifree.uu.uubuy.listener.GaoDeLocationListener;
import com.ifree.uu.uubuy.mvp.entity.UpdateEntity;
import com.ifree.uu.uubuy.mvp.entity.UserInfoEntity;
import com.ifree.uu.uubuy.mvp.presenter.ElasticFramePresenter;
import com.ifree.uu.uubuy.mvp.presenter.UpdatePresenter;
import com.ifree.uu.uubuy.mvp.view.ProjectView;
import com.ifree.uu.uubuy.service.UURunService;
import com.ifree.uu.uubuy.ui.base.BaseActivity;
import com.ifree.uu.uubuy.ui.fragment.AroundFragment;
import com.ifree.uu.uubuy.ui.fragment.CollectionFragment;
import com.ifree.uu.uubuy.ui.fragment.HomeFragment;
import com.ifree.uu.uubuy.ui.fragment.MineFragment;
import com.ifree.uu.uubuy.ui.fragment.OrderFragment;
import com.ifree.uu.uubuy.uitls.AppManager;
import com.ifree.uu.uubuy.uitls.GlobalMethod;
import com.ifree.uu.uubuy.uitls.SPUtil;
import com.ifree.uu.uubuy.uitls.ToastUtils;
import com.ifree.uu.uubuy.uitls.UpdateAppUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    private UpdatePresenter mUpdatePresenter;
    private ElasticFramePresenter mElasticFramePresenter;
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
    private String updataAddress, versionName, updataLog;
    private int versionCode;
    private boolean force = false;
    private OpenActivitiesDialog openActivitiesDialog;
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
    protected void initView() {
        mUpdatePresenter = new UpdatePresenter(context);
        mElasticFramePresenter = new ElasticFramePresenter(context);
        setLocation(SPUtil.getString(context, "district"));
        hideBack(1);
        changeFragment(HomeFragment.class, R.id.linear_main_layout_content, true, null, true);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.ifree.uu.location.changed");
        registerReceiver(mAllBroad, intentFilter);
        Intent startIntent = new Intent(getApplicationContext(), UURunService.class);
        startService(startIntent);

    }

    @Override
    protected void loadData() {
        mUpdatePresenter.onCreate();
        mUpdatePresenter.attachView(mUpdateView);
        mUpdatePresenter.getUpdate("加载中...");
        keyWord.setCursorVisible(false);
        keyWord.setFocusable(false);
        keyWord.setFocusableInTouchMode(false);
        openActivitiesDialog = new OpenActivitiesDialog(context, new OpenActivitiesDialog.Callback() {
            @Override
            public void sure() {
                MyApplication.openActivity(context,UUActivitiesActivity.class);
            }
        });

        openActivitiesDialog.show();
    }

    ProjectView<UpdateEntity> mUpdateView = new ProjectView<UpdateEntity>() {
        @Override
        public void onSuccess(UpdateEntity updateEntity) {
            if (updateEntity.getResultCode().equals("1")) {
                ToastUtils.makeText(context, updateEntity.getMsg());
                return;
            }
            versionCode = Integer.parseInt(updateEntity.getData().getVersionCode());
            updataAddress = updateEntity.getData().getAddress();
            versionName = updateEntity.getData().getVersionName();
            updataLog = updateEntity.getData().getDesc();
            if (updateEntity.getData().getForce().equals("0")) {
                force = false;
            } else {
                force = true;
            }
            if (versionCode > GlobalMethod.getVersionCode(context)) {
                UpdateAppUtils.from(MainActivity.this)
                        .checkBy(UpdateAppUtils.CHECK_BY_VERSION_CODE)//版本检查方式
                        .serverVersionName(versionName)//获取版本名
                        .serverVersionCode(versionCode)//获取版本号
                        .apkPath(updataAddress)//下载地址
                        .updateInfo(updataLog)
                        .downloadBy(UpdateAppUtils.DOWNLOAD_BY_APP)//下载方式，app或浏览器
                        .showNotification(true)
                        .isForce(force)//是否强制更新
                        .update();
            } else {
                searchElasticFrame();
            }
        }
        @Override
        public void onError(String result) {
            searchElasticFrame();
        }
    };


    private void searchElasticFrame() {
        mElasticFramePresenter.onCreate();
        mElasticFramePresenter.attachView(new ProjectView<UserInfoEntity>(){
            @Override
            public void onSuccess(final UserInfoEntity userInfoEntity) {
                if (userInfoEntity.getResultCode().equals("1")){
                    return;
                }
                if (userInfoEntity.getData().getPop().equals("1")){
                    openActivitiesDialog = new OpenActivitiesDialog(context, new OpenActivitiesDialog.Callback() {
                        @Override
                        public void sure() {
                            if (userInfoEntity.getData().getIsEnroll().equals("0")){
                                MyApplication.openActivity(context,UUActivitiesActivity.class);
                            }else {
                                MyApplication.openActivity(context, ReceivePrizeCenterActivity.class);//已报名
                            }
                        }
                    });
                    openActivitiesDialog.show();
                    SPUtil.putString(context,"pop",userInfoEntity.getData().getPop());
                }
            }

            @Override
            public void onError(String result) {

            }
        });
        mElasticFramePresenter.searchElasticFrame(uid);
    }


    @OnClick({R.id.iv_main_home, R.id.iv_main_around, R.id.iv_main_activities, R.id.iv_main_order, R.id.iv_main_mine
            , R.id.ly_base_location, R.id.ly_restart_location, R.id.iv_base_message, R.id.iv_base_setting, R.id.ly_base_search, R.id.edt_a_key_search})
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
                changeFragment(CollectionFragment.class, R.id.linear_main_layout_content, true, null, false);
                hideBack(3);
                setTitleText("收藏");
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
                setLocation(SPUtil.getString(context, "district"));
                Log.i("TAG", "success: " + result);
                Intent intent = new Intent();
                intent.setAction("com.ifree.uu.location.changed");
                getApplicationContext().sendBroadcast(intent);
            }

            @Override
            public void error(String result) {
                setLocation(result);
            }
        });
        gaoDeLocationListener.startLocation();
    }

    private BroadcastReceiver mAllBroad = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, final Intent intent) {
            //接到广播通知后刷新数据源
            setLocation(SPUtil.getDistrict(context));
        }
    };

}
