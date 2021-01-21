package com.example.x_smartcity_1.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.x_smartcity_1.R;
import com.example.x_smartcity_1.adapter.ZHUYE_xinwen_msg_adapter;
import com.example.x_smartcity_1.bean.GetCommitById;
import com.example.x_smartcity_1.bean.GetNEWSContent;
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

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/1/21  11:34
 */
public class Fragment_zhuye_xinwen extends Fragment {

    private GetNEWSContent getNEWSContent;
    private TextView xinwenTitle;
    private ImageView xinwenImg;
    private TextView xinwenMsg;
    private ListView listview;
    private int i;
    private List<GetCommitById> getCommitByIds;
    private ZHUYE_xinwen_msg_adapter adapter;

    public Fragment_zhuye_xinwen(GetNEWSContent news, int i) {
        this.getNEWSContent = news;
        this.i = i;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_zhuye_xinwen, null);
        initView(view);
        getCommitByIds = new ArrayList<>();

        getdata();
        getpinglun();


        return view;
    }

    private void getpinglun() {
        new OKHttpTo()
                .setUrl("getCommitById")
                .setJSONObject("id",i)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        getCommitByIds.addAll((Collection<? extends GetCommitById>) new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<GetCommitById>>(){}.getType()));
                        adapter = new ZHUYE_xinwen_msg_adapter(getCommitByIds);
                        listview.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void getdata() {
        xinwenTitle.setText(getNEWSContent.getTitle());
        xinwenMsg.setText(getNEWSContent.getAbstractX());
        new OkHttpToImage()
                .setUrl(getNEWSContent.getPicture())
                .setOkHttpLoImage(new OkHttpLoImage() {
                    @Override
                    public void onResponse(Call call, Bitmap bitmap) {
                        xinwenImg.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onFailure(Call call, IOException e) {

                    }
                }).start();
    }


    private void initView(View view) {
        xinwenTitle = view.findViewById(R.id.xinwen_title);
        xinwenImg = view.findViewById(R.id.xinwen_img);
        xinwenMsg = view.findViewById(R.id.xinwen_msg);
        listview = view.findViewById(R.id.listview);
    }
}
