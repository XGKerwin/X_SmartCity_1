package com.example.x_smartcity_1.fragment.page;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.x_smartcity_1.R;
import com.example.x_smartcity_1.fragment.Fragment_home;
import com.example.x_smartcity_1.fragment.Fragment_wlsz;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/30 19:43
 */
public class Fragment_5 extends Fragment {

    private Button btnWlsz;
    private Button btnStart;
    private FragmentTransaction fragmentTransaction;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_5, null);
        initView(view);

        //进入首页
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_home());
            }
        });

        //进入ip地址调整界面
        btnWlsz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_wlsz());
            }
        });



        return view;
    }

    private void getFragment(Fragment fragment) {
        fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment,fragment).commit();
    }


    private void initView(View view) {
        btnWlsz = view.findViewById(R.id.btn_wlsz);
        btnStart = view.findViewById(R.id.btn_start);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
