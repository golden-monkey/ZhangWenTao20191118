package com.bawei.zhangwentao20191118.base.mvp;

import java.lang.ref.WeakReference;

public abstract class IBasePresenter<V, v> {
    private V view;
    private WeakReference<V> weakReference;

    // 绑定
    public void attach(V v) {
        this.view = v;
        weakReference = new WeakReference<>(v);
    }

    // 解绑
    public void detach() {
        if (weakReference != null) {
            weakReference.clear();
            weakReference = null;
            this.view = null;
        }
    }
}
