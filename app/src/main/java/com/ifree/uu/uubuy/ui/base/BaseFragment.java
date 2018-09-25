package com.ifree.uu.uubuy.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ifree.uu.uubuy.uitls.SPUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/15.
 * Description: 所有Fragment都继承此类
 */

public abstract class BaseFragment extends Fragment {
    protected Bundle bundle;
    protected Context context;
    protected View view;
    protected Unbinder unbinder;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        if (view == null){
            view = inflater.inflate(getLayout(),container,false);
        }
        unbinder = ButterKnife.bind(this, view);
        initView();
        initData();
        return view;
    }

    protected abstract int getLayout();

    protected abstract void initView();

    protected abstract void initData();

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
