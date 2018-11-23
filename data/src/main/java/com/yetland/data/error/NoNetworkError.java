package com.yetland.data.error;

/**
 * @author YETLAND
 * @date 2018/11/22 16:36
 */
public class NoNetworkError extends Throwable {
    @Override
    public String getMessage() {
        return "无网络连接";
    }
}
