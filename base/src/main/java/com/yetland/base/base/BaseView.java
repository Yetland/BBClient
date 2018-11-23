package com.yetland.base.base;

/**
 * @author YETLAND
 * @date 2018/10/8 16:55
 */
public interface BaseView<T> {
    void loading(String msg);

    void success(T t);

    void failed(String msg);
}
