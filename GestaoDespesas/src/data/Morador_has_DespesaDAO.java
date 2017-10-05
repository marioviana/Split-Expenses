/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import business.Despesa;
import business.Morador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marcos
 */
public class Morador_has_DespesaDAO {
    
        public static void save(String mail, Despesa d) throws SQLException{
        Connection c = Connect.connect();
        PreparedStatement ps =  c.prepareStatement("insert into Morador_has_Despesa values"
                                        + "( ?, ? )");
        ps.setString(1, mail);
        ps.setFloat(2, d.getId());
        

        ps.executeUpdate();
        c.close();
    }
    
 
    
    
    
    public static void DeleteRow(String name) throws SQLException{
       Connection c = Connect.connect();
     try 
     {     

        PreparedStatement st = c.prepareStatement("DELETE FROM Divida WHERE Mail = ?");
        st.setString(1,name);
        st.executeUpdate(); 
     }
     catch(Exception e)
     {
         System.out.println(e);
     }
     c.close();
}
    public static List<String> list() throws SQLException {
        Connection c = Connect.connect();
        ResultSet rs = c.createStatement().executeQuery("Select * from Divida");
        
        List<String> list = new ArrayList<>();
        
           while(rs.next()){
            
              String nome = rs.getString("Mail");

              list.add(nome);
           }
           
           return list;
        
    } 
}
