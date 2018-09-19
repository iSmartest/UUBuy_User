package com.ifree.uu.uubuy.service.view;

import com.ifree.uu.uubuy.service.entity.CompareCommodityEntity;

/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2018/9/19 0019
 * Description:
 */
public interface CompareView extends View {
    void onSuccess(CompareCommodityEntity mCompareCommodityEntity);
    void onError(String result);
}
