package com.example.x_smartcity_1.adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.x_smartcity_1.R;
import com.example.x_smartcity_1.bean.GetAllStationById;
import com.example.x_smartcity_1.fragment.ditie.Fragment_zhuye_ditie;
import com.example.x_smartcity_1.net.OKHttpTo;
import com.example.x_smartcity_1.net.OkHttpLo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/1/22  16:25
 */
public class Fragment_ditie_xianlu extends Fragment {
    private String id;
    private TextView txtDidian1;
    private TextView txtDidian2;
    private ListView listview;
    private String didian;
    private List<GetAllStationById> allStationByIds;
    private DITIE_xianlu_adapter adapter;
    private ImageView back;

    public Fragment_ditie_xianlu(String subwayid, String didian) {
        this.id = subwayid;
        this.didian = didian;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_ditie_xianlu, null);
        initView(view);
        allStationByIds = new ArrayList<>();
        getOkhttp();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_zhuye_ditie());
            }
        });

        return view;
    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home,fragment).commit();
    }

    private void getOkhttp() {
        new OKHttpTo()
                .setUrl("getAllStationById")
                .setJSONObject("id", id)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        allStationByIds.addAll((Collection<? extends GetAllStationById>) new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<GetAllStationById>>() {
                                }.getType()));
                        adapter = new DITIE_xianlu_adapter(allStationByIds, didian);
                        listview.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void initView(View view) {
        txtDidian1 = view.findViewById(R.id.txt_didian1);
        txtDidian2 = view.findViewById(R.id.txt_didian2);
        listview = view.findViewById(R.id.listview);
        back = view.findViewById(R.id.back);
    }
}
