package com.ifree.uu.uubuy.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.service.entity.MyFootPrintEntity;
import com.ifree.uu.uubuy.service.presenter.MyFootPrintPresenter;
import com.ifree.uu.uubuy.service.view.MyFootPrintView;
import com.ifree.uu.uubuy.ui.adapter.MyFootprintAdapter;
import com.ifree.uu.uubuy.ui.adapter.SearchAdapter;
import com.ifree.uu.uubuy.ui.base.BaseActivity;
import com.ifree.uu.uubuy.uitls.ToastUtils;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/22.
 * Description:
 */
public class MyFootprintActivity extends BaseActivity {
    private MyFootPrintPresenter mMyFootPrintPresenter;
    @BindView(R.id.xr_foot)
    XRecyclerView xRecyclerView;
    @BindView(R.id.iv_foot)
    ImageView iv_foot;
    private int page = 1;
    private List<MyFootPrintEntity.DataBean.FootprintList> mList = new ArrayList<>();
    private MyFootprintAdapter mAdapter;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_footprint;
    }

    @Override
    protected void initView() {
        hideBack(5);
        setTitleText("我的足迹");
        mMyFootPrintPresenter = new MyFootPrintPresenter(context);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xRecyclerView.setLayoutManager(layoutManager);
        xRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        xRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        xRecyclerView.setArrowImageView(R.drawable.iconfont_downgrey);
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                mList.clear();
                mAdapter.notifyDataSetChanged();
                loadData();
            }

            @Override
            public void onLoadMore() {
                page ++ ;
                loadData();
            }
        });

        mAdapter = new MyFootprintAdapter(context,mList);
        xRecyclerView.setAdapter(mAdapter);

    }


    @Override
    protected void loadData() {
        mMyFootPrintPresenter.onCreate();
        mMyFootPrintPresenter.attachView(mMyFootPrintView);
        mMyFootPrintPresenter.getSearchMyFootPrint("116",page,"加载中...");
    }

    private MyFootPrintView mMyFootPrintView = new MyFootPrintView() {
        @Override
        public void onSuccess(MyFootPrintEntity mMyFootPrintEntity) {
            if (page == 1){
                xRecyclerView.refreshComplete();
            }else {
                xRecyclerView.loadMoreComplete();
            }
            if (mMyFootPrintEntity.getResultCode().equals("1")){
                ToastUtils.makeText(context,mMyFootPrintEntity.getMsg());
                return;
            }
            List<MyFootPrintEntity.DataBean.FootprintList> footprintLists = mMyFootPrintEntity.getData().getFootprintList();
            if (footprintLists != null && !footprintLists.isEmpty()){
                iv_foot.setVisibility(View.GONE);
                mList.addAll(footprintLists);
                mAdapter.notifyDataSetChanged();
                if (footprintLists.size() < 10){
                    xRecyclerView.setNoMore(true);
                }
            }else {
                iv_foot.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onError(String result) {
            ToastUtils.makeText(context,result);
            iv_foot.setVisibility(View.VISIBLE);
            if (page == 1){
                xRecyclerView.refreshComplete();
            }else {
                xRecyclerView.loadMoreComplete();
            }
        }
    };

    @OnClick({R.id.iv_foot})
    public void onClickView(View view){
        switch (view.getId()){
            case R.id.iv_foot:
                xRecyclerView.setRefreshing(true);
                break;
        }
    }
}
