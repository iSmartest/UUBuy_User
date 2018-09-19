package com.ifree.uu.uubuy.service.view;

import com.ifree.uu.uubuy.service.entity.MyFootPrintEntity;

/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2018/9/19 0019
 * Description:
 */
public interface MyFootPrintView extends View {
    void onSuccess(MyFootPrintEntity mMyFootPrintEntity);
    void onError(String result);

}
