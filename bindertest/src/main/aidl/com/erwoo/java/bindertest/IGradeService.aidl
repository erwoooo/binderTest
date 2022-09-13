// IGradeService.aidl
package com.erwoo.java.bindertest;

// Declare any non-default types here with import statements

interface IGradeService {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

            //根据名称返回一个int值
     int getGradeByName(String name);
}