package com.ifree.uu.uubuy.service.entity;

import java.util.List;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/20.
 * Description:
 */
public class OrderEntity {
    private String msg;
    private String resultCode;
    private DataBean data;
    public static class DataBean{
        private List<OrderInfoList> orderInfoList;
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
            private String type;
            private String shopId;

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

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getShopId() {
                return shopId;
            }

            public void setShopId(String shopId) {
                this.shopId = shopId;
            }
        }

        public List<OrderInfoList> getOrderInfoList() {
            return orderInfoList;
        }

        public void setOrderInfoList(List<OrderInfoList> orderInfoList) {
            this.orderInfoList = orderInfoList;
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
