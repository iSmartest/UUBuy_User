package com.ifree.uu.uubuy.service.view;

import com.ifree.uu.uubuy.service.entity.HotKeyWordEntity;

/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2018/9/21 0021
 * Description:
 */
public interface HotKeyWordView extends View{
    void onSuccess(HotKeyWordEntity mHotKeyWordEntity);
    void onError(String result);
}
