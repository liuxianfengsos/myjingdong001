package com.myjingdong001;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MoreFragment extends Fragment{
    private static final String ARG_SHOW_TEXT = "text";
    private String mContentText;
    //返回通过工厂方法获取BlankFragment
    public static MoreFragment newInstance(){
        MoreFragment fragment = new MoreFragment();
        Bundle args = new Bundle();
        //args.putString(ARG_SHOW_TEXT,param1);
       // fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        /*if (getArguments()!=null){
            mContentText = getArguments().getString(ARG_SHOW_TEXT);
        }*/

    }
    private ListView lv;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //FOR fragment
        View rootView = inflater.inflate(R.layout.fragment_more,container,false);
        //TextView contentTv = rootView.findViewById(R.id.content_tv);
        //contentTv.setText(mContentText);
        lv = rootView.findViewById(R.id.more_list);
        MoreAdapter moreAdapter = new MoreAdapter();
        lv.setAdapter(moreAdapter);
        return rootView;
    }

    private class MoreAdapter extends BaseAdapter{
        int[] images = {R.drawable.catergory_appliance,R.drawable.catergory_book,R.drawable.catergory_cloth,R.drawable.catergory_deskbook,R.drawable.catergory_digtcamer,R.drawable.catergory_furnitrue,R.drawable.catergory_mobile,R.drawable.catergory_skincare};
        String[] titles = {"家电","图书","衣服","笔记本","数码","家具","手机","护肤"};
        String[] names = {"家电/生活电器","图书/电子书","上衣/裤子","hp/thinkpad/lenovo","sony/相机","桌子/椅子","小米/华为","大宝/迪奥"};
        @Override
        public int getCount() {
            return images.length;
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
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = null;
            if(convertView == null){
                view = LayoutInflater.from(getContext()).inflate(R.layout.morelist,null);
                ImageView imageView = view.findViewById(R.id.more_list_iv);
                imageView.setImageResource(images[position]);
                //设置标题
                TextView tv1 = view.findViewById(R.id.tv_1);
                tv1.setText(titles[position]);
                //设置副标题
                TextView tv2 = view.findViewById(R.id.tv_2);
                tv2.setText(names[position]);
            }else{
                view = convertView;
            }
            return view;
        }
    }
}
