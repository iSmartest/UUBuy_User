package com.ifree.uu.uubuy.service.entity;

import android.widget.ListView;

import java.util.List;

/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2018/9/18 0018
 * Description:
 */
public class MoreEntity {
    private String msg;
    private String resultCode;
    private DataBean data;
    public static class DataBean{
        private List<CommodityList> commodityList;
        public static class CommodityList{
            private String commodityId;
            private String commodityName;
            private String commodityPic;//商品图片
            private String commodityOriginalPrice;//原价
            private String commodityNowPrice;//现价
            private String commodityDec;//描述
            private String commodityTime;//活动时间
            private String commodityDescent;//折扣
            private String commodityType;//0专柜1可预订商品

            public String getCommodityId() {
                return commodityId;
            }

            public String getCommodityName() {
                return commodityName;
            }

            public String getCommodityPic() {
                return commodityPic;
            }

            public String getCommodityOriginalPrice() {
                return commodityOriginalPrice;
            }

            public String getCommodityNowPrice() {
                return commodityNowPrice;
            }

            public String getCommodityDec() {
                return commodityDec;
            }

            public String getCommodityTime() {
                return commodityTime;
            }

            public String getCommodityDescent() {
                return commodityDescent;
            }

            public String getCommodityType() {
                return commodityType;
            }
        }

        public List<CommodityList> getCommodityList() {
            return commodityList;
        }

        public void setCommodityList(List<CommodityList> commodityList) {
            this.commodityList = commodityList;
        }
    }

    public String getMsg() {
        return msg;
    }

    public String getResultCode() {
        return resultCode;
    }

    public DataBean getData() {
        return data;
    }
}
