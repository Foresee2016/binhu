package org.foresee.binhu;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements MedicineFragment.Callbacks {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private ViewPager mViewPager;
    private TabLayout mNavBottom;
    private String[] mTitles = new String[]{"中药", "方剂", "脉学", "经络"};
    private Fragment[] mFragments = new Fragment[mTitles.length];
    private int[] mResNormalIcons = new int[]{R.drawable.selector_ic_leaf,
            R.drawable.selector_ic_prescript,
            R.drawable.selector_ic_sphygmology,
            R.drawable.selector_ic_channel};
    private DrawerFragment.DrawerMenuClickListener mDrawerMenuClickListener;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        mDrawerLayout = findViewById(R.id.main_drawer);
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.app_name, R.string.app_name);
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);
        // 4 Main Fragment
        mFragments[0] = MedicineFragment.newInstance(this);
        mFragments[1] = PrescriptFragment.newInstance();
        mFragments[2] = PrescriptFragment.newInstance();
        mFragments[3] = PrescriptFragment.newInstance();
        // Nav Bottom bar
        mViewPager = findViewById(R.id.view_pager);
        mViewPager.setAdapter(new NavBottomAdapter(getSupportFragmentManager(), mFragments, mTitles));
        mNavBottom = findViewById(R.id.nav_bottom);
        mNavBottom.setupWithViewPager(mViewPager);
        for (int i = 0; i < mFragments.length; i++) {
            TabLayout.Tab item = mNavBottom.getTabAt(i);
            if (item != null) {
                item.setCustomView(R.layout.icon_text_view);
                TextView textView = (TextView) item.getCustomView();
                textView.setText(mTitles[i]);
                textView.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(mResNormalIcons[i]), null, null);
            }
        }
        mNavBottom.getTabAt(0).getCustomView().setSelected(true);
        mNavBottom.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.getCustomView().setSelected(true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getCustomView().setSelected(false);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        FragmentManager fm = getSupportFragmentManager();
//        Fragment contentFrame = fm.findFragmentById(R.id.content_frame);
//        if (contentFrame == null) {
//
//            contentFrame = MedicineFragment.newInstance(this);
//            fm.beginTransaction().add(R.id.content_frame, contentFrame).commit();
//        }
        Fragment drawerFrame = fm.findFragmentById(R.id.drawer_frame);
        if (drawerFrame == null) {
            mDrawerMenuClickListener=new DrawerFragment.DrawerMenuClickListener() {
                @Override
                public void onMenuClicked(int viewId) {
                    //TODO 激活对应viewId的Fragment，填充进主框
                }

                @Override
                public int getCurrentMenuId() {
                    return R.id.home;
                }
            };
            drawerFrame = DrawerFragment.newInstance(mDrawerMenuClickListener);
            fm.beginTransaction().add(R.id.drawer_frame, drawerFrame).commit();
        }
    }

    @Override
    public void onToolbarNavClick() {
        mDrawerLayout.openDrawer(GravityCompat.START);
    }

    public class NavBottomAdapter extends FragmentPagerAdapter {
        private Fragment[] mFragments;
        private String[] mTitles;

        public NavBottomAdapter(FragmentManager fm, Fragment[] fragments, String[] titles) {
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
