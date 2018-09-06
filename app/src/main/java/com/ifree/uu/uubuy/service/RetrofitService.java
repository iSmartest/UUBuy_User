package com.ifree.uu.uubuy.service;

import com.ifree.uu.uubuy.config.BaseUrl;
import com.ifree.uu.uubuy.service.entity.ActivitiesEntity;
import com.ifree.uu.uubuy.service.entity.AroundEntity;
import com.ifree.uu.uubuy.service.entity.CityInfoEntity;
import com.ifree.uu.uubuy.service.entity.GroupEntity;
import com.ifree.uu.uubuy.service.entity.HomeEntity;
import com.ifree.uu.uubuy.service.entity.MessageEntity;
import com.ifree.uu.uubuy.service.entity.MineEntity;
import com.ifree.uu.uubuy.service.entity.OrderEntity;
import com.ifree.uu.uubuy.service.entity.UserInfoEntity;

import retrofit2.http.GET;
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
                                                 @Query("type") String type);

    @GET(BaseUrl.USER_REGISTER)
    Observable<UserInfoEntity> getSearchRegister(@Query("userPhone") String userPhone,
                                                 @Query("password") String password,
                                                 @Query("code") String code);

    @GET(BaseUrl.FORGET_PASSWORD)
    Observable<UserInfoEntity> getSearchForgetPassword(@Query("userPhone") String userPhone,
                                                       @Query("password") String password,
                                                       @Query("code") String code);

    @GET(BaseUrl.SEND_CODE_LOGIN)
    Observable<UserInfoEntity> getSearchPhoneCodeLogin(@Query("userPhone") String userPhone,
                                                       @Query("code") String code);

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


    @GET(BaseUrl.HOME_INFO)
    Observable<HomeEntity> getSearchHomes(@Query("longitude") String longitude,
                                          @Query("latitude") String latitude,
                                          @Query("townAdCode") String townAdCode,
                                          @Query("page") int page,
                                          @Query("uid") String uid);

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

    @GET(BaseUrl.ORDER_INFO)
    Observable<OrderEntity> getSearchOrders(@Query("orderState") String orderState,
                                            @Query("page") int page,
                                            @Query("uid") String uid);

    @GET(BaseUrl.MINE_INFO)
    Observable<MineEntity> getSearchMineInfos(@Query("longitude") String longitude,
                                              @Query("latitude") String latitude,
                                              @Query("townAdCode") String townAdCode,
                                              @Query("page") int page,
                                              @Query("uid") String uid);

    @GET(BaseUrl.GROUP_INFO)
    Observable<GroupEntity> getSearchGroupInfos(@Query("uid") String uid);

    @GET(BaseUrl.MESSAGE_INFO)
    Observable<MessageEntity> getSearchMessages(@Query("uid") String uid,
                                                @Query("type") String type,
                                                @Query("page") int page);
}
