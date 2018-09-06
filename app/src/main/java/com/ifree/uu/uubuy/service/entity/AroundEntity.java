package com.ifree.uu.uubuy.service.entity;

import java.util.List;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/20.
 * Description:
 */
public class AroundEntity {
    private String result;
    private String resultCode;
    private List<ActivitiesList> activitiesList;
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

    public List<ActivitiesList> getActivitiesList() {
        return activitiesList;
    }

    public void setActivitiesList(List<ActivitiesList> activitiesList) {
        this.activitiesList = activitiesList;
    }
}
