package org.foresee.binhu.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static org.foresee.binhu.data.DbSchama.MedicineTable;
import static org.foresee.binhu.data.DbSchama.UpdateInfoTable;

public class BinhuDbHelper extends SQLiteOpenHelper {
    private static final String TAG="BinhuDbHelper";
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "binhu.db";

    public BinhuDbHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "onCreate: 创建数据表："+UpdateInfoTable.TABLE_NAME+", "+MedicineTable.TABLE_NAME);
        db.execSQL("CREATE TABLE " + UpdateInfoTable.TABLE_NAME + "(" +
                "_id integer primary key autoincrement, " +
                UpdateInfoTable.Cols.PART + " VARCHAR(10), " +
                UpdateInfoTable.Cols.DATACOUNT + " VARCHAR(50), " +
                UpdateInfoTable.Cols.DATASIZE + " VARCHAR(20), " +
                UpdateInfoTable.Cols.UPDATETIME + " DATETIME" +
                ")");
        db.execSQL("CREATE TABLE " + MedicineTable.TABLE_NAME + "(" +
                "_id integer primary key autoincrement, " +
                MedicineTable.Cols.ID + " INT, " +
                MedicineTable.Cols.NAME + " VARCHAR(10), " +
                MedicineTable.Cols.THUMBNAIL + " VARCHAR(50), " +
                MedicineTable.Cols.TASTE + " VARCHAR(20), " +
                MedicineTable.Cols.FUNC + " VARCHAR(200), " +
                MedicineTable.Cols.IMAGES + " VARCHAR(300), " +
                MedicineTable.Cols.UPDATETIME + " DATETIME" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
