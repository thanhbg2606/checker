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
public class Room implements Serializable{
    private int roomId;
    private int roomCode;
    private String roomName;
    private String description;
    private int roomMasterId;
    private int playerId;
    private int quantity;
    
    public Room() {
        
    }

    public Room(int roomId, int roomCode, String roomName, String description, int roomMasterId, int playerId, int quantity) {
        this.roomId = roomId;
        this.roomCode = roomCode;
        this.roomName = roomName;
        this.description = description;
        this.roomMasterId = roomMasterId;
        this.playerId = playerId;
        this.quantity = quantity;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(int roomCode) {
        this.roomCode = roomCode;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRoomMasterId() {
        return roomMasterId;
    }

    public void setRoomMasterId(int roomMasterId) {
        this.roomMasterId = roomMasterId;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    
}
