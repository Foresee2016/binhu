package org.foresee.binhu.model;

import org.foresee.binhu.share.Utils;

public class PrescriptViewModel {
    private Prescript mPrescript;

    public PrescriptViewModel(Prescript prescript) {
        mPrescript = prescript;
    }

    public void setPrescript(Prescript prescript) {
        mPrescript = prescript;
    }

    public String getName() {
        return mPrescript.getName();
    }

    public String getFunc() {
        return mPrescript.getFunc();
    }

    public String getComponents() {
        return "成分：" + Utils.join(" ", mPrescript.getComponents());
    }
}
