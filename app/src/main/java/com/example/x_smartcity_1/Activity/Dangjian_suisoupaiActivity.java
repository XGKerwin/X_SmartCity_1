package com.example.x_smartcity_1.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.x_smartcity_1.R;

public class Dangjian_suisoupaiActivity extends AppCompatActivity {

    private ImageView back;
    private EditText edMsg;
    private GridView gridview;
    private TextView tijiao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangjian_suisoupai);
        initView();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = edMsg.getText().toString();
                if (s.equals("")){
                    Toast.makeText(Dangjian_suisoupaiActivity.this,"请输入内容",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Dangjian_suisoupaiActivity.this,"提交成功",Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });


    }

    private void initView() {
        back = findViewById(R.id.back);
        edMsg = findViewById(R.id.ed_msg);
        gridview = findViewById(R.id.gridview);
        tijiao = findViewById(R.id.tijiao);
    }
}