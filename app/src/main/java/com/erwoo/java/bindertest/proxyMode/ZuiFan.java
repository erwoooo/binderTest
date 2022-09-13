package com.erwoo.java.bindertest.proxyMode;
//定义一个类，叫罪犯，罪犯也是人
public class ZuiFan implements IPerson {
    @Override
    public void speak() {
        System.out.println("罪犯也有说话的权力");
    }
}
