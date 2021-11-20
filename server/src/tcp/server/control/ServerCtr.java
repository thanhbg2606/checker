/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcp.server.control;

/**
 *
 * @author hnonstoph
 */
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdbc.dao.NotifyDAO;
 
import jdbc.dao.UserDAO;
import model.Friend;
import model.Group;
import model.IPAddress;
import model.Notify;
import model.ObjectWrapper;
import model.User;
import tcp.server.view.ServerMainFrm;
 
public class ServerCtr {
    private ServerMainFrm view;
    private ServerSocket myServer;
    private ServerListening myListening;
    private ArrayList<ServerProcessing> myProcess;
    private ArrayList<User> listUser;
    private IPAddress myAddress = new IPAddress("localhost",8888);  //default server host and port
     
    public ServerCtr(ServerMainFrm view){
        myProcess = new ArrayList<ServerProcessing>();
        listUser = new ArrayList<User>();
        this.view = view;
        openServer();       
    }
     
    public ServerCtr(ServerMainFrm view, int serverPort){
        myProcess = new ArrayList<ServerProcessing>();
        listUser = new ArrayList<User>();
        this.view = view;
        myAddress.setPort(serverPort);
        openServer();       
    }
     
     
    private void openServer(){
        try {
            myServer = new ServerSocket(myAddress.getPort());
            myListening = new ServerListening();
            myListening.start();
            myAddress.setHost(InetAddress.getLocalHost().getHostAddress());
            view.showServerInfor(myAddress);
            //System.out.println("server started!");
            view.showMessage("TCP server is running at the port " + myAddress.getPort() +"...");
        }catch(Exception e) {
            e.printStackTrace();;
        }
    }
     
    public void stopServer() {
        try {
            for(ServerProcessing sp:myProcess)
                sp.stop();
            myListening.stop();
            myServer.close();
            view.showMessage("TCP server is stopped!");
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
     
    
    
    public void publicClientNumber() {
        ObjectWrapper data = new ObjectWrapper(ObjectWrapper.SERVER_INFORM_CLIENT_NUMBER, myProcess.size());
        for(ServerProcessing sp : myProcess) {
            sp.sendData(data);
        }
    }

    // gửi lại trạng thái bạn bè(online) khi có 1 người bạn online; sẽ gửi đến tất cả bạn bè của họ nếu họ đang online
    public void sendClientFriend(User u){
        UserDAO ud = new UserDAO();
        ArrayList<Friend> lf = new ArrayList<>();
        lf = ud.displayFriend(u);
        for (ServerProcessing sp : myProcess) {
            for (Friend f : lf) {
                if(sp.uo != null && sp.uo.getId() == f.getFriendid()){
                    User user = new User();
                    user = ud.getUserById(f.getFriendid());
                    sp.sendDisplayFriend(user);
                }
           }
        }
        System.out.println("Đang");
    }
    
    //  thêm bạn khi 2 người mới kết bạn ; trong khi họ đang online  
    public void updateDisplayFriend(User[] us){
        for (ServerProcessing ps : myProcess) {
             if(ps.uo.getId() == us[0].getId()){
                ps.sendDisplayFriend(us[0]);
             }
             if(ps.uo.getId() == us[1].getId()){
                ps.sendDisplayFriend(us[1]);
             }
        }
    }
    
    // Lấy DS người đang online trong hệ thống
    public ArrayList<User> updateStatusUser(){
        UserDAO ud = new UserDAO();
        listUser.clear();
        listUser = ud.updateStatusUser();
        System.out.println("Danh sách Client Đang Online: ");
        for (User u : listUser) {
            if(u.getStatus().equals("ONLINE") || u.getStatus().equals("BUSY")){
                System.out.println(u.getId()+ "-" + u.getName());
            }
        }
        return listUser;
    }
    
        //hiển thị thông báo
        public void displayNotify(User user){
            NotifyDAO nd = new NotifyDAO();
            ArrayList<Notify> result = nd.displayNotify(user);
            for (ServerProcessing sp : myProcess){
                if(sp.uo != null && sp.uo.getId() == user.getId()){
                   sp.sendData(new ObjectWrapper(ObjectWrapper.REPLY_DISPLAY_NOTIFY, result)); 
                }
            }     
        }    
    
    
     
    /**
     * The class to listen the connections from client, avoiding the blocking of accept connection
     *
     */
    class ServerListening extends Thread{
         
        public ServerListening() {
            super();
        }
         
        public void run() {
            view.showMessage("server is listening... ");
            try {
                while(true) {
                    Socket clientSocket = myServer.accept();
                    ServerProcessing sp = new ServerProcessing(clientSocket);
                    sp.start();
                    
                    myProcess.add(sp);
                    view.showMessage("Number of client connecting to the server: "
                            + myProcess.size()
                                +"\nClient:" + sp);
                    publicClientNumber();
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
     
    /**
     * The class to treat the requirement from client
     *
     */
        class ServerProcessing extends Thread{
        private Socket mySocket;
        private User uo;
        
        
        //private ObjectInputStream ois;
        //private ObjectOutputStream oos;
         
        public ServerProcessing(Socket s) {
            super();
            mySocket = s;
            
        }

        //------------------------------
        public ServerProcessing(Socket s, User u) {
            super();
            mySocket = s;
            uo = u;
        }

        public Socket getMySocket() {
            return mySocket;
        }

        public void setMySocket(Socket mySocket) {
            this.mySocket = mySocket;
        }

        public User getUo() {
            return uo;
        }

        public void setUo(User uo) {
            this.uo = uo;
        }
        
        public void sendData(Object obj) {
            try {
                
                ObjectOutputStream oos= new ObjectOutputStream(mySocket.getOutputStream());
                oos.writeObject(obj);
            }catch(Exception e) {
                System.out.println("loi gui du lieu tu server");
                e.printStackTrace();
            }
        }
        
        // gửi ds bạn bè để hiển thị đến client
        public void sendDisplayFriend(User user){
            UserDAO ud1 = new UserDAO();
            ArrayList<Friend> result = ud1.displayFriend(user);
            sendData(new ObjectWrapper(ObjectWrapper.REPLY_DISPLAY_FRIEND,result));
        }  
 
        // gửi yêu cầu kết bạn
        public void sendRequestFriend(ObjectWrapper o){
            User[] u = (User[]) o.getData();
            if(o.getPerformative() == ObjectWrapper.SEND_REQUEST_ADD_FRIEND){
                for (ServerProcessing sp : myProcess) {
                    if(sp.uo.getId() == u[1].getId()){
                        displayNotify(u[1]);
      //                  sp.sendData(new ObjectWrapper(ObjectWrapper.REPLY_SEND_REQUEST_FRIEND_CLIENT,u));
                    }
                }
            }
            else if(o.getPerformative() == ObjectWrapper.SEND_CHALLENGE_FRIEND){
                for (ServerProcessing sp : myProcess) {
                    if(sp.uo.getId() == u[1].getId()){                     
                        sp.sendData(new ObjectWrapper(ObjectWrapper.REPLY_SEND_CHALLENGE_FRIEND,u));
                    }
                }
            }
            
        }
        
        
        
        public void run() {
            UserDAO ud = new UserDAO();
            NotifyDAO nd = new NotifyDAO();
            try {
                while(true) {
                    ObjectInputStream ois = new ObjectInputStream(mySocket.getInputStream());
                    //ObjectOutputStream oos= new ObjectOutputStream(mySocket.getOutputStream());
                    Object o = ois.readObject();
                    
                    if(o instanceof ObjectWrapper){
                        ObjectWrapper data = (ObjectWrapper)o;
                        
                        switch(data.getPerformative()) {
                        case ObjectWrapper.LOGIN_USER -> {
                            User user = (User)data.getData();                            
                            if(ud.checkLogin(user) && ud.updateStatusUser(user, 1)){
                                this.setUo(user);
                                System.out.println(this.mySocket.getChannel() + "-" + this.uo.getName());
                                
                                sendData(new ObjectWrapper(ObjectWrapper.REPLY_LOGIN_USER, user));
                                updateStatusUser(); // cập nhật ds những người online trên server
                                sendDisplayFriend(user);
                                displayNotify(user);
                                
                                sendClientFriend(user);
                                view.showMessage("Đã gửi data thành công login tới client: " + data.to_String());                                
                            }
                            else{
                                sendData(new ObjectWrapper(ObjectWrapper.REPLY_LOGIN_USER,"false"));
                                view.showMessage("Đã gửi data thất bại login tới client: " + data.to_String());
                            }        
                            break;
                        }
                        case ObjectWrapper.REGISTER_USER -> {
                            User user1 = (User)data.getData();
                            if(ud.addUser(user1)){
                                sendData(new ObjectWrapper(ObjectWrapper.REPLY_REGISTER_USER,"ok"));
                                view.showMessage("Đã gửi data thành công register tới client: " + data.to_String());

                            }
                            else{
                                sendData(new ObjectWrapper(ObjectWrapper.REPLY_REGISTER_USER,"false"));
                                view.showMessage("Đã gửi data thất bại register tới client: " + data.to_String());                                
                            }
                            break;
                        }
                        case ObjectWrapper.SEARCH_USER -> {
                            String key = data.getData().toString();
                            ArrayList<User> result = ud.searchUser(key);
                            sendData(new ObjectWrapper(ObjectWrapper.REPLY_SEARCH_USER,result));
                            view.showMessage("Đã gửi data search friend tới client: " + data.to_String());

                            break;
                        }
                        
                        case ObjectWrapper.ADD_FRIEND -> {
                            User[] users = (User[])data.getData();
                            if(ud.addFriend(users[0], users[1])){                               
                                updateDisplayFriend(users);
                                nd.updateStatusNotify(users[1]);
                                view.showMessage("Đã gửi data thành công kết bạn tới client: " + data.to_String());

                            }        
                            break;
                        }    
                        case ObjectWrapper.SEND_REQUEST_ADD_FRIEND ->{
                            User[] users = (User[])data.getData();
                            Notify n = new Notify();
                            n.setType("addfriend");
                            n.setContent(users[0].getName() + " muốn kết bạn");
                            n.setStatus(0);
                            n.setIdsent(users[0].getId());
                            nd.addNotify(n, users);
                            sendRequestFriend(data);                                           
                            break;
                        } 
                        case ObjectWrapper.SEND_CHALLENGE_FRIEND -> {
                            User[] users = (User[])data.getData();
                             sendRequestFriend(data);
               
                            break;
                        }
                        case ObjectWrapper.ADD_GROUP ->{
                            Group g = (Group)data.getData();
                        }
                        }
 
                    }
                    //ois.reset();
                    //oos.reset();
                }
            }catch (EOFException | SocketException e) {   
                e.printStackTrace();
                
                ud.updateStatusUser(this.uo, 0);
                sendClientFriend(this.uo);
                System.out.println("Client: "+this.uo.getName()+ "Đăng xuất!!!");
                myProcess.remove(this);                
                view.showMessage("Number of client connecting to the server: " + myProcess.size());
                publicClientNumber();
                try {
                    mySocket.close();
                }catch(Exception ex) {
                    ex.printStackTrace();
                }
                this.stop();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        
       
    
    }
}