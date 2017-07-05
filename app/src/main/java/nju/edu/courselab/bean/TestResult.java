
package nju.edu.courselab.bean;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TestResult {

    @SerializedName("git_url")
    @Expose
    private String gitUrl;
    @SerializedName("compile_succeeded")
    @Expose
    private Boolean compileSucceeded;
    @SerializedName("tested")
    @Expose
    private Boolean tested;
    @SerializedName("testcases")
    @Expose
    private List<Testcase> testcases = null;

    public String getGitUrl() {
        return gitUrl;
    }

    public void setGitUrl(String gitUrl) {
        this.gitUrl = gitUrl;
    }

    public Boolean getCompileSucceeded() {
        return compileSucceeded;
    }

    public void setCompileSucceeded(Boolean compileSucceeded) {
        this.compileSucceeded = compileSucceeded;
    }

    public Boolean getTested() {
        return tested;
    }

    public void setTested(Boolean tested) {
        this.tested = tested;
    }

    public List<Testcase> getTestcases() {
        return testcases;
    }

    public void setTestcases(List<Testcase> testcases) {
        this.testcases = testcases;
    }

}
