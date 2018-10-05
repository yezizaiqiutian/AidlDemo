package com.gh.happyddz;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.gh.alipay.Iservice;

public class MainActivity extends AppCompatActivity {

    private MyConn conn;
    private Iservice iservice;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent();
        intent.setAction("com.gh.alipay");
        intent.setPackage("com.gh.alipay");
        conn = new MyConn();

        bindService(intent, conn, BIND_AUTO_CREATE);

    }

    @Override
    protected void onDestroy() {
        unbindService(conn);
        super.onDestroy();
    }

    /**
     * 监视服务状态
     */
    private class MyConn implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //获取中间人对象
            iservice = Iservice.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

    /**
     * 点击去支付
     * 调用alipay
     * @param view
     */
    public void click(View view) {
        try {
            boolean result = iservice.callPay("abc", "123", 100);
            if (result) {
                Toast.makeText(MainActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}
