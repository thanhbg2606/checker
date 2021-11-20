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
import model.Notify;
import model.User;

/**
 *
 * @author NgocThanh
 */
public class NotifyDAO extends DAO{

    public NotifyDAO() {
        super();
    }
    
    public boolean addNotify(Notify n, User[] u) {
        
        String sql = "INSERT INTO tblnotify(type, content, status, userid, idsend)"
                + "VALUES(?, ?, ?, ?, ?) ";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, n.getType());
            ps.setString(2, n.getContent());
            ps.setInt(3, n.getStatus());
            ps.setInt(4, u[1].getId());
            ps.setInt(5, u[0].getId());
     
            ps.executeUpdate();
            return true;
            
        }catch(Exception e) {
            e.printStackTrace();
            return false;
        }
        
    }
    
    public ArrayList<Notify> displayNotify(User u){
        ArrayList<Notify> result = new ArrayList<Notify>();
        String sql = "SELECT type, content, idsend FROM tblnotify WHERE userid = ? and status = ?";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, u.getId());
            ps.setInt(2, 0); //trạng thái 0 là chưa xem
           
            ResultSet rs = ps.executeQuery();
 
            while(rs.next()){
                Notify n = new Notify();
                n.setType(rs.getString("type"));
                n.setContent(rs.getString("content"));
                n.setIdsent(rs.getInt("idsend"));
    
                result.add(n);
            }    
        } catch (Exception e) {
        }
        return result;
    }
    
    
    //Vẫn còn Bug ;;-------------------------------Xóa hết thông báo của 1 user
    public boolean updateStatusNotify(User u){
        String sql = "UPDATE tblnotify SET status = ? WHERE userid = ?";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, 1); // trạng thái 1 là thông bá0 đã xem
            ps.setInt(2, u.getId());
            ps.executeUpdate();
           
            return true;            
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
