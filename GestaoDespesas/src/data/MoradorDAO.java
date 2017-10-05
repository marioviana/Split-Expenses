/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import business.Morador;


/**
 *
 * @author Marcos
 */
public class MoradorDAO {
    
    public static void save(Morador morador,String pin) throws SQLException{
        Connection c = Connect.connect();
        PreparedStatement ps =  c.prepareStatement("insert into morador values"
                                        + "( ?, ?, ?, ?, ?, ?, ? )");
        ps.setString(1, morador.getNome());
        ps.setString(2, morador.getMail());
        ps.setString(3, morador.getPassword());
        ps.setString(4, "2016-12-20");
        ps.setFloat(5, morador.getBalanco());
        ps.setBoolean(6, morador.getAdmin());
        ps.setString(7, pin);

        
        ps.executeUpdate();
        c.close();
        
    }
    
  
    public static void DeleteRow(String name) throws SQLException{
       Connection c = Connect.connect();
     try 
     {     

        PreparedStatement st = c.prepareStatement("DELETE FROM morador WHERE name = ?");
        st.setString(1,name);
        st.executeUpdate(); 
     }
     catch(Exception e)
     {
         System.out.println(e);
     }
     c.close();
    }
    
    public static List<Morador> list() throws SQLException {
        Connection c = Connect.connect();
        ResultSet rs = c.createStatement().executeQuery("Select * from morador");
        
        List<Morador> list = new ArrayList<>();
        
           while(rs.next()){
              String name =  rs.getString("nome");
              String mail = rs.getString("mail");
              String pw = rs.getString("password");

              Float balanco = rs.getFloat("balanco");
              Boolean admin = rs.getBoolean("admin");
              Morador a = new Morador(name,mail,pw,1,balanco,admin );
              list.add(a);
           }
           
           return list;
        
    }
    
      public static void actualiza(String logado, String valor) throws SQLException{
        Connection c  = Connect.connect();
         try 
     {     

        PreparedStatement st = c.prepareStatement("UPDATE Morador SET Balanco = ? WHERE Email = ? ");
        st.setString(1,valor);
        st.setString(2,logado);
 
        st.executeUpdate(); 
     }
     catch(Exception e)
     {
         System.out.println(e);
     }
     c.close();
        
    }
    
    public static List<Morador> list_Apartamento(String pin) throws SQLException{
        Connection c = Connect.connect();
        PreparedStatement st = c.prepareStatement("Select * from morador WHERE Apartamento_Pin = ?");
         st.setString(1,pin);
        ResultSet rs = st.executeQuery();
        
       List<Morador> list = new ArrayList<>();
        while(rs.next()){
              String name =  rs.getString("nome");
              String mail = rs.getString("email");
              String pw = rs.getString("password");
          
              Float balanco = rs.getFloat("balanco");
              Boolean admin = rs.getBoolean("admin");
              Morador a = new Morador(name,mail,pw,1,balanco,admin );
             
              list.add(a);
           }
           
           return list;
       
    }
    
}
