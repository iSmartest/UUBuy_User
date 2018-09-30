package com.ifree.uu.uubuy.service.presenter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.gson.Gson;
import com.ifree.uu.uubuy.dialog.ProgressDialog;
import com.ifree.uu.uubuy.service.RequestResult;
import com.ifree.uu.uubuy.service.entity.MyFootPrintEntity;
import com.ifree.uu.uubuy.service.manager.DataManager;
import com.ifree.uu.uubuy.service.view.ProjectView;
import com.ifree.uu.uubuy.service.view.View;

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
public class MyFootPrintPresenter implements Presenter {
    private DataManager manager;
    private CompositeSubscription mCompositeSubscription;
    private Context mContext;
    private MyFootPrintEntity mMyFootPrintEntity;
    private ProjectView mMyFootPrintView;

    public MyFootPrintPresenter(Context mContext) {
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
        mMyFootPrintView = (ProjectView) view;
    }

    @Override
    public void attachIncomingIntent(Intent intent) {

    }

    public void getSearchMyFootPrint(String uid, int page, String mContent) {
        final Dialog dialog = ProgressDialog.createLoadingDialog(mContext, mContent);
        dialog.show();
        mCompositeSubscription.add(manager.getSearchMyFootPrint(uid,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MyFootPrintEntity>() {
                    @Override
                    public void onCompleted() {
                        dialog.dismiss();
                        Log.i("TAG", "onCompleted: " + new Gson().toJson(mMyFootPrintEntity));
                        if (mMyFootPrintEntity != null) {
                            mMyFootPrintView.onSuccess(mMyFootPrintEntity);
                            Log.i("TAG", "onCompleted: " + new Gson().toJson(mMyFootPrintEntity));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        dialog.dismiss();
                        mMyFootPrintView.onError(RequestResult.getError(e));
                    }

                    @Override
                    public void onNext(MyFootPrintEntity MyFootPrintEntity) {
                        mMyFootPrintEntity = MyFootPrintEntity;
                    }
                }));
    }
}
