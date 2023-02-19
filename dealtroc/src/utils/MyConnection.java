/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Eya
 */
public class MyConnection {
          private  Connection  conn;
   String url = "jdbc:mysql://localhost:3306/dealtroc";
   String user = "root";
   String pwd = "";
   private static MyConnection instance;
    private MyConnection() {
        
        try {
            conn = DriverManager.getConnection(url, user, pwd);
            System.out.println("Connexion etablie!!!");
        } catch (SQLException ex) {
             System.out.println(ex.getMessage()); 
//            System.out.println("probleme de Connexion");    
        }}

  public static MyConnection getInstance(){
        if (MyConnection.instance == null) {
            MyConnection.instance = new MyConnection();
        }
        return MyConnection.instance;
        
    }
  public static Connection getConnection() {
        return MyConnection.getInstance().conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }
  
    
}
