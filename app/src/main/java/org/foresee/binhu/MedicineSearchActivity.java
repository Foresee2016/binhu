package org.foresee.binhu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MedicineSearchActivity extends AppCompatActivity {
    private static final String TAG = "MedicineSearchActivity";
    private EditText mSearchText;
    private TextView mCancel;
    private LinearLayout mHistoryItems;
    private String[] mHistoryStrings;
    private TextView mClearHistory;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
//        if (mHistoryStrings == null) {
//            int historyCnt = 20;
//            mHistoryStrings = new ArrayList<>(historyCnt);
//            for (int i = 0; i < historyCnt; i++) {
//                mHistoryStrings.add("历史" + i);
//            }
//        }
        setContentView(R.layout.activity_medicine_search);
        mHistoryItems = findViewById(R.id.history_items);
        freshHistoryItems();
        mClearHistory = findViewById(R.id.clear_search);
        mSearchText = findViewById(R.id.search_text);
        mSearchText.requestFocus();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        mSearchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId== EditorInfo.IME_ACTION_SEARCH){
                    Log.d(TAG, "onEditorAction: Search Text = "+v.getText());
                    String searchStr=String.valueOf(v.getText());
                    if(searchStr.trim().length()==0){
                        return true;
                    }
                    SearchHistory.saveHistoryString(MedicineSearchActivity.this, searchStr);
                    Intent intent=MedicineSearchResultActivity.newIntent(MedicineSearchActivity.this, searchStr);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        freshHistoryItems();
    }
    private void freshHistoryItems(){
        mHistoryStrings=SearchHistory.getHistoryString(this);
        freshHistoryItems(mHistoryStrings);
    }
    private void freshHistoryItems(String[] items) {
        mHistoryItems.removeAllViews();
        LinearLayout rowLinear=createNewLinearRow();
        boolean isNewRow=true;
        float maxWidth=getScreenWidth() - dipToPx(30);
        float remainWidth=maxWidth;
        for (String item : items) {
            TextView textView = (TextView) getLayoutInflater().inflate(R.layout.search_history_item, null);
            textView.setText(item);
            textView.measure(0,0);
            if(remainWidth<textView.getMeasuredWidth()){
                rowLinear=createNewLinearRow();
                remainWidth=maxWidth;
                isNewRow=true;
            }
            rowLinear.addView(textView);
            remainWidth -=textView.getMeasuredWidth();
            if(isNewRow){
                mHistoryItems.addView(rowLinear, 0);
                isNewRow=false;
            }
        }
    }
    private LinearLayout.LayoutParams historyRowParam;
    private LinearLayout createNewLinearRow(){
        if(historyRowParam==null){
            historyRowParam= new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            historyRowParam.setMargins(0, (int) dipToPx(8), 0, 0);
        }
        LinearLayout rowLinear = new LinearLayout(this);
        rowLinear.setLayoutParams(historyRowParam);
        return rowLinear;
    }
    private float dipToPx(int dipValue){
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue,
                this.getResources().getDisplayMetrics());
    }
    private float getScreenWidth(){
        return this.getResources().getDisplayMetrics().widthPixels;
    }

}
