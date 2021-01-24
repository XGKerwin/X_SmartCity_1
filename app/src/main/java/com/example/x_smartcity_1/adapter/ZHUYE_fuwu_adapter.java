package com.example.x_smartcity_1.adapter;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.x_smartcity_1.R;
import com.example.x_smartcity_1.bean.Service_info;
import com.example.x_smartcity_1.fragment.ditie.Fragment_zhuye_ditie;
import com.example.x_smartcity_1.fragment.fuwu.Fragment_fuwu;
import com.example.x_smartcity_1.net.OkHttpLoImage;
import com.example.x_smartcity_1.net.OkHttpToImage;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/1/20  21:31
 */
public class ZHUYE_fuwu_adapter extends BaseAdapter {
    private List<Service_info> serviceInfos;
    private FragmentTransaction fragmentTransaction;
    private FragmentActivity activity;

    public ZHUYE_fuwu_adapter(List<Service_info> service_infos, FragmentActivity activity) {
        this.activity = activity;
        this.serviceInfos = service_infos;
    }

    @Override
    public int getCount() {
        return 10;
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
    public View getView(final int position, View convertView, final ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_zhuye_fuwu, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (position == 9){
            holder.adapterZhuyeWodeImg.setImageResource(R.drawable.fuwu_gengduo);
            holder.adapterZhuyeWodeTxt.setText("更多服务");
        }else {
            Service_info service_info = serviceInfos.get(position);
            holder.adapterZhuyeWodeTxt.setText(service_info.getServiceName());
            new OkHttpToImage()
                    .setUrl(service_info.getIcon())
                    .setOkHttpLoImage(new OkHttpLoImage() {
                        @Override
                        public void onResponse(Call call, Bitmap bitmap) {
                            holder.adapterZhuyeWodeImg.setImageBitmap(bitmap);
                        }

                        @Override
                        public void onFailure(Call call, IOException e) {

                        }
                    }).start();
        }

        holder.adapter_zhuye_wode_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = holder.adapterZhuyeWodeTxt.getText().toString();
                if (s.equals("地铁查询")){
                    getFragment(new Fragment_zhuye_ditie());
                }else if (s.equals("更多服务")){
                    getFragment(new Fragment_fuwu());
                }else {
                    return;
                }
            }
        });

        return convertView;
    }

    private void getFragment(Fragment fragment) {
        fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home,fragment).commit();
    }


    class ViewHolder {
        private LinearLayout adapter_zhuye_wode_layout;
        private ImageView adapterZhuyeWodeImg;
        private TextView adapterZhuyeWodeTxt;

        public ViewHolder(View view) {
            adapter_zhuye_wode_layout = view.findViewById(R.id.adapter_zhuye_wode_layout);
            adapterZhuyeWodeImg = view.findViewById(R.id.adapter_zhuye_wode_img);
            adapterZhuyeWodeTxt = view.findViewById(R.id.adapter_zhuye_wode_txt);
        }
    }
}
