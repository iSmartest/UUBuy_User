package com.ifree.uu.uubuy.mvp.entity;

import java.util.List;

/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2018/9/14 0014
 * Description:
 */
public class CommodityInfoEntity {
    private String msg;
    private String resultCode;
    private DataBean data;
    public static class DataBean{
        private List<String> bannerPic;
        private List<CarPointList> carPointList;
        private String commodityId;
        private String commodityBrandName;
        private String commodityName;
        private String commodityDes;
        private String commodityPresentPrice;
        private String commoditySurplusNum;
        private String activitiesStoreAddress;
        private String commodityCondition;
        private String commodityPrice;
        private String commodityShopId;
        private String isCollection;
        private String type;
        private String carName;
        private String carAddress;
        private String carPic;
        private String carId;
        public static class CarPointList{
        private String carDesc;
        private String carSellPoint;
        private String carSellPointPic;

            public String getCarDesc() {
                return carDesc;
            }

            public void setCarDesc(String carDesc) {
                this.carDesc = carDesc;
            }

            public String getCarSellPoint() {
                return carSellPoint;
            }

            public void setCarSellPoint(String carSellPoint) {
                this.carSellPoint = carSellPoint;
            }

            public String getCarSellPointPic() {
                return carSellPointPic;
            }

            public void setCarSellPointPic(String carSellPointPic) {
                this.carSellPointPic = carSellPointPic;
            }
        }
        public List<String> getBannerPic() {
            return bannerPic;
        }

        public void setBannerPic(List<String> bannerPic) {
            this.bannerPic = bannerPic;
        }

        public String getCommodityId() {
            return commodityId;
        }

        public void setCommodityId(String commodityId) {
            this.commodityId = commodityId;
        }

        public String getCommodityBrandName() {
            return commodityBrandName;
        }

        public void setCommodityBrandName(String commodityBrandName) {
            this.commodityBrandName = commodityBrandName;
        }

        public String getCommodityName() {
            return commodityName;
        }

        public void setCommodityName(String commodityName) {
            this.commodityName = commodityName;
        }

        public String getCommodityDes() {
            return commodityDes;
        }

        public void setCommodityDes(String commodityDes) {
            this.commodityDes = commodityDes;
        }

        public String getCommodityPresentPrice() {
            return commodityPresentPrice;
        }

        public void setCommodityPresentPrice(String commodityPresentPrice) {
            this.commodityPresentPrice = commodityPresentPrice;
        }

        public String getCommoditySurplusNum() {
            return commoditySurplusNum;
        }

        public void setCommoditySurplusNum(String commoditySurplusNum) {
            this.commoditySurplusNum = commoditySurplusNum;
        }

        public String getCommodityCondition() {
            return commodityCondition;
        }

        public void setCommodityCondition(String commodityCondition) {
            this.commodityCondition = commodityCondition;
        }

        public String getCommodityPrice() {
            return commodityPrice;
        }

        public void setCommodityPrice(String commodityPrice) {
            this.commodityPrice = commodityPrice;
        }

        public String getCommodityShopId() {
            return commodityShopId;
        }

        public void setCommodityShopId(String commodityShopId) {
            this.commodityShopId = commodityShopId;
        }

        public String getIsCollection() {
            return isCollection;
        }

        public void setIsCollection(String isCollection) {
            this.isCollection = isCollection;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getCarName() {
            return carName;
        }

        public void setCarName(String carName) {
            this.carName = carName;
        }

        public String getCarAddress() {
            return carAddress;
        }

        public void setCarAddress(String carAddress) {
            this.carAddress = carAddress;
        }

        public String getCarPic() {
            return carPic;
        }

        public void setCarPic(String carPic) {
            this.carPic = carPic;
        }

        public String getCarId() {
            return carId;
        }

        public void setCarId(String carId) {
            this.carId = carId;
        }

        public String getActivitiesStoreAddress() {
            return activitiesStoreAddress;
        }

        public void setActivitiesStoreAddress(String activitiesStoreAddress) {
            this.activitiesStoreAddress = activitiesStoreAddress;
        }

        public List<CarPointList> getCarPointList() {
            return carPointList;
        }

        public void setCarPointList(List<CarPointList> carPointList) {
            this.carPointList = carPointList;
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
