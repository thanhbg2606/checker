/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
 
public class ObjectWrapper  implements Serializable{
    private static final long serialVersionUID = 20210811011L;
    public static final int UPDATE_STATUS_USER = -1;
    public static final int SERVER_INFORM_CLIENT_NUMBER = 0;
    public static final int LOGIN_USER = 1;
    public static final int REPLY_LOGIN_USER = 2;
    public static final int REGISTER_USER = 3;
    public static final int REPLY_REGISTER_USER = 4;
    
    public static final int SEARCH_USER = 5;
    public static final int REPLY_SEARCH_USER = 6;
    
    public static final int ADD_FRIEND = 7;
    public static final int REPLY_ADD_FRIEND = 8;
    public static final int DISPLAY_FRIEND = 9;
    public static final int REPLY_DISPLAY_FRIEND = 10;
    
    public static final int SEND_REQUEST_ADD_FRIEND = 11;
    public static final int REPLY_SEND_REQUEST_ADD_FRIEND = 12;
    
    public static final int SEND_REQUEST_FRIEND_CLIENT = 13;
    public static final int REPLY_SEND_REQUEST_FRIEND_CLIENT = 14;
    
    public static final int DISPLAY_NOTIFY = 15;
    public static final int REPLY_DISPLAY_NOTIFY = 16;
    
    public static final int SEND_CHALLENGE_FRIEND = 17;
    public static final int REPLY_SEND_CHALLENGE_FRIEND  = 18;
    
    public static final int ADD_CHALLENGE = 19;
    public static final int REPLY_ADD_CHALLENGE = 20;
    
    public static final int ADD_GROUP = 21;
    public static final int REPLY_ADD_GROUP = 22;
    
    public static final int REPLY_DISPLAY_RANK = 23;


    
     
    private int performative;
    private Object data;
    public ObjectWrapper() {
        super();
    }
    public ObjectWrapper(int performative, Object data) {
        super();
        this.performative = performative;
        this.data = data;
    }
    public int getPerformative() {
        return performative;
    }
    public void setPerformative(int performative) {
        this.performative = performative;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    } 
    
    public String to_String(){
        return "Number fun: " + this.getPerformative() +"|Object: " + this.getData().toString();
    }
}
