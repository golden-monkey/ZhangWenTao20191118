package com.bawei.zhangwentao20191118.app;

import android.app.Application;
import android.content.Context;
/**
 * 作者：张文涛
 * 功能：获取上下文
 * 时间：2019年11月18日11:31:30
 */
public class App extends Application {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
}
