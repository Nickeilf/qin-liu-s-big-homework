package nju.edu.courselab.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import nju.edu.courselab.R;
import nju.edu.courselab.bean.Group;
import nju.edu.courselab.bean.Student;

/**
 * Created by nick on 2017/6/23.
 */
public class StudentAdapter extends ArrayAdapter<Student>{
    private int resourceId;

    public StudentAdapter(Context context, int resourceId, List<Student> objects) {
        super(context, resourceId, objects);
        this.resourceId = resourceId;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Student student = getItem(position);
        LinearLayout userListItem = new LinearLayout(getContext());
        String inflater = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater vi = (LayoutInflater) getContext().getSystemService(inflater);
        vi.inflate(resourceId, userListItem, true);


        TextView id = (TextView) userListItem.findViewById(R.id.student_id);
        TextView name = (TextView) userListItem.findViewById(R.id.student_username);

        id.setText(student.getId()+"");
        name.setText(student.getUsername()+" "+student.getName());

        return userListItem;
    }
}
