package com.ifree.uu.uubuy.mvp.entity;

import java.util.List;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/23.
 * Description:
 */
public class FirstClassifyEntity {
    private String msg;
    private String resultCode;
    private DataBean data;
    public static class DataBean{
        private List<MenuList> menuList;
        private List<FristActivitiesList> fristActivitiesList;
        public static class MenuList{
            private String menuId;
            private String menuName;
            private String menuNameInfo;
            private String type;
            private boolean open;
            private List<SecondList>secondList;
            public static class SecondList{
                private String menuId;
                private String menuName;

                public String getMenuId() {
                    return menuId;
                }

                public void setMenuId(String menuId) {
                    this.menuId = menuId;
                }

                public String getMenuName() {
                    return menuName;
                }

                public void setMenuName(String menuName) {
                    this.menuName = menuName;
                }
            }

            public String getMenuId() {
                return menuId;
            }

            public void setMenuId(String menuId) {
                this.menuId = menuId;
            }

            public String getMenuName() {
                return menuName;
            }

            public void setMenuName(String menuName) {
                this.menuName = menuName;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getMenuNameInfo() {
                return menuNameInfo;
            }

            public void setMenuNameInfo(String menuNameInfo) {
                this.menuNameInfo = menuNameInfo;
            }

            public boolean isOpen() {
                return open;
            }

            public void setOpen(boolean open) {
                this.open = open;
            }

            public List<SecondList> getSecondList() {
                return secondList;
            }

            public void setSecondList(List<SecondList> secondList) {
                this.secondList = secondList;
            }
        }
        public static class FristActivitiesList{
            private String fristActivitiesId;//用于跳转到具体店铺
            private String fristActivitiesName; //活动名称
            private String fristActivitiesPic;//活动图片
            private String fristActivitiesTime; //活动时间
            private String fristActivitiesAdAddress; //活动地址
            private String fristActivitiesPrice;//价格
            private String fristActivitiesType;//活动type:""//活动类型1普通2超市3品牌

            public String getFristActivitiesId() {
                return fristActivitiesId;
            }

            public void setFristActivitiesId(String fristActivitiesId) {
                this.fristActivitiesId = fristActivitiesId;
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

            public String getFristActivitiesPrice() {
                return fristActivitiesPrice;
            }

            public void setFristActivitiesPrice(String fristActivitiesPrice) {
                this.fristActivitiesPrice = fristActivitiesPrice;
            }

            public String getFristActivitiesType() {
                return fristActivitiesType;
            }

            public void setFristActivitiesType(String fristActivitiesType) {
                this.fristActivitiesType = fristActivitiesType;
            }
        }

        public List<MenuList> getMenuList() {
            return menuList;
        }

        public void setMenuList(List<MenuList> menuList) {
            this.menuList = menuList;
        }

        public List<FristActivitiesList> getFristActivitiesList() {
            return fristActivitiesList;
        }

        public void setFristActivitiesList(List<FristActivitiesList> fristActivitiesList) {
            this.fristActivitiesList = fristActivitiesList;
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
