package com.ifree.uu.uubuy.config;


import com.ifree.uu.uubuy.BuildConfig;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/13.
 * Description:
 */
public class BaseUrl {

//    public static final String HTTP = BuildConfig.API_HOST;
    public static final String HTTP = "http://www.uugood.cn:8080/uugo-user/app/";

    public static final String HTTP2 = "http://192.168.1.33:8084/uugo-operation/app/";

    public static final String ShARE_URL = "http://a.app.qq.com/o/simple.jsp?pkgname=com.ifree.uu.uubuy";
    //获取验证码
    public static final String SEND_CODE = "sendCode";
    //注册
    public static final String USER_REGISTER = "userRegister";
    //绑定手机号
    public static final String BIND_PHONE = "bindPhone?";
    //更改手机号
    public static final String MODIFY_PHONE = "modifyPhone?";
    //更改登录密码
    public static final String MODIFY_PASSWORD = "modifyPassword?";
    //忘记密码
    public static final String FORGET_PASSWORD = "forgetPassword";
    //手机号验证码登录
    public static final String SEND_CODE_LOGIN = "userPhoneLogin";
    //密码登录
    public static final String USER_PASSWORD_LOGIN = "userPassWordLogin";
    //第三方登录
    public static final String THIRD_LOGIN = "thirdLogin";
    //获取开通城市
    public static final String CITY_INFO = "getCityInfo";
    //搜索
    public static final String SEARCH_INFO = "getSearchInfo?";
    //热词
    public static final String HOT_KEY_WORD = "hotKeyword?";
    //首页
    public static final String HOME_INFO = "getHomeInfo?";
    //商场（第一级）
    public static final String CLASSIFY_LIST_INFO = "getClassifyListInfo?";
    //活动详情
    public static final String ACTIVITIES_INFO = "activityDetail?";
    //店铺（第二级）
    public static final String SECOND_LIST_INFO = "getSecondActivitiesListInfo?";
    //商品（第三级）
    public static final String COMMODITY_LIST_INFO = "getCommodityListInfo?";
    //更多
    public static final String MORE_LIST_INFO = "superMarketProductDetail?";
    //商品详情（）
    public static final String COMMODITY_INFO = "getReserveCommodityInfo?";
    //同类竞品
    public static final String COMPARE_INFO = "tongLeiCompare ?";
    //周围
    public static final String AROUND_INFO = "getAroundInfo?";
    //生活圈
    public static final String ACTIVITIES_CIRCLE_INFO = "getActivitiesCircleInfo?";
    //提交预订
    public static final String SUBMIT_RESERVE_INFO = "submitReserveInfo?";
    //订单
    public static final String ORDER_INFO = "getOrderInfo?";
    //订单取消、删除、重新下单
    public static final String SUBMIT_OPERATION_ORDER = "getOperationOrder?";
    //我的
    public static final String MINE_INFO = "getMineInfo?";
    //编辑个人资料
    public static final String MODIFY_USER_INFO = "modifyUserInfo?";
    //用户成长页面
    public static final String GROUP_INFO = "getGroupInfo?";
    //签到
    public static final String SIGN_IN = "getSignIn?";
    //分享
    public static final String SHARE = "share?";
    //领券中心
    public static final String COUPON_CENTER = "getCouponInfo?";
    //领取优惠券
    public static final String GET_COUPON = "getCoupon?";
    //我的优惠券
    public static final String MY_COUPON = "getMyCouponInfo?";
    //消息
    public static final String MESSAGE_INFO = "getMeassage?";
    //足迹
    public static final String FOOT_PRINT = "getMyFootprint?";
    //活动报名
    public static final String SIGN_UP = "marketSignUp?";
    //活动取消报名
    public static final String CANCEL_SIGN_UP = "cancelMarketSignUp?";
    //收藏、取消收藏
    public static final String SUBMIT_IS_COLLECTION = "submitIsCollection?";
    //意见反馈
    public static final String FEEDBACK = "submitIsCollection?";
    //版本更新
    public static final String UPDATE = "version?";
    //浏览量
    public static final String CLICK_COUNT= "clickCount?";
    //上传UUID
    public static final String UPLOAD_UUID = "statistics";


    //推广活动是否开启
    public static final String ELASTIC_FRAME = "elasticFrame";
    //UU推广活动报名
    public static final String ENROLL = "enroll";
    //获取中奖码
    public static final String PRIZE_CODE = "prizeCode";
}
