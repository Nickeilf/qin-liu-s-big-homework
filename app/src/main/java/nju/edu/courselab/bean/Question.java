
package nju.edu.courselab.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Question implements Serializable{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("difficulty")
    @Expose
    private String difficulty;
    @SerializedName("gitUrl")
    @Expose
    private String gitUrl;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("creator")
    @Expose
    private Creator creator;
    @SerializedName("duration")
    @Expose
    private Integer duration;
    @SerializedName("link")
    @Expose
    private Integer link;
    @SerializedName("knowledgeVos")
    @Expose
    private Object knowledgeVos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getGitUrl() {
        return gitUrl;
    }

    public void setGitUrl(String gitUrl) {
        this.gitUrl = gitUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Creator getCreator() {
        return creator;
    }

    public void setCreator(Creator creator) {
        this.creator = creator;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getLink() {
        return link;
    }

    public void setLink(Integer link) {
        this.link = link;
    }

    public Object getKnowledgeVos() {
        return knowledgeVos;
    }

    public void setKnowledgeVos(Object knowledgeVos) {
        this.knowledgeVos = knowledgeVos;
    }

}
