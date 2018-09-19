package com.ifree.uu.uubuy.service.presenter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.gson.Gson;
import com.ifree.uu.uubuy.dialog.ProgressDialog;
import com.ifree.uu.uubuy.service.entity.SearchEntity;
import com.ifree.uu.uubuy.service.manager.DataManager;
import com.ifree.uu.uubuy.service.view.SearchView;
import com.ifree.uu.uubuy.service.view.View;

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
public class SearchPresenter implements Presenter {

    private DataManager manager;
    private CompositeSubscription mCompositeSubscription;
    private Context mContext;
    private SearchView mSearchView;
    private SearchEntity mSearchEntity;
    public SearchPresenter(Context mContext){
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
        mSearchView = (SearchView) view;
    }

    @Override
    public void attachIncomingIntent(Intent intent) {

    }

    public void getSearchInfo(String longitude, String latitude, String townAdCode, int page, String keyWord, String uid, String searchType,String mContent){
        final Dialog dialog = ProgressDialog.createLoadingDialog(mContext,mContent);
        dialog.show();
        mCompositeSubscription.add(manager.getSearchInfo(longitude,latitude,townAdCode,page,keyWord,uid,searchType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SearchEntity>() {
                    @Override
                    public void onCompleted() {
                        dialog.dismiss();
                        if (mSearchEntity != null){
                            mSearchView.onSuccess(mSearchEntity);
                            Log.i("TAG", "onSearch: " + new Gson().toJson(mSearchEntity));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        dialog.dismiss();
                        mSearchView.onError("请求失败！！");
                    }

                    @Override
                    public void onNext(SearchEntity SearchEntity) {
                        mSearchEntity = SearchEntity;
                    }
                })
        );
    }

}
