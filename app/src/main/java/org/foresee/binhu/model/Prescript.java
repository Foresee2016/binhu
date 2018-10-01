package org.foresee.binhu.model;

import java.util.ArrayList;
import java.util.List;

public class Prescript {
    private String mName;
    private List<String> mMedicines;
    private String mFunc;

    public Prescript(String name) {
        mName = name;
        mMedicines=new ArrayList<>();
    }
    public void addMedicine(String medicineName){
        mMedicines.add(medicineName);
    }
    public List<String> getMedicines() {
        return mMedicines;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getFunc() {
        return mFunc;
    }

    public void setFunc(String func) {
        mFunc = func;
    }
}
