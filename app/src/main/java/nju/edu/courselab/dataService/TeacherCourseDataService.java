package nju.edu.courselab.dataService;

import java.util.List;

import nju.edu.courselab.bean.Homework;

/**
 * Created by nick on 2017/6/25.
 */
public interface TeacherCourseDataService {

    public List<Homework> getHomework(String username,String password,String courseid,int index);


}
