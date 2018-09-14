package com.ifree.uu.uubuy.service.entity;

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
        private String commodityId;
        private String commodityBrandName;
        private String commodityName;
        private String commodityDes;
        private String commodityPresentPrice;
        private String commoditySurplusNum;
        private String activditiesContent;
        private String activitiesStoreAddress;

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

        public String getActivditiesContent() {
            return activditiesContent;
        }

        public void setActivditiesContent(String activditiesContent) {
            this.activditiesContent = activditiesContent;
        }

        public String getActivitiesStoreAddress() {
            return activitiesStoreAddress;
        }

        public void setActivitiesStoreAddress(String activitiesStoreAddress) {
            this.activitiesStoreAddress = activitiesStoreAddress;
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
