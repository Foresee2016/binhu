package org.foresee.binhu.setting;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import org.foresee.binhu.R;

public class SettingActivity extends BackNavActivity {
    private static final String TAG = "SettingActivity";
    private TextView mAbout;
    @Override
    public int getLayout() {
        return R.layout.activity_setting;
    }

    @Override
    public int getToolbarTitleResId() {
        return R.string.setting;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAbout=findViewById(R.id.about);
        mAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: start AboutActivity");
                startActivity(AboutActivity.newIntent(SettingActivity.this));
            }
        });
    }

}
