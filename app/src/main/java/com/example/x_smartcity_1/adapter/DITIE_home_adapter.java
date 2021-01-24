package com.example.x_smartcity_1.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.x_smartcity_1.R;
import com.example.x_smartcity_1.bean.GetSubwaysByStation;

import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/1/22  16:08
 */
public class DITIE_home_adapter extends BaseAdapter {
    private List<GetSubwaysByStation> subwaysByStations;
    private FragmentActivity activity;
    private String didian;

    public DITIE_home_adapter(List<GetSubwaysByStation> subwaysByStations, FragmentActivity activity, String s_didian) {
        this.subwaysByStations = subwaysByStations;
        this.activity = activity;
        this.didian = s_didian;
    }

    @Override
    public int getCount() {
        if (subwaysByStations == null) return 0;
        return subwaysByStations.size();
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_ditie_hone, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final GetSubwaysByStation byStation = subwaysByStations.get(position);
        holder.txtDitie.setText(byStation.getName());
        holder.txtDidian.setText(byStation.getNextname());
        holder.txtTime.setText(byStation.getTime());

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment1(new Fragment_ditie_xianlu(byStation.getSubwayid(),didian));
            }
        });

        return convertView;
    }

    private void getFragment1(Fragment fragment) {
        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home,fragment).commit();
    }


    class ViewHolder {
        private TextView txtDitie;
        private TextView txtDidian;
        private TextView txtTime;
        private LinearLayout layout;

        public ViewHolder(View view) {
            layout = view.findViewById(R.id.layout);
            txtDitie = view.findViewById(R.id.txt_ditie);
            txtDidian = view.findViewById(R.id.txt_didian);
            txtTime = view.findViewById(R.id.txt_time);
        }
    }
}
