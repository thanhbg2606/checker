package rmi.client.control;
 
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import model.Group;
 

import model.IPAddress;
import model.Rank;
import model.User;
import tcp.client.view.ClientMainFrm;
import rmi.general.GroupInterface;
import rmi.general.RankInterface;
 
 
public class ClientRMICtr {
    private ClientMainFrm view;
    private GroupInterface groupRO;
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
}