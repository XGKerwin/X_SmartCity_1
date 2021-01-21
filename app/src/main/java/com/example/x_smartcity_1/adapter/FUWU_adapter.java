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
import com.example.x_smartcity_1.bean.GetServiceByType;
import com.example.x_smartcity_1.net.OkHttpLoImage;
import com.example.x_smartcity_1.net.OkHttpToImage;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/1/21  18:05
 */
public class FUWU_adapter extends BaseAdapter {
    private List<GetServiceByType> getServiceByTypes;

    public FUWU_adapter(List<GetServiceByType> getServiceByType) {
        this.getServiceByTypes = getServiceByType;
    }

    @Override
    public int getCount() {
        return getServiceByTypes.size();
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_fuwu, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        GetServiceByType getServiceByType = getServiceByTypes.get(position);
        holder.fuwuTxt.setText(getServiceByType.getServiceName());
        new OkHttpToImage()
                .setUrl(getServiceByType.getIcon())
                .setOkHttpLoImage(new OkHttpLoImage() {
                    @Override
                    public void onResponse(Call call, Bitmap bitmap) {
                        holder.fuwuImg.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onFailure(Call call, IOException e) {

                    }
                }).start();

        holder.fuwuLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return convertView;
    }


    class ViewHolder {
        private LinearLayout fuwuLayout;
        private ImageView fuwuImg;
        private TextView fuwuTxt;

        public ViewHolder(View view) {
            fuwuLayout = view.findViewById(R.id.fuwu_layout);
            fuwuImg = view.findViewById(R.id.fuwu_img);
            fuwuTxt = view.findViewById(R.id.fuwu_txt);
        }
    }
}
