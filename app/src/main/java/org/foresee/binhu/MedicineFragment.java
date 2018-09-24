package org.foresee.binhu;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MedicineFragment extends Fragment {
    public static MedicineFragment newInstance(Callbacks callbacks) {
        MedicineFragment fragment = new MedicineFragment();
        fragment.setCallbacks(callbacks);
        return fragment;
    }

    private static final String ARGS_CALLBACKS = "CallBacks";
    private Callbacks mCallbacks;

    private Toolbar mToolbar;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private String[] mTitles = new String[]{"全部", "上品", "温", "凉", "寒"};
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
                textView.setText(mTitles[i]);
            }
        }
        return view;
    }

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
                        Toast.makeText(getActivity(), "Update data", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.search:
                        Toast.makeText(getActivity(), "Search Medicine", Toast.LENGTH_SHORT).show();
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

    public void setCallbacks(Callbacks callbacks) {
        mCallbacks = callbacks;
    }

    public interface Callbacks {
        void onToolbarNavClick();
    }

    public class TabsAdapter extends FragmentPagerAdapter {
        private Fragment[] mFragments;
        private String[] mTitles;

        public TabsAdapter(FragmentManager fm, Fragment[] fragments, String[] titles) {
            super(fm);
            mFragments = fragments;
            mTitles = titles;
        }

        @Override
        public Fragment getItem(int i) {
            return mFragments[i];
        }

        @Override
        public int getCount() {
            return mFragments.length;
        }
    }
}
