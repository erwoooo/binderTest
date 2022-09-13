package com.erwoo.java.bindertest.proxyMode;
//律师代理罪犯打官司，需要从罪犯口中知道案情的经过，律师也是人
public class LvShi implements IPerson{
    //需要持有一个罪犯的对象
    ZuiFan zuiFan;

    public LvShi(ZuiFan zuiFan) {
        this.zuiFan = zuiFan;
    }

    @Override
    public void speak() {
        System.out.println("律师说");
        zuiFan.speak();
        System.out.println("我话讲完，谁赞成，谁反对");
    }
}
