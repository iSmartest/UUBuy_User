package com.ifree.uu.uubuy.service.view;

import com.ifree.uu.uubuy.service.entity.SecondActivitiesEntity;

/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2018/9/14 0014
 * Description:
 */
public interface SecondListView extends View {
    void onSuccess(SecondActivitiesEntity mSecondListEntity);
    void onError(String result);
}
