package com.ifree.uu.uubuy.ui.activity;

import android.content.res.ColorStateList;
import android.content.res.XmlResourceParser;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.ui.base.BaseActivity;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2018/9/29 0029
 * Description:
 */
public class TestActivity extends BaseActivity {
    private String title;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_test;
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
