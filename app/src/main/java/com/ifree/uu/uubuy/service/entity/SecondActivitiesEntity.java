package com.ifree.uu.uubuy.service.entity;

import java.util.List;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/24.
 * Description:
 */
public class SecondActivitiesEntity {
    private String result;
    private String resultCode;
    private String bandStorePic;
    private String fristActivitiesName;
    private String fristActivitiesPic;
    private String fristActivitiesTime;
    private String fristActivitiesAdAddress;
    private String fristActivitiesIsCollection;
    private List<SecondActivitiesList> secondActivitiesList;
    private List<MarketCommodityList> marketCommodityList;
    private List<BandCommodityList> bandCommodityList;

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

    public String getBandStorePic() {
        return bandStorePic;
    }

    public void setBandStorePic(String bandStorePic) {
        this.bandStorePic = bandStorePic;
    }

    public String getFristActivitiesName() {
        return fristActivitiesName;
    }

    public void setFristActivitiesName(String fristActivitiesName) {
        this.fristActivitiesName = fristActivitiesName;
    }

    public String getFristActivitiesPic() {
        return fristActivitiesPic;
    }

    public void setFristActivitiesPic(String fristActivitiesPic) {
        this.fristActivitiesPic = fristActivitiesPic;
    }

    public String getFristActivitiesTime() {
        return fristActivitiesTime;
    }

    public void setFristActivitiesTime(String fristActivitiesTime) {
        this.fristActivitiesTime = fristActivitiesTime;
    }

    public String getFristActivitiesAdAddress() {
        return fristActivitiesAdAddress;
    }

    public void setFristActivitiesAdAddress(String fristActivitiesAdAddress) {
        this.fristActivitiesAdAddress = fristActivitiesAdAddress;
    }

    public String getFristActivitiesIsCollection() {
        return fristActivitiesIsCollection;
    }

    public void setFristActivitiesIsCollection(String fristActivitiesIsCollection) {
        this.fristActivitiesIsCollection = fristActivitiesIsCollection;
    }

    public List<SecondActivitiesList> getSecondActivitiesList() {
        return secondActivitiesList;
    }

    public void setSecondActivitiesList(List<SecondActivitiesList> secondActivitiesList) {
        this.secondActivitiesList = secondActivitiesList;
    }

    public List<MarketCommodityList> getMarketCommodityList() {
        return marketCommodityList;
    }

    public void setMarketCommodityList(List<MarketCommodityList> marketCommodityList) {
        this.marketCommodityList = marketCommodityList;
    }

    public List<BandCommodityList> getBandCommodityList() {
        return bandCommodityList;
    }

    public void setBandCommodityList(List<BandCommodityList> bandCommodityList) {
        this.bandCommodityList = bandCommodityList;
    }

    public static class SecondActivitiesList {
        private String secondActivitiesId;
        private String secondActivitiesName;
        private String secondActivitiesTime;
        private String secondActivitiesAdAddress;
        private String secondActivitiesPrice;
        private String secondActivitiesType;
        private String secondActivitiesIsCollection;

        public String getSecondActivitiesId() {
            return secondActivitiesId;
        }

        public void setSecondActivitiesId(String secondActivitiesId) {
            this.secondActivitiesId = secondActivitiesId;
        }

        public String getSecondActivitiesName() {
            return secondActivitiesName;
        }

        public void setSecondActivitiesName(String secondActivitiesName) {
            this.secondActivitiesName = secondActivitiesName;
        }

        public String getSecondActivitiesTime() {
            return secondActivitiesTime;
        }

        public void setSecondActivitiesTime(String secondActivitiesTime) {
            this.secondActivitiesTime = secondActivitiesTime;
        }

        public String getSecondActivitiesAdAddress() {
            return secondActivitiesAdAddress;
        }

        public void setSecondActivitiesAdAddress(String secondActivitiesAdAddress) {
            this.secondActivitiesAdAddress = secondActivitiesAdAddress;
        }

        public String getSecondActivitiesPrice() {
            return secondActivitiesPrice;
        }

        public void setSecondActivitiesPrice(String secondActivitiesPrice) {
            this.secondActivitiesPrice = secondActivitiesPrice;
        }

        public String getSecondActivitiesType() {
            return secondActivitiesType;
        }

        public void setSecondActivitiesType(String secondActivitiesType) {
            this.secondActivitiesType = secondActivitiesType;
        }

        public String getSecondActivitiesIsCollection() {
            return secondActivitiesIsCollection;
        }

        public void setSecondActivitiesIsCollection(String secondActivitiesIsCollection) {
            this.secondActivitiesIsCollection = secondActivitiesIsCollection;
        }
    }

    public static class MarketCommodityList {
        private String commodityTitle;
        private List<CommodityList> commodityList;

        public String getCommodityTitle() {
            return commodityTitle;
        }

        public void setCommodityTitle(String commodityTitle) {
            this.commodityTitle = commodityTitle;
        }

        public List<CommodityList> getCommodityList() {
            return commodityList;
        }

        public void setCommodityList(List<CommodityList> commodityList) {
            this.commodityList = commodityList;
        }

        public static class CommodityList {
            private String commodityId;
            private String commodityName;
            private String commodityPic;
            private String commodityOriginalPrice;
            private String commodityNowPrice;
            private String commodityDec;
            private String commodityTime;
            private String commodityDescent;
            private String commodityType;

            public String getCommodityId() {
                return commodityId;
            }

            public void setCommodityId(String commodityId) {
                this.commodityId = commodityId;
            }

            public String getCommodityName() {
                return commodityName;
            }

            public void setCommodityName(String commodityName) {
                this.commodityName = commodityName;
            }

            public String getCommodityPic() {
                return commodityPic;
            }

            public void setCommodityPic(String commodityPic) {
                this.commodityPic = commodityPic;
            }

            public String getCommodityOriginalPrice() {
                return commodityOriginalPrice;
            }

            public void setCommodityOriginalPrice(String commodityOriginalPrice) {
                this.commodityOriginalPrice = commodityOriginalPrice;
            }

            public String getCommodityNowPrice() {
                return commodityNowPrice;
            }

            public void setCommodityNowPrice(String commodityNowPrice) {
                this.commodityNowPrice = commodityNowPrice;
            }

            public String getCommodityDec() {
                return commodityDec;
            }

            public void setCommodityDec(String commodityDec) {
                this.commodityDec = commodityDec;
            }

            public String getCommodityTime() {
                return commodityTime;
            }

            public void setCommodityTime(String commodityTime) {
                this.commodityTime = commodityTime;
            }

            public String getCommodityDescent() {
                return commodityDescent;
            }

            public void setCommodityDescent(String commodityDescent) {
                this.commodityDescent = commodityDescent;
            }

            public String getCommodityType() {
                return commodityType;
            }

            public void setCommodityType(String commodityType) {
                this.commodityType = commodityType;
            }
        }
    }
    public static class BandCommodityList {
        private String commodityId;
        private String commodityName;
        private String commodityPic;
        private String commodityDes;
        private String commodityType;

        public String getCommodityId() {
            return commodityId;
        }

        public void setCommodityId(String commodityId) {
            this.commodityId = commodityId;
        }

        public String getCommodityName() {
            return commodityName;
        }

        public void setCommodityName(String commodityName) {
            this.commodityName = commodityName;
        }

        public String getCommodityPic() {
            return commodityPic;
        }

        public void setCommodityPic(String commodityPic) {
            this.commodityPic = commodityPic;
        }

        public String getCommodityDes() {
            return commodityDes;
        }

        public void setCommodityDes(String commodityDes) {
            this.commodityDes = commodityDes;
        }

        public String getCommodityType() {
            return commodityType;
        }

        public void setCommodityType(String commodityType) {
            this.commodityType = commodityType;
        }
    }

}

