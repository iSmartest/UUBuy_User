package com.ifree.uu.uubuy.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hjq.toast.ToastUtils;
import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.common.CommonActivity;
import com.ifree.uu.uubuy.dialog.ErrorDialog;
import com.ifree.uu.uubuy.mvp.modle.UpdateBean;
import com.ifree.uu.uubuy.mvp.persenter.UpdatePresenter;
import com.ifree.uu.uubuy.mvp.view.ProjectView;
import com.ifree.uu.uubuy.utils.GlobalMethod;
import com.ifree.uu.uubuy.utils.UpdateAppUtils;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2018/10/8 0008
 * Description:
 */
public class UpdateActivity extends CommonActivity {
    private UpdatePresenter mUpdatePresenter;
    @BindView(R.id.a_about_lay_rate)
    TextView mRate;
    @BindView(R.id.a_about_check_version)
    LinearLayout llVersion;
    @BindView(R.id.text_lay_introduce_dec)
    TextView mIntroduce;
    @BindView(R.id.a_about_tv_right)
    TextView tvRight;
    @BindView(R.id.a_about_tv_version)
    TextView tvVersion;
    @BindView(R.id.a_about_version_name)
    TextView tvVersionName;
    private String updataAddress,versionName,updataLog;
    private int versionCode = 0;
    private boolean force = false;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_update;
    }

    @Override
    protected int getTitleBarId() {
        return R.id.tb_version_update_title;
    }


    @Override
    protected void initView() {
        mUpdatePresenter = new UpdatePresenter(context);
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        tvRight.setText(String.format(getString(R.string.company_copyright), year));
        tvVersion.setText(String.format(getString(R.string.version_format), GlobalMethod.getVersionName(UpdateActivity.this)));
    }

    @Override
    protected void initData() {
        mUpdatePresenter.onCreate();
        mUpdatePresenter.attachView(mUpdateView);
        mUpdatePresenter.getUpdate("查询中...");
    }

    ProjectView<UpdateBean> mUpdateView = new ProjectView<UpdateBean>() {
        @Override
        public void onSuccess(UpdateBean updateBean) {
            if (updateBean.getResultCode().equals("1")){
                ToastUtils.show(updateBean.getMsg());
                return;
            }
            versionCode = Integer.parseInt(updateBean.getData().getVersionCode());
            updataAddress = updateBean.getData().getAddress();
            versionName = updateBean.getData().getVersionName();
            updataLog = updateBean.getData().getDesc();
            mIntroduce.setText(updataLog);
            if (updateBean.getData().getForce().equals("0")) {
                force = false;
            } else {
                force = true;
            }
            if (versionCode > GlobalMethod.getVersionCode(context)) {
                tvVersionName.setText("最新版" + versionName);
            } else {
                tvVersionName.setText("已是最新版");
            }
        }

        @Override
        public void onError(String result) {
            ToastUtils.show(result);
        }
    };


    @OnClick({R.id.a_about_lay_rate, R.id.a_about_check_version})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.a_about_lay_rate:
                rate();
                break;
            case R.id.a_about_check_version:
                if (versionCode > GlobalMethod.getVersionCode(context)) {
                    UpdateAppUtils.from(UpdateActivity.this)
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
                    ToastUtils.show("当前已是最新版本");
                }
                break;
        }
    }

    /**
     * 打分
     */
    private void rate() {
        try {
            Uri uri = Uri.parse("market://details?id=" + getPackageName());
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } catch (Exception e) {
            new ErrorDialog(UpdateActivity.this, null).show();
        }
    }
}
