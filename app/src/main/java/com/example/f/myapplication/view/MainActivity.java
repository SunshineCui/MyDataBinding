package com.example.f.myapplication.view;

import android.databinding.DataBindingUtil;
import android.nfc.Tag;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.f.myapplication.Base.adapter.MyFragmentPagerAdapter;
import com.example.f.myapplication.R;
import com.example.f.myapplication.databinding.ActivityMainBinding;
import com.example.f.myapplication.model.User;
import com.example.f.myapplication.view.fragment.OneFragment;
import com.example.f.myapplication.view.fragment.TwoFragment;
import com.example.f.myapplication.view.fragment.mineFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


public class MainActivity extends AppCompatActivity implements View.OnClickListener ,IView, ViewPager.OnPageChangeListener {

    private static final String TAG = "MainActivity.class";

    private ViewPager vpContent;
    private ImageView ivTitleTwo;
    private ImageView ivTitleOne;
    private ImageView ivTitleThree;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        this.getSupportActionBar().hide();
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        vpContent = binding.vpContent;
        ivTitleOne = binding.ivTitleOne;
        ivTitleTwo = binding.ivTitleTwo;
        ivTitleThree = binding.ivTitleThree;
        initListener();
        initVP();

//        FragmentManager supportFragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
//        fragmentTransaction.hide()
    }

    private void initVP() {
        List<Fragment> list = new ArrayList<>(5);
        list.add(new OneFragment());
        list.add(new TwoFragment());
        list.add(new mineFragment());
        Log.d(TAG,"fragment size  " + list.size());
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),list);
        vpContent.setAdapter(adapter);
        vpContent.setOffscreenPageLimit(2);
        vpContent.addOnPageChangeListener(this);
        setCurrentItem(0);
    }

    private void initListener() {
        ivTitleOne.setOnClickListener(this);
        ivTitleTwo.setOnClickListener(this);
        ivTitleThree.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_one:
                if (vpContent.getCurrentItem() != 0) {
                    setCurrentItem(0);
                }
                break;
            case R.id.iv_title_two:
            if (vpContent.getCurrentItem() != 1) {
                    setCurrentItem(1);
                }
            break;
            case R.id.iv_title_three:
                if (vpContent.getCurrentItem() != 2) {
                    setCurrentItem(2);
                }
                break;
            default:
                break;
        }
    }


    /**
     * 切换页面
     *
     * @param position 分类角标
     */
    private void setCurrentItem(int position) {
        boolean isOne = false;
        boolean isTwo = false;
        boolean isThree = false;
        switch (position) {
            case 0:
                isOne = true;
                break;
            case 1:
                isTwo = true;
                break;
            case 2:
                isThree = true;
                break;
            default:

                break;
        }
        vpContent.setCurrentItem(position);
        ivTitleOne.setSelected(isOne);
        ivTitleTwo.setSelected(isTwo);
        ivTitleThree.setSelected(isThree);
    }


    @Override
    public void refresh() {
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                setCurrentItem(0);
                break;
            case 1:
                setCurrentItem(1);
                break;
            case 2:
                setCurrentItem(2);
                break;
            default:
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
