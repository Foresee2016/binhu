package org.foresee.binhu;

import android.app.Application;
import android.util.Log;

public class MyApplication extends Application {

    private static final String TAG = "MyApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: 注册全局异常处理");
        CrashHandler crashHandler=CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());
    }
}
