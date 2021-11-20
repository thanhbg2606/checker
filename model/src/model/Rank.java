/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.io.Serializable;
/**
 *
 * @author PC
 */
public class Rank implements Serializable{
    private int rankId;
    private int userId;
    private String fullName;
    private int battleWin;
    private int battleLose;
    private int totalBattle;
    private int point;
    private double winRate;

    public Rank(int rankId, int userId, String fullName, int battleWin, int battleLose, int totalBattle, int point, double winRate) {
        this.rankId = rankId;
        this.userId = userId;
        this.fullName = fullName;
        this.battleWin = battleWin;
        this.battleLose = battleLose;
        this.totalBattle = totalBattle;
        this.point = point;
        this.winRate = winRate;
    }

    public Rank() {
        
    }

    public int getRankId() {
        return rankId;
    }

    public void setRankId(int rankId) {
        this.rankId = rankId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getBattleWin() {
        return battleWin;
    }

    public void setBattleWin(int battleWin) {
        this.battleWin = battleWin;
    }

    public int getBattleLose() {
        return battleLose;
    }

    public void setBattleLose(int battleLose) {
        this.battleLose = battleLose;
    }

    public int getTotalBattle() {
        return totalBattle;
    }

    public void setTotalBattle(int totalBattle) {
        this.totalBattle = totalBattle;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public double getWinRate() {
        return winRate;
    }

    public void setWinRate(double winRate) {
        this.winRate = winRate;
    }
    
    
}
