/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author NgocThanh
 */
public class Notify implements Serializable{
    private int ID;
    private String type;
    private String content;
    private Date time;
    private int status;
    private int userid;
    private int idsend;

    public Notify() {
    }

    public Notify(String type, String content, Date time, int status, int id, int idsend) {
        this.type = type;
        this.content = content;
        this.status = status;
        this.userid = id;
        this.time = time;
        this.idsend = idsend;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getIdsent() {
        return idsend;
    }

    public void setIdsent(int idsend) {
        this.idsend = idsend;
    }
    
    
    
    
}
