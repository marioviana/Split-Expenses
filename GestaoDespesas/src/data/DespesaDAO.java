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
import business.Despesa;
import business.Morador;
import data.Connect;
import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author Ruben
 */
public class DespesaDAO {
    
    public static void save(Despesa despesa, String i) throws SQLException{
        Connection c = Connect.connect();
        PreparedStatement ps =  c.prepareStatement("insert into despesa values"
                                        + "( ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        ps.setInt(1, despesa.getId());
        ps.setFloat(2, despesa.getValor());
        String date_format = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(date_format);
        String prazo = sdf.format(despesa.getPrazo());
        System.out.println(prazo);
        java.sql.Date d = java.sql.Date.valueOf(prazo);
        ps.setDate(3, d);
        ps.setString(4, despesa.getNome());
        ps.setString(5, despesa.getDescricao());
        ps.setInt(6, despesa.getEstado());
        ps.setString(7, despesa.getTipo());
        String data_recorrente = sdf.format(despesa.getData_Recorrente());
        java.sql.Date d1 = java.sql.Date.valueOf( data_recorrente);
        ps.setDate(8,d1);
        ps.setString(9,i);

        ps.executeUpdate();
        c.close();
    }
    
    public static void actualiza(Despesa despesa , String pin) throws SQLException{
        Connection c  = Connect.connect();
         try 
     {     

        PreparedStatement st = c.prepareStatement("UPDATE Despesa SET Estado = ? WHERE idDespesa = ? AND Apartamento_Pin = ?");
        st.setInt(1,1);
        st.setInt(2,despesa.getId());
        st.setString(3,pin);
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

        PreparedStatement st = c.prepareStatement("DELETE FROM Despesa WHERE name = ?");
        st.setString(1,name);
        st.executeUpdate(); 
     }
     catch(Exception e)
     {
         System.out.println(e);
     }
     c.close();
}
    public static List<Despesa> list() throws SQLException {
        Connection c = Connect.connect();
        ResultSet rs = c.createStatement().executeQuery("Select * from despesa");
        
        List<Despesa> list = new ArrayList<>();
        
           while(rs.next()){
              int id =  rs.getInt("id");
              float valor = rs.getFloat("valor");
              Date prazo = rs.getDate("prazo");
              String nome = rs.getString("nome");
              String descricao = rs.getString("descricao");
              int estado = rs.getInt("estado");
              String tipo = rs.getString("tipo");
              Date data_recorrente = rs.getDate("data_recorrente");

              Despesa a = new Despesa(id,valor,prazo,nome, descricao, estado, tipo, data_recorrente);
              list.add(a);
           }
           
           return list;
        
    }
    
     public static List<Despesa> list_Apartamento(String pin) throws SQLException {
          Connection c = Connect.connect();
        PreparedStatement st = c.prepareStatement("Select * from despesa WHERE Apartamento_Pin = ?");
         st.setString(1,pin);
        ResultSet rs = st.executeQuery();
        
       List<Despesa> list = new ArrayList<>();
        while(rs.next()){
              int id =  rs.getInt("idDespesa");
              float valor = rs.getFloat("valor");
              Date prazo = rs.getDate("prazo");
              String nome = rs.getString("nome");
              String descricao = rs.getString("descricao");
              int estado = rs.getInt("estado");
              String tipo = rs.getString("tipo");
              Date data_recorrente = rs.getDate("data_recorrente");

              Despesa a = new Despesa(id,valor,prazo,nome, descricao, estado, tipo, data_recorrente);
              list.add(a);
           }
           
           return list;
        
    }
    
}
