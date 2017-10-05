/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;


import business.Pagamento;
import java.sql.Connection;
import java.sql.Date;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marcos
 */
public class PagamentoDAO {
    public static void save(int id, Date data) throws SQLException{
        Connection c = Connect.connect();
        PreparedStatement ps =  c.prepareStatement("insert into pagamento values"
                                        + "( ?, ?)");
        ps.setInt(1, id);
        ps.setDate(2, data);
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
        Connection c = Connect.connect();
        ResultSet rs = c.createStatement().executeQuery("Select * from apartamento");
        
        List<String> list = new ArrayList<>();
        
           while(rs.next()){
              String a =  rs.getString("Pin");
              list.add(a);
           }
           
           return list;
        
    }
    
    public static Pagamento Pagamento_de_despesa(int i) throws SQLException {
        try{
        Connection c = Connect.connect();
        PreparedStatement st = c.prepareStatement("Select * from pagamento WHERE Despesa_idDespesa = ?");
         st.setFloat(1,i);
        ResultSet rs = st.executeQuery();
        
        Date data = rs.getDate("Data_Pagamento");
        Pagamento a = new Pagamento(data);
        
          return a;}catch(Exception e ){return new Pagamento();}
        
    }
}
