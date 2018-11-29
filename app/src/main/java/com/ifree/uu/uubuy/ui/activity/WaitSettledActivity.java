package com.ifree.uu.uubuy.ui.activity;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.ui.base.BaseActivity;

/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2018/9/29 0029
 * Description:
 */
public class WaitSettledActivity extends BaseActivity {
    private String title;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_wait_settled;
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initView() {
        hideBack(5);
        title = getIntent().getStringExtra("title");
        setTitleText(title);
    }
}
