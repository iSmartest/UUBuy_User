package com.ifree.uu.uubuy.mvp.persenter;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.gson.Gson;
import com.ifree.uu.uubuy.mvp.RequestResult;
import com.ifree.uu.uubuy.mvp.manager.DataManager;
import com.ifree.uu.uubuy.mvp.modle.HomeBean;
import com.ifree.uu.uubuy.mvp.persenter.Presenter;
import com.ifree.uu.uubuy.mvp.view.ProjectView;
import com.ifree.uu.uubuy.mvp.view.View;

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
    private ProjectView mHomeView;
    private HomeBean mHomeBean;
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
        mHomeView = (ProjectView) view;
    }

    @Override
    public void attachIncomingIntent(Intent intent) {

    }

    public void getSearchHomes(String longitude, String latitude, String townAdCode, String city, int page, String mContent){
//        final Dialog dialog = ProgressDialog.createLoadingDialog(mContext,mContent);
//        dialog.show();
        mCompositeSubscription.add(manager.getSearchHomes(longitude,latitude,townAdCode,city,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeBean>() {
                    @Override
                    public void onCompleted() {
//                        dialog.dismiss();
                        if (mHomeBean != null){
                            mHomeView.onSuccess(mHomeBean);
                            Log.i("TAG", "onCompleted: " + new Gson().toJson(mHomeBean));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
//                        dialog.dismiss();
                        mHomeView.onError(RequestResult.getError(e));
                    }

                    @Override
                    public void onNext(HomeBean homeBean) {
                        mHomeBean = homeBean;
                    }
                })
        );
    }

}
