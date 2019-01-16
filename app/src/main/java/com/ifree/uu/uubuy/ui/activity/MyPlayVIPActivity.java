package com.ifree.uu.uubuy.ui.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.hjq.toast.ToastUtils;
import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.app.MyApplication;
import com.ifree.uu.uubuy.common.CommonActivity;
import com.ifree.uu.uubuy.config.BaseUrl;
import com.ifree.uu.uubuy.mvp.modle.GroupBean;
import com.ifree.uu.uubuy.mvp.modle.UserInfoBean;
import com.ifree.uu.uubuy.mvp.persenter.GroupInfoPresenter;
import com.ifree.uu.uubuy.mvp.persenter.SharePresenter;
import com.ifree.uu.uubuy.mvp.persenter.SignInPresenter;
import com.ifree.uu.uubuy.mvp.view.ProjectView;
import com.ifree.uu.uubuy.ui.adapter.PlayVIPAdapter;
import com.ifree.uu.uubuy.utils.GlideImageLoader;
import com.ifree.uu.uubuy.utils.SPUtil;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.shareboard.ShareBoardConfig;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/22.
 * Description:
 */
public class MyPlayVIPActivity extends CommonActivity {
    private SharePresenter mSharePresenter;
    private GroupInfoPresenter mGroupInfoPresenter;
    private SignInPresenter mSignInPresenter;
    @BindView(R.id.ri_vip_icon_img)
    CircleImageView mIcon;
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
    @BindView(R.id.tv_real_name)
    TextView tvRealName;
    private PlayVIPAdapter mAdapter;
    private List<GroupBean.DataBean.SignInList> mList = new ArrayList<>();
    private String uid;
    private String isUserInfo = "0";
    private String isShare = "0";
    private String isSignIn = "0";
    private String userName,userIcon;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_play_vip;
    }

    @Override
    protected int getTitleBarId() {
        return R.id.tb_play_vip_title;
    }


    @Override
    protected void initView() {
        mSharePresenter = new SharePresenter(context);
        userName = SPUtil.getString(context,"userName");
        userIcon = SPUtil.getString(context,"userIcon");
        mGroupInfoPresenter = new GroupInfoPresenter(context);
        mSignInPresenter = new SignInPresenter(context);
        uid = SPUtil.getString(context,"uid");
        mName.setText(userName);
        GlideImageLoader.headerImageLoader(context,userIcon,mIcon);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, OrientationHelper.HORIZONTAL,false));
        mAdapter = new PlayVIPAdapter(context,mList);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        mList.clear();
        mAdapter.notifyDataSetChanged();
        mGroupInfoPresenter.onCreate();
        mGroupInfoPresenter.attachView(mGroupInfoView);
        mGroupInfoPresenter.getSearchGroupInfos(uid,"加载中...");
    }

    private ProjectView<GroupBean> mGroupInfoView = new ProjectView<GroupBean>() {
        @Override
        public void onSuccess(GroupBean mGroupBean) {
            if (mGroupBean.getResultCode().equals("1")){
                ToastUtils.show(mGroupBean.getMsg());
                return;
            }
            isUserInfo = mGroupBean.getData().getIsUserInfo();
            isShare = mGroupBean.getData().getShare();
            if (mGroupBean.getData().getIsUserInfo().equals("1")){
                mGoPerfectInformation.setBackgroundResource(R.drawable.shape_city_location_background);
                mGoPerfectInformation.setText("已完成");
            }else {
                mGoPerfectInformation.setBackgroundResource(R.drawable.shape_red_background);
                mGoPerfectInformation.setText("做任务");
            }

            List<GroupBean.DataBean.SignInList> signInList = mGroupBean.getData().getSignInList();
            if (signInList != null && !signInList.isEmpty()){
                isSignIn = signInList.get(3).getIsSignIn();
                mList.addAll(signInList);
                mAdapter.notifyDataSetChanged();
            }
            if (isShare.equals("1")){
                mGoShare.setBackgroundResource(R.drawable.shape_city_location_background);
                mGoShare.setText("已分享");
            }else {
                mGoShare.setBackgroundResource(R.drawable.shape_red_background);
                mGoShare.setText("分享");
            }
            mValue.setText("成长值："+mGroupBean.getData().getUserGrowthValue());
        }

        @Override
        public void onError(String result) {
            ToastUtils.show(result);
        }
    };


    @OnClick({R.id.tv_go_perfect_information, R.id.tv_go_share,R.id.tv_real_name})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_go_perfect_information:
                if (isUserInfo.equals("0")){
                    MyApplication.openActivity(context,MyPersonalInformationActivity.class);
                }else {
                    ToastUtils.show("任务已经完成了，看看其他的吧！");
                }
                break;
            case R.id.tv_go_share:
//                if (isShare.equals("0")){
//                    ShareBoardConfig config = new ShareBoardConfig();
//                    ShareAction mShareAction = new ShareAction(this);
//                    config.setMenuItemBackgroundShape(ShareBoardConfig.BG_SHAPE_CIRCULAR);// 圆角背景
//                    config.setCancelButtonVisibility(false);
//                    config.setTitleVisibility(true);
//                    config.setTitleText("— 分享到 —");
//                    mShareAction.setDisplayList(SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE)
//                            .withTitle("您的好友"+userName+"邀请您加入【UU购】")
//                            .withText("赶快下载体验【UU购】APP！")
//                            .withMedia(new UMImage(context, R.mipmap.app_icon))
//                            .withTargetUrl(BaseUrl.ShARE_URL)
//                            .setCallback(umShareListener)
//                            .open(config);
//                }else {
//                    ToastUtils.show("今天任务已经完成了，看看其他的吧！");
//                }
                break;
            case R.id.tv_real_name:
                MyApplication.openActivity(context,RealNameActivity.class);
                break;
        }
    }

    private void goSignIn() {
        mSignInPresenter.onCreate();
        mSignInPresenter.attachView(mUserInfoView);
        mSignInPresenter.getSearchSignIns(uid,"签到中...");
    }

    private ProjectView<UserInfoBean> mUserInfoView = new ProjectView<UserInfoBean>() {
        @Override
        public void onSuccess(UserInfoBean mUserInfoBean) {
            if (mUserInfoBean.getResultCode().equals("1")){
                ToastUtils.show(mUserInfoBean.getMsg());
                return;
            }
            isSignIn = "1";
            ToastUtils.show(mUserInfoBean.getMsg());
            initData();
        }

        @Override
        public void onError(String result) {
            ToastUtils.show(result);
        }
    };

    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Log.d("plat", "platform" + platform);
            if (SPUtil.getUid(context).isEmpty()){
                ToastUtils.show("分享成功啦");
            }else {
                Share();
            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            ToastUtils.show("分享失败啦！");
            if (t != null) {
                Log.d("throw", "throw:" + t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            ToastUtils.show("分享已取消了");
        }
    };

    private void Share() {
        mSharePresenter.onCreate();
        mSharePresenter.attachView(mShareView);
        mSharePresenter.getSearchShare(SPUtil.getUid(context),"分享中...");
    }

    private ProjectView<UserInfoBean> mShareView = new ProjectView<UserInfoBean>() {
        @Override
        public void onSuccess(UserInfoBean mUserInfoBean) {
            if (mUserInfoBean.getResultCode().equals("1")){
                ToastUtils.show(mUserInfoBean.getMsg());
                return;
            }
            ToastUtils.show("分享成功啦");
            initData();
//            mGoShare.setText("已分享");
//            mGoShare.setBackgroundResource(R.drawable.shape_city_location_background);
            Intent intent = new Intent();
            intent.setAction("com.ifree.uu.mine.changed");
            getApplicationContext().sendBroadcast(intent);
        }

        @Override
        public void onError(String result) {
            ToastUtils.show(result);
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(context).onActivityResult(requestCode, resultCode, data);
    }
}
