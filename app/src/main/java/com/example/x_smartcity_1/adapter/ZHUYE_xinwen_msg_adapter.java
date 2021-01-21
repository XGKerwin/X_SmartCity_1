package com.example.x_smartcity_1.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.x_smartcity_1.R;
import com.example.x_smartcity_1.bean.GetCommitById;

import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/1/21  15:02
 */
public class ZHUYE_xinwen_msg_adapter extends BaseAdapter {
    private List<GetCommitById> commitByIds;

    public ZHUYE_xinwen_msg_adapter(List<GetCommitById> getCommitByIds) {
        this.commitByIds = getCommitByIds;
    }

    @Override
    public int getCount() {
        return commitByIds.size();
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_xinwen, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        GetCommitById byId = commitByIds.get(position);
        holder.xinwenTitle.setText(byId.getReviewer());
        holder.xinwenMag.setText(byId.getCommit());
        holder.xinwenTime.setText(byId.getCommitTime());


        return convertView;
    }


    class ViewHolder {
        private TextView xinwenTitle;
        private TextView xinwenMag;
        private TextView xinwenTime;

        public ViewHolder(View view) {
            xinwenTitle = view.findViewById(R.id.xinwen_title);
            xinwenMag = view.findViewById(R.id.xinwen_mag);
            xinwenTime = view.findViewById(R.id.xinwen_time);
        }
    }

}
