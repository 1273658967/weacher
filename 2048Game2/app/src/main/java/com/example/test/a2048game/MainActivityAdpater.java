package com.example.test.a2048game;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by 陈芳微 on 2019/6/17.
 */

public class MainActivityAdpater extends PagerAdapter {
    private List<View> viewList;

    public MainActivityAdpater(List<View> viewList){
        this.viewList = viewList;
    }

    //返回个体的页面数
    @Override
    public int getCount() {
        if(viewList != null){
            return viewList.size();
        }
        return 0;
    }

    //判断对象是否生成界面
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    //初始化Position的位置
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(viewList.get(position));
        return viewList.get(position);
    }

    //销毁
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(viewList.get(position));
    }
}
