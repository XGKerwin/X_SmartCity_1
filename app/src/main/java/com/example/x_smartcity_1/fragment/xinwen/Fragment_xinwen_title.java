package com.example.x_smartcity_1.fragment.xinwen;

import android.content.Intent;
import android.net.Uri;
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
import androidx.fragment.app.FragmentTransaction;

import com.example.x_smartcity_1.R;
import com.example.x_smartcity_1.adapter.XINWEN_title_adapter;
import com.example.x_smartcity_1.bean.GetNEWsList;
import com.example.x_smartcity_1.net.OKHttpTo;
import com.example.x_smartcity_1.net.OkHttpLo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/1/22  0:50
 */
public class Fragment_xinwen_title extends Fragment {
    private ImageView imgBack;
    private TextView title;
    private ListView listview;
    private String string;
    private List<GetNEWsList> neWsLists,list;
    private FragmentTransaction fragmentTransaction;
    private XINWEN_title_adapter adapter;

    public Fragment_xinwen_title(String s) {
        this.string = s;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_xinwen_title, null);
        initView(view);
        title.setText(string);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_xinwen());
            }
        });

        getOkHttp();

        return view;
    }

    private void getFragment(Fragment fragment) {
        fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home,fragment).commit();
    }

    private void getOkHttp() {
        if (neWsLists == null){
            neWsLists = new ArrayList<>();
        }else {
            neWsLists.clear();
        }
        /**
         * {"keys":"新闻"}
         */
        new OKHttpTo()
                .setUrl("getNEWsList")
                .setJSONObject("keys","新闻")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        neWsLists.addAll((Collection<? extends GetNEWsList>) new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<GetNEWsList>>(){}.getType()));
                        setdata();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void setdata() {
        if (list == null){
            list = new ArrayList<>();
        }else {
            list.clear();
        }
        for (int i=0;i<neWsLists.size();i++){
            GetNEWsList getNEWsList = neWsLists.get(i);
            if (string.equals(getNEWsList.getNewsType())){
                list.add(getNEWsList);
            }
        }
        adapter = new XINWEN_title_adapter(list);
        listview.setAdapter(adapter);
        adapter.Myxinwen(new XINWEN_title_adapter.Myxinwen() {
            @Override
            public void setOnclick(String url) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

    }

    private void initView(View view) {
        imgBack = view.findViewById(R.id.img_back);
        title = view.findViewById(R.id.title);
        listview = view.findViewById(R.id.listview);
    }
}
