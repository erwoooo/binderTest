package com.erwoo.java.bindertest.proxyMode;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.erwoo.java.bindertest.R;

public class ProxyStaticActivity  extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //new 律师
        LvShi lvShi = new LvShi(new ZuiFan());  //律师一定要有罪犯，要有辩护人
        lvShi.speak();  //律师讲话
    }
}
