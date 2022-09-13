package com.erwoo.java.bindertest.aidl;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.erwoo.java.bindertest.R;
import com.erwoo.java.myaidl.IGradeService;

public class AidlActivity extends AppCompatActivity {
    private IGradeService stub;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //绑定
        findViewById(R.id.tv_bind).setOnClickListener(v -> {
            Log.e("AidlActivity","客户端开始绑定service");
            bindServices();
        });

        //发送数据，等待回复
        findViewById(R.id.tv_send).setOnClickListener(v -> {
            sendData();
        });

    }

    private void sendData() {
        String name = (int)(Math.random()*100) + "发";
        Log.e("AidlActivity","我发送给服务端的数据name: " + name);
        if (stub == null){
            Log.e("AidlActivity","没有连上服务端");
            return;
        }
        try {
            int response = stub.getGradeByName(name);
            Log.e("AidlActivity","我收到服务端的回复response: " + response);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void bindServices() {
        Intent intent = new Intent("android.intent.action.service.aidl");
        intent.setPackage("com.erwoo.java.myaidl");
        intent.setComponent(new ComponentName("com.erwoo.java.myaidl","com.erwoo.java.myaidl.MyAidlService"));
        bindService(intent,serviceConnection,BIND_AUTO_CREATE);
    }


    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e("AidlActivity","连上服务端了");
            stub = IGradeService.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e("AidlActivity","断开了");
            stub = null;
        }
    };
}
