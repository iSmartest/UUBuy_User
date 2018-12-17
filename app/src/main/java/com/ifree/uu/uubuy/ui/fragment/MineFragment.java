package com.ifree.uu.uubuy.ui.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.app.MyApplication;
import com.ifree.uu.uubuy.custom.rounded.RoundedImageView;
import com.ifree.uu.uubuy.mvp.entity.MineEntity;
import com.ifree.uu.uubuy.mvp.presenter.MineInfoPresenter;
import com.ifree.uu.uubuy.mvp.view.ProjectView;
import com.ifree.uu.uubuy.ui.activity.CouponCenterActivity;
import com.ifree.uu.uubuy.ui.activity.LoginActivity;
import com.ifree.uu.uubuy.ui.activity.MyCouponActivity;
import com.ifree.uu.uubuy.ui.activity.MyFootprintActivity;
import com.ifree.uu.uubuy.ui.activity.MyPersonalInformationActivity;
import com.ifree.uu.uubuy.ui.activity.PlayVIPActivity;
import com.ifree.uu.uubuy.ui.activity.ReceivePrizeCenterActivity;
import com.ifree.uu.uubuy.ui.adapter.MineAdapter;
import com.ifree.uu.uubuy.ui.base.BaseFragment;
import com.ifree.uu.uubuy.uitls.GlideImageLoader;
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
 * Created by 2018/8/17.
 * Description: 我的
 */
public class MineFragment extends BaseFragment implements View.OnClickListener {
    private MineInfoPresenter mineInfoPresenter;
    @BindView(R.id.xr_mine)
    XRecyclerView xRecyclerView;
    private RoundedImageView mIcon;
    private LinearLayout mUserLogin;
    private TextView mUserName, mMedalType, mGrownValue, mConpouNum, mIntegralNum, mGoLogin;
    private ProgressBar grown_bar;
    private View headView;
    int page = 1;
    private MineAdapter mAdapter;
    private List<MineEntity.DataBean.RecommendactivitiesList> mList = new ArrayList<>();
    private String pop;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //注册广播
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.ifree.uu.mine.changed");
        getActivity().registerReceiver(mAllBroad, intentFilter);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView() {
        mineInfoPresenter = new MineInfoPresenter(context);
        pop = SPUtil.getString(context,"pop");
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xRecyclerView.setLayoutManager(layoutManager);
        xRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        xRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        xRecyclerView.setArrowImageView(R.drawable.iconfont_downgrey);
        headView = LayoutInflater.from(context).inflate(R.layout.header_mine, null);
        mIcon = headView.findViewById(R.id.my_icon_img);
        headView.findViewById(R.id.linear_mine_coupon).setOnClickListener(this);
        headView.findViewById(R.id.linear_mine_integral).setOnClickListener(this);
        mUserName = headView.findViewById(R.id.tv_mine_user_name);
        mMedalType = headView.findViewById(R.id.tv_mine_medal_type);
        grown_bar = headView.findViewById(R.id.grown_bar);
        mGrownValue = headView.findViewById(R.id.tv_mine_grown_value);
        mConpouNum = headView.findViewById(R.id.tv_mine_coupon_num);
        mIntegralNum = headView.findViewById(R.id.tv_mine_integral);
        mUserLogin = headView.findViewById(R.id.ll_user_login);
        headView.findViewById(R.id.tv_mine_play_vip).setOnClickListener(this);
        headView.findViewById(R.id.tv_mine_footprint).setOnClickListener(this);
        headView.findViewById(R.id.tv_mine_get_coupon_center).setOnClickListener(this);
        headView.findViewById(R.id.tv_mine_receive_prize).setOnClickListener(this);
        if (pop.equals("1")){
            headView.findViewById(R.id.tv_mine_receive_prize).setVisibility(View.VISIBLE);
        }
        mGoLogin = headView.findViewById(R.id.tv_go_login);
        headView.findViewById(R.id.ll_mine_user_info).setOnClickListener(this);
        if (headView != null) xRecyclerView.addHeaderView(headView);
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                initData();
            }

            @Override
            public void onLoadMore() {
                page++;
                initData();
            }
        });
        mAdapter = new MineAdapter(context, mList);
        xRecyclerView.setAdapter(mAdapter);

    }

    @Override
    protected void initData() {
        if (SPUtil.getUid(context).isEmpty()) {
            mUserLogin.setVisibility(View.GONE);
            mGoLogin.setVisibility(View.VISIBLE);
        } else {
            mUserLogin.setVisibility(View.VISIBLE);
            mGoLogin.setVisibility(View.GONE);
        }
        mineInfoPresenter.onCreate();
        mineInfoPresenter.attachView(mMineInfoView);
        mineInfoPresenter.getSearchMineInfo(SPUtil.getLongitude(context), SPUtil.getLatitude(context), SPUtil.getTownAdCode(context), page, SPUtil.getUid(context), "加载中...");
    }

    private ProjectView<MineEntity> mMineInfoView = new ProjectView<MineEntity>() {
        @Override
        public void onSuccess(MineEntity mMineEntity) {
            if (page == 1) {
                xRecyclerView.refreshComplete();
                mList.clear();
                mAdapter.notifyDataSetChanged();
            } else {
                xRecyclerView.loadMoreComplete();
            }
            if (mMineEntity.getResultCode().equals("1")) {
                ToastUtils.makeText(context, mMineEntity.getMsg());
                return;
            }
            mUserName.setText(mMineEntity.getData().getUserName());
            SPUtil.putString(context, "userName", mMineEntity.getData().getUserName());
            SPUtil.putString(context, "isPhone", mMineEntity.getData().getUserBindPhone());
            SPUtil.putString(context, "userAddress", mMineEntity.getData().getUserAddress());
            SPUtil.putString(context, "userBirthday", mMineEntity.getData().getUserBirthday());
            SPUtil.putString(context, "userIcon", mMineEntity.getData().getUserIcon());
            SPUtil.putString(context, "userIdCard", mMineEntity.getData().getUserIdcard());
            SPUtil.putString(context, "userPhone", mMineEntity.getData().getUserPhone());
            SPUtil.putString(context, "userSex", mMineEntity.getData().getUserSex());
            mMedalType.setText("银牌");
            mConpouNum.setText(mMineEntity.getData().getUserCoupon() + "张");
            mGrownValue.setText(mMineEntity.getData().getUserGrowthValue() + "/" + mMineEntity.getData().getMedalTotalValue());
            if (mMineEntity.getData().getMedalTotalValue().isEmpty()) {
                grown_bar.setMax(0);
            } else {
                grown_bar.setMax(Integer.parseInt(mMineEntity.getData().getMedalTotalValue()));
            }
            if (mMineEntity.getData().getUserGrowthValue().isEmpty()) {
                grown_bar.setProgress(0);
            } else {
                grown_bar.setProgress(Integer.parseInt(mMineEntity.getData().getUserGrowthValue()));
            }
            GlideImageLoader.headerImageLoader(context, mMineEntity.getData().getUserIcon(), mIcon);
            mIntegralNum.setText(mMineEntity.getData().getUserIntegral() + "分");
            List<MineEntity.DataBean.RecommendactivitiesList> recommendActivitiesLists = mMineEntity.getData().getRecommendactivitiesList();
            if (recommendActivitiesLists != null && !recommendActivitiesLists.isEmpty()) {
                mList.addAll(recommendActivitiesLists);
                mAdapter.notifyDataSetChanged();
                if (recommendActivitiesLists.size() < 10) {
                    xRecyclerView.setNoMore(true);
                }
            } else {
                xRecyclerView.setNoMore(true);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_mine_user_info://个人信息，用户登录可进入否则进入登录页面
                if (TextUtils.isEmpty(SPUtil.getUid(context))) {
                    MyApplication.openActivity(context, LoginActivity.class);
                } else {
                    MyApplication.openActivity(context, MyPersonalInformationActivity.class);
                }
                break;
            case R.id.linear_mine_coupon://我的优惠券，用户登录可进入否则进入登录页面
                if (TextUtils.isEmpty(SPUtil.getUid(context))) {
                    ToastUtils.makeText(context, "请先登录！");
                } else {
                    MyApplication.openActivity(context, MyCouponActivity.class);
                }
                break;
            case R.id.linear_mine_integral://积分，用户登录可进入否则进入登录页面(暂未开通)
//                if (TextUtils.isEmpty(SPUtil.getUid(context))) {
//                    ToastUtils.makeText(context, "请先登录！");
//                } else {
//                    ToastUtils.makeText(context, "即将开通，敬请期待");
//                }
                break;
            case R.id.tv_mine_play_vip://玩转会员，用户登录后可进入否则就进入登录页面
                if (TextUtils.isEmpty(SPUtil.getUid(context))) {
                    ToastUtils.makeText(context, "请先登录！");
                } else {
                    MyApplication.openActivity(context, PlayVIPActivity.class);
                }
                break;
            case R.id.tv_mine_footprint://我的足迹，用户登录后可进入否则就进入登录页面
                if (TextUtils.isEmpty(SPUtil.getUid(context))) {
                    ToastUtils.makeText(context, "请先登录！");
                } else {
                    MyApplication.openActivity(context, MyFootprintActivity.class);
                }
                break;
            case R.id.tv_mine_get_coupon_center://领券中心，用户登录可进入否则进入登录页面
                if (TextUtils.isEmpty(SPUtil.getUid(context))) {
                    ToastUtils.makeText(context, "请先登录！");
                } else {
                    MyApplication.openActivity(context, CouponCenterActivity.class);
                }
                break;
            case R.id.tv_mine_receive_prize:
                if (TextUtils.isEmpty(SPUtil.getUid(context))) {
                    ToastUtils.makeText(context, "请先登录！");
                } else {
                    MyApplication.openActivity(context, ReceivePrizeCenterActivity.class);
                }
                break;
        }
    }

    private BroadcastReceiver mAllBroad = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, final Intent intent) {
            //接到广播通知后刷新数据源
            initData();
        }
    };
}
