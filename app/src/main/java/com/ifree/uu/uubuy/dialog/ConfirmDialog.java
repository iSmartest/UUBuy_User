package com.ifree.uu.uubuy.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.ifree.uu.uubuy.R;


/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2018/10/23 0023
 * Description:更新弹框
 */
public class ConfirmDialog extends Dialog implements View.OnClickListener {
    TextView tvVersionName;
    TextView tvUpdateLog;
    TextView sureBtn;
    TextView cancelBtn;
    private Context mContext;
    private Callback callback;

    public ConfirmDialog(Context mContext, Callback callback) {
        super(mContext, R.style.CustomDialog);
        this.mContext = mContext;
        this.callback = callback;
        setCustomDialog();
    }

    private void setCustomDialog() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_confirm, null);
        tvVersionName = view.findViewById(R.id.tv_version_name);
        tvUpdateLog = view.findViewById(R.id.dialog_confirm_log);
        sureBtn = view.findViewById(R.id.dialog_confirm_sure);
        cancelBtn = view.findViewById(R.id.dialog_confirm_cancel);
        sureBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);
        try {
            int dividerID = mContext.getResources().getIdentifier("android:id/titleDivider", null, null);
            View divider = findViewById(dividerID);
            divider.setBackgroundColor(Color.TRANSPARENT);
        } catch (Exception e) {
            //上面的代码，是用来去除Holo主题的蓝色线条
            e.printStackTrace();
        }
        super.setContentView(view);
    }

    public ConfirmDialog setContent(String versionName, String updataLog) {
        tvVersionName.setText(versionName);
        tvUpdateLog.setText(updataLog);
        return this;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialog_confirm_cancel:
                callback.callback(0);
                dismiss();
                break;
            case R.id.dialog_confirm_sure:
                callback.callback(1);
                dismiss();
                break;
        }
    }

    public interface Callback {
        void callback(int position);
    }

    @Override
    public void show() {
        windowDeploy(0, 0);
        super.show();
    }

    public void windowDeploy(int x, int y) {
        Window window = getWindow(); // 得到对话框
        window.setBackgroundDrawableResource(R.drawable.transpant_bg); // 设置对话框背景为透明

        WindowManager.LayoutParams wl = window.getAttributes();
        wl.width = (int) (mContext.getApplicationContext().getResources()
                .getDisplayMetrics().widthPixels * 0.75);
        wl.x = x;
        wl.y = y;
        wl.gravity = Gravity.CENTER;// 设置重力
        window.setAttributes(wl);
    }

}
