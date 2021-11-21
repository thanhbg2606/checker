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
import model.Group;
import model.User;
import model.UserGroup;

/**
 *
 * @author NgocThanh
 */
public class UserGroupDAO extends DAO{
    //Chỉ gửi position = 0, thành viên = 1
    public boolean addUserGroup(UserGroup ug, int position){
        String sql = "INSERT INTO tblusergroup(groupid, userid, position)"
                + "VALUES(?, ?, ?)";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, ug.getGroup().getGroupid());
            ps.setInt(2, ug.getUser().getId());
            if(position == 1)
                ps.setString(3, "member");
            else if(position == 0){
                ps.setString(3, "owner");
            }                         
            ps.executeUpdate();
            System.out.println("Thêm User vào Group thành công");
            
            return true;
            
        }catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public UserGroup getPositionGroup(int id){
        UserGroup res = new UserGroup();
        
        String sql = "SELECT \n" +
                        "	viewmembergroup.groupid, \n" +
                        "    viewmembergroup.nameGroup,\n" +
                        "    viewmembergroup.position,\n" +
                        "    tblgroup.notify\n" +
                     "FROM \n" +
                        "	viewmembergroup\n" +
                        "    INNER JOIN tblgroup ON tblgroup.groupid=viewmembergroup.groupid\n" +
                     "WHERE viewmembergroup.userid=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
                        
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Group g = new Group();
                g.setGroupid(rs.getInt("groupid"));
                g.setName(rs.getString("nameGroup"));
                g.setNotify(rs.getString("notify"));
                res.setGroup(g);
                res.setPosition(rs.getString("position"));
                
                return res;
                 
            }
            
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }
        res.setPosition("no-group");
        return res;
    }
    
    public ArrayList<UserGroup> getListUserGroup(int group_id){
        ArrayList<UserGroup> lst = new ArrayList<>();
        String sql = "SELECT * FROM viewmembergroup WHERE groupid = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, group_id);
            ResultSet rs = ps.executeQuery();
                        
            while(rs.next()){
                User u = new User();
                u.setId(rs.getInt("userid"));
                u.setName(rs.getString("fullName"));
                
                UserGroup userg = new UserGroup();
                userg.setUser(u);
                userg.setPosition(rs.getString("position"));
                
                lst.add(userg);
                
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lst;
    }
    
    
    public boolean deleteUserGroup(int id){
        String sql = "DELETE FROM tblusergroup WHERE userid = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
