package com.ifree.uu.uubuy.ui.fragment;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.app.MyApplication;
import com.ifree.uu.uubuy.listener.RecyclerItemTouchListener;
import com.ifree.uu.uubuy.service.entity.AroundEntity;
import com.ifree.uu.uubuy.service.presenter.AroundPresenter;
import com.ifree.uu.uubuy.service.view.AroundView;
import com.ifree.uu.uubuy.ui.activity.BrandActivity;
import com.ifree.uu.uubuy.ui.activity.FurnitureMarketActivity;
import com.ifree.uu.uubuy.ui.activity.MarketActivity;
import com.ifree.uu.uubuy.ui.activity.ShoppingMallActivity;
import com.ifree.uu.uubuy.ui.activity.StoreActivity;
import com.ifree.uu.uubuy.ui.adapter.AroundAdapter;
import com.ifree.uu.uubuy.ui.base.BaseFragment;
import com.ifree.uu.uubuy.uitls.SPUtil;
import com.ifree.uu.uubuy.uitls.ToastUtils;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/17.
 * Description:
 */
public class AroundFragment extends BaseFragment {
    private AroundPresenter aroundPresenter;
    @BindView(R.id.xr_around)
    XRecyclerView xRecyclerView;
    @BindView(R.id.iv_placeholder)
    ImageView imageView;
    private int page = 1;
    private List<AroundEntity.DataBean.ActivitiesList> mList = new ArrayList<>();
    private AroundAdapter mAdapter;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        //注册广播
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.ifree.uu.location.changed");
        getActivity().registerReceiver(mAllBroad, intentFilter);
    }
    @Override
    protected int getLayout() {
        return R.layout.fragment_around;
    }

    @Override
    protected void initView() {
        aroundPresenter = new AroundPresenter(context);
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
            }

            @Override
            public void onLoadMore() {
                page ++ ;
                initData();
            }
        });
        mAdapter = new AroundAdapter(context,mList);
        xRecyclerView.setAdapter(mAdapter);
        xRecyclerView.addOnItemTouchListener(new RecyclerItemTouchListener(xRecyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
                int position = vh.getAdapterPosition()-1;
                if (position < 0 | position >= mList.size()){
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putString("fristActivitiesId",mList.get(position).getActivitiesId());
                bundle.putString("fristActivitiesType",mList.get(position).getType());
                bundle.putString("fristActivitiesName",mList.get(position).getActivitiesName());
                switch (mList.get(position).getType()){// 1 商城 2 超市 3 建材 4 车 5 品牌 6 教育
                    case "1":
                        if (mList.get(position).getActivitiesType().equals("1")){
                            MyApplication.openActivity(context, StoreActivity.class,bundle);
                        }else {
                            MyApplication.openActivity(context,ShoppingMallActivity.class,bundle);
                        }
                        break;
                    case "2"://超市
                        if (mList.get(position).getActivitiesType().equals("1")){
                            MyApplication.openActivity(context, StoreActivity.class,bundle);
                        }else {
                            MyApplication.openActivity(context,MarketActivity.class,bundle);
                        }
                        break;
                    case "3":
                        if (mList.get(position).getActivitiesType().equals("1")){
                            MyApplication.openActivity(context, StoreActivity.class,bundle);
                        }else {
                            MyApplication.openActivity(context,FurnitureMarketActivity.class,bundle);
                        }
                        break;
                    case "4":
                        if (mList.get(position).getActivitiesType().equals("1")){
                            MyApplication.openActivity(context, BrandActivity.class,bundle);
                        }else {
                            MyApplication.openActivity(context,ShoppingMallActivity.class,bundle);
                        }
                        break;
                    case "5":
                        if (mList.get(position).getActivitiesType().equals("1")){
                            MyApplication.openActivity(context, BrandActivity.class,bundle);
                        }else {
                            MyApplication.openActivity(context,ShoppingMallActivity.class,bundle);
                        }
                        break;
                    case "6":
                        MyApplication.openActivity(context,BrandActivity.class,bundle);
                        break;
                }
            }
        });
    }

    @Override
    protected void initData() {
        aroundPresenter.onCreate();
        aroundPresenter.attachView(mAroundView);
        aroundPresenter.getSearchArounds(SPUtil.getLongitude(context),SPUtil.getLatitude(context)
                ,SPUtil.getTownAdCode(context),page,SPUtil.getUid(context),"加载中...");
    }
    private AroundView mAroundView = new AroundView() {
        @Override
        public void onSuccess(AroundEntity mAroundEntity) {
            if (page == 1){
                xRecyclerView.refreshComplete();
            }else {
                xRecyclerView.loadMoreComplete();
            }
            if (mAroundEntity.getResultCode().equals("1")){
                ToastUtils.makeText(context,mAroundEntity.getMsg());
                return;
            }
            List<AroundEntity.DataBean.ActivitiesList> activitiesLists = mAroundEntity.getData().getActivityList();
            if (activitiesLists != null && !activitiesLists.isEmpty()){
                mList.addAll(activitiesLists);
                mAdapter.notifyDataSetChanged();
                if (activitiesLists.size() < 10){
                    xRecyclerView.setNoMore(true);
                }
            }else {
                xRecyclerView.setNoMore(true);
            }
        }

        @Override
        public void onError(String result) {
            ToastUtils.makeText(context,result);
            if (page == 1){
                xRecyclerView.refreshComplete();
            }else {
                xRecyclerView.loadMoreComplete();
            }
        }
    };

    private BroadcastReceiver mAllBroad = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, final Intent intent) {
            //接到广播通知后刷新数据源
            xRecyclerView.setRefreshing(true);
        }
    };
}
