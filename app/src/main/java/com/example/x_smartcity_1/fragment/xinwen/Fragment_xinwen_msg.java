package com.example.x_smartcity_1.fragment.xinwen;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.x_smartcity_1.App;
import com.example.x_smartcity_1.R;
import com.example.x_smartcity_1.adapter.XINWEN_adapterpinglun;
import com.example.x_smartcity_1.bean.GetCommitById;
import com.example.x_smartcity_1.bean.GetNEWsList;
import com.example.x_smartcity_1.fragment.xinwen.Fragment_xinwen;
import com.example.x_smartcity_1.net.OKHttpTo;
import com.example.x_smartcity_1.net.OkHttpLo;
import com.example.x_smartcity_1.net.OkHttpLoImage;
import com.example.x_smartcity_1.net.OkHttpToImage;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import okhttp3.Call;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/1/22  0:04
 */
public class Fragment_xinwen_msg extends Fragment {
    private TextView xinwenTitle;
    private ImageView xinwenImg;
    private TextView xinwenMsg;
    private ListView listview;
    private GetNEWsList neWsList;
    private ImageView back;
    private FragmentTransaction fragmentTransaction;
    private TextView btnXiepinglun;
    private String user1;
    private List<GetCommitById> commitByIds;
    private XINWEN_adapterpinglun adapterpinglun;

    public Fragment_xinwen_msg(GetNEWsList neWsList) {
        this.neWsList = neWsList;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_xinwen_msg, null);
        initView(view);
        user1 = App.getUserida();
        if (user1 == null){
            Toast.makeText(getContext(),"登录才可查看评论",Toast.LENGTH_SHORT).show();
        }

        getdata();
        getpinglun();


        return view;
    }

    private void getpinglun() {
        if (commitByIds == null){
            commitByIds = new ArrayList<>();
        }else {
            commitByIds.clear();
        }
        new OKHttpTo()
                .setUrl("getCommitById")
                .setJSONObject("id",user1)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        commitByIds.addAll((Collection<? extends GetCommitById>) new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<GetCommitById>>(){}.getType()));
                        adapterpinglun = new XINWEN_adapterpinglun(commitByIds);
                        listview.setAdapter(adapterpinglun);
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void getdata() {
        btnXiepinglun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user1 == null){
                    Toast.makeText(getContext(),"请登录",Toast.LENGTH_SHORT).show();
                }else {
                    getdia();
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_xinwen());
            }
        });
        xinwenTitle.setText(neWsList.getTitle());
        xinwenMsg.setText(neWsList.getAbstractX());
        new OkHttpToImage()
                .setUrl(neWsList.getPicture())
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

    private void getdia() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_yijian,null);
        builder.setView(view);
        builder.setCancelable(false);
        TextView fanhui = view.findViewById(R.id.dia_yijian_quxiao);
        TextView queding = view.findViewById(R.id.dia_yijian_queding);
        final EditText editText = view.findViewById(R.id.dia_yijian_edit);

        final Dialog dialog = builder.show();

        queding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String neirong = editText.getText().toString();
                if (neirong.equals("")){
                    Toast.makeText(getContext(),"请输入您的意见",Toast.LENGTH_SHORT).show();
                }else {
                    getpublicOpinion(neirong,dialog);
                }
            }
        });

        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    private void getpublicOpinion(String neirong, final Dialog dialog) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String time = simpleDateFormat.format(date);
        /**
         * {"userid":"1","content":"内容","time":"yyyy.MM.dd HH:mm:ss"}
         */
        new OKHttpTo()
                .setUrl("publicOpinion")
                .setJSONObject("userid",1)
                .setJSONObject("content",neirong)
                .setJSONObject("time",time)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Toast.makeText(getContext(),"提交成功",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();

    }

    private void getFragment(Fragment fragment) {
        fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home, fragment).commit();
    }

    private void initView(View view) {
        xinwenTitle = view.findViewById(R.id.xinwen_title);
        xinwenImg = view.findViewById(R.id.xinwen_img);
        xinwenMsg = view.findViewById(R.id.xinwen_msg);
        listview = view.findViewById(R.id.listview);
        back = view.findViewById(R.id.back);
        btnXiepinglun = view.findViewById(R.id.btn_xiepinglun);
    }
}
