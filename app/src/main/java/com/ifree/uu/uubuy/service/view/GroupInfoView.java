package com.ifree.uu.uubuy.service.view;

import com.ifree.uu.uubuy.service.entity.GroupEntity;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/22.
 * Description:
 */
public interface GroupInfoView extends View {
    void onSuccess(GroupEntity mGroupEntity);
    void onError(String result);
}
