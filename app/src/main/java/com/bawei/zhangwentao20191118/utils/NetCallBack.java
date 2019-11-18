package com.bawei.zhangwentao20191118.utils;

public interface NetCallBack {
    // 请求成功的方法
    void success(Object o);

    // 请求失败的方法
    void failure(String error);

}
