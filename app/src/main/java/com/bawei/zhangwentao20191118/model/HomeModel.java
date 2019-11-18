package com.bawei.zhangwentao20191118.model;

import com.bawei.zhangwentao20191118.api.HomeService;
import com.bawei.zhangwentao20191118.contract.IHomeContract;
import com.bawei.zhangwentao20191118.entity.HomeEntity;
import com.bawei.zhangwentao20191118.utils.NetCallBack;
import com.bawei.zhangwentao20191118.utils.RetrofitUtil;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者：张文涛
 * 功能：首页的M层
 * 时间：2019年11月18日11:31:30
 */
public class HomeModel implements IHomeContract.IHomeModel {
    @Override
    public void getHomeData(HashMap<String, String> params, int page, int count, NetCallBack netCallBack) {
        RetrofitUtil.getInstance().create(HomeService.class)
                .getHomeData(params, page, count).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<HomeEntity>() {
                    @Override
                    public void accept(HomeEntity homeEntity) throws Exception {
                        if (netCallBack != null) {
                            netCallBack.success(homeEntity);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        netCallBack.failure("网络可能有问题");
                    }
                });


    }
}
