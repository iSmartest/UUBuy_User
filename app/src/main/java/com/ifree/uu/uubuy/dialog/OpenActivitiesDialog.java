package com.ifree.uu.uubuy.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.ifree.uu.uubuy.R;

/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2018/10/23 0023
 * Description:打开活动弹框
 */
public class OpenActivitiesDialog extends Dialog implements View.OnClickListener {
    ImageView sureBtn;
    ImageView cancelBtn;
    private Context mContext;
    private Callback callback;

    public OpenActivitiesDialog(Context mContext, Callback callback) {
        super(mContext, R.style.CustomDialog);
        this.mContext = mContext;
        this.callback = callback;
        setCustomDialog();
    }

    private void setCustomDialog() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_open_activities, null);
        sureBtn = view.findViewById(R.id.iv_open_sure);
        cancelBtn = view.findViewById(R.id.iv_close_cancel);
        Animation animation= AnimationUtils.loadAnimation(mContext,R.anim.anim_small);
        cancelBtn.startAnimation(animation);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_close_cancel:
                dismiss();
                break;
            case R.id.iv_open_sure:
                callback.sure();
                break;
        }
    }

    public interface Callback {
        void sure();
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
