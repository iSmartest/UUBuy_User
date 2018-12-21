package com.ifree.uu.uubuy.mvp.entity;

import java.util.List;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/21.
 * Description:
 */
public class MineEntity {
    private String msg;
    private String resultCode;
    private DataBean data;
    public static class DataBean{
        private String userIcon;
        private String userName;
        private String userPassword;
        private String userPhone;
        private String userID;
        private String userSex;
        private String userBirthday;
        private String userGrowthValue;
        private String medalTotalValue;
        private String userMedal;
        private String userCoupon;
        private String userIntegral;
        private String userAddress;
        private String userBindPhone;
        private String userIdcard;
        private List<RecommendactivitiesList> recommendactivitiesList;
        public static class RecommendactivitiesList{
            private String activitiesId;
            private String activitiesPic;
            private String activitiesName;
            private String activitiesTime;
            private String activitiesAdAddress;
            private String activitiesType;
            private String type;
            private String isOver;
            private String aId;
            public String getActivitiesId() {
                return activitiesId;
            }

            public void setActivitiesId(String activitiesId) {
                this.activitiesId = activitiesId;
            }

            public String getActivitiesPic() {
                return activitiesPic;
            }

            public void setActivitiesPic(String activitiesPic) {
                this.activitiesPic = activitiesPic;
            }

            public String getActivitiesName() {
                return activitiesName;
            }

            public void setActivitiesName(String activitiesName) {
                this.activitiesName = activitiesName;
            }

            public String getActivitiesTime() {
                return activitiesTime;
            }

            public void setActivitiesTime(String activitiesTime) {
                this.activitiesTime = activitiesTime;
            }

            public String getActivitiesAdAddress() {
                return activitiesAdAddress;
            }

            public void setActivitiesAdAddress(String activitiesAdAddress) {
                this.activitiesAdAddress = activitiesAdAddress;
            }

            public String getActivitiesType() {
                return activitiesType;
            }

            public void setActivitiesType(String activitiesType) {
                this.activitiesType = activitiesType;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getIsOver() {
                return isOver;
            }

            public void setIsOver(String isOver) {
                this.isOver = isOver;
            }

            public String getaId() {
                return aId;
            }
        }
        public String getUserIcon() {
            return userIcon;
        }

        public void setUserIcon(String userIcon) {
            this.userIcon = userIcon;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserID() {
            return userID;
        }

        public void setUserID(String userID) {
            this.userID = userID;
        }

        public String getUserSex() {
            return userSex;
        }

        public void setUserSex(String userSex) {
            this.userSex = userSex;
        }

        public String getUserBirthday() {
            return userBirthday;
        }

        public void setUserBirthday(String userBirthday) {
            this.userBirthday = userBirthday;
        }

        public String getUserGrowthValue() {
            return userGrowthValue;
        }

        public void setUserGrowthValue(String userGrowthValue) {
            this.userGrowthValue = userGrowthValue;
        }

        public String getMedalTotalValue() {
            return medalTotalValue;
        }

        public void setMedalTotalValue(String medalTotalValue) {
            this.medalTotalValue = medalTotalValue;
        }

        public String getUserMedal() {
            return userMedal;
        }

        public void setUserMedal(String userMedal) {
            this.userMedal = userMedal;
        }

        public String getUserCoupon() {
            return userCoupon;
        }

        public void setUserCoupon(String userCoupon) {
            this.userCoupon = userCoupon;
        }

        public String getUserIntegral() {
            return userIntegral;
        }

        public void setUserIntegral(String userIntegral) {
            this.userIntegral = userIntegral;
        }

        public String getUserPassword() {
            return userPassword;
        }

        public void setUserPassword(String userPassword) {
            this.userPassword = userPassword;
        }

        public String getUserPhone() {
            return userPhone;
        }

        public void setUserPhone(String userPhone) {
            this.userPhone = userPhone;
        }

        public String getUserAddress() {
            return userAddress;
        }

        public void setUserAddress(String userAddress) {
            this.userAddress = userAddress;
        }

        public String getUserBindPhone() {
            return userBindPhone;
        }

        public void setUserBindPhone(String userBindPhone) {
            this.userBindPhone = userBindPhone;
        }

        public String getUserIdcard() {
            return userIdcard;
        }

        public void setUserIdcard(String userIdcard) {
            this.userIdcard = userIdcard;
        }

        public List<RecommendactivitiesList> getRecommendactivitiesList() {
            return recommendactivitiesList;
        }

        public void setRecommendactivitiesList(List<RecommendactivitiesList> recommendactivitiesList) {
            this.recommendactivitiesList = recommendactivitiesList;
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
