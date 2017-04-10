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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
public class DbColName extends JFrame 
{
    DbColName d;
    JButton b1;
    JLabel l1,l2,l3;
    JTextField t1,t2,t3;
    JPanel jp;
    
    public  DbColName (String s)
    {
        super(s);
        setLayout(null);
        d=this;
        //For Label
        l1=new JLabel("Enter the name of Database :");
        l2=new JLabel("Enter the name of table :");
        l3=new JLabel("Enter the number of column  :");
         
         
        l1.setBounds(40,40,200,30);        
        l2.setBounds(40,90,290,30);
        l3.setBounds(40, 140, 300, 30);
       
        l2.setBackground(Color.GRAY);
        l1.setBackground(Color.GRAY);
        l3.setBackground(Color.GRAY);
        
       
        
        //FOR Textfield
        t1=new JTextField();
        t2=new JTextField();
        t3=new JTextField();
        t1.setBackground(Color.WHITE);
        t2.setBackground(Color.WHITE);
        t1.setBounds(300, 40, 100, 30);
        t1.setFont(new Font("Century Gothic", Font.ITALIC, 20));
         t2.setBounds(300, 90, 100, 30);
          t2.setFont(new Font("Century Gothic", Font.ITALIC, 20));
        t3.setBounds(300, 140, 100, 30);
         t3.setFont(new Font("Century Gothic", Font.ITALIC, 20));
  
        
        //For Button
        jp=new JPanel();
        b1=new JButton("OK");
      b1.setBounds(150,195,100,30);
      b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int foo = Integer.parseInt(t3.getText());
                TColName n =new TColName(foo);
        
                d.setVisible(false); 
            }
        });
        
        
        //for jpanel
        
        add(b1);
        
        add(l1);
        add(l3);
        add(l2);
        add(t1);
        add(t2);
        add(t3);
        setSize(450,300);
        setLocation(450,350);
       
        
    }
    
   
    
}

