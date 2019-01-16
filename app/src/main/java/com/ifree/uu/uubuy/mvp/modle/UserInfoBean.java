package com.ifree.uu.uubuy.mvp.modle;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/9/6.
 * Description:
 */
public class UserInfoBean {
    private String msg;
    private String resultCode;
    private DataBean data;
    public static class DataBean{
        private String code;//验证码
        private String userSex;
        private String medalTotalValue;
        private String userIntegral;
        private String userCoupon;
        private String userPassword;
        private String userPhone;
        private String userName;//返回服务器上的昵称
        private String userBindPhone;//0未绑定1绑定过
        private String userAddress;
        private String userMedal;
        private String id;//用户ID
        private String userGrowthValue;
        private String userIdcard;
        private String userIcon;//返回服务器上的用户头像
        private String userBirthday;
        private String sessionId;
        private String pop;//0不弹，1弹
        private String isEnroll;//0未,1已报名
        private String index;//兑奖码

        public String getCode() {
            return code;
        }

        public String getUserSex() {
            return userSex;
        }

        public String getMedalTotalValue() {
            return medalTotalValue;
        }

        public String getUserIntegral() {
            return userIntegral;
        }

        public String getUserCoupon() {
            return userCoupon;
        }

        public String getUserPassword() {
            return userPassword;
        }

        public String getUserPhone() {
            return userPhone;
        }

        public String getUserName() {
            return userName;
        }

        public String getUserBindPhone() {
            return userBindPhone;
        }

        public String getUserAddress() {
            return userAddress;
        }

        public String getUserMedal() {
            return userMedal;
        }

        public String getId() {
            return id;
        }

        public String getUserGrowthValue() {
            return userGrowthValue;
        }

        public String getUserIdcard() {
            return userIdcard;
        }

        public String getUserIcon() {
            return userIcon;
        }

        public String getUserBirthday() {
            return userBirthday;
        }

        public String getSessionId() {
            return sessionId;
        }

        public String getPop() {
            return pop;
        }

        public String getIsEnroll() {
            return isEnroll;
        }

        public String getIndex() {
            return index;
        }
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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }
}
