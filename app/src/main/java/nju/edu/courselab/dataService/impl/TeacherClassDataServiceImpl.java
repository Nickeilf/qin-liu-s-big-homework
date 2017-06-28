package nju.edu.courselab.dataService.impl;

import com.google.gson.Gson;

import java.util.List;

import nju.edu.courselab.Util.AsycThread;
import nju.edu.courselab.Util.BaseEncoder;
import nju.edu.courselab.Util.GsonUtil;
import nju.edu.courselab.bean.Group;
import nju.edu.courselab.bean.Student;
import nju.edu.courselab.dataService.TeacherClassDataService;
import okhttp3.Request;

/**
 * Created by nick on 2017/6/23.
 */
public class TeacherClassDataServiceImpl implements TeacherClassDataService {
    private final String baseURL="http://115.29.184.56:8090/api/";

    @Override
    public List<Group> getGroups(String username, String password) {
        String url=baseURL+"group";
        String base=username+":"+password;
        String encodedString = BaseEncoder.encodeString(base);
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
            List<Group> groups= GsonUtil.parseJsonArrayWithGson(response,Group[].class);
            for (Group group:groups){
                System.out.println(group.getName());
            }
            return groups;
        }
        return null;
    }

    @Override
    public List<Student> getStudents(String username, String password, String groupId) {
        String url=baseURL+"group/"+groupId+"/students";
        String base=username+":"+password;
        String encodedString = BaseEncoder.encodeString(base);
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
            List<Student> students= GsonUtil.parseJsonArrayWithGson(response,Student[].class);
            for (Student student:students){
                System.out.println(student.getName());
            }
            return students;
        }
        return null;
    }

}
