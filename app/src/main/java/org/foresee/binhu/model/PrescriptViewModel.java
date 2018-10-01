package org.foresee.binhu.model;

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
}
