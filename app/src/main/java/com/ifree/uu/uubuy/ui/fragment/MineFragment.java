package com.ifree.uu.uubuy.ui.fragment;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.app.MyApplication;
import com.ifree.uu.uubuy.custom.rounded.RoundedImageView;
import com.ifree.uu.uubuy.service.entity.MineEntity;
import com.ifree.uu.uubuy.service.presenter.MineInfoPresenter;
import com.ifree.uu.uubuy.service.view.MineInfoView;
import com.ifree.uu.uubuy.ui.activity.CouponCenterActivity;
import com.ifree.uu.uubuy.ui.activity.LoginActivity;
import com.ifree.uu.uubuy.ui.activity.MyCouponActivity;
import com.ifree.uu.uubuy.ui.activity.MyFootprintActivity;
import com.ifree.uu.uubuy.ui.activity.MyPersonalInformationActivity;
import com.ifree.uu.uubuy.ui.activity.PlayVIPActivity;
import com.ifree.uu.uubuy.ui.adapter.HomeAdapter;
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
 * Description:
 */
public class MineFragment extends BaseFragment implements View.OnClickListener {
    private MineInfoPresenter mineInfoPresenter;
    @BindView(R.id.xr_mine)
    XRecyclerView xRecyclerView;
    private RoundedImageView mIcon;
    private LinearLayout mUserLogin;
    private TextView mUserName, mMedalType, mGrownValue, mConpouNum, mIntegralNum,mGoLogin;
    private ProgressBar grown_bar;
    private View headView;
    int page = 1;
    private MineAdapter mAdapter;
    private List<MineEntity.DataBean.RecommendactivitiesList> mList = new ArrayList<>();
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
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
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xRecyclerView.setLayoutManager(layoutManager);
        xRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        xRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        xRecyclerView.setArrowImageView(R.drawable.iconfont_downgrey);
        headView = LayoutInflater.from(context).inflate(R.layout.header_mine, null);
        mIcon = headView.findViewById(R.id.my_icon_img);
        mIcon.setOnClickListener(this);
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
        mGoLogin = headView.findViewById(R.id.tv_go_login);
        mGoLogin.setOnClickListener(this);
        if (headView != null) xRecyclerView.addHeaderView(headView);
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                mList.clear();
                mAdapter.notifyDataSetChanged();
                initData();
                xRecyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                page++;
                initData();
                xRecyclerView.loadMoreComplete();
                mAdapter.notifyDataSetChanged();
                xRecyclerView.setNoMore(true);
            }
        });

        mAdapter = new MineAdapter(context, mList);
        xRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        if (uid.isEmpty()){
            mUserLogin.setVisibility(View.GONE);
            mGoLogin.setVisibility(View.VISIBLE);
        }else {
            mUserLogin.setVisibility(View.VISIBLE);
            mGoLogin.setVisibility(View.GONE);
        }
        mineInfoPresenter.onCreate();
        mineInfoPresenter.attachView(mMineInfoView);
        mineInfoPresenter.getSearchMineInfo(longitude, latitude, townAdCode, page, uid, "加载中...");
    }

    private MineInfoView mMineInfoView = new MineInfoView() {
        @Override
        public void onSuccess(MineEntity mMineEntity) {

            if (mMineEntity.getResultCode().equals("1")) {
                ToastUtils.makeText(context, mMineEntity.getMsg());
                return;
            }
            mUserName.setText(mMineEntity.getData().getUserName());
            SPUtil.putString(context,"userName",mMineEntity.getData().getUserName());
            SPUtil.putString(context,"isPhone",mMineEntity.getData().getUserBindPhone());
            SPUtil.putString(context,"userAddress",mMineEntity.getData().getUserAddress());
            SPUtil.putString(context,"userBirthday",mMineEntity.getData().getUserBirthday());
            SPUtil.putString(context,"userIcon",mMineEntity.getData().getUserIcon());
            SPUtil.putString(context,"userIdCard",mMineEntity.getData().getUserIdcard());
            SPUtil.putString(context,"userPhone",mMineEntity.getData().getUserPhone());
            SPUtil.putString(context,"userSex",mMineEntity.getData().getUserSex());
            mConpouNum.setText(mMineEntity.getData().getUserCoupon() + "张");
            mGrownValue.setText(mMineEntity.getData().getUserGrowthValue() + "/" + mMineEntity.getData().getMedalTotalValue());
            grown_bar.setMax(Integer.parseInt(mMineEntity.getData().getMedalTotalValue()));
            grown_bar.setProgress(Integer.parseInt(mMineEntity.getData().getUserGrowthValue()));
            GlideImageLoader.imageLoader(context,mMineEntity.getData().getUserIcon(),mIcon);
            mIntegralNum.setText(mMineEntity.getData().getUserIntegral() + "分");
            List<MineEntity.DataBean.RecommendactivitiesList> recommendactivitiesLists = mMineEntity.getData().getRecommendactivitiesList();
            if (recommendactivitiesLists != null && !recommendactivitiesLists.isEmpty()) {
                mList.addAll(recommendactivitiesLists);
                mAdapter.notifyDataSetChanged();
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
            case R.id.tv_go_login:
                MyApplication.openActivity(context, LoginActivity.class);
                break;
            case R.id.my_icon_img:
                MyApplication.openActivity(context, MyPersonalInformationActivity.class);
                break;
            case R.id.linear_mine_coupon:
                MyApplication.openActivity(context, MyCouponActivity.class);
                break;
            case R.id.linear_mine_integral:
                ToastUtils.makeText(context, "开发中...");
                break;
            case R.id.tv_mine_play_vip:
                MyApplication.openActivity(context, PlayVIPActivity.class);
                break;
            case R.id.tv_mine_footprint:
                MyApplication.openActivity(context, MyFootprintActivity.class);
                break;
            case R.id.tv_mine_get_coupon_center:
                MyApplication.openActivity(context, CouponCenterActivity.class);
                break;
        }
    }
    private BroadcastReceiver mAllBroad = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, final Intent intent) {
            //接到广播通知后刷新数据源
            uid = SPUtil.getString(context,"uid");
            initData();
        }
    };
}
