package com.myjingdong001;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
        //5实现图片墙
        initGalleryJinqiu(homeView);
        return homeView;
    }
    //初始化 金秋风暴图片墙 的方法及参数
    private Gallery jingqiu_gallery;//金秋风暴图片墙
    private ImageAdapter imageAdapter;//图片适配器

    public void initGalleryJinqiu(View homeView){
        jingqiu_gallery = homeView.findViewById(R.id.index_jingqiu_gallery);
        imageAdapter = new ImageAdapter(homeView.getContext());
        jingqiu_gallery.setAdapter(imageAdapter);
        //相应的点击事件
        jingqiu_gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(adapterView.getContext(), "您点击的是" + i, Toast.LENGTH_LONG).show();
            }
        });
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
    //5
    private class ImageAdapter extends BaseAdapter{
        private Context context;
        int mGralleyItemBackground;
        int[] images = {R.drawable.index_gallery_01,R.drawable.index_gallery_02,R.drawable.index_gallery_03,R.drawable.index_gallery_04,R.drawable.index_gallery_05};
        public ImageAdapter (Context context){
            this.context = context;
        }
        @Override
        public int getCount() {
            return images.length;
        }
        @Override
        public Object getItem(int position) {
            return position;
        }
        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //在此最好判断一下view是否为空
            ImageView image = new ImageView(context);
            image.setImageResource(images[position]);
            image.setAdjustViewBounds(true);
            //设置宽高
           /*image.setLayoutParams(new Gallery.LayoutParams(
                   ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));*/
            return image;
        }
    }
}