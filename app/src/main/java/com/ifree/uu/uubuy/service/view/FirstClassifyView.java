package com.ifree.uu.uubuy.service.view;

import com.ifree.uu.uubuy.service.entity.FirstClassifyEntity;

/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2018/9/11 0011
 * Description:
 */
public interface FirstClassifyView extends View {
    void onSuccess(FirstClassifyEntity mFirstClassifyEntity);
    void onError(String result);
}
