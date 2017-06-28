package nju.edu.courselab.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by nick on 2017/6/23.
 */
public class Group{
    private int id;

    private String name;

    public Group(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }

}
