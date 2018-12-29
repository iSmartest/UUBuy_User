package com.ifree.uu.uubuy.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.app.MyApplication;
import com.ifree.uu.uubuy.config.Constant;
import com.ifree.uu.uubuy.listener.RecyclerItemTouchListener;
import com.ifree.uu.uubuy.mvp.entity.FirstClassifyEntity;
import com.ifree.uu.uubuy.mvp.presenter.FirstClassifyPresenter;
import com.ifree.uu.uubuy.mvp.view.ProjectView;
import com.ifree.uu.uubuy.ui.adapter.FirstClassifyAdapter;
import com.ifree.uu.uubuy.ui.adapter.FirstMenuAdapter;
import com.ifree.uu.uubuy.ui.base.BaseActivity;
import com.ifree.uu.uubuy.uitls.SPUtil;
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
 * Created by 2018/8/23.
 * Description:
 */
public class FirstClassifyActivity extends BaseActivity implements FirstMenuAdapter.firstMenuListener {
    private FirstClassifyPresenter mFirstClassifyPresenter;
    @BindView(R.id.rc_menu)
    ListView rc_menu;
    @BindView(R.id.xr_market)
    XRecyclerView xRecyclerView;
    private View headerView;
    private TextView mheadItem;
    private FirstMenuAdapter mFirstMenuAdapter;
    private FirstClassifyAdapter mAdapter;
    private List<FirstClassifyEntity.DataBean.MenuList> mMenuList;
    private List<FirstClassifyEntity.DataBean.FristActivitiesList> mList;
    private int page = 1;
    private String adTypeId;
    private String type;
    private String title;
    private String menuId = "";
    private ColorStateList csl1,csl2;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_first_classify;
    }

    @Override
    protected void initView() {
        hideBack(6);
        mMenuList = new ArrayList<>();
        mList = new ArrayList<>();
        csl1 = context.getResources().getColorStateList(R.color.text_subtitle_color);
        csl2 = context.getResources().getColorStateList(R.color.text_green);
        adTypeId = getIntent().getStringExtra("adTypeId");
        type = getIntent().getStringExtra("type");
        title = getIntent().getStringExtra("title");
        setTitleText(title);
        setRightText(SPUtil.getString(context, "district"));
        headerView = LayoutInflater.from(context).inflate(R.layout.header_menu,null);
        mheadItem = headerView.findViewById(R.id.tv_first_item_menu_name);
        mheadItem.setText(title);
        mheadItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuId = "";
                xRecyclerView.setRefreshing(true);
                mheadItem.setTextColor(csl2);
                mFirstMenuAdapter.setDefSelect(-1);
            }
        });
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
                loadData();
            }

            @Override
            public void onLoadMore() {
                page ++ ;
                loadData();
            }
        });

        mAdapter = new FirstClassifyAdapter(context,mList);
        xRecyclerView.setAdapter(mAdapter);

        mFirstMenuAdapter = new FirstMenuAdapter(context,mMenuList,type);
        mFirstMenuAdapter.setFirstMenuListener(this);
        rc_menu.setAdapter(mFirstMenuAdapter);

        rc_menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0 && mMenuList.size() ==0){
                    return;
                }
                mheadItem.setTextColor(csl1);
                menuId = mMenuList.get(position-1).getMenuId();
                xRecyclerView.setRefreshing(true);
                mFirstMenuAdapter.setDefSelect(position-1);
            }
        });
        xRecyclerView.addOnItemTouchListener(new RecyclerItemTouchListener(xRecyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
                int position = vh.getAdapterPosition()-1;
                if (position < 0 | position >= mList.size()){
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putString("fristActivitiesId",mList.get(position).getFristActivitiesId());
                bundle.putString("fristActivitiesType",mList.get(position).getFristActivitiesType());
                bundle.putString("fristActivitiesName",mList.get(position).getFristActivitiesName());
                switch (mList.get(position).getFristActivitiesType()){// 1 商城 2 超市 3 建材 4 车 5 品牌 6 教育
                    case "1":
                        MyApplication.openActivity(context,ShoppingMallActivity.class,bundle);
                        break;
                }
            }
        });
    }


    @Override
    protected void loadData() {
        mFirstClassifyPresenter.onCreate();
        mFirstClassifyPresenter.attachView(mFirstClassifyView);
        mFirstClassifyPresenter.getSearchClassifyListInfo(longitude,latitude,townAdCode,adTypeId,type,menuId,page,uid,"加载中...");
    }

    private ProjectView<FirstClassifyEntity> mFirstClassifyView = new ProjectView<FirstClassifyEntity>() {
        @Override
        public void onSuccess(FirstClassifyEntity mFirstClassifyEntity) {
            if (page == 1) {
                xRecyclerView.refreshComplete();
                mList.clear();
                mAdapter.notifyDataSetChanged();
            } else {
                xRecyclerView.loadMoreComplete();
            }
            if (mFirstClassifyEntity.getResultCode().equals("1")){
                ToastUtils.makeText(context,mFirstClassifyEntity.getMsg());
                return;
            }
            List<FirstClassifyEntity.DataBean.MenuList> menuLists = mFirstClassifyEntity.getData().getMenuList();
            if (menuLists != null && !menuLists.isEmpty()){
                if (mMenuList.size() == 0){
                    mMenuList.addAll(menuLists);
                    mFirstMenuAdapter.notifyDataSetChanged();
                }
            }
            List<FirstClassifyEntity.DataBean.FristActivitiesList> fristActivitiesLists = mFirstClassifyEntity.getData().getFristActivitiesList();
            if (fristActivitiesLists != null && !fristActivitiesLists.isEmpty()){
                mList.addAll(fristActivitiesLists);
                mAdapter.notifyDataSetChanged();
            }
            if (fristActivitiesLists.size() < 10) {
                xRecyclerView.setNoMore(true);
            }
        }

        @Override
        public void onError(String result) {
            ToastUtils.makeText(context,result);
            if (page == 1) {
                xRecyclerView.refreshComplete();
            } else {
                xRecyclerView.loadMoreComplete();
            }
        }
    };

    @Override
    public void onFirstMenu(int position,int i) {
        menuId = mMenuList.get(position).getSecondList().get(i).getMenuId();
        xRecyclerView.setRefreshing(true);
    }
    @OnClick({R.id.tv_base_rightText})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_base_rightText:
                MyApplication.openActivityForResult(FirstClassifyActivity.this, SelectHotCityActivity.class,1001);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.FIRST_CLASSIFY_REQUEST && resultCode == Activity.RESULT_OK){
            setRightText(SPUtil.getString(context,"district"));
            latitude = SPUtil.getString(context,"latitude");
            longitude = SPUtil.getString(context,"longitude");
            townAdCode = SPUtil.getString(context,"townAdCode");
            mMenuList.clear();
            mFirstMenuAdapter.notifyDataSetChanged();
            xRecyclerView.setRefreshing(true);
        }
    }
}
