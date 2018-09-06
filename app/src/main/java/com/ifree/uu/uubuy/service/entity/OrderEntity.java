package com.ifree.uu.uubuy.service.entity;

import java.util.List;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/20.
 * Description:
 */
public class OrderEntity {
    private String result;
    private String resultCode;
    private List<OrderInfoList> orderInfoList;

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

    public List<OrderInfoList> getOrderInfoList() {
        return orderInfoList;
    }

    public void setOrderInfoList(List<OrderInfoList> orderInfoList) {
        this.orderInfoList = orderInfoList;
    }

    public static class OrderInfoList{
        private String orderId;
        private String orderState;
        private String commodityid;
        private String commodityTitle;
        private String commodityIcon;
        private String commodityDec;
        private String commodityNum;
        private String commodityPresentPrice;
        private String orderTime;
        private String isOver;
        private String storeAddress;

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getOrderState() {
            return orderState;
        }

        public void setOrderState(String orderState) {
            this.orderState = orderState;
        }

        public String getCommodityid() {
            return commodityid;
        }

        public void setCommodityid(String commodityid) {
            this.commodityid = commodityid;
        }

        public String getCommodityTitle() {
            return commodityTitle;
        }

        public void setCommodityTitle(String commodityTitle) {
            this.commodityTitle = commodityTitle;
        }

        public String getCommodityIcon() {
            return commodityIcon;
        }

        public void setCommodityIcon(String commodityIcon) {
            this.commodityIcon = commodityIcon;
        }

        public String getCommodityDec() {
            return commodityDec;
        }

        public void setCommodityDec(String commodityDec) {
            this.commodityDec = commodityDec;
        }

        public String getCommodityNum() {
            return commodityNum;
        }

        public void setCommodityNum(String commodityNum) {
            this.commodityNum = commodityNum;
        }

        public String getCommodityPresentPrice() {
            return commodityPresentPrice;
        }

        public void setCommodityPresentPrice(String commodityPresentPrice) {
            this.commodityPresentPrice = commodityPresentPrice;
        }

        public String getOrderTime() {
            return orderTime;
        }

        public void setOrderTime(String orderTime) {
            this.orderTime = orderTime;
        }

        public String getIsOver() {
            return isOver;
        }

        public void setIsOver(String isOver) {
            this.isOver = isOver;
        }

        public String getStoreAddress() {
            return storeAddress;
        }

        public void setStoreAddress(String storeAddress) {
            this.storeAddress = storeAddress;
        }
    }
}
