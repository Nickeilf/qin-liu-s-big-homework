package nju.edu.courselab.dataService;

import java.util.List;

import nju.edu.courselab.bean.Assignment;

/**
 * Created by nick on 2017/6/25.
 */
public interface TeacherAssignmentDataService {

    public Assignment getAssignments(String username,String password,String id);
}
