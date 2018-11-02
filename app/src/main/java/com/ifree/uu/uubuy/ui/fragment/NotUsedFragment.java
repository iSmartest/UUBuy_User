package com.ifree.uu.uubuy.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.mvp.entity.CouponEntity;
import com.ifree.uu.uubuy.mvp.presenter.MyCouponPresenter;
import com.ifree.uu.uubuy.mvp.view.ProjectView;
import com.ifree.uu.uubuy.ui.adapter.CouponAdapter;
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
 * Created by 2018/8/21.
 * Description:
 */
public class NotUsedFragment extends BaseFragment {
    private MyCouponPresenter mMyCouponPresenter;
    @BindView(R.id.xr_coupon)
    XRecyclerView xRecyclerView;
    @BindView(R.id.tv_coupon_empty)
    TextView mEmpty;
    private int page = 1;
    private CouponAdapter mAdapter;
    private List<CouponEntity.DataBean.CouponList> mList;
    private String couponType = "0";
    private String businessId = "";
    @Override
    protected int getLayout() {
        return R.layout.fragment_coupon;
    }

    @Override
    protected void initView() {
        mList = new ArrayList<>();
        mMyCouponPresenter = new MyCouponPresenter(context);
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
        mAdapter = new CouponAdapter(context,mList,couponType);
        xRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        mMyCouponPresenter.onCreate();
        mMyCouponPresenter.attachView(mCouponView);
        mMyCouponPresenter.getSearchMyCoupon(SPUtil.getUid(context),businessId,couponType,SPUtil.getLongitude(context),SPUtil.getLatitude(context),SPUtil.getTownAdCode(context),page,"加载中...");
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
                mEmpty.setVisibility(View.GONE);
                mList.addAll(couponLists);
                mAdapter.notifyDataSetChanged();
                if (couponLists.size() < 10){
                    xRecyclerView.setNoMore(true);
                }
            }else {
                mEmpty.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onError(String result) {
            ToastUtils.makeText(context,result);
            mEmpty.setVisibility(View.VISIBLE);
            if (page == 1){
                xRecyclerView.refreshComplete();
            }else {
                xRecyclerView.loadMoreComplete();
            }
        }
    };
}
