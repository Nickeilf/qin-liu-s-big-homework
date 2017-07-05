package nju.edu.courselab.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import nju.edu.courselab.R;
import nju.edu.courselab.bean.QuestionResult;
import nju.edu.courselab.bean.ReadMe;
import nju.edu.courselab.bean.Testcase;

/**
 * Created by nick on 2017/6/28.
 */
public class StudentAssignmentAdapter extends BaseExpandableListAdapter {

    private List<QuestionResult> group_list;
    private List<List<Testcase>> item_list;
    private List<ReadMe> readMeList;
    private Context context;



    public StudentAssignmentAdapter(Context context, List<QuestionResult> group_list, List<List<Testcase>> item_list, List<ReadMe> readMeList) {
        this.group_list = group_list;
        this.item_list = item_list;
        this.context = context;
        this.readMeList = readMeList;
    }

    @Override
    public int getGroupCount() {
        return group_list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return item_list.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return group_list.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return item_list.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder groupHolder = null;
        if (convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.expand_group_assignment_student, null);
            groupHolder = new GroupHolder();
            groupHolder.question_title_student = (TextView)convertView.findViewById(R.id.question_title_student);
            groupHolder.url = (TextView)convertView.findViewById(R.id.url);
            groupHolder.readme = (TextView)convertView.findViewById(R.id.readme);
            groupHolder.measured = (TextView)convertView.findViewById(R.id.measured);
            groupHolder.compiled = (TextView)convertView.findViewById(R.id.compiled);
            groupHolder.tested = (TextView)convertView.findViewById(R.id.tested);
            groupHolder.scored = (TextView)convertView.findViewById(R.id.scored);
            groupHolder.score_student = (TextView)convertView.findViewById(R.id.score_student);
            groupHolder.line_count = (TextView)convertView.findViewById(R.id.line_count);
            groupHolder.comment_count = (TextView)convertView.findViewById(R.id.comment_count);
            groupHolder.method_count = (TextView)convertView.findViewById(R.id.method_count);
            convertView.setTag(groupHolder);
        }
        else
        {
            groupHolder = (GroupHolder)convertView.getTag();
        }

        QuestionResult question = group_list.get(groupPosition);
        ReadMe readMe = readMeList.get(groupPosition);

        groupHolder.question_title_student.setText(question.getQuestionTitle());
        groupHolder.url.setText(question.getMetricData().getGitUrl());
        groupHolder.readme.setText(readMe.getContent());
        groupHolder.measured.setText("measured:"+question.getMetricData().getMeasured());
        groupHolder.compiled.setText("编译通过:"+question.getTestResult().getCompileSucceeded());
        groupHolder.tested.setText("已测试:"+question.getTestResult().getTested());
        groupHolder.scored.setText("已评分:"+question.getScoreResult().getScored());
        groupHolder.score_student.setText("分数:"+question.getScoreResult().getScore());
        groupHolder.line_count.setText("代码行数:"+question.getMetricData().getTotalLineCount());
        groupHolder.comment_count.setText("注释行数:"+question.getMetricData().getCommentLineCount());
        groupHolder.method_count.setText("方法数:"+question.getMetricData().getMethodCount());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ItemHolder itemHolder = null;
        if (convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.expand_child_assignment_student, null);
            itemHolder = new ItemHolder();
            itemHolder.testcase_name = (TextView)convertView.findViewById(R.id.testcase_name);
            itemHolder.passed = (ImageView) convertView.findViewById(R.id.passed);
            convertView.setTag(itemHolder);
        }
        else
        {
            itemHolder = (ItemHolder)convertView.getTag();
        }

        Testcase testcase = item_list.get(groupPosition).get(childPosition);
        itemHolder.testcase_name.setText(testcase.getName());
        if (testcase.getPassed()){
            itemHolder.passed.setImageResource(R.drawable.check);
        }else{
            itemHolder.passed.setImageResource(R.drawable.close);
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class GroupHolder
    {
        public TextView question_title_student;

        public TextView url;

        public TextView readme;
        public TextView measured;
        public TextView compiled;
        public TextView tested;
        public TextView scored;
        public TextView score_student;
        public TextView line_count;
        public TextView comment_count;
        public TextView method_count;
    }

    class ItemHolder
    {
        public TextView testcase_name;

        public ImageView passed;
    }
}
