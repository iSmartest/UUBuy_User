package com.ifree.uu.uubuy.service.entity;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/9/6.
 * Description:
 */
public class UserInfoEntity {
    private String result;
    private String resultCode;
    private DataBean data;
    public static class DataBean{
        private String code;//验证码
        private String uid; //用户ID
        private String isPhone;//0未绑定1绑定过
        private String nickName; //返回服务器上的昵称
        private String userIcon;  //返回服务器上的用户头像

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getIsPhone() {
            return isPhone;
        }

        public void setIsPhone(String isPhone) {
            this.isPhone = isPhone;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getUserIcon() {
            return userIcon;
        }

        public void setUserIcon(String userIcon) {
            this.userIcon = userIcon;
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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }
}
