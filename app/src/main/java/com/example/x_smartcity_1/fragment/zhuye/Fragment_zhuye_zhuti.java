package com.example.x_smartcity_1.fragment.zhuye;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.x_smartcity_1.R;
import com.example.x_smartcity_1.adapter.ZHUYE_zhuti_xinwen_adapter;
import com.example.x_smartcity_1.bean.GetNEWSContent;
import com.example.x_smartcity_1.bean.GetNewsInfoBySubject;
import com.example.x_smartcity_1.net.OKHttpTo;
import com.example.x_smartcity_1.net.OkHttpLo;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/1/21  15:37
 */
public class Fragment_zhuye_zhuti extends Fragment {

    private List<GetNewsInfoBySubject> subjects;
    private List<GetNEWSContent> getNEWSContents;
    private int ss;
    private ZHUYE_zhuti_xinwen_adapter adapter;

    private List<String> strings;
    private TextView ABiaoti;
    private ListView listview;
    private String string;

    public Fragment_zhuye_zhuti(List<GetNewsInfoBySubject> getNewsInfoBySubjects, String s) {
        this.subjects = getNewsInfoBySubjects;
        this.string = s;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_zhuye_zhuti, null);
        initView(view);

        ABiaoti.setText(string);

        strings = new ArrayList<>();
        getNEWSContents = new ArrayList<>();

        ss = subjects.size();
        for (int i = 0; i < subjects.size(); i++) {
            GetNewsInfoBySubject subject = subjects.get(i);
            strings.add(subject.getNewsid());
        }

        for (int i = 0; i < strings.size(); i++) {
            String s = strings.get(i);
            new OKHttpTo()
                    .setUrl("getNEWSContent")
                    .setJSONObject("newsid", s)
                    .setOkHttpLo(new OkHttpLo() {
                        @Override
                        public void onResponse(JSONObject jsonObject) {
                            GetNEWSContent getNEWSContent = new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").optJSONObject(0).toString(),
                                    GetNEWSContent.class);
                            getNEWSContents.add(getNEWSContent);
                            if (getNEWSContents.size() == ss) {
                                adapter = new ZHUYE_zhuti_xinwen_adapter(getNEWSContents,getContext());
                                listview.setAdapter(adapter);

                                adapter.MyOnclick(new ZHUYE_zhuti_xinwen_adapter.MyOnclick() {
                                    @Override
                                    public void setOnclick(String url) {
                                        Intent intent = new Intent(Intent.ACTION_VIEW);
                                        intent.setData(Uri.parse(url));
                                        startActivity(intent);
                                    }
                                });

                            }
                        }

                        @Override
                        public void onFailure(IOException obj) {

                        }
                    }).start();
        }


        return view;
    }

    private void initView(View view) {
        ABiaoti = view.findViewById(R.id.A_biaoti);
        listview = view.findViewById(R.id.listview);
    }
}
