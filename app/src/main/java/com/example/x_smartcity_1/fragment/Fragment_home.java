package com.example.x_smartcity_1.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.x_smartcity_1.R;
import com.example.x_smartcity_1.fragment.fuwu.Fragment_fuwu;
import com.example.x_smartcity_1.fragment.wode.Fragment_wode;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class Fragment_home extends Fragment {

    private FragmentTransaction fragmentTransaction;
    private BottomNavigationView bottomNav;

    @SuppressLint("WrongConstant")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_home, null);
        initView(view);

        bottomNav.setLabelVisibilityMode(1);
        bottomNav.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.bom_home:
                        Toast.makeText(getContext(),"aa",Toast.LENGTH_SHORT).show();
                        getFragment(new Fragment_zhuye());
                        break;
                    case R.id.bom_fuwu:
                        getFragment(new Fragment_fuwu());
                        break;
                    case R.id.bom_dangjian:
                        getFragment(new Fragmen_dangjian());
                        break;
                    case R.id.bom_xinwen:
                        getFragment(new Fragment_xinwen());
                        break;
                    case R.id.bom_wode:
                        getFragment(new Fragment_wode());
                        break;
                }
            }
        });

        return view;
    }


    private void getFragment(Fragment fragment) {
        fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home, fragment).commit();
    }

    private void initView(View view) {
        bottomNav = view.findViewById(R.id.bottom_nav);
    }
}
