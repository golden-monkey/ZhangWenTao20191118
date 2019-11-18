package com.bawei.zhangwentao20191118.presenter;

import com.bawei.zhangwentao20191118.base.mvp.IBasePresenter;
import com.bawei.zhangwentao20191118.contract.IHomeContract;
import com.bawei.zhangwentao20191118.model.HomeModel;
import com.bawei.zhangwentao20191118.utils.NetCallBack;

import java.util.HashMap;

/**
 * 作者：张文涛
 * 功能：首页的P层
 * 时间：2019年11月18日11:31:30
 */
public class HomePresenter extends IHomeContract.IHomePresenter {

    private HomeModel model;
    public IHomeContract.IHomeView iHomeView;

    public HomePresenter(IHomeContract.IHomeView iHomeView) {
        this.iHomeView = iHomeView;
        model = new HomeModel();
    }

    @Override
    public void getHomeData(HashMap<String, String> params, int page, int count) {
        model.getHomeData(params ,page, count, new NetCallBack() {
            @Override
            public void success(Object o) {
                if (iHomeView != null) {
                    iHomeView.success(o);
                }
            }
            @Override
            public void failure(String error) {
                iHomeView.failure(error);
            }
        });
    }
}
