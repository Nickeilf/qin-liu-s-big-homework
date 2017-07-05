package nju.edu.courselab.dataService;

import java.util.List;

import nju.edu.courselab.bean.Analysis;
import nju.edu.courselab.bean.QuestionResult;
import nju.edu.courselab.bean.ReadMe;

/**
 * Created by nick on 2017/6/28.
 */
public interface StudentDataService {

    public Analysis getAnalysis(String username,String password, String assignment_id,String student_id);

    public List<ReadMe> getReadMe(String username, String password, String assignment_id, String student_id, List<QuestionResult> list);
}
