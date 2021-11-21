/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc.dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import static jdbc.dao.DAO.con;
import model.Friend;
 
import model.User;
 
public class UserDAO extends DAO{
     
    public UserDAO() {
        super();
    }
     
    public boolean updateStatusUser(User u, int s){
        String sql = "UPDATE tbluser SET status = ? WHERE id = ?";
        try {
           PreparedStatement ps = con.prepareStatement(sql);
           ps.setInt(2, u.getId());
           
           if(s==0) ps.setString(1, "OFFLINE");
           else if(s==1) ps.setString(1, "ONLINE");
           else if(s==2) ps.setString(1, "BUSY");
           
           ps.executeUpdate();
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }       
    }
    
    
    
    public User getUserById(int key){
        User client = new User();
        String sql = "SELECT * FROM tbluser WHERE id = ?";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, key);
            ResultSet rs = ps.executeQuery();
 
            while(rs.next()){ 
                client.setId(rs.getInt("id"));
                client.setName(rs.getString("fullName"));
                client.setPosition(rs.getString("position"));
                client.setStatus(rs.getString("status"));
                
                
                System.out.println("Đã KN CSDL lấy User theo ID");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }   
        return client;
    }
    
    public ArrayList<User> updateStatusUser(){
        ArrayList<User> result = new ArrayList<User>();
        String sql = "SELECT * FROM tbluser";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
 
            while(rs.next()){
                User client = new User(); 
                
                client.setId(rs.getInt("id"));
                client.setName(rs.getString("fullName"));
                client.setPosition(rs.getString("position"));
                client.setStatus(rs.getString("status"));
                
                result.add(client);
            }
        }catch(Exception e){
            e.printStackTrace();
        }   
        return result;
    }
    
    public boolean checkLogin(User user) {
        boolean result = false;
        String sql = "SELECT  id, fullName, position, status FROM tbluser WHERE username = ? AND password = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
             
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("fullName"));
                user.setPosition(rs.getString("position"));
                user.setStatus("ONLINE");

                result = true;
            }
            
            
        }catch(Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public boolean addUser(User user) {
        
        String sql = "INSERT INTO tbluser(username, password, fullName, position, status)"
                + "VALUES(?, ?, ?, ?, ?) ";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getName());
            ps.setString(4, "Player");
            ps.setString(5, "OFFLINE");
             
            ps.executeUpdate();
            return true;
            
        }catch(Exception e) {
            e.printStackTrace();
            return false;
        }
        
    }
    
    public ArrayList<User> searchUser(String key){
        ArrayList<User> result = new ArrayList<User>();
        String sql = "SELECT * FROM tbluser WHERE fullName LIKE ?";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + key + "%");
            ResultSet rs = ps.executeQuery();
 
            while(rs.next()){
                User client = new User();
                client.setId(rs.getInt("ID"));
                client.setName(rs.getString("fullName"));
                
                result.add(client);
                System.out.println("Da KL toi database lay tt search User");
            }
        }catch(Exception e){
            e.printStackTrace();
        }   
        return result;
    }
    
    public ArrayList<Friend> displayFriend(User user){
        ArrayList<Friend> result = new ArrayList<Friend>();
        String sql = "SELECT * FROM viewfriend WHERE userid = ?";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, user.getId());
            ResultSet rs = ps.executeQuery();
 
            while(rs.next()){
                Friend c = new Friend();
                c.setFriendid(rs.getInt("friendid"));
                c.setFullName(rs.getString("fullName"));
                c.setStatus(rs.getString("status"));
                
                result.add(c);
            }
        }catch(Exception e){
            e.printStackTrace();
        }   
        return result;
    }
    
    public boolean addFriend(User u1, User u2) {
        
        String sql = "INSERT INTO tblfriend(userid, friendid) VALUES(?, ?)";
        String sql2 = "INSERT INTO tblfriend(friendid, userid) VALUES(?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, u1.getId());
            ps.setInt(2, u2.getId());
             
            ps.executeUpdate();
            
            PreparedStatement ps1 = con.prepareStatement(sql2);
            ps1.setInt(1, u1.getId());
            ps1.setInt(2, u2.getId());
            
            ps1.executeUpdate();
            
            return true;
            
        }catch(Exception e) {
            e.printStackTrace();
            return false;
        }       
    }
    
}
