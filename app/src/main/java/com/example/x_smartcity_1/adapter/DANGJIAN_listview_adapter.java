package com.example.x_smartcity_1.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.x_smartcity_1.R;
import com.example.x_smartcity_1.bean.GetNEWsList;

import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/1/21  20:30
 */
public class DANGJIAN_listview_adapter extends BaseAdapter {
    private List<GetNEWsList> neWsLists;
    public MyOnclickdangjian myOnclickdangjian;
    
    public interface MyOnclickdangjian{

        void setOnclick(String url);
    }
    public void MyOnclickdangjian(MyOnclickdangjian myOnclickdangjian){
        this.myOnclickdangjian = myOnclickdangjian;
    }

    public DANGJIAN_listview_adapter(List<GetNEWsList> neWsLists) {
        this.neWsLists = neWsLists;

    }

    @Override
    public int getCount() {
        return neWsLists.size();
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_dangjian, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final GetNEWsList neWsList = neWsLists.get(position);
        holder.dangjianTitle.setText(neWsList.getTitle());
        holder.dangjianLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myOnclickdangjian.setOnclick(neWsList.getUrl());
            }
        });

        return convertView;
    }


    class ViewHolder {
        private LinearLayout dangjianLayout;
        private TextView dangjianTitle;

        public ViewHolder(View view) {
            dangjianLayout = view.findViewById(R.id.dangjian_layout);
            dangjianTitle = view.findViewById(R.id.dangjian_title);
        }
    }
}
