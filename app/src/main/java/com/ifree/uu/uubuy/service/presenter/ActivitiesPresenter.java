package com.ifree.uu.uubuy.service.presenter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;

import com.ifree.uu.uubuy.dialog.ProgressDialog;
import com.ifree.uu.uubuy.service.entity.ActivitiesEntity;
import com.ifree.uu.uubuy.service.entity.AroundEntity;
import com.ifree.uu.uubuy.service.manager.DataManager;
import com.ifree.uu.uubuy.service.view.ActivitiesView;
import com.ifree.uu.uubuy.service.view.AroundView;
import com.ifree.uu.uubuy.service.view.View;

import rx.Observer;
import rx.Scheduler;
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
    private ActivitiesView mActivitiesView;
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
        mActivitiesView = (ActivitiesView) view;
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
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        dialog.dismiss();
                        mActivitiesView.onError("请求失败！！");
                    }

                    @Override
                    public void onNext(ActivitiesEntity activitiesEntity) {
                        mActivitiesEntity = activitiesEntity;
                    }
                }));
    }
}
