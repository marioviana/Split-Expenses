/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * Connect
 * ruicouto in 28/nov/2016
 */
package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Connect {
    
    private static final String USERNAME = "root";
    private static final String PASSWORD = "mariposa";
    private static final String URL = "localhost";
    private static final String SCHEMA = "gestaodespesas";
    

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");   
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://"+URL+"/"+SCHEMA+"?user="+USERNAME+"&password="+PASSWORD);
    }
    
}