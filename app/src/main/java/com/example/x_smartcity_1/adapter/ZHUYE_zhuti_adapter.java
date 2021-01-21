package com.example.x_smartcity_1.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import androidx.fragment.app.FragmentActivity;

import com.example.x_smartcity_1.R;

import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/1/21  9:42
 */
public class ZHUYE_zhuti_adapter extends BaseAdapter {
    private List<String> strings;
    private FragmentActivity activity;

    public ZHUYE_zhuti_adapter(FragmentActivity activity, List<String> strings) {
        this.activity = activity;
        this.strings = strings;
    }

    @Override
    public int getCount() {
        if (strings == null) return 0;
        return strings.size();
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
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_zhuye_zhuti, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String s = strings.get(position);
        switch (s) {
            case "电影":
                holder.zhuyeZhutiImg.setImageResource(R.drawable.zhuti_dianying);
                break;
            case "国庆专题":
                holder.zhuyeZhutiImg.setImageResource(R.drawable.zhuti_guoqing);
                break;
            case "抗肺炎":
                holder.zhuyeZhutiImg.setImageResource(R.drawable.zhuti_kangfeiyan);
                break;
            case "烈士纪念日":
                holder.zhuyeZhutiImg.setImageResource(R.drawable.zhuti_lieshi);
                break;
        }


        return convertView;
    }



    class ViewHolder {
        private ImageView zhuyeZhutiImg;

        public ViewHolder(View view) {
            zhuyeZhutiImg = view.findViewById(R.id.zhuye_zhuti_img);
        }
    }
}
