package com.ifree.uu.uubuy.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.text.InputType;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.uitls.ToastUtils;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/21.
 * Description:
 */

public class EditContentDialog extends Dialog implements View.OnClickListener {
    private TextView tv_tips;
    private Context mContext;
    private Button sureButton, cancelButton;
    private EditText change_name;
    private OnSureBtnClickListener mSureListener;
    public EditContentDialog(Context context, int tips, OnSureBtnClickListener sureListener) {
        super(context);
        this.mContext = context;
        this.mSureListener = sureListener;
        setContentView(R.layout.dialog_edit_content);
        tv_tips = (TextView) findViewById(R.id.d_tips_tv);
        change_name = (EditText) findViewById(R.id.change_name);
        cancelButton = (Button) findViewById(R.id.d_tips_btn_cancel);
        cancelButton.setOnClickListener(this);
        sureButton = (Button) findViewById(R.id.d_tips_btn_sure);
        sureButton.setOnClickListener(this);
        tv_tips.setText(tips);
        tv_tips.setTextSize(TypedValue.COMPLEX_UNIT_PX, context.getResources().getDimensionPixelSize(R.dimen.textsize));
        try{
            int dividerID = mContext.getResources().getIdentifier("android:id/titleDivider", null, null);
            View divider=findViewById(dividerID);
            divider.setBackgroundColor(Color.TRANSPARENT);
        }catch(Exception e){
            //上面的代码，是用来去除Holo主题的蓝色线条
            e.printStackTrace();
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.d_tips_btn_cancel:
                dismiss();
                break;
            case R.id.d_tips_btn_sure:
                String string = change_name.getText().toString().trim();
                if (mSureListener != null) {
                    if(TextUtils.isEmpty(string)){
                        ToastUtils.showMessageShort(mContext,"输入框不能为空");
                        return;
                    }
                    mSureListener.sure(string);
                }
                dismiss();
                break;
        }
    }
    public void setEditText(int resourceId,boolean inputType) {
        change_name.setHint(resourceId);
        if (inputType){
            change_name.setInputType(InputType.TYPE_CLASS_NUMBER);
        }
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

    public interface OnSureBtnClickListener {
        public void sure(String string);
    }
}

