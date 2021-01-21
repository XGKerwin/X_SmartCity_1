package com.example.x_smartcity_1;

import android.app.Application;

import org.litepal.LitePal;

public class App extends Application {

    public static String userida;

    public static String getUserida() {
        return userida;
    }

    public static void setUserida(String userida) {
        App.userida = userida;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
    }
}
