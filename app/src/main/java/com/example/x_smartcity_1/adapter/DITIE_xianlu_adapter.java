package com.example.x_smartcity_1.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.x_smartcity_1.R;
import com.example.x_smartcity_1.bean.GetAllStationById;

import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/1/22  16:53
 */
public class DITIE_xianlu_adapter extends BaseAdapter {
    private String didian;
    private List<GetAllStationById> allStationByIds;

    public DITIE_xianlu_adapter(List<GetAllStationById> allStationByIds, String didian) {
        this.allStationByIds = allStationByIds;
        this.didian = didian;
    }

    @Override
    public int getCount() {
        return allStationByIds.size();
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_xianlu, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        GetAllStationById allStationById = allStationByIds.get(position);
        holder.txtDidian.setText(allStationById.getStationname());
        holder.txtTime.setText(allStationById.getStationIndex());
        holder.txtJvli.setText(allStationById.getDistance());
        if (didian.equals(allStationById.getStationname())){
            holder.txtDidian.setTextColor(Color.RED);
            holder.txtTime.setTextColor(Color.RED);
            holder.txtJvli.setTextColor(Color.RED);
        }

        return convertView;
    }

    class ViewHolder {
        private TextView txtDidian;
        private TextView txtTime;
        private TextView txtJvli;

        public ViewHolder(View view) {
            txtDidian = view.findViewById(R.id.txt_didian);
            txtTime = view.findViewById(R.id.txt_time);
            txtJvli = view.findViewById(R.id.txt_jvli);
        }
    }
}
