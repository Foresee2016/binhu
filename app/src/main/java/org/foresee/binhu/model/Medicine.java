package org.foresee.binhu.model;

import java.util.List;

public class Medicine {
    private String mName;
    private List<String> mImgPaths;

    public Medicine(String name) {
        mName = name;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public List<String> getImgPaths() {
        return mImgPaths;
    }

    public void setImgPaths(List<String> imgPaths) {
        mImgPaths = imgPaths;
    }
}
