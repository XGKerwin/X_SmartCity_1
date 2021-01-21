package com.example.x_smartcity_1.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.x_smartcity_1.R;
import com.example.x_smartcity_1.bean.GetOrderById;
import com.example.x_smartcity_1.bean.GetUsers;

import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/1/20  11:56
 */
public class WODE_dingdan_adapter extends BaseAdapter {
    private List<GetOrderById> getOrderByIds;

    public WODE_dingdan_adapter(List<GetOrderById> getOrderByIds) {
        this.getOrderByIds = getOrderByIds;
    }

    @Override
    public int getCount() {
        if (getOrderByIds == null) return 0;
        return getOrderByIds.size();
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.wode_dingdan_adapter, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        GetOrderById orderById = getOrderByIds.get(position);
        holder.txtDidian.setText(orderById.getBusiness());
        holder.txtName.setText(orderById.getCommodity());
        holder.txtJiage.setText("价格"+orderById.getPrice());
        holder.txtShuliang.setText("数量"+orderById.getCount());

        return convertView;
    }


    class ViewHolder {
        private TextView txtDidian;
        private TextView txtName;
        private TextView txtShuliang;
        private TextView txtJiage;

        public ViewHolder(View view) {
            txtDidian = view.findViewById(R.id.txt_didian);
            txtName = view.findViewById(R.id.txt_name);
            txtShuliang = view.findViewById(R.id.txt_shuliang);
            txtJiage = view.findViewById(R.id.txt_jiage);
        }
    }
}
