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
import nju.edu.courselab.bean.Student;

/**
 * Created by nick on 2017/6/25.
 */
public class HomeworkAdapter  extends ArrayAdapter<Homework>{

        private int resourceId;

        public HomeworkAdapter(Context context, int resourceId, List<Homework> objects) {
            super(context, resourceId, objects);
            this.resourceId = resourceId;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Homework homework = getItem(position);
            LinearLayout userListItem = new LinearLayout(getContext());
            String inflater = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(inflater);
            vi.inflate(resourceId, userListItem, true);


            TextView title = (TextView) userListItem.findViewById(R.id.homework_title);
            TextView description = (TextView) userListItem.findViewById(R.id.homework_description);
            TextView start = (TextView) userListItem.findViewById(R.id.homework_start);
            TextView end = (TextView) userListItem.findViewById(R.id.homework_end);


            title.setText(homework.getTitle());
            if (homework.getDescription()!=null) {
                description.setText(homework.getDescription());
            }else{
                description.setText("无课程描述");
            }
            start.setText("开始时间:"+homework.getStartAt());
            end.setText("结束时间:"+homework.getEndAt());

            return userListItem;
        }
}
