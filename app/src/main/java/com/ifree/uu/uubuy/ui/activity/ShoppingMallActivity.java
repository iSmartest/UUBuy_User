package com.ifree.uu.uubuy.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.app.MyApplication;
import com.ifree.uu.uubuy.config.BaseUrl;
import com.ifree.uu.uubuy.custom.ZoomOutPageTransformer;
import com.ifree.uu.uubuy.mvp.entity.SecondActivitiesEntity;
import com.ifree.uu.uubuy.mvp.entity.UserInfoEntity;
import com.ifree.uu.uubuy.mvp.presenter.CollectionPresenter;
import com.ifree.uu.uubuy.mvp.presenter.SecondListPresenter;
import com.ifree.uu.uubuy.mvp.view.ProjectView;
import com.ifree.uu.uubuy.ui.adapter.BannerAdapter;
import com.ifree.uu.uubuy.ui.adapter.MarketOrStoreAdapter;
import com.ifree.uu.uubuy.ui.base.BaseActivity;
import com.ifree.uu.uubuy.uitls.GlideImageLoader;
import com.ifree.uu.uubuy.uitls.SPUtil;
import com.ifree.uu.uubuy.uitls.TimeFormatUtils;
import com.ifree.uu.uubuy.uitls.ToastUtils;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.shareboard.ShareBoardConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/25.
 * Description:商场
 */
public class ShoppingMallActivity extends BaseActivity implements View.OnClickListener {
    private SecondListPresenter mSecondListPresenter;
    private CollectionPresenter mCollectionPresenter;
    @BindView(R.id.xr_market_store)
    XRecyclerView xRecyclerView;
    private View headView;
    private int page = 1;
    private MarketOrStoreAdapter mAdapter;
    private List<SecondActivitiesEntity.DataBean.BandCommodityList> mList = new ArrayList<>();
    private String fristActivitiesId;
    private String fristActivitiesType;
    private String fristActivitiesName;
    private String isCollection = "0";
    private String floor = " ";
    private TextView mName;
    private ImageView mPicture;
    private TabLayout tabLayout;
    private String isOver = "0";
    private boolean isFirst = true;
    private ViewPager viewPager;
    private BannerAdapter mBannerAdapter;
    private List<SecondActivitiesEntity.DataBean.MarketListInfo> mMarketList = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_market_store;
    }

    @Override
    protected void initView() {
        hideBack(6);
        setRightText("收藏");
        mCollectionPresenter = new CollectionPresenter(context);
        fristActivitiesId = getIntent().getStringExtra("fristActivitiesId");
        fristActivitiesType = getIntent().getStringExtra("fristActivitiesType");
        mSecondListPresenter = new SecondListPresenter(context);
        GridLayoutManager layoutManager = new GridLayoutManager(context, 2);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xRecyclerView.setLayoutManager(layoutManager);
        xRecyclerView.setRefreshProgressStyle(ProgressStyle.BallPulse);
        xRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallPulse);
        xRecyclerView.setArrowImageView(R.drawable.iconfont_downgrey);
        headView = LayoutInflater.from(context).inflate(R.layout.header_market, null);
        mName = headView.findViewById(R.id.tv_market_name);
        mPicture = headView.findViewById(R.id.tv_market_picture);
        viewPager = headView.findViewById(R.id.viewpager);
        tabLayout = headView.findViewById(R.id.tl_top_indicator);
        headView.findViewById(R.id.tv_market_share).setOnClickListener(this);
        if (headView != null) xRecyclerView.addHeaderView(headView);
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mList.clear();
                mAdapter.notifyDataSetChanged();
                mMarketList.clear();
                mBannerAdapter.notifyDataSetChanged();
                page = 1;
                loadData();
                xRecyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                page++;
                loadData();
                xRecyclerView.loadMoreComplete();
            }
        });
        mBannerAdapter = new BannerAdapter(context,mMarketList);
        viewPager.setAdapter(mBannerAdapter);
        viewPager.setOffscreenPageLimit(8);
        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        mAdapter = new MarketOrStoreAdapter(context, mList);
        xRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void loadData() {
        mSecondListPresenter.onCreate();
        mSecondListPresenter.attachView(mSecondListView);
        mSecondListPresenter.getSearchSecondListInfo(floor, fristActivitiesId, page, uid, fristActivitiesType, "", "加载中...");
    }

    private ProjectView<SecondActivitiesEntity> mSecondListView = new ProjectView<SecondActivitiesEntity>() {
        @Override
        public void onSuccess(SecondActivitiesEntity mSecondListEntity) {
            if (mSecondListEntity.getResultCode().equals("1")) {
                ToastUtils.makeText(context, mSecondListEntity.getMsg());
                return;
            }
            List<SecondActivitiesEntity.DataBean.BandCommodityList> secondActivitiesList = mSecondListEntity.getData().getBandCommodityList();
            if (secondActivitiesList != null && !secondActivitiesList.isEmpty()) {
                mList.addAll(secondActivitiesList);
                mAdapter.notifyDataSetChanged();
                if (secondActivitiesList.size() < 10) {
                    xRecyclerView.setNoMore(true);
                }
            } else {
                xRecyclerView.setNoMore(true);
            }

            if (!(mSecondListEntity.getData().getMarketInfo().getStartFloor()+"").equals("null")&&!mSecondListEntity.getData().getMarketInfo().getStartFloor().isEmpty() && mSecondListEntity.getData().getMarketInfo().getStartFloor() != null
                &&!(mSecondListEntity.getData().getMarketInfo().getEndFloor() + "").equals("null")&& !mSecondListEntity.getData().getMarketInfo().getEndFloor().isEmpty() && mSecondListEntity.getData().getMarketInfo().getEndFloor() != null) {
                int startFloor = Integer.valueOf(mSecondListEntity.getData().getMarketInfo().getStartFloor());
                int endFloor = Integer.valueOf(mSecondListEntity.getData().getMarketInfo().getEndFloor());
                tabLayout.setVisibility(View.VISIBLE);
                initTabLayoutTitle(startFloor, endFloor);
            } else {
                tabLayout.setVisibility(View.GONE);
            }

            fristActivitiesName = mSecondListEntity.getData().getMarketInfo().getMarketName();
            setTitleText(fristActivitiesName);
            isCollection = mSecondListEntity.getData().getMarketInfo().getIsCollection();
            if (isCollection.equals("0")) {
                setRightText("收藏");
            } else {
                setRightText("取消收藏");
            }

            List<SecondActivitiesEntity.DataBean.MarketListInfo> marketListInfo = mSecondListEntity.getData().getMarketListInfo();
            if (marketListInfo != null && !marketListInfo.isEmpty()){
                mMarketList.addAll(marketListInfo);
                mBannerAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onError(String result) {
            ToastUtils.makeText(context, result);
            if (page == 1) {
                xRecyclerView.refreshComplete();
            } else {
                xRecyclerView.loadMoreComplete();
            }
        }
    };

    private void initTabLayoutTitle(int start, int end) {
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        if (isFirst) {//第一次加入值
            final List<String> list = new ArrayList<>();
            for (int i = start; i <= end; i++) {
                if (i == 0) {
                    continue;
                }
                list.add(i + "");
            }

            for (int i = 0; i < list.size(); i++) {
                tabLayout.addTab(tabLayout.newTab().setText("第" + list.get(i).replace('-', 'B') + "层"), i);
            }
            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    page = 1;
                    mList.clear();
                    mAdapter.notifyDataSetChanged();
                    floor = list.get(tab.getPosition());
                    loadData();
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });
            isFirst = false;
        }
    }


    @OnClick({R.id.tv_base_rightText})
    public void onViewClicked() {
        if (TextUtils.isEmpty(uid)) {
            ToastUtils.makeText(context, "用户未登录，请登录");
            return;
        }
        mCollectionPresenter.onCreate();
        mCollectionPresenter.attachView(mCollectionView);
        if (isCollection.equals("0")) {
            mCollectionPresenter.getSubmitIsCollection(uid, fristActivitiesId, "0", "1", "处理中...");
        } else {
            mCollectionPresenter.getSubmitIsCollection(uid, fristActivitiesId, "0", "0", "处理中...");
        }
    }

    private ProjectView<UserInfoEntity> mCollectionView = new ProjectView<UserInfoEntity>() {
        @Override
        public void onSuccess(UserInfoEntity mUserInfoEntity) {
            if (mUserInfoEntity.getResultCode().equals("1")) {
                ToastUtils.makeText(context, mUserInfoEntity.getMsg());
                return;
            }
            ToastUtils.makeText(context, mUserInfoEntity.getMsg());
            if (isCollection.equals("0")) {
                setRightText("取消收藏");
                isCollection = "1";
            } else {
                setRightText("收藏");
                isCollection = "0";
            }
        }

        @Override
        public void onError(String result) {
            ToastUtils.makeText(context, result);
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_market_share:
                ShareBoardConfig config = new ShareBoardConfig();
                ShareAction mShareAction = new ShareAction(this);
                config.setMenuItemBackgroundShape(ShareBoardConfig.BG_SHAPE_CIRCULAR);// 圆角背景
                config.setCancelButtonVisibility(false);
                config.setTitleVisibility(true);
                config.setTitleText("— 分享到 —");
                mShareAction.setDisplayList(SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE)
                        .withTitle("您的好友"+SPUtil.getString(context,"nickName") +"邀请您加入【UU购】")
                        .withText("赶快下载体验【UU购】APP！")
                        .withMedia(new UMImage(context, R.mipmap.app_icon))
                        .withTargetUrl(BaseUrl.ShARE_URL)
                        .setCallback(umShareListener)
                        .open(config);
                break;
        }
    }

    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Log.d("plat", "platform" + platform);
            ToastUtils.makeText(context, "分享成功啦");
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            ToastUtils.makeText(context, "分享失败啦！");
            if (t != null) {
                Log.d("throw", "throw:" + t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            ToastUtils.makeText(context, "分享已取消了");
        }
    };

}
