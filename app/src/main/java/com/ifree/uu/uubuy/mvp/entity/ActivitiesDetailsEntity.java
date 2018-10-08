package com.ifree.uu.uubuy.mvp.entity;

import java.util.List;

/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2018/9/19 0019
 * Description:
 */
public class ActivitiesDetailsEntity {
    private String resultCode;
    private String msg;
    private DataBean data;
    public static class DataBean{
        private String isSingUp;
        private String activitiesDes;
        private String activitiesPic;
        private String activitiesName;
        private List<CouponList> couponList;
        public static class CouponList{
            private String isUse;
            private String couponType;
            private String couponCondition;
            private String couponId;
            private int couponValue;

            public String getIsUse() {
                return isUse;
            }

            public void setIsUse(String isUse) {
                this.isUse = isUse;
            }

            public String getCouponType() {
                return couponType;
            }


            public void setCouponType(String couponType) {
                this.couponType = couponType;
            }

            public String getCouponCondition() {
                return couponCondition;
            }

            public void setCouponCondition(String couponCondition) {
                this.couponCondition = couponCondition;
            }

            public String getCouponId() {
                return couponId;
            }

            public void setCouponId(String couponId) {
                this.couponId = couponId;
            }

            public int getCouponValue() {
                return couponValue;
            }

            public void setCouponValue(int couponValue) {
                this.couponValue = couponValue;
            }
        }

        public String getIsSingUp() {
            return isSingUp;
        }

        public String getActivitiesDes() {
            return activitiesDes;
        }

        public String getActivitiesPic() {
            return activitiesPic;
        }

        public String getActivitiesName() {
            return activitiesName;
        }

        public List<CouponList> getCouponList() {
            return couponList;
        }
    }

    public String getResultCode() {
        return resultCode;
    }

    public String getMsg() {
        return msg;
    }

    public DataBean getData() {
        return data;
    }
}
