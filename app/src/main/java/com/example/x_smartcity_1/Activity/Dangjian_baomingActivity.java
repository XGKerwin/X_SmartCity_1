package com.example.x_smartcity_1.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.x_smartcity_1.R;

public class Dangjian_baomingActivity extends AppCompatActivity {

    private ImageView back;
    private LinearLayout layout1;
    private LinearLayout layout2;
    private LinearLayout layout3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangjian_baoming);
        initView();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        layout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Dangjian_baomingActivity.this,"天安门广场举行国庆升旗仪式",Toast.LENGTH_SHORT).show();
            }
        });
        layout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Dangjian_baomingActivity.this,"迟到8个月的“年夜饭”：火神山参建者王志齐回家",Toast.LENGTH_SHORT).show();
            }
        });
        layout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Dangjian_baomingActivity.this,"香港公布2020年授勋名单，梁君彦等4人荣获大紫荆勋章",Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initView() {
        back = findViewById(R.id.back);
        layout1 = findViewById(R.id.layout_1);
        layout2 = findViewById(R.id.layout_2);
        layout3 = findViewById(R.id.layout_3);
    }
}