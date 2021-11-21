/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author NgocThanh
 */
public class Group implements Serializable{
    private int groupid;
    private String name;
    private String notify;
    private User userCreate;
    private Date createDate;

    public Group() {
    }

    public Group(int groupid, String name, String notify, User userCreate, Date createDate) {
        this.groupid = groupid;
        this.name = name;
        this.notify = notify;
        this.userCreate = userCreate;
        this.createDate = createDate;
    }

    public int getGroupid() {
        return groupid;
    }

    public void setGroupid(int groupid) {
        this.groupid = groupid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotify() {
        return notify;
    }

    public void setNotify(String notify) {
        this.notify = notify;
    }

    public User getUserCreate() {
        return userCreate;
    }

    public void setUserCreate(User userCreate) {
        this.userCreate = userCreate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    
    
}
