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
import nju.edu.courselab.bean.Student;

/**
 * Created by nick on 2017/6/25.
 */
public class AssignmentAdapter extends ArrayAdapter<String> {
    private int resourceId;

    public AssignmentAdapter(Context context, int resourceId, List<String> objects) {
        super(context, resourceId, objects);
        this.resourceId = resourceId;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String id = getItem(position);
        LinearLayout userListItem = new LinearLayout(getContext());
        String inflater = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater vi = (LayoutInflater) getContext().getSystemService(inflater);
        vi.inflate(resourceId, userListItem, true);


        TextView id_txt = (TextView) userListItem.findViewById(R.id.assignment_id);
        id_txt.setText(id);


        return userListItem;
    }
}
