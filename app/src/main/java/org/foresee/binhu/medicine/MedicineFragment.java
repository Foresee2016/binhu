package org.foresee.binhu.medicine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.foresee.binhu.R;
import org.foresee.binhu.search.MedicineSearchActivity;
import org.foresee.binhu.share.TabsAdapter;
import org.foresee.binhu.web.UpdateInfoActivity;

public class MedicineFragment extends Fragment {
    public static MedicineFragment newInstance(Callbacks callbacks) {
        MedicineFragment fragment = new MedicineFragment();
        fragment.setCallbacks(callbacks);
        return fragment;
    }

    private static final String TAG = "MedicineFragment";
    private static final String ARGS_CALLBACKS = "CallBacks";
    private Callbacks mCallbacks;

    private Toolbar mToolbar;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private String[] mTitles = new String[]{"全部", "最近更新", "搜索", "分类"};
    private Fragment[] mFragments = new Fragment[mTitles.length];

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_medicine, container, false);
        mToolbar = view.findViewById(R.id.tool_bar);
        initToolbar();
        for (int i = 0; i < mFragments.length; i++) {
            mFragments[i] = MedicineAllFragment.newInstance();
        }
        mViewPager = view.findViewById(R.id.view_pager);
        mViewPager.setAdapter(new TabsAdapter(getChildFragmentManager(), mFragments, mTitles));
        mTabLayout = view.findViewById(R.id.tab_layout);
        mTabLayout.setupWithViewPager(mViewPager);
        for (int i = 0; i < mTitles.length; i++) {
            TabLayout.Tab item=mTabLayout.getTabAt(i);
            if(item!=null){
                item.setCustomView(R.layout.icon_text_view);
                TextView textView = (TextView) item.getCustomView();
                if (textView != null) {
                    textView.setText(mTitles[i]);
                }
            }
        }
        return view;
    }
    private static final int REQ_UPDATE=1;
    private void initToolbar() {
        mToolbar.inflateMenu(R.menu.toolbar_main);
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.read_pic:
                        Toast.makeText(getActivity(), "Read Pic", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.update:
//                        Toast.makeText(getActivity(), "Update data", Toast.LENGTH_SHORT).show();
                        Intent updateIntent=UpdateInfoActivity.newIntent(getActivity());
                        startActivityForResult(updateIntent, REQ_UPDATE);
                        return true;
                    case R.id.search_medicine:
                        Intent searchIntent=new Intent(getActivity(), MedicineSearchActivity.class);
                        startActivity(searchIntent);
                        return true;
                    default:
                        return false;
                }
            }
        });
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallbacks.onToolbarNavClick();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQ_UPDATE){
            Log.i(TAG, "onActivityResult: resultCode = "+resultCode);
            boolean hasNewData=UpdateInfoActivity.hasNewData(data);
            Log.i(TAG, "onActivityResult: hasNewData = "+hasNewData);
            if(hasNewData){
                ((MedicineAllFragment)mFragments[0]).freshDbToUI();
            }
        }
    }

    public void setCallbacks(Callbacks callbacks) {
        mCallbacks = callbacks;
    }

    public interface Callbacks {
        void onToolbarNavClick();
    }

}
