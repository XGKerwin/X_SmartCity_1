package com.example.x_smartcity_1.adapter;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.x_smartcity_1.R;
import com.example.x_smartcity_1.bean.GetNEWsList;
import com.example.x_smartcity_1.net.OkHttpLoImage;
import com.example.x_smartcity_1.net.OkHttpToImage;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/1/22  1:04
 */
public class XINWEN_title_adapter extends BaseAdapter {
    private List<GetNEWsList> list;
    public Myxinwen myxinwen;

    public interface Myxinwen{

        void setOnclick(String url);
    }

    public void Myxinwen(Myxinwen myxinwen){
        this.myxinwen = myxinwen;
    }

    public XINWEN_title_adapter(List<GetNEWsList> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_xinwen_title, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final GetNEWsList neWsList = list.get(position);
        holder.xinwenTitle.setText(neWsList.getTitle());
        holder.xinwenMsg.setText(neWsList.getAbstractX());

        new OkHttpToImage()
                .setUrl(neWsList.getPicture())
                .setOkHttpLoImage(new OkHttpLoImage() {
                    @Override
                    public void onResponse(Call call, Bitmap bitmap) {
                        holder.xinwenImg.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onFailure(Call call, IOException e) {

                    }
                }).start();

        holder.xinwenLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myxinwen.setOnclick(neWsList.getUrl());
            }
        });

        return convertView;
    }


    class ViewHolder {
        private LinearLayout xinwenLayout;
        private ImageView xinwenImg;
        private TextView xinwenTitle;
        private TextView xinwenMsg;

        public ViewHolder(View view) {
            xinwenLayout = view.findViewById(R.id.xinwen_layout);
            xinwenImg = view.findViewById(R.id.xinwen_img);
            xinwenTitle = view.findViewById(R.id.xinwen_title);
            xinwenMsg = view.findViewById(R.id.xinwen_msg);
        }
    }
}
