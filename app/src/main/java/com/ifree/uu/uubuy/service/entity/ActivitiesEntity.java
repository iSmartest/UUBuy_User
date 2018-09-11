package com.ifree.uu.uubuy.service.entity;

import java.util.List;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/20.
 * Description:
 */
public class ActivitiesEntity {
    private String msg;
    private String resultCode;
    private DataBean data;
    public static class DataBean{
        private List<ActivitiesList> activitiesList;
        public static class ActivitiesList{
            private String activitiesId;
            private String activitiesPic;
            private String activitiesName;
            private String activitiesDes;
            private String activitiesAdAddress;
            private String activitiesOriginalPrice;
            private String activitiesPresentPrice;
            private String activitiesSurplusNum;
            private String isOver;
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

            public String getActivitiesDes() {
                return activitiesDes;
            }

            public void setActivitiesDes(String activitiesDes) {
                this.activitiesDes = activitiesDes;
            }

            public String getActivitiesAdAddress() {
                return activitiesAdAddress;
            }

            public void setActivitiesAdAddress(String activitiesAdAddress) {
                this.activitiesAdAddress = activitiesAdAddress;
            }

            public String getActivitiesOriginalPrice() {
                return activitiesOriginalPrice;
            }

            public void setActivitiesOriginalPrice(String activitiesOriginalPrice) {
                this.activitiesOriginalPrice = activitiesOriginalPrice;
            }

            public String getActivitiesPresentPrice() {
                return activitiesPresentPrice;
            }

            public void setActivitiesPresentPrice(String activitiesPresentPrice) {
                this.activitiesPresentPrice = activitiesPresentPrice;
            }

            public String getActivitiesSurplusNum() {
                return activitiesSurplusNum;
            }

            public void setActivitiesSurplusNum(String activitiesSurplusNum) {
                this.activitiesSurplusNum = activitiesSurplusNum;
            }

            public String getIsOver() {
                return isOver;
            }

            public void setIsOver(String isOver) {
                this.isOver = isOver;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }

        public List<ActivitiesList> getActivitiesList() {
            return activitiesList;
        }

        public void setActivitiesList(List<ActivitiesList> activitiesList) {
            this.activitiesList = activitiesList;
        }
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
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
}
