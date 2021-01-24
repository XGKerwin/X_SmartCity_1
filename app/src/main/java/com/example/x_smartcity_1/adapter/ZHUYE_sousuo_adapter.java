package com.example.x_smartcity_1.adapter;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.x_smartcity_1.R;
import com.example.x_smartcity_1.bean.GetNewsByKeys;
import com.example.x_smartcity_1.net.OkHttpLoImage;
import com.example.x_smartcity_1.net.OkHttpToImage;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/1/24  8:56
 */
public class ZHUYE_sousuo_adapter extends BaseAdapter {
    private List<GetNewsByKeys> getNewsByKeys;

    public ZHUYE_sousuo_adapter(List<GetNewsByKeys> getNewsByKeys) {
        this.getNewsByKeys = getNewsByKeys;
    }

    @Override
    public int getCount() {
        if (getNewsByKeys == null) return 0;
        return getNewsByKeys.size();
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_sousuo, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        GetNewsByKeys newsByKeys = getNewsByKeys.get(position);
        holder.txt.setText(newsByKeys.getTitle());
        holder.txt2.setText(newsByKeys.getAbstractX());

        new OkHttpToImage()
                .setUrl(newsByKeys.getPicture())
                .setOkHttpLoImage(new OkHttpLoImage() {
                    @Override
                    public void onResponse(Call call, Bitmap bitmap) {
                        holder.img.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onFailure(Call call, IOException e) {

                    }
                }).start();

        return convertView;
    }

    class ViewHolder {
        private ImageView img;
        private TextView txt;
        private TextView txt2;

        public ViewHolder(View view) {
            img = view.findViewById(R.id.img);
            txt = view.findViewById(R.id.txt);
            txt2 = view.findViewById(R.id.txt2);
        }
    }
}
