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
import model.Rank;

/**
 *
 * @author PC
 */
public class RankDAO {
    public ArrayList<Rank> getRank(int by){
        System.out.println("vao day di");
        ArrayList<Rank> result = new ArrayList<Rank>();
        String sql = "";
        if(by == 0){
             sql+= "SELECT * FROM viewrank ORDER BY point DESC;";
        }
        if(by == 1){
             sql+= "SELECT * FROM viewrank ORDER BY totalBattle DESC;";
        }
        if(by == 2){
            sql+= "SELECT * FROM viewrank ORDER BY winRate DESC;";
        }
        if(by == 3){
            sql+= "SELECT * FROM viewrank ORDER BY battleWin DESC;";
        }
        if(by == 4){
            sql+= "SELECT * FROM viewrank ORDER BY battleLose DESC;";
        }
        
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
 
            while(rs.next()){
                Rank rank = new Rank();
                rank.setRankId(rs.getInt("rankId"));
                rank.setFullName(rs.getString("fullName"));
                rank.setUserId(rs.getInt("userId"));
                rank.setBattleLose(rs.getInt("battleLose"));
                rank.setBattleWin(rs.getInt("battleWin"));
                rank.setTotalBattle(rs.getInt("totalBattle"));
                rank.setPoint(rs.getInt("point"));
                rank.setWinRate(rs.getDouble("winRate"));
                
                result.add(rank);
                System.out.println(result.size());
            }
        }catch(Exception e){
            e.printStackTrace();
        }   
        return result;
        
        
    }
}
