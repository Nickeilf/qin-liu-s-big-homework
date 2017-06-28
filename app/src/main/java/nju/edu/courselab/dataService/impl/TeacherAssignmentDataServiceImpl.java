package nju.edu.courselab.dataService.impl;

import com.google.gson.Gson;

import java.util.List;

import nju.edu.courselab.Util.AsycThread;
import nju.edu.courselab.Util.BaseEncoder;
import nju.edu.courselab.Util.GsonUtil;
import nju.edu.courselab.bean.Assignment;
import nju.edu.courselab.bean.Homework;
import nju.edu.courselab.dataService.TeacherAssignmentDataService;
import okhttp3.Request;

/**
 * Created by nick on 2017/6/25.
 */
public class TeacherAssignmentDataServiceImpl implements TeacherAssignmentDataService {
    private final String baseURL="http://115.29.184.56:8090/api/assignment/";

    @Override
    public Assignment getAssignments(String username, String password, String id) {
        String url = baseURL+id+"/score";
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
            Assignment assignment = GsonUtil.parseJsonWithGson(response,Assignment.class);
            System.out.println(assignment.getAssignmentId());
            return assignment;
        }

        return null;
    }
}
