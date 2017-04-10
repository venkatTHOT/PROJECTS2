/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.sql;

/**
 *
 * @author AVINASH GURJAR
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
class AdminLogIn extends JFrame implements ActionListener
{
    AdminLogIn a;
    JButton b1,b2,b3;
    JLabel l1,l2,l3,l4;
    TextField t1,t2;
    JPanel jp;
    
    
    public AdminLogIn (String s)
    {
        super(s);
        setLayout(null);
        a=this;
        
        //For Label
        l3=new JLabel("  new user");
        l1=new JLabel("Username   :");
        l2=new JLabel("Password    :");
         l4=new JLabel("  forget   password");
         
         
        l1.setBounds(40,80,100,30);        
        l2.setBounds(40,160,100,30);
        l3.setBounds(305,40,80,30);
        l4.setBounds(100,210,120,30);
        l3.setBackground(Color.GRAY);
        l2.setBackground(Color.GRAY);
        l1.setBackground(Color.GRAY);
        l4.setBackground(Color.GRAY);
       
        
        //FOR Textfield
        t1=new TextField();
        t2=new TextField();       
        t1.setBackground(Color.WHITE);
        t2.setBackground(Color.WHITE);
        t1.setBounds(180, 80, 100, 30);
        t2.setBounds(180, 160, 100, 30);
            
  
        
        //FOR Button
        jp=new JPanel();
        b1=new JButton("Login");
        b2=new JButton("Cancel");
        b1.setBounds(50,260,90,30);
        b2.setBounds(180,260,90,30);
        
        
        //for jpanel
        
       add(b1);
        add(b2);
        add(l1);
        add(l1);
        add(l2);
        add(t1);
        add(t2);
        add(l3);
        add(l4);
        
        //for button login
        
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s1=t1.getText();
                String s2=t2.getText();
                Database1 db=new Database1();
               
                
                try{     
                     boolean b=db.login_check(s1,s2);
                     System.out.println(b);
               if(db.login_check(s1,s2))
                {
                    new Manage().setVisible(true);
                    a.setVisible(false);
                }
               
                }catch(Exception f)
                {
                    f.printStackTrace();
                }
            }
        }
        );
        
        
        setSize(450,450);
        mouseactionlabel(this);
         setResizable(false);
        setLocation(450,450);
        
    }
    public static void main(String ... a)
    {
        AdminLogIn f= new AdminLogIn("Loginform");
        f.setVisible(true);
       
       
       
        
    }
    void mouseactionlabel(AdminLogIn a){
l3.addMouseListener(new MouseListener()
{
public void mouseClicked(MouseEvent arg0) {

 NewUser n = new NewUser("New user");
                n.setVisible(true);
                a.setVisible(false);
} 
public void mouseEntered(MouseEvent arg0) {
}
public void mouseExited(MouseEvent arg0) {
}
public void mousePressed(MouseEvent arg0) {
}
public void mouseReleased(MouseEvent arg0) {
}
});
}

    @Override
    public void actionPerformed(ActionEvent e) {
    }
    
}
