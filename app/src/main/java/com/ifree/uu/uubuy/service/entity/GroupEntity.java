package com.ifree.uu.uubuy.service.entity;

import java.util.List;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/22.
 * Description:
 */
public class GroupEntity {
    private String result;
    private String resultCode;
    private String userGrowthValue;
    private String isUserInfo;

    private List<SignInList> signInList;

    public class SignInList {
        private long date;
        private String isSignIn;

        public long getDate() {
            return date;
        }

        public void setDate(long date) {
            this.date = date;
        }

        public String getIsSignIn() {
            return isSignIn;
        }

        public void setIsSignIn(String isSignIn) {
            this.isSignIn = isSignIn;
        }
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getUserGrowthValue() {
        return userGrowthValue;
    }

    public void setUserGrowthValue(String userGrowthValue) {
        this.userGrowthValue = userGrowthValue;
    }

    public String getIsUserInfo() {
        return isUserInfo;
    }

    public void setIsUserInfo(String isUserInfo) {
        this.isUserInfo = isUserInfo;
    }

    public List<SignInList> getSignInList() {
        return signInList;
    }

    public void setSignInList(List<SignInList> signInList) {
        this.signInList = signInList;
    }
}
