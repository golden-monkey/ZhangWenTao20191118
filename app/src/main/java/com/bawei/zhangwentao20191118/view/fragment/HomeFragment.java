package com.bawei.zhangwentao20191118.view.fragment;

import android.widget.TextView;
import android.widget.Toast;

import com.bawei.zhangwentao20191118.R;
import com.bawei.zhangwentao20191118.app.App;
import com.bawei.zhangwentao20191118.base.BaseFragment;
import com.bawei.zhangwentao20191118.contract.IHomeContract;
import com.bawei.zhangwentao20191118.entity.HomeEntity;
import com.bawei.zhangwentao20191118.presenter.HomePresenter;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者：张文涛
 * 功能：首页
 * 时间：2019年11月18日11:31:30
 */

public class HomeFragment extends BaseFragment implements IHomeContract.IHomeView {


    @BindView(R.id.txt)
    TextView txt;
    private HomePresenter homePresenter;

    @Override
    public int bindLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        // 获取输入的内容
        String txt = this.txt.getText().toString();

        homePresenter = new HomePresenter(this);
        homePresenter.attach(this);
        HashMap<String, String> params = new HashMap<>();
        params.put("title", txt);
        homePresenter.getHomeData(params, 1, 5);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void success(Object data) {
        if (data instanceof HomeEntity) {
            Toast.makeText(getContext(), (CharSequence) ((HomeEntity) data).getResult(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void failure(String error) {
        Toast.makeText(App.context, error, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.txt)
    public void onViewClicked() {

    }
}
