/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcp.client.control;

/**
 *
 * @author hnonstoph
 */
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
 
import model.IPAddress;
import model.ObjectWrapper;
import model.User;
import tcp.client.view.AddFriendFrm;
import tcp.client.view.LoginFrm;
import tcp.client.view.ClientMainFrm;
import tcp.client.view.HomeUserFrm;
import tcp.client.view.Login;
import tcp.client.view.RegUserFrm;
 
 
public class ClientCtr {
    private Socket mySocket;
    private ClientMainFrm view;
    private ClientListening myListening;                            // thread to listen the data from the server
    private ArrayList<ObjectWrapper> myFunction;                  // list of active client functions
    private IPAddress serverAddress = new IPAddress("localhost",8888);  // default server host and port
     
    public ClientCtr(ClientMainFrm view){
        super();
        this.view = view;
        myFunction = new ArrayList<ObjectWrapper>();  
    }
     
    public ClientCtr(ClientMainFrm view, IPAddress serverAddr) {
        super();
        this.view = view;
        this.serverAddress = serverAddr;
        myFunction = new ArrayList<ObjectWrapper>();
    }
 
 
 
    public boolean openConnection(){        
        try {
            mySocket = new Socket(serverAddress.getHost(), serverAddress.getPort());  
            myListening = new ClientListening();
            myListening.start();
            view.showMessage("Connected to the server at host: " + serverAddress.getHost() + ", port: " + serverAddress.getPort());
        } catch (Exception e) {
            //e.printStackTrace();
            view.showMessage("Error when connecting to the server!");
            return false;
        }
        return true;
    }
     
    public boolean sendData(Object obj){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(mySocket.getOutputStream());
            oos.writeObject(obj);  
            view.showMessage("Đã gửi data tới server!!!");
             
        } catch (Exception e) {
            //e.printStackTrace();
            view.showMessage("Error when sending data to the server!");
            return false;
        }
        return true;
    }
     
    /*
    public Object receiveData(){
        Object result = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(mySocket.getInputStream());
            result = ois.readObject();
        } catch (Exception e) {
            //e.printStackTrace();
            view.showMessage("Error when receiving data from the server!");
            return null;
        }
        return result;
    }*/
     
    public boolean closeConnection(){
         try {
             if(myListening != null)
                 myListening.stop();
             if(mySocket !=null) {
                 mySocket.close();
                 view.showMessage("Disconnected from the server!");
             }
            myFunction.clear();             
         } catch (Exception e) {
             //e.printStackTrace();
             view.showMessage("Error when disconnecting from the server!");
             return false;
         }
         return true;
    }
     
     
     
    public ArrayList<ObjectWrapper> getActiveFunction() {
        return myFunction;
    }
 
 
    class ClientListening extends Thread{
         
        public ClientListening() {
            super();
        }
         
        public void run() {
            try {
                while(true) {
                //Object obj = null;    
                ObjectInputStream ois = new ObjectInputStream(mySocket.getInputStream());
                Object obj = ois.readObject();
                if(obj instanceof ObjectWrapper) {
                    ObjectWrapper data = (ObjectWrapper)obj;
                    
                    view.showMessage(data.to_String());
                    if(data.getPerformative() == ObjectWrapper.SERVER_INFORM_CLIENT_NUMBER)
                        view.showMessage("Number of client connecting to the server: " + data.getData());
//                    else if(data.getPerformative() == ObjectWrapper.UPDATE_STATUS_USER){
//                        HomeUserFrm huf = new HomeUserFrm();
//                                    huf.updateInfo(data);
//                                   
//                                    view.showMessage("Update User");
//                    }
                    else {
                        //dung for each --> ko dung remote
                        for(int i=0; i<myFunction.size(); i++){
                            ObjectWrapper fto = myFunction.get(i);
                            if(fto.getPerformative() == data.getPerformative()) {
                                switch(data.getPerformative()) {
                                case ObjectWrapper.REPLY_LOGIN_USER -> {
                                    //--Thay giao diện
                                    LoginFrm loginView = (LoginFrm)fto.getData();
                                    loginView.receivedDataProcessing(data);
                                   // myFunction.remove(fto);
                                    view.showMessage("Login");                                   
                                }
                                case ObjectWrapper.REPLY_REGISTER_USER -> {
                                    RegUserFrm ruf = (RegUserFrm)fto.getData();
                                    //System.out.println("Phản hồi đăng ký");
                                    ruf.receivedDataProcessing(data);
                                    //myFunction.remove(fto);                                    
                                    view.showMessage("Register");
                                }
                                case ObjectWrapper.REPLY_SEARCH_USER -> {
                                    
                                    AddFriendFrm af = (AddFriendFrm)fto.getData();
                                    af.receivedDataProcessing(data);
                                    //myFunction.remove(fto);
                                    view.showMessage("Search Friend");
                                }
                                case ObjectWrapper.REPLY_DISPLAY_FRIEND -> {
                                    HomeUserFrm huf = (HomeUserFrm)fto.getData();
                                    huf.receivedDataProcessing(data);
                                    
                                    view.showMessage("Display Friend");
                                }
                                case ObjectWrapper.REPLY_SEND_REQUEST_FRIEND_CLIENT ->{
                                    HomeUserFrm huf = (HomeUserFrm)fto.getData();
                                    huf.receivedDataProcessing(data); 
                                    view.showMessage("Request Friend");
                                }
                                
                                case ObjectWrapper.REPLY_ADD_FRIEND ->{
                                    HomeUserFrm huf = (HomeUserFrm)fto.getData();
                                    huf.receivedDataProcessing(data); 
                                    //myFunction.remove(fto);
                                    view.showMessage("Kết bạn thành công");
                                }
                                
                                case ObjectWrapper.REPLY_DISPLAY_NOTIFY ->{
                                    HomeUserFrm huf = (HomeUserFrm)fto.getData();
                                    huf.receivedDataProcessing(data);
                                    System.out.println("Hien thi thong bao");
                                }
                                
                                case ObjectWrapper.REPLY_SEND_CHALLENGE_FRIEND ->{
                                    HomeUserFrm huf = (HomeUserFrm)fto.getData();
                                    huf.receivedDataProcessing(data);
                                    System.out.println("Thách đấu");
                                }
                                
                                case ObjectWrapper.REPLY_ADD_USER_GROUP ->{
                                    HomeUserFrm huf = (HomeUserFrm)fto.getData();
                                    huf.receivedDataProcessing(data);
                                    System.out.println("Cập nhật lại khi giao diện khi gia nhập group");
                                }
                                 
                                
                            }    
                                view.showMessage("Received an object: " + data.getPerformative());
                            }
                        
                        }
                    }
                }
                }    
            }
            catch (Exception e) {
                e.printStackTrace();
                view.showMessage("Error when receiving data from the server! and reset all function client");
                view.resetClient();
            }
        }
    }
}