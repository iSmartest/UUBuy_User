package com.ifree.uu.uubuy.service.presenter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.gson.Gson;
import com.ifree.uu.uubuy.dialog.ProgressDialog;
import com.ifree.uu.uubuy.service.entity.GroupEntity;
import com.ifree.uu.uubuy.service.manager.DataManager;
import com.ifree.uu.uubuy.service.view.GroupInfoView;
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
public class GroupInfoPresenter implements Presenter {

    private DataManager manager;
    private CompositeSubscription mCompositeSubscription;
    private Context mContext;
    private GroupEntity mGroupEntity;
    private GroupInfoView mGroupInfoView;

    public GroupInfoPresenter(Context mContext){
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
        mGroupInfoView = (GroupInfoView) view;
    }

    @Override
    public void attachIncomingIntent(Intent intent) {

    }

    public void getSearchGroupInfos(String uid, String mContent) {
        final Dialog dialog = ProgressDialog.createLoadingDialog(mContext, mContent);
        dialog.show();
        mCompositeSubscription.add(manager.getSearchGroupInfos(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GroupEntity>() {
                    @Override
                    public void onCompleted() {
                        dialog.dismiss();
                        if (mGroupEntity != null) {
                            mGroupInfoView.onSuccess(mGroupEntity);
                            Log.i("TAG", "onCompleted: " + new Gson().toJson(mGroupEntity));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        dialog.dismiss();
                        Log.i("TAG", "onCompleted: " + e.getMessage());
                        mGroupInfoView.onError("请求失败！！");
                    }

                    @Override
                    public void onNext(GroupEntity groupEntity) {
                        mGroupEntity = groupEntity;
                    }
                }));

    }

}
