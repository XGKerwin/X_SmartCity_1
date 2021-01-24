package com.example.x_smartcity_1.fragment.zhuye;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.x_smartcity_1.R;
import com.example.x_smartcity_1.adapter.ZHUYE_sousuo_adapter;
import com.example.x_smartcity_1.bean.GetNewsByKeys;

import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/1/24  8:57
 */
public class Fragment_sousuo extends Fragment {
    private ListView listview;
    private List<GetNewsByKeys> getNewsByKeys;
    private ZHUYE_sousuo_adapter adapter;

    public Fragment_sousuo(List<GetNewsByKeys> getNewsByKeys) {
        this.getNewsByKeys = getNewsByKeys;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_sousuo, null);
        initView(view);


        adapter = new ZHUYE_sousuo_adapter(getNewsByKeys);
        listview.setAdapter(adapter);

        return view;
    }

    private void initView(View view) {
        listview = view.findViewById(R.id.listview);
    }
}
