package org.foresee.binhu.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.foresee.binhu.model.Medicine;

import java.util.ArrayList;
import java.util.List;

public class MedicineLab {
    private static MedicineLab sMedicineLab;
    private Context mContext;
    private SQLiteDatabase mSqLiteDatabase;

    public static MedicineLab getMedicineLab(Context context) {
        if (sMedicineLab == null) {
            sMedicineLab = new MedicineLab(context);
        }
        return sMedicineLab;
    }

    private List<Medicine> mMedicines;

    private MedicineLab(Context context) {
        mContext=context;
        mSqLiteDatabase=new MedicineDbHelper(context).getWritableDatabase();
        mMedicines = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            mMedicines.add(new Medicine("药名"));
        }
    }

    public List<Medicine> getMedicines() {
        return mMedicines;
    }
}
