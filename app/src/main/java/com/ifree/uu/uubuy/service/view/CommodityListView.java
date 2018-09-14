package com.ifree.uu.uubuy.service.view;

import com.ifree.uu.uubuy.service.entity.CommodityListEntity;
import com.ifree.uu.uubuy.service.entity.CouponEntity;

/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2018/9/14 0014
 * Description:
 */
public interface CommodityListView extends View {
    void onSuccess(CommodityListEntity mCommodityListEntity);
    void onError(String result);
}
