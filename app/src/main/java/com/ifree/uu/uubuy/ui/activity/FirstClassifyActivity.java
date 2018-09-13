package com.ifree.uu.uubuy.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.app.MyApplication;
import com.ifree.uu.uubuy.listener.RecyclerItemTouchListener;
import com.ifree.uu.uubuy.service.entity.FirstClassifyEntity;
import com.ifree.uu.uubuy.service.presenter.FirstClassifyPresenter;
import com.ifree.uu.uubuy.service.view.FirstClassifyView;
import com.ifree.uu.uubuy.ui.adapter.FirstClassifyAdapter;
import com.ifree.uu.uubuy.ui.adapter.FirstMenuAdapter;
import com.ifree.uu.uubuy.ui.base.BaseActivity;
import com.ifree.uu.uubuy.uitls.ToastUtils;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/23.
 * Description:
 */
public class FirstClassifyActivity extends BaseActivity {
    private FirstClassifyPresenter mFirstClassifyPresenter;
    @BindView(R.id.rc_menu)
    ListView rc_menu;
    @BindView(R.id.xr_market)
    XRecyclerView xRecyclerView;
    private View headerView;
    private FirstMenuAdapter mFirstMenuAdapter;
    private FirstClassifyAdapter mAdapter;
    private List<FirstClassifyEntity.DataBean.MenuList> mMenuList;
    private List<FirstClassifyEntity.DataBean.FristActivitiesList> mList;
    private int page = 1;
    private String adTypeId;
    private String type;
    private String title;
    private String menuId = "";
    @Override
    protected int getLayoutId() {
        return R.layout.activity_first_classify;
    }

    @Override
    protected void initView() {
        hideBack(5);
        setTitleText("综合商场");
        mMenuList = new ArrayList<>();
        mList = new ArrayList<>();
        adTypeId = getIntent().getStringExtra("adTypeId");
        type = getIntent().getStringExtra("type");
        title = getIntent().getStringExtra("title");
        headerView = LayoutInflater.from(context).inflate(R.layout.header_menu,null);
        if (headerView != null){
            rc_menu.addHeaderView(headerView);
        }

        mFirstClassifyPresenter = new FirstClassifyPresenter(context);
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
                xRecyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                page ++ ;
                loadData();
                xRecyclerView.loadMoreComplete();
                mAdapter.notifyDataSetChanged();
                xRecyclerView.setNoMore(true);
            }
        });

        mAdapter = new FirstClassifyAdapter(context);
        xRecyclerView.setAdapter(mAdapter);

        mFirstMenuAdapter = new FirstMenuAdapter(context,mMenuList,type);
        rc_menu.setAdapter(mFirstMenuAdapter);

        xRecyclerView.addOnItemTouchListener(new RecyclerItemTouchListener(xRecyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
//                int position = vh.getAdapterPosition();
//                if (position < 0 | position >= mAdTypeList.size()){
//                    return;
//                }
//                MyApplication.openActivity(context,MarketActivity.class);
                MyApplication.openActivity(context,MarketOrStoreActivity.class);
            }
        });
    }


    @Override
    protected void loadData() {
        mFirstClassifyPresenter.onCreate();
        mFirstClassifyPresenter.attachView(mFirstClassifyView);
        mFirstClassifyPresenter.getSearchClassifyListInfo(longitude,latitude,townAdCode,adTypeId,type,menuId,page,uid,"加载中...");
    }

    private FirstClassifyView mFirstClassifyView = new FirstClassifyView() {
        @Override
        public void onSuccess(FirstClassifyEntity mFirstClassifyEntity) {
            if (mFirstClassifyEntity.getResultCode().equals("1")){
                ToastUtils.makeText(context,mFirstClassifyEntity.getMsg());
                return;
            }
            List<FirstClassifyEntity.DataBean.MenuList> menuLists = mFirstClassifyEntity.getData().getMenuList();
            if (menuLists != null && !menuLists.isEmpty()){
                if (mMenuList.isEmpty()){
                    mMenuList.addAll(menuLists);
                    mFirstMenuAdapter.notifyDataSetChanged();
                }
            }
            List<FirstClassifyEntity.DataBean.FristActivitiesList> fristActivitiesLists = mFirstClassifyEntity.getData().getFristActivitiesList();
            if (fristActivitiesLists != null && !fristActivitiesLists.isEmpty()){
                mList.addAll(fristActivitiesLists);
                mAdapter.notifyDataSetChanged();
            }

        }

        @Override
        public void onError(String result) {

        }
    };
}
