/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi.general;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import model.Room;
import model.User;

/**
 *
 * @author PC
 */
public interface RoomInterface extends Remote{
    public ArrayList<Room> getRoom(String string) throws RemoteException;
    public Room createdRoom(int id, String name) throws RemoteException;
    public User getUserById(int id) throws RemoteException;
    public ArrayList<User> lstUserRoom(Room room) throws RemoteException;
    
}
