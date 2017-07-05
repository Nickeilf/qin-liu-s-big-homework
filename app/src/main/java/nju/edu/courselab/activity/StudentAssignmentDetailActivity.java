package nju.edu.courselab.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import nju.edu.courselab.R;
import nju.edu.courselab.adapter.StudentAssignmentAdapter;
import nju.edu.courselab.bean.Analysis;
import nju.edu.courselab.bean.QuestionResult;
import nju.edu.courselab.bean.ReadMe;
import nju.edu.courselab.bean.Testcase;
import nju.edu.courselab.dataService.StudentDataService;
import nju.edu.courselab.dataService.impl.StudentDataServiceImpl;

/**
 * Created by nick on 2017/6/28.
 */
public class StudentAssignmentDetailActivity extends AppCompatActivity implements View.OnClickListener{
    private StudentDataService dataService;
    private String assignment_id;
    private String student_id;
    private String username;
    private String password;

    private Analysis analysis;
    private List<ReadMe> readList;
    private List<List<Testcase>> item_list;

    private ImageView back;
    private ExpandableListView list;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_assignment_student);
        initData();

        init();
    }

    private void init() {
        back = (ImageView) findViewById(R.id.assignment_detail_student_back);
        back.setOnClickListener(this);

        list = (ExpandableListView) findViewById(R.id.assignment_question_result_list);
        StudentAssignmentAdapter assignmentAdapter = new StudentAssignmentAdapter(this,analysis.getQuestionResults(),item_list,readList);
        list.setAdapter(assignmentAdapter);

    }

    private void initData() {
        dataService = new StudentDataServiceImpl();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        username = bundle.getString("username");
        password = bundle.getString("password");
        student_id = bundle.getString("student_id");
        assignment_id = bundle.getString("id");

        analysis = dataService.getAnalysis(username,password,assignment_id,student_id);
        readList = dataService.getReadMe(username,password,assignment_id,student_id,analysis.getQuestionResults());

        item_list = new ArrayList<>();

        List<QuestionResult> questionResults = analysis.getQuestionResults();
        for (QuestionResult questionResult:questionResults){
            item_list.add(questionResult.getTestResult().getTestcases());
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.assignment_detail_student_back){
            finish();
        }
    }
}
