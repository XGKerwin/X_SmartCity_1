package com.example.x_smartcity_1.fragment.ditie;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.x_smartcity_1.R;
import com.example.x_smartcity_1.adapter.DITIE_home_adapter;
import com.example.x_smartcity_1.bean.GetAllStationById;
import com.example.x_smartcity_1.bean.GetSubwaysByStation;
import com.example.x_smartcity_1.fragment.ditie.Fragment_ditie_luxian;
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
 * date   : 2021/1/20  23:52
 */
public class Fragment_zhuye_ditie extends Fragment {
    private ImageView back;
    private EditText edDidian;
    private Button btnSousuo;
    private ImageView btnLuxian;
    private ListView listview;
    private String S_didian = "建国门站";
    private FragmentTransaction fragmentTransaction;
    private List<GetSubwaysByStation> subwaysByStations;
    private DITIE_home_adapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_zhuye_ditie, null);
        initView(view);

        btn();
        getOkhttp();


        return view;
    }

    private void getOkhttp() {
        if (subwaysByStations == null){
            subwaysByStations = new ArrayList<>();
        }else {
            subwaysByStations.clear();
        }
        new OKHttpTo()
                .setUrl("getSubwaysByStation")
                .setJSONObject("stationName",S_didian)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        subwaysByStations.addAll((Collection<? extends GetSubwaysByStation>) new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString()
                            ,new TypeToken<List<GetSubwaysByStation>>(){}.getType()));
                        if (subwaysByStations.size() == 0 ){
                            Toast.makeText(getContext(),"没有找到"+S_didian,Toast.LENGTH_SHORT).show();
                        }else {
                            adapter = new DITIE_home_adapter(subwaysByStations,getActivity(),S_didian);
                            listview.setAdapter(adapter);
                        }


                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void btn() {
        btnLuxian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_ditie_luxian());
            }
        });
        btnSousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                S_didian = edDidian.getText().toString().trim();
                getOkhttp();
            }
        });
    }

    private void getFragment(Fragment fragment) {
        fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home,fragment).commit();
    }

    private void initView(View view) {
        back = view.findViewById(R.id.back);
        edDidian = view.findViewById(R.id.ed_didian);
        btnSousuo = view.findViewById(R.id.btn_sousuo);
        btnLuxian = view.findViewById(R.id.btn_luxian);
        listview = view.findViewById(R.id.listview);
    }
}
