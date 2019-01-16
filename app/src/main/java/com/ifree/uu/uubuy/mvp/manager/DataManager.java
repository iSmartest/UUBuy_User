package com.ifree.uu.uubuy.mvp.manager;

import android.content.Context;

import com.ifree.uu.uubuy.app.MyApplication;
import com.ifree.uu.uubuy.mvp.modle.CityInfoBean;
import com.ifree.uu.uubuy.mvp.modle.GroupBean;
import com.ifree.uu.uubuy.mvp.modle.HomeBean;
import com.ifree.uu.uubuy.mvp.modle.HotKeyWordBean;
import com.ifree.uu.uubuy.mvp.modle.UpdateBean;
import com.ifree.uu.uubuy.mvp.modle.UserInfoBean;
import com.ifree.uu.uubuy.mvp.persenter.SearchBean;
import com.ifree.uu.uubuy.mvp.view.RetrofitHelper;
import com.ifree.uu.uubuy.mvp.view.RetrofitService;
import com.ifree.uu.uubuy.utils.DeviceInfoUtils;

import okhttp3.MultipartBody;
import rx.Observable;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/13.
 * Description:
 */
public class DataManager {

    private RetrofitService mRetrofitService;

    public DataManager(Context context) {
        this.mRetrofitService = RetrofitHelper.getInstance(context).getServer();
    }

    public Observable<UserInfoBean> upLoadUUId(String systemName, String uuid, String uid) {
        return mRetrofitService.upLoadUUId(systemName,uuid,uid);
    }


    public Observable<HomeBean> getSearchHomes(String longitude, String latitude, String townAdCode, String city,int page) {
        return mRetrofitService.getSearchHomes(longitude, latitude, townAdCode, city,page);
    }

    public Observable<UserInfoBean> getSearchModifyUserIconInfo(String uid, MultipartBody.Part body) {
        return mRetrofitService.getSearchModifyUserIconInfo(uid, body);
    }

    public Observable<UserInfoBean> getSearchModifyUserInfo(String uid, String userSex, String userName, String userBirthday, String userIdCartNumber, String userAddress) {
        return mRetrofitService.getSearchModifyUserInfo(uid, userSex, userName, userBirthday, userIdCartNumber, userAddress);
    }

    //获取验证码
    public Observable<UserInfoBean> getSearchSendCode(String userPhone, String codeType) {
        return mRetrofitService.getSearchSendCode(userPhone, codeType);
    }
    //密码登录
    public Observable<UserInfoBean> getSearchPassWordLogin(String userPhone, String password) {
        return mRetrofitService.getSearchPassWordLogin(userPhone, password,"Android",DeviceInfoUtils.getMyUUID(MyApplication.getApplication()));
    }
    //手机号验证码登录
    public Observable<UserInfoBean> getSearchPhoneCodeLogin(String userPhone, String code, String sessionId) {
        return mRetrofitService.getSearchPhoneCodeLogin(userPhone, code, sessionId,"Android",DeviceInfoUtils.getMyUUID(MyApplication.getApplication()));
    }
    //第三方登录
    public Observable<UserInfoBean> getSearchThirdLogin(String thirdUid, String nickName, String userIcon, String type) {
        return mRetrofitService.getSearchThirdLogin(thirdUid, nickName, userIcon, type,"Android",DeviceInfoUtils.getMyUUID(MyApplication.getApplication()));
    }
    //绑定手机号
    public Observable<UserInfoBean> getSearchBindPhone(String userPhone, String password, String code, String sessionId, String uid, String type) {
        return mRetrofitService.getSearchBindPhone(userPhone, password, code, sessionId, uid, type);
    }
    //忘记密码
    public Observable<UserInfoBean> getSearchForgetPassword(String userPhone, String password, String code, String sessionId) {
        return mRetrofitService.getSearchForgetPassword(userPhone, password, code, sessionId);
    }
    //注册账号
    public Observable<UserInfoBean> getSearchRegister(String userPhone, String password, String code, String sessionId) {
        return mRetrofitService.getSearchRegister(userPhone, password, code, sessionId);
    }
    //修改手机号
    public Observable<UserInfoBean> getSearchModifyPhone(String userPhone, String password, String code, String sessionId,String uid) {
        return mRetrofitService.getSearchModifyPhone(userPhone, password, code, sessionId,uid);
    }
    //修改登录密码
    public Observable<UserInfoBean> getSearchModifyPassword(String userPhone, String oldPassword, String newPassword, String code, String sessionId,String uid) {
        return mRetrofitService.getSearchModifyPassword(userPhone, oldPassword, newPassword,code, sessionId,uid);
    }
    // 检查版本更新
    public Observable<UpdateBean> getUpdate() {
        return mRetrofitService.getUpdate();
    }
    //分享
    public Observable<UserInfoBean> getSearchShare(String uid) {
        return mRetrofitService.getSearchShare(uid);
    }
    //签到
    public Observable<UserInfoBean> getSearchSignIns(String uid) {
        return mRetrofitService.getSearchSignIns(uid);
    }
    //用户成长页面
    public Observable<GroupBean> getSearchGroupInfos(String uid) {
        return mRetrofitService.getSearchGroupInfos(uid);
    }
    //热词
    public Observable<HotKeyWordBean> getSearchHotKeyword() {
        return mRetrofitService.getSearchHotKeyword();
    }
    //搜索
    public Observable<SearchBean> getSearchInfo(String longitude, String latitude, String townAdCode, int page, String keyWord, String uid, String searchType) {
        return mRetrofitService.getSearchInfo(longitude, latitude, townAdCode, page, keyWord, uid, searchType);
    }
    //获取开通城市
    public Observable<CityInfoBean> getSearchCityInfo() {
        return mRetrofitService.getSearchCityInfo();
    }
}
