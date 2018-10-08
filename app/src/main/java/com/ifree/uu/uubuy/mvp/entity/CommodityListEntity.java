package com.ifree.uu.uubuy.mvp.entity;

import java.util.List;

/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2018/9/14 0014
 * Description:
 */
public class CommodityListEntity {
    private String resultCode;
    private String msg;
    private DataBean data;
    public static class DataBean{
        private String storePic;
        private String storeAddress;
        private String storeTime;
        private String isCollection;
        private String storeName;
        private List<CommodityList> commodityList;
        public static class CommodityList{
            private String commodityOriginalPrice;
            private String commodityPic;
            private String commodityStock;
            private String commodityNowPrice;
            private String commodityId;
            private String type;
            private String commodityType;
            private String commodityName;

            public String getCommodityOriginalPrice() {
                return commodityOriginalPrice;
            }

            public String getCommodityPic() {
                return commodityPic;
            }

            public String getCommodityStock() {
                return commodityStock;
            }

            public String getCommodityNowPrice() {
                return commodityNowPrice;
            }

            public String getCommodityId() {
                return commodityId;
            }

            public String getType() {
                return type;
            }

            public String getCommodityType() {
                return commodityType;
            }

            public String getCommodityName() {
                return commodityName;
            }
        }

        public String getStorePic() {
            return storePic;
        }

        public String getStoreAddress() {
            return storeAddress;
        }

        public String getStoreTime() {
            return storeTime;
        }

        public String getIsCollection() {
            return isCollection;
        }

        public String getStoreName() {
            return storeName;
        }

        public List<CommodityList> getCommodityList() {
            return commodityList;
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
