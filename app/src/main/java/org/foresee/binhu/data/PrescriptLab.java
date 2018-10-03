package org.foresee.binhu.data;

import org.foresee.binhu.model.Prescript;

import java.util.ArrayList;
import java.util.List;

public class PrescriptLab {
    private static PrescriptLab sPrescriptLab;
    private List<Prescript> mPrescripts;

    public static PrescriptLab getPrescriptLab() {
        if (sPrescriptLab == null) {
            sPrescriptLab = new PrescriptLab();
        }
        return sPrescriptLab;
    }

    private PrescriptLab() {
        mPrescripts = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Prescript prescript = new Prescript("方剂名称" + i);
            int rand = (int) (Math.random() * 10);
            for (int j = 0; j < rand; j++) {
                prescript.addMedicine("成分" + j);
            }
            prescript.setFunc("功能主治" + i);
            mPrescripts.add(prescript);
        }
    }

    public List<Prescript> getPrescripts() {
        return mPrescripts;
    }
}
