package com.example.f.myapplication.Base.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Billy_Cui on 2018/5/17
 * Describe:
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<?> mFragmentList;
    private List<String> mTitleList;

    public MyFragmentPagerAdapter(FragmentManager fm, List<?> mFragmentList) {
        super(fm);
        this.mFragmentList = mFragmentList;
    }

    public MyFragmentPagerAdapter(FragmentManager fm, List<?> mFragmentList,List<String> mTitleList) {
        super(fm);
        this.mFragmentList = mFragmentList;
        this.mTitleList = mTitleList;
    }


    @Override
    public Fragment getItem(int position) {
        Log.d("MyFragmentPagerAdapter","MyFragmentPagerAdapter :" +position);
        return (Fragment) mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
        }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (mTitleList!=null){
            return mTitleList.get(position);
        }else {
            return super.getPageTitle(position);
        }
    }
}
