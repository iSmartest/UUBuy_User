package com.ifree.uu.uubuy.mvp.presenter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.gson.Gson;
import com.ifree.uu.uubuy.dialog.ProgressDialog;
import com.ifree.uu.uubuy.mvp.RequestResult;
import com.ifree.uu.uubuy.mvp.entity.FirstClassifyEntity;
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
 * Created by 2018/8/22.
 * Description:
 */
public class FirstClassifyPresenter implements Presenter {
    private DataManager manager;
    private CompositeSubscription mCompositeSubscription;
    private Context mContext;
    private FirstClassifyEntity mFirstClassifyEntity;
    private ProjectView mFirstClassifyView;

    public FirstClassifyPresenter(Context mContext){
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
        mFirstClassifyView = (ProjectView) view;
    }

    @Override
    public void attachIncomingIntent(Intent intent) {

    }

    public void getSearchClassifyListInfo(String longitude, String latitude, String townAdCode, String adTypeId, String type, String menuId, int page, String uid, String mContent) {
        final Dialog dialog = ProgressDialog.createLoadingDialog(mContext, mContent);
        dialog.show();
        mCompositeSubscription.add(manager.getSearchClassifyListInfo(longitude,latitude,townAdCode,adTypeId,type,menuId,page,uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FirstClassifyEntity >() {
                    @Override
                    public void onCompleted() {
                        dialog.dismiss();
                        if (mFirstClassifyEntity != null) {
                            mFirstClassifyView.onSuccess(mFirstClassifyEntity);
                            Log.i("TAG", "onFirst: " + new Gson().toJson(mFirstClassifyEntity));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        dialog.dismiss();
                        mFirstClassifyView.onError(RequestResult.getError(e));
                    }

                    @Override
                    public void onNext(FirstClassifyEntity  firstClassifyEntity) {
                        mFirstClassifyEntity = firstClassifyEntity;
                    }
                }));

    }

}
