package org.foresee.binhu.data;

import android.database.Cursor;
import android.database.CursorWrapper;

import org.foresee.binhu.model.Medicine;
import org.foresee.binhu.share.Utils;

import java.text.ParseException;

import static org.foresee.binhu.data.DbSchama.MedicineTable;

public class MedicineCursorWrapper extends CursorWrapper {
    public MedicineCursorWrapper(Cursor cursor) {
        super(cursor);
    }
    public Medicine getMedicine(){
        Medicine medicine=new Medicine();
        medicine.setName(getString(getColumnIndex(MedicineTable.Cols.NAME)));
        medicine.setThumbnail(getString(getColumnIndex(MedicineTable.Cols.THUMBNAIL)));
        medicine.setTaste(getString(getColumnIndex(MedicineTable.Cols.TASTE)));
        medicine.setFunc(getString(getColumnIndex(MedicineTable.Cols.FUNC)));
        medicine.setImages(getString(getColumnIndex(MedicineTable.Cols.IMAGES)));
        try {
            medicine.setUpdateTime(Utils.parseDate(getString(getColumnIndex(MedicineTable.Cols.UPDATETIME))));
        } catch (ParseException e) {
            medicine.setUpdateTime(Utils.DEFAULT_DATE);
        }
        return medicine;
    }
}
