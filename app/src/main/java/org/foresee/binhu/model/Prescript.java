package org.foresee.binhu.model;

public class Prescript {
    private String mName;
    private String mFunc;

    public Prescript(String name) {
        mName = name;
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
