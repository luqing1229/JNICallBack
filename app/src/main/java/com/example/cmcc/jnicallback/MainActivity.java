package com.example.cmcc.jnicallback;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    private Button intButton = null;
    private Button stringButton = null;
    private Button arrayButton = null;
    private TextView intTextView = null;
    private TextView stringTextView = null;
    private TextView arrayTextView = null;

    private Handler mHandler = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intButton = (Button) this.findViewById(R.id.intbutton);
        //注册按钮监听
        intButton.setOnClickListener(new ClickListener());
        stringButton = (Button) this.findViewById(R.id.stringbutton);
        //注册按钮监听
        stringButton.setOnClickListener(new ClickListener());
        arrayButton = (Button) this.findViewById(R.id.arraybutton);
        //注册按钮监听
        arrayButton.setOnClickListener(new ClickListener());

        intTextView = (TextView) this.findViewById(R.id.inttextview);
        stringTextView = (TextView) this.findViewById(R.id.stringtextview);
        arrayTextView = (TextView) this.findViewById(R.id.arraytextview);

        //消息处理
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    //整型
                    case 0: {
                        intTextView.setText(msg.obj.toString());
                        break;
                    }
                    //字符串
                    case 1: {
                        stringTextView.setText(msg.obj.toString());
                        break;
                    }
                    //数组
                    case 2: {
                        byte[] b = (byte[]) msg.obj;
                        arrayTextView.setText(Byte.toString(b[0]) + Byte.toString(b[1]) + Byte.toString(b[2]) + Byte.toString(b[3]) + Byte.toString(b[4]));
                        break;
                    }
                }

            }

        };


    }

    //按钮监听实现
    public class ClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            switch (v.getId()) {
                case R.id.intbutton: {
                    //调用JNI中的函数
                    callJNIInt(1);
                    break;
                }
                case R.id.stringbutton: {
                    //调用JNI中的函数
                    callJNIString("你好A");
                    break;
                }
                case R.id.arraybutton: {
                    //调用JNI中的函数
                    callJNIByte(new byte[]{1, 2, 3, 4, 5});
                    break;
                }
            }
        }
    }


    //被JNI调用，参数由JNI传入
    private void callbackInt(int i) {
        Message msg = new Message();
        //消息类型
        msg.what = 0;
        //消息内容
        msg.obj = i;
        //发送消息
        mHandler.sendMessage(msg);
    }

    //被JNI调用，参数由JNI传入
    private void callbackString(String s) {
        Message msg = new Message();
        //消息类型
        msg.what = 1;
        //消息内容
        msg.obj = s;
        //发送消息
        mHandler.sendMessage(msg);
    }

    //被JNI调用，参数由JNI传入
    private void callbackByte(byte[] b) {
        Message msg = new Message();
        //消息类型
        msg.what = 2;
        //消息内容
        msg.obj = b;
        //发送消息
        mHandler.sendMessage(msg);
    }

    //本地方法，由java调用
    private native void callJNIInt(int i);

    private native void callJNIString(String s);

    private native void callJNIByte(byte[] b);

    static {
        //加载本地库
        System.loadLibrary("native-lib");
    }

}