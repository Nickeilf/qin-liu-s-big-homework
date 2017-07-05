package nju.edu.courselab.dataService.impl;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import nju.edu.courselab.Util.AsycThread;
import nju.edu.courselab.Util.BaseEncoder;
import nju.edu.courselab.Util.GsonUtil;
import nju.edu.courselab.bean.Analysis;
import nju.edu.courselab.bean.Assignment;
import nju.edu.courselab.bean.QuestionResult;
import nju.edu.courselab.bean.ReadMe;
import nju.edu.courselab.dataService.StudentDataService;
import okhttp3.Request;

/**
 * Created by nick on 2017/6/28.
 */
public class StudentDataServiceImpl implements StudentDataService {
    private final String baseURL="http://115.29.184.56:8090/api/assignment/";

    @Override
    public Analysis getAnalysis(String username, String password, String assignment_id, String student_id) {
        String url = baseURL+assignment_id+"/student/"+student_id+"/analysis";
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
            Analysis analysis = GsonUtil.parseJsonWithGson(response,Analysis.class);
            System.out.println(analysis.getAssignmentId());
            return analysis;
        }

        return null;
    }

    @Override
    public List<ReadMe> getReadMe(String username, String password, String assignment_id, String student_id, List<QuestionResult> list) {
        String url = baseURL+assignment_id+"/student/"+student_id+"/question/";
        String base=username+":"+password;
        String encodedString = BaseEncoder.encodeString(base);

        List<ReadMe> readList = new ArrayList<>();

        for (int i=0;i<list.size();i++){
            Request request = new Request.Builder()
                    .url(url+list.get(i).getQuestionId())
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
                ReadMe readMe = GsonUtil.parseJsonWithGson(response,ReadMe.class);
                readList.add(readMe);
            }
        }
        return readList;
    }
}
