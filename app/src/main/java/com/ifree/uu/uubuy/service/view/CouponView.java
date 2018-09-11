package com.ifree.uu.uubuy.service.view;

import com.ifree.uu.uubuy.service.entity.CouponEntity;
import com.ifree.uu.uubuy.service.entity.UserInfoEntity;

/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2018/9/10 0010
 * Description:
 */
public interface CouponView extends View{
    void onSuccess(CouponEntity mCouponEntity);
    void onError(String result);
}
