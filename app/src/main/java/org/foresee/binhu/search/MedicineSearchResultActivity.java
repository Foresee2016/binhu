package org.foresee.binhu.search;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import org.foresee.binhu.MedicineAllFragment;
import org.foresee.binhu.PrescriptFragment;
import org.foresee.binhu.R;
import org.foresee.binhu.share.TabsAdapter;

public class MedicineSearchResultActivity extends AppCompatActivity {
    private static final String EXTRA_SEARCH_TEXT = "search_text";
    private static final String TAG = "MedicineSearchResultAct";

    public static Intent newIntent(Context context, String searchText) {
        Intent intent = new Intent(context, MedicineSearchResultActivity.class);
        intent.putExtra(EXTRA_SEARCH_TEXT, searchText);
        return intent;
    }

    private String mSearchString;
    private EditText searchEditText;
    private TextView mCancel;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private String[] mTitles = new String[]{"中药", "附方"};
    private Fragment[] mFragments = new Fragment[mTitles.length];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_medicine_search_result);
        mSearchString = getIntent().getStringExtra(EXTRA_SEARCH_TEXT);
        if (mSearchString == null) {
            mSearchString = "";
        }
        searchEditText = findViewById(R.id.search_edit_text);
        searchEditText.setText(mSearchString);
        searchEditText.setSelection(mSearchString.length());
        searchEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    Log.d(TAG, "onEditorAction: Search Text = " + v.getText());
                    String searchText = v.getText().toString();
                    if (searchText.trim().length() == 0) {
                        return true;
                    }
                    SearchHistory.saveHistoryString(getApplicationContext(), searchText);
                    startSearchTask(searchText);
                    return true;
                }
                return false;
            }
        });
        mCancel = findViewById(R.id.cancel);
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mViewPager = findViewById(R.id.view_pager);
        mFragments[0] = MedicineAllFragment.newInstance();
        mFragments[1] = PrescriptFragment.newInstance();
        mViewPager.setAdapter(new TabsAdapter(getSupportFragmentManager(), mFragments, mTitles));
        mTabLayout = findViewById(R.id.tab_layout);
        mTabLayout.setupWithViewPager(mViewPager);
        for (int i = 0; i < mTitles.length; i++) {
            TabLayout.Tab item = mTabLayout.getTabAt(i);
            if (item != null) {
                item.setCustomView(R.layout.icon_text_view);
                TextView textView = (TextView) item.getCustomView();
                if (textView != null) {
                    textView.setText(mTitles[i]);
                }
            }
        }
    }
//    private void hideSoftInput(){
//        InputMethodManager imm= (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.hideSoftInputFromWindow(searchEditText.getWindowToken(), 0);
//    }
    private void startSearchTask(String searchText) {
        Log.d(TAG, "startSearchTask: searchText = " + searchText);
    }
}
