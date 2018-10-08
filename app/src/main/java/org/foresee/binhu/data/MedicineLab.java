package org.foresee.binhu.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.foresee.binhu.model.Medicine;
import org.foresee.binhu.share.Utils;

import static org.foresee.binhu.data.DbSchama.MedicineTable;
import java.util.ArrayList;
import java.util.Collection;
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
        mContext = context.getApplicationContext();
        mSqLiteDatabase = new BinhuDbHelper(mContext).getWritableDatabase();
        mMedicines = new ArrayList<>();
//        for (int i = 0; i < 50; i++) {
//            mMedicines.add(new Medicine("药名"));
//        }
    }

    private MedicineCursorWrapper queryMedicines(String whereClause, String[] whereArgs){
        Cursor cursor=mSqLiteDatabase.query(MedicineTable.TABLE_NAME, null,whereClause, whereArgs,null,null,null);
        return new MedicineCursorWrapper(cursor);
    }
    public List<Medicine> getMedicines() {
        mMedicines=new ArrayList<>();
        try (MedicineCursorWrapper cursor = queryMedicines(null, null)) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                mMedicines.add(cursor.getMedicine());
                cursor.moveToNext();
            }
        }
        return mMedicines;
    }
    public Medicine getMedicine(String name){
        try (MedicineCursorWrapper cursor = queryMedicines(MedicineTable.Cols.NAME + " = ?", new String[]{name})) {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getMedicine();
        }
    }
    public void saveNewMedicine(Collection<Medicine> medicines){
        for (Medicine m : medicines) {
            ContentValues values=getContentValues(m);
            mSqLiteDatabase.insert(MedicineTable.TABLE_NAME, null,values);
        }
        mMedicines.addAll(medicines);
    }
    private ContentValues getContentValues(Medicine medicine){
        ContentValues values=new ContentValues();
        values.put(MedicineTable.Cols.NAME, medicine.getName());
        values.put(MedicineTable.Cols.TASTE, medicine.getTaste());
        values.put(MedicineTable.Cols.THUMBNAIL, medicine.getThumbnail());
        values.put(MedicineTable.Cols.FUNC, medicine.getFunc());
        values.put(MedicineTable.Cols.IMAGES, medicine.getImages());
        values.put(MedicineTable.Cols.UPDATETIME, Utils.formatDate(medicine.getUpdateTime()));
        return values;
    }
}
