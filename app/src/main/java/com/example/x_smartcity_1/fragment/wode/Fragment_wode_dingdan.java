package com.example.x_smartcity_1.fragment.wode;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.x_smartcity_1.App;
import com.example.x_smartcity_1.R;
import com.example.x_smartcity_1.adapter.WODE_dingdan_adapter;
import com.example.x_smartcity_1.bean.GetOrderById;
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
 * date   : 2021/1/20  11:12
 */
public class Fragment_wode_dingdan extends Fragment {
    private TextView txtFanhui;
    private ListView listview;
    private TextView txtTime;
    private TextView txtJine;
    private FragmentTransaction fragmentTransaction;
    private String userid;
    private List<GetOrderById> getOrderByIds;
    private TextView txtLeixing;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_wode_geren_dingdan, null);
        initView(view);
        btn();

        userid = App.getUserida();

        getOkHttp();


        return view;
    }

    private void getOkHttp() {
        if (getOrderByIds == null) {
            getOrderByIds = new ArrayList<>();
        } else {
            getOrderByIds.clear();
        }
        new OKHttpTo()
                .setUrl("getOrderById")
                .setJSONObject("id", userid)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        txtLeixing.setText(jsonObject.optString("type"));
                        txtTime.setText(jsonObject.optString("date"));
                        txtJine.setText(jsonObject.optString("cost"));
                        getOrderByIds.addAll((Collection<? extends GetOrderById>) new Gson().fromJson(jsonObject.optString("ROWS_DETAIL").toString(),
                                new TypeToken<List<GetOrderById>>(){}.getType()));
                        Log.d("wodeaaaaaa", "onResponse: "+getOrderByIds.size());
                        showlist();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void showlist() {
        WODE_dingdan_adapter adapter = new WODE_dingdan_adapter(getOrderByIds);
        listview.setAdapter(adapter);
    }

    private void btn() {
        txtFanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_wode());
            }
        });
    }

    private void getFragment(Fragment fragment) {
        fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home, fragment).commit();
    }

    private void initView(View view) {
        txtFanhui = view.findViewById(R.id.txt_fanhui);
        listview = view.findViewById(R.id.listview);
        txtTime = view.findViewById(R.id.txt_time);
        txtJine = view.findViewById(R.id.txt_jine);
        txtLeixing = view.findViewById(R.id.txt_leixing);
    }
}
