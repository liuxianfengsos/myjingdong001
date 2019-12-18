package com.myjingdong001;


import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.bumptech.glide.Glide;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.myjingdong001.util.MyImageView;
import com.myjingdong001.vo.ListViewCars;

import net.callumtaylor.asynchttp.AsyncHttpClient;
import net.callumtaylor.asynchttp.obj.NameValuePair;
import net.callumtaylor.asynchttp.response.JsonResponseHandler;
import net.callumtaylor.asynchttp.response.ResponseHandler;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import okhttp3.Headers;

public class MoreFragment extends Fragment{
    private static final String ARG_SHOW_TEXT = "text";
    private String mContentText;
    //返回通过工厂方法获取BlankFragment
    public static MoreFragment newInstance(){
        MoreFragment fragment = new MoreFragment();
        Bundle args = new Bundle();


        //访问servlet端口:
        //开始准备访问服务器
        AsyncHttpClient client = new AsyncHttpClient("http://192.168.25.1:8080/mautomallserver/");

        List<NameValuePair> params = new ArrayList<>();
        Headers headers = Headers.of("Header", "");
        client.post("getallgoodsserver", params, headers, new JsonResponseHandler()
        {
            @Override public void onSuccess()
            {
                JsonElement  result = getContent();
                Log.v("2311111111111",result.toString());

                Map<String,List<ListViewCars>> lscars = JSONObject.parseObject(result.toString(),Map.class);

                List<ListViewCars> carsMap = lscars.get("goodsList");

                if(carsMap!=null){
                    Log.v("000000000000","0000000000000000000000000000");
                }
                List<ListViewCars> carsList = JSONObject.parseArray(carsMap.toString(),ListViewCars.class);
                Log.v("000000000000",carsMap.toString());


                for(int i=0;i<carsList.size();i++){
                    ListViewCars listViewCars = carsList.get(i);
                    images.add(listViewCars.getUrl());
                    titles.add(listViewCars.getTitle());
                    names.add(listViewCars.getMessage());
                    Log.v("77777777777777777",titles.toString());
                }

            }

        });


        //args.putString(ARG_SHOW_TEXT,param1);
       // fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    private ListView lv;
    //JsonElement result;
    public static List images = new ArrayList();
    public static List titles = new ArrayList();
    public static List names = new ArrayList();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //FOR fragment
        View rootView = inflater.inflate(R.layout.fragment_more,container,false);
        //TextView contentTv = rootView.findViewById(R.id.content_tv);
        //contentTv.setText(mContentText);

        Log.v("4444444444444444444444",names.toString());
        lv = rootView.findViewById(R.id.more_list);
       // MoreAdapter moreAdapter = new MoreAdapter();
        MoreAdapter moreAdapter = new MoreAdapter(images,titles,names);
        lv.setAdapter(moreAdapter);
        return rootView;
    }

    private class MoreAdapter extends BaseAdapter{
        List images;
        List titles;
        List names;
        //Drawable drawable = LoadImageFromWebOperations("http://localhost:8080/mautomallserver/images/index_gallery_01.png");
        //Drawable[] dbs ={drawable,drawable,drawable,drawable,drawable,drawable,drawable,drawable};
        public MoreAdapter(){

        }
        public MoreAdapter(List images ,List titles,List names){
            this.images = images;
            this.names = names;
            this.titles = titles;
            Log.v("0000names000000000000",titles.toString());
        }
        //int[] images = {R.drawable.catergory_appliance,R.drawable.catergory_book,R.drawable.catergory_cloth,R.drawable.catergory_deskbook,R.drawable.catergory_digtcamer,R.drawable.catergory_furnitrue,R.drawable.catergory_mobile,R.drawable.catergory_skincare};
        //String[] titles = {"家电","图书","衣服","笔记本","数码","家具","手机","护肤"};
        //String[] names = {"家电/生活电器","图书/电子书","上衣/裤子","hp/thinkpad/lenovo","sony/相机","桌子/椅子","小米/华为","大宝/迪奥"};



        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view = null;
            if(convertView == null){
                view = LayoutInflater.from(getContext()).inflate(R.layout.morelist,null);
                final ImageView imageView = view.findViewById(R.id.more_list_iv);
                String imageUrl = images.get(position).toString();
                //发送请求获取图片
//Glide.with(MainActivity.this).load(url).into(myImageView);
                Glide.with(view).load(imageUrl).into(imageView);
                //设置标题
                TextView tv1 = view.findViewById(R.id.tv_1);
                tv1.setText(titles.get(position).toString());
                //设置副标题
                TextView tv2 = view.findViewById(R.id.tv_2);
                tv2.setText(names.get(position).toString());
            }else{
                view = convertView;
            }
            return view;
        }
    }

    private Drawable LoadImageFromWebOperations(String url)
    {
        try
        {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        }catch (Exception e) {
            System.out.println("Exc="+e);
            return null;
        }
    }
    public Bitmap returnBitMap(String url) {
        URL myFileUrl = null;
        Bitmap bitmap = null;
        try {
            myFileUrl = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }try {
            HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;}
}
