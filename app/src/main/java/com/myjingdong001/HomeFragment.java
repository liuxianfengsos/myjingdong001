package com.myjingdong001;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.myjingdong001.util.MyImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment{

    private static final String ARG_SHOW_TEXT = "text";
    private String mContentText;
    //返回通过工厂方法获取BlankFragment
    public static HomeFragment newInstance(){
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        //args.putString(ARG_SHOW_TEXT,param1);
        //fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* if (getArguments()!=null){
            mContentText = getArguments().getString(ARG_SHOW_TEXT);
        }*/
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //FOR fragment
        View homeView = inflater.inflate(R.layout.fragment_home,container,false);
       // TextView contentTv = rootView.findViewById(R.id.content_tv);
       // contentTv.setText(mContentText);
        initBanner(homeView);
        return homeView;
    }
//4实现广告轮播
    private void initBanner(View homeView) {
        List images = new ArrayList();//创建图片结合，并存放图片
        images.add(R.drawable.image01);
        images.add(R.drawable.image02);
        images.add(R.drawable.image03);
        images.add(R.drawable.image04);
        images.add(R.drawable.image05);
        MyImageLoader myImageLoader = new MyImageLoader();//设置图片加载器
        Banner banner = homeView.findViewById(R.id.banner);
        banner.setImageLoader(myImageLoader);
        banner.setBannerAnimation(Transformer.ZoomOutSlide);
        banner.setDelayTime(3000);//延时3000ms
        banner.setImages(images);//设置图片结合
        banner.start();
    }

}
