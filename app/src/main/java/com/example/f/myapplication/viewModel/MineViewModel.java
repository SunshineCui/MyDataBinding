package com.example.f.myapplication.viewModel;

import android.os.Handler;
import android.os.Message;

import com.example.f.myapplication.view.IView;




public class MineViewModel implements viewModel {
    private IView iView;

    public MineViewModel(IView iView) {
        this.iView = iView;
    }

    @Override
    public void init() {

         new Handler().postDelayed(new Runnable() {
             @Override
             public void run() {
                 iView.refresh();
             }
         },3000);
    }

    @Override
    public void destroy() {

    }
}
