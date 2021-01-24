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
import com.example.x_smartcity_1.fragment.xinwen.Fragment_xinwen;
import com.example.x_smartcity_1.fragment.zhuye.Fragment_zhuye;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;
import java.util.Map;


public class Fragment_home extends Fragment {

    private FragmentTransaction fragmentTransaction;
    private BottomNavigationView bottomNav;

//    Map<String,Fragment> fragmentMap = new HashMap<>();
    @SuppressLint("WrongConstant")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_home, null);
        initView(view);

//        fragmentMap.put("a",new Fragment_zhuye());
//        fragmentMap.put("b",new Fragment_fuwu());
//        fragmentMap.put("c",new Fragmen_dangjian());
//        fragmentMap.put("d",new Fragment_xinwen());
//        fragmentMap.put("e",new Fragment_wode());

//        getFragment(fragmentMap.get("a"));

        getFragment(new Fragment_zhuye());

        bottomNav.setLabelVisibilityMode(1);    //显示下列文字
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.bom_home:
//                        getFragment(fragmentMap.get("a"));
                        getFragment(new Fragment_zhuye());
                        break;
                    case R.id.bom_fuwu:
//                        getFragment(fragmentMap.get("b"));
                        getFragment(new Fragment_fuwu());
                        break;
                    case R.id.bom_dangjian:
//                        getFragment(fragmentMap.get("c"));
                        getFragment(new Fragmen_dangjian());
                        break;
                    case R.id.bom_xinwen:
//                        getFragment(fragmentMap.get("d"));
                        getFragment(new Fragment_xinwen());
                        break;
                    case R.id.bom_wode:
//                        getFragment(fragmentMap.get("e"));
                        getFragment(new Fragment_wode());
                        break;
                }
                return true;
            }
        });



        return view;
    }


    Fragment currentFragment = new Fragment();
    private void getFragment(Fragment fragment) {
//        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//        if (!fragment.isAdded()){
//            if (currentFragment==null){
//                transaction.hide(currentFragment);
//            }
//            transaction.add(R.id.fragment_home,fragment,fragment.getClass().getName());
//        }else {
//            transaction.hide(currentFragment).show(fragment);
//        }
//        currentFragment = fragment;
//        transaction.commit();

        fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home,fragment).commit();

    }

    private void initView(View view) {
        bottomNav = view.findViewById(R.id.bottom_nav);
    }
}
