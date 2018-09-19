package com.ifree.uu.uubuy.ui.activity;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.app.MyApplication;
import com.ifree.uu.uubuy.service.entity.SearchEntity;
import com.ifree.uu.uubuy.service.presenter.SearchPresenter;
import com.ifree.uu.uubuy.service.view.SearchView;
import com.ifree.uu.uubuy.ui.adapter.SearchAdapter;
import com.ifree.uu.uubuy.ui.base.BaseActivity;
import com.ifree.uu.uubuy.ui.fragment.ActivitiesFragment;
import com.ifree.uu.uubuy.ui.fragment.AroundFragment;
import com.ifree.uu.uubuy.ui.fragment.HomeFragment;
import com.ifree.uu.uubuy.ui.fragment.MineFragment;
import com.ifree.uu.uubuy.ui.fragment.OrderFragment;
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
 * Created by 2018/9/19 0019
 * Description:
 */
public class SearchActivity extends BaseActivity implements View.OnClickListener {
    private SearchPresenter mSearchPresenter;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.edt_a_key_search)
    EditText edtKeyword;
    @BindView(R.id.xr_search)
    XRecyclerView xRecyclerView;
    private LinearLayout ll_hot_word,ll_search_style;
    private TextView mAll,mMarket,mStore,mCommodity;
    private int page = 1;
    private View headView;
    private List<SearchEntity.DataBean.ActivitiesList> mList = new ArrayList<>();
    private SearchAdapter mAdapter;
    private String searchType = "0";
    private String keyWord;
    private ColorStateList csl1,csl2;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView() {
        hideBack(8);
        keyWord = getIntent().getStringExtra("keyWord");
        edtKeyword.setText(keyWord);
        Resources resource = context.getResources();
        csl1 = resource.getColorStateList(R.color.text_green);
        csl2 = resource.getColorStateList(R.color.text_main_color);
        mSearchPresenter = new SearchPresenter(context);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xRecyclerView.setLayoutManager(layoutManager);
        xRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        xRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        xRecyclerView.setArrowImageView(R.drawable.iconfont_downgrey);
        headView = LayoutInflater.from(context).inflate(R.layout.header_search,null);
        ll_hot_word = headView.findViewById(R.id.ll_hot_word);
        ll_search_style = headView.findViewById(R.id.ll_search_style);
        mAll = headView.findViewById(R.id.tv_search_all);
        mAll.setOnClickListener(this);
        mMarket = headView.findViewById(R.id.tv_search_market);
        mMarket.setOnClickListener(this);
        mStore = headView.findViewById(R.id.tv_search_store);
        mStore.setOnClickListener(this);
        mCommodity = headView.findViewById(R.id.tv_search_commodity);
        mCommodity.setOnClickListener(this);
        if (headView != null) xRecyclerView.addHeaderView(headView);
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

        mAdapter = new SearchAdapter(context,mList,searchType);
        xRecyclerView.setAdapter(mAdapter);

        edtKeyword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH){
                    if (TextUtils.isEmpty(edtKeyword.getText().toString().trim())){
                        ToastUtils.makeText(context,"请输入搜索内容");
                    }else {
                        ll_hot_word.setVisibility(View.GONE);
                        ll_search_style.setVisibility(View.VISIBLE);
                        keyWord = edtKeyword.getText().toString().trim();
                        xRecyclerView.setRefreshing(true);
                    }
                    return true;
                }
                return false;
            }
        });

        edtKeyword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s.toString())){
                    ll_hot_word.setVisibility(View.VISIBLE);
                    ll_search_style.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    protected void loadData() {
        mSearchPresenter.onCreate();
        mSearchPresenter.attachView(mSearchView);
        mSearchPresenter.getSearchInfo(longitude,latitude,townAdCode,page,keyWord,uid,searchType,"搜索中...");

    }
    private SearchView mSearchView = new SearchView() {
        @Override
        public void onSuccess(SearchEntity mSearchEntity) {
            if (mSearchEntity.getResultCode().equals("1")){
                ToastUtils.makeText(context,mSearchEntity.getMsg());
                return;
            }
            List<SearchEntity.DataBean.ActivitiesList> activitiesLists = mSearchEntity.getData().getActivitiesList();
            if (activitiesLists != null && !activitiesLists.isEmpty()){
                mList.addAll(activitiesLists);
                mAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onError(String result) {
            ToastUtils.makeText(context,result);
        }
    };


    @OnClick({R.id.iv_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_search:
                keyWord = edtKeyword.getText().toString().trim();
                if (keyWord.isEmpty()){
                    ToastUtils.makeText(context,"请输入搜索内容");
                    return;
                }
                ll_hot_word.setVisibility(View.GONE);
                ll_search_style.setVisibility(View.VISIBLE);
                xRecyclerView.setRefreshing(true);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_search_all:
                mAll.setTextColor(csl1);
                mMarket.setTextColor(csl2);
                mStore.setTextColor(csl2);
                mCommodity.setTextColor(csl2);
                searchType ="0";
                xRecyclerView.setRefreshing(true);
                break;
            case R.id.tv_search_market:
                mAll.setTextColor(csl2);
                mMarket.setTextColor(csl1);
                mStore.setTextColor(csl2);
                mCommodity.setTextColor(csl2);
                searchType ="1";
                xRecyclerView.setRefreshing(true);
                break;
            case R.id.tv_search_store:
                mAll.setTextColor(csl2);
                mMarket.setTextColor(csl2);
                mStore.setTextColor(csl1);
                mCommodity.setTextColor(csl2);
                searchType ="2";
                xRecyclerView.setRefreshing(true);
                break;
            case R.id.tv_search_commodity:
                mAll.setTextColor(csl2);
                mMarket.setTextColor(csl2);
                mStore.setTextColor(csl2);
                mCommodity.setTextColor(csl1);
                searchType ="3";
                xRecyclerView.setRefreshing(true);
                break;
        }
    }
}
