package com.example.x_smartcity_1.fragment.xinwen;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.x_smartcity_1.R;
import com.example.x_smartcity_1.adapter.XINWEN_adapter;
import com.example.x_smartcity_1.bean.GetImages;
import com.example.x_smartcity_1.bean.GetNEWsList;
import com.example.x_smartcity_1.fragment.Fragmen_dangjian;
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

public class Fragment_xinwen extends Fragment {

    private ViewFlipper viewFlipper;
    private TextView txtShizheng;
    private TextView txtYiqing;
    private ListView listview;
    private List<GetImages> getImages;
    private List<ImageView> imageViews;
    private List<GetNEWsList> neWsLists;
    private XINWEN_adapter adapter;
    private TextView txtYule;
    private FragmentTransaction fragmentTransaction;

    public static Fragment_xinwen nweInstance(){
        return  new Fragment_xinwen();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_xinwen, null);
        initView(view);
        imageViews = new ArrayList<>();
        getImage();     //轮播图
        getnewlist();
        btn();

        return view;
    }

    private void btn() {
        txtShizheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_xinwen_title("时政"));
            }
        });

        txtYiqing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_xinwen_title("疫情"));
            }
        });

        txtYule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_xinwen_title("娱乐"));
            }
        });
    }

    private void getFragment(Fragment fragment) {
        fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home,fragment).commit();
    }

    private void getnewlist() {
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
                        adapter = new XINWEN_adapter(neWsLists, getActivity());
                        listview.setAdapter(adapter);
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
            final int finalI = i+1;
            new OkHttpToImage()
                    .setUrl(getImages.get(i).getPath())
                    .setOkHttpLoImage(new OkHttpLoImage() {
                        @Override
                        public void onResponse(Call call, Bitmap bitmap) {
                            try {
                                ImageView imageView = new ImageView(getContext());
                                imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                                imageView.setImageBitmap(bitmap);
                                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                                imageViews.add(imageView);
                                imageView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        getFragment(new Fragment_xinwen1(finalI));
                                    }
                                });
                                if (imageViews.size() == 5) {
                                    for (int i = 0; i < imageViews.size(); i++) {
                                        viewFlipper.addView(imageViews.get(i));
                                    }
                                    viewFlipper.startFlipping();
                                }
                            }catch (NullPointerException e){

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
        txtShizheng = view.findViewById(R.id.txt_shizheng);
        txtYiqing = view.findViewById(R.id.txt_yiqing);
        listview = view.findViewById(R.id.listview);
        txtYule = view.findViewById(R.id.txt_yule);
    }
}
