package com.ifree.uu.uubuy.service.presenter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;

import com.ifree.uu.uubuy.dialog.ProgressDialog;
import com.ifree.uu.uubuy.service.entity.MessageEntity;
import com.ifree.uu.uubuy.service.manager.DataManager;
import com.ifree.uu.uubuy.service.view.MessageView;
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
public class MessagePresenter implements Presenter {
    private DataManager manager;
    private CompositeSubscription mCompositeSubscription;
    private Context mContext;
    private MessageEntity mMessageEntity;
    private MessageView mMessageView;

    public MessagePresenter(Context mContext) {
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
        mMessageView = (MessageView) view;
    }

    @Override
    public void attachIncomingIntent(Intent intent) {

    }

    public void getSearchMessages(String uid, String type, int page, String mContent) {
        final Dialog dialog = ProgressDialog.createLoadingDialog(mContext, mContent);
        dialog.show();
        mCompositeSubscription.add(manager.getSearchMessages(uid, type, page)
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MessageEntity>() {
                    @Override
                    public void onCompleted() {
                        dialog.dismiss();
                        if (mMessageEntity != null) {
                            mMessageView.onSuccess(mMessageEntity);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        dialog.dismiss();
                        mMessageView.onError("请求失败！！");
                    }

                    @Override
                    public void onNext(MessageEntity messageEntity) {
                        mMessageEntity = messageEntity;
                    }
                }));
    }
}
