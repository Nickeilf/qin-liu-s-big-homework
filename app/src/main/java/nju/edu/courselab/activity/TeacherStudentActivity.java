package nju.edu.courselab.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.List;

import nju.edu.courselab.R;
import nju.edu.courselab.adapter.StudentAdapter;
import nju.edu.courselab.bean.Student;
import nju.edu.courselab.dataService.TeacherClassDataService;
import nju.edu.courselab.dataService.impl.TeacherClassDataServiceImpl;

/**
 * Created by nick on 2017/6/23.
 */
public class TeacherStudentActivity extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemClickListener {
    private TeacherClassDataService teacherService;
    private String username;
    private String password;
    private List<Student> students;

    private ListView list;

    private ImageView back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_student_teacher);
        init();
    }

    private void init() {
        teacherService = new TeacherClassDataServiceImpl();

        Intent intent=getIntent();
        Bundle bundle =intent.getExtras();
        username = bundle.getString("username");
        password=bundle.getString("password");
        String id=bundle.getString("id");

        students =teacherService.getStudents(username,password,id);

        list = (ListView) findViewById(R.id.student_list);
        StudentAdapter adapter = new StudentAdapter(this,R.layout.list_item_student,students);
        list.setAdapter(adapter);

        list.setOnItemClickListener(this);

        back = (ImageView) findViewById(R.id.student_back);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.student_back){
            this.finish();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Student student = students.get(position);
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("avatar", student.getAvatar());
        bundle.putString("username",student.getUsername());
        bundle.putString("name",student.getName());
        bundle.putString("gender",student.getGender());
        bundle.putString("email",student.getEmail());
        bundle.putString("git_username",student.getGitUsername());
        bundle.putString("student_id",student.getId()+"");
        bundle.putString("number",student.getNumber()+"");
        bundle.putString("password",password);
        intent.putExtras(bundle);
        intent.setClass(this, StudentSingleActivity.class);
        startActivity(intent);
    }
}
