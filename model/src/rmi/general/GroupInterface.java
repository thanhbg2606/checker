/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi.general;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import model.Group;

/**
 *
 * @author NgocThanh
 */
public interface GroupInterface extends Remote{
    public boolean checkDuplicateNameGroup(String key) throws RemoteException;
    public boolean addGroup(Group g) throws RemoteException;
    public ArrayList<Group> searchGroup(String key) throws RemoteException;
}
