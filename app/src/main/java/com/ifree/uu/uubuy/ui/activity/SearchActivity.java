package com.ifree.uu.uubuy.ui.activity;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import com.hjq.toast.ToastUtils;
import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.common.CommonActivity;
import com.ifree.uu.uubuy.mvp.modle.HotKeyWordBean;
import com.ifree.uu.uubuy.mvp.persenter.HotKeywordPresenter;
import com.ifree.uu.uubuy.mvp.persenter.SearchBean;
import com.ifree.uu.uubuy.mvp.persenter.SearchPresenter;
import com.ifree.uu.uubuy.mvp.view.ProjectView;
import com.ifree.uu.uubuy.ui.adapter.FlowTagAdapter;
import com.ifree.uu.uubuy.ui.adapter.MallStoreAdapter;
import com.ifree.uu.uubuy.ui.adapter.SearchAdapter;
import com.ifree.uu.uubuy.utils.SPUtil;
import com.ifree.uu.uubuy.widget.flowTagLayout.FlowTagLayout;
import com.ifree.uu.uubuy.widget.flowTagLayout.OnTagClickListener;
import com.ifree.uu.uubuy.widget.flowTagLayout.OnTagSelectListener;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2018/9/19 0019
 * Description: 搜索页面
 */
public class SearchActivity extends CommonActivity{
    private SearchPresenter mSearchPresenter;
    private HotKeywordPresenter mHotKeywordPresenter;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.edt_a_key_search)
    EditText edtKeyword;
    @BindView(R.id.tf_flavor)
    FlowTagLayout mFlavor;
    @BindView(R.id.ll_hot_word)
    LinearLayout ll_hot_word;
    @BindView(R.id.tl_hot_top_indicator)
    TabLayout tabLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    private List<SearchBean.DataBean.ActivitiesList> mList = new ArrayList<>();
    private SearchAdapter mAdapter;
    private String searchType = "0";
    private String keyWord;
    private List<HotKeyWordBean.DataBean.KeywordList> hotSearch = new ArrayList<>();
    private FlowTagAdapter mFlavorAdapter;
    private int page = 1;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected int getTitleBarId() {
        return R.id.tb_search_title;
    }

    @Override
    protected void initView() {
        initTabLayoutTitle();
        mSearchPresenter = new SearchPresenter(context);
        mHotKeywordPresenter = new HotKeywordPresenter(context);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mFlavor.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_NONE);
        mFlavorAdapter = new FlowTagAdapter(context,hotSearch);
        mFlavor.setAdapter(mFlavorAdapter);
        mFlavor.setOnTagClickListener(new OnTagClickListener() {
            @Override
            public void onItemClick(FlowTagLayout parent, View view, int position) {
                keyWord = hotSearch.get(position).getName();
                edtKeyword.setText(keyWord);
                ll_hot_word.setVisibility(View.GONE);
                tabLayout.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.VISIBLE);
            }
        });
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        mAdapter = new SearchAdapter(context);
        recyclerView.setAdapter(mAdapter);
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
//                mAdapter.loadMore(buildItems());
                refreshLayout.finishLoadmore();
            }
        });

        edtKeyword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH){
                    if (TextUtils.isEmpty(edtKeyword.getText().toString().trim())){
                        ToastUtils.show("请输入搜索内容");
                    }else {
                        ll_hot_word.setVisibility(View.GONE);
                        tabLayout.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.VISIBLE);
                        keyWord = edtKeyword.getText().toString().trim();

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
                    tabLayout.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.GONE);
                }
            }
        });

        mFlavor.setOnTagSelectListener(new OnTagSelectListener() {
            @Override
            public void onItemSelect(FlowTagLayout parent, List<Integer> selectedList) {
                if (selectedList != null && selectedList.size() > 0) {
                    StringBuilder sb = new StringBuilder();
                    for (int i : selectedList) {
                        sb.append(parent.getAdapter().getItem(i));
                    }
                    keyWord = sb.toString();
                    edtKeyword.setText(keyWord);
                } else {
                    keyWord = "";
                    edtKeyword.setText(keyWord);
                }
            }
        });
    }

    private void initTabLayoutTitle() {
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
            final List<String> list = new ArrayList<>();
            list.add("商场");
            list.add("店铺");
            list.add("商品");
            for (int i = 0; i < list.size(); i++) {
                tabLayout.addTab(tabLayout.newTab().setText(list.get(i)), i);
            }
            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
//                    page = 1;
//                    mList.clear();
//                    mAdapter.notifyDataSetChanged();
//                    floor = list.get(tab.getPosition());
//                    loadData();
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });

    }


    @Override
    protected void initData() {
        mHotKeywordPresenter.onCreate();
        mHotKeywordPresenter.attachView(mHotKeywordView);
        mHotKeywordPresenter.getSearchHotKeyword("获取中...");
    }

    private void search() {
        mSearchPresenter.onCreate();
        mSearchPresenter.attachView(mSearchView);
        mSearchPresenter.getSearchInfo(SPUtil.getLongitude(context),SPUtil.getLatitude(context),SPUtil.getTownAdCode(context)
                ,page,keyWord,SPUtil.getUid(context),searchType,"搜索中...");
    }


    private ProjectView<HotKeyWordBean> mHotKeywordView = new ProjectView<HotKeyWordBean>() {
        @Override
        public void onSuccess(HotKeyWordBean mHotKeyWordBean) {
            if (mHotKeyWordBean.getData().equals("1")){
                ToastUtils.show(mHotKeyWordBean.getMsg());
                return;
            }
            if (mHotKeyWordBean.getData().getKeywordList() != null && !mHotKeyWordBean.getData().getKeywordList().isEmpty()){
                hotSearch.addAll(mHotKeyWordBean.getData().getKeywordList());
                mFlavorAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onError(String result) {
            ToastUtils.show(result);
        }
    };

    private ProjectView<SearchBean> mSearchView = new ProjectView<SearchBean>() {
        @Override
        public void onSuccess(SearchBean mSearchBean) {
            if (mSearchBean.getResultCode().equals("1")){
                ToastUtils.show(mSearchBean.getMsg());
                return;
            }
        }

        @Override
        public void onError(String result) {
            ToastUtils.show(result);
        }
    };


    @OnClick({R.id.tv_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_search:
                keyWord = edtKeyword.getText().toString().trim();
                if (keyWord.isEmpty()){
                    ToastUtils.show("请输入搜索内容");
                    return;
                }
                ll_hot_word.setVisibility(View.GONE);
                tabLayout.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.VISIBLE);
                break;
        }
    }
}
