package com.example.x_smartcity_1.fragment.wode;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.x_smartcity_1.App;
import com.example.x_smartcity_1.R;
import com.example.x_smartcity_1.bean.GetUserInfo;
import com.example.x_smartcity_1.net.OKHttpTo;
import com.example.x_smartcity_1.net.OkHttpLo;

import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/1/20  10:28
 */
public class Fragment_wode_geren_xiugai extends Fragment {

    private TextView txtFanhui;
    private TextView txtBaocun;
    private EditText edName;
    private EditText edSex;
    private EditText edTel;
    private EditText edId;
    private List<GetUserInfo> getUserInfos;
    private String userid;
    private FragmentTransaction fragmentTransaction;

    public Fragment_wode_geren_xiugai(List<GetUserInfo> getUserInfos) {
        this.getUserInfos = getUserInfos;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_wode_geren_xiugai, null);
        initView(view);

        userid = App.getUserida();

        edName.setHint(getUserInfos.get(0).getName());
        edId.setHint(getUserInfos.get(0).getId());
        edSex.setHint(getUserInfos.get(0).getSex());
        edTel.setHint(getUserInfos.get(0).getPhone());
        edName.setText(getUserInfos.get(0).getName());
        edId.setText(getUserInfos.get(0).getId());
        edSex.setText(getUserInfos.get(0).getSex());
        edTel.setText(getUserInfos.get(0).getPhone());

        btn();


        return view;
    }

    private void btn() {
        txtBaocun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edName.getText().toString().trim();
                String sex = edSex.getText().toString().trim();
                String tel = edTel.getText().toString().trim();
                String id = edId.getText().toString().trim();
                if (name.equals("")){
                    Toast.makeText(getContext(),"姓名不能为空",Toast.LENGTH_SHORT).show();
                }else if (sex.equals("")){
                    Toast.makeText(getContext(),"性别不能为空",Toast.LENGTH_SHORT).show();
                }else if (tel.equals("")){
                    Toast.makeText(getContext(),"手机号不能为空",Toast.LENGTH_SHORT).show();
                }else if (id.equals("")){
                    Toast.makeText(getContext(),"身份证号不能为空",Toast.LENGTH_SHORT).show();
                }else {
                    setUserInfo(name,sex,tel,id);
                }

            }

        });
    }

    private void setUserInfo(String name, String sex, String tel, String id) {

        /**
         {userid:"1","name":"赵b涵","avatar":"user4.png","phone":"33333333dddd5888812","id":"371402199902041133","gender":"女"}
         */

        new OKHttpTo()
                .setUrl("setUserInfo")
                .setJSONObject("userid",userid)
                .setJSONObject("name",name)
                .setJSONObject("avatar","")
                .setJSONObject("gender",sex)
                .setJSONObject("phone",tel)
                .setJSONObject("id",id)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {

                        Toast.makeText(getContext(),"修改成功",Toast.LENGTH_SHORT).show();
                        getFragment(new Fragment_wode_geren());
                    }

                    @Override
                    public void onFailure(IOException obj) {
                        Toast.makeText(getContext(),"修改失败",Toast.LENGTH_SHORT).show();
                    }
                }).start();
    }

    private void getFragment(Fragment fragment) {
        fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home,fragment).commit();
    }


    private void initView(View view) {
        txtFanhui = view.findViewById(R.id.txt_fanhui);
        txtBaocun = view.findViewById(R.id.txt_baocun);
        edName = view.findViewById(R.id.ed_name);
        edSex = view.findViewById(R.id.ed_sex);
        edTel = view.findViewById(R.id.ed_tel);
        edId = view.findViewById(R.id.ed_id);
    }
}
