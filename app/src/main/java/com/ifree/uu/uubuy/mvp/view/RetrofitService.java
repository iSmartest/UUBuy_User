package com.ifree.uu.uubuy.mvp.view;

import com.ifree.uu.uubuy.config.BaseUrl;
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
import com.ifree.uu.uubuy.mvp.entity.UpdateEntity;
import com.ifree.uu.uubuy.mvp.entity.UserInfoEntity;

import okhttp3.MultipartBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
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
    @GET(BaseUrl.SEND_CODE)
    Observable<UserInfoEntity> getSearchSendCode(@Query("userPhone") String userPhone,
                                                 @Query("codeType") String codeType);

    @GET(BaseUrl.USER_REGISTER)
    Observable<UserInfoEntity> getSearchRegister(@Query("userPhone") String userPhone,
                                                 @Query("password") String password,
                                                 @Query("code") String code,
                                                 @Query("sessionId") String sessionId);

    @GET(BaseUrl.BIND_PHONE)
    Observable<UserInfoEntity> getSearchBindPhone(@Query("userPhone") String userPhone,
                                                  @Query("userPassword") String password,
                                                  @Query("code") String code,
                                                  @Query("sessionId") String sessionId,
                                                  @Query("uid") String uid,
                                                  @Query("type") String type);

    @GET(BaseUrl.MODIFY_PHONE)
    Observable<UserInfoEntity> getSearchModifyPhone(@Query("newphone") String newphone,
                                                    @Query("userPassword") String userPassword,
                                                    @Query("code") String code,
                                                    @Query("sessionId") String sessionId,
                                                    @Query("uid") String uid);

    @GET(BaseUrl.MODIFY_PASSWORD)
    Observable<UserInfoEntity> getSearchModifyPassword(@Query("userPhone") String userPhone,
                                                       @Query("oldPassword") String oldPassword,
                                                       @Query("newPassword") String newPassword,
                                                       @Query("code") String code,
                                                       @Query("sessionId") String sessionId,
                                                       @Query("uid") String uid);

    @GET(BaseUrl.FORGET_PASSWORD)
    Observable<UserInfoEntity> getSearchForgetPassword(@Query("userPhone") String userPhone,
                                                       @Query("password") String password,
                                                       @Query("code") String code,
                                                       @Query("sessionId") String sessionId);

    @GET(BaseUrl.SEND_CODE_LOGIN)
    Observable<UserInfoEntity> getSearchPhoneCodeLogin(@Query("userPhone") String userPhone,
                                                       @Query("code") String code,
                                                       @Query("sessionId") String sessionId);

    @GET(BaseUrl.USER_PASSWORD_LOGIN)
    Observable<UserInfoEntity> getSearchPassWordLogin(@Query("userPhone") String userPhone,
                                                      @Query("password") String password);

    @GET(BaseUrl.THIRD_LOGIN)
    Observable<UserInfoEntity> getSearchThirdLogin(@Query("thirdUid") String thirdUid,
                                                   @Query("nickName") String nickName,
                                                   @Query("userIcon") String userIcon,
                                                   @Query("type") String type);

    @GET(BaseUrl.CITY_INFO)
    Observable<CityInfoEntity> getSearchCityInfo();

    @GET(BaseUrl.HOT_KEY_WORD)
    Observable<HotKeyWordEntity> getSearchHotKeyword();

    @FormUrlEncoded
    @POST(BaseUrl.SEARCH_INFO)
    Observable<SearchEntity> getSearchInfo(@Query("longitude") String longitude,
                                           @Query("latitude") String latitude,
                                           @Query("townAdCode") String townAdCode,
                                           @Query("page") int page,
                                           @Field("keyWord") String keyWord,
                                           @Query("uid") String uid,
                                           @Query("searchType") String searchType);
    @FormUrlEncoded
    @POST(BaseUrl.HOME_INFO)
    Observable<HomeEntity> getSearchHomes(@Query("longitude") String longitude,
                                          @Query("latitude") String latitude,
                                          @Query("townAdCode") String townAdCode,
                                          @Field("city") String city,
                                          @Query("page") int page);

    @GET(BaseUrl.CLASSIFY_LIST_INFO)
    Observable<FirstClassifyEntity> getSearchClassifyListInfo(@Query("longitude") String longitude,
                                                              @Query("latitude") String latitude,
                                                              @Query("townAdCode") String townAdCode,
                                                              @Query("adTypeId") String adTypeId,
                                                              @Query("type") String type,
                                                              @Query("menuId") String menuId,
                                                              @Query("page") int page,
                                                              @Query("uid") String uid);

    @GET(BaseUrl.COMMODITY_LIST_INFO)
    Observable<CommodityListEntity> getSearchCommodityListInfo(@Query("storeId") String storeId,
                                                               @Query("page") int page,
                                                               @Query("uid") String uid);

    @GET(BaseUrl.MORE_LIST_INFO)
    Observable<MoreEntity> getSearchMoreListInfo(@Query("classfyId") String classifyId,
                                                 @Query("type") String type,
                                                 @Query("menuId") String menuId,
                                                 @Query("uid") String uid,
                                                 @Query("page") int page);

    @GET(BaseUrl.COMMODITY_INFO)
    Observable<CommodityInfoEntity> getSearchCommodityInfo(@Query("commodityId") String commodityId,
                                                           @Query("type") String type,
                                                           @Query("uid") String uid);

    @GET(BaseUrl.COPARE_INFO)
    Observable<CompareCommodityEntity> getSearchCompareInfo(@Query("commodityId") String commodityId,
                                                            @Query("page") int page);

    @GET(BaseUrl.SECOND_LIST_INFO)
    Observable<SecondActivitiesEntity> getSearchSecondListInfo(@Query("fristActivitiesId") String fristActivitiesId,
                                                               @Query("page") int page,
                                                               @Query("uid") String uid,
                                                               @Query("fristActivitiesType") String fristActivitiesType,
                                                               @Query("classfy") String classify);

    @GET(BaseUrl.AROUND_INFO)
    Observable<AroundEntity> getSearchArounds(@Query("longitude") String longitude,
                                              @Query("latitude") String latitude,
                                              @Query("townAdCode") String townAdCode,
                                              @Query("page") int page,
                                              @Query("uid") String uid);

    @GET(BaseUrl.ACTIVITIES_CIRCLE_INFO)
    Observable<ActivitiesEntity> getSearchActivities(@Query("longitude") String longitude,
                                                     @Query("latitude") String latitude,
                                                     @Query("townAdCode") String townAdCode,
                                                     @Query("page") int page,
                                                     @Query("uid") String uid,
                                                     @Query("activitiesType") String activitiesType);

    @GET(BaseUrl.SUBMIT_RESERVE_INFO)
    Observable<UserInfoEntity> getSubmitReserveInfo(@Query("commodityId") String commodityId,
                                                    @Query("type") String type,
                                                    @Query("count") String count,
                                                    @Query("shopId") String shopId,
                                                    @Query("uid") String uid);

    @GET(BaseUrl.ORDER_INFO)
    Observable<OrderEntity> getSearchOrders(@Query("orderState") String orderState,
                                            @Query("page") int page,
                                            @Query("uid") String uid);

    @GET(BaseUrl.SUBNIT_OPERATION_ORDER)
    Observable<UserInfoEntity> getSubmitOperationOrder(@Query("orderId") String orderId,
                                                       @Query("type") String type,
                                                       @Query("uid") String uid);

    @GET(BaseUrl.MINE_INFO)
    Observable<MineEntity> getSearchMineInfos(@Query("longitude") String longitude,
                                              @Query("latitude") String latitude,
                                              @Query("townAdCode") String townAdCode,
                                              @Query("page") int page,
                                              @Query("uid") String uid);

    @Multipart
    @POST(BaseUrl.MODIFY_USER_INFO)
    Observable<UserInfoEntity> getSearchModifyUserIconInfo(@Query("uid") String uid,
                                                           @Part MultipartBody.Part body);

    @FormUrlEncoded
    @POST(BaseUrl.MODIFY_USER_INFO)
    Observable<UserInfoEntity> getSearchModifyUserInfo(@Query("uid") String uid,
                                                       @Query("userSex") String userSex,
                                                       @Field("userName") String userName,
                                                       @Query("userBirthday") String userBirthday,
                                                       @Query("idCard") String userIdCartNumber,
                                                       @Field("address") String userAddress);

    @GET(BaseUrl.GROUP_INFO)
    Observable<GroupEntity> getSearchGroupInfos(@Query("uid") String uid);

    @GET(BaseUrl.SIGN_IN)
    Observable<UserInfoEntity> getSearchSignIns(@Query("uid") String uid);

    @GET(BaseUrl.SHARE)
    Observable<UserInfoEntity> getSearchShare(@Query("uid") String uid);

    @GET(BaseUrl.COUPON_CENTER)
    Observable<CouponEntity> getSearchCouponCenter(@Query("uid") String uid,
                                                   @Query("businessId") String businessId,
                                                   @Query("couponType") String couponType,
                                                   @Query("longitude") String longitude,
                                                   @Query("latitude") String latitude,
                                                   @Query("townAdCode") String townAdCode,
                                                   @Query("page") int page);

    @GET(BaseUrl.GET_COUPON)
    Observable<UserInfoEntity> getCoupon(@Query("uid") String uid,
                                         @Query("couponId") String businessId);

    @GET(BaseUrl.MY_COUPON)
    Observable<CouponEntity> getSearchMyCoupon(@Query("uid") String uid,
                                               @Query("businessId") String businessId,
                                               @Query("couponType") String couponType,
                                               @Query("longitude") String longitude,
                                               @Query("latitude") String latitude,
                                               @Query("townAdCode") String townAdCode,
                                               @Query("page") int page);

    @GET(BaseUrl.MESSAGE_INFO)
    Observable<MessageEntity> getSearchMessages(@Query("uid") String uid,
                                                @Query("type") String type,
                                                @Query("page") int page);

    @GET(BaseUrl.FOOT_PRINT)
    Observable<MyFootPrintEntity> getSearchMyFootPrint(@Query("uid") String uid,
                                                       @Query("page") int page);

    @GET(BaseUrl.ACTIVITIES_INFO)
    Observable<ActivitiesDetailsEntity> getSearchActivitiesInfo(@Query("uid") String uid,
                                                                @Query("marketId") String marketId);
    @FormUrlEncoded
    @POST(BaseUrl.SIGN_UP)
    Observable<UserInfoEntity> getSearchActivitiesSignUp(@Query("uid") String uid,
                                                         @Query("marketId") String marketId,
                                                         @Field("name") String name,
                                                         @Query("phone") String phone,
                                                         @Query("idCard") String idCard,
                                                         @Query("type") String type);

    @GET(BaseUrl.CANCEL_SIGN_UP)
    Observable<UserInfoEntity> getSearchCancelSignUp(@Query("uid") String uid,
                                                     @Query("marketId") String marketId,
                                                     @Query("type") String type);

    @GET(BaseUrl.SUBMIT_IS_COLLECTION)
    Observable<UserInfoEntity> getSubmitIsCollection(@Query("uid") String uid,
                                                     @Query("activitiesId") String activitiesId,
                                                     @Query("type") String type,
                                                     @Query("isCollection") String isCollection);
    //版本更新
    @GET(BaseUrl.UPDATE)
    Observable<UpdateEntity> getUpdate();

    //版本更新
    @GET(BaseUrl.CLICK_COUNT)
    Observable<UserInfoEntity> getClickCount(@Query("json") String json);
}
