package com.ifree.uu.uubuy.service.view;

import com.ifree.uu.uubuy.service.entity.ActivitiesEntity;
import com.ifree.uu.uubuy.service.entity.AroundEntity;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/20.
 * Description:
 */
public interface ActivitiesView extends View{
    void onSuccess(ActivitiesEntity mActivitiesEntity);
    void onError(String result);
}
