package nju.edu.courselab.activity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import nju.edu.courselab.R;
import nju.edu.courselab.adapter.AssignmentAdapter;

/**
 * Created by nick on 2017/6/25.
 */
public class TeacherAssignmentActivity extends AppCompatActivity implements AdapterView.OnItemClickListener,View.OnClickListener{

    private List<String> ids;
    private ListView list;
    private ImageView back;

    private String username;
    private String password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_teacher);
        initData();

        init();
    }

    private void init() {
        list = (ListView) findViewById(R.id.assignment_list);
        AssignmentAdapter adapter = new AssignmentAdapter(this,R.layout.list_item_assignment,ids);
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);

        back = (ImageView) findViewById(R.id.assignment_back);
        back.setOnClickListener(this);


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        username = bundle.getString("username");
        password = bundle.getString("password");
    }

    private void initData() {
        ids = new ArrayList<>();
        ids.add("38");
        ids.add("93");
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.assignment_back){
            finish();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("id",ids.get(position));
        bundle.putString("username",username);
        bundle.putString("password",password);
        intent.putExtras(bundle);
        intent.setClass(this,TeacherAssignmentDetailActivity.class);
        startActivity(intent);
    }
}
