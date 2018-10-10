package com.ifree.uu.uubuy.mvp.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2018/10/9 0009
 * Description:
 */
public class BrowsingBean {
    private List<Info> loginInfo;
    public List<Info> getLoginInfo() {
        return loginInfo;
    }

    public void setLoginInfo(List<Info> loginInfo) {
        this.loginInfo = loginInfo;
    }

    public static class Info implements Serializable {
        private String id;
        private int browsingVolume;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getBrowsingVolume() {
            return browsingVolume;
        }

        public void setBrowsingVolume(int browsingVolume) {
            this.browsingVolume = browsingVolume;
        }
    }

}
