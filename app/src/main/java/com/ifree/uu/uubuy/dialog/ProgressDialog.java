package com.ifree.uu.uubuy.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ifree.uu.uubuy.R;

/**
 * 类说明: 自定义ProgressDialog
 * Created by 小火
 * Create time on  2018/8/13
 * My mailbox is 1403241630@qq.com
 */
@SuppressWarnings("JavaDoc")
public class ProgressDialog {

    public static void dismissDialog(Dialog dialog) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
            dialog = null;
        }
    }

    /**
     * 得到自定义的progressDialog
     *
     * @param context
     * @param msg
     * @return
     */
    public static Dialog createLoadingDialog(Context context, String msg) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.dlg_progress_bar, null);// 得到加载view
        LinearLayout layout = v.findViewById(R.id.dialog_view);// 加载布局
        TextView tipTextView = v.findViewById(R.id.tipTextView);// 提示文字
        ImageView spaceshipImage = v.findViewById(R.id.img);
//        Animation animation = AnimationUtils.loadAnimation(
//                context, R.anim.progress_dialog_rotate);
//        spaceshipImage.setAnimation(animation);
        Glide.with(context).load(R.drawable.loading_icon).into(spaceshipImage);
        tipTextView.setText(msg);// 设置加载信息
        Dialog loadingDialog = new Dialog(context, R.style.loading_dialog);// 创建自定义样式dialog
        loadingDialog.setCancelable(true);// 可以用“返回键”
        loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));// 设置布局
        return loadingDialog;
    }
}
