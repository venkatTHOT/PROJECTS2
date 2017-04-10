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
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
public class NewUser extends JFrame 
{
    NewUser n;
    JButton b1;
    JLabel l1,l2,l3,l4,l5,l6;
    JTextField t1,t2,t5,t6;
    JPasswordField t4,t3;
     Database1 db;
    int i=0;
    
    public NewUser(String s)
    {
        super(s);
        setLayout(null);
        n=this;
        //For Label
        l1=new JLabel("Name    :");
        l2=new JLabel("Username   :");
        l3=new JLabel("Create your Password  :");
        l4=new JLabel("Confirm your password :");
	l5=new JLabel("Mobile :");
	l6=new JLabel("Gender :");
	     

	l1.setBounds(40,50,150,30);        
        l2.setBounds(40,100,150,30);
        l3.setBounds(40,150,150,30);
        l4.setBounds(40,200,150,30);
	l5.setBounds(40,250,150,30);
	l6.setBounds(40,300,150,30);
	
	addWindowListener(new MyWindowAdaptar());
	
	
        l1.setBackground(Color.GRAY);
        l2.setBackground(Color.GRAY);
        l3.setBackground(Color.GRAY);
        l4.setBackground(Color.GRAY);
	l5.setBackground(Color.GRAY);
	l6.setBackground(Color.GRAY);
	

    
   
        add(l1);
        add(l2);
        add(l3);
        add(l4);
        add(l5);
        add(l6);
        

	//for textfield
	
	//For Label
        t1=new JTextField("");
        t2=new JTextField("");
        t3=new JPasswordField("");
        t4=new JPasswordField("");
	t5=new JTextField("");
	t6=new JTextField("");
	     

	t1.setBounds(240,50,150,30);        
        t2.setBounds(240,100,150,30);
        t3.setBounds(240,150,150,30);
        t4.setBounds(240,200,150,30);
	t5.setBounds(240,250,150,30);
	t6.setBounds(240,300,150,30);
	
	
        t1.setBackground(Color.WHITE);
        t2.setBackground(Color.WHITE);
        t3.setBackground(Color.WHITE);
        t4.setBackground(Color.WHITE);
	t5.setBackground(Color.WHITE);
	t6.setBackground(Color.WHITE);
	

    
   
        add(t1);
        add(t2);
        add(t3);
        add(t4);
        add(t5);
        add(t6);

	//for Button
	b1=new JButton("Submit");
	
        b1.setBounds(140,350,150,30);
        setSize(450,450);
       setLocation(500,280);
	
        
       
        b1.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
           
            
           String pass=String.valueOf(t4.getPassword());
      try{
            db =new Database1();
            db.insert_newuser(t1.getText(),
                    t2.getText(),
                   pass,
                    t5.getText(),
                    t6.getText()
            );
             
                   
   }
      catch(Exception f)
	{
	f.printStackTrace();
	}
            DbColName dcn=new DbColName("Details");
            dcn.setVisible(true);
            
            n.setVisible(false);
        }
       
       
       
      
         }); 
        
            
       add(b1);
     
    
        setResizable(false);
        
        
       
    }
   
    

  
    
}


