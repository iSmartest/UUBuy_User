package com.ifree.uu.uubuy.service.view;

import com.ifree.uu.uubuy.service.entity.MessageEntity;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/23.
 * Description:
 */
public interface MessageView extends View {
    void onSuccess(MessageEntity mMessageEntity);
    void onError(String result);
}
