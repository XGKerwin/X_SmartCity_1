package com.example.x_smartcity_1.fragment.wode;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.x_smartcity_1.Activity.Activity_geren_user;
import com.example.x_smartcity_1.App;
import com.example.x_smartcity_1.R;
import com.example.x_smartcity_1.bean.GetUserInfo;
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

public class Fragment_wode extends Fragment {
    private TextView textName;
    private ImageView imgTouxiang;
    private LinearLayout layoutGerenzhongxin;
    private LinearLayout layoutDingdanliebiao;
    private LinearLayout layoutMimaxiugai;
    private LinearLayout layoutYijianfankui;
    private LinearLayout layoutTuichudenglu;
    private FragmentTransaction fragmentTransaction;
    private String user1;
    private List<GetUserInfo> getUserInfos;
    private String img_user = "";

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

//        if (requestCode==1){
//            if (resultCode==getActivity().RESULT_OK){
//                Toast.makeText(getActivity(), "aaa", Toast.LENGTH_SHORT).show();
//            }
//        }

        user1 = App.getUserida();

        if (user1 == null){

        }else {
            getOkhttp(user1);
        }

    }


    private void getOkhttp(String userid1) {
        if (userid1 == null){
            textName.setText("注册/登录");
        }
        if (getUserInfos == null){
            getUserInfos = new ArrayList<>();
        }else {
            getUserInfos.clear();
        }
        new OKHttpTo()
                .setUrl("getUserInfo")
                .setJSONObject("userid",userid1)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        getUserInfos.addAll((Collection<? extends GetUserInfo>) new Gson().fromJson(jsonObject.optString("ROWS_DETAIL").toString(),
                                new TypeToken<List<GetUserInfo>>(){}.getType()));
                        textName.setText(getUserInfos.get(0).getName());
                        img_user = getUserInfos.get(0).getAvatar();
                        getOkhttpimg();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void getOkhttpimg() {
        new OkHttpToImage()
                .setUrl(img_user)
                .setOkHttpLoImage(new OkHttpLoImage() {
                    @Override
                    public void onResponse(Call call, Bitmap bitmap) {
                        imgTouxiang.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onFailure(Call call, IOException e) {

                    }
                }).start();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_wode, null);
        initView(view);

        user1 = App.getUserida();

        if (user1 == null){

        }else {
            getOkhttp(user1);
        }


        btn();


        getuser();

        return view;
    }

    private void getuser() {
        textName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Activity_geren_user.class);
                startActivityForResult(intent,1);
            }
        });


    }


    private void btn() {
        layoutGerenzhongxin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user1 == null){
                    Toast.makeText(getContext(),"请登录",Toast.LENGTH_SHORT).show();
                }else {
                    getFragment(new Fragment_wode_geren());
                }
            }
        });

        layoutDingdanliebiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user1 == null){
                    Toast.makeText(getContext(),"请登录",Toast.LENGTH_SHORT).show();
                }else {
                    getFragment(new Fragment_wode_dingdan());
                }
            }
        });

        layoutMimaxiugai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user1 == null){
                    Toast.makeText(getContext(),"请登录",Toast.LENGTH_SHORT).show();
                }else {
                    /**
                     * {userid:"1",password:"123"}
                     */
                    getDialog();
                }
            }
        });

        layoutYijianfankui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user1 == null){
                    Toast.makeText(getContext(),"请登录",Toast.LENGTH_SHORT).show();
                }else {
                    getyijiandia();
                }


            }
        });

        layoutTuichudenglu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user1 == null){
                    Toast.makeText(getContext(),"请登录",Toast.LENGTH_SHORT).show();
                }else {
                    App.setUserida(null);
                    user1 = App.getUserida();
                    Toast.makeText(getContext(),"退出成功",Toast.LENGTH_SHORT).show();
                    getOkhttp(user1);
                }
            }
        });

    }

    private void getyijiandia() {
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
                .setJSONObject("userid",user1)
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

    private void getDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_mima,null);
        builder.setView(view);
        builder.setCancelable(false);
        final EditText edpass1 = view.findViewById(R.id.ed_pass1);
        final EditText edpass2 = view.findViewById(R.id.ed_pass2);
        TextView queding = view.findViewById(R.id.txt_queding);
        TextView quxiao = view.findViewById(R.id.txt_quxiao);
        final Dialog dialog = builder.show();

        queding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass1 = edpass1.getText().toString();
                String pass2 = edpass2.getText().toString();
                if (pass1.equals("")){
                    Toast.makeText(getContext(),"请输入密码",Toast.LENGTH_SHORT).show();
                }else if (pass2.equals("")){
                    Toast.makeText(getContext(),"请输入密码",Toast.LENGTH_SHORT).show();
                }else if (pass1.equals(pass2)){
                    setPwd(pass1);
                    dialog.dismiss();
                }else {
                    Toast.makeText(getContext(),"两次密码输入的不一致",Toast.LENGTH_SHORT).show();
                }
            }
        });

        quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

    private void setPwd(String pass1) {
        /**
         * {userid:"1",password:"123"}
         */
        new OKHttpTo()
                .setUrl("setPwd")
                .setJSONObject("userid",user1)
                .setJSONObject("password",pass1)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Toast.makeText(getContext(),"设置成功",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(IOException obj) {
                        Toast.makeText(getContext(),"修改失败",Toast.LENGTH_SHORT).show();
                    }
                }).start();
    }


    private void getFragment(Fragment fragment) {
        fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home,fragment).commit();
    }

    private void initView(View view) {
        textName = view.findViewById(R.id.text_name);
        imgTouxiang = view.findViewById(R.id.img_touxiang);
        layoutGerenzhongxin = view.findViewById(R.id.layout_gerenzhongxin);
        layoutDingdanliebiao = view.findViewById(R.id.layout_dingdanliebiao);
        layoutMimaxiugai = view.findViewById(R.id.layout_mimaxiugai);
        layoutYijianfankui = view.findViewById(R.id.layout_yijianfankui);
        layoutTuichudenglu = view.findViewById(R.id.layout_tuichudenglu);
    }
}
