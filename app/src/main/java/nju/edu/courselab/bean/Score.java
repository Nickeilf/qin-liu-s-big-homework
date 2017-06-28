
package nju.edu.courselab.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Score {

    @SerializedName("studentId")
    @Expose
    private Integer studentId;
    @SerializedName("studentName")
    @Expose
    private String studentName;
    @SerializedName("studentNumber")
    @Expose
    private String studentNumber;
    @SerializedName("score")
    @Expose
    private Integer score;
    @SerializedName("scored")
    @Expose
    private Boolean scored;

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Boolean getScored() {
        return scored;
    }

    public void setScored(Boolean scored) {
        this.scored = scored;
    }

}
