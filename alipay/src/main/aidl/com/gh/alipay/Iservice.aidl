// Iservice.aidl
package com.gh.alipay;

// Declare any non-default types here with import statements

interface Iservice {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
//    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
//            double aDouble, String aString);
     boolean callPay(String userName, String passWord, int money);
}
