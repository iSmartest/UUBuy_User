package com.ifree.uu.uubuy.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.service.entity.AroundEntity;
import com.ifree.uu.uubuy.service.entity.HomeEntity;
import com.ifree.uu.uubuy.service.presenter.AroundPresenter;
import com.ifree.uu.uubuy.service.presenter.HomePresenter;
import com.ifree.uu.uubuy.service.view.AroundView;
import com.ifree.uu.uubuy.ui.adapter.AroundAdapter;
import com.ifree.uu.uubuy.ui.adapter.HomeAdapter;
import com.ifree.uu.uubuy.ui.base.BaseFragment;
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
    private int page = 1;
    private List<AroundEntity.ActivitiesList> mList = new ArrayList<>();
    private AroundAdapter mAdapter;
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
        mAdapter = new AroundAdapter(context,mList);
        xRecyclerView.setAdapter(mAdapter);
        xRecyclerView.setRefreshing(true);
    }

    @Override
    protected void initData() {
        aroundPresenter.onCreate();
        aroundPresenter.attachView(mAroundView);
        aroundPresenter.getSearchArounds(longitude,latitude,townAdCode,page,uid,"加载中...");
    }
    private AroundView mAroundView = new AroundView() {
        @Override
        public void onSuccess(AroundEntity mAroundEntity) {
            if (mAroundEntity.getResult().equals("1")){
                ToastUtils.makeText(context,mAroundEntity.getResultCode());
                return;
            }
            List<AroundEntity.ActivitiesList> activitiesLists = mAroundEntity.getActivitiesList();
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
}
