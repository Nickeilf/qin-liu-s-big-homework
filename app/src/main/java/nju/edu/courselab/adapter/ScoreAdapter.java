package nju.edu.courselab.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.List;

import nju.edu.courselab.R;
import nju.edu.courselab.bean.Question;
import nju.edu.courselab.bean.QuestionInfo;
import nju.edu.courselab.bean.Questions;
import nju.edu.courselab.bean.Score;

/**
 * Created by nick on 2017/6/25.
 */
public class ScoreAdapter extends BaseExpandableListAdapter {
    private List<Questions> group_list;
    private List<List<Score>> item_list;
    private Context context;



    public ScoreAdapter(Context context, List<Questions> group_list, List<List<Score>> item_list) {
        this.group_list = group_list;
        this.item_list = item_list;
        this.context = context;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.expand_group_assignment_teacher, null);
            groupHolder = new GroupHolder();
            groupHolder.title = (TextView)convertView.findViewById(R.id.question_detail_title);
            groupHolder.description = (TextView)convertView.findViewById(R.id.question_detail_description);
            convertView.setTag(groupHolder);
        }
        else
        {
            groupHolder = (GroupHolder)convertView.getTag();
        }

        QuestionInfo info = group_list.get(groupPosition).getQuestionInfo();

        groupHolder.title.setText(info.getType()+":"+info.getTitle());
        groupHolder.description.setText(info.getDescription());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ItemHolder itemHolder = null;
        if (convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.expand_child_score_teacher, null);
            itemHolder = new ItemHolder();
            itemHolder.name = (TextView)convertView.findViewById(R.id.score_name);
            itemHolder.score = (TextView)convertView.findViewById(R.id.score);
            itemHolder.scored = (TextView)convertView.findViewById(R.id.scored);
            convertView.setTag(itemHolder);
        }
        else
        {
            itemHolder = (ItemHolder)convertView.getTag();
        }

        Score score = item_list.get(groupPosition).get(childPosition);
        itemHolder.name.setText(score.getStudentNumber()+" "+score.getStudentName());
        itemHolder.score.setText("分数:"+score.getScore());
        if (score.getScored()){
            itemHolder.scored.setText("合格");
        }else{
            itemHolder.scored.setText("不合格");
        }

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class GroupHolder
    {
        public TextView title;

        public TextView description;
    }

    class ItemHolder
    {
        public TextView name;

        public TextView score;

        public TextView scored;

    }
}
