package com.ifree.uu.uubuy.service.entity;

import java.util.List;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/13.
 * Description:
 */
public class HomeEntity {
    private String msg;
    private String resultCode;
    private DataBean data;
    public static class DataBean{
        private List<BannerList> bannerList;
        private List<AdTypeList> adTypeList;
        private List<UURecommendNotice> uuRecommendNotice;
        private List<CityADList> cityADList;
        private List<RotateADList> rotateADList;
        private List<ActivitiesList> activitiesList;

        public static class BannerList{
            private String bannerPic;
            private String bannerTitle;
            private String bannerDetails;

            public String getBannerPic() {
                return bannerPic;
            }

            public void setBannerPic(String bannerPic) {
                this.bannerPic = bannerPic;
            }

            public String getBannerTitle() {
                return bannerTitle;
            }

            public void setBannerTitle(String bannerTitle) {
                this.bannerTitle = bannerTitle;
            }

            public String getBannerDetails() {
                return bannerDetails;
            }

            public void setBannerDetails(String bannerDetails) {
                this.bannerDetails = bannerDetails;
            }
        }
        public static class AdTypeList{
            private String adTypeId;
            private String type;
            private String adTypeIcon;
            private String adTypeTitle;

            public String getAdTypeId() {
                return adTypeId;
            }

            public void setAdTypeId(String adTypeId) {
                this.adTypeId = adTypeId;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getAdTypeIcon() {
                return adTypeIcon;
            }

            public void setAdTypeIcon(String adTypeIcon) {
                this.adTypeIcon = adTypeIcon;
            }

            public String getAdTypeTitle() {
                return adTypeTitle;
            }

            public void setAdTypeTitle(String adTypeTitle) {
                this.adTypeTitle = adTypeTitle;
            }
        }

        public static class UURecommendNotice{
            private String uuRecommendId;
            private String uuRecommentContent;
            private String uuRecommendName;
            private String uuRecommentType;
            private String type;

            public String getUuRecommendId() {
                return uuRecommendId;
            }

            public void setUuRecommendId(String uuRecommendId) {
                this.uuRecommendId = uuRecommendId;
            }

            public String getUuRecommentContent() {
                return uuRecommentContent;
            }

            public void setUuRecommentContent(String uuRecommentContent) {
                this.uuRecommentContent = uuRecommentContent;
            }

            public String getUuRecommendName() {
                return uuRecommendName;
            }

            public void setUuRecommendName(String uuRecommendName) {
                this.uuRecommendName = uuRecommendName;
            }

            public String getUuRecommentType() {
                return uuRecommentType;
            }

            public void setUuRecommentType(String uuRecommentType) {
                this.uuRecommentType = uuRecommentType;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }

        public static class CityADList{
            private String cityADId;
            private String cityADName;
            private String cityADPic;
            private String cityADStartTime;
            private String cityADEndTime;
            private String cityADType;
            private String type;

            public String getCityADId() {
                return cityADId;
            }

            public void setCityADId(String cityADId) {
                this.cityADId = cityADId;
            }

            public String getCityADName() {
                return cityADName;
            }

            public void setCityADName(String cityADName) {
                this.cityADName = cityADName;
            }

            public String getCityADPic() {
                return cityADPic;
            }

            public void setCityADPic(String cityADPic) {
                this.cityADPic = cityADPic;
            }

            public String getCityADStartTime() {
                return cityADStartTime;
            }

            public void setCityADStartTime(String cityADStartTime) {
                this.cityADStartTime = cityADStartTime;
            }

            public String getCityADEndTime() {
                return cityADEndTime;
            }

            public void setCityADEndTime(String cityADEndTime) {
                this.cityADEndTime = cityADEndTime;
            }

            public String getCityADType() {
                return cityADType;
            }

            public void setCityADType(String cityADType) {
                this.cityADType = cityADType;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }

        public static class RotateADList{
            private String rotateADId;
            private String rotateName;
            private String rotateADIcon;
            private String rotateADType;
            private String type;

            public String getRotateADId() {
                return rotateADId;
            }

            public void setRotateADId(String rotateADId) {
                this.rotateADId = rotateADId;
            }

            public String getRotateName() {
                return rotateName;
            }

            public void setRotateName(String rotateName) {
                this.rotateName = rotateName;
            }

            public String getRotateADIcon() {
                return rotateADIcon;
            }

            public void setRotateADIcon(String rotateADIcon) {
                this.rotateADIcon = rotateADIcon;
            }

            public String getRotateADType() {
                return rotateADType;
            }

            public void setRotateADType(String rotateADType) {
                this.rotateADType = rotateADType;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }

        public static class ActivitiesList{
            private String activitiesId;
            private String activitiesPic;
            private String activitiesName;
            private String activitiesTime;
            private String activitiesAdAddress;
            private String activitiesType;
            private String type;
            public String getActivitiesId() {
                return activitiesId;
            }

            public void setActivitiesId(String activitiesId) {
                this.activitiesId = activitiesId;
            }

            public String getActivitiesPic() {
                return activitiesPic;
            }

            public void setActivitiesPic(String activitiesPic) {
                this.activitiesPic = activitiesPic;
            }

            public String getActivitiesName() {
                return activitiesName;
            }

            public void setActivitiesName(String activitiesName) {
                this.activitiesName = activitiesName;
            }

            public String getActivitiesTime() {
                return activitiesTime;
            }

            public void setActivitiesTime(String activitiesTime) {
                this.activitiesTime = activitiesTime;
            }

            public String getActivitiesAdAddress() {
                return activitiesAdAddress;
            }

            public void setActivitiesAdAddress(String activitiesAdAddress) {
                this.activitiesAdAddress = activitiesAdAddress;
            }

            public String getActivitiesType() {
                return activitiesType;
            }

            public void setActivitiesType(String activitiesType) {
                this.activitiesType = activitiesType;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }

        public List<BannerList> getBannerList() {
            return bannerList;
        }

        public void setBannerList(List<BannerList> bannerList) {
            this.bannerList = bannerList;
        }

        public List<AdTypeList> getAdTypeList() {
            return adTypeList;
        }

        public void setAdTypeList(List<AdTypeList> adTypeList) {
            this.adTypeList = adTypeList;
        }

        public List<UURecommendNotice> getUuRecommendNotice() {
            return uuRecommendNotice;
        }

        public void setUuRecommendNotice(List<UURecommendNotice> uuRecommendNotice) {
            this.uuRecommendNotice = uuRecommendNotice;
        }

        public List<CityADList> getCityADList() {
            return cityADList;
        }

        public void setCityADList(List<CityADList> cityADList) {
            this.cityADList = cityADList;
        }

        public List<RotateADList> getRotateADList() {
            return rotateADList;
        }

        public void setRotateADList(List<RotateADList> rotateADList) {
            this.rotateADList = rotateADList;
        }

        public List<ActivitiesList> getActivitiesList() {
            return activitiesList;
        }

        public void setActivitiesList(List<ActivitiesList> activitiesList) {
            this.activitiesList = activitiesList;
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
