package com.ifree.uu.uubuy.mvp.view;

import com.ifree.uu.uubuy.config.BaseUrl;
import com.ifree.uu.uubuy.mvp.entity.ActivitiesDetailsEntity;
import com.ifree.uu.uubuy.mvp.entity.ActivitiesEntity;
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
    /**
     * 获取验证码
     * @param userPhone 用户手机号
     * @param codeType 验证码类型（1:登录 2:注册 3:手机号登录 4:找回密码 5:绑定密码 6:修改密码 7:更改手机号）
     * @return
     */
    @GET(BaseUrl.SEND_CODE)
    Observable<UserInfoEntity> getSearchSendCode(@Query("userPhone") String userPhone,
                                                 @Query("codeType") String codeType);

    /**
     * 注册
     * @param userPhone 用户手机号
     * @param password 用户密码
     * @param code 六位验证码
     * @param sessionId 验证码ID
     * @return
     */
    @GET(BaseUrl.USER_REGISTER)
    Observable<UserInfoEntity> getSearchRegister(@Query("userPhone") String userPhone,
                                                 @Query("password") String password,
                                                 @Query("code") String code,
                                                 @Query("sessionId") String sessionId);
    /**
     * 绑定手机号
     * @param userPhone 用户手机号
     * @param password 用户密码
     * @param code 六位验证码
     * @param sessionId 验证码ID
     * @param uid 用户ID
     * @param type 0:微信 1:QQ
     * @return
     */
    @GET(BaseUrl.BIND_PHONE)
    Observable<UserInfoEntity> getSearchBindPhone(@Query("userPhone") String userPhone,
                                                  @Query("userPassword") String password,
                                                  @Query("code") String code,
                                                  @Query("sessionId") String sessionId,
                                                  @Query("uid") String uid,
                                                  @Query("type") String type);

    /**
     * 更改手机号
     * @param newPhone 新手机号
     * @param userPassword 用户密码
     * @param code 六位验证码
     * @param sessionId 验证码ID
     * @param uid 用户ID
     * @return
     */
    @GET(BaseUrl.MODIFY_PHONE)
    Observable<UserInfoEntity> getSearchModifyPhone(@Query("newphone") String newPhone,
                                                    @Query("userPassword") String userPassword,
                                                    @Query("code") String code,
                                                    @Query("sessionId") String sessionId,
                                                    @Query("uid") String uid);

    /**
     * 更改登录密码
     * @param userPhone 用户手机号
     * @param oldPassword 原密码
     * @param newPassword 新密码
     * @param code 六位验证码
     * @param sessionId 验证码ID
     * @param uid 用户ID
     * @return
     */
    @GET(BaseUrl.MODIFY_PASSWORD)
    Observable<UserInfoEntity> getSearchModifyPassword(@Query("userPhone") String userPhone,
                                                       @Query("oldPassword") String oldPassword,
                                                       @Query("newPassword") String newPassword,
                                                       @Query("code") String code,
                                                       @Query("sessionId") String sessionId,
                                                       @Query("uid") String uid);

    /**
     * 忘记密码
     * @param userPhone 用户手机号
     * @param password 密码
     * @param code 六位验证码
     * @param sessionId 验证码ID
     * @return
     */
    @GET(BaseUrl.FORGET_PASSWORD)
    Observable<UserInfoEntity> getSearchForgetPassword(@Query("userPhone") String userPhone,
                                                       @Query("password") String password,
                                                       @Query("code") String code,
                                                       @Query("sessionId") String sessionId);

    /**
     * 手机号验证码登录
     * @param userPhone 用户手机号
     * @param code 六位验证码
     * @param sessionId 验证码ID
     * @return
     */
    @GET(BaseUrl.SEND_CODE_LOGIN)
    Observable<UserInfoEntity> getSearchPhoneCodeLogin(@Query("userPhone") String userPhone,
                                                       @Query("code") String code,
                                                       @Query("sessionId") String sessionId);

    /**
     * 密码登录
     * @param userPhone 用户手机号
     * @param password 密码
     * @return
     */
    @GET(BaseUrl.USER_PASSWORD_LOGIN)
    Observable<UserInfoEntity> getSearchPassWordLogin(@Query("userPhone") String userPhone,
                                                      @Query("password") String password);

    /**
     * 第三方登录
     * @param thirdUid 三方ID
     * @param nickName 三方昵称
     * @param userIcon 三方头像
     * @param type 0:微信 1:QQ
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUrl.THIRD_LOGIN)
    Observable<UserInfoEntity> getSearchThirdLogin(@Query("thirdUid") String thirdUid,
                                                   @Field("nickName") String nickName,
                                                   @Query("userIcon") String userIcon,
                                                   @Query("type") String type);

    /**
     * 获取开通城市
     * @return
     */
    @GET(BaseUrl.CITY_INFO)
    Observable<CityInfoEntity> getSearchCityInfo();

    /**
     * 热词
     * @return
     */
    @GET(BaseUrl.HOT_KEY_WORD)
    Observable<HotKeyWordEntity> getSearchHotKeyword();

    /**
     * 搜索
     * @param longitude 纬度
     * @param latitude 经度
     * @param townAdCode 区县编码
     * @param page 当前页数，用于刷新
     * @param keyWord 关键词
     * @param uid 用户ID
     * @param searchType 0:全部 1:商城 2:商铺 3:商品
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUrl.SEARCH_INFO)
    Observable<SearchEntity> getSearchInfo(@Query("longitude") String longitude,
                                           @Query("latitude") String latitude,
                                           @Query("townAdCode") String townAdCode,
                                           @Query("page") int page,
                                           @Field("keyWord") String keyWord,
                                           @Query("uid") String uid,
                                           @Query("searchType") String searchType);

    /**
     * 首页
     * @param longitude 纬度
     * @param latitude 经度
     * @param townAdCode 区县编码
     * @param city 城市
     * @param page 当前页数，用于刷新
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUrl.HOME_INFO)
    Observable<HomeEntity> getSearchHomes(@Query("longitude") String longitude,
                                          @Query("latitude") String latitude,
                                          @Query("townAdCode") String townAdCode,
                                          @Field("city") String city,
                                          @Query("page") int page);

    /**
     * 商场（第一级）
     * @param longitude 纬度
     * @param latitude 经度
     * @param townAdCode 区县编码
     * @param adTypeId 大类ID（如:综合商城）
     * @param type 大类类型
     * @param menuId 分类ID
     * @param page 当前页数，用于刷新
     * @param uid 用户ID
     * @return
     */
    @GET(BaseUrl.CLASSIFY_LIST_INFO)
    Observable<FirstClassifyEntity> getSearchClassifyListInfo(@Query("longitude") String longitude,
                                                              @Query("latitude") String latitude,
                                                              @Query("townAdCode") String townAdCode,
                                                              @Query("adTypeId") String adTypeId,
                                                              @Query("type") String type,
                                                              @Query("menuId") String menuId,
                                                              @Query("page") int page,
                                                              @Query("uid") String uid);

    /**
     * 商品（第三级）
     * @param storeId 商铺ID
     * @param page 当前页数，用于刷新
     * @param uid 用户ID
     * @return
     */
    @GET(BaseUrl.COMMODITY_LIST_INFO)
    Observable<CommodityListEntity> getSearchCommodityListInfo(@Query("storeId") String storeId,
                                                               @Query("page") int page,
                                                               @Query("uid") String uid);

    /**
     * 更多
     * @param classifyId 大类ID
     * @param type 所属类型
     * @param menuId 分类ID
     * @param uid 用户ID
     * @param page 当前页数，用于刷新
     * @return
     */
    @GET(BaseUrl.MORE_LIST_INFO)
    Observable<MoreEntity> getSearchMoreListInfo(@Query("classfyId") String classifyId,
                                                 @Query("type") String type,
                                                 @Query("menuId") String menuId,
                                                 @Query("uid") String uid,
                                                 @Query("page") int page);

    /**
     * 商品详情
     * @param commodityId 商品ID
     * @param type 商品所属类型
     * @param uid 用户ID
     * @return
     */
    @GET(BaseUrl.COMMODITY_INFO)
    Observable<CommodityInfoEntity> getSearchCommodityInfo(@Query("commodityId") String commodityId,
                                                           @Query("type") String type,
                                                           @Query("uid") String uid);

    /**
     * 同类竞品
     * @param commodityId 商品ID
     * @param page 当前页数，用于刷新
     * @return
     */
    @GET(BaseUrl.COMPARE_INFO)
    Observable<CompareCommodityEntity> getSearchCompareInfo(@Query("commodityId") String commodityId,
                                                            @Query("page") int page);

    /**
     * 店铺（第二级）
     * @param floor 楼层
     * @param firstActivitiesId 活动ID
     * @param page 当前页数，用于刷新
     * @param uid 用户ID
     * @param firstActivitiesType 活动所属类型
     * @param classify 活动所属商城分类类型
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUrl.SECOND_LIST_INFO)
    Observable<SecondActivitiesEntity> getSearchSecondListInfo(@Field("floor") String floor,
                                                               @Query("fristActivitiesId") String firstActivitiesId,
                                                               @Query("page") int page,
                                                               @Query("uid") String uid,
                                                               @Query("fristActivitiesType") String firstActivitiesType,
                                                               @Query("classfy") String classify);

    /**
     * 周围
     * @param longitude 纬度
     * @param latitude 经度
     * @param townAdCode 区县编码
     * @param page 当前页数，用于刷新
     * @param uid 用户ID
     * @return
     */
    @GET(BaseUrl.AROUND_INFO)
    Observable<AroundEntity> getSearchArounds(@Query("longitude") String longitude,
                                              @Query("latitude") String latitude,
                                              @Query("townAdCode") String townAdCode,
                                              @Query("page") int page,
                                              @Query("uid") String uid);

    /**
     * 生活圈
     * @param longitude 纬度
     * @param latitude 经度
     * @param townAdCode 区县编码
     * @param page 当前页数，用于刷新
     * @param uid 用户ID
     * @param activitiesType 0:商城 1:店铺 2:商品
     * @return
     */
    @GET(BaseUrl.ACTIVITIES_CIRCLE_INFO)
    Observable<ActivitiesEntity> getSearchActivities(@Query("longitude") String longitude,
                                                     @Query("latitude") String latitude,
                                                     @Query("townAdCode") String townAdCode,
                                                     @Query("page") int page,
                                                     @Query("uid") String uid,
                                                     @Query("activitiesType") String activitiesType);

    /**
     * 商品预订
     * @param commodityId 商品ID
     * @param type 店铺所属大类
     * @param count 预订商品数量
     * @param shopId 商铺ID
     * @param uid 用户ID
     * @return
     */
    @GET(BaseUrl.SUBMIT_RESERVE_INFO)
    Observable<UserInfoEntity> getSubmitReserveInfo(@Query("commodityId") String commodityId,
                                                    @Query("type") String type,
                                                    @Query("count") String count,
                                                    @Query("shopId") String shopId,
                                                    @Query("uid") String uid);
    /**
     * 订单
     * @param orderState 订单状态 （0:已预订,1:已完成,2:已取消）
     * @param page 当前页数，用于刷新
     * @param uid 用户ID
     * @return
     */
    @GET(BaseUrl.ORDER_INFO)
    Observable<OrderEntity> getSearchOrders(@Query("orderState") String orderState,
                                            @Query("page") int page,
                                            @Query("uid") String uid);

    /**
     * 订单取消、删除、恢复下单
     * @param orderId 订单号，唯一标识
     * @param type 操作类型：0取消预订，1删除订单，2恢复预订
     * @param uid 用户ID
     * @return
     */
    @GET(BaseUrl.SUBMIT_OPERATION_ORDER)
    Observable<UserInfoEntity> getSubmitOperationOrder(@Query("orderId") String orderId,
                                                       @Query("type") String type,
                                                       @Query("uid") String uid);

    /**
     * 我的
     * @param longitude 纬度
     * @param latitude 经度
     * @param townAdCode 区县编码
     * @param page 当前页数，用于刷新
     * @param uid 用户ID
     * @return
     */
    @GET(BaseUrl.MINE_INFO)
    Observable<MineEntity> getSearchMineInfos(@Query("longitude") String longitude,
                                              @Query("latitude") String latitude,
                                              @Query("townAdCode") String townAdCode,
                                              @Query("page") int page,
                                              @Query("uid") String uid);

    /**
     * 编辑用户资料-用户头像
     * @param uid 用户ID
     * @param body 用户头像
     * @return
     */
    @Multipart
    @POST(BaseUrl.MODIFY_USER_INFO)
    Observable<UserInfoEntity> getSearchModifyUserIconInfo(@Query("uid") String uid,
                                                           @Part MultipartBody.Part body);

    /**
     * 编辑用户资料
     * @param uid 用户ID
     * @param userSex 用户性别
     * @param userName 用户昵称
     * @param userBirthday 用户生日
     * @param userIdCartNumber 用户身份证号
     * @param userAddress 用户地址
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUrl.MODIFY_USER_INFO)
    Observable<UserInfoEntity> getSearchModifyUserInfo(@Query("uid") String uid,
                                                       @Query("userSex") String userSex,
                                                       @Field("userName") String userName,
                                                       @Query("userBirthday") String userBirthday,
                                                       @Query("idCard") String userIdCartNumber,
                                                       @Field("address") String userAddress);
    /**
     * 用户成长页面
     * @param uid 用户ID
     * @return
     */
    @GET(BaseUrl.GROUP_INFO)
    Observable<GroupEntity> getSearchGroupInfos(@Query("uid") String uid);

    /**
     * 签到
     * @param uid 用户ID
     * @return
     */
    @GET(BaseUrl.SIGN_IN)
    Observable<UserInfoEntity> getSearchSignIns(@Query("uid") String uid);

    /**
     * 分享
     * @param uid 用户ID
     * @return
     */
    @GET(BaseUrl.SHARE)
    Observable<UserInfoEntity> getSearchShare(@Query("uid") String uid);

    /**
     * 领券中心
     * @param uid 用户ID
     * @param businessId 商家ID(参数为空时，返回全部商家的)
     * @param couponType 优惠券类型 0：UU券，1：商城券，2：店铺券
     * @param longitude 纬度
     * @param latitude 经度
     * @param townAdCode 区县编码
     * @param page 当前页数，用于刷新
     * @return
     */
    @GET(BaseUrl.COUPON_CENTER)
    Observable<CouponEntity> getSearchCouponCenter(@Query("uid") String uid,
                                                   @Query("businessId") String businessId,
                                                   @Query("couponType") String couponType,
                                                   @Query("longitude") String longitude,
                                                   @Query("latitude") String latitude,
                                                   @Query("townAdCode") String townAdCode,
                                                   @Query("page") int page);

    /**
     * 领取优惠券
     * @param uid 用户ID
     * @param businessId 门店ID
     * @return
     */
    @GET(BaseUrl.GET_COUPON)
    Observable<UserInfoEntity> getCoupon(@Query("uid") String uid,
                                         @Query("couponId") String businessId);

    /**
     * 我的优惠券
     * @param uid 用户ID
     * @param businessId 门店ID
     * @param couponType 优惠券类型（0:未使用 1:已使用 2:已失效）
     * @param longitude 纬度
     * @param latitude 经度
     * @param townAdCode 区县编码
     * @param page 当前页数，用于刷新
     * @return
     */
    @GET(BaseUrl.MY_COUPON)
    Observable<CouponEntity> getSearchMyCoupon(@Query("uid") String uid,
                                               @Query("businessId") String businessId,
                                               @Query("couponType") String couponType,
                                               @Query("longitude") String longitude,
                                               @Query("latitude") String latitude,
                                               @Query("townAdCode") String townAdCode,
                                               @Query("page") int page);

    /**
     * 消息
     * @param uid 用户ID
     * @param type 0:通知 1:活动 2:消息（用户关注活动，平台发布为通知）
     * @param page 当前页数，用于刷新
     * @return
     */
    @GET(BaseUrl.MESSAGE_INFO)
    Observable<MessageEntity> getSearchMessages(@Query("uid") String uid,
                                                @Query("type") String type,
                                                @Query("page") int page);

    /**
     * 足迹
     * @param uid 用户ID
     * @param page 当前页数，用于刷新
     * @return
     */
    @GET(BaseUrl.FOOT_PRINT)
    Observable<MyFootPrintEntity> getSearchMyFootPrint(@Query("uid") String uid,
                                                       @Query("page") int page);

    /**
     * 活动详情
     * @param uid 用户ID
     * @param marketId 商城ID
     * @return
     */
    @GET(BaseUrl.ACTIVITIES_INFO)
    Observable<ActivitiesDetailsEntity> getSearchActivitiesInfo(@Query("uid") String uid,
                                                                @Query("marketId") String marketId);

    /**
     * 活动报名
     * @param uid 用户ID
     * @param marketId 商城ID
     * @param name 姓名
     * @param phone 电话
     * @param idCard 省份证号（选填）
     * @param type 商城所属类型
     * @return
     */
    @FormUrlEncoded
    @POST(BaseUrl.SIGN_UP)
    Observable<UserInfoEntity> getSearchActivitiesSignUp(@Query("uid") String uid,
                                                         @Query("marketId") String marketId,
                                                         @Field("name") String name,
                                                         @Query("phone") String phone,
                                                         @Query("idCard") String idCard,
                                                         @Query("type") String type);

    /**
     * 活动取消报名
     * @param uid 用户ID
     * @param marketId 商城ID
     * @param type 商城所属类型
     * @return
     */
    @GET(BaseUrl.CANCEL_SIGN_UP)
    Observable<UserInfoEntity> getSearchCancelSignUp(@Query("uid") String uid,
                                                     @Query("marketId") String marketId,
                                                     @Query("type") String type);

    /**
     * 收藏、取消收藏
     * @param uid 用户ID
     * @param activitiesId ID(商城，店铺，商品)
     * @param type 0:商城 1:门店 2:商品
     * @param isCollection 0:取消 1:收藏
     * @return
     */
    @GET(BaseUrl.SUBMIT_IS_COLLECTION)
    Observable<UserInfoEntity> getSubmitIsCollection(@Query("uid") String uid,
                                                     @Query("activitiesId") String activitiesId,
                                                     @Query("type") String type,
                                                     @Query("isCollection") String isCollection);

    /**
     * 版本更新
     * @return
     */
    @GET(BaseUrl.UPDATE)
    Observable<UpdateEntity> getUpdate();

    /**
     * 点击量
     * @param json 上传活动ID和对应的点击量如：{"1":"22","2":"544"}
     * @return
     */
    @GET(BaseUrl.CLICK_COUNT)
    Observable<UserInfoEntity> getClickCount(@Query("json") String json);
}
