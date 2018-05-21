package com.example.f.myapplication.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.graphics.drawable.Drawable;

import com.example.f.myapplication.BR;

public class ItemMineCommon extends BaseObservable{
    public Drawable ivSrc;
    public String title;
    public String time;
    public Boolean showStatus;

    public ItemMineCommon(Drawable ivSrc, String title, String time, Boolean showStatus) {
        this.ivSrc = ivSrc;
        this.title = title;
        this.time = time;
        this.showStatus = showStatus;
    }

    public Boolean getShowStatus() {
        return showStatus;
    }

    public void setShowStatus(Boolean showStatus) {
        this.showStatus = showStatus;
    }

    public Drawable getIvSrc() {
        return ivSrc;
    }

    public void setIvSrc(Drawable ivSrc) {
        this.ivSrc = ivSrc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Bindable
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
        notifyPropertyChanged(BR.time);
    }
}
