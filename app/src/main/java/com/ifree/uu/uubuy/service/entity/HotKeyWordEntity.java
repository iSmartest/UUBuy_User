package com.ifree.uu.uubuy.service.entity;

import java.util.List;

/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2018/9/21 0021
 * Description:
 */
public class HotKeyWordEntity {
    private String msg;
    private String resultCode;
    private DataBean data;
    public static class DataBean{
        private List<String> keywordList;

        public List<String> getKeywordList() {
            return keywordList;
        }

        public void setKeywordList(List<String> keywordList) {
            this.keywordList = keywordList;
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
