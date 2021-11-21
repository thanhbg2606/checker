/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcp.client.view;

import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Friend;
import model.Group;
import model.Notify;
import model.ObjectWrapper;
import model.User;
import model.UserGroup;
import model.Rank;

import tcp.client.control.ClientCtr;
import rmi.client.control.ClientRMICtr;

/**
 *
 * @author NgocThanh
 */
public class HomeUserFrm extends javax.swing.JFrame implements ActionListener{

    /**
     * Creates new form HomeUserFrm
     */
    private DefaultTableModel modelSfriend;
    private DefaultTableModel modelNotify;
    private DefaultTableModel modelGroup;
    private DefaultTableModel modelRank;
    private DefaultTableModel modelUserGroup;
    private ClientCtr mySocket;
    private ClientRMICtr myRMI;
    private User user;
    private ArrayList<User> listUser;
    private ArrayList<Friend> listFriend;
    private ArrayList<Notify> listNotify;
    private ArrayList<Rank> listRank;
    private ArrayList<Group> listGroup;
    private ArrayList<UserGroup> listUserGroup;
    //Mảng tạm như cockie, tham số 1 sẽ lưu tên group;
    private String[] tmp;
    private HomeUserFrm home;
    
    public HomeUserFrm(){
    }
    public HomeUserFrm(ClientCtr socket, User u, ClientRMICtr rmi){
        super("Home");
        home = this;
        myRMI = rmi;
        mySocket = socket;
        user = u;       
        //listUser = new ArrayList<User>();
        
        initComponents();
        tmp = new String[3];
        listFriend = new ArrayList<>();
        modelSfriend = (DefaultTableModel) tblSfriend.getModel();
        
        listNotify = new ArrayList<>();
        modelNotify = (DefaultTableModel) tblNotify.getModel();
        
        listGroup = new ArrayList<>();
        modelGroup = (DefaultTableModel) tbllistgroup.getModel();
        
        listUserGroup = new ArrayList<>();
        modelUserGroup = (DefaultTableModel) tbllistmember_owner.getModel();
       
        
        
        listRank = new ArrayList<>();
        modelRank = (DefaultTableModel) tblRank.getModel();
        
        
        btnAddFriend.addActionListener(this);
        btnaddgroup.addActionListener(this);
        //test
        btnsearchgroup.addActionListener(this);
        cbbSortRank.addActionListener(this);
        btnGroupNotify.addActionListener(this);
        btn_exit_group.addActionListener(this);
        btnUpdateNotify.addActionListener(this);
        
        
        showTT();
        
        
        tblSfriend.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int column = tblSfriend.getColumnModel().getColumnIndexAtX(e.getX()); // get the coloum of the button
                int row = e.getY() / tblSfriend.getRowHeight(); // get the row of the button
 
                // *Checking the row or column is valid or not
                if (row < tblSfriend.getRowCount() && row >= 0 && column < tblSfriend.getColumnCount() && column >= 0) {
                    //search and delete all existing previous view
                    ObjectWrapper existed = null;
//                    for(ObjectWrapper func: mySocket.getActiveFunction())
//                        if(func.getData() instanceof EditCustomerFrm) {
//                            ((EditCustomerFrm)func.getData()).dispose();
//                            existed = func;
//                        }
//                    if(existed != null)
//                        mySocket.getActiveFunction().remove(existed);
                     
                    //create new instance
//                    (new EditCustomerFrm(mySocket, listClient.get(row))).setVisible(true);
//                    dispose();
//                      new  ComfirmFriend(new Frame(), true);

                if(listFriend.get(row).getStatus().equals("BUSY")){
                    JOptionPane.showMessageDialog(home, "Đối thủ đang bận");
                }
                else if(listFriend.get(row).getStatus().equals("OFFLINE")){
                    JOptionPane.showMessageDialog(home, "Đối thủ đang OFFLINE");
                }    
                else{
                    int n = JOptionPane.showConfirmDialog(home,
                                "Bạn muốn thách đấu với " + listFriend.get(row).getFullName(),
                                "An Inane Question",
                                JOptionPane.YES_NO_OPTION);
			if(n==0){
                            System.out.println("YES");
                            System.out.println("Thách đấu với ID: "+ listFriend.get(row).getFriendid());
                            User friend  = new User();
                            friend.setId(listFriend.get(row).getFriendid());
                            friend.setName(listFriend.get(row).getFullName());
                            User[] users = {user, friend};
                            mySocket.sendData(new ObjectWrapper(ObjectWrapper.SEND_CHALLENGE_FRIEND, users));
                            

                        }
                        else{
                            System.out.println("No");
                        }
                }
                
                }
            }
        });
        
        tblNotify.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int column = tblNotify.getColumnModel().getColumnIndexAtX(e.getX()); // get the coloum of the button
                int row = e.getY() / tblNotify.getRowHeight(); // get the row of the button
 
                // *Checking the row or column is valid or not
                if (row < tblNotify.getRowCount() && row >= 0 && column < tblNotify.getColumnCount() && column >= 0) {
                    //search and delete all existing previous view
                    ObjectWrapper existed = null;
//                    for(ObjectWrapper func: mySocket.getActiveFunction())
//                        if(func.getData() instanceof EditCustomerFrm) {
//                            ((EditCustomerFrm)func.getData()).dispose();
//                            existed = func;
//                        }
//                    if(existed != null)
//                        mySocket.getActiveFunction().remove(existed);
                     
                    //create new instance
//                    (new EditCustomerFrm(mySocket, listClient.get(row))).setVisible(true);
//                    dispose();
//                      new  ComfirmFriend(new Frame(), true);

                 if(listNotify.get(row).getType().equals("addfriend")){
                    int n = JOptionPane.showConfirmDialog(home, 
                            "Xác nhận kết bạn! ",
                                    "An Inane Question",
                                    JOptionPane.YES_NO_OPTION);
                            if(n==0){
                                System.out.println("YES");
                                System.out.println("Kết bạn có ID: "+ listNotify.get(row).getIdsent());
                                User u = new User();
                                u.setId(listNotify.get(row).getIdsent());
                                User[] us = {u, user};
                                mySocket.sendData(new ObjectWrapper(ObjectWrapper.ADD_FRIEND, us));
                                mySocket.getActiveFunction().add(new ObjectWrapper(ObjectWrapper.REPLY_ADD_FRIEND, this));
                                
                                ((DefaultTableModel)tblNotify.getModel()).removeRow(row);
                                myRMI.remoteDeleteNotify(listNotify.get(row).getID());
//                                modelNotify.removeRow(row);
//                                tblNotify.setModel(modelNotify);
                                
                            }
                            else{
                                System.out.println("No");
                                ((DefaultTableModel)tblNotify.getModel()).removeRow(row);
                                myRMI.remoteDeleteNotify(listNotify.get(row).getID());
                            }
                    }
                 else if(listNotify.get(row).getType().equals("joingroup")){
                     //nếu thông báo đó là vào nhóm.--> sẽ hiển thị thông báo xác nhận vào nhóm
                     //Sẽ thêm chứa năng xem thông tin trước khi cho gia nhập
                    int n = JOptionPane.showConfirmDialog(home, 
                            "Chấp nhận cho vào nhóm ",
                                    "An Inane Question",
                                    JOptionPane.YES_NO_OPTION);
                            if(n==0){
                                //Xem
                                User u = new User();
                                u.setId(listNotify.get(row).getIdsent());
                                Group g = new Group();
                                g.setGroupid(Integer.parseInt(tmp[0]));
                                g.setName(tmp[1]);
                                UserGroup ug = new UserGroup();
                                ug.setUser(u);                                
                                ug.setGroup(g);
                                
                                mySocket.sendData(new ObjectWrapper(ObjectWrapper.ADD_USER_GROUP, ug));
                                
                                
                                ((DefaultTableModel)tblNotify.getModel()).removeRow(row);
                                myRMI.remoteDeleteNotify(listNotify.get(row).getID());
//                                modelNotify.removeRow(row);
//                                tblNotify.setModel(modelNotify);
                                
                            }
                            else{
                                System.out.println("No");
                                ((DefaultTableModel)tblNotify.getModel()).removeRow(row);
                                myRMI.remoteDeleteNotify(listNotify.get(row).getID());
                            } 
                 }
                }
            }
        });
        
        tbllistgroup.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int column = tbllistgroup.getColumnModel().getColumnIndexAtX(e.getX()); // get the coloum of the button
                int row = e.getY() / tbllistgroup.getRowHeight(); // get the row of the button
 
                // *Checking the row or column is valid or not
                if (row < tbllistgroup.getRowCount() && row >= 0 && column < tbllistgroup.getColumnCount() && column >= 0) {
                    //search and delete all existing previous view
                    ObjectWrapper existed = null;
//                    for(ObjectWrapper func: mySocket.getActiveFunction())
//                        if(func.getData() instanceof EditCustomerFrm) {
//                            ((EditCustomerFrm)func.getData()).dispose();
//                            existed = func;
//                        }
//                    if(existed != null)
//                        mySocket.getActiveFunction().remove(existed);
                     
                    //create new instance
//                    (new EditCustomerFrm(mySocket, listClient.get(row))).setVisible(true);
//                    dispose();
//                      new  ComfirmFriend(new Frame(), true);

			//tùy chỉnh văn bản cho nút lệnh
			Object[] options = {"Xem thông tin","Xin Gia nhập","Đóng"};
			int n = JOptionPane.showOptionDialog(home, 
                                "Lựa chọn của bạn ?",  
                                "Click a button",
                                JOptionPane.YES_NO_CANCEL_OPTION,
                                JOptionPane.QUESTION_MESSAGE,
                                null,options,options[2]);
			
                        if(n==0){
                            //Xem thông tin thành viên
                            System.out.println("Xem thông tin thành viên");
                            return;
                        }
                        else if(n==1){
                            System.out.println("Gửi yêu cầu gia nhập nhóm");
                            User[] users = {user, listGroup.get(row).getUserCreate()};
                            mySocket.sendData(new ObjectWrapper(ObjectWrapper.SEND_REQUEST_JOIN_GROUP, users));
                        }
                        else{
                            System.out.println("Hủy");
                        }
			
		
	


                }
            }
        });
        
        tbllistmember_owner.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int column = tbllistmember_owner.getColumnModel().getColumnIndexAtX(e.getX()); // get the coloum of the button
                int row = e.getY() / tbllistmember_owner.getRowHeight(); // get the row of the button
 
                // *Checking the row or column is valid or not
                if (row < tbllistmember_owner.getRowCount() && row >= 0 && column < tbllistmember_owner.getColumnCount() && column >= 0) {
                    //search and delete all existing previous view
                    ObjectWrapper existed = null;
//                    for(ObjectWrapper func: mySocket.getActiveFunction())
//                        if(func.getData() instanceof EditCustomerFrm) {
//                            ((EditCustomerFrm)func.getData()).dispose();
//                            existed = func;
//                        }
//                    if(existed != null)
//                        mySocket.getActiveFunction().remove(existed);
                     
                    //create new instance
//                    (new EditCustomerFrm(mySocket, listClient.get(row))).setVisible(true);
//                    dispose();
//                      new  ComfirmFriend(new Frame(), true);

			//tùy chỉnh văn bản cho nút lệnh
			Object[] options = {"Xem thông tin","Xóa Thành viên","Đóng"};
			int n = JOptionPane.showOptionDialog(home, 
                                "Lựa chọn của bạn ?",  
                                "Click a button",
                                JOptionPane.YES_NO_CANCEL_OPTION,
                                JOptionPane.QUESTION_MESSAGE,
                                null,options,options[2]);
			
                        if(n==0){
                            //Xem thông tin thành viên
                            System.out.println("Xem thông tin");
                            return;
                        }
                        else if(n==1){
                            System.out.println("Xóa thành viên");
                            User[] users = {user, listUserGroup.get(row).getUser()};
                            mySocket.sendData(new ObjectWrapper(ObjectWrapper.DELETE_USER_GROUP, users));
                        }
                        else{
                            System.out.println("Hủy");
                        }
			
		
	


                }
            }
        });
        
        
        
        mySocket.getActiveFunction().add(new ObjectWrapper(ObjectWrapper.REPLY_DISPLAY_FRIEND,this));
        mySocket.getActiveFunction().add(new ObjectWrapper(ObjectWrapper.REPLY_SEND_REQUEST_FRIEND_CLIENT, this));
        mySocket.getActiveFunction().add(new ObjectWrapper(ObjectWrapper.REPLY_DISPLAY_NOTIFY, this));
        mySocket.getActiveFunction().add(new ObjectWrapper(ObjectWrapper.REPLY_SEND_CHALLENGE_FRIEND, this));
        mySocket.getActiveFunction().add(new ObjectWrapper(ObjectWrapper.REPLY_ADD_USER_GROUP, this));
        mySocket.getActiveFunction().add(new ObjectWrapper(ObjectWrapper.REPLY_SEND_NOTIFY_GROUP, this));
        
       

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnAddFriend = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtStatus = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        txtSfriend = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSfriend = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        panel_no_group = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbllistgroup = new javax.swing.JTable();
        btnaddgroup = new javax.swing.JButton();
        btnsearchgroup = new javax.swing.JButton();
        panel_member = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tbllistmember_member = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        lbGroup_Member = new javax.swing.JLabel();
        lbNotify_Member = new javax.swing.JLabel();
        btn_exit_group = new javax.swing.JButton();
        panel_owner = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbllistmember_owner = new javax.swing.JTable();
        btnGroupNotify = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        lbGroup_Owner = new javax.swing.JLabel();
        lbNotify_Owner = new javax.swing.JLabel();
        txtNotifyGroup = new javax.swing.JTextField();
        btnUpdateNotify = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblNotify = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblRank = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        cbbSortRank = new javax.swing.JComboBox<>();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnAddFriend.setText("Kết bạn");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("PROFILE");

        jButton1.setText("Tìm trận");

        jLabel3.setText("ID: ");

        jLabel4.setText("Name:");

        jLabel5.setText("Status:");

        txtStatus.setBackground(new java.awt.Color(0, 0, 0));
        txtStatus.setForeground(new java.awt.Color(0, 204, 0));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtID)
                    .addComponent(txtName, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE))
                .addGap(0, 113, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(116, 116, 116)
                .addComponent(jLabel2)
                .addGap(84, 125, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(179, 179, 179))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnAddFriend)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel2)
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddFriend)
                    .addComponent(jButton1))
                .addGap(39, 39, 39))
        );

        jLabel1.setText("Tìm bạn:");

        tblSfriend.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Status"
            }
        ));
        jScrollPane2.setViewportView(tblSfriend);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSfriend, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSfriend, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Friend", jPanel3);

        jLayeredPane1.setLayout(new java.awt.CardLayout());

        panel_no_group.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbllistgroup.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Name", "Position"
            }
        ));
        jScrollPane4.setViewportView(tbllistgroup);

        panel_no_group.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 76, -1, 177));

        btnaddgroup.setText("Tạo group");
        panel_no_group.add(btnaddgroup, new org.netbeans.lib.awtextra.AbsoluteConstraints(53, 20, -1, -1));

        btnsearchgroup.setText("Tìm group");
        panel_no_group.add(btnsearchgroup, new org.netbeans.lib.awtextra.AbsoluteConstraints(269, 20, -1, -1));

        jLayeredPane1.add(panel_no_group, "card2");

        panel_member.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbllistmember_member.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane7.setViewportView(tbllistmember_member);

        panel_member.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, -1, 200));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Group:");
        panel_member.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        lbGroup_Member.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        panel_member.add(lbGroup_Member, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 70, 20));

        lbNotify_Member.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        panel_member.add(lbNotify_Member, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, 260, 20));

        btn_exit_group.setText("Thoát");
        panel_member.add(btn_exit_group, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 260, -1, -1));

        jLayeredPane1.add(panel_member, "card4");

        panel_owner.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbllistmember_owner.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane6.setViewportView(tbllistmember_owner);

        panel_owner.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, -1, 200));

        btnGroupNotify.setText("Thông báo");
        btnGroupNotify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGroupNotifyActionPerformed(evt);
            }
        });
        panel_owner.add(btnGroupNotify, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Group:");
        panel_owner.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        lbGroup_Owner.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        panel_owner.add(lbGroup_Owner, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 70, 20));

        lbNotify_Owner.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        panel_owner.add(lbNotify_Owner, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, 260, 20));
        panel_owner.add(txtNotifyGroup, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 260, 200, -1));

        btnUpdateNotify.setText("Đăng");
        btnUpdateNotify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateNotifyActionPerformed(evt);
            }
        });
        panel_owner.add(btnUpdateNotify, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 260, -1, 20));

        jLayeredPane1.add(panel_owner, "card3");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1)
        );

        jTabbedPane1.addTab("Group", jPanel4);

        tblNotify.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Loại", "Nội Dung"
            }
        ));
        jScrollPane3.setViewportView(tblNotify);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 35, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Notify", jPanel5);

        tblRank.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane5.setViewportView(tblRank);

        jLabel6.setText("Sắp xếp theo: ");

        cbbSortRank.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Điểm", "Tổng số trận", "Tỷ lệ thắng", "Số trận thắng", "Số trận thua", " " }));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(jLabel6)
                .addGap(43, 43, 43)
                .addComponent(cbbSortRank, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cbbSortRank, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 20, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Rank", jPanel6);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGroupNotifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGroupNotifyActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_btnGroupNotifyActionPerformed

    private void btnUpdateNotifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateNotifyActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_btnUpdateNotifyActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddFriend;
    private javax.swing.JButton btnGroupNotify;
    private javax.swing.JButton btnUpdateNotify;
    private javax.swing.JButton btn_exit_group;
    private javax.swing.JButton btnaddgroup;
    private javax.swing.JButton btnsearchgroup;
    private javax.swing.JComboBox<String> cbbSortRank;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lbGroup_Member;
    private javax.swing.JLabel lbGroup_Owner;
    private javax.swing.JLabel lbNotify_Member;
    private javax.swing.JLabel lbNotify_Owner;
    private javax.swing.JPanel panel_member;
    private javax.swing.JPanel panel_no_group;
    private javax.swing.JPanel panel_owner;
    private javax.swing.JTable tblNotify;
    private javax.swing.JTable tblRank;
    private javax.swing.JTable tblSfriend;
    private javax.swing.JTable tbllistgroup;
    private javax.swing.JTable tbllistmember_member;
    private javax.swing.JTable tbllistmember_owner;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtNotifyGroup;
    private javax.swing.JTextField txtSfriend;
    private javax.swing.JLabel txtStatus;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        if((e.getSource() instanceof JButton) && (((JButton)e.getSource()).equals(btnAddFriend))) {
            for(ObjectWrapper func: mySocket.getActiveFunction())
                    if(func.getData() instanceof AddFriendFrm) {
                        ((AddFriendFrm)func.getData()).setVisible(true);
                        return;
                    }
                AddFriendFrm scv = new AddFriendFrm(mySocket, user);
                scv.setVisible(true);
        }
        if((e.getSource() instanceof JButton) && (((JButton)e.getSource()).equals(btnaddgroup))) {
            for(ObjectWrapper func: mySocket.getActiveFunction())
                    if(func.getData() instanceof AddGroup) {
                        ((AddGroup)func.getData()).setVisible(true);
                        return;
                    }
                AddGroup scv = new AddGroup(myRMI, user, this);
                scv.setVisible(true);
        }
        
        
        //Đang test, ----phát triển nếu chưa có group khi vào giao diện hiển thị luôn ds group
        if((e.getSource() instanceof JButton) && (((JButton)e.getSource()).equals(btnsearchgroup))) {
            listGroup = myRMI.remoteGetListAllGroup();
            receivedDataProcessing(new ObjectWrapper(ObjectWrapper.REPLY_GET_ALL_GROUP, listGroup));
            
        }
        
        if((e.getSource() instanceof JButton) && (((JButton)e.getSource()).equals(btn_exit_group))) {
            System.out.println("THoát group");
            
        }
        
        if((e.getSource() instanceof JButton) && (((JButton)e.getSource()).equals(btnGroupNotify))) {
            System.out.println("Click button hiển thị thanh đăng thông báo");
            txtNotifyGroup.setVisible(true);
            btnUpdateNotify.setVisible(true);
            
        }
        
        if((e.getSource() instanceof JButton) && (((JButton)e.getSource()).equals(btnUpdateNotify))) {
            if(txtNotifyGroup.getText().isEmpty()) return;
            else{
                Group g = new Group();
                g.setGroupid(Integer.parseInt(tmp[0]));
                g.setNotify(txtNotifyGroup.getText());

                mySocket.sendData(new ObjectWrapper(ObjectWrapper.SEND_NOTIFY_GROUP, g));

                txtNotifyGroup.setVisible(false);
                btnUpdateNotify.setVisible(false);
            }
            
            
            
        }
        
        
        
        if((e.getSource() instanceof JComboBox) && (((JComboBox)e.getSource()).equals(cbbSortRank))) {
            int index = cbbSortRank.getSelectedIndex();
            listRank = myRMI.getRank(index);
            
            ObjectWrapper ob = new ObjectWrapper();
            ob.setPerformative(ObjectWrapper.REPLY_DISPLAY_RANK);
            ob.setData(listRank);
            
            receivedDataProcessing(ob);
            
        }
        
        
        
    }
    
    //Tùy thuộc vào chức vụ sẽ có giao diện khác nhau
    //Mangr pos_name gom: vị trí+namegroup
    public  void showGDGroup(UserGroup ug){
        if(ug.getPosition().equals("owner")){
            panel_no_group.setVisible(false);
            panel_owner.setVisible(true);
            panel_member.setVisible(false);
            txtNotifyGroup.setVisible(false);
            btnUpdateNotify.setVisible(false);
            lbNotify_Owner.setText(ug.getGroup().getNotify());
            tmp[0] = ug.getGroup().getGroupid()+"";
            tmp[1] = ug.getGroup().getName();
            lbGroup_Owner.setText(tmp[1]);
            displayListUserGroup();
        }
        else if(ug.getPosition().equals("member")){
            panel_no_group.setVisible(false);
            panel_owner.setVisible(false);
            panel_member.setVisible(true);
            lbNotify_Member.setText(ug.getGroup().getNotify());
            tmp[0] = ug.getGroup().getGroupid()+"";
            tmp[1] = ug.getGroup().getName();
            lbGroup_Member.setText(tmp[1]);
            displayListUserGroup();
        }
        else if(ug.getPosition().equals("no-group")){
            panel_no_group.setVisible(true);
            panel_owner.setVisible(false);
            panel_member.setVisible(false);
        }
    }
    
    public UserGroup getPositionGroup(){
        UserGroup ug = new UserGroup();
        ug =  myRMI.getPositionGroup(user.getId());
        return ug;
    }
    
    public ArrayList<UserGroup> getListUserGroup(int groupid){
        return myRMI.remoteGetListUserGroup(groupid);
    }
    
    public void displayListUserGroup(){
        int id = Integer.parseInt(tmp[0]);
        ObjectWrapper ow = new ObjectWrapper(ObjectWrapper.REPLY_GET_ALL_MEMBER, getListUserGroup(id));
        receivedDataProcessing(ow);
    }
    
    public  void showTT(){
        displayInfoPlayer();
        //displayFriend();
        showGDGroup(this.getPositionGroup());
    }
    
    public void updateInfo(ObjectWrapper data){
        listUser = (ArrayList<User>)data.getData();
    }
    
    public  void displayFriend(){
        mySocket.sendData(new ObjectWrapper(ObjectWrapper.DISPLAY_FRIEND, user));
    }
    
    
    public  void displayInfoPlayer(){
        txtID.setText(user.getId() + "");
        txtName.setText(user.getName());
        txtStatus.setText(user.getStatus());
        txtID.setEditable(false);
        txtName.setEditable(false);
        txtStatus.setEnabled(false);
    }
    
    public void receivedDataProcessing(ObjectWrapper data) {
        if(data.getData() instanceof ArrayList<?> 
                && data.getPerformative() == ObjectWrapper.REPLY_DISPLAY_FRIEND) {
            listFriend = (ArrayList<Friend>)data.getData();
            System.out.println(listFriend);
            String[] columnNames = {"Id", "Name", "Status"};
            String[][] value = new String[listFriend.size()][columnNames.length];
            for(int i=0; i<listFriend.size(); i++){
                value[i][0] = listFriend.get(i).getFriendid()+"";
                value[i][1] = listFriend.get(i).getFullName();
                value[i][2] = listFriend.get(i).getStatus();
                
            }
            DefaultTableModel tableModel = new DefaultTableModel(value, columnNames) {
                @Override
                public boolean isCellEditable(int row, int column) {
                   //unable to edit cells
                   return false;
                }
            };
            
            
            for(Friend f : listFriend){
                modelSfriend.addRow(new Object[]{
                    f.getFriendid(),
                    f.getFullName(),
                    f.getStatus()
                });
            }
            tblSfriend.setModel(tableModel);
        }        
        else if (data.getData() instanceof ArrayList<?> 
                && data.getPerformative() == ObjectWrapper.REPLY_DISPLAY_NOTIFY){
            listNotify = (ArrayList<Notify>)data.getData();
            System.out.println(listNotify);
            String[] columnNames = {"Loại", "Nội Dung", "Người gửi"};
            String[][] value = new String[listNotify.size()][columnNames.length];
            for(int i=0; i<listNotify.size(); i++){
                value[i][0] = listNotify.get(i).getType()+"";
                value[i][1] = listNotify.get(i).getContent();
                value[i][2] = listNotify.get(i).getIdsent() + "";
                
            }
            DefaultTableModel tableModel = new DefaultTableModel(value, columnNames) {
                @Override
                public boolean isCellEditable(int row, int column) {
                   //unable to edit cells
                   return false;
                }
            };
            
            
            for(Notify f : listNotify){
                modelNotify.addRow(new Object[]{
                    f.getType(),
                    f.getContent(),
                    f.getIdsent()
                });
            }
            tblNotify.setModel(tableModel);
        }
        
        else if (data.getData() instanceof ArrayList<?> 
                && data.getPerformative() == ObjectWrapper.REPLY_GET_ALL_GROUP){
            listGroup = (ArrayList<Group>)data.getData();
            System.out.println(listGroup);
            String[] columnNames = {"Group Name", "Notify", "Creator"};
            String[][] value = new String[listGroup.size()][columnNames.length];
            for(int i=0; i<listGroup.size(); i++){
                value[i][0] = listGroup.get(i).getName()+"";
                value[i][1] = listGroup.get(i).getNotify();
                value[i][2] = listGroup.get(i).getUserCreate().getName()+ "";
                
            }
            DefaultTableModel tableModel = new DefaultTableModel(value, columnNames) {
                @Override
                public boolean isCellEditable(int row, int column) {
                   //unable to edit cells
                   return false;
                }
            };
            
            
            for(Group f : listGroup){
                modelGroup.addRow(new Object[]{
                    f.getName(),
                    f.getNotify(),
                    f.getUserCreate()
                });
            }
            tbllistgroup.setModel(tableModel);
        }
        
        else if (data.getData() instanceof ArrayList<?> 
                && data.getPerformative() == ObjectWrapper.REPLY_GET_ALL_MEMBER){
            listUserGroup = (ArrayList<UserGroup>)data.getData();
            
            String[] columnNames = {"ID", "Name", "Position"};
            String[][] value = new String[listUserGroup.size()][columnNames.length];
            for(int i=0; i<listUserGroup.size(); i++){
                value[i][0] = listUserGroup.get(i).getUser().getId()+"";
                value[i][1] = listUserGroup.get(i).getUser().getName();
                value[i][2] = listUserGroup.get(i).getPosition();
                
            }
            DefaultTableModel tableModel = new DefaultTableModel(value, columnNames) {
                @Override
                public boolean isCellEditable(int row, int column) {
                   //unable to edit cells
                   return false;
                }
            };
            tbllistmember_owner.setModel(tableModel);
            tbllistmember_member.setModel(tableModel);
        }
        
        else if (data.getData() instanceof User[] 
                && data.getPerformative() == ObjectWrapper.REPLY_SEND_CHALLENGE_FRIEND){
            User[] users = (User[]) data.getData();
            int n = JOptionPane.showConfirmDialog(home, 
                            "Chấp nhận thách đấu " + users[1].getName(),
                                    "An Inane Question",
                                    JOptionPane.YES_NO_OPTION);
                            if(n==0){
                                System.out.println("YES");
//                                mySocket.sendData(new ObjectWrapper(ObjectWrapper.ADD_FRIEND, users));
//                                mySocket.getActiveFunction().add(new ObjectWrapper(ObjectWrapper.REPLY_ADD_FRIEND, this));
                            }
                            else{
                                System.out.println("No");
                            }
        }
        
        else if (data.getData() instanceof ArrayList<?> 
                && data.getPerformative() == ObjectWrapper.REPLY_DISPLAY_RANK){
            listRank = (ArrayList<Rank>)data.getData();
            String[] columnNames = {"Họ Tên", "Số trận Thắng", "Số trận Thua", "Tổng số trận","Tỉ lệ thắng","Điểm"};
            String[][] value = new String[listRank.size()][columnNames.length];
            for(int i=0; i<listRank.size(); i++){
                value[i][0] = listRank.get(i).getFullName();
                value[i][1] = listRank.get(i).getBattleWin()+"";
                value[i][2] = listRank.get(i).getBattleLose() + "";
                value[i][3] = listRank.get(i).getTotalBattle()+"";
                value[i][4] = listRank.get(i).getWinRate()+"%";
                value[i][5] = listRank.get(i).getPoint() + "";
                
            }
            DefaultTableModel tableModel = new DefaultTableModel(value, columnNames) {
                @Override
                public boolean isCellEditable(int row, int column) {
                   //unable to edit cells
                   return false;
                }
            };
            
            
            for(Rank r : listRank){
                modelRank.addRow(new Object[]{
                r.getFullName(),
                r.getBattleWin(),
                r.getBattleLose(),
                r.getTotalBattle(),
                r.getWinRate(),
                r.getPoint()
                });
            }
            tblRank.setModel(tableModel);
        }
        
        else if(data.getData().equals("update") && data.getPerformative() == ObjectWrapper.REPLY_ADD_USER_GROUP){
            showTT();
            System.out.println("Cap nhat lai giao dien group");
        }
 

    }    
}
