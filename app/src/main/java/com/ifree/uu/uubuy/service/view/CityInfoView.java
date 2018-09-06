package com.ifree.uu.uubuy.service.view;

import com.ifree.uu.uubuy.service.entity.CityInfoEntity;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/9/6.
 * Description:
 */
public interface CityInfoView extends View {
    void onSuccess(CityInfoEntity mCityInfoEntity);
    void onError(String result);
}
