package org.foresee.binhu;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MedicineFragment.Callbacks{
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private BottomNavigationView mBottomNavigationView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        mDrawerLayout=findViewById(R.id.main_drawer);
        mActionBarDrawerToggle=new ActionBarDrawerToggle(this, mDrawerLayout, R.string.app_name, R.string.app_name);
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);
        FragmentManager fm=getSupportFragmentManager();
        Fragment contentFrame=fm.findFragmentById(R.id.content_frame);
        if(contentFrame==null){
            contentFrame=MedicineFragment.newInstance(this);
            fm.beginTransaction().add(R.id.content_frame, contentFrame).commit();
        }
        Fragment drawerFrame=fm.findFragmentById(R.id.drawer_frame);
        if(drawerFrame==null){
            drawerFrame=DrawerFragment.newInstance();
            fm.beginTransaction().add(R.id.drawer_frame, drawerFrame).commit();
        }
    }

    @Override
    public void onToolbarNavClick() {
        mDrawerLayout.openDrawer(GravityCompat.START);
    }

}
