package org.foresee.binhu;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class MedicineFragment extends Fragment {
    public static MedicineFragment newInstance(Callbacks callbacks) {
        MedicineFragment fragment=new MedicineFragment();
        fragment.setCallbacks(callbacks);
        return fragment;
    }

    private static final String ARGS_CALLBACKS="CallBacks";
    private Callbacks mCallbacks;

    private Toolbar mToolbar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        mToolbar = view.findViewById(R.id.tool_bar);
        initToolbar();
//        mBottomNavigationView = view.findViewById(R.id.nav_bottom);
//        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId()) {
//                    case R.id.medicine:
//                        Toast.makeText(getActivity(), "PK", Toast.LENGTH_SHORT).show();
//                        return true;
//                    case R.id.prescription:
//                        Toast.makeText(getActivity(), "主页", Toast.LENGTH_SHORT).show();
//                        return true;
//                    case R.id.sphygmology:
//                        Toast.makeText(getActivity(), "我的", Toast.LENGTH_SHORT).show();
//                        return true;
//                    case R.id.channel:
//                        Toast.makeText(getActivity(), "我的", Toast.LENGTH_SHORT).show();
//                        return true;
//                    default:
//                        return false;
//                }
//            }
//        });
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

    public interface Callbacks{
        void onToolbarNavClick();
    }
}
