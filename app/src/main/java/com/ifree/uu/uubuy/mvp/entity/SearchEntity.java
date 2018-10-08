package com.ifree.uu.uubuy.mvp.entity;

import java.util.List;

/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2018/9/19 0019
 * Description:
 */
public class SearchEntity {
    private String msg;
    private String resultCode;
    private DataBean data;
    public static class DataBean{
        private List<ActivitiesList> activitiesList;
        public static class ActivitiesList{
            private String activitiesId;//用于跳转到具体活动
            private String activitiesName; //活动名称
            private String activitiesPic;//活动图片
            private String activitiesTime; //活动时间
            private String activitiesAdAddress; //活动地址
            private String activitiesType;//0商场1店铺2商品
            private String type;//活动类型1普通2超市3品牌
            private String activitiesPrice;//价格

            public String getActivitiesId() {
                return activitiesId;
            }

            public void setActivitiesId(String activitiesId) {
                this.activitiesId = activitiesId;
            }

            public String getActivitiesName() {
                return activitiesName;
            }

            public void setActivitiesName(String activitiesName) {
                this.activitiesName = activitiesName;
            }

            public String getActivitiesPic() {
                return activitiesPic;
            }

            public void setActivitiesPic(String activitiesPic) {
                this.activitiesPic = activitiesPic;
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

            public String getActivitiesPrice() {
                return activitiesPrice;
            }

            public void setActivitiesPrice(String activitiesPrice) {
                this.activitiesPrice = activitiesPrice;
            }
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
