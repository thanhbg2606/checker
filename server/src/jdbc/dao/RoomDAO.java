/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc.dao;

import java.util.ArrayList;
import static jdbc.dao.DAO.con;
import model.Room;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.AEADBadTagException;
import model.User;
/**
 *
 * @author PC
 */
public class RoomDAO {
    public ArrayList<Room> getRoom(String string){
        System.out.println("vao day di");
        ArrayList<Room> result = new ArrayList<Room>();
        String sql = "";
        if(string.length() == 0){
             sql+= "SELECT * FROM tblroom;";
        }
        else{
            sql+= "SELECT * FROM tblroom WHERE roomCode LIKE ? OR roomName LIKE ?";
        }
        
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            if(string.length() > 0){
                ps.setString(1, "%" + string + "%");
                ps.setString(2, "%" + string + "%");
            }
            ResultSet rs = ps.executeQuery();
 
            while(rs.next()){
                Room room = new Room();
                room.setRoomId(rs.getInt("roomId"));
                room.setRoomCode(rs.getInt("roomCode"));
                room.setRoomName(rs.getString("roomName"));
                room.setDescription(rs.getString("description"));
                room.setRoomMasterId(rs.getInt("roomMasterId"));
                room.setPlayerId(rs.getInt("playerId"));
                room.setQuantity(rs.getInt("quantity"));
                
                result.add(room);
            }
        }catch(Exception e){
            e.printStackTrace();
        }   
        return result;
    }
    
    public Room createdRoom(int id, String name){
        String sql = "INSERT INTO tblroom (roomCode,roomName,description,roomMasterId, playerId, quantity) VALUES (?,?,?,?,?,1)";
        Room r = new Room();
        try {
            double randomDouble = Math.random();
            randomDouble = randomDouble * 1000 + 1;
            int randomInt = (int) randomDouble;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, randomInt);
            ps.setString(2, name);
            ps.setString(3,"Đang chờ");
            ps.setInt(4, id);
            //Nguoi choi chua vao gan id=0
            ps.setInt(5, 0);
            ps.executeUpdate();
            
            //chấp nhận lỗi nếu random trùng mã phòng
            r.setRoomCode(randomInt);
            r.setRoomName(name);
            return r;
            
        }catch(Exception e) {
            e.printStackTrace();
            return r;
        }
    }
    
    public boolean  joinRoom(int playerid, int roomCode){
        String sql = "UPDATE tblroom SET playerId = ? , description = ?, quantity = 2 WHERE roomCode = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, playerid);
            ps.setString(2, "Đủ");
            ps.setInt(3, roomCode);
            ps.executeUpdate();
            return true;
            
        }catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public ArrayList<User> lstUserRoom(Room room){
        ArrayList<User> lstUser = new ArrayList<>();
        try {
            
            String sql = "SELECT roomMasterId,playerId FROM `tblroom` WHERE roomCode=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, room.getRoomCode());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                int roomMasterId = rs.getInt("roomMasterId");
                int playerId = rs.getInt("playerId");
                if(playerId != 0){
                    User u1 = new UserDAO().getUserById(roomMasterId);
                    User u2 = new UserDAO().getUserById(playerId);
                    lstUser.add(u2);
                    lstUser.add(u1);
                }
                if(playerId == 0){
                    User u1 = new UserDAO().getUserById(roomMasterId);
                    lstUser.add(u1);
                }
            }           
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lstUser;
    }
}
