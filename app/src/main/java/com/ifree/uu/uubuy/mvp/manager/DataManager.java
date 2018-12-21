package com.ifree.uu.uubuy.mvp.manager;

import android.content.Context;

import com.ifree.uu.uubuy.app.MyApplication;
import com.ifree.uu.uubuy.mvp.entity.UpdateEntity;
import com.ifree.uu.uubuy.mvp.view.RetrofitHelper;
import com.ifree.uu.uubuy.mvp.view.RetrofitService;
import com.ifree.uu.uubuy.mvp.entity.ActivitiesEntity;
import com.ifree.uu.uubuy.mvp.entity.ActivitiesDetailsEntity;
import com.ifree.uu.uubuy.mvp.entity.AroundEntity;
import com.ifree.uu.uubuy.mvp.entity.CityInfoEntity;
import com.ifree.uu.uubuy.mvp.entity.CommodityInfoEntity;
import com.ifree.uu.uubuy.mvp.entity.CommodityListEntity;
import com.ifree.uu.uubuy.mvp.entity.CompareCommodityEntity;
import com.ifree.uu.uubuy.mvp.entity.CouponEntity;
import com.ifree.uu.uubuy.mvp.entity.FirstClassifyEntity;
import com.ifree.uu.uubuy.mvp.entity.GroupEntity;
import com.ifree.uu.uubuy.mvp.entity.HomeEntity;
import com.ifree.uu.uubuy.mvp.entity.HotKeyWordEntity;
import com.ifree.uu.uubuy.mvp.entity.MessageEntity;
import com.ifree.uu.uubuy.mvp.entity.MineEntity;
import com.ifree.uu.uubuy.mvp.entity.MoreEntity;
import com.ifree.uu.uubuy.mvp.entity.MyFootPrintEntity;
import com.ifree.uu.uubuy.mvp.entity.OrderEntity;
import com.ifree.uu.uubuy.mvp.entity.SearchEntity;
import com.ifree.uu.uubuy.mvp.entity.SecondActivitiesEntity;
import com.ifree.uu.uubuy.mvp.entity.UserInfoEntity;
import com.ifree.uu.uubuy.uitls.DeviceInfoUtils;

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

    public Observable<UserInfoEntity> getSearchSendCode(String userPhone, String codeType) {
        return mRetrofitService.getSearchSendCode(userPhone, codeType);
    }

    public Observable<UserInfoEntity> getSearchRegister(String userPhone, String password, String code, String sessionId) {
        return mRetrofitService.getSearchRegister(userPhone, password, code, sessionId);
    }
    public Observable<UserInfoEntity> getSearchModifyPhone(String userPhone, String password, String code, String sessionId,String uid) {
        return mRetrofitService.getSearchModifyPhone(userPhone, password, code, sessionId,uid);
    }

    public Observable<UserInfoEntity> getSearchModifyPassword(String userPhone, String oldPassword, String newPassword, String code, String sessionId,String uid) {
        return mRetrofitService.getSearchModifyPassword(userPhone, oldPassword, newPassword,code, sessionId,uid);
    }

    public Observable<UserInfoEntity> getSearchBindPhone(String userPhone, String password, String code, String sessionId, String uid, String type) {
        return mRetrofitService.getSearchBindPhone(userPhone, password, code, sessionId, uid, type);
    }

    public Observable<UserInfoEntity> getSearchForgetPassword(String userPhone, String password, String code, String sessionId) {
        return mRetrofitService.getSearchForgetPassword(userPhone, password, code, sessionId);
    }

    public Observable<UserInfoEntity> getSearchPhoneCodeLogin(String userPhone, String code, String sessionId) {
        return mRetrofitService.getSearchPhoneCodeLogin(userPhone, code, sessionId,"Android",DeviceInfoUtils.getMyUUID(MyApplication.getApplication()));
    }

    public Observable<UserInfoEntity> getSearchPassWordLogin(String userPhone, String password) {
        return mRetrofitService.getSearchPassWordLogin(userPhone, password,"Android",DeviceInfoUtils.getMyUUID(MyApplication.getApplication()));
    }

    public Observable<UserInfoEntity> getSearchThirdLogin(String thirdUid, String nickName, String userIcon, String type) {
        return mRetrofitService.getSearchThirdLogin(thirdUid, nickName, userIcon, type,"Android",DeviceInfoUtils.getMyUUID(MyApplication.getApplication()));
    }

    public Observable<CityInfoEntity> getSearchCityInfo() {
        return mRetrofitService.getSearchCityInfo();
    }

    public Observable<HotKeyWordEntity> getSearchHotKeyword() {
        return mRetrofitService.getSearchHotKeyword();
    }

    public Observable<SearchEntity> getSearchInfo(String longitude, String latitude, String townAdCode, int page, String keyWord, String uid, String searchType) {
        return mRetrofitService.getSearchInfo(longitude, latitude, townAdCode, page, keyWord, uid, searchType);
    }

    public Observable<HomeEntity> getSearchHomes(String longitude, String latitude, String townAdCode, String city,int page) {
        return mRetrofitService.getSearchHomes(longitude, latitude, townAdCode, city,page);
    }

    public Observable<FirstClassifyEntity> getSearchClassifyListInfo(String longitude, String latitude, String townAdCode, String adTypeId, String type, String menuId, int page, String uid) {
        return mRetrofitService.getSearchClassifyListInfo(longitude, latitude, townAdCode, adTypeId, type, menuId, page, uid);
    }

    public Observable<CommodityListEntity> getSearchCommodityListInfo(String storeId, int page, String uid) {
        return mRetrofitService.getSearchCommodityListInfo(storeId, page, uid);
    }

    public Observable<MoreEntity> getSearchMoreListInfo(String classifyId, String type, String menuId, String uid, int page) {
        return mRetrofitService.getSearchMoreListInfo(classifyId, type, menuId, uid, page);
    }

    public Observable<CommodityInfoEntity> getSearchCommodityInfo(String commodityId, String type, String uid) {
        return mRetrofitService.getSearchCommodityInfo(commodityId, type, uid);
    }

    public Observable<CompareCommodityEntity> getSearchCompareInfo(String commodityId, int page) {
        return mRetrofitService.getSearchCompareInfo(commodityId, page);
    }

    public Observable<SecondActivitiesEntity> getSearchSecondListInfo(String floor,String fristActivitiesId, int page, String uid, String fristActivitiesType, String classify) {
        return mRetrofitService.getSearchSecondListInfo(floor,fristActivitiesId, page, uid, fristActivitiesType, classify);
    }

    public Observable<AroundEntity> getSearchArounds(String longitude, String latitude, String townAdCode, int page, String uid) {
        return mRetrofitService.getSearchArounds(longitude, latitude, townAdCode, page, uid);
    }

    public Observable<ActivitiesEntity> getSearchActivities(String longitude, String latitude, String townAdCode, int page, String uid, String activitiesType) {
        return mRetrofitService.getSearchActivities(longitude, latitude, townAdCode, page, uid, activitiesType);
    }

    /**
     * 商品预订
     * @param commodityId
     * @param type
     * @param count
     * @param shopId
     * @param uid
     * @return
     */
    public Observable<UserInfoEntity> getSubmitReserveInfo(String commodityId, String type, String count, String shopId, String uid) {
        return mRetrofitService.getSubmitReserveInfo(commodityId, type, count, shopId, uid);
    }

    public Observable<OrderEntity> getSearchOrders(String orderState, int page, String uid) {
        return mRetrofitService.getSearchOrders(orderState, page, uid);
    }

    public Observable<UserInfoEntity> getSubmitOperationOrder(String orderId, String type, String uid) {
        return mRetrofitService.getSubmitOperationOrder(orderId, type, uid);
    }

    public Observable<MineEntity> getSearchMineInfos(String longitude, String latitude, String townAdCode, int page, String uid) {
        return mRetrofitService.getSearchMineInfos(longitude, latitude, townAdCode, page, uid);
    }

    public Observable<UserInfoEntity> getSearchModifyUserIconInfo(String uid, MultipartBody.Part body) {
        return mRetrofitService.getSearchModifyUserIconInfo(uid, body);
    }

    public Observable<UserInfoEntity> getSearchModifyUserInfo(String uid, String userSex, String userName, String userBirthday, String userIdCartNumber, String userAddress) {
        return mRetrofitService.getSearchModifyUserInfo(uid, userSex, userName, userBirthday, userIdCartNumber, userAddress);
    }

    public Observable<GroupEntity> getSearchGroupInfos(String uid) {
        return mRetrofitService.getSearchGroupInfos(uid);
    }

    public Observable<UserInfoEntity> getSearchSignIns(String uid) {
        return mRetrofitService.getSearchSignIns(uid);
    }

    public Observable<UserInfoEntity> getSearchShare(String uid) {
        return mRetrofitService.getSearchShare(uid);
    }

    public Observable<CouponEntity> getSearchCouponCenter(String uid, String businessId, String couponType, String longitude, String latitude, String townAdCode, int page) {
        return mRetrofitService.getSearchCouponCenter(uid, businessId, couponType, longitude, latitude, townAdCode, page);
    }

    public Observable<UserInfoEntity> getCoupon(String uid, String couponId) {
        return mRetrofitService.getCoupon(uid, couponId);
    }

    public Observable<CouponEntity> getSearchMyCoupon(String uid, String businessId, String couponType, String longitude, String latitude, String townAdCode, int page) {
        return mRetrofitService.getSearchMyCoupon(uid, businessId, couponType, longitude, latitude, townAdCode, page);
    }

    public Observable<MessageEntity> getSearchMessages(String uid, String type, int page) {
        return mRetrofitService.getSearchMessages(uid, type, page);
    }

    public Observable<MyFootPrintEntity> getSearchMyFootPrint(String uid, int page) {
        return mRetrofitService.getSearchMyFootPrint(uid, page);
    }

    public Observable<ActivitiesDetailsEntity> getSearchActivitiesInfo(String uid, String marketId,String advId) {
        return mRetrofitService.getSearchActivitiesInfo(uid, marketId,advId);
    }

    public Observable<UserInfoEntity> getSearchActivitiesSignUp(String uid, String marketId,String advId, String name, String phone, String idCard, String type) {
        return mRetrofitService.getSearchActivitiesSignUp(uid, marketId,advId, name, phone, idCard, type);
    }

    public Observable<UserInfoEntity> getSearchCancelSignUp(String uid, String marketId, String advId,String type) {
        return mRetrofitService.getSearchCancelSignUp(uid, marketId, advId, type);
    }

    public Observable<UserInfoEntity> getSubmitIsCollection(String uid, String activitiesId, String type, String isCollection) {
        return mRetrofitService.getSubmitIsCollection(uid, activitiesId, type, isCollection);
    }
    // 检查版本更新
    public Observable<UpdateEntity> getUpdate() {
        return mRetrofitService.getUpdate();
    }

    // 点击量
    public Observable<UserInfoEntity> getClickCount(String json) {
        return mRetrofitService.getClickCount(json);
    }

    public Observable<UserInfoEntity> upLoadUUId(String systemName,String uuid,String uid) {
        return mRetrofitService.upLoadUUId(systemName,uuid,uid);
    }
}
