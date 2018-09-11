package com.ifree.uu.uubuy.ui.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.config.CommonLog;
import com.ifree.uu.uubuy.dialog.ProgressDialog;
import com.ifree.uu.uubuy.listener.LoginCompleteListener;
import com.ifree.uu.uubuy.service.entity.UserInfoEntity;
import com.ifree.uu.uubuy.service.presenter.ThirdLoginPresenter;
import com.ifree.uu.uubuy.service.view.UserInfoView;
import com.ifree.uu.uubuy.ui.base.BaseFragment;
import com.ifree.uu.uubuy.uitls.SPUtil;
import com.ifree.uu.uubuy.uitls.ToastUtils;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/23.
 * Description:
 */
public class ThirdFragment extends BaseFragment {
    private ThirdLoginPresenter mThirdLoginPresenter;
    @BindView(R.id.iv_wx_login)
    ImageView mWXLogin;
    @BindView(R.id.iv_qq_login)
    ImageView mQQLogin;
    private Dialog progressDlg;
    private String type = null;
    private String nickName = null, userIcon = null, thirdUid = null,phoneNum = null;
    private UMShareAPI mShareAPI;
    private SHARE_MEDIA platform;
    private LoginCompleteListener completeListener;

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        completeListener = (LoginCompleteListener) getActivity();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_third_login;
    }

    @Override
    protected void initView() {
        mShareAPI = UMShareAPI.get(getActivity());
        mThirdLoginPresenter = new ThirdLoginPresenter(context);
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.iv_wx_login,R.id.iv_qq_login})
    public void onClickView(View view){
        switch (view.getId()){
            case R.id.iv_wx_login:
                type = "0";
                if (!isWeixinAvilible(context)) {
                    ToastUtils.makeText(context, "请安装微信客户端");
                    return;
                }
                progressDlg = ProgressDialog.createLoadingDialog(context, "登录跳转中...");
                progressDlg.show();
                ToastUtils.makeText(context, "正在跳转微信登录,请稍后...");
                mShareAPI.isInstall(getActivity(), SHARE_MEDIA.WEIXIN);
                mShareAPI.getPlatformInfo(getActivity(), SHARE_MEDIA.WEIXIN, umAuthListener);
                break;
            case R.id.iv_qq_login:
                type = "1";
                progressDlg = ProgressDialog.createLoadingDialog(context, "登录跳转中...");
                progressDlg.show();
                ToastUtils.makeText(context, "正在跳转QQ登录,请稍后...");
                mShareAPI.isInstall(getActivity(), SHARE_MEDIA.QQ);
                mShareAPI.getPlatformInfo(getActivity(), SHARE_MEDIA.QQ, umAuthListener);
                break;
        }
    }
    private UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
            if (SHARE_MEDIA.QQ.equals(share_media)) {
                nickName = map.get("screen_name");//昵称
                userIcon = map.get("profile_image_url");//头像
                thirdUid = map.get("openid");//第三方平台id
            } else if (SHARE_MEDIA.WEIXIN.equals(share_media)) {
                CommonLog.i("onComplete: " + map);
                nickName = map.get("screen_name");//昵称
                userIcon = map.get("profile_image_url");//头像
                thirdUid = map.get("openid");//第三方平台id
                phoneNum = map.get("phoneNum");
                Log.i("TAG", "thirdLogin: " + nickName);
                Log.i("ssss", "onComplete: " + "授权成功了");
            }
            thirdLogin(thirdUid, nickName,userIcon);
        }

        @Override
        public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
            Log.i("TAG", "onError: " + "授权失败");
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media, int i) {
            Log.i("TAG", "onCancel: " + "授权取消");
        }
    };

    private void thirdLogin(String thirdUid, String nickName, String userIcon) {
        mThirdLoginPresenter.onCreate();
        mThirdLoginPresenter.attachView(mThirdLoginView);
        mThirdLoginPresenter.getSearchThirdLogin(thirdUid,nickName,userIcon,type,"登录中...");
    }

    private UserInfoView mThirdLoginView = new UserInfoView() {
        @Override
        public void onSuccess(UserInfoEntity mUserInfoEntity) {
            if (mUserInfoEntity.getResultCode().equals("1")){
                ToastUtils.makeText(context,mUserInfoEntity.getMsg());
                return;
            }
            SPUtil.putString(context,"uid",mUserInfoEntity.getData().getUid());
            SPUtil.putString(context,"isPhone",mUserInfoEntity.getData().getIsPhone());
            SPUtil.putString(context,"nickName",mUserInfoEntity.getData().getNickName());
            SPUtil.putString(context,"userIcon",mUserInfoEntity.getData().getUserIcon());
            completeListener.SendMessageValue();
        }

        @Override
        public void onError(String result) {
            ToastUtils.makeText(context,result);
        }
    };

    /**
     * 判断 用户是否安装微信客户端
     */
    public static boolean isWeixinAvilible(Context context) {
        final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.tencent.mm")) {
                    return true;
                }
            }
        }
        return false;
    }
}
