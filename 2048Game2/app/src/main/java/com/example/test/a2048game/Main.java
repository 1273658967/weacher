package com.example.test.a2048game;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 陈芳微 on 2019/6/17.
 */

public class Main extends AppCompatActivity implements ViewPager.OnPageChangeListener{
    private ViewPager viewPager;
    private int [] imageIdArray;//图片数组的资源
    private List<View> viewList;//图片资源的集合
    private ViewGroup viewGroup;//放置小圆点

    //实例化原点View
    private ImageView iv_point;
    private ImageView []ivPointArray;
    private Button button;

    //最后一页的按钮
    private ImageButton ib_start;
    private LinearLayout.LayoutParams layoutParams;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.but);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main.this,MainActivity.class));
                finish();
            }
        });
        initViewPager();
        initPoint();
    }

    private void initPoint() {
        //实例化linearLayout
        viewGroup = findViewById(R.id.ll_point);
        //根据ViewPager的item数量实例化数组
        ivPointArray = new ImageView[viewList.size()];
        //循环新建底部圆点ImageView，将生成的ImageView保存到数组中
        int size = viewList.size();
        for(int i = 0;i < size;i++){
            iv_point = new ImageView(this);
            layoutParams = new LinearLayout.LayoutParams(15,15);
            //第一个页面需要设置为选中状态，这里采用两张不同的图片
            if (i == 0){
                iv_point.setBackgroundResource(R.drawable.dot_2);
            }else{
                layoutParams.leftMargin=20;
                iv_point.setBackgroundResource(R.drawable.dot_1);
            }
            iv_point.setLayoutParams(layoutParams);
            iv_point.setPadding(30,0,30,0);//left,top,right,bottom
            ivPointArray[i] = iv_point;

            //将数组中的ImageView加入到ViewGroup
            viewGroup.addView(ivPointArray[i]);
        }
    }

    private void initViewPager() {
        viewPager = findViewById(R.id.viewPager);
        imageIdArray = new int[]{R.drawable.img1,R.drawable.img2,R.drawable.img3};
        viewList = new ArrayList<>();
        //获取一个Layout参数，设置为全屏
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.
                LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        //循环创建View并加入到集合中
        int len = imageIdArray.length;
        for(int i = 0;i < len;i++){
            //new ImageView并设置全屏和图片资源
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(params);
            imageView.setImageResource(imageIdArray[i]);
            //将ImageView将入到集合中
            viewList.add(imageView);
        }
        //View集合初始化好后，设置Adapter
        viewPager.setAdapter(new MainActivityAdpater(viewList));
        //设置滑动监听
        viewPager.addOnPageChangeListener((ViewPager.OnPageChangeListener) Main.this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    //滑动后的监听
    @Override
    public void onPageSelected(int position) {
        //循环设置当前页面的标记图
        //循环设置当前页的标记图
        int length = imageIdArray.length;
        for (int i = 0;i<length;i++){
            ivPointArray[position].setBackgroundResource(R.drawable.dot_2);
            if (position != i){
                ivPointArray[i].setBackgroundResource(R.drawable.dot_1);
            }
        }

       //判断是否是最后一页，若是则显示按钮
        if (position == imageIdArray.length - 1){
            button.setVisibility(View.VISIBLE);
        }else {
            button.setVisibility(View.GONE);
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
