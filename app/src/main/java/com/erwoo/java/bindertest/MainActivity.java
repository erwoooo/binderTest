package com.erwoo.java.bindertest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

//测试binder普通跨进程通信，客户端
public class MainActivity extends AppCompatActivity {
    private IBinder mRemoteBinder;  //远程服务的binder代理
    private static final int REQUEST_CODE = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //绑定
        findViewById(R.id.tv_bind).setOnClickListener(v -> {
            Log.e("MainActivity","客户端开始绑定service");
            bindServices();
        });

        //发送数据，等待回复
        findViewById(R.id.tv_send).setOnClickListener(v -> {
            sendData();
        });

    }

    //发送数据
    private void sendData() {
       String name = (int)(Math.random()*100) + " MainActivity";
       Log.e("MainActivity","客户端发送的数据是name： "+ name);
        //将请求数据打包成Parcel
        Parcel data = Parcel.obtain();  //请求数据
        Parcel reply = Parcel.obtain();  //回复数据
        data.writeString(name);     //写入数据
        int grade = -1;
        if (mRemoteBinder == null){
            Log.e("MainActivity","客户端没有连上服务端");
            return;
        }
        try {
            mRemoteBinder.transact(REQUEST_CODE,data,reply,0);  //和BpBinder的transact一样
            grade = reply.readInt();

            Log.e("MainActivity","客户端收到来自服务端的grade: " + grade);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void bindServices() {
        String action = "android.intent.action.server.binderService";  //和服务端service的action要一样
        Intent intent = new Intent(action);
        intent.setPackage("com.erwoo.java.mybinderservice");
        bindService(intent,serviceConnection,BIND_AUTO_CREATE);  //连接服务
    }


    private final ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //获取远程服务的binder
            Log.e("MainActivity","连上啦");
            mRemoteBinder = service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            //断开
            Log.e("MainActivity","又被断开啦");
            mRemoteBinder = null;
        }
    };

}