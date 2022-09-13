package com.erwoo.java.mybinderservice.proxy;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

//代理优化binder
public class GradeService extends Service {
    public static final int REQUEST_CODE = 1001;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new GradeBinder();   //新建一个binder对象，也是一个接口
    }
}
