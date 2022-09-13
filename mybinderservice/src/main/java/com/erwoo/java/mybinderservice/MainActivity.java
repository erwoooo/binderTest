package com.erwoo.java.mybinderservice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
//binder服务端，在这边只写一个service，开启服务，等待客户端的binder连接
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}