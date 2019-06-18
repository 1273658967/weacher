package com.example.test.a2048game.Util;

/**
 * Created by 陈芳微 on 2019/6/13.
 */
import okhttp3.OkHttpClient;
import okhttp3.Request;

//服务器的交互
public class HttpUtil {
    public static void sendOkHttpRequest(String address, okhttp3.Callback callback) {
        //创建OkHttpClient对象
        OkHttpClient client = new OkHttpClient();
        //请求传送地址   注册并且回调来处理服务器响应
        //创建Request对象，设置一个url地址（地址）,设置请求方式。
        Request request = new Request.Builder().url(address).build();
        //请求加入调度，重写回调方法
        client.newCall(request).enqueue(callback);
    }
}
