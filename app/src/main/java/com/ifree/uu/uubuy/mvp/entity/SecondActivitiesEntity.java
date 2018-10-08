package com.ifree.uu.uubuy.mvp.entity;

import java.util.List;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/24.
 * Description:
 */
public class SecondActivitiesEntity {
    private String msg;
    private String resultCode;
    private DataBean data;
    public static class DataBean{
        private MarketInfo marketInfo;
        private List<BandCommodityList> bandCommodityList;
        private List<MarketCommodityList> marketCommodityList;
        public static class MarketInfo{
            private String activitiesAddress;
            private String activitiesPic;
            private String activitiesTime;
            private String isCollection;
            private String isOver;
            private String marketId;
            private String marketName;

            public String getActivitiesAddress() {
                return activitiesAddress;
            }

            public String getActivitiesPic() {
                return activitiesPic;
            }

            public String getActivitiesTime() {
                return activitiesTime;
            }

            public String getIsCollection() {
                return isCollection;
            }

            public String getIsOver() {
                return isOver;
            }

            public String getMarketId() {
                return marketId;
            }

            public String getMarketName() {
                return marketName;
            }
        }
        public static class BandCommodityList{
            private String secondActivitiesId;
            private String secondActivitiesName;
            private String secondActivitiesPic;
            private String secondActivitiesTime;
            private String secondActivitiesAdAddress;
            private String secondActivitiesPrice;
            private String secondActivitiesType;
            private String secondActivitiesIsCollection;

            public String getSecondActivitiesId() {
                return secondActivitiesId;
            }

            public String getSecondActivitiesName() {
                return secondActivitiesName;
            }

            public String getSecondActivitiesPic() {
                return secondActivitiesPic;
            }

            public String getSecondActivitiesTime() {
                return secondActivitiesTime;
            }

            public String getSecondActivitiesAdAddress() {
                return secondActivitiesAdAddress;
            }

            public String getSecondActivitiesPrice() {
                return secondActivitiesPrice;
            }

            public String getSecondActivitiesType() {
                return secondActivitiesType;
            }

            public String getSecondActivitiesIsCollection() {
                return secondActivitiesIsCollection;
            }
        }
        public static class MarketCommodityList{
            private String commodityTitle;
            private String classfyId;
            private List<CommodityList> commodityList;
            public static class CommodityList{
                private String commodityId;
                private String commodityName;
                private String commodityPic;//商品图片
                private int commodityOriginalPrice;//原价
                private int commodityNowPrice;//现价
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

                public int getCommodityOriginalPrice() {
                    return commodityOriginalPrice;
                }

                public int getCommodityNowPrice() {
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

            public String getCommodityTitle() {
                return commodityTitle;
            }

            public String getClassfyId() {
                return classfyId;
            }

            public List<CommodityList> getCommodityList() {
                return commodityList;
            }
        }

        public MarketInfo getMarketInfo() {
            return marketInfo;
        }

        public List<BandCommodityList> getBandCommodityList() {
            return bandCommodityList;
        }

        public List<MarketCommodityList> getMarketCommodityList() {
            return marketCommodityList;
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

