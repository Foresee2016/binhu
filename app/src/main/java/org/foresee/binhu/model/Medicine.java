package org.foresee.binhu.model;

import java.io.Serializable;
import java.util.Date;

public class Medicine implements Serializable {
    private String mName;
    private String mThumbnail;
    private String mTaste;
    private String mFunc;
    private String mImages;
    private Date mUpdateTime;

    public Medicine() {
    }

    public Medicine(String name) {
        this.mName = name;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getThumbnail() {
        return mThumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.mThumbnail = thumbnail;
    }

    public String getTaste() {
        return mTaste;
    }

    public void setTaste(String taste) {
        this.mTaste = taste;
    }

    public String getFunc() {
        return mFunc;
    }

    public void setFunc(String func) {
        this.mFunc = func;
    }

    public String getImages() {
        return mImages;
    }

    public void setImages(String images) {
        this.mImages = images;
    }

    public Date getUpdateTime() {
        return mUpdateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.mUpdateTime = updateTime;
    }
}
