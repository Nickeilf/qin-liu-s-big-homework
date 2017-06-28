package nju.edu.courselab.dataService;

import java.util.List;

import nju.edu.courselab.bean.Group;
import nju.edu.courselab.bean.Student;

/**
 * Created by nick on 2017/6/23.
 */
public interface TeacherClassDataService {

    public List<Group> getGroups(String username, String password);

    public List<Student> getStudents(String username,String password,String groupId);
}
