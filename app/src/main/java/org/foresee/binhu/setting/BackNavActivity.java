package org.foresee.binhu.setting;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Toolbar;

import org.foresee.binhu.R;
public abstract class BackNavActivity extends AppCompatActivity {
    public abstract int getLayout();
    public abstract int getToolbarTitleResId();
    private Toolbar mToolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getLayout());
        mToolbar=findViewById(R.id.tool_bar);
        mToolbar.setTitle(getToolbarTitleResId());
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
