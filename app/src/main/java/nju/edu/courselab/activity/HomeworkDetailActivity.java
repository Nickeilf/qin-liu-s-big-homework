package nju.edu.courselab.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import nju.edu.courselab.R;
import nju.edu.courselab.adapter.QuestionAdapter;
import nju.edu.courselab.bean.Homework;

/**
 * Created by nick on 2017/6/25.
 */
public class HomeworkDetailActivity extends AppCompatActivity implements View.OnClickListener{

    private ListView list;

    private ImageView back;

    private Homework data;
    private TextView title;
    private TextView description;
    private TextView start;
    private TextView end;
    private TextView courseNum;
    private TextView status;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_homework);

        init();
    }

    private void init() {
        data= (Homework) getIntent().getSerializableExtra("data");

        list = (ListView) findViewById(R.id.question_list);
        back = (ImageView) findViewById(R.id.detail_back);
        title= (TextView) findViewById(R.id.detail_title);
        description= (TextView) findViewById(R.id.detail_description);
        start= (TextView) findViewById(R.id.detail_start);
        end= (TextView) findViewById(R.id.detail_end);
        courseNum= (TextView) findViewById(R.id.detail_num);
        status= (TextView) findViewById(R.id.detail_status);

        title.setText(data.getTitle());
        description.setText(data.getDescription());
        start.setText(data.getStartAt());
        end.setText(data.getEndAt());
        courseNum.setText("课程号："+data.getCourse());
        status.setText("状态："+data.getStatus());

        QuestionAdapter adapter = new QuestionAdapter(this,R.layout.list_item_detail_homework,data.getQuestions());
        list.setAdapter(adapter);

        back.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.detail_back){
            finish();
        }
    }
}
