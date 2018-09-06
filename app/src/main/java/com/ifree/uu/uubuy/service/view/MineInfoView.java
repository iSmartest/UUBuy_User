package com.ifree.uu.uubuy.service.view;

import com.ifree.uu.uubuy.service.entity.MineEntity;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/21.
 * Description:
 */
public interface MineInfoView extends View {
    void onSuccess(MineEntity mMineEntity);
    void onError(String result);
}
