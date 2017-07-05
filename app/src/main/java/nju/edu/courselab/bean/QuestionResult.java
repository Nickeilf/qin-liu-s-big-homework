
package nju.edu.courselab.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QuestionResult {

    @SerializedName("questionId")
    @Expose
    private Integer questionId;
    @SerializedName("questionTitle")
    @Expose
    private String questionTitle;
    @SerializedName("metricData")
    @Expose
    private MetricData metricData;
    @SerializedName("testResult")
    @Expose
    private TestResult testResult;
    @SerializedName("scoreResult")
    @Expose
    private ScoreResult scoreResult;

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public MetricData getMetricData() {
        return metricData;
    }

    public void setMetricData(MetricData metricData) {
        this.metricData = metricData;
    }

    public TestResult getTestResult() {
        return testResult;
    }

    public void setTestResult(TestResult testResult) {
        this.testResult = testResult;
    }

    public ScoreResult getScoreResult() {
        return scoreResult;
    }

    public void setScoreResult(ScoreResult scoreResult) {
        this.scoreResult = scoreResult;
    }

}
