package com.ifree.uu.uubuy.service.entity;

import java.util.List;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/23.
 * Description:
 */
public class MessageEntity {
    private String msg;
    private String resultCode;
    private DataBean data;
    public static class DataBean{
        private List<NotifyList> notifyList;
        public static class NotifyList{
            private String messageSendTime;
            private String messageId;
            private String messageTitle;
            private String messageContent;
            private String messageTime;
            private String messageType;
            private String activitiesId;
            private String activitiesType;
            private String type;

            public String getMessageSendTime() {
                return messageSendTime;
            }

            public void setMessageSendTime(String messageSendTime) {
                this.messageSendTime = messageSendTime;
            }

            public String getMessageId() {
                return messageId;
            }

            public void setMessageId(String messageId) {
                this.messageId = messageId;
            }

            public String getMessageTitle() {
                return messageTitle;
            }

            public void setMessageTitle(String messageTitle) {
                this.messageTitle = messageTitle;
            }

            public String getMessageContent() {
                return messageContent;
            }

            public void setMessageContent(String messageContent) {
                this.messageContent = messageContent;
            }

            public String getMessageTime() {
                return messageTime;
            }

            public void setMessageTime(String messageTime) {
                this.messageTime = messageTime;
            }

            public String getMessageType() {
                return messageType;
            }

            public void setMessageType(String messageType) {
                this.messageType = messageType;
            }

            public String getActivitiesId() {
                return activitiesId;
            }

            public void setActivitiesId(String activitiesId) {
                this.activitiesId = activitiesId;
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

        public List<NotifyList> getNotifyList() {
            return notifyList;
        }

        public void setNotifyList(List<NotifyList> notifyList) {
            this.notifyList = notifyList;
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
