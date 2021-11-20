/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcp.client.view;

/**
 *
 * @author hnonstoph
 */
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import model.ObjectWrapper;
 
import model.User;
import rmi.client.control.ClientRMICtr;
import tcp.client.control.ClientCtr;
 
public class LoginFrm extends JFrame implements ActionListener{
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private ClientCtr mySocket;
    private User u;
    private ClientRMICtr myRMI;
    
    public LoginFrm(ClientCtr socket,ClientRMICtr rmi){
        super("TCP Login MVC");     
        mySocket = socket;
        
        myRMI = rmi;
        mySocket = socket;
        this.setLocationRelativeTo(null);

        
        txtUsername = new JTextField(15);
        txtPassword = new JPasswordField(15);
        txtPassword.setEchoChar('*');
        btnLogin = new JButton("Login");
         
        JPanel content = new JPanel();
        content.setLayout(new FlowLayout());
        content.add(new JLabel("Username:"));
        content.add(txtUsername);
        content.add(new JLabel("Password:"));
        content.add(txtPassword);
        content.add(btnLogin);
        btnLogin.addActionListener(this);
                 
        this.setContentPane(content);
        this.pack();        
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mySocket.getActiveFunction().add(new ObjectWrapper(ObjectWrapper.REPLY_LOGIN_USER,this));
    }
     
    public void receivedDataProcessing(ObjectWrapper data)
    {
       if (data.getData().equals("false")) {
           JOptionPane.showMessageDialog(this, "Incorect username or password", "Lỗi", JOptionPane.ERROR_MESSAGE);
//           if(user.getPosition().equalsIgnoreCase("manager")) {
//                    (new ManagerHomeFrm()).setVisible(true);
       }
       else {
           u = (User)data.getData();
           System.out.println(u.getName()+ "-" + u.getPosition());
           
           new HomeUserFrm(mySocket, u, myRMI).setVisible(true);
           
           this.dispose();
       }
    }
    public void actionPerformed(ActionEvent e) {
        if((e.getSource() instanceof JButton) && (((JButton)e.getSource()).equals(btnLogin))) {
            //pack the entity
            User user = new User();
            user.setUsername(txtUsername.getText());
            user.setPassword(txtPassword.getText());
            
            ObjectWrapper data = new ObjectWrapper(ObjectWrapper.LOGIN_USER, user);
            //sending data
            mySocket.sendData(data);
        }
    }
}