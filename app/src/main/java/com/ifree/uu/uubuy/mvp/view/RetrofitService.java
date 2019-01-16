package com.ifree.uu.uubuy.mvp.view;

import com.ifree.uu.uubuy.config.BaseUrl;
import com.ifree.uu.uubuy.mvp.modle.CityInfoBean;
import com.ifree.uu.uubuy.mvp.modle.GroupBean;
import com.ifree.uu.uubuy.mvp.modle.HomeBean;
import com.ifree.uu.uubuy.mvp.modle.HotKeyWordBean;
import com.ifree.uu.uubuy.mvp.modle.UpdateBean;
import com.ifree.uu.uubuy.mvp.modle.UserInfoBean;
import com.ifree.uu.uubuy.mvp.persenter.SearchBean;

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


    @POST(BaseUrl.UPLOAD_UUID)
    Observable<UserInfoBean> upLoadUUId(@Query("index") String systemName,
                                        @Query("uuid") String uuid,
                                        @Query("uid") String uid);

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
    Observable<HomeBean> getSearchHomes(@Query("longitude") String longitude,
                                        @Query("latitude") String latitude,
                                        @Query("townAdCode") String townAdCode,
                                        @Field("city") String city,
                                        @Query("page") int page);

    /**
     * 编辑用户资料-用户头像
     * @param uid 用户ID
     * @param body 用户头像
     * @return
     */
    @Multipart
    @POST(BaseUrl.MODIFY_USER_INFO)
    Observable<UserInfoBean> getSearchModifyUserIconInfo(@Query("uid") String uid,
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
    Observable<UserInfoBean> getSearchModifyUserInfo(@Query("uid") String uid,
                                                       @Query("userSex") String userSex,
                                                       @Field("userName") String userName,
                                                       @Query("userBirthday") String userBirthday,
                                                       @Query("idCard") String userIdCartNumber,
                                                       @Field("address") String userAddress);

    /**
     * 获取验证码
     * @param userPhone 用户手机号
     * @param codeType 验证码类型（1:登录 2:注册 3:手机号登录 4:找回密码 5:绑定密码 6:修改密码 7:更改手机号）
     * @return
     */
    @GET(BaseUrl.SEND_CODE)
    Observable<UserInfoBean> getSearchSendCode(@Query("userPhone") String userPhone,
                                                 @Query("codeType") String codeType);

    /**
     * 密码登录
     * @param userPhone 用户手机号
     * @param password 密码
     * @return
     */
    @GET(BaseUrl.USER_PASSWORD_LOGIN)
    Observable<UserInfoBean> getSearchPassWordLogin(@Query("userPhone") String userPhone,
                                                      @Query("password") String password,
                                                      @Query("index") String systemName,
                                                      @Query("uuid") String uuid);

    /**
     * 手机号验证码登录
     * @param userPhone 用户手机号
     * @param code 六位验证码
     * @param sessionId 验证码ID
     * @return
     */
    @GET(BaseUrl.SEND_CODE_LOGIN)
    Observable<UserInfoBean> getSearchPhoneCodeLogin(@Query("userPhone") String userPhone,
                                                       @Query("code") String code,
                                                       @Query("sessionId") String sessionId,
                                                       @Query("index") String systemName,
                                                       @Query("uuid") String uuid);

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
    Observable<UserInfoBean> getSearchThirdLogin(@Query("thirdUid") String thirdUid,
                                                   @Field("nickName") String nickName,
                                                   @Query("userIcon") String userIcon,
                                                   @Query("type") String type,
                                                   @Query("index") String systemName,
                                                   @Query("uuid") String uuid);

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
    Observable<UserInfoBean> getSearchBindPhone(@Query("userPhone") String userPhone,
                                                  @Query("userPassword") String password,
                                                  @Query("code") String code,
                                                  @Query("sessionId") String sessionId,
                                                  @Query("uid") String uid,
                                                  @Query("type") String type);

    /**
     * 忘记密码
     * @param userPhone 用户手机号
     * @param password 密码
     * @param code 六位验证码
     * @param sessionId 验证码ID
     * @return
     */
    @GET(BaseUrl.FORGET_PASSWORD)
    Observable<UserInfoBean> getSearchForgetPassword(@Query("userPhone") String userPhone,
                                                       @Query("password") String password,
                                                       @Query("code") String code,
                                                       @Query("sessionId") String sessionId);

    /**
     * 注册
     * @param userPhone 用户手机号
     * @param password 用户密码
     * @param code 六位验证码
     * @param sessionId 验证码ID
     * @return
     */
    @GET(BaseUrl.USER_REGISTER)
    Observable<UserInfoBean> getSearchRegister(@Query("userPhone") String userPhone,
                                                 @Query("password") String password,
                                                 @Query("code") String code,
                                                 @Query("sessionId") String sessionId);

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
    Observable<UserInfoBean> getSearchModifyPhone(@Query("newphone") String newPhone,
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
    Observable<UserInfoBean> getSearchModifyPassword(@Query("userPhone") String userPhone,
                                                       @Query("oldPassword") String oldPassword,
                                                       @Query("newPassword") String newPassword,
                                                       @Query("code") String code,
                                                       @Query("sessionId") String sessionId,
                                                       @Query("uid") String uid);

    /**
     * 版本更新
     * @return
     */
    @GET(BaseUrl.UPDATE)
    Observable<UpdateBean> getUpdate();

    /**
     * 分享
     * @param uid 用户ID
     * @return
     */
    @GET(BaseUrl.SHARE)
    Observable<UserInfoBean> getSearchShare(@Query("uid") String uid);

    /**
     * 签到
     * @param uid 用户ID
     * @return
     */
    @GET(BaseUrl.SIGN_IN)
    Observable<UserInfoBean> getSearchSignIns(@Query("uid") String uid);

    /**
     * 用户成长页面
     * @param uid 用户ID
     * @return
     */
    @GET(BaseUrl.GROUP_INFO)
    Observable<GroupBean> getSearchGroupInfos(@Query("uid") String uid);

    /**
     * 热词
     * @return
     */
    @GET(BaseUrl.HOT_KEY_WORD)
    Observable<HotKeyWordBean> getSearchHotKeyword();

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
    Observable<SearchBean> getSearchInfo(@Query("longitude") String longitude,
                                         @Query("latitude") String latitude,
                                         @Query("townAdCode") String townAdCode,
                                         @Query("page") int page,
                                         @Field("keyWord") String keyWord,
                                         @Query("uid") String uid,
                                         @Query("searchType") String searchType);

    /**
     * 获取开通城市
     * @return
     */
    @GET(BaseUrl.CITY_INFO)
    Observable<CityInfoBean> getSearchCityInfo();
}
