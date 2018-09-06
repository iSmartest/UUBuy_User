package com.ifree.uu.uubuy.service.entity;

import java.util.List;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/23.
 * Description:
 */
public class MenuClassifyEntity {
    private String result;
    private String resultCode;
    private List<FirstMenuList> firstMenuList;
    public static class FirstMenuList{
        private String menuId;
        private String menuName;
        private String type;
        private boolean open;
        private List<SecondMenuList>secondMenuList;
        public static class SecondMenuList{
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

        public boolean isOpen() {
            return open;
        }

        public void setOpen(boolean open) {
            this.open = open;
        }

        public List<SecondMenuList> getSecondMenuList() {
            return secondMenuList;
        }

        public void setSecondMenuList(List<SecondMenuList> secondMenuList) {
            this.secondMenuList = secondMenuList;
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

    public List<FirstMenuList> getFirstMenuList() {
        return firstMenuList;
    }

    public void setFirstMenuList(List<FirstMenuList> firstMenuList) {
        this.firstMenuList = firstMenuList;
    }
}
