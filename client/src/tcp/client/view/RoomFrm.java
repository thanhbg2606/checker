/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcp.client.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Friend;
import model.ObjectWrapper;
import model.Room;
import model.User;
import rmi.client.control.ClientRMICtr;
import tcp.client.control.ClientCtr;

/**
 *
 * @author PC
 */
public class RoomFrm extends javax.swing.JDialog {

    /**
     * Creates new form RoomFrm
     */
    private ClientRMICtr myRMI;
    private DefaultTableModel modelRoom;
    private ClientCtr mySocket;
    private RoomFrm room;
    private User user;
    private Room r;
    private ArrayList<Friend> listFriend;
    private DefaultTableModel modelFriend;
    private ArrayList<User> lstUserPlayer;
    private DefaultTableModel modelUserPlayer;
    private RoomFrm home;
    public RoomFrm(ClientRMICtr rmi, ClientCtr socket, User u, Room ro){
        home = this;
        room = this;
        myRMI = rmi;
        mySocket = socket;     
        user = u;
        r = ro;
        
        
        initComponents();
        
        listFriend = new ArrayList<>();
        modelFriend = (DefaultTableModel) tblFriend.getModel();
        
        lstUserPlayer = new ArrayList<>();
        modelUserPlayer = (DefaultTableModel) tblPlayer.getModel();
        
        mySocket.getActiveFunction().add(new ObjectWrapper(ObjectWrapper.REPLY_DISPLAY_ROOM,this));

        textRoomCode.setText(r.getRoomCode()+"");
        txtRoomName.setText(r.getRoomName());
        updateGD();
        
        tblFriend.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int column = tblFriend.getColumnModel().getColumnIndexAtX(e.getX()); // get the coloum of the button
                int row = e.getY() / tblFriend.getRowHeight(); // get the row of the button
 
                // *Checking the row or column is valid or not
                if (row < tblFriend.getRowCount() && row >= 0 && column < tblFriend.getColumnCount() && column >= 0) {
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
                            Room room = new Room();
                            room.setRoomCode(r.getRoomCode());
                            room.setPlayerId(listFriend.get(row).getFriendid());
                            room.setRoomMasterId(user.getId());
                            mySocket.sendData(new ObjectWrapper(ObjectWrapper.SEND_CHALLENGE_FRIEND, room));
                            

                        }
                        else{
                            System.out.println("No");
                        }
                }
                
                }
            }
        });
    }
    
    
    public RoomFrm(ClientRMICtr rmi, ClientCtr socket, User u) {
        home = this;
        room = this;
        myRMI = rmi;
        mySocket = socket;     
        user = u;
        
        
        initComponents();
        
        listFriend = new ArrayList<>();
        modelFriend = (DefaultTableModel) tblFriend.getModel();
        
        lstUserPlayer = new ArrayList<>();
        modelUserPlayer = (DefaultTableModel) tblPlayer.getModel();
        
//        btnAddRoom.addActionListener(this);
//        btnSearchRoom.addActionListener(this);
        
        mySocket.getActiveFunction().add(new ObjectWrapper(ObjectWrapper.REPLY_DISPLAY_ROOM,this));
        mySocket.getActiveFunction().add(new ObjectWrapper(ObjectWrapper.REPLY_UPDATE_GD_PLAYER, this));
        showRoom(u.getId(), u.getName());
        updateGD();
        
        tblFriend.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int column = tblFriend.getColumnModel().getColumnIndexAtX(e.getX()); // get the coloum of the button
                int row = e.getY() / tblFriend.getRowHeight(); // get the row of the button
 
                // *Checking the row or column is valid or not
                if (row < tblFriend.getRowCount() && row >= 0 && column < tblFriend.getColumnCount() && column >= 0) {
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
                            Room room = new Room();
                            room.setRoomCode(r.getRoomCode());
                            room.setRoomName(r.getRoomName());
                            room.setPlayerId(listFriend.get(row).getFriendid());
                            room.setRoomMasterId(user.getId());
                            mySocket.sendData(new ObjectWrapper(ObjectWrapper.SEND_CHALLENGE_FRIEND, room));
                            

                        }
                        else{
                            System.out.println("No");
                        }
                }
                
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtRoomName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        textRoomCode = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPlayer = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblFriend = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblGroup = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Tên phòng :");

        jLabel3.setText("Mã Phòng :");

        textRoomCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textRoomCodeActionPerformed(evt);
            }
        });

        jButton1.setText("Bắt đầu");

        jButton2.setText("Kết thúc");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 247, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 372, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Trò chuyện", jPanel2);

        tblPlayer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên", "Trạng thái", "Điểm"
            }
        ));
        jScrollPane1.setViewportView(tblPlayer);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Người chơi", jPanel3);

        tblFriend.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên", "Trạng thái"
            }
        ));
        jScrollPane2.setViewportView(tblFriend);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Bạn bè", jPanel4);

        tblGroup.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên", "Trạng thái", "Điểm"
            }
        ));
        jScrollPane3.setViewportView(tblGroup);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Hội quán", jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtRoomName, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addGap(51, 51, 51)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textRoomCode, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(119, 119, 119)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(txtRoomName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(textRoomCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(24, 24, 24))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textRoomCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textRoomCodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textRoomCodeActionPerformed

    
    public void showRoom(int id, String name){
        
        try{
            r = myRMI.createdRoom(id, name);
            System.out.println(r.getRoomCode());
            textRoomCode.setText(r.getRoomCode()+"");
            txtRoomName.setText(r.getRoomName());
            
            
             mySocket.sendData(new ObjectWrapper(ObjectWrapper.DISPLAY_ROOM, user));
        }catch(Exception ex){
            
        }
        
    }
    
    public void updateGD(){
        listPlayer();
    }
    
    public void listPlayer(){
        ArrayList<User> lstUser = myRMI.RemoteLstUserRoom(r);
        ObjectWrapper ow = new ObjectWrapper(ObjectWrapper.REPLY_GET_LIST_PLAYER, lstUser);
        receivedDataProcessing(ow);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tblFriend;
    private javax.swing.JTable tblGroup;
    private javax.swing.JTable tblPlayer;
    private javax.swing.JTextField textRoomCode;
    private javax.swing.JTextField txtRoomName;
    // End of variables declaration//GEN-END:variables
    
    public void receivedDataProcessing(ObjectWrapper data) {
        
        if (data.getData() instanceof ArrayList<?> 
                && data.getPerformative() == ObjectWrapper.REPLY_DISPLAY_ROOM){
            listFriend = (ArrayList<Friend>)data.getData();
            String[] columnNames = {"Tên", "Trạng thái"};
            String[][] value = new String[listFriend.size()][columnNames.length];
            for(int i=0; i<listFriend.size(); i++){
                value[i][0] = listFriend.get(i).getFullName()+"";
                value[i][1] = listFriend.get(i).getStatus();
                
            }
            DefaultTableModel tableModel = new DefaultTableModel(value, columnNames) {
                @Override
                public boolean isCellEditable(int row, int column) {
                   //unable to edit cells
                   return false;
                }
            };
            
            
            for(Friend f : listFriend){
                modelFriend.addRow(new Object[]{
                f.getFullName(),
                f.getStatus()
                
                });
            }
            tblFriend.setModel(tableModel);
        }
        
        if (data.getData() instanceof ArrayList<?> 
                && data.getPerformative() == ObjectWrapper.REPLY_GET_LIST_PLAYER){
            lstUserPlayer = (ArrayList<User>)data.getData();
            String[] columnNames = {"Tên", "Trạng thái"};
            String[][] value = new String[lstUserPlayer.size()][columnNames.length];
            for(int i=0; i<lstUserPlayer.size(); i++){
                value[i][0] = lstUserPlayer.get(i).getName()+"";
                value[i][1] = lstUserPlayer.get(i).getStatus();
                
            }
            DefaultTableModel tableModel = new DefaultTableModel(value, columnNames) {
                @Override
                public boolean isCellEditable(int row, int column) {
                   //unable to edit cells
                   return false;
                }
            };
            
            
            for(User f : lstUserPlayer){
                modelUserPlayer.addRow(new Object[]{
                f.getName(),
                f.getStatus()
                
                });
            }
            tblPlayer.setModel(tableModel);
        }
        
        else if(data.getData().equals("updateGD") && data.getPerformative() == ObjectWrapper.REPLY_UPDATE_GD_PLAYER){
            updateGD();
            System.out.println("Cap nhat lai giao dien room");
        }
    }
}
