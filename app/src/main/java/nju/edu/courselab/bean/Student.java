package nju.edu.courselab.bean;

/**
 * Created by nick on 2017/6/21.
 */
public class Student extends User {

    private int gitId;

    private int number;

    private int groupId;

    private String gitUsername;

    public int getGitId() {
        return gitId;
    }

    public void setGitId(int gitId) {
        this.gitId = gitId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGitUsername() {
        return gitUsername;
    }

    public void setGitUsername(String gitUsername) {
        this.gitUsername = gitUsername;
    }

    public Student(int id, String username, String name, String type, String avatar, String gender, String email, int schoolId, int gitId, int number, int groupId, String gitUsername) {
        super(id, username, name, type, avatar, gender, email, schoolId);
        this.gitId = gitId;
        this.number = number;
        this.groupId = groupId;
        this.gitUsername = gitUsername;
    }
}
