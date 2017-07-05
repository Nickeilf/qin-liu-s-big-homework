
package nju.edu.courselab.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MetricData {

    @SerializedName("git_url")
    @Expose
    private String gitUrl;
    @SerializedName("measured")
    @Expose
    private Boolean measured;
    @SerializedName("total_line_count")
    @Expose
    private Integer totalLineCount;
    @SerializedName("comment_line_count")
    @Expose
    private Integer commentLineCount;
    @SerializedName("field_count")
    @Expose
    private Integer fieldCount;
    @SerializedName("method_count")
    @Expose
    private Integer methodCount;
    @SerializedName("max_coc")
    @Expose
    private Integer maxCoc;

    public String getGitUrl() {
        return gitUrl;
    }

    public void setGitUrl(String gitUrl) {
        this.gitUrl = gitUrl;
    }

    public Boolean getMeasured() {
        return measured;
    }

    public void setMeasured(Boolean measured) {
        this.measured = measured;
    }

    public Integer getTotalLineCount() {
        return totalLineCount;
    }

    public void setTotalLineCount(Integer totalLineCount) {
        this.totalLineCount = totalLineCount;
    }

    public Integer getCommentLineCount() {
        return commentLineCount;
    }

    public void setCommentLineCount(Integer commentLineCount) {
        this.commentLineCount = commentLineCount;
    }

    public Integer getFieldCount() {
        return fieldCount;
    }

    public void setFieldCount(Integer fieldCount) {
        this.fieldCount = fieldCount;
    }

    public Integer getMethodCount() {
        return methodCount;
    }

    public void setMethodCount(Integer methodCount) {
        this.methodCount = methodCount;
    }

    public Integer getMaxCoc() {
        return maxCoc;
    }

    public void setMaxCoc(Integer maxCoc) {
        this.maxCoc = maxCoc;
    }

}
