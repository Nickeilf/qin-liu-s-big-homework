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
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import java.util.List;

import nju.edu.courselab.R;
import nju.edu.courselab.adapter.HomeworkAdapter;
import nju.edu.courselab.bean.Homework;
import nju.edu.courselab.dataService.TeacherCourseDataService;
import nju.edu.courselab.dataService.impl.TeacherCourseDataServiceImpl;

/**
 * Created by nick on 2017/6/25.
 */
public class TeacherHomeworkActivity extends AppCompatActivity implements AdapterView.OnItemClickListener,View.OnClickListener{
    private TeacherCourseDataService dataService;
    private List<Homework> list;

    private ListView listView;
    private TextView title;
    private ImageView back;

    private String username;
    private String password;

    private int courseId;
    private int index;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework_teacher);

        statusSet();
        initData();

        initComponent();


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

    private void initData() {
        //初始化数据
        dataService = new TeacherCourseDataServiceImpl();

        Intent intent = getIntent();
        Bundle bundle=intent.getExtras();
        username=bundle.getString("username");
        password=bundle.getString("password");
        courseId = bundle.getInt("courseid");
        index = bundle.getInt("index");

        list = dataService.getHomework(username,password,courseId+"",index);

    }

    private void initComponent() {
        listView = (ListView) findViewById(R.id.homework_list);
        HomeworkAdapter adapter = new HomeworkAdapter(this,R.layout.list_item_homework,list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        title= (TextView) findViewById(R.id.homework_column_title);
        switch (index){
            case 0:
                title.setText("课程作业");
                break;
            case 1:
                title.setText("课程考试");
                break;
            case 2:
                title.setText("课程练习");
                break;
        }

        back = (ImageView) findViewById(R.id.homework_back);
        back.setOnClickListener(this);



    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent= new Intent();
        intent.setClass(this,HomeworkDetailActivity.class);
        intent.putExtra("data",list.get(position));
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.homework_back){
            finish();
        }
    }
}
