package com.example.x_smartcity_1.adapter;

import android.graphics.Bitmap;
import android.util.Log;
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
import com.example.x_smartcity_1.bean.GetNEWSContent;
import com.example.x_smartcity_1.fragment.zhuye.Fragment_zhuye_xinwen;
import com.example.x_smartcity_1.net.OkHttpLoImage;
import com.example.x_smartcity_1.net.OkHttpToImage;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/1/21  10:14
 */
public class ZHUYE_xinwen_adapter extends BaseAdapter {
    private List<GetNEWSContent> getNEWSContents;
    private FragmentActivity activity;
    private FragmentTransaction fragmentTransaction;

    public ZHUYE_xinwen_adapter(FragmentActivity activity, List<GetNEWSContent> getNEWSContents) {
        this.getNEWSContents = getNEWSContents;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return getNEWSContents.size();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_zhuye_xinwen, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Log.d("ddddddddddddd", "getView: "+position);
        final GetNEWSContent news = getNEWSContents.get(position);
        holder.xinwenTitle.setText(news.getTitle());
        holder.xinwenMsg.setText(news.getAbstractX());

        new OkHttpToImage()
                .setUrl(news.getPicture())
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
                getFragment(new Fragment_zhuye_xinwen(news,position+1));
            }
        });


        return convertView;
    }

    private void getFragment(Fragment fragment) {
        fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home,fragment).commit();

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
