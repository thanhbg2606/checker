/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi.general;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import model.UserGroup;


/**
 *
 * @author NgocThanh
 */
public interface UserGroupInterface extends Remote{
    public boolean addUserGroup(UserGroup ug, int position)throws RemoteException;
    public boolean deleteUserGroup(int id) throws RemoteException;
    public ArrayList<UserGroup> getListUserGroup(int groupid) throws RemoteException;
    public UserGroup getPositionGroup(int id) throws RemoteException;
}
