package org.foresee.binhu;

import java.util.ArrayList;
import java.util.List;

public class MedicineLab {
    private static MedicineLab sMedicineLab;

    public static MedicineLab getMedicineLab() {
        if (sMedicineLab == null) {
            sMedicineLab = new MedicineLab();
        }
        return sMedicineLab;
    }

    private List<Medicine> mMedicines;

    private MedicineLab() {
        mMedicines = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            mMedicines.add(new Medicine("药名"));
        }
    }

    public List<Medicine> getMedicines() {
        return mMedicines;
    }
}
