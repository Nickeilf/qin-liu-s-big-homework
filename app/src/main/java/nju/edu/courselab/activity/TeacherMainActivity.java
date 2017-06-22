package nju.edu.courselab.activity;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import nju.edu.courselab.R;
import nju.edu.courselab.View.CircleImageView;

public class TeacherMainActivity extends AppCompatActivity implements View.OnClickListener{

    private CircleImageView avatar;
    private TextView username;
    private TextView name;
    private TextView gender;
    private TextView email;

    private LinearLayout myclass;
    private LinearLayout mycourse;
    private LinearLayout myassignment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_teacher);
        statusSet();

        init();

        Intent intent=getIntent();

        Bundle bundle=intent.getExtras();

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

        myclass= (LinearLayout) findViewById(R.id.class_column);
        mycourse= (LinearLayout) findViewById(R.id.course_column);
        myassignment= (LinearLayout) findViewById(R.id.assignment_column);

        myclass.setOnClickListener(this);
        mycourse.setOnClickListener(this);
        myassignment.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}
