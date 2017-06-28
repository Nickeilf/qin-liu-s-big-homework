package nju.edu.courselab.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import nju.edu.courselab.R;
import nju.edu.courselab.adapter.ClassAdapter;
import nju.edu.courselab.adapter.CourseAdapter;
import nju.edu.courselab.bean.Course;

/**
 * Created by nick on 2017/6/24.
 */
public class TeacherCourseActivity extends AppCompatActivity implements View.OnClickListener, ExpandableListView.OnChildClickListener {
    String username;
    String password;
    private ExpandableListView list;
    private ImageView back;

    private List<Course> group;
    private List<List<String>> item;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_teacher);
        statusSet();

        Intent intent=getIntent();
        Bundle bundle = intent.getExtras();
        username = bundle.getString("username");
        password = bundle.getString("password");

        list = (ExpandableListView) findViewById(R.id.course_list);
        back = (ImageView) findViewById(R.id.course_back);
        group = new ArrayList<>();
        item = new ArrayList<>();

        createData();

        CourseAdapter adapter = new CourseAdapter(this,group,item);
        list.setAdapter(adapter);
        list.setOnChildClickListener(this);
        back.setOnClickListener(this);
    }

    private void createData() {
        Course course = new Course(2);
        group.add(course);

        List<String> item1 = new ArrayList<>();
        item1.add("查看作业");
        item1.add("查看考试");
        item1.add("查看练习");

        item.add(item1);
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


    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        int course_id = group.get(groupPosition).getId();

        Intent intent1 = new Intent();
        Bundle bundle1 = new Bundle();
        bundle1.putString("username",username);
        bundle1.putString("password",password);
        bundle1.putInt("courseid",course_id);
        bundle1.putInt("index",childPosition);
        intent1.putExtras(bundle1);
        intent1.setClass(this,TeacherHomeworkActivity.class);
        startActivity(intent1);
        return true;
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.course_back){
            finish();
        }
    }
}
