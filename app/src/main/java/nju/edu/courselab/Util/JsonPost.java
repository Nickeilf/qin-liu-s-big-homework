package nju.edu.courselab.Util;

import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.URL;

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by nick on 2017/6/21.
 */
public class JsonPost {

    public static class PostJsonThread extends Thread{
        String response ="";
        String path;
        String json;
        boolean ready=false;

        public PostJsonThread(String path, String json) {
            this.path = path;
            this.json = json;
        }

        @Override
        public void run() {
            super.run();
            OkHttpClient client = new OkHttpClient();

            System.out.println(json);
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, json);
            Request request = new Request.Builder()
                    .url("http://115.29.184.56:8090/api/user/auth")
                    .post(body)
                    .addHeader("content-type", "application/json")
                    .addHeader("cache-control", "no-cache")
//                    .addHeader("postman-token", "ddbdad63-7e42-f3a2-5bcd-77e0bc6aedf4")
                    .build();

            try {
                Response re = client.newCall(request).execute();
                response = re.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                ready=true;
            }


        }

        public boolean isReady() {
            return ready;
        }

        public String getResponse() {
            return response;
        }
    }
}
