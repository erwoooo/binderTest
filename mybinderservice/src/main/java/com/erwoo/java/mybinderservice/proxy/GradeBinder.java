package com.erwoo.java.mybinderservice.proxy;

import android.os.Binder;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

//代理模式优化
//继承binder,并实现根据name返回对应一个int数
public class GradeBinder extends Binder implements IGradeInterface {
    public static final int REQUEST_CODE = 1001;
    @Override
    public int getMyGrade(String name) {
        int response = (int) (Math.random() * 100);
        return response;  //返回的值
    }

    //binder要实现传输功能
    @Override
    protected boolean onTransact(int code, @NonNull Parcel data, @Nullable Parcel reply, int flags) throws RemoteException {
        if (code == REQUEST_CODE){
            String name = data.readString();
            Log.e("GradeBinder","onTransact接收到的name: " +name);
            int response = getMyGrade(name);  //随机生成的返回值
            Log.e("GradeBinder","随机生成的返回值response: " + response);
            if (reply != null)
                reply.writeInt(response);
            return true;
        }
        return super.onTransact(code, data, reply, flags);
    }
}
