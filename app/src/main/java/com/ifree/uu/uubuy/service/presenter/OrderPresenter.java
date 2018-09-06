package com.ifree.uu.uubuy.service.presenter;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;

import com.ifree.uu.uubuy.dialog.ProgressDialog;
import com.ifree.uu.uubuy.service.entity.OrderEntity;
import com.ifree.uu.uubuy.service.manager.DataManager;
import com.ifree.uu.uubuy.service.view.OrderView;
import com.ifree.uu.uubuy.service.view.View;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/21.
 * Description:
 */
public class OrderPresenter implements Presenter {
    private DataManager manager;
    private CompositeSubscription mCompositeSubscription;
    private Context mContext;
    private OrderView mOrderView;
    private OrderEntity mOrderEntity;

    public OrderPresenter(Context mContext){
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
        if (mCompositeSubscription.hasSubscriptions()){
            mCompositeSubscription.unsubscribe();
        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void attachView(View view) {
        mOrderView = (OrderView) view;
    }

    @Override
    public void attachIncomingIntent(Intent intent) {

    }

    public void getSearchOrders(String orderState,int page,String uid,String mContent){
        final Dialog dialog = ProgressDialog.createLoadingDialog(mContext,mContent);
        dialog.show();
        mCompositeSubscription.add(manager.getSearchOrders(orderState,page,uid)
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<OrderEntity>() {
                    @Override
                    public void onCompleted() {
                        dialog.dismiss();
                        if (mOrderEntity != null){
                            mOrderView.onSuccess(mOrderEntity);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        dialog.dismiss();
                        mOrderView.onError("请求失败！！");
                    }

                    @Override
                    public void onNext(OrderEntity orderEntity) {
                        mOrderEntity = orderEntity;
                    }
                }));
    }
}
