package nju.edu.courselab.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import nju.edu.courselab.R;
import nju.edu.courselab.adapter.ScoreAdapter;
import nju.edu.courselab.bean.Assignment;
import nju.edu.courselab.bean.Questions;
import nju.edu.courselab.bean.Score;
import nju.edu.courselab.dataService.TeacherAssignmentDataService;
import nju.edu.courselab.dataService.impl.TeacherAssignmentDataServiceImpl;

/**
 * Created by nick on 2017/6/25.
 */
public class TeacherAssignmentDetailActivity extends AppCompatActivity implements View.OnClickListener{

    private TeacherAssignmentDataService dataService;
    private Assignment assignment;

    private ExpandableListView list;
    private ImageView back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_assignment);
        statusSet();
        initData();

        init();
    }

    private void statusSet() {
        Window window = this.getWindow();
        //设置透明状态栏,这样才能让 ContentView 向上
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //设置状态栏颜色
        window.setStatusBarColor(0x1A1A1A);

        ViewGroup mContentView = (ViewGroup) this.findViewById(Window.ID_ANDROID_CONTENT);
        View mChildView = mContentView.getChildAt(0);
        if (mChildView != null) {
            //注意不是设置 ContentView 的 FitsSystemWindows, 而是设置 ContentView 的第一个子 View . 使其不为系统 View 预留空间.
            ViewCompat.setFitsSystemWindows(mChildView, false);
        }
    }

    private void init() {
        list= (ExpandableListView) findViewById(R.id.assignment_question_list);

        List<Questions> group = assignment.getQuestions();
        List<List<Score>> item = new ArrayList<>();
        for (Questions questions:group){
            item.add(questions.getStudents());
        }

        ScoreAdapter adapter = new ScoreAdapter(this,group,item);
        list.setAdapter(adapter);



        back = (ImageView) findViewById(R.id.assignment_detal_back);
        back.setOnClickListener(this);
    }

    private void initData() {
        dataService = new TeacherAssignmentDataServiceImpl();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String username = bundle.getString("username");
        String password = bundle.getString("password");
        String id = bundle.getString("id");

        assignment = dataService.getAssignments(username,password,id);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.assignment_detal_back){
            finish();
        }
    }


}
