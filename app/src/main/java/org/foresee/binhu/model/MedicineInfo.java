package org.foresee.binhu.model;

import java.util.ArrayList;
import java.util.List;

public class MedicineInfo {
    private String mName;
    private String mMainImage;//冗余一些信息，方便在主界面显示缩略图和简介
    private List<MedicinePart> mParts;
    private String mFrom = "无";
    private String mTaste;
    private String mFunc;

    public MedicineInfo(String name) {
        mName = name;
        mParts = new ArrayList<>();
    }

    public String getMainImage() {
        return mMainImage;
    }

    public void setMainImage(String mainImage) {
        mMainImage = mainImage;
    }

    public List<MedicinePart> getParts() {
        return mParts;
    }

    public void addMedicinePart(MedicinePart part) {
        mParts.add(part);
    }

    public String getFrom() {
        return mFrom;
    }

    public void setFrom(String from) {
        mFrom = from;
    }

    public class MedicinePart {
        private String mPartName;
        private String mTaste;
        private String mFunc;
        private boolean mMainPart;
        private List<String> mImages;

        public MedicinePart(String partName) {
            mPartName = partName;
            mImages=new ArrayList<>();
        }

        public String getPartName() {
            return mPartName;
        }

        public void setPartName(String partName) {
            mPartName = partName;
        }

        public String getTaste() {
            return mTaste;
        }

        public void setTaste(String taste) {
            mTaste = taste;
        }

        public String getFunc() {
            return mFunc;
        }

        public void setFunc(String func) {
            mFunc = func;
        }

        public boolean isMainPart() {
            return mMainPart;
        }

        public void setMainPart(boolean mainPart) {
            mMainPart = mainPart;
        }

        public List<String> getImages() {
            return mImages;
        }
        public void addImage(String image){
            mImages.add(image);
        }
    }
}
