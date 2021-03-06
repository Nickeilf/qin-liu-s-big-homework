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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import nju.edu.courselab.R;
import nju.edu.courselab.View.CircleImageView;

/**
 * Created by nick on 2017/6/28.
 */
public class StudentMainActivity extends AppCompatActivity implements View.OnClickListener{
    private CircleImageView avatar;
    private TextView username;
    private TextView name;
    private TextView gender;
    private TextView email;
    private TextView number;
    private TextView gitUsername;

    private String userName;
    private String password;
    private String studentId;

    private LinearLayout mycourse;
    private LinearLayout myassignment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_student);
        statusSet();



        init();


        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();

        username.setText((String) bundle.get("username"));
        name.setText((String)bundle.get("name"));
        gender.setText((String)bundle.get("gender"));
        email.setText((String)bundle.get("email"));
        number.setText("学号:"+bundle.getString("number"));
        gitUsername.setText("Git用户名:"+bundle.getString("git_username"));
        userName=(String) bundle.get("username");
        password =(String)bundle.get("password");
        studentId = bundle.getString("student_id");

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
        avatar= (CircleImageView) findViewById(R.id.avartar);
        username= (TextView) findViewById(R.id.username_text);
        name= (TextView) findViewById(R.id.name_text);
        gender= (TextView) findViewById(R.id.gender);
        email= (TextView) findViewById(R.id.email);
        number= (TextView) findViewById(R.id.number);
        gitUsername = (TextView) findViewById(R.id.git_username);

        mycourse= (LinearLayout) findViewById(R.id.course_column);
        myassignment= (LinearLayout) findViewById(R.id.assignment_column);

        mycourse.setOnClickListener(this);
        myassignment.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.course_column:
                // 课程界面
                Toast.makeText(this,"进入课程",Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent();
                Bundle bundle2 = new Bundle();
                bundle2.putString("username",userName);
                bundle2.putString("password",password);
                intent2.putExtras(bundle2);
                intent2.setClass(this, TeacherCourseActivity.class);
                startActivity(intent2);
                break;
            case R.id.assignment_column:
                // 作业界面
                Toast.makeText(this,"进入作业",Toast.LENGTH_SHORT).show();
                Intent intent3 = new Intent();
                Bundle bundle3 = new Bundle();
                bundle3.putString("username",userName);
                bundle3.putString("password",password);
                bundle3.putString("student_id",studentId);
                intent3.putExtras(bundle3);
                intent3.setClass(this, StudentAssignmentActivity.class);
                startActivity(intent3);
                break;

        }
    }
}
