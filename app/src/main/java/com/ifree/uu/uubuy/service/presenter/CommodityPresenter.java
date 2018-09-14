package com.ifree.uu.uubuy.service.presenter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.gson.Gson;
import com.ifree.uu.uubuy.dialog.ProgressDialog;
import com.ifree.uu.uubuy.service.entity.CommodityListEntity;
import com.ifree.uu.uubuy.service.entity.FirstClassifyEntity;
import com.ifree.uu.uubuy.service.manager.DataManager;
import com.ifree.uu.uubuy.service.view.CommodityListView;
import com.ifree.uu.uubuy.service.view.FirstClassifyView;
import com.ifree.uu.uubuy.service.view.View;

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
public class CommodityPresenter implements Presenter {
    private DataManager manager;
    private CompositeSubscription mCompositeSubscription;
    private Context mContext;
    private CommodityListEntity mCommodityListEntity;
    private CommodityListView mCommodityListView;

    public CommodityPresenter(Context mContext){
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
        mCommodityListView = (CommodityListView) view;
    }

    @Override
    public void attachIncomingIntent(Intent intent) {

    }

    public void getSearchCommodityListInfo( String storeId, int page, String uid, String mContent) {
        final Dialog dialog = ProgressDialog.createLoadingDialog(mContext, mContent);
        dialog.show();
        mCompositeSubscription.add(manager.getSearchCommodityListInfo(storeId,page,uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CommodityListEntity >() {
                    @Override
                    public void onCompleted() {
                        dialog.dismiss();
                        if (mCommodityListEntity != null) {
                            mCommodityListView.onSuccess(mCommodityListEntity);
                            Log.i("TAG", "onFirst: " + new Gson().toJson(mCommodityListEntity));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        dialog.dismiss();
                        mCommodityListView.onError("请求失败！！");
                    }

                    @Override
                    public void onNext(CommodityListEntity  commodityListEntity) {
                        mCommodityListEntity = commodityListEntity;
                    }
                }));

    }

}
