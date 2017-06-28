package nju.edu.courselab.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import nju.edu.courselab.R;
import nju.edu.courselab.bean.Homework;
import nju.edu.courselab.bean.Question;

/**
 * Created by nick on 2017/6/25.
 */
public class QuestionAdapter extends ArrayAdapter<Question> {

    private int resourceId;

    public QuestionAdapter(Context context, int resourceId, List<Question> objects) {
        super(context, resourceId, objects);
        this.resourceId = resourceId;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Question question = getItem(position);
        LinearLayout userListItem = new LinearLayout(getContext());
        String inflater = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater vi = (LayoutInflater) getContext().getSystemService(inflater);
        vi.inflate(resourceId, userListItem, true);


        TextView title = (TextView) userListItem.findViewById(R.id.question_title);
        TextView description = (TextView) userListItem.findViewById(R.id.question_description);
        TextView difficulty = (TextView) userListItem.findViewById(R.id.question_difficulty);
        TextView type = (TextView) userListItem.findViewById(R.id.question_type);
        TextView creator = (TextView) userListItem.findViewById(R.id.question_creator);
        TextView url = (TextView) userListItem.findViewById(R.id.question_url);


        title.setText(question.getTitle());
        if (question.getDescription()!=null) {
            description.setText(question.getDescription());
        }else{
            description.setText("无问题描述");
        }
        difficulty.setText("难度："+question.getDifficulty());
        type.setText("类型："+question.getType());
        creator.setText("创建者:"+question.getCreator().getName());
        url.setText(question.getGitUrl());


        return userListItem;
    }
}
