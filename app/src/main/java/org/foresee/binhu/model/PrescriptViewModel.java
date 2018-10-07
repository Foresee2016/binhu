package org.foresee.binhu.model;

import org.foresee.binhu.share.Utils;

import java.util.List;

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
        List<Component> comps = mPrescript.getComponents();
        StringBuilder builder = new StringBuilder("成分：");
        for (int i=0; i<comps.size(); i++) {
            Component comp=comps.get(i);
            builder.append(comp.getMedicineName()).append(":").append(comp.getWeight());
            if(!comp.getProcess().equals("")){
                builder.append("(").append(comp.getProcess()).append(")");
            }
            if(i==comps.size()-1){
                builder.append("。");
            }else{
                builder.append("，");
            }
        }
        return builder.toString();
    }
}
