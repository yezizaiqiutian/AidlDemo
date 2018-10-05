package com.gh.alipay;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class PayService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
//        return null;
    }

    public boolean pay(String userName, String passWord, int money) {
        Log.d("gh","调用到ALIPAY应用");
        if ("abc".equals(userName) && "123".equals(passWord) && 1000 > money) {
            return true;
        } else {
            return false;
        }
    }

    //[定义中间人对象]
    private class MyBinder extends Iservice.Stub{

        @Override
        public boolean callPay(String userName, String passWord, int money) {
            return pay(userName,passWord,money);
        }
    }

}
