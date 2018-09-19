package com.ifree.uu.uubuy.service.entity;

import java.util.List;

/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2018/9/19 0019
 * Description:
 */
public class MyFootPrintEntity {
    private String msg;
    private String resultCode;
    private DataBean data;
    public static class DataBean{
        private List<FootprintList> footprintList;
        public static class FootprintList{
            private String recordTime;
            private List<FootprintInfoList> footprintInfoList;
            public static class FootprintInfoList{
                private String activitiesId;//活动商场/商店/商品id
                private String activitiesPic;//活动商场/商店/商品图片
                private String activitiesName;//活动商场/商店/商品名
                private String activitiesDes;//活动描述
                private String activitiesAdAddress;//活动商场/商店地址
                private String activitiesOriginalPrice; //商品的原价
                private String activitiesPresentPrice;//商品现价
                private String activitiesSurplusNum;//商品剩余件数
                private String isOver;//是否结束下架
                private String activitiesType;//0商场1店铺
                private String type;//活动类型1普通2超市3品牌

                public String getActivitiesId() {
                    return activitiesId;
                }

                public String getActivitiesPic() {
                    return activitiesPic;
                }

                public String getActivitiesName() {
                    return activitiesName;
                }

                public String getActivitiesDes() {
                    return activitiesDes;
                }

                public String getActivitiesAdAddress() {
                    return activitiesAdAddress;
                }

                public String getActivitiesOriginalPrice() {
                    return activitiesOriginalPrice;
                }

                public String getActivitiesPresentPrice() {
                    return activitiesPresentPrice;
                }

                public String getActivitiesSurplusNum() {
                    return activitiesSurplusNum;
                }

                public String getIsOver() {
                    return isOver;
                }

                public String getActivitiesType() {
                    return activitiesType;
                }

                public String getType() {
                    return type;
                }
            }

            public String getRecordTime() {
                return recordTime;
            }

            public List<FootprintInfoList> getFootprintInfoList() {
                return footprintInfoList;
            }
        }

        public List<FootprintList> getFootprintList() {
            return footprintList;
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
