package org.foresee.binhu.medicine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import org.foresee.binhu.R;
import org.foresee.binhu.model.Medicine;
import org.foresee.binhu.share.BackNavActivity;

import java.io.Serializable;

public class MedicineDetailActivity extends BackNavActivity {
    public static Intent newIntent(Context context, Medicine medicine) {
        Intent intent = new Intent(context, MedicineDetailActivity.class);
        intent.putExtra(EXTRA_MEDICINE_NAME, medicine);
        return intent;
    }

    @Override
    public int getLayout() {
        return R.layout.activity_fragment;
    }

    @Override
    public int getToolbarTitleResId() {
        return R.string.medicine_detail;
    }

    private static final String TAG = "MedicineDetailActivity";
    private static final String EXTRA_MEDICINE_NAME = "medicineName";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Serializable s = getIntent().getSerializableExtra(EXTRA_MEDICINE_NAME);
        if (s instanceof Medicine) {
            FragmentManager fm = getSupportFragmentManager();
            Fragment fragment = fm.findFragmentById(R.id.fragment);
            if (fragment == null) {
                Medicine medicine = (Medicine) s;
                fragment = MedicineDetailFragment.newInstance(medicine);
                fm.beginTransaction().add(R.id.fragment, fragment).commit();
            }
        } else {
            Log.i(TAG, "onCreate: 未传入Medicine");
        }
    }

}
