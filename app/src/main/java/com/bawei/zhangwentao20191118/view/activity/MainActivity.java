package com.bawei.zhangwentao20191118.view.activity;

import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bawei.zhangwentao20191118.R;
import com.bawei.zhangwentao20191118.base.BaseActivity;
import com.bawei.zhangwentao20191118.utils.RetrofitUtil;
import com.bawei.zhangwentao20191118.view.fragment.HomeFragment;
import com.bawei.zhangwentao20191118.view.fragment.MyFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：张文涛
 * 功能：主页
 * 时间：2019年11月18日11:31:30
 */
public class MainActivity extends BaseActivity {
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.group)
    RadioGroup group;
    private ArrayList<Fragment> list;

    @Override
    public int bindLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        // 创建 Fragment 集合
        list = new ArrayList<>();
        list.add(new HomeFragment());
        list.add(new MyFragment());
        // ViewPager适配器
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }
            @Override
            public int getCount() {
                return list.size();
            }
        });
        // 点击切换
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                group.check(viewPager.getChildAt(position).getId());
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        // 滑动切换
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rbt1:
                        viewPager.setCurrentItem(0, false);
                        break;
                    case R.id.rbt2:
                        viewPager.setCurrentItem(1, false);
                        break;
                }
            }
        });

    }

    @Override
    protected void initData() {

    }
}
