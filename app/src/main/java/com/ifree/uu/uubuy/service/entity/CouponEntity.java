package com.ifree.uu.uubuy.service.entity;

import java.util.List;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/21.
 * Description:
 */
public class CouponEntity {
    private String result;
    private String resultCode;
    private List<CouponList> couponList;
    public class CouponList{
        private String couponId;
        private String couponAllPrice;
        private String couponReducePrice;
        private String couponDiscount;
        private String securitiesTimeZone;


        public String getCouponId() {
            return couponId;
        }

        public void setCouponId(String couponId) {
            this.couponId = couponId;
        }

        public String getCouponAllPrice() {
            return couponAllPrice;
        }

        public void setCouponAllPrice(String couponAllPrice) {
            this.couponAllPrice = couponAllPrice;
        }

        public String getCouponReducePrice() {
            return couponReducePrice;
        }

        public void setCouponReducePrice(String couponReducePrice) {
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

    public List<CouponList> getCouponList() {
        return couponList;
    }

    public void setCouponList(List<CouponList> couponList) {
        this.couponList = couponList;
    }
}
