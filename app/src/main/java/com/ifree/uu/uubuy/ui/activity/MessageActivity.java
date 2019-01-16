package com.ifree.uu.uubuy.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.app.MyApplication;
import com.ifree.uu.uubuy.common.CommonActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2019/1/10 0010
 * Description:
 */
public class MessageActivity extends CommonActivity {
    @BindView(R.id.ll_account_message)
    LinearLayout llAccountMessage;
    @BindView(R.id.ll_system_message)
    LinearLayout llSystemMessage;
    @BindView(R.id.ll_activities_message)
    LinearLayout llActivitiesMessage;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_message;
    }

    @Override
    protected int getTitleBarId() {
        return R.id.tb_message_title;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.ll_account_message,R.id.ll_system_message,R.id.ll_activities_message})
    public void onViewClicked(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.ll_account_message:
                bundle.putString("title",context.getString(R.string.account_message));
                MyApplication.openActivity(context,MessageInfoActivity.class,bundle);
                break;
            case R.id.ll_system_message:
                bundle.putString("title",context.getString(R.string.system_message));
                MyApplication.openActivity(context,MessageInfoActivity.class,bundle);
                break;
            case R.id.ll_activities_message:
                bundle.putString("title",context.getString(R.string.activities_message));
                MyApplication.openActivity(context,MessageInfoActivity.class,bundle);
                break;
        }
    }
}
