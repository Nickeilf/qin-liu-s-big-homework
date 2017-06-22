package nju.edu.courselab.dataService;

import nju.edu.courselab.bean.User;

/**
 * Created by nick on 2017/6/17.
 */
public interface LoginDataService {

    public User login(String username,String password);
}
