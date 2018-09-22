package org.foresee.binhu;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MedicineActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return MedicineFragment.newInstance();
    }
}
