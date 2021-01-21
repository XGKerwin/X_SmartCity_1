package com.example.x_smartcity_1.net;

import android.graphics.Bitmap;

import java.io.IOException;

import okhttp3.Call;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/1/19  13:23
 */
public interface OkHttpLoImage {
    void onResponse(Call call, Bitmap bitmap);
    void onFailure(Call call, IOException e);
}
