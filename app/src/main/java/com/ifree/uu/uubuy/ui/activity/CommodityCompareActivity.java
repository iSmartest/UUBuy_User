package com.ifree.uu.uubuy.ui.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.listener.RecyclerItemTouchListener;
import com.ifree.uu.uubuy.service.entity.CompareCommodityEntity;
import com.ifree.uu.uubuy.service.presenter.CommodityComparePresenter;
import com.ifree.uu.uubuy.service.view.ProjectView;
import com.ifree.uu.uubuy.ui.adapter.CommodityCompareAdapter;
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
 * Created by 2018/9/19 0019
 * Description:
 */
public class CommodityCompareActivity extends BaseActivity{
    private CommodityComparePresenter mCommodityComparePresenter;
    @BindView(R.id.xr_compare)
    XRecyclerView xRecyclerView;
    private int page = 1;
    private CommodityCompareAdapter mAdapter;
    private List<CompareCommodityEntity.DataBean.PList> mList = new ArrayList<>();
    private String commodityId;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_commodity_compare;
    }

    @Override
    protected void initView() {
        hideBack(5);
        setTitleText("同类精品");
        commodityId = getIntent().getStringExtra("commodityId");
        mCommodityComparePresenter = new CommodityComparePresenter(context);
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

        mAdapter = new CommodityCompareAdapter(context,mList);
        xRecyclerView.setAdapter(mAdapter);
        xRecyclerView.addOnItemTouchListener(new RecyclerItemTouchListener(xRecyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
                int position = vh.getAdapterPosition() - 1;
                if (position < 0 | position >= mList.size()){
                    return;
                }
                Intent intent = new Intent();
                intent.putExtra("commodityId",mList.get(position).getCommodityId());
                intent.putExtra("type","3");
                intent.putExtra("commodityIcon",mList.get(position).getBannerPic());
                setResult(1002,intent);
                finish();
            }
        });

    }

    @Override
    protected void loadData() {
        mCommodityComparePresenter.onCreate();
        mCommodityComparePresenter.attachView(mCompareView);
        mCommodityComparePresenter.getSearchCompareInfo(commodityId,page,"加载中");

    }
    private ProjectView<CompareCommodityEntity> mCompareView = new ProjectView<CompareCommodityEntity>() {
        @Override
        public void onSuccess(CompareCommodityEntity mCompareCommodityEntity) {
            if (page == 1){
                xRecyclerView.refreshComplete();
                mList.clear();
                mAdapter.notifyDataSetChanged();
            }else {
                xRecyclerView.loadMoreComplete();
            }
            if (mCompareCommodityEntity.getResultCode().equals("1")){
                ToastUtils.makeText(context,mCompareCommodityEntity.getMsg());
                return;
            }
            List<CompareCommodityEntity.DataBean.PList> commodityLists = mCompareCommodityEntity.getData().getpList();
            if (commodityLists != null && !commodityLists.isEmpty()){
                mList.addAll(commodityLists);
                mAdapter.notifyDataSetChanged();
                if (commodityLists.size() < 10){
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
