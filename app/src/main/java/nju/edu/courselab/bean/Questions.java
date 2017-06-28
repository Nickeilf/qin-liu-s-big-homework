
package nju.edu.courselab.bean;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Questions {

    @SerializedName("questionInfo")
    @Expose
    private QuestionInfo questionInfo;
    @SerializedName("students")
    @Expose
    private List<Score> students = null;

    public QuestionInfo getQuestionInfo() {
        return questionInfo;
    }

    public void setQuestionInfo(QuestionInfo questionInfo) {
        this.questionInfo = questionInfo;
    }

    public List<Score> getStudents() {
        return students;
    }

    public void setStudents(List<Score> students) {
        this.students = students;
    }

}
