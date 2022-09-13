package com.erwoo.java.bindertest.proxy;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

//客户端binder代理，要实现IGradeInterface接口
public class BinderProxy implements IGradeInterface{
    public static final int REQUEST_CODE = 1001;
    private IBinder mBinder;  //被代理的binder,也就是说，代理远程的binder?

    private BinderProxy(IBinder mBinder) {   //私有化构造函数，也就是不能new
        this.mBinder = mBinder;
    }

    @Override
    public int getMyGrade(String name) {
        Parcel data = Parcel.obtain();   //要发送给服务端的数据
        data.writeString(name);
        Parcel reply = Parcel.obtain();    //服务端回复
        Log.e("BinderProxy","客户端发送的name: " + name);
        int grade = -1;   //默认值
        if (mBinder == null){
            Log.e("BinderProxy","没有连上服务端");
            return -1;
        }
        try {
            mBinder.transact(REQUEST_CODE,data,reply,0); //传输
            grade = reply.readInt();    //得到服务端的回复

        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return grade;
    }

    //实例化binder代理对象
    //服务端的binder使用的是IGradeInterface，继承了binder，如果是IGradeInterface，那就是服务端的binder
    //本进程的binder,并没有继承IGradeInterface，所以是将本进程的binder包装成IGradeInterface？
    public static IGradeInterface asInterface(IBinder iBinder){
        if (iBinder == null){
            return null;
        }
        if (iBinder instanceof IGradeInterface){
            Log.e("IGradeInterface","当前进程");
            return (IGradeInterface) iBinder;
        }else {
            Log.e("IGradeInterface","跨进程");
            return new BinderProxy(iBinder);
        }

    }
}
