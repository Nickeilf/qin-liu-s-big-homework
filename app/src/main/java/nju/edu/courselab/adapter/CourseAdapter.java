package nju.edu.courselab.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import nju.edu.courselab.R;
import nju.edu.courselab.bean.Course;
import nju.edu.courselab.bean.Group;

/**
 * Created by nick on 2017/6/25.
 */
public class CourseAdapter  extends BaseExpandableListAdapter {
    private List<Course> group_list;
    private List<List<String>> item_list;
    private Context context;



    public CourseAdapter(Context context,List<Course> group_list, List<List<String>> item_list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.expand_group_course_teacher, null);
            groupHolder = new GroupHolder();
            groupHolder.id = (TextView)convertView.findViewById(R.id.course_item_id);
            groupHolder.name = (TextView)convertView.findViewById(R.id.course_item);
            convertView.setTag(groupHolder);
        }
        else
        {
            groupHolder = (GroupHolder)convertView.getTag();
        }


        groupHolder.id.setText(""+group_list.get(groupPosition).getId());
        groupHolder.name.setText("课程"+group_list.get(groupPosition).getId());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ItemHolder itemHolder = null;
        if (convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.expand_child_course_teacher, null);
            itemHolder = new ItemHolder();
            itemHolder.txt = (TextView)convertView.findViewById(R.id.course_choice);
            convertView.setTag(itemHolder);
        }
        else
        {
            itemHolder = (ItemHolder)convertView.getTag();
        }
        itemHolder.txt.setText(item_list.get(groupPosition).get(childPosition));
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class GroupHolder
    {
        public TextView id;

        public TextView name;
    }

    class ItemHolder
    {
        public TextView txt;
    }
}
