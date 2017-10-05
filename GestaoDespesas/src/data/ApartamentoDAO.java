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
import business.Apartamento;
import business.Morador;
/**
 *
 * @author Marcos
 */
public class ApartamentoDAO {
    public static void save(Apartamento apartamento) throws SQLException{
        Connection c = Connect.connect();
        PreparedStatement ps =  c.prepareStatement("insert into apartamento values"
                                        + "( ? )");
        ps.setString(1, apartamento.getPin());
        ps.executeUpdate();
        c.close();
        
    }
    
    public static void save_Pin(String apartamento) throws SQLException{
        Connection c = Connect.connect();
        PreparedStatement ps =  c.prepareStatement("insert into apartamento values"
                                        + "( ? )");
        ps.setString(1, apartamento);
        ps.executeUpdate();
        c.close();
        
    }
    
  
    public static void DeleteRow(String pin) throws SQLException{
       Connection c = Connect.connect();
     try 
     {     

        PreparedStatement st = c.prepareStatement("DELETE FROM apartamento WHERE Pin = ?");
        st.setString(1,pin);
        st.executeUpdate(); 
     }
     catch(Exception e)
     {
         System.out.println(e);
     }
     c.close();
    }
    
    public static List<String> list() throws SQLException {
        try {
        Connection c = Connect.connect();
        ResultSet rs = c.createStatement().executeQuery("Select * from Apartamento");
        
        List<String> list = new ArrayList<>();
        
           while(rs.next()){
              String a =  rs.getString("Pin");
              list.add(a);
           }
           
           return list;
        } catch (Exception e) { return new ArrayList<String>(); }
        
    }

}
