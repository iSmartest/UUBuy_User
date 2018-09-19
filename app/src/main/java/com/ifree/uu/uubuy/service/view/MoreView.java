package com.ifree.uu.uubuy.service.view;

import com.ifree.uu.uubuy.service.entity.MoreEntity;

/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2018/9/19 0019
 * Description:
 */
public interface MoreView extends View {
    void onSuccess(MoreEntity mMoreEntity);
    void onError(String result);
}
