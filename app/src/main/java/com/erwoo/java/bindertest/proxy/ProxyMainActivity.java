package com.erwoo.java.bindertest.proxy;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.erwoo.java.bindertest.R;
//代理binder
public class ProxyMainActivity  extends AppCompatActivity {
    private IGradeInterface mBinderProxy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //绑定
        findViewById(R.id.tv_bind).setOnClickListener(v -> {
            Log.e("ProxyMainActivity","客户端开始绑定service");
            bindServices();
        });

        //发送数据，等待回复
        findViewById(R.id.tv_send).setOnClickListener(v -> {
            sendData();
        });

    }

    private void sendData() {
        String name = (int)(Math.random() * 100) + " proxy";
        int response = mBinderProxy.getMyGrade(name);
        Log.e("ProxyMainActivity","接收到回复的数据response: " + response);
    }

    private void bindServices() {
        String action = "android.intent.action.service.GradeService";
        Intent intent = new Intent(action);
        intent.setPackage("com.erwoo.java.mybinderservice"); //显式
        bindService(intent,serviceConnection,BIND_AUTO_CREATE);
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //连接进程成功，根据是否跨进程获取BinderProxy或者GradeBinder
            Log.e("ProxyMainActivity","连上服务端");
            mBinderProxy = BinderProxy.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e("ProxyMainActivity","断开啦");
            mBinderProxy = null;
        }
    };
}
