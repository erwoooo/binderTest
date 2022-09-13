package com.erwoo.java.mybinderservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

//binder服务端,继承service
public class MyBinderService extends Service {
    public static final int REQUEST_CODE = 1000;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private Binder mBinder = new Binder(){

        //服务端的传输，这里和binder.c里面一样，onTransact,客户端请求是transact
        @Override
        protected boolean onTransact(int code, @NonNull Parcel data, @Nullable Parcel reply, int flags) throws RemoteException {
            if (code == REQUEST_CODE){
                String name = data.readString();

                Log.e("MyBinderService","接收到的数据 name: "  + name);
                int response = (int) (Math.random() * 100);

                Log.e("MyBinderService","随机生成的数据 response: "  + response + " 对应name: " + name);
                if (reply != null){  //这里的reply是来自客户端transact的，发过来的时候有data、reply两个parcel
                    reply.writeInt(response);
                }
                return true;
            }

            return super.onTransact(code, data, reply, flags);   //和ServiceManager里面回复client端的onTransact一样
        }

    };
}
/**
 * 服务端的代码就写好了，就是收到一个name，然后对应随机生成一个int数返回给客户端
 * 下一步将service注册到Manifest
 */