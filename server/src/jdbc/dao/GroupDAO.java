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
import model.UserGroup;

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
    
    public int getGroupidByGroupName(String name){
        String sql = "SELECT * FROM tblgroup WHERE name = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                return rs.getInt("groupid");
            }
        } catch (Exception e) {
        }
        return 0;
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
        //Thêm dữ liệu vào 2 bảng Group và GroupUser
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
            System.out.println("add group thaats bai");
            return false;
        }
    }    
    
    public ArrayList<Group> getListAllGroup(){
        ArrayList<Group> result = new ArrayList<Group>();
        String sql = "SELECT * FROM viewlistallgroup";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
 
            while(rs.next()){
                Group g = new Group();
                g.setGroupid(rs.getInt("groupid"));
                
                User u = new User();
                u.setId(rs.getInt("userid"));
                u.setName(rs.getString("createName"));
                
                g.setUserCreate(u);
                g.setNotify(rs.getString("notify"));
                g.setName(rs.getString("name"));
                
                result.add(g);
                
            }
        }catch(Exception e){
            e.printStackTrace();
        }   
        return result;
    }
    
    public boolean addNotifyGroup(Group p){
        String sql = "UPDATE tblgroup SET notify = ? WHERE groupid=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1, p.getNotify());
            ps.setInt(2, p.getGroupid());               
            ps.executeUpdate();
                        
            return true;
        }catch(Exception e) {
            e.printStackTrace();
            System.out.println("add notify group thaats bai");
            return false;
        }
    }
}
