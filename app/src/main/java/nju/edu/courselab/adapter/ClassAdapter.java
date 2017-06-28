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
import nju.edu.courselab.bean.Course;
import nju.edu.courselab.bean.Group;

/**
 * Created by nick on 2017/6/23.
 */
public class ClassAdapter extends ArrayAdapter<Group> {
    private int resourceId;
    private List<Group> groups;

    public ClassAdapter(Context context, int resourceId, List<Group> objects) {
        super(context, resourceId, objects);
        this.resourceId = resourceId;
        this.groups=objects;
    }




    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Group group = groups.get(position);
        LinearLayout userListItem = new LinearLayout(getContext());
        String inflater = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater vi = (LayoutInflater) getContext().getSystemService(inflater);
        vi.inflate(resourceId, userListItem, true);

        TextView id= (TextView) userListItem.findViewById(R.id.class_item_id);
        TextView classname= (TextView) userListItem.findViewById(R.id.class_item);

        id.setText(""+group.getId());
        classname.setText(group.getName());

        return userListItem;
    }

}
