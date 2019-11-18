package com.bawei.zhangwentao20191118.api;

import com.bawei.zhangwentao20191118.entity.HomeEntity;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
/**
 * 作者：张文涛
 * 功能：地址
 * 时间：2019年11月18日11:31:30
 */
public interface HomeService {
    @GET("/techApi/information/v1/findInformationByTitle")
    Observable<HomeEntity> getHomeData(@QueryMap HashMap<String, String> params, @Query("page") int page, @Query("count") int count);
}
