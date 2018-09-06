package com.ifree.uu.uubuy.service.view;

import com.ifree.uu.uubuy.service.entity.HomeEntity;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/13.
 * Description:
 */
public interface HomeView extends View {
    void onSuccess(HomeEntity mHomeEntity);
    void onError(String result);
}
