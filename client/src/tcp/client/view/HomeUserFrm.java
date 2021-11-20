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
import model.Notify;
import model.ObjectWrapper;
import model.Rank;
import model.User;

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
    private DefaultTableModel modelRank;
    private ClientCtr mySocket;
    private ClientRMICtr myRMI;
    private User user;
    private ArrayList<User> listUser;
    private ArrayList<Friend> listFriend;
    private ArrayList<Notify> listNotify;
    private ArrayList<Rank> listRank;
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
        listFriend = new ArrayList<>();
        modelSfriend = (DefaultTableModel) tblSfriend.getModel();
        
        listNotify = new ArrayList<>();
        modelNotify = (DefaultTableModel) tblNotify.getModel();
        
        
        btnAddFriend.addActionListener(this);
        btnaddgroup.addActionListener(this);
        
        listRank = new ArrayList<>();
        modelRank = (DefaultTableModel) tblRank.getModel();
        cbbSortRank.addActionListener(this);
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
//                                modelNotify.removeRow(row);
//                                tblNotify.setModel(modelNotify);
                                
                            }
                            else{
                                System.out.println("No");
                                ((DefaultTableModel)tblNotify.getModel()).removeRow(row);
                            }
                    }
                 else{
                     //nếu thông báo đó là vào nhóm.--> sẽ hiển thị thông báo xác nhận vào nhóm
                 }
                }
            }
        });
        
        
        
        mySocket.getActiveFunction().add(new ObjectWrapper(ObjectWrapper.REPLY_DISPLAY_FRIEND,this));
        mySocket.getActiveFunction().add(new ObjectWrapper(ObjectWrapper.REPLY_SEND_REQUEST_FRIEND_CLIENT, this));
        mySocket.getActiveFunction().add(new ObjectWrapper(ObjectWrapper.REPLY_DISPLAY_NOTIFY, this));
        mySocket.getActiveFunction().add(new ObjectWrapper(ObjectWrapper.REPLY_SEND_CHALLENGE_FRIEND, this));

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
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
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
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        btnaddgroup = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblNotify = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblRank = new javax.swing.JTable();
        cbbSortRank = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();

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

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jLabel7.setText("jLabel7");

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

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Name", "Position"
            }
        ));
        jScrollPane4.setViewportView(jTable2);

        btnaddgroup.setText("Tạo group");

        jButton2.setText("Tìm group");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(btnaddgroup)
                .addGap(38, 38, 38)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnaddgroup)
                    .addComponent(jButton2))
                .addGap(29, 29, 29)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE)
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
                "Họ Tên", "Số trận Thắng", "Số trận Thua", "Tổng số trận", "Tỉ lệ thắng", "Điểm"
            }
        ));
        jScrollPane5.setViewportView(tblRank);

        cbbSortRank.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Điểm", "Tổng số trận", "Tỷ lệ thắng", "Số trận thắng", "Số trận thua" }));
        cbbSortRank.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbSortRankActionPerformed(evt);
            }
        });

        jLabel6.setText("Sắp xếp theo :");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(jLabel6)
                .addGap(54, 54, 54)
                .addComponent(cbbSortRank, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbSortRank, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Rank", jPanel7);

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

    private void cbbSortRankActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbSortRankActionPerformed
        // TODO add your handling code here:
         int index = cbbSortRank.getSelectedIndex();
            listRank = myRMI.getRank(index);
            
            ObjectWrapper ob = new ObjectWrapper();
            ob.setPerformative(ObjectWrapper.REPLY_DISPLAY_RANK);
            ob.setData(listRank);
            
            receivedDataProcessing(ob);
    }//GEN-LAST:event_cbbSortRankActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddFriend;
    private javax.swing.JButton btnaddgroup;
    private javax.swing.JComboBox<String> cbbSortRank;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
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
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable tblNotify;
    private javax.swing.JTable tblRank;
    private javax.swing.JTable tblSfriend;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtName;
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
                AddGroup scv = new AddGroup(myRMI, user);
                scv.setVisible(true);
        }
        
        
        
        
    }
    
    private void showTT(){
        displayInfoPlayer();
        //displayFriend();
    }
    
    public void updateInfo(ObjectWrapper data){
        listUser = (ArrayList<User>)data.getData();
    }
    
    private void displayFriend(){
        mySocket.sendData(new ObjectWrapper(ObjectWrapper.DISPLAY_FRIEND, user));
    }
    
    
    private void displayInfoPlayer(){
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
                && data.getPerformative() == ObjectWrapper.REPLY_DISPLAY_RANK){
            listRank = (ArrayList<Rank>)data.getData();
            String[] columnNames = {"Họ Tên", "Số trận Thắng", "Số trận Thua", "Tổng số trận","Tỉ lệ thắng","Điểm"};
            String[][] value = new String[listRank.size()][columnNames.length];
            for(int i=0; i<listRank.size(); i++){
                value[i][0] = listRank.get(i).getFullName();
                value[i][1] = listRank.get(i).getBattleWin()+"";
                value[i][2] = listRank.get(i).getBattleLose() + "";
                value[i][4] = listRank.get(i).getTotalBattle()+"";
                value[i][5] = listRank.get(i).getWinRate()+"%";
                value[i][6] = listRank.get(i).getPoint() + "";
                
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
 

    }    
}
