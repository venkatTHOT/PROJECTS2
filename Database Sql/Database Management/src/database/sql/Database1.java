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


import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

//import net.proteanit.sql.DBUtils;
public class Database1 extends JFrame
{
    
    static String checkUser;
    String DATABASE_CREATE_NEWUSER ="create table db_newuser (id integer primary key autoincrement, name varchar(30) not null, Usn varchar(30) not null, pass varchar(30) not null,mobile varchar(30) not null,sex varchar(30) not null);";
    String DATABASE_CREATE_Record ="CREATE TABLE REGISTRATION " +
                   "(id INTEGER not NULL, " +
                   " first VARCHAR(255), " + 
                   " last VARCHAR(255), " + 
                   " age INTEGER, " + 
                   " PRIMARY KEY ( id ))";
    Statement S;
    String insertSQL = "INSERT INTO db_newuser( id, name, username,pass,mobile,sex) VALUES (name_of_sequence.NEXTVAL, ?, ?, ?, ?, ?)";
    String insert_in_record="INSERT INTO db_newuser( id, name, username,pass,mobile,sex) VALUES (name_of_sequence.NEXTVAL";
     public Database1() {
        
     /*    
         
         try
{
//Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
//Connection c=DriverManager.getConnection("jdbc:odbc:mydsn","system","server");

Class.forName("oracle.jdbc.driver.OracleDriver");
Connection c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","server");



Statement S=c.createStatement();

//"create table" + table_name + "("+column1+" "+data_type+","


int x=S.executeUpdate("insert into db_newuser values("+1+",'"+"name"+"','"+"username"+"','"+"pass"+"','"+"mobile"+"','"+"sex"+"')");





//int x=S.executeUpdate("update student set name='"+t1+"'where age="+t2);
//int x=.executeUpdate("delete from student where age="+t2);
//System.out.println(x);
//S.commit();

ResultSet result1=S.executeQuery("select * from db_newuser");
while(result1.next())
{
System.out.println("name="+result1.getString(2));
System.out.println("pass="+result1.getString(4));
}

}catch(Exception e)
	{
            System.out.println("hello");
	e.printStackTrace();
	}*/
         
     }
         
     
         
                
    
   //for new user
    public int insert_newuser(String name,String username,String pass,String mobile,String sex){
            
            int x=0;
            try{
                
                
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    Connection c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","server");


                    PreparedStatement stmt = null;
                        

                    //Statement S=c.createStatement();
                   //x=S.executeUpdate("insert into db_newuser values("+id+",'"+name+"','"+username+"','"+pass+"','"+mobile+"','"+sex+"')");
                   stmt = c.prepareStatement( insertSQL );
              
             stmt.setString( 1, name);
             stmt.setString( 2, username );
             stmt.setString( 3, pass );
             stmt.setString( 4, mobile );
             stmt.setString( 5, sex );
             stmt.executeUpdate();
                   
                }
                catch(Exception e)
                {
                    System.out.println("insert into db_newuser values('"+name+"','"+username+"','"+pass+"','"+mobile+"','"+sex+"')");
                    e.printStackTrace();
                }
             return x;  
                        }
    
    
    //for displaying data 
    public ResultSet update_table()
    {
        ResultSet result1=null;
        try{
             Class.forName("oracle.jdbc.driver.OracleDriver");
                    Connection c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","server");


                    PreparedStatement stmt = null;
                        

                    //Statement S=c.createStatement();
                   //x=S.executeUpdate("insert into db_newuser values("+id+",'"+name+"','"+username+"','"+pass+"','"+mobile+"','"+sex+"')");
                   stmt = c.prepareStatement( "SELECT * FROM db_newuser");
                    result1=stmt.executeQuery();
                 
                   
        }
        catch(Exception e)
                {
                   
                    e.printStackTrace();
                }
         return  result1;
    }
    
    public boolean login_check(String username,String password) throws ClassNotFoundException
    {
         
         try{
                     Class.forName("oracle.jdbc.driver.OracleDriver");
                    Connection c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","server");

        String P="SELECT * FROM db_newuser";
        Statement S=c.createStatement();
       ResultSet results = S.executeQuery(P);

            while (results.next()) {
            String staffname = results.getString(3);
            
            String pass =  results.getString(4);

               if ((username.equals(staffname)) && password.equals(pass)) {
                    return true;
                 // JOptionPane.showMessageDialog(null, "Username and Password exist");  
                  
                  
            }
               else {

             //JOptionPane.showMessageDialog(null, "Please Check Username and Password ");
            }
            
        }
         }catch (SQLException sql) {

            System.out.println(sql);
        }
          return  false;
    }
    public void create_table(String col[],String data_type[])
    {
        
      /*  "CREATE TABLE REGISTRATION " +
                   "(id INTEGER not NULL, " +
                   " first VARCHAR(255), " + 
                   " last VARCHAR(255), " + 
                   " age INTEGER, " + 
                   " PRIMARY KEY ( id ))";*/
        
            for(int i=0;i<col.length;i++){
                
                DATABASE_CREATE_Record=DATABASE_CREATE_Record+col[i]+
                        " "+
                        data_type[i];
                if(data_type.equals("VARCHAR"))
                {
                    DATABASE_CREATE_Record=DATABASE_CREATE_Record+"(255)";
                }
                
                        
                System.out.println(col[i]+data_type[i]);
                        
            }
    }
    
    
    
   
    
}
    
    //for record
    
    /* public void createTable_record(String Dname,String type0,
             String col1,String type1,
             String col2,String type2,
             String col3,String type3,
             String col4,String type4)
     {
            
           DATABASE_CREATE_RECORD ="create table db_newuser (_id integer primary key autoincrement, "+Dname+" "+ type0+" not null, "+col1+" "+ type1+" not null, "+col2+" " +type2+" not null,"+col3+" "+ type3+" not null,"+col4+" " +type4+" not null);";
                        
     
     }
    
        
    public ResultSet getRecord(){
            ResultSet result1=null;
            try{
         
                    result1=S.executeQuery("select * from database");
    
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
                    return result1;    
    }                      }*/
     
   

        




/*
import java.sql.*;
class Database1
{
public static void main(String...a)
{
try
{
//Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
//Connection c=DriverManager.getConnection("jdbc:odbc:mydsn","system","server");

Class.forName("oracle.jdbc.driver.OracleDriver");
Connection c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","server");



Statement S=c.createStatement();

//"create table" + table_name + "("+column1+" "+data_type+","


int x=S.executeUpdate("insert into db_newuser values("+1+",'"+"name"+"','"+"username"+"','"+"pass"+"','"+"mobile"+"','"+"sex"+"')");





//int x=S.executeUpdate("update student set name='"+t1+"'where age="+t2);
//int x=.executeUpdate("delete from student where age="+t2);
//System.out.println(x);
//S.commit();

ResultSet result1=S.executeQuery("select * from db_newuser");
while(result1.next())
{
System.out.println("name="+result1.getString(2));
System.out.println("pass="+result1.getString(4));
}

}catch(Exception e)
	{
	e.printStackTrace();
	}

}
}
    
   
//"insert into db_newuser values("+1+",'"+name+"','"+username+"','"+pass+"','"+mobile+"','"+sex+"')"
//String sql = "CREATE TABLE REGISTRATION " +
                   "(id INTEGER not NULL, " +
                   " first VARCHAR(255), " + 
                   " last VARCHAR(255), " + 
                   " age INTEGER, " + 
                   " PRIMARY KEY ( id ))";     
   

*/



/*CREATE SEQUENCE name_of_sequence
  START WITH 1
  INCREMENT BY 1
  CACHE 100;
*/