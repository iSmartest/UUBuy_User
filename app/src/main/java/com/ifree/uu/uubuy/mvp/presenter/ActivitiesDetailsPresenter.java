package com.ifree.uu.uubuy.mvp.presenter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.gson.Gson;
import com.ifree.uu.uubuy.dialog.ProgressDialog;
import com.ifree.uu.uubuy.mvp.RequestResult;
import com.ifree.uu.uubuy.mvp.entity.ActivitiesDetailsEntity;
import com.ifree.uu.uubuy.mvp.manager.DataManager;
import com.ifree.uu.uubuy.mvp.view.ProjectView;
import com.ifree.uu.uubuy.mvp.view.View;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/23.
 * Description:
 */
public class ActivitiesDetailsPresenter implements Presenter {
    private DataManager manager;
    private CompositeSubscription mCompositeSubscription;
    private Context mContext;
    private ActivitiesDetailsEntity mActivitiesDetailsEntity;
    private ProjectView mActivitiesDetailsView;

    public ActivitiesDetailsPresenter(Context mContext) {
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
        mActivitiesDetailsView = (ProjectView) view;
    }

    @Override
    public void attachIncomingIntent(Intent intent) {

    }

    public void getSearchActivitiesInfo(String uid, String marketId, String advId, String mContent) {
        final Dialog dialog = ProgressDialog.createLoadingDialog(mContext, mContent);
        dialog.show();
        mCompositeSubscription.add(manager.getSearchActivitiesInfo(uid,marketId,advId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ActivitiesDetailsEntity>() {
                    @Override
                    public void onCompleted() {
                        dialog.dismiss();
                        if (mActivitiesDetailsEntity != null) {
                            mActivitiesDetailsView.onSuccess(mActivitiesDetailsEntity);
                            Log.i("TAG", "onCompleted: " + new Gson().toJson(mActivitiesDetailsEntity));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        dialog.dismiss();
                        mActivitiesDetailsView.onError(RequestResult.getError(e));
                    }

                    @Override
                    public void onNext(ActivitiesDetailsEntity ActivitiesDetailsEntity) {
                        mActivitiesDetailsEntity = ActivitiesDetailsEntity;
                    }
                }));
    }

}
