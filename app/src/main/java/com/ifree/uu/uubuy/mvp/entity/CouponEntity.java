package com.ifree.uu.uubuy.mvp.entity;

import java.util.List;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/21.
 * Description:
 */
public class CouponEntity {
    private String msg;
    private String resultCode;
    private DataBean data;
    public static class DataBean{
        private List<CouponList> couponList;
        public class CouponList{
            private String couponId;
            private String type;
            private String msName;
            private String condition;
            private String couponAllPrice;
            private int couponReducePrice;
            private String couponDiscount;
            private String securitiesTimeZone;


            public String getCouponId() {
                return couponId;
            }

            public void setCouponId(String couponId) {
                this.couponId = couponId;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getMsName() {
                return msName;
            }

            public void setMsName(String msName) {
                this.msName = msName;
            }

            public String getCondition() {
                return condition;
            }

            public void setCondition(String condition) {
                this.condition = condition;
            }

            public String getCouponAllPrice() {
                return couponAllPrice;
            }

            public void setCouponAllPrice(String couponAllPrice) {
                this.couponAllPrice = couponAllPrice;
            }

            public int getCouponReducePrice() {
                return couponReducePrice;
            }

            public void setCouponReducePrice(int couponReducePrice) {
                this.couponReducePrice = couponReducePrice;
            }

            public String getCouponDiscount() {
                return couponDiscount;
            }

            public void setCouponDiscount(String couponDiscount) {
                this.couponDiscount = couponDiscount;
            }

            public String getSecuritiesTimeZone() {
                return securitiesTimeZone;
            }

            public void setSecuritiesTimeZone(String securitiesTimeZone) {
                this.securitiesTimeZone = securitiesTimeZone;
            }
        }

        public List<CouponList> getCouponList() {
            return couponList;
        }

        public void setCouponList(List<CouponList> couponList) {
            this.couponList = couponList;
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
