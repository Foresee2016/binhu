package org.foresee.binhu.model;

import android.text.format.DateFormat;

import org.foresee.binhu.share.Utils;

import java.text.SimpleDateFormat;

public class UpdateInfoViewModel {
    private UpdateInfo mUpdateInfo;

    public UpdateInfoViewModel(UpdateInfo updateInfo) {
        mUpdateInfo = updateInfo;
    }
    public void setUpdateInfo(UpdateInfo updateInfo) {
        mUpdateInfo = updateInfo;
    }
    public String getUpdateTime(){
        return Utils.formatDate(mUpdateInfo.getUpdateTime());
    }
    public String getPart(){
        return mUpdateInfo.getPart();
    }
    public String getDataCount(){
        return mUpdateInfo.getDataCount()+"条";
    }
    public String getDataSize(){
        int size=mUpdateInfo.getDataSize();
        if(size>1000_000){
            return size+"MB";
        }
        if(size>1000){
            return size+"KB";
        }
        return size+"B";
    }
}
