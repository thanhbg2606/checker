/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi.general;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import model.Rank;
/**
 *
 * @author PC
 */
public interface RankInterface extends Remote{
    public ArrayList<Rank> getRank(int by) throws RemoteException;
}
