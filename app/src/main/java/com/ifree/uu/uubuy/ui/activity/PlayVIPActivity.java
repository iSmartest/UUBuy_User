package com.ifree.uu.uubuy.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.app.MyApplication;
import com.ifree.uu.uubuy.custom.rounded.RoundedImageView;
import com.ifree.uu.uubuy.service.entity.GroupEntity;
import com.ifree.uu.uubuy.service.entity.UserInfoEntity;
import com.ifree.uu.uubuy.service.presenter.GroupInfoPresenter;
import com.ifree.uu.uubuy.service.presenter.SignInPresenter;
import com.ifree.uu.uubuy.service.view.GroupInfoView;
import com.ifree.uu.uubuy.service.view.UserInfoView;
import com.ifree.uu.uubuy.ui.adapter.PlayVIPAdapter;
import com.ifree.uu.uubuy.ui.base.BaseActivity;
import com.ifree.uu.uubuy.uitls.GlideImageLoader;
import com.ifree.uu.uubuy.uitls.SPUtil;
import com.ifree.uu.uubuy.uitls.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/22.
 * Description:
 */
public class PlayVIPActivity extends BaseActivity {
    private GroupInfoPresenter mGroupInfoPresenter;
    private SignInPresenter mSignInPresenter;
    @BindView(R.id.tv_sign_in)
    TextView mSignIn;
    @BindView(R.id.ri_vip_icon_img)
    RoundedImageView mIcon;
    @BindView(R.id.tv_play_vip_user_name)
    TextView mName;
    @BindView(R.id.tv_vip_grown_value)
    TextView mValue;
    @BindView(R.id.rv_sign)
    RecyclerView recyclerView;
    @BindView(R.id.tv_go_perfect_information)
    TextView mGoPerfectInformation;
    @BindView(R.id.tv_go_share)
    TextView mGoShare;
    private PlayVIPAdapter mAdapter;
    private List<GroupEntity.DataBean.SignInList> mList = new ArrayList<>();
    private String uid;
    private String isUserInfo = "0";
    private String isShare = "0";
    private String isSignIn = "0";
    private String userName,userIcon;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_play_vip;
    }


    @Override
    protected void initView() {
        hideBack(7);
        setTitleText("玩转会员");
        userName = SPUtil.getString(context,"userName");
        userIcon = SPUtil.getString(context,"userIcon");
        mGroupInfoPresenter = new GroupInfoPresenter(context);
        mSignInPresenter = new SignInPresenter(context);
        uid = SPUtil.getString(context,"uid");
        mName.setText(userName);
        GlideImageLoader.imageLoader(context,userIcon,mIcon);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, OrientationHelper.HORIZONTAL,false));
        mAdapter = new PlayVIPAdapter(context,mList);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void loadData() {
        mGroupInfoPresenter.onCreate();
        mGroupInfoPresenter.attachView(mGroupInfoView);
        mGroupInfoPresenter.getSearchGroupInfos(uid,"加载中...");
    }

    private GroupInfoView mGroupInfoView = new GroupInfoView() {
        @Override
        public void onSuccess(GroupEntity mGroupEntity) {
            if (mGroupEntity.getResultCode().equals("1")){
                ToastUtils.makeText(context,mGroupEntity.getMsg());
                return;
            }
            isUserInfo = mGroupEntity.getData().getIsUserInfo();
            isShare = mGroupEntity.getData().getShare();
            if (mGroupEntity.getData().getIsUserInfo().equals("1")){
                mGoPerfectInformation.setBackgroundResource(R.drawable.shape_city_location_background);
                mGoPerfectInformation.setText("已完成");
            }else {
                mGoPerfectInformation.setBackgroundResource(R.drawable.shape_red_background);
                mGoPerfectInformation.setText("做任务");
            }

            List<GroupEntity.DataBean.SignInList> signInList = mGroupEntity.getData().getSignInList();
            if (signInList != null && !signInList.isEmpty()){
                isSignIn = signInList.get(3).getIsSignIn();
                mList.addAll(signInList);
                mAdapter.notifyDataSetChanged();
            }
            if (isSignIn.equals("1")){
                mSignIn.setBackgroundResource(R.drawable.shape_city_location_background);
                mSignIn.setText("已签到");
            }else {
                mGoPerfectInformation.setBackgroundResource(R.drawable.shape_red_background);
                mGoPerfectInformation.setText("签到");
            }
            if (isShare.equals("1")){
                mGoShare.setBackgroundResource(R.drawable.shape_city_location_background);
                mGoShare.setText("已分享");
            }else {
                mGoShare.setBackgroundResource(R.drawable.shape_red_background);
                mGoShare.setText("分享");
            }
            mValue.setText("成长值："+mGroupEntity.getData().getUserGrowthValue());
        }

        @Override
        public void onError(String result) {
            ToastUtils.makeText(context,result);
        }
    };


    @OnClick({R.id.tv_go_perfect_information, R.id.tv_go_share, R.id.iv_base_question,R.id.tv_sign_in})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_go_perfect_information:
                if (isUserInfo.equals("0")){
                    MyApplication.openActivity(context,MyPersonalInformationActivity.class);
                }else {
                    ToastUtils.makeText(context,"任务已经完成了，看看其他的吧！");
                }
                break;
            case R.id.tv_go_share:
                if (isShare.equals("0")){
                    ToastUtils.makeText(context,"跳转三方分享");
                }else {
                    ToastUtils.makeText(context,"今天任务已经完成了，看看其他的吧！");
                }
                break;
            case R.id.iv_base_question:
                ToastUtils.makeText(context,"玩转会员规则");
                break;
            case R.id.tv_sign_in:
                if (uid.isEmpty()){
                    ToastUtils.makeText(context,"登录之后才能签到哦！");
                }
                if (isSignIn.equals("0")){
                    goSignIn();
                }else {
                    ToastUtils.makeText(context,"你已经签到过啦");
                }
                break;
        }
    }

    private void goSignIn() {
        mSignInPresenter.onCreate();
        mSignInPresenter.attachView(mUserInfoView);
        mSignInPresenter.getSearchSignIns(uid,"签到中...");
    }

    private UserInfoView mUserInfoView = new UserInfoView() {
        @Override
        public void onSuccess(UserInfoEntity mUserInfoEntity) {
            if (mUserInfoEntity.getResultCode().equals("1")){
                ToastUtils.makeText(context,mUserInfoEntity.getMsg());
                return;
            }
            isSignIn = "1";
            ToastUtils.makeText(context,mUserInfoEntity.getMsg());
            mSignIn.setBackgroundResource(R.drawable.shape_city_location_background);
            mSignIn.setText("已签到");
            loadData();
        }

        @Override
        public void onError(String result) {
            ToastUtils.makeText(context,result);
        }
    };
}
