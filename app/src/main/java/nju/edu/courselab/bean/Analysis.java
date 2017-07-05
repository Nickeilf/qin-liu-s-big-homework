
package nju.edu.courselab.bean;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Analysis {

    @SerializedName("studentId")
    @Expose
    private Integer studentId;
    @SerializedName("assignmentId")
    @Expose
    private Integer assignmentId;
    @SerializedName("questionResults")
    @Expose
    private List<QuestionResult> questionResults = null;

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(Integer assignmentId) {
        this.assignmentId = assignmentId;
    }

    public List<QuestionResult> getQuestionResults() {
        return questionResults;
    }

    public void setQuestionResults(List<QuestionResult> questionResults) {
        this.questionResults = questionResults;
    }

}
