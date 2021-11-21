package rmi.client.control;
 
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

 

import model.IPAddress;
import model.Group;
import model.User;
import model.UserGroup;
import model.Rank;

import tcp.client.view.ClientMainFrm;
import rmi.general.GroupInterface;
import rmi.general.NotifyInterface;
import rmi.general.UserGroupInterface;
import rmi.general.RankInterface;
 
 
public class ClientRMICtr {
    private ClientMainFrm view;
    private GroupInterface groupRO;
    private NotifyInterface notifyRO;
    private UserGroupInterface usergroupRO;
    private RankInterface rankRO;
    private IPAddress serverAddress = new IPAddress("localhost", 9999); //default server address
    private String rmiService = "rmiServer";                            //default server service key
     
    public ClientRMICtr(ClientMainFrm view){
        this.view = view;
    }
         
    public ClientRMICtr(ClientMainFrm view, String serverHost, int serverPort, String service){
        this.view = view;
        serverAddress.setHost(serverHost);
        serverAddress.setPort(serverPort);
        rmiService = service;
    }   
     
    public boolean init(){
        try{
            // get the registry
            Registry registry = LocateRegistry.getRegistry(serverAddress.getHost(), serverAddress.getPort());
            // lookup the remote objects
            groupRO = (GroupInterface)(registry.lookup(rmiService));
            notifyRO = (NotifyInterface)(registry.lookup(rmiService));
            usergroupRO = (UserGroupInterface)(registry.lookup(rmiService));
            rankRO = (RankInterface)(registry.lookup(rmiService));
             
            view.showMessage("Found the remote objects at the host: " + serverAddress.getHost() + ", port: " + serverAddress.getPort());
        }catch(Exception e){
            e.printStackTrace();
            view.showMessage("Error to lookup the remote objects!");
            return false;
        }
        return true;
    }
    
    public boolean remoteCheckDuplicateNameGroup(String key){
        try {
            if(groupRO.checkDuplicateNameGroup(key)){
                System.out.println("Tên group đã tồn tại");
                return true;
            }    
            else{
                
                return false;
                
            }
                
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    //Chú ý hàm null
    public ArrayList<Group> remoteSearchGroup(String key){
        ArrayList<Group> res = new ArrayList<>();
        try {
            res = groupRO.searchGroup(key);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public boolean remoteAddGroup(Group group){
        try {
            if(groupRO.addGroup(group))
                return true;
            else
                return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public ArrayList<Group> remoteGetListAllGroup(){
        ArrayList<Group> res = new ArrayList<>();
        try {
            res = groupRO.getListAllGroup();
            System.out.println("Danh sách tất cả group");
            return res;
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public int remoteGetGroupidByGroupName(String name){
        try {
            return groupRO.getGroupidByGroupName(name);
        } catch (Exception e) {
            System.out.println("Lấy ig group thất bại");
            return 0;
        }
    }
    
    public boolean remoteAddUserGroup(UserGroup ug, int position){
        try {
            if(usergroupRO.addUserGroup(ug, position));
            return true;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public UserGroup getPositionGroup(int id){
        try {
            return usergroupRO.getPositionGroup(id);
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public ArrayList<UserGroup> remoteGetListUserGroup(int groupid){
        try {
            return usergroupRO.getListUserGroup(groupid);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
    public boolean remoteDeleteNotify(int id){
        try {
            if(notifyRO.deleteNotify(id)){
                System.out.println("Xóa thông báo");
                return true;
            }    
            else
                return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public ArrayList<Rank> getRank(int by){
        ArrayList<Rank> res = new ArrayList<Rank>();
        try {
            
            res = rankRO.getRank(by);
//            
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("sao lai sai");
            return null;
        }
    }
    
    public boolean remoteDeleteUserGroup(int id){
        try {
            if(usergroupRO.deleteUserGroup(id)){
                System.out.println("Xóa thông báo");
                return true;
            }    
            else
                return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
}