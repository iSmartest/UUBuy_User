package com.ifree.uu.uubuy.config;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/13.
 * Description:
 */
public class BaseUrl {
    //服务器地址
//    public static final String HTTP = "http://101.200.60.12:8080/robot-manager-web/";//网络

    public static final String HTTP = "http://192.168.1.6:8083/app/";//本地
    //获取验证码
    public static final String SEND_CODE = "sendCode";
    //注册
    public static final String USER_REGISTER = "userRegister";
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
    //首页
    public static final String HOME_INFO = "getHomeInfo?";
    //周围
    public static final String AROUND_INFO = "getAroundInfo?";
    //生活圈
    public static final String ACTIVITIES_CIRCLE_INFO = "getActivitiesCircleInfo?";
    //订单
    public static final String ORDER_INFO = "getOrderInfo?";
    //我的
    public static final String MINE_INFO = "getMineInfo?";
    //用户成长页面
    public static final String GROUP_INFO = "getGroupInfo?";

    //消息
    public static final String MESSAGE_INFO = "getMeassage?";
}
