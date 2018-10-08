package com.ifree.uu.uubuy.mvp.presenter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.gson.Gson;
import com.ifree.uu.uubuy.dialog.ProgressDialog;
import com.ifree.uu.uubuy.mvp.entity.ActivitiesEntity;
import com.ifree.uu.uubuy.mvp.manager.DataManager;
import com.ifree.uu.uubuy.mvp.view.ProjectView;
import com.ifree.uu.uubuy.mvp.view.View;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import javax.net.ssl.SSLHandshakeException;

import retrofit2.HttpException;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/20.
 * Description:
 */
public class ActivitiesPresenter implements Presenter {

    private DataManager manager;
    private CompositeSubscription mCompositeSubscription;
    private Context mContext;
    private ProjectView mActivitiesView;
    private ActivitiesEntity mActivitiesEntity;

    public ActivitiesPresenter(Context mContext){
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
        mActivitiesView = (ProjectView) view;
    }

    @Override
    public void attachIncomingIntent(Intent intent) {

    }

    public void getSearchActivities(String longitude, String latitude, String townAdCode, int page, String uid,String activitiesType,String mContent){
        final Dialog dialog = ProgressDialog.createLoadingDialog(mContext,mContent);
        dialog.show();
        mCompositeSubscription.add(manager.getSearchActivities(longitude,latitude,townAdCode,page,uid,activitiesType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ActivitiesEntity>() {
                    @Override
                    public void onCompleted() {
                        dialog.dismiss();
                        if (mActivitiesEntity != null){
                            mActivitiesView.onSuccess(mActivitiesEntity);
                            Log.i("TAG", "onActivities: " + new Gson().toJson(mActivitiesEntity));

                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        dialog.dismiss();
                        try {
                            if (e instanceof SocketTimeoutException) {//请求超时
                            } else if (e instanceof ConnectException) {//网络连接超时
                                mActivitiesView.onError("网络连接超时");
                            } else if (e instanceof SSLHandshakeException) {//安全证书异常
                                mActivitiesView.onError("安全证书异常");
                            } else if (e instanceof HttpException) {//请求的地址不存在
                                int code = ((HttpException) e).code();
                                if (code == 504) {
                                    mActivitiesView.onError("网络异常，请检查您的网络状态");
                                } else if (code == 404) {
                                    mActivitiesView.onError("请求的地址不存在");
                                } else {
                                    mActivitiesView.onError("请求失败");
                                }
                            } else if (e instanceof UnknownHostException) {//域名解析失败
                                mActivitiesView.onError("域名解析失败");
                            } else {
                                mActivitiesView.onError("error:" + e.getMessage());
                            }
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        } finally {
                            Log.e("OnSuccessAndFaultSub", "error:" + e.getMessage());
                        }
                    }

                    @Override
                    public void onNext(ActivitiesEntity activitiesEntity) {
                        mActivitiesEntity = activitiesEntity;
                    }
                }));
    }
}
