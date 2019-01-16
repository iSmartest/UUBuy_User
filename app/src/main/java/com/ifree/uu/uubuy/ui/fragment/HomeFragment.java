package com.ifree.uu.uubuy.ui.fragment;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.app.MyApplication;
import com.ifree.uu.uubuy.common.CommonLazyFragment;
import com.ifree.uu.uubuy.mvp.modle.HomeBean;
import com.ifree.uu.uubuy.mvp.persenter.HomePresenter;
import com.ifree.uu.uubuy.ui.activity.BombShopActivity;
import com.ifree.uu.uubuy.ui.activity.GoShoppingActivity;
import com.ifree.uu.uubuy.ui.activity.HotProductActivity;
import com.ifree.uu.uubuy.ui.activity.MessageActivity;
import com.ifree.uu.uubuy.ui.activity.MyOrderActivity;
import com.ifree.uu.uubuy.ui.activity.SearchActivity;
import com.ifree.uu.uubuy.ui.activity.SelectHotCityActivity;
import com.ifree.uu.uubuy.ui.adapter.CityADAdapter;
import com.ifree.uu.uubuy.ui.adapter.HomeAdapter;
import com.ifree.uu.uubuy.widget.XCollapsingToolbarLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2019/1/3 0003
 * Description: 首页
 */
public class HomeFragment extends CommonLazyFragment implements XCollapsingToolbarLayout.OnScrimsListener {
    private HomePresenter mHomePresenter;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    @BindView(R.id.rc_city_ad)
    RecyclerView rc_city_ad;
    @BindView(R.id.tv_home_go_shopping)
    TextView tvGoShopping;
    @BindView(R.id.tv_home_bomb_shop)
    TextView tvBombShop;
    @BindView(R.id.tv_home_hot_product)
    TextView tvHotProduct;
    @BindView(R.id.tv_home_my_order)
    TextView tvMyOrder;
    @BindView(R.id.tv_home_address)
    TextView tvAddress;
    @BindView(R.id.tv_home_search)
    TextView tvSearch;
    @BindView(R.id.iv_home_message)
    ImageView ivMessage;
    private List<HomeBean.DataBean.ActivitiesList> mList = new ArrayList<>();
    private List<HomeBean.DataBean.CityADList> mCityADList = new ArrayList<>();
    private HomeAdapter mAdapter;
    private CityADAdapter cityADAdapter;
    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected int getTitleBarId() {
        return R.id.tb_test_bar;
    }

    @Override
    protected void initView() {
        mHomePresenter = new HomePresenter(context);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new HomeAdapter(context, mList);
        recyclerView.setAdapter(mAdapter);
        cityADAdapter = new CityADAdapter(context, mCityADList);
        rc_city_ad.setLayoutManager(new GridLayoutManager(context, 2));
        rc_city_ad.setAdapter(cityADAdapter);
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
//                mAdapter.loadMore(buildItems());
                refreshLayout.finishLoadmore();
            }
        });
    }


    @Override
    protected void initData() {

    }

    @Override
    public void onScrimsStateChange(boolean shown) {

    }

    @OnClick({R.id.tv_home_go_shopping,R.id.tv_home_bomb_shop,R.id.tv_home_hot_product,R.id.tv_home_my_order,
            R.id.tv_home_address,R.id.tv_home_search,R.id.iv_home_message})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_home_go_shopping:
                MyApplication.openActivity(context,GoShoppingActivity.class);
                break;
            case R.id.tv_home_bomb_shop:
                MyApplication.openActivity(context,BombShopActivity.class);
                break;
            case R.id.tv_home_hot_product:
                MyApplication.openActivity(context,HotProductActivity.class);
                break;
            case R.id.tv_home_my_order:
                MyApplication.openActivity(context,MyOrderActivity.class);
                break;
            case R.id.tv_home_address:
                MyApplication.openActivity(context, SelectHotCityActivity.class);
                break;
            case R.id.tv_home_search:
                MyApplication.openActivity(context,SearchActivity.class);
                break;
            case R.id.iv_home_message:
                MyApplication.openActivity(context,MessageActivity.class);
                break;
        }
    }
}
