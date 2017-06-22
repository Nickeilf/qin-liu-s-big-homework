package nju.edu.courselab.bean;

/**
 * Created by nick on 2017/6/21.
 */
public class Teacher extends User {
    private int authority;

    public int getAuthority() {
        return authority;
    }

    public void setAuthority(int authority) {
        this.authority = authority;
    }

    public Teacher(int id, String username, String name, String type, String avatar, String gender, String email, int schoolId, int authority) {
        super(id, username, name, type, avatar, gender, email, schoolId);
        this.authority = authority;
    }
}
