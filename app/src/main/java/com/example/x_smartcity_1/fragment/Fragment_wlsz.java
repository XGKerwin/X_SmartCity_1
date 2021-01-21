package com.example.x_smartcity_1.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.x_smartcity_1.R;
import com.example.x_smartcity_1.fragment.page.Fragment_5;



public class Fragment_wlsz extends Fragment {
    private Button btnXiugai;
    private Button btnBaocun;
    private FragmentTransaction fragmentTransaction;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_wlsz, null);
        initView(view);

        btnXiugai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"修改成功",Toast.LENGTH_SHORT).show();
            }
        });

        btnBaocun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_5());
                Toast.makeText(getContext(),"保存成功",Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }

    private void getFragment(Fragment fragment) {
        fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment,fragment).commit();

    }

    private void initView(View view) {
        btnXiugai = view.findViewById(R.id.btn_xiugai);
        btnBaocun = view.findViewById(R.id.btn_baocun);
    }
}
