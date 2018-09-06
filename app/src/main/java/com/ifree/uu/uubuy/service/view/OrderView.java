package com.ifree.uu.uubuy.service.view;

import com.ifree.uu.uubuy.service.entity.OrderEntity;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/21.
 * Description:
 */
public interface OrderView extends View {
    void onSuccess(OrderEntity mOrderEntity);
    void onError(String result);
}
