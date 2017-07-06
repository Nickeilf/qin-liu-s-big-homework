package nju.edu.courselab.dataService.impl;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;


import nju.edu.courselab.Util.JsonPost;
import nju.edu.courselab.bean.Login;
import nju.edu.courselab.bean.Student;
import nju.edu.courselab.bean.Teacher;
import nju.edu.courselab.bean.User;
import nju.edu.courselab.dataService.LoginDataService;

/**
 * Created by nick on 2017/6/17.
 */
public class LoginDataServiceImpl implements LoginDataService {
    private final String path = "http://115.29.184.56:8090/api/user/auth";

    @Override
    public User login(String username, String password) {
        System.out.println(username+"  "+password);
        JsonPost.PostJsonThread t = new JsonPost.PostJsonThread(path,new Gson().toJson(new Login(username,password)),new Handler());
        t.start();
        System.out.println("thread start");
        while(!t.isReady()){
            System.out.println("not ready");
        }
        String response = t.getResponse();
        Gson gson = new Gson();
        if(response.length()!=0) {
            if (response.contains("authority")) {
                //teacher
                Teacher teacher = gson.fromJson(response, Teacher.class);
                return teacher;
            } else if (response.contains("gitId")){
                //student
                Student student = gson.fromJson(response, Student.class);
                return student;
            }
            System.out.println(response);
        }
        return null;
    }

    private JSONObject getUserLogin(String un, String pw) {
        JSONObject js = new JSONObject();
        try {
            js.put("username", un);
            js.put("password", pw);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return js;
    }

}
