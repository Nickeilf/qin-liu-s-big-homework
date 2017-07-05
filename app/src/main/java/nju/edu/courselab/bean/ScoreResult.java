
package nju.edu.courselab.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ScoreResult {

    @SerializedName("git_url")
    @Expose
    private String gitUrl;
    @SerializedName("score")
    @Expose
    private Integer score;
    @SerializedName("scored")
    @Expose
    private Boolean scored;

    public String getGitUrl() {
        return gitUrl;
    }

    public void setGitUrl(String gitUrl) {
        this.gitUrl = gitUrl;
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
