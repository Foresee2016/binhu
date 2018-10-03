package org.foresee.binhu;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.foresee.binhu.setting.SettingActivity;
import org.foresee.binhu.share.SettingPreferences;

public class DrawerFragment extends Fragment {
    public static DrawerFragment newInstance(DrawerMenuClickListener drawerMenuClickListener) {
        DrawerFragment fragment = new DrawerFragment();
        fragment.setDrawerMenuClickListener(drawerMenuClickListener);
        return fragment;
    }

    private TextView mSetting, mTheme, mMoon; //左滑栏底部三按钮
    private boolean mMoonMode;
    private TextView mMenuHome, mMenuHistory, mMenuStar;//左滑栏竖向菜单项
    private View.OnClickListener menuListener;
    private DrawerMenuClickListener mDrawerMenuClickListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_drawer, container, false);
        mMenuHome = view.findViewById(R.id.home);
        mMenuHistory = view.findViewById(R.id.history);
        mMenuStar = view.findViewById(R.id.star);
        mMenuHome.setOnClickListener(menuListener);
        mMenuHistory.setOnClickListener(menuListener);
        mMenuStar.setOnClickListener(menuListener);

        mSetting = view.findViewById(R.id.btn_setting);
        mSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
            }
        });
        mTheme = view.findViewById(R.id.btn_theme);
        mMoonMode = SettingPreferences.getMoonMode(getActivity());
        mMoon = view.findViewById(R.id.btn_moon);
        freshMoonTextView();
        mMoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMoonMode = !mMoonMode;
                SettingPreferences.setMoonMode(getActivity(), mMoonMode);
                freshMoonTextView();
            }
        });
        return view;
    }

    private void setDrawerMenuClickListener(DrawerMenuClickListener listener) {
        mDrawerMenuClickListener = listener;
        menuListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerMenuClickListener.onMenuClicked(v.getId());
                setOneMenuSelect(mDrawerMenuClickListener.getCurrentMenuId());
            }
        };
    }

    private void setOneMenuSelect(int viewId) {
        mMenuHome.setSelected(mMenuHome.getId() == viewId);
        mMenuHistory.setSelected(mMenuHistory.getId() == viewId);
        mMenuStar.setSelected(mMenuStar.getId() == viewId);
    }

    private void freshMoonTextView() {
        Drawable moonIcon = getResources().getDrawable(mMoonMode ? R.drawable.ic_sun : R.drawable.ic_moon);
        //动态设置图标时，必须用setBounds()，否则不显示
        moonIcon.setBounds(0, 0, moonIcon.getMinimumWidth(), moonIcon.getMinimumHeight());
        mMoon.setCompoundDrawables(moonIcon, null, null, null);
    }

    public interface DrawerMenuClickListener {
        void onMenuClicked(int viewId);

        int getCurrentMenuId();
    }
}
