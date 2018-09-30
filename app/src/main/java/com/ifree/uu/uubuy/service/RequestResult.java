package com.ifree.uu.uubuy.service;

import android.util.Log;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import javax.net.ssl.SSLHandshakeException;

import retrofit2.HttpException;

/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2018/9/29 0029
 * Description:
 */
public class RequestResult {

    public static String getError(Throwable throwable){
        String mErrorMessage = null;
        try {
            if (throwable instanceof SocketTimeoutException) {//请求超时
            } else if (throwable instanceof ConnectException) {//网络连接超时
                mErrorMessage =  "网络连接超时";
            } else if (throwable instanceof SSLHandshakeException) {//安全证书异常
                mErrorMessage = "安全证书异常";
            } else if (throwable instanceof HttpException) {//请求的地址不存在
                int code = ((HttpException) throwable).code();
                if (code == 504) {
                    mErrorMessage = "网络异常，请检查您的网络状态";
                } else if (code == 404) {
                    mErrorMessage = "请求的地址不存在";
                } else {
                    mErrorMessage = "请求失败";
                }
            } else if (throwable instanceof UnknownHostException) {//域名解析失败
                mErrorMessage = "域名解析失败";
            } else {
                mErrorMessage = "error:" + throwable.getMessage();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        } finally {
            Log.e("OnSuccessAndFaultSub", "error:" + throwable.getMessage());
        }
        return mErrorMessage;
    }
}
