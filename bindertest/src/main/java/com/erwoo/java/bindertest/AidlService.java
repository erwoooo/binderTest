package com.erwoo.java.bindertest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

//第一步，新建一个类，继承service,并配置到manifest
public class AidlService  extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {  //客户端绑定到服务端后执行
        return iBinder;
    }

    private IBinder iBinder = new IGradeService.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public int getGradeByName(String name) throws RemoteException {
            Log.e("AidlService","收到远程的name：" + name);
            int response = (int) (Math.random() * 100);
            Log.e("AidlService","服务端返回给客户端的response: " + response);
            return response;
        }
    };
}
