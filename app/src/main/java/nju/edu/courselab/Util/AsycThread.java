package nju.edu.courselab.Util;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by nick on 2017/6/23.
 */
public class AsycThread extends Thread {
    boolean ready = false;
    Request request;
    String response;

    public AsycThread(Request request) {
        this.request = request;
    }

    @Override
    public void run() {
        super.run();
        OkHttpClient client = new OkHttpClient();
        try {
            Response res = client.newCall(request).execute();
            response = res.body().string();
        } catch (IOException e) {
            e.printStackTrace();
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

