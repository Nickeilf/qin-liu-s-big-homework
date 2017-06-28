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
import nju.edu.courselab.adapter.ClassAdapter;
import nju.edu.courselab.bean.Group;
import nju.edu.courselab.dataService.TeacherClassDataService;
import nju.edu.courselab.dataService.impl.TeacherClassDataServiceImpl;

/**
 * Created by nick on 2017/6/23.
 */
public class TeacherClassActivity extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemClickListener{

    private TeacherClassDataService teacherService;

    private List<Group> groups;
    private ListView list;
    private ImageView back;

    private String username;
    private String password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_teacher);
        init();
        statusSet();
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
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        teacherService = new TeacherClassDataServiceImpl();
        username=bundle.getString("username");
        password=bundle.getString("password");
        groups=teacherService.getGroups(bundle.getString("username"),bundle.getString("password"));


        back = (ImageView) findViewById(R.id.class_back);
        list= (ListView) findViewById(R.id.class_list);
        ClassAdapter adapter =new ClassAdapter(this,R.layout.list_item_class,groups);
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);
        back.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.class_back){
           this.finish();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TextView class_id = (TextView)view.findViewById(R.id.class_item_id);
        String cid = class_id.getText().toString();
        // 跳转班级的学生
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("username",username);
        bundle.putString("password",password);
        bundle.putString("id",((TextView) view.findViewById(R.id.class_item_id)).getText().toString());
        intent.putExtras(bundle);
        intent.setClass(this, TeacherStudentActivity.class);
        startActivity(intent);
    }
}
