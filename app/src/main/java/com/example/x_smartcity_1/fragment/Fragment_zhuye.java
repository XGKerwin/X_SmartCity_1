package com.example.x_smartcity_1.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.x_smartcity_1.R;
import com.example.x_smartcity_1.adapter.ZHUYE_fuwu_adapter;
import com.example.x_smartcity_1.adapter.ZHUYE_xinwen_adapter;
import com.example.x_smartcity_1.adapter.ZHUYE_zhuti_adapter;
import com.example.x_smartcity_1.bean.GetImages;
import com.example.x_smartcity_1.bean.GetNEWSContent;
import com.example.x_smartcity_1.bean.GetNewsInfoBySubject;
import com.example.x_smartcity_1.bean.Service_info;
import com.example.x_smartcity_1.net.OKHttpTo;
import com.example.x_smartcity_1.net.OkHttpLo;
import com.example.x_smartcity_1.net.OkHttpLoImage;
import com.example.x_smartcity_1.net.OkHttpToImage;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import okhttp3.Call;

public class Fragment_zhuye extends Fragment {

    private List<GetImages> getImages;
    private FragmentTransaction fragmentTransaction;
    private ViewFlipper viewFlipper;
    private GridView gridFuwu;
    private GridView girdZhuti;
    private LinearLayout newsLayout;
    private List<Service_info> service_infos;
    private ZHUYE_fuwu_adapter fuwu_adapter;
    private ZHUYE_zhuti_adapter zhuti_adapter;
    private ListView listview;
    private ZHUYE_xinwen_adapter xinwen_adapter;
    private List<GetNEWSContent> getNEWSContents;
    private List<GetNewsInfoBySubject> getNewsInfoBySubjects;
    private List<ImageView> imageViews;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_zhuye, container, false);
        View view = View.inflate(getContext(), R.layout.fragment_zhuye, null);
        initView(view);
        imageViews = new ArrayList<>();
        getImagView();      //轮播图
        getfuwu();          //关于服务的内容
        gettheme();         //热门主题
        getnews();          //新闻
        btn_zhuti();        //主题点击事件
        return view;
    }

    private void btn_zhuti() {
        girdZhuti.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        getNewsInfoBySubject("电影");
                        break;
                    case 1:
                        getNewsInfoBySubject("国庆专题");
                        break;
                    case 2:
                        getNewsInfoBySubject("抗肺炎");
                        break;
                    case 3:
                        getNewsInfoBySubject("烈士纪念日");
                        break;

                }
            }
        });
    }

    private void getNewsInfoBySubject(final String s) {
        if (getNewsInfoBySubjects == null){
            getNewsInfoBySubjects = new ArrayList<>();
        }else {
            getNewsInfoBySubjects.clear();
        }
        new OKHttpTo()
                .setUrl("getNewsInfoBySubject")
                .setJSONObject("subject",s)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        getNewsInfoBySubjects.addAll((Collection<? extends GetNewsInfoBySubject>) new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<GetNewsInfoBySubject>>(){}.getType()));
                        getFragment(new Fragment_zhuye_zhuti(getNewsInfoBySubjects,s));
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void getnews() {
        if (getNEWSContents == null){
            getNEWSContents = new ArrayList<>();
        }else {
            getNEWSContents.clear();
        }
        for (int i=1;i<=10;i++){
            GetNEWSContent(i);
        }
    }

    private void GetNEWSContent(int i) {
        new OKHttpTo()
                .setUrl("getNEWSContent")
                .setJSONObject("newsid",i)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {

                        GetNEWSContent getNEWSContent = new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL")
                                        .optJSONObject(0).toString(),
                                GetNEWSContent.class);
                        getNEWSContents.add(getNEWSContent);
                        if (getNEWSContents.size() == 10){
                            xinwen_adapter = new ZHUYE_xinwen_adapter(getActivity(),getNEWSContents);
                            listview.setAdapter(xinwen_adapter);
                        }
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void gettheme() {
        new OKHttpTo()
                .setUrl("getAllSubject")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        List<String> strings = new ArrayList<>();
                        String jsonArray[] = jsonObject.optString("ROWS_DETAIL").replace("[", "").replace("]", "").split(",");
                        for (int i = 0; i < jsonArray.length; i++) {
                            strings.add(jsonArray[i].trim());
                        }
                        zhuti_adapter = new ZHUYE_zhuti_adapter(getActivity(), strings);
                        girdZhuti.setAdapter(zhuti_adapter);
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void getfuwu() {
        if (service_infos == null) {
            service_infos = new ArrayList<>();
        } else {
            service_infos.clear();
        }

        for (int i = 1; i < 10; i++) {
            getOkHttpinfo(i);
        }

    }

    private void getOkHttpinfo(final int i) {
        new OKHttpTo()
                .setUrl("service_info")
                .setJSONObject("serviceid", i)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        JSONObject jsonObject1 = jsonArray.optJSONObject(0);
                        Service_info service_info = new Gson().fromJson(jsonObject1.toString(), Service_info.class);
                        service_infos.add(service_info);
                        if (service_infos.size() == 9) {
                            fuwu_adapter = new ZHUYE_fuwu_adapter(service_infos, getActivity());
                            gridFuwu.setAdapter(fuwu_adapter);
                        }
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


    }

    private void getImagView() {
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
            new OkHttpToImage()
                    .setUrl(getImages.get(i).getPath())
                    .setOkHttpLoImage(new OkHttpLoImage() {
                        @Override
                        public void onResponse(Call call, Bitmap bitmap) {
                            ImageView imageView = new ImageView(getContext());
                            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                            imageView.setImageBitmap(bitmap);
                            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                            imageViews.add(imageView);
                            imageView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    getFragment(new Fragment_xinwen1(getImages.get(finalI).getPath()));
                                }
                            });
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

    private void getFragment(Fragment fragment) {
        fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home, fragment).commit();
    }


    private void initView(View view) {
        viewFlipper = view.findViewById(R.id.view_flipper);
        gridFuwu = view.findViewById(R.id.grid_fuwu);
        girdZhuti = view.findViewById(R.id.gird_zhuti);
        newsLayout = view.findViewById(R.id.news_layout);
        listview = view.findViewById(R.id.listview);
    }
}
