package com.ifree.uu.uubuy.ui.activity;

import android.support.v7.widget.LinearLayoutManager;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.mvp.entity.CouponEntity;
import com.ifree.uu.uubuy.mvp.presenter.CouponCenterPresenter;
import com.ifree.uu.uubuy.mvp.view.ProjectView;
import com.ifree.uu.uubuy.ui.adapter.CouponCenterAdapter;
import com.ifree.uu.uubuy.ui.base.BaseActivity;
import com.ifree.uu.uubuy.uitls.ToastUtils;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2018/9/20 0020
 * Description:
 */
public class StoreCouponActivity extends BaseActivity {
    private CouponCenterPresenter mCouponCenterPresenter;
    @BindView(R.id.xr_store_coupon)
    XRecyclerView xRecyclerView;
    private CouponCenterAdapter mAdapter;
    private List<CouponEntity.DataBean.CouponList> mList = new ArrayList<>();
    private String couponType = "2";
    private String businessId;
    private int page = 1;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_store_coupon;
    }

    @Override
    protected void initView() {
        hideBack(5);
        setTitleText("我要优惠券");
        businessId = getIntent().getStringExtra("storeId");
        mCouponCenterPresenter = new CouponCenterPresenter(context);
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
                loadData();
            }

            @Override
            public void onLoadMore() {
                page ++ ;
                loadData();
            }
        });

        mAdapter = new CouponCenterAdapter(context,mList,couponType);
        xRecyclerView.setAdapter(mAdapter);
        xRecyclerView.setRefreshing(true);

    }


    @Override
    protected void loadData() {
        mCouponCenterPresenter.onCreate();
        mCouponCenterPresenter.attachView(mCouponView);
        mCouponCenterPresenter.getSearchCouponCenter(uid,businessId,couponType,longitude,latitude,townAdCode,page,"加载中...");

    }

    private ProjectView<CouponEntity> mCouponView = new ProjectView<CouponEntity>() {
        @Override
        public void onSuccess(CouponEntity mCouponEntity) {
            if (page == 1){
                xRecyclerView.refreshComplete();
                mList.clear();
                mAdapter.notifyDataSetChanged();
            }else {
                xRecyclerView.loadMoreComplete();
            }
            if (mCouponEntity.getResultCode().equals("1")){
                ToastUtils.makeText(context,mCouponEntity.getMsg());
                return;
            }
            List<CouponEntity.DataBean.CouponList> couponLists = mCouponEntity.getData().getCouponList();
            if (couponLists != null && !couponLists.isEmpty()){
                mList.addAll(couponLists);
                mAdapter.notifyDataSetChanged();
                if (couponLists.size() < 10){
                    xRecyclerView.setNoMore(true);
                }
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
}
