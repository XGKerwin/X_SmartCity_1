package com.example.x_smartcity_1.fragment.wode;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.x_smartcity_1.App;
import com.example.x_smartcity_1.R;
import com.example.x_smartcity_1.bean.GetUserInfo;
import com.example.x_smartcity_1.net.OKHttpTo;
import com.example.x_smartcity_1.net.OkHttpLo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class Fragment_wode_geren extends Fragment {

    private TextView txtFanhui;
    private TextView txtXiugai;
    private TextView txtName;
    private TextView txtSex;
    private TextView txtTel;
    private TextView txtIdcard;
    private FragmentTransaction fragmentTransaction;
    private String userid;
    private List<GetUserInfo> getUserInfos;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_wode_geren, null);
        initView(view);
        btn();

        userid = App.getUserida();

        getOkHttp();

        return view;
    }

    private void getOkHttp() {
        if (getUserInfos == null){
            getUserInfos = new ArrayList<>();
        }else {
            getUserInfos.clear();
        }
        if (userid == null){
            return;
        }else {
            new OKHttpTo()
                    .setUrl("getUserInfo")
                    .setJSONObject("userid",userid)
                    .setOkHttpLo(new OkHttpLo() {
                        @Override
                        public void onResponse(JSONObject jsonObject) {
                            getUserInfos.addAll((Collection<? extends GetUserInfo>) new Gson().fromJson(jsonObject.optString("ROWS_DETAIL").toString(),
                                    new TypeToken<List<GetUserInfo>>(){}.getType()));
                            txtName.setText(getUserInfos.get(0).getName());
                            txtSex.setText(getUserInfos.get(0).getSex());
                            txtIdcard.setText(getUserInfos.get(0).getId());
                            txtTel.setText(getUserInfos.get(0).getPhone());
                        }

                        @Override
                        public void onFailure(IOException obj) {

                        }
                    }).start();
        }
    }

    private void btn() {
        txtFanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_wode());
            }
        });
        txtXiugai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_wode_geren_xiugai(getUserInfos));
            }
        });
    }

    private void getFragment(Fragment fragment) {
        fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home,fragment).commit();
    }

    private void initView(View view) {
        txtFanhui = view.findViewById(R.id.txt_fanhui);
        txtXiugai = view.findViewById(R.id.txt_xiugai);
        txtName = view.findViewById(R.id.txt_name);
        txtSex = view.findViewById(R.id.txt_sex);
        txtTel = view.findViewById(R.id.txt_tel);
        txtIdcard = view.findViewById(R.id.txt_idcard);
    }
}
