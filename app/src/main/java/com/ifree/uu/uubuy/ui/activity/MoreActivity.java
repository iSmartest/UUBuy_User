package com.ifree.uu.uubuy.ui.activity;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.mvp.entity.MoreEntity;
import com.ifree.uu.uubuy.mvp.presenter.MorePresenter;
import com.ifree.uu.uubuy.mvp.view.ProjectView;
import com.ifree.uu.uubuy.ui.adapter.MoreAdapter;
import com.ifree.uu.uubuy.ui.base.BaseActivity;
import com.ifree.uu.uubuy.uitls.ToastUtils;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2018/9/18 0018
 * Description:
 */
public class MoreActivity extends BaseActivity {
    private MorePresenter mMorePresenter;
    @BindView(R.id.tv_more_commodity)
    TextView mCommodity;
    @BindView(R.id.tv_more_store)
    TextView mStore;
    @BindView(R.id.xr_more)
    XRecyclerView xRecyclerView;
    private ColorStateList csl1,csl2;
    private int page = 1;
    private MoreAdapter mAdapter;
    private List<MoreEntity.DataBean.CommodityList> mList = new ArrayList<>();
    private String classifyId;
    private String type = "1";
    private String menuId;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_more;
    }


    @Override
    protected void initView() {
        hideBack(5);

        classifyId = getIntent().getStringExtra("classifyId");
        menuId = getIntent().getStringExtra("fristActivitiesId");
        mMorePresenter = new MorePresenter(context);
        Resources resource = context.getResources();
        csl1 = resource.getColorStateList(R.color.text_green);
        csl2 = resource.getColorStateList(R.color.title_color);
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
        mAdapter = new MoreAdapter(context,mList,type);
        xRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void loadData() {
        mMorePresenter.onCreate();
        mMorePresenter.attachView(mMoreView);
        mMorePresenter.getSearchMoreListInfo(classifyId,type,menuId,uid,page,"加载中...");
    }


    private ProjectView<MoreEntity> mMoreView = new ProjectView<MoreEntity>() {
        @Override
        public void onSuccess(MoreEntity mMoreEntity) {
            if (page == 1){
                xRecyclerView.refreshComplete();
                mList.clear();
                mAdapter.notifyDataSetChanged();
            }else {
                xRecyclerView.loadMoreComplete();
            }
            if (mMoreEntity.getResultCode().equals("1")){
                ToastUtils.makeText(context,mMoreEntity.getMsg());
                return;
            }
            List<MoreEntity.DataBean.CommodityList> commodityLists = mMoreEntity.getData().getCommodityList();
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
    @OnClick({R.id.tv_more_commodity,R.id.tv_more_store})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.tv_more_commodity:
                mCommodity.setTextColor(csl1);
                mStore.setTextColor(csl2);
                type = "1";
                mAdapter.setType(type);
                xRecyclerView.setRefreshing(true);
                break;
            case R.id.tv_more_store:
                mCommodity.setTextColor(csl2);
                mStore.setTextColor(csl1);
                type = "0";
                mAdapter.setType(type);
                xRecyclerView.setRefreshing(true);
                break;
        }
    }
}
