package com.ifree.uu.uubuy.service.presenter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.gson.Gson;
import com.ifree.uu.uubuy.dialog.ProgressDialog;
import com.ifree.uu.uubuy.service.entity.CommodityInfoEntity;
import com.ifree.uu.uubuy.service.manager.DataManager;
import com.ifree.uu.uubuy.service.view.CommodityInfoView;
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
public class CommodityInfoPresenter implements Presenter {
    private DataManager manager;
    private CompositeSubscription mCompositeSubscription;
    private Context mContext;
    private CommodityInfoEntity mCommodityInfoEntity;
    private CommodityInfoView mCommodityInfoView;

    public CommodityInfoPresenter(Context mContext){
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
        mCommodityInfoView = (CommodityInfoView) view;
    }

    @Override
    public void attachIncomingIntent(Intent intent) {

    }

    public void getSearchCommodityInfo( String commodityId, String type, String uid, String mContent) {
        final Dialog dialog = ProgressDialog.createLoadingDialog(mContext, mContent);
        dialog.show();
        mCompositeSubscription.add(manager.getSearchCommodityInfo(commodityId,type,uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CommodityInfoEntity >() {
                    @Override
                    public void onCompleted() {
                        dialog.dismiss();
                        if (mCommodityInfoEntity != null) {
                            mCommodityInfoView.onSuccess(mCommodityInfoEntity);
                            Log.i("TAG", "onFirst: " + new Gson().toJson(mCommodityInfoEntity));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        dialog.dismiss();
                        mCommodityInfoView.onError("请求失败！！");
                    }

                    @Override
                    public void onNext(CommodityInfoEntity  CommodityInfoEntity) {
                        mCommodityInfoEntity = CommodityInfoEntity;
                    }
                }));

    }

}
