package com.ifree.uu.uubuy.service.entity;

import java.util.List;

/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2018/9/19 0019
 * Description:
 */
public class CompareCommodityEntity {
    private String resultCode;
    private String msg;
    private DataBean data;
    public static class DataBean {
        private List<PList> pList;

        public static class PList {
            private String commodityPresentPrice;
            private String bannerPic;
            private String activitiesStoreAddress;
            private String commodityShopId;
            private String commodityId;
            private String commodityDes;
            private String commodityCondition;
            private String commodityBrandName;
            private String commodityPrice;
            private String commodityName;
            private String commoditySurplusNum;

            public String getCommodityPresentPrice() {
                return commodityPresentPrice;
            }

            public String getBannerPic() {
                return bannerPic;
            }

            public String getActivitiesStoreAddress() {
                return activitiesStoreAddress;
            }

            public String getCommodityShopId() {
                return commodityShopId;
            }

            public String getCommodityId() {
                return commodityId;
            }

            public String getCommodityDes() {
                return commodityDes;
            }

            public String getCommodityCondition() {
                return commodityCondition;
            }

            public String getCommodityBrandName() {
                return commodityBrandName;
            }

            public String getCommodityPrice() {
                return commodityPrice;
            }

            public String getCommodityName() {
                return commodityName;
            }

            public String getCommoditySurplusNum() {
                return commoditySurplusNum;
            }
        }

        public List<PList> getpList() {
            return pList;
        }
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }
}
