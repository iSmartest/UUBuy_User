package com.ifree.uu.uubuy.service.view;

import com.ifree.uu.uubuy.service.entity.AroundEntity;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/13.
 * Description:
 */
public interface AroundView extends View {
    void onSuccess(AroundEntity mAroundEntity);
    void onError(String result);
}
