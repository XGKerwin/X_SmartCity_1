package com.example.x_smartcity_1.fragment.fuwu;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.x_smartcity_1.R;
import com.example.x_smartcity_1.adapter.FUWU_adapter;
import com.example.x_smartcity_1.bean.GetServiceByType;
import com.example.x_smartcity_1.net.OKHttpTo;
import com.example.x_smartcity_1.net.OkHttpLo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Fragment_fuwu extends Fragment {


    private EditText edSeek;
    private LinearLayout btnSousuo;
    private TextView fuwu;
    private TextView yanglao;
    private TextView lvyou;
    private TextView huanbao;
    private TextView yiliao;
    private GridView gridview;
    private List<GetServiceByType> getServiceByType;
    private FUWU_adapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_fuwu, null);
        initView(view);

        getService("智慧服务");

        btn();
        

        return view;
    }

    private void getService(String s) {
        if (getServiceByType == null){
            getServiceByType = new ArrayList<>();
        }else {
            getServiceByType.clear();
        }
        new OKHttpTo()
                .setUrl("getServiceByType")
                .setJSONObject("serviceType",s)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        getServiceByType.addAll((Collection<? extends GetServiceByType>) new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<GetServiceByType>>(){}.getType()));
                        if (adapter == null){
                            adapter = new FUWU_adapter(getServiceByType);
                            gridview.setAdapter(adapter);
                        }else {
                            adapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void btn() {
        fuwu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getService("智慧服务");
            }
        });

        yanglao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getService("智慧养老");
            }
        });

        lvyou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getService("智慧旅游");
            }
        });

        huanbao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getService("智慧环保");
            }
        });

        yiliao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getService("智慧医疗");
            }
        });





    }


    private void initView(View view) {
        edSeek = view.findViewById(R.id.ed_seek);
        btnSousuo = view.findViewById(R.id.btn_sousuo);
        fuwu = view.findViewById(R.id.fuwu);
        yanglao = view.findViewById(R.id.yanglao);
        lvyou = view.findViewById(R.id.lvyou);
        huanbao = view.findViewById(R.id.huanbao);
        yiliao = view.findViewById(R.id.yiliao);
        gridview = view.findViewById(R.id.gridview);
    }
}
