package rmi.server.control;
 
 
import java.net.InetAddress;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ExportException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import jdbc.dao.GroupDAO;
import jdbc.dao.NotifyDAO;
import jdbc.dao.RankDAO;
 
import jdbc.dao.UserDAO;
import model.Friend;
import model.Group;
import model.IPAddress;
import model.Notify;
import model.Rank;
import model.User;
import rmi.general.GroupInterface;
import rmi.general.RankInterface;
import tcp.server.view.ServerMainFrm;
 
 
public class ServerRMICtr extends UnicastRemoteObject implements GroupInterface,RankInterface{
    private IPAddress myAddress = new IPAddress("localhost", 9999);     // default server host/port
    private Registry registry;
    private ServerMainFrm view;
    private String rmiService = "rmiServer";    // default rmi service key
     
    public ServerRMICtr(ServerMainFrm view) throws RemoteException{
        this.view = view;   
    }
     
    public ServerRMICtr(ServerMainFrm view, int port, String service) throws RemoteException{
        this.view = view;   
        myAddress.setPort(port);
        this.rmiService = service;
    }
     
    public void start() throws RemoteException{
        // registry this to the localhost
        try{
            try {
                //create new one
                registry = LocateRegistry.createRegistry(myAddress.getPort());
            }catch(ExportException e) {//the Registry exists, get it
                registry = LocateRegistry.getRegistry(myAddress.getPort());
            }
            registry.rebind(rmiService, this);
            //myAddress.setHost(InetAddress.getLocalHost().getHostAddress());
//            view.showServerInfo(myAddress, rmiService);
            view.showMessage("The RIM has registered the service key: " + rmiService + ", at the port: " + myAddress.getPort());
        }catch(RemoteException e){
            throw e;
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
     
    public void stop() throws RemoteException{
        // unbind the service
        try{
            if(registry != null) {
                registry.unbind(rmiService);
                UnicastRemoteObject.unexportObject(this,true);
            }
            view.showMessage("The RIM has unbinded the service key: " + rmiService + ", at the port: " + myAddress.getPort());
        }catch(RemoteException e){
            throw e;
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean checkDuplicateNameGroup(String key) throws RemoteException {
        System.out.println("kiểm tra tên group đã tồn tại chưa");
        return new GroupDAO().checkDuplicateNameGroup(key);
    }

    @Override
    public boolean addGroup(Group g) throws RemoteException {
        return new GroupDAO().addGroup(g);
    }

    @Override
    public ArrayList<Group> searchGroup(String key) throws RemoteException {
        return new GroupDAO().searchGroup(key);
    }

    @Override
    public ArrayList<Rank> getRank(int by) throws RemoteException {
       return new RankDAO().getRank(by);
    }

    
}