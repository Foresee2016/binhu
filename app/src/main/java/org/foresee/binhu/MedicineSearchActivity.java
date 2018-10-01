package org.foresee.binhu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class MedicineSearchActivity extends AppCompatActivity {
    private static final String TAG = "MedicineSearchActivity";
    private EditText mSearchText;
    private TextView mCancel;
    private TextView mSpread;
    private LinearLayout mHistoryItems;
    private String[] mHistoryStrings;
    private TextView mClearHistory;
    private View.OnClickListener mHistoryItemListener;
    private static final String DIALOG_DELETE_CONFIRM = "deleteConfirm";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_medicine_search);
        mSearchText = findViewById(R.id.search_edit_text);
        mSearchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    Log.d(TAG, "onEditorAction: Search Text = " + v.getText());
                    String searchText = v.getText().toString();
                    if (searchText.trim().length() == 0) {
                        return true;
                    }
                    SearchHistory.saveHistoryString(MedicineSearchActivity.this, searchText);
                    startSearch(searchText);
                    return true;
                }
                return false;
            }
        });
        mClearHistory = findViewById(R.id.clear_search);
        mClearHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SearchHistory.getHistoryString(MedicineSearchActivity.this).length == 0) {
                    Toast.makeText(MedicineSearchActivity.this, R.string.no_search_history, Toast.LENGTH_SHORT).show();
                    return;
                }
                FragmentManager fragmentManager = getSupportFragmentManager();
                DeleteConfirmDialogFragment dialog = DeleteConfirmDialogFragment.newInstance(R.string.delete_alert_msg,
                        new DeleteConfirmDialogFragment.DeleteConfirmDialogListener() {
                            @Override
                            public void onConfirmed() {
                                SearchHistory.clearHistoryString(MedicineSearchActivity.this);
                                freshHistoryItems();
                            }
                        });
                dialog.show(fragmentManager, DIALOG_DELETE_CONFIRM);
            }
        });
        mSpread = findViewById(R.id.spread_history);
        mSpread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHistoryLimit = !mHistoryLimit;
                freshHistoryItems(mHistoryLimit);
                mSpread.setText(mHistoryLimit ? R.string.spread : R.string.shrink);
            }
        });
        mCancel = findViewById(R.id.cancel);
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mHistoryItemListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = (TextView) v;
//                Toast.makeText(MedicineSearchActivity.this, textView.getText(), Toast.LENGTH_SHORT).show();
                startSearch(textView.getText().toString());
            }
        };
        mHistoryItems = findViewById(R.id.history_items);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSearchText.setText("");
        mSearchText.requestFocus();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            freshHistoryItems();
        }
    }

    private void freshHistoryItems() {
        freshHistoryItems(mHistoryLimit);
    }

    private void freshHistoryItems(boolean limitRows) {
        mHistoryStrings = SearchHistory.getHistoryString(this);
        freshHistoryItems(mHistoryStrings, limitRows);
    }

    private boolean mHistoryLimit = true;
    private static final int HISTORY_LIMIT_ROWS = 2;

    private void freshHistoryItems(String[] items, boolean limitRows) {
        Log.d(TAG, "freshHistoryItems: items = " + Arrays.toString(items));
        mSpread.setVisibility(View.GONE);
        mHistoryItems.removeAllViews();
        LinearLayout rowLinear = createNewLinearRow();
        boolean isNewRow = true;
        mHistoryItems.measure(0, 0);
        float maxWidth = mHistoryItems.getWidth() - mHistoryItems.getPaddingStart() - mHistoryItems.getPaddingEnd();
        Log.v(TAG, "freshHistoryItems: maxWidth = " + maxWidth);
        float remainWidth = maxWidth;
        Log.v(TAG, "freshHistoryItems: init remainWidth = " + remainWidth);
        for (String item : items) {
            TextView textView = (TextView) getLayoutInflater().inflate(R.layout.search_history_item, rowLinear, false);
            textView.setText(item);
            textView.setOnClickListener(mHistoryItemListener);
            textView.measure(0, 0);
            ViewGroup.MarginLayoutParams textMargin = (ViewGroup.MarginLayoutParams) textView.getLayoutParams();
            int textWidth = textView.getMeasuredWidth() + textMargin.leftMargin + textMargin.rightMargin;
            Log.v(TAG, "freshHistoryItems: textWidth = " + textWidth + ", leftMargin = " + textMargin.leftMargin + ", rightMargin = " + textMargin.rightMargin);
            if (remainWidth < textWidth) {
                if (mHistoryItems.getChildCount() == HISTORY_LIMIT_ROWS) {
                    mSpread.setVisibility(View.VISIBLE);
                    if (limitRows) {
                        Log.d(TAG, "freshHistoryItems: 如果限制行数并且当前行已经足够，不再创建新行，跳出去");
                        break;
                    }
                }
                Log.v(TAG, "freshHistoryItems: no enough width remain, create new row.");
                rowLinear = createNewLinearRow();
                remainWidth = maxWidth;
                isNewRow = true;
            }
            rowLinear.addView(textView);
            remainWidth -= textWidth;
            Log.v(TAG, "freshHistoryItems: add new view, remainWidth = " + remainWidth);
            if (isNewRow) {
                Log.v(TAG, "freshHistoryItems: this is new row, add to history items pane");
                mHistoryItems.addView(rowLinear);
                isNewRow = false;
            }
        }
    }

    private LinearLayout.LayoutParams mHistoryRowParam;

    private LinearLayout createNewLinearRow() {
        if (mHistoryRowParam == null) {
            mHistoryRowParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            mHistoryRowParam.setMargins(0, (int) dipToPx(8), 0, 0);
        }
        LinearLayout rowLinear = new LinearLayout(this);
        rowLinear.setLayoutParams(mHistoryRowParam);
        return rowLinear;
    }

    private void startSearch(String searchText) {
        Log.d(TAG, "startSearch: searchText = " + searchText);
        Intent intent = MedicineSearchResultActivity.newIntent(MedicineSearchActivity.this, searchText);
        startActivity(intent);
    }

    private float dipToPx(int dipValue) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue,
                this.getResources().getDisplayMetrics());
    }

}
