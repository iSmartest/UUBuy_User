package com.ifree.uu.uubuy.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.dialog.ErrorDialog;
import com.ifree.uu.uubuy.mvp.entity.UpdateEntity;
import com.ifree.uu.uubuy.mvp.presenter.UpdatePresenter;
import com.ifree.uu.uubuy.mvp.view.ProjectView;
import com.ifree.uu.uubuy.ui.base.BaseActivity;
import com.ifree.uu.uubuy.uitls.GlobalMethod;
import com.ifree.uu.uubuy.uitls.ToastUtils;
import com.ifree.uu.uubuy.uitls.UpdateManager;

import java.util.Calendar;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2018/10/8 0008
 * Description:
 */
public class UpdateActivity extends BaseActivity {
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
    private String updataAddress,versionName,descc;
    private int versionCode;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_update;
    }


    @Override
    protected void initView() {
        hideBack(5);
        setTitleText("版本更新");
        mUpdatePresenter = new UpdatePresenter(context);
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        tvRight.setText(String.format(getString(R.string.company_copyright), year));
        tvVersion.setText(String.format(getString(R.string.version_format), GlobalMethod.getVersionName(UpdateActivity.this)));
    }

    @Override
    protected void loadData() {
        mUpdatePresenter.onCreate();
        mUpdatePresenter.attachView(mUpdateView);
        mUpdatePresenter.getUpdate("查询中...");
    }

    ProjectView<UpdateEntity> mUpdateView = new ProjectView<UpdateEntity>() {
        @Override
        public void onSuccess(UpdateEntity updateEntity) {
            if (updateEntity.getResultCode().equals("1")){
                ToastUtils.makeText(context,updateEntity.getMsg());
                return;
            }
            versionCode = Integer.parseInt(updateEntity.getData().getVersionCode());
            updataAddress = updateEntity.getData().getAddress();
            versionName = updateEntity.getData().getVersionName();
            descc = updateEntity.getData().getDesc();
            mIntroduce.setText(descc);
            if (versionCode > GlobalMethod.getVersionCode(context)) {
                tvVersionName.setText("最新版" + versionName);
            } else {
                tvVersionName.setText("已是最新版");
            }
        }

        @Override
        public void onError(String result) {
            ToastUtils.makeText(context,result);
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
                    UpdateManager mUpdateManager = new UpdateManager(context,updataAddress,versionName);
                    mUpdateManager.checkUpdateInfo();
                } else {
                    ToastUtils.makeText(context, "当前已是最新版本");
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
