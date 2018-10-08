package com.ifree.uu.uubuy.mvp.entity;

import java.util.List;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/22.
 * Description:
 */
public class GroupEntity {
    private String msg;
    private String resultCode;
    private DataBean data;
    public static class DataBean {
        private String userGrowthValue;
        private String isUserInfo;
        private String share;
        private List<SignInList> signInList;

        public class SignInList {
            private String date;
            private String isSignIn;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getIsSignIn() {
                return isSignIn;
            }

            public void setIsSignIn(String isSignIn) {
                this.isSignIn = isSignIn;
            }
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

        public String getShare() {
            return share;
        }

        public void setShare(String share) {
            this.share = share;
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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }


}
