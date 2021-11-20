/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import static jdbc.dao.DAO.con;
import model.Group;
import model.User;

/**
 *
 * @author NgocThanh
 */
public class GroupDAO {
    
    public boolean checkDuplicateNameGroup(String key){
        boolean result = false;
        String sql = "SELECT * FROM tblgroup WHERE name = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, key);
            
            ResultSet rs = ps.executeQuery();
            
            
            if(rs.next()){
                System.out.println("Da KL toi database kiểm tra trùng group Group");
                result =  true;
            }
            
        }catch(Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    
    public ArrayList<Group> searchGroup(String key){
        ArrayList<Group> result = new ArrayList<Group>();
        String sql = "SELECT * FROM tblgroup WHERE name LIKE ?";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + key + "%");
            ResultSet rs = ps.executeQuery();
 
            while(rs.next()){
                Group group = new Group();
                group.setGroupid(rs.getInt("groupid"));
                group.setName(rs.getString("name"));
                group.setNotify(rs.getString("notify"));
                
                result.add(group);
                System.out.println("Da KL toi database lay tt search Group");
            }
        }catch(Exception e){
            e.printStackTrace();
        }   
        return result;
    }
    
    
    public boolean addGroup(Group group){
        String sql = "INSERT INTO tblgroup(name, createDate, userid) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1, group.getName());
            
            LocalDateTime myDateObj = LocalDateTime.now();
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            String formattedDate = myDateObj.format(myFormatObj);
            System.out.println(formattedDate);
            
            ps.setString(2, formattedDate);
            ps.setInt(3, group.getUserCreate().getId());
                                    
            ps.executeUpdate();
            
            return true;
            
        }catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean joinGroup(User user, Group group){
        String sql = "INSERT INTO tblgroup(groupid, userid, position, name) VALUES (?, ?, 0, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, group.getGroupid());
            ps.setInt(2, user.getId());
            ps.setString(3, group.getName());
            ps.executeUpdate();
            return true;
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public ArrayList<Group> getListGroup(String key){
        ArrayList<Group> result = new ArrayList<Group>();
        String sql = "SELECT * FROM tblgroup WHERE name LIKE ?";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + key + "%");
            ResultSet rs = ps.executeQuery();
 
            while(rs.next()){
                Group client = new Group();
                client.setGroupid(rs.getInt("groupid"));
                client.setName(rs.getString("name"));
                
                result.add(client);
                
            }
        }catch(Exception e){
            e.printStackTrace();
        }   
        return result;
    }
    
//    public ArrayList<Group> getListUser(Group group){
//        String sql = "SELECT * FROM viewgroup  WHERE groupid = ?";
//        try {
//            PreparedStatement ps = con.prepareStatement(sql);
//            ps.setInt(1, group.getGroupid());
//            ResultSet rs = ps.executeQuery();
//        } catch (Exception e) {
//        }
//    } 
}
