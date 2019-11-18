package com.bawei.zhangwentao20191118.contract;

import com.bawei.zhangwentao20191118.base.mvp.IBasePresenter;
import com.bawei.zhangwentao20191118.utils.NetCallBack;

import java.util.HashMap;

/**
 * 作者：张文涛
 * 功能：主页的契约类
 * 时间：2019年11月18日10:44:56
 */
public interface IHomeContract {
    /**
     * M 层
     */
    interface IHomeModel {
        public void getHomeData(HashMap<String, String> params, int page, int count, NetCallBack netCallBack);
    }

    /**
     * V 层
     */
    interface IHomeView {
        // 请求成功的方法
        void success(Object data);

        // 请求失败的方法
        void failure(String error);
    }

    /**
     * P 层
     */
    abstract static class IHomePresenter extends IBasePresenter {
        public abstract void getHomeData(HashMap<String, String> params, int page, int count);
    }
}
