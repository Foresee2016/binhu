package org.foresee.binhu.setting;

import android.content.Context;
import android.content.Intent;

import org.foresee.binhu.R;
import org.foresee.binhu.share.BackNavActivity;

public class AboutActivity extends BackNavActivity {
    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, AboutActivity.class);
        return intent;
    }

    @Override
    public int getLayout() {
        return R.layout.activity_about;
    }

    @Override
    public int getToolbarTitleResId() {
        return R.string.about;
    }
}
