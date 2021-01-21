package com.example.x_smartcity_1.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.x_smartcity_1.App;
import com.example.x_smartcity_1.R;
import com.example.x_smartcity_1.bean.GetUsers;
import com.example.x_smartcity_1.net.OKHttpTo;
import com.example.x_smartcity_1.net.OkHttpLo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.example.x_smartcity_1.App.getUserida;

public class Activity_geren_user extends AppCompatActivity {

    private TextView btnFanhui;
    private EditText edUser;
    private EditText edPass;
    private Button btnLogin;
    private List<GetUsers> getUsers;
    private String user,pass;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gren_user);
        initView();

        btnFanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = edUser.getText().toString();
                pass = edPass.getText().toString();
                if (user.equals("")){
                    Toast.makeText(Activity_geren_user.this,"请输入用户名",Toast.LENGTH_SHORT).show();
                }else if (pass.equals("")){
                    Toast.makeText(Activity_geren_user.this,"请输入密码",Toast.LENGTH_SHORT).show();
                }else {
                    getOKhttp();
                }
            }
        });

    }

    private void getOKhttp() {
        if (getUsers == null){
            getUsers = new ArrayList<>();
        }else {
            getUsers.clear();
        }
        new OKHttpTo()
                .setUrl("getUsers")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        getUsers.addAll((Collection<? extends GetUsers>) new Gson().fromJson(jsonObject.optString("ROWS_DETAIL").toString(),
                                new TypeToken<List<GetUsers>>(){}.getType()));

                        for (int i=0;i<getUsers.size();i++){
                            GetUsers users = getUsers.get(i);
                            String suser = users.getUsername();
                            if (suser.equals(user)){
                                getOKhttppass(users.getUserid());
                            }else {

                            }
                        }
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void getOKhttppass(final String userid) {
        new OKHttpTo()
                .setUrl("getPwd")
                .setJSONObject("userid",userid)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        String pwd = jsonObject.optString("password");
                        if (pwd.equals(pass)){
                            Toast.makeText(Activity_geren_user.this,"登录成功",Toast.LENGTH_SHORT).show();

                            App.setUserida(userid);

//                            setResult(RESULT_OK,null);

                            finish();
                        }else {
                            Toast.makeText(Activity_geren_user.this,"密码错误",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();

    }


    private void initView() {
        btnFanhui = findViewById(R.id.btn_fanhui);
        edUser = findViewById(R.id.ed_user);
        edPass = findViewById(R.id.ed_pass);
        btnLogin = findViewById(R.id.btn_login);
    }
}