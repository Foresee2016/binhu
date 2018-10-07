package org.foresee.binhu.model;

import java.sql.Timestamp;

public class Medicine {
    private int mId;
    private String mName;
    private String mThumbnail;
    private String mTaste;
    private String mFunc;
    private String mImages;
    private Timestamp mUpdateTime = new Timestamp(System.currentTimeMillis());

    public Medicine() {
    }

    public Medicine(String name) {
        this.mName = name;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
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

    public Timestamp getUpdateTime() {
        return mUpdateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.mUpdateTime = updateTime;
    }
}
