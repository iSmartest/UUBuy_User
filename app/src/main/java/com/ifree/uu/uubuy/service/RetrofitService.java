package com.ifree.uu.uubuy.service;

import com.ifree.uu.uubuy.config.BaseUrl;
import com.ifree.uu.uubuy.service.entity.ActivitiesEntity;
import com.ifree.uu.uubuy.service.entity.ActivitiesDetailsEntity;
import com.ifree.uu.uubuy.service.entity.AroundEntity;
import com.ifree.uu.uubuy.service.entity.CityInfoEntity;
import com.ifree.uu.uubuy.service.entity.CommodityInfoEntity;
import com.ifree.uu.uubuy.service.entity.CommodityListEntity;
import com.ifree.uu.uubuy.service.entity.CompareCommodityEntity;
import com.ifree.uu.uubuy.service.entity.CouponEntity;
import com.ifree.uu.uubuy.service.entity.FirstClassifyEntity;
import com.ifree.uu.uubuy.service.entity.GroupEntity;
import com.ifree.uu.uubuy.service.entity.HomeEntity;
import com.ifree.uu.uubuy.service.entity.MessageEntity;
import com.ifree.uu.uubuy.service.entity.MineEntity;
import com.ifree.uu.uubuy.service.entity.MoreEntity;
import com.ifree.uu.uubuy.service.entity.MyFootPrintEntity;
import com.ifree.uu.uubuy.service.entity.OrderEntity;
import com.ifree.uu.uubuy.service.entity.SearchEntity;
import com.ifree.uu.uubuy.service.entity.SecondActivitiesEntity;
import com.ifree.uu.uubuy.service.entity.UserInfoEntity;

import okhttp3.MultipartBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/13.
 * Description:
 */
public interface RetrofitService {
    @POST(BaseUrl.SEND_CODE)
    Observable<UserInfoEntity> getSearchSendCode(@Query("userPhone") String userPhone,
                                                 @Query("codeType") String codeType);

    @POST(BaseUrl.USER_REGISTER)
    Observable<UserInfoEntity> getSearchRegister(@Query("userPhone") String userPhone,
                                                 @Query("password") String password,
                                                 @Query("code") String code,
                                                 @Query("sessionId") String sessionId);

    @POST(BaseUrl.USER_REGISTER)
    Observable<UserInfoEntity> getSearchBindPhone(@Query("userPhone") String userPhone,
                                                  @Query("userPassword") String password,
                                                  @Query("code") String code,
                                                  @Query("sessionId") String sessionId,
                                                  @Query("uid") String uid);

    @POST(BaseUrl.FORGET_PASSWORD)
    Observable<UserInfoEntity> getSearchForgetPassword(@Query("userPhone") String userPhone,
                                                       @Query("password") String password,
                                                       @Query("code") String code);

    @POST(BaseUrl.SEND_CODE_LOGIN)
    Observable<UserInfoEntity> getSearchPhoneCodeLogin(@Query("userPhone") String userPhone,
                                                       @Query("code") String code,
                                                       @Query("sessionId") String sessionId);

    @POST(BaseUrl.USER_PASSWORD_LOGIN)
    Observable<UserInfoEntity> getSearchPassWordLogin(@Query("userPhone") String userPhone,
                                                      @Query("password") String password);

    @POST(BaseUrl.THIRD_LOGIN)
    Observable<UserInfoEntity> getSearchThirdLogin(@Query("thirdUid") String thirdUid,
                                                   @Query("nickName") String nickName,
                                                   @Query("userIcon") String userIcon,
                                                   @Query("type") String type);

    @POST(BaseUrl.CITY_INFO)
    Observable<CityInfoEntity> getSearchCityInfo();


    @POST(BaseUrl.SEARCH_INFO)
    Observable<SearchEntity> getSearchInfo(@Query("longitude") String longitude,
                                           @Query("latitude") String latitude,
                                           @Query("townAdCode") String townAdCode,
                                           @Query("page") int page,
                                           @Query("keyWord") String keyWord,
                                           @Query("uid") String uid,
                                           @Query("searchType") String searchType);

    @POST(BaseUrl.HOME_INFO)
    Observable<HomeEntity> getSearchHomes(@Query("longitude") String longitude,
                                          @Query("latitude") String latitude,
                                          @Query("townAdCode") String townAdCode,
                                          @Query("page") int page);

    @POST(BaseUrl.CLASSIFY_LIST_INFO)
    Observable<FirstClassifyEntity> getSearchClassifyListInfo(@Query("longitude") String longitude,
                                                              @Query("latitude") String latitude,
                                                              @Query("townAdCode") String townAdCode,
                                                              @Query("adTypeId") String adTypeId,
                                                              @Query("type") String type,
                                                              @Query("menuId") String menuId,
                                                              @Query("page") int page,
                                                              @Query("uid") String uid);

    @POST(BaseUrl.COMMODITY_LIST_INFO)
    Observable<CommodityListEntity> getSearchCommodityListInfo(@Query("storeId") String storeId,
                                                               @Query("page") int page,
                                                               @Query("uid") String uid);

    @POST(BaseUrl.MORE_LIST_INFO)
    Observable<MoreEntity> getSearchMoreListInfo(@Query("classfyId") String classifyId,
                                                 @Query("type") String type,
                                                 @Query("menuId") String menuId,
                                                 @Query("uid") String uid,
                                                 @Query("page") int page);

    @POST(BaseUrl.COMMODITY_INFO)
    Observable<CommodityInfoEntity> getSearchCommodityInfo(@Query("commodityId") String commodityId,
                                                           @Query("type") String type,
                                                           @Query("uid") String uid);

    @POST(BaseUrl.COPARE_INFO)
    Observable<CompareCommodityEntity> getSearchCompareInfo(@Query("commodityId") String commodityId,
                                                            @Query("page") int page);

    @POST(BaseUrl.SECOND_LIST_INFO)
    Observable<SecondActivitiesEntity> getSearchSecondListInfo(@Query("fristActivitiesId") String fristActivitiesId,
                                                               @Query("page") int page,
                                                               @Query("uid") String uid,
                                                               @Query("fristActivitiesType") String fristActivitiesType,
                                                               @Query("classfy") String classify);

    @POST(BaseUrl.AROUND_INFO)
    Observable<AroundEntity> getSearchArounds(@Query("longitude") String longitude,
                                              @Query("latitude") String latitude,
                                              @Query("townAdCode") String townAdCode,
                                              @Query("page") int page,
                                              @Query("uid") String uid);

    @POST(BaseUrl.ACTIVITIES_CIRCLE_INFO)
    Observable<ActivitiesEntity> getSearchActivities(@Query("longitude") String longitude,
                                                     @Query("latitude") String latitude,
                                                     @Query("townAdCode") String townAdCode,
                                                     @Query("page") int page,
                                                     @Query("uid") String uid,
                                                     @Query("activitiesType") String activitiesType);

    @POST(BaseUrl.SUBMIT_RESERVE_INFO)
    Observable<UserInfoEntity> getSubmitReserveInfo(@Query("commodityId") String commodityId,
                                                    @Query("type") String type,
                                                    @Query("count") String count,
                                                    @Query("shopId") String shopId,
                                                    @Query("uid") String uid);

    @POST(BaseUrl.ORDER_INFO)
    Observable<OrderEntity> getSearchOrders(@Query("orderState") String orderState,
                                            @Query("page") int page,
                                            @Query("uid") String uid);

    @POST(BaseUrl.SUBNIT_OPERATION_ORDER)
    Observable<UserInfoEntity> getSubmitOperationOrder(@Query("orderId") String orderId,
                                                       @Query("type") String type,
                                                       @Query("uid") String uid);

    @POST(BaseUrl.MINE_INFO)
    Observable<MineEntity> getSearchMineInfos(@Query("longitude") String longitude,
                                              @Query("latitude") String latitude,
                                              @Query("townAdCode") String townAdCode,
                                              @Query("page") int page,
                                              @Query("uid") String uid);

    @Multipart
    @POST(BaseUrl.MODIFY_USER_INFO)
    Observable<UserInfoEntity> getSearchModifyUserIconInfo(@Query("uid") String uid,
                                                           @Part MultipartBody.Part body);


    @POST(BaseUrl.MODIFY_USER_INFO)
    Observable<UserInfoEntity> getSearchModifyUserInfo(@Query("uid") String uid,
                                                       @Query("userSex") String userSex,
                                                       @Query("userName") String userName,
                                                       @Query("userBirthday") String userBirthday,
                                                       @Query("idCard") String userIdCartNumber,
                                                       @Query("address") String userAddress);

    @POST(BaseUrl.GROUP_INFO)
    Observable<GroupEntity> getSearchGroupInfos(@Query("uid") String uid);

    @POST(BaseUrl.SIGN_IN)
    Observable<UserInfoEntity> getSearchSignIns(@Query("uid") String uid);

    @POST(BaseUrl.COUPON_CENTER)
    Observable<CouponEntity> getSearchCouponCenter(@Query("uid") String uid,
                                                   @Query("businessId") String businessId,
                                                   @Query("couponType") String couponType,
                                                   @Query("longitude") String longitude,
                                                   @Query("latitude") String latitude,
                                                   @Query("townAdCode") String townAdCode,
                                                   @Query("page") int page);

    @POST(BaseUrl.MY_COUPON)
    Observable<CouponEntity> getSearchMyCoupon(@Query("uid") String uid,
                                               @Query("businessId") String businessId,
                                               @Query("couponType") String couponType,
                                               @Query("longitude") String longitude,
                                               @Query("latitude") String latitude,
                                               @Query("townAdCode") String townAdCode,
                                               @Query("page") int page);

    @POST(BaseUrl.MESSAGE_INFO)
    Observable<MessageEntity> getSearchMessages(@Query("uid") String uid,
                                                @Query("type") String type,
                                                @Query("page") int page);

    @POST(BaseUrl.FOOT_PRINT)
    Observable<MyFootPrintEntity> getSearchMyFootPrint(@Query("uid") String uid,
                                                       @Query("page") int page);

    @POST(BaseUrl.ACTIVITIES_INFO)
    Observable<ActivitiesDetailsEntity> getSearchActivitiesInfo(@Query("uid") String uid,
                                                                @Query("marketId") String marketId);

    @POST(BaseUrl.SIGN_UP)
    Observable<UserInfoEntity> getSearchActivitiesSignUp(@Query("uid") String uid,
                                                         @Query("marketId") String marketId,
                                                         @Query("name") String name,
                                                         @Query("phone") String phone,
                                                         @Query("idCard") String idCard,
                                                         @Query("type") String type);

    @POST(BaseUrl.SUBMIT_IS_COLLECTION)
    Observable<UserInfoEntity> getSubmitIsCollection(@Query("uid") String uid,
                                                     @Query("activitiesId") String activitiesId,
                                                     @Query("type") String type,
                                                     @Query("isCollection") String isCollection);
}
