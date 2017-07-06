package nju.edu.courselab.Util;

import android.os.Handler;
import android.os.Message;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by nick on 2017/6/23.
 */
public class AsycThread extends Thread {
    private boolean ready = false;
    private Request request;
    private String response;
    private Handler handler;

    public AsycThread(Request request) {
        this.request = request;
        this.handler=handler;
    }

    @Override
    public void run() {
        super.run();
        OkHttpClient client = new OkHttpClient();
        try {
            Response res = client.newCall(request).execute();
            response = res.body().string();

//            Message message = new Message();
//            message.what = 1;
//            message.obj = response;
//            handler.sendMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
//            Message message = new Message();
//            message.what = 0;
//            message.obj = "fail";
//            handler.sendMessage(message);
        } finally {
            ready = true;
        }


    }

    public boolean isReady() {
        return ready;
    }

    public String getResponse() {
        return response;
    }
}

