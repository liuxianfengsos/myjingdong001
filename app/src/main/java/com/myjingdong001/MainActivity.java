package com.myjingdong001;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.gson.JsonElement;

import net.callumtaylor.asynchttp.AsyncHttpClient;
import net.callumtaylor.asynchttp.obj.NameValuePair;
import net.callumtaylor.asynchttp.response.JsonResponseHandler;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;

public class MainActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private RadioGroup mTabRadioGroup;
    private List<Fragment> mFragments;
    private FragmentPagerAdapter mAdapter;//fragment页面滑动

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }
    public void initView(){
        //首先完成分页；1完成activity_main.xml
        //find view  fragment_vp
        mViewPager = findViewById(R.id.fragment_vp);
        mTabRadioGroup = findViewById(R.id.tabs_rg);
        //init fragment
        mFragments = new ArrayList<>(5);
        mFragments.add(HomeFragment.newInstance());
        mFragments.add(SearchFragment.newInstance());
        mFragments.add(MoreFragment.newInstance());
        mFragments.add(CartFragment.newInstance());
        mFragments.add(MyFragment.newInstance());
        //init view pager
        mAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),mFragments);
        mViewPager.setAdapter(mAdapter);
        // register listener
        mViewPager.addOnPageChangeListener(mPageChangeListener);
        mTabRadioGroup.setOnCheckedChangeListener(mOnCheckedChangeListener);
    }
        //获取ViewPager
    private ViewPager.OnPageChangeListener mPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            Log.v("5555555555555","55555555555555555position="
                    +position+"=positionOffset="+positionOffset+"=positionOffsetPixels="+positionOffsetPixels);
            if("2".equals(position)){
                Log.v("5555555555555","55555555555555555position="+position);
                findAllGoods();
            }
        }

        @Override
        public void onPageSelected(int position) {
            RadioButton radioButton = (RadioButton) mTabRadioGroup.getChildAt(position);
            radioButton.setChecked(true);

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mViewPager.removeOnPageChangeListener(mPageChangeListener);
    }
    private RadioGroup.OnCheckedChangeListener mOnCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            for (int i = 0; i < group.getChildCount(); i++) {
                if (group.getChildAt(i).getId() == checkedId) {
                    mViewPager.setCurrentItem(i);
                    return;
                }
            }
        }
    };
    //2完成MyFragmentPagerAdapter内部类
    private class MyFragmentPagerAdapter extends FragmentPagerAdapter {
        //https://blog.csdn.net/afei__/article/details/80950288
        private List<Fragment> mList;

        public MyFragmentPagerAdapter(FragmentManager fm, List<Fragment> list) {
            super(fm);
            this.mList = list;
        }

        @Override
        public Fragment getItem(int position) {
            return this.mList == null?null:this.mList.get(position);
        }

        @Override
        public int getCount() {
            return this.mList == null?0:this.mList.size();
        }
    }

    //访问服务器加载数据

    public void findAllGoods(){
        //访问servlet端口:
        //开始准备访问服务器
        AsyncHttpClient client = new AsyncHttpClient("http://192.168.25.1:8080/mautomallserver/");

        List<NameValuePair> params = new ArrayList<>();
        //params.add(new NameValuePair("username", username));
        //params.add(new NameValuePair("password", password));

        Headers headers = Headers.of("Header", "");

        client.post("getallgoodsserver", params, headers, new JsonResponseHandler()
        {
            @Override public void onSuccess()
            {
                Log.v("result00000000000000000","0000000000000000000000000000");
                JsonElement result = getContent();
                Log.v("result11111111111111111",result.toString());
                //JsonObject jsonObject = result.getAsJsonObject();
                // Log.v("result22222222222222222",jsonObject.toString());
                // String goodsList = jsonObject.get("goodsList").getAsString();
                // Log.v("msg1",goodsList);

            }

        });
    }


}