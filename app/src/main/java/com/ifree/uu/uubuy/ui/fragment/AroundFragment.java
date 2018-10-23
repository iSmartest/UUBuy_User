package com.ifree.uu.uubuy.ui.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.ImageView;
import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.mvp.entity.AroundEntity;
import com.ifree.uu.uubuy.mvp.presenter.AroundPresenter;
import com.ifree.uu.uubuy.mvp.view.ProjectView;
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
    public void onAttach(Context context) {
        super.onAttach(context);
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
    }

    @Override
    protected void initData() {
        aroundPresenter.onCreate();
        aroundPresenter.attachView(mAroundView);
        aroundPresenter.getSearchArounds(SPUtil.getLongitude(context),SPUtil.getLatitude(context)
                ,SPUtil.getTownAdCode(context),page,SPUtil.getUid(context),"加载中...");
    }
    private ProjectView<AroundEntity> mAroundView = new ProjectView<AroundEntity>() {
        @Override
        public void onSuccess(AroundEntity mAroundEntity) {
            if (page == 1){
                mList.clear();
                mAdapter.notifyDataSetChanged();
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
