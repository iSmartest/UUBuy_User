package com.ifree.uu.uubuy.mvp.persenter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.gson.Gson;
import com.ifree.uu.uubuy.dialog.ProgressDialog;
import com.ifree.uu.uubuy.mvp.RequestResult;
import com.ifree.uu.uubuy.mvp.manager.DataManager;
import com.ifree.uu.uubuy.mvp.modle.UserInfoBean;
import com.ifree.uu.uubuy.mvp.view.ProjectView;
import com.ifree.uu.uubuy.mvp.view.View;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/22.
 * Description:
 */
public class SignInPresenter implements Presenter {
    private DataManager manager;
    private CompositeSubscription mCompositeSubscription;
    private Context mContext;
    private UserInfoBean mUserInfoBean;
    private ProjectView mUserInfoView;

    public SignInPresenter(Context mContext){
        this.mContext = mContext;
    }
    @Override
    public void onCreate() {
        manager = new DataManager(mContext);
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {
        if (mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void attachView(View view) {
        mUserInfoView = (ProjectView) view;
    }

    @Override
    public void attachIncomingIntent(Intent intent) {

    }

    public void getSearchSignIns(String uid, String mContent) {
        final Dialog dialog = ProgressDialog.createLoadingDialog(mContext, mContent);
        dialog.show();
        mCompositeSubscription.add(manager.getSearchSignIns(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserInfoBean>() {
                    @Override
                    public void onCompleted() {
                        dialog.dismiss();
                        if (mUserInfoBean != null) {
                            mUserInfoView.onSuccess(mUserInfoBean);
                            Log.i("TAG", "onCompleted: " + new Gson().toJson(mUserInfoBean));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        dialog.dismiss();
                        mUserInfoView.onError(RequestResult.getError(e));
                    }

                    @Override
                    public void onNext(UserInfoBean userInfoBean) {
                        mUserInfoBean = userInfoBean;
                    }
                }));

    }

}
