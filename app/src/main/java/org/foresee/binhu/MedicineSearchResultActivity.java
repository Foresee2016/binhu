package org.foresee.binhu;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

public class MedicineSearchResultActivity extends AppCompatActivity {
    private static final String EXTRA_SEARCH_TEXT="search_text";
    public static Intent newIntent(Context context, String searchText){
        Intent intent=new Intent(context, MedicineSearchResultActivity.class);
        intent.putExtra(EXTRA_SEARCH_TEXT, searchText);
        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_medicine_search_result);
    }
}
