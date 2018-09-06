package com.ifree.uu.uubuy.service.presenter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;

import com.ifree.uu.uubuy.dialog.ProgressDialog;
import com.ifree.uu.uubuy.service.entity.HomeEntity;
import com.ifree.uu.uubuy.service.view.View;


import com.ifree.uu.uubuy.service.manager.DataManager;
import com.ifree.uu.uubuy.service.view.HomeView;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/13.
 * Description:
 */
public class HomePresenter implements Presenter {

    private DataManager manager;
    private CompositeSubscription mCompositeSubscription;
    private Context mContext;
    private HomeView mHomeView;
    private HomeEntity mHomeEntity;
    public HomePresenter(Context mContext){
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
        mHomeView = (HomeView) view;
    }

    @Override
    public void attachIncomingIntent(Intent intent) {

    }

    public void getSearchHomes(String longitude, String latitude, String townAdCode, int page,String uid,String mContent){
        final Dialog dialog = ProgressDialog.createLoadingDialog(mContext,mContent);
        dialog.show();
        mCompositeSubscription.add(manager.getSearchHomes(longitude,latitude,townAdCode,page,uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeEntity>() {
                    @Override
                    public void onCompleted() {
                        dialog.dismiss();
                        if (mHomeEntity != null){
                            mHomeView.onSuccess(mHomeEntity);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        dialog.dismiss();
                        mHomeView.onError("请求失败！！");
                    }

                    @Override
                    public void onNext(HomeEntity homeEntity) {
                        mHomeEntity = homeEntity;
                    }
                })
        );
    }

}
