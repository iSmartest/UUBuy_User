package com.ifree.uu.uubuy.ui.fragment;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.service.entity.ActivitiesEntity;
import com.ifree.uu.uubuy.service.presenter.ActivitiesPresenter;
import com.ifree.uu.uubuy.service.view.ActivitiesView;
import com.ifree.uu.uubuy.ui.adapter.ActivitiesAdapter;
import com.ifree.uu.uubuy.ui.base.BaseFragment;
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
 * Created by 2018/8/17.
 * Description:
 */
public class ActivitiesFragment extends BaseFragment {
    private ActivitiesPresenter activitiesPresenter;
    @BindView(R.id.tv_activities_market)
    TextView mMarket;
    @BindView(R.id.tv_activities_store)
    TextView mStore;
    @BindView(R.id.tv_activities_commodity)
    TextView mCommodity;
    @BindView(R.id.xr_activities)
    XRecyclerView xRecyclerView;
    private int page = 1;
    private ColorStateList csl1,csl2;
    private List<ActivitiesEntity.DataBean.ActivitiesList> mList = new ArrayList<>();
    private ActivitiesAdapter mAdapter;
    private String activitiesType = "0";
    @Override
    protected int getLayout() {
        return R.layout.fragment_activities;
    }

    @Override
    protected void initView() {
        activitiesPresenter = new ActivitiesPresenter(context);
        Resources resource = context.getResources();
        csl1 = resource.getColorStateList(R.color.app_main_default);
        csl2 = resource.getColorStateList(R.color.text_main_color);
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
                initData();
                xRecyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                page ++ ;
                initData();
                xRecyclerView.loadMoreComplete();
                mAdapter.notifyDataSetChanged();
                xRecyclerView.setNoMore(true);
            }
        });
        mAdapter = new ActivitiesAdapter(context,mList,activitiesType);
        xRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        activitiesPresenter.onCreate();
        activitiesPresenter.attachView(mActivitiesView);
        activitiesPresenter.getSearchActivities(longitude,latitude,townAdCode,page,uid,activitiesType,"加载中...");
    }

    private ActivitiesView mActivitiesView = new ActivitiesView() {
        @Override
        public void onSuccess(ActivitiesEntity mActivitiesEntity) {
            if (mActivitiesEntity.getResultCode().equals("1")){
                ToastUtils.makeText(context,mActivitiesEntity.getMsg());
                return;
            }
            List<ActivitiesEntity.DataBean.ActivitiesList> activitiesLists = mActivitiesEntity.getData().getActivitiesList();
            if (activitiesLists != null && !activitiesLists.isEmpty() && activitiesLists.size() > 0){
                mList.addAll(activitiesLists);
                mAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onError(String result) {
            ToastUtils.makeText(context,result);
        }
    };

    @OnClick({R.id.tv_activities_market, R.id.tv_activities_store, R.id.tv_activities_commodity})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_activities_market:
                mMarket.setBackgroundResource(R.drawable.shape_left_select_background);
                mStore.setBackgroundResource(R.drawable.shape_center_background);
                mCommodity.setBackgroundResource(R.drawable.shape_right_background);
                mMarket.setTextColor(csl1);
                mStore.setTextColor(csl2);
                mCommodity.setTextColor(csl2);
                activitiesType = "0";
                mAdapter.setType(activitiesType);
                xRecyclerView.setRefreshing(true);
                break;
            case R.id.tv_activities_store:
                mMarket.setBackgroundResource(R.drawable.shape_left_background);
                mStore.setBackgroundResource(R.drawable.shape_center_select_background);
                mCommodity.setBackgroundResource(R.drawable.shape_right_background);
                mMarket.setTextColor(csl2);
                mStore.setTextColor(csl1);
                mCommodity.setTextColor(csl2);
                activitiesType = "1";
                mAdapter.setType(activitiesType);
                xRecyclerView.setRefreshing(true);
                break;
            case R.id.tv_activities_commodity:
                mMarket.setBackgroundResource(R.drawable.shape_left_background);
                mStore.setBackgroundResource(R.drawable.shape_center_background);
                mCommodity.setBackgroundResource(R.drawable.shape_right_select_background);
                mMarket.setTextColor(csl2);
                mStore.setTextColor(csl2);
                mCommodity.setTextColor(csl1);
                activitiesType = "2";
                mAdapter.setType(activitiesType);
                xRecyclerView.setRefreshing(true);
                break;
        }
    }

}
