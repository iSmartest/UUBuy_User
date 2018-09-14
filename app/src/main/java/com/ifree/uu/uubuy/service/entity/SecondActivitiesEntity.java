package com.ifree.uu.uubuy.service.entity;

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
        private String bandStorePic;
        private String fristActivitiesName;
        private String fristActivitiesPic;
        private String fristActivitiesTime;
        private String fristActivitiesAdAddress;
        private String fristActivitiesIsCollection;
        private List<SecondActivitiesList> secondActivitiesList;
        private List<MarketCommodityList> marketCommodityList;
        private List<BandCommodityList> bandCommodityList;
        public static class SecondActivitiesList{
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

            public String getCommodityTitle() {
                return commodityTitle;
            }

            public List<CommodityList> getCommodityList() {
                return commodityList;
            }
        }
        public static class BandCommodityList{
            private String commodityId;//用于跳转到具体活动
            private String commodityName; //商品名称
            private String commodityPic;//商品图片
            private String commodityDes;//商品描述
            private String commodityType;//1可预订商品2不可商品

            public String getCommodityId() {
                return commodityId;
            }

            public String getCommodityName() {
                return commodityName;
            }

            public String getCommodityPic() {
                return commodityPic;
            }

            public String getCommodityDes() {
                return commodityDes;
            }

            public String getCommodityType() {
                return commodityType;
            }
        }

        public String getBandStorePic() {
            return bandStorePic;
        }

        public String getFristActivitiesName() {
            return fristActivitiesName;
        }

        public String getFristActivitiesPic() {
            return fristActivitiesPic;
        }

        public String getFristActivitiesTime() {
            return fristActivitiesTime;
        }

        public String getFristActivitiesAdAddress() {
            return fristActivitiesAdAddress;
        }

        public String getFristActivitiesIsCollection() {
            return fristActivitiesIsCollection;
        }

        public List<SecondActivitiesList> getSecondActivitiesList() {
            return secondActivitiesList;
        }

        public List<MarketCommodityList> getMarketCommodityList() {
            return marketCommodityList;
        }

        public List<BandCommodityList> getBandCommodityList() {
            return bandCommodityList;
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

