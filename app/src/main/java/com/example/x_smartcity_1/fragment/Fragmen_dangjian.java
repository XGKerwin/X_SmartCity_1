package com.example.x_smartcity_1.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.x_smartcity_1.Activity.Dangjian_baomingActivity;
import com.example.x_smartcity_1.Activity.Dangjian_huodongActivity;
import com.example.x_smartcity_1.Activity.Dangjian_suisoupaiActivity;
import com.example.x_smartcity_1.Activity.Dangjian_xuexiActivity;
import com.example.x_smartcity_1.R;
import com.example.x_smartcity_1.adapter.DANGJIAN_listview_adapter;
import com.example.x_smartcity_1.bean.GetImages;
import com.example.x_smartcity_1.bean.GetNEWsList;
import com.example.x_smartcity_1.fragment.zhuye.Fragment_zhuye;
import com.example.x_smartcity_1.net.OKHttpTo;
import com.example.x_smartcity_1.net.OkHttpLo;
import com.example.x_smartcity_1.net.OkHttpLoImage;
import com.example.x_smartcity_1.net.OkHttpToImage;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import okhttp3.Call;

public class Fragmen_dangjian extends Fragment {

    private ViewFlipper viewFlipper;
    private ListView listview;
    private DANGJIAN_listview_adapter adapter;
    private List<ImageView> imageViews;
    private List<GetImages> getImages;
    private List<GetNEWsList> neWsLists;
    private ImageView imgXuexi;
    private ImageView imgHuodong;
    private ImageView imgSuishoupai;
    private ImageView imgBaoming;

    public static Fragmen_dangjian nweInstance(){
        return  new Fragmen_dangjian();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_dangjian, null);
        initView(view);
        imageViews = new ArrayList<>();
        getImage();
        getdongtai();   //动态新闻

        btn();
        return view;
    }

    private void btn() {
        imgXuexi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Dangjian_xuexiActivity.class);
                startActivity(intent);
            }
        });

        imgBaoming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Dangjian_baomingActivity.class);
                startActivity(intent);
            }
        });

        imgHuodong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Dangjian_huodongActivity.class);
                startActivity(intent);
            }
        });

        imgSuishoupai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Dangjian_suisoupaiActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getdongtai() {
        if (neWsLists == null) {
            neWsLists = new ArrayList<>();
        } else {
            neWsLists.clear();
        }
        new OKHttpTo()
                .setUrl("getNEWsList")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        neWsLists.addAll((Collection<? extends GetNEWsList>) new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<GetNEWsList>>() {
                                }.getType()));
                        adapter = new DANGJIAN_listview_adapter(neWsLists);
                        listview.setAdapter(adapter);
                        adapter.MyOnclickdangjian(new DANGJIAN_listview_adapter.MyOnclickdangjian() {
                            @Override
                            public void setOnclick(String url) {
                                Intent intent = new Intent(Intent.ACTION_VIEW);
                                intent.setData(Uri.parse(url));
                                startActivity(intent);
                            }
                        });

                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void getImage() {
        if (getImages == null) {
            getImages = new ArrayList<>();
        } else {
            getImages.clear();
        }
        new OKHttpTo()
                .setUrl("getImages")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        getImages.addAll((Collection<? extends GetImages>) new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<GetImages>>() {
                                }.getType()));
                        getImag();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void getImag() {
        for (int i = 0; i < getImages.size(); i++) {
            final int finalI = i;
            final ImageView imageView = new ImageView(getContext());
            new OkHttpToImage()
                    .setUrl(getImages.get(i).getPath())
                    .setOkHttpLoImage(new OkHttpLoImage() {
                        @Override
                        public void onResponse(Call call, Bitmap bitmap) {
                            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                            imageView.setImageBitmap(bitmap);
                            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                            imageViews.add(imageView);
                            if (imageViews.size() == 5) {
                                for (int i = 0; i < imageViews.size(); i++) {
                                    viewFlipper.addView(imageViews.get(i));
                                }
                                viewFlipper.startFlipping();
                            }
                        }

                        @Override
                        public void onFailure(Call call, IOException e) {

                        }
                    }).start();
        }
    }

    private void initView(View view) {
        viewFlipper = view.findViewById(R.id.view_flipper);
        listview = view.findViewById(R.id.listview);
        imgXuexi = view.findViewById(R.id.img_xuexi);
        imgHuodong = view.findViewById(R.id.img_huodong);
        imgSuishoupai = view.findViewById(R.id.img_suishoupai);
        imgBaoming = view.findViewById(R.id.img_baoming);
    }
}
