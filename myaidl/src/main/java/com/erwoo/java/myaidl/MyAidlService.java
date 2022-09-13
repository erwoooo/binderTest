package com.erwoo.java.myaidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyAidlService extends Service {

    public MyAidlService() {
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }

    private IBinder iBinder = new IGradeService.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public int getGradeByName(String name) throws RemoteException {
            Log.e("MyAidlService","我接收到客户端的name: " + name);
            int response = (int)(Math.random() * 100);
            Log.e("MyAidlService","我生成的response: " + response);
            return response;
        }
    };
}
