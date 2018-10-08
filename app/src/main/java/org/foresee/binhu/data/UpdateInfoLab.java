package org.foresee.binhu.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.foresee.binhu.model.UpdateInfo;
import org.foresee.binhu.share.Utils;

import static org.foresee.binhu.data.DbSchama.UpdateInfoTable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UpdateInfoLab {
    private static UpdateInfoLab sUpdateInfoLab;
    private Context mContext;
    private SQLiteDatabase mSqLiteDatabase;

    public static UpdateInfoLab getMedicineLab(Context context) {
        if (sUpdateInfoLab == null) {
            sUpdateInfoLab = new UpdateInfoLab(context);
        }
        return sUpdateInfoLab;
    }

    private List<UpdateInfo> mUpdateInfos;

    private UpdateInfoLab(Context context) {
        mContext = context.getApplicationContext();
        mSqLiteDatabase = new BinhuDbHelper(mContext).getWritableDatabase();
        mUpdateInfos = new ArrayList<>();
//        for (int i = 0; i < 50; i++) {
//            mMedicines.add(new Medicine("药名"));
//        }
    }

    public List<UpdateInfo> getUpdateInfos() {
        return mUpdateInfos;
    }

    public void saveNewUpdateInfo(Collection<UpdateInfo> infos) {
        for (UpdateInfo info : infos) {
            ContentValues values = getContentValue(info);
            mSqLiteDatabase.insert(UpdateInfoTable.TABLE_NAME, null, values);
        }
        mUpdateInfos.addAll(infos);
    }

    private ContentValues getContentValue(UpdateInfo info) {
        ContentValues values = new ContentValues();
        values.put(UpdateInfoTable.Cols.PART, info.getPart());
        values.put(UpdateInfoTable.Cols.DATACOUNT, info.getDataCount());
        values.put(UpdateInfoTable.Cols.DATASIZE, info.getDataSize());
        values.put(UpdateInfoTable.Cols.UPDATETIME, Utils.formatDate(info.getUpdateTime()));
        return values;
    }
}
