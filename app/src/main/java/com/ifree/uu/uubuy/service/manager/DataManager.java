package com.ifree.uu.uubuy.service.manager;

import android.content.Context;

import com.ifree.uu.uubuy.service.RetrofitHelper;
import com.ifree.uu.uubuy.service.RetrofitService;
import com.ifree.uu.uubuy.service.entity.ActivitiesEntity;
import com.ifree.uu.uubuy.service.entity.AroundEntity;
import com.ifree.uu.uubuy.service.entity.CityInfoEntity;
import com.ifree.uu.uubuy.service.entity.CouponEntity;
import com.ifree.uu.uubuy.service.entity.FirstClassifyEntity;
import com.ifree.uu.uubuy.service.entity.GroupEntity;
import com.ifree.uu.uubuy.service.entity.HomeEntity;
import com.ifree.uu.uubuy.service.entity.MessageEntity;
import com.ifree.uu.uubuy.service.entity.MineEntity;
import com.ifree.uu.uubuy.service.entity.OrderEntity;
import com.ifree.uu.uubuy.service.entity.UserInfoEntity;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observable;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/13.
 * Description:
 */
public class DataManager {

    private RetrofitService mRetrofitService;

    public DataManager(Context context){
        this.mRetrofitService = RetrofitHelper.getInstance(context).getServer();
    }

    public Observable<UserInfoEntity> getSearchSendCode(String userPhone,String codeType){
        return mRetrofitService.getSearchSendCode(userPhone,codeType);
    }

    public Observable<UserInfoEntity> getSearchRegister(String userPhone,String password,String code){
        return mRetrofitService.getSearchRegister(userPhone,password,code);
    }

    public Observable<UserInfoEntity> getSearchForgetPassword(String userPhone,String password,String code){
        return mRetrofitService.getSearchForgetPassword(userPhone,password,code);
    }

    public Observable<UserInfoEntity> getSearchPhoneCodeLogin(String userPhone,String code){
        return mRetrofitService.getSearchPhoneCodeLogin(userPhone,code);
    }

    public Observable<UserInfoEntity> getSearchPassWordLogin(String userPhone,String password){
        return mRetrofitService.getSearchPassWordLogin(userPhone,password);
    }

    public Observable<UserInfoEntity> getSearchThirdLogin(String thirdUid,String nickName,String userIcon,String type){
        return mRetrofitService.getSearchThirdLogin(thirdUid,nickName,userIcon,type);
    }

    public Observable<CityInfoEntity> getSearchCityInfo(){
        return mRetrofitService.getSearchCityInfo();
    }

    public Observable<HomeEntity> getSearchHomes(String longitude, String latitude, String townAdCode, int page, String uid){
        return mRetrofitService.getSearchHomes(longitude,latitude,townAdCode,page,uid);
    }

    public Observable<FirstClassifyEntity> getSearchClassifyListInfo(String longitude, String latitude, String townAdCode, String adTypeId, String type, String menuId, int page, String uid){
        return mRetrofitService.getSearchClassifyListInfo(longitude,latitude,townAdCode,adTypeId,type,menuId,page,uid);
    }

    public Observable<AroundEntity> getSearchArounds(String longitude, String latitude, String townAdCode, int page, String uid){
        return mRetrofitService.getSearchArounds(longitude,latitude,townAdCode,page,uid);
    }

    public Observable<ActivitiesEntity> getSearchActivities(String longitude, String latitude, String townAdCode, int page, String uid, String activitiesType){
        return mRetrofitService.getSearchActivities(longitude,latitude,townAdCode,page,uid,activitiesType);
    }

    public Observable<OrderEntity> getSearchOrders(String orderState,int page,String uid){
        return mRetrofitService.getSearchOrders(orderState,page,uid);
    }

    public Observable<MineEntity> getSearchMineInfos(String longitude, String latitude, String townAdCode, int page, String uid){
        return mRetrofitService.getSearchMineInfos(longitude,latitude,townAdCode,page,uid);
    }
    public Observable<UserInfoEntity> getSearchModifyUserIconInfo(String uid,MultipartBody.Part body){
        return mRetrofitService.getSearchModifyUserIconInfo(uid,body);
    }
    public Observable<UserInfoEntity> getSearchModifyUserInfo(String uid, String userSex, String userName, String userBirthday, String userIdCartNumber, String userAddress){
        return mRetrofitService.getSearchModifyUserInfo(uid,userSex,userName,userBirthday,userIdCartNumber,userAddress);
    }

    public Observable<GroupEntity> getSearchGroupInfos(String uid){
        return mRetrofitService.getSearchGroupInfos(uid);
    }

    public Observable<UserInfoEntity> getSearchSignIns(String uid){
        return mRetrofitService.getSearchSignIns(uid);
    }

    public Observable<CouponEntity> getSearchCouponCenter(String uid,String businessId,String couponType,String longitude,String latitude,String townAdCode,int page){
        return mRetrofitService.getSearchCouponCenter(uid,businessId,couponType,longitude,latitude,townAdCode,page);
    }

    public Observable<CouponEntity> getSearchMyCoupon(String uid,String businessId,String couponType,String longitude,String latitude,String townAdCode,int page){
        return mRetrofitService.getSearchMyCoupon(uid,businessId,couponType,longitude,latitude,townAdCode,page);
    }

    public Observable<MessageEntity> getSearchMessages(String uid, String type, int page){
            return mRetrofitService.getSearchMessages(uid,type,page);
        }


}
