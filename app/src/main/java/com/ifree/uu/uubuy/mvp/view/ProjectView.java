package com.ifree.uu.uubuy.mvp.view;

/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2018/9/29 0029
 * Description:
 */
public interface ProjectView<T>extends View {
    void onSuccess(T t);
    void onError(String result);
}
