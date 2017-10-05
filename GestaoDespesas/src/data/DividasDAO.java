/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import business.Despesa;
import business.Morador;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Marcos
 */
public class DividasDAO {
    public static void save(Morador m, String nome, Float valor) throws SQLException{
        Connection c = Connect.connect();
        PreparedStatement ps =  c.prepareStatement("insert into Dividas values"
                                        + "( ?, ?, ? )");
        ps.setString(1, nome);
        ps.setFloat(2, valor);
        ps.setString(3, m.getMail());
        

        ps.executeUpdate();
        c.close();
    }
    
 
    public static void actualiza(String logado, String name ,Float valor) throws SQLException{
        Connection c  = Connect.connect();
         try 
     {     

        PreparedStatement st = c.prepareStatement("UPDATE Dividas SET Valor = ? WHERE Mail = ? AND Morador_Email = ?");
        st.setFloat(1,valor);
        st.setString(2,name);
        st.setString(3,logado);
        st.executeUpdate(); 
     }
     catch(Exception e)
     {
         System.out.println(e);
     }
     c.close();
        
    }
    
    
    public static void DeleteRow(String name) throws SQLException{
       Connection c = Connect.connect();
     try 
     {     

        PreparedStatement st = c.prepareStatement("DELETE FROM Divida WHERE Morador_Email = ?");
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
    
    public static HashMap<String,Float> list_Morador(String email) throws SQLException {
         Connection c = Connect.connect();
        PreparedStatement st = c.prepareStatement("Select * from dividas WHERE Mail = ?");
         st.setString(1,email);
        ResultSet rs = st.executeQuery();
        
       HashMap<String,Float> mapa = new HashMap<>();
        while(rs.next()){
              
              String mail = rs.getString("Morador_Email");
              Float valor = rs.getFloat("valor");
              mapa.put(mail,valor);
           }
           
           return mapa;
       
    }
}
