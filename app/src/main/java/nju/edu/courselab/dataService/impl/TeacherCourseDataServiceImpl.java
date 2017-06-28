package nju.edu.courselab.dataService.impl;

import com.google.gson.Gson;

import java.util.List;

import nju.edu.courselab.Util.AsycThread;
import nju.edu.courselab.Util.BaseEncoder;
import nju.edu.courselab.Util.GsonUtil;
import nju.edu.courselab.bean.Group;
import nju.edu.courselab.bean.Homework;
import nju.edu.courselab.dataService.TeacherCourseDataService;
import okhttp3.Request;

/**
 * Created by nick on 2017/6/25.
 */
public class TeacherCourseDataServiceImpl implements TeacherCourseDataService{
    private final String baseURL="http://115.29.184.56:8090/api/course/";


    @Override
    public List<Homework> getHomework(String username, String password, String courseid, int index) {
        String url=baseURL;
        String base=username+":"+password;
        String encodedString = BaseEncoder.encodeString(base);

        switch (index){
            case 0:
                url = url+courseid+"/homework";
                break;
            case 1:
                url = url+courseid+"/exam";
                break;
            case 2:
                url = url+courseid+"/exercise";
                break;
        }

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("authorization", "Basic "+encodedString)
                .addHeader("cache-control", "no-cache")
                .build();

        AsycThread t = new AsycThread(request);
        t.start();
        while(!t.isReady()){
            System.out.println("NOT READY!!!!!!");
        }
        String response = t.getResponse();
        Gson gson = new Gson();

        if(response.length()!=0) {
            System.out.println(response);
            List<Homework> homeworks= GsonUtil.parseJsonArrayWithGson(response,Homework[].class);
            for (Homework homework:homeworks){
                System.out.println(homework.getTitle());
            }
            return homeworks;
        }

        return null;
    }
}
