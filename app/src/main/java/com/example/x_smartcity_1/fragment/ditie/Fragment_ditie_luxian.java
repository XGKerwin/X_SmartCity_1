package com.example.x_smartcity_1.fragment.ditie;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.x_smartcity_1.R;
import com.example.x_smartcity_1.net.OKHttpTo;
import com.example.x_smartcity_1.net.OkHttpLo;
import com.example.x_smartcity_1.net.OkHttpLoImage;
import com.example.x_smartcity_1.net.OkHttpToImage;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/1/22  14:19
 */
public class Fragment_ditie_luxian extends Fragment {

    private FragmentTransaction fragmentTransaction;
    private ImageView back;
    private TextView xianlu1;
    private TextView xianlu2;
    private TextView xianlu3;
    private TextView xianlu4;
    private ImageView img;
    private TextView txtXianlu;
    private String url = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_ditie_luxian, null);
        initView(view);

        getOkHttp("1");
        btn();


        return view;
    }

    private void getOkHttp(String id) {
        new OKHttpTo()
                .setUrl("getSubwaysImage")
                .setJSONObject("id",id)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        url = jsonObject.optString("image");
                        getImage();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void getImage() {
        new OkHttpToImage()
                .setUrl(url)
                .setOkHttpLoImage(new OkHttpLoImage() {
                    @Override
                    public void onResponse(Call call, Bitmap bitmap) {
                        img.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onFailure(Call call, IOException e) {

                    }
                }).start();
    }

    private void btn() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_zhuye_ditie());
            }
        });

        xianlu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtXianlu.setText("地铁1号线");
                getOkHttp("1");
            }
        });

        xianlu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtXianlu.setText("地铁2号线");
                getOkHttp("2");
            }
        });


        xianlu3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtXianlu.setText("地铁3号线");
                getOkHttp("3");
            }
        });


        xianlu4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtXianlu.setText("地铁4号线");
                getOkHttp("4");
            }
        });


    }

    private void getFragment(Fragment fragment) {
        fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home, fragment).commit();
    }

    private void initView(View view) {
        back = view.findViewById(R.id.back);
        xianlu1 = view.findViewById(R.id.xianlu1);
        xianlu2 = view.findViewById(R.id.xianlu2);
        xianlu3 = view.findViewById(R.id.xianlu3);
        xianlu4 = view.findViewById(R.id.xianlu4);
        img = view.findViewById(R.id.img);
        txtXianlu = view.findViewById(R.id.txt_xianlu);
    }
}
