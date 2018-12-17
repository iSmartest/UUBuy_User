package com.ifree.uu.uubuy.mvp.manager;

import android.content.Context;

import com.ifree.uu.uubuy.mvp.entity.UserInfoEntity;
import com.ifree.uu.uubuy.mvp.view.RetrofitHelper2;
import com.ifree.uu.uubuy.mvp.view.RetrofitService;

import rx.Observable;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/13.
 * Description:
 */
public class DataManager2 {

    private RetrofitService mRetrofitService;

    public DataManager2(Context context) {
        this.mRetrofitService = RetrofitHelper2.getInstance(context).getServer();
    }

    public Observable<UserInfoEntity> searchElasticFrame(String uid) {
        return mRetrofitService.searchElasticFrame(uid);
    }

    public Observable<UserInfoEntity> searchEnroll(String uid, String userPhone) {
        return mRetrofitService.searchEnroll(uid,userPhone);
    }

    public Observable<UserInfoEntity> searchPrizeCode(String uid) {
        return mRetrofitService.searchPrizeCode(uid);
    }
}
