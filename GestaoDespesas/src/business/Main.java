package business;

import data.ApartamentoDAO;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import presentation.Login;
import data.MoradorDAO;
import data.Connect;
import data.DespesaDAO;
import data.DividasDAO;
import data.PagamentoDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.


/**
 *
 * @author marioferreira
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
        Date date = null;
        try {
            date = sdf.parse("1/4/2016");
        } catch (ParseException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*
        HashSet<String> mor = new HashSet<>();
        mor.add("mario@mail.com");
        mor.add("creissac@mail.com");
        Apartamento a = new Apartamento("abc");
       
        app.put(a.getPin(),a);
        a.registarMorador("mario","mario@mail.com", "pw1",1);
        
        a.registarMorador("mano_creissac","creissac@mail.com", "pw2",2);
        //float v ,Date p , String n, String d, int e, String t, Date data, HashSet<String> moradores, int ent, int ref 
        //a.registarDespesa(15, date, "despesa1", "pagar agua", 3, mor, 30000, 300000000);

        a.getMoradores().get("mario@mail.com").setAdmin(true);
        */
        HashMap<String,Apartamento> app = carrega();
        HashSet<String> mor = new HashSet<>();
        mor.add("mario@mail.com");
        mor.add("creissac@mail.com");
        Apartamento a = new Apartamento("abc");
        app.put(a.getPin(),a);
        a.registarMorador("mario","mario@mail.com", "pw1",1, true);
        a.registarMorador("mano_creissac","creissac@mail.com", "pw2",1, false);
        app.put("abc", a);
         
        Login l = new Login(app);
        l.setLocationRelativeTo(null);
        l.setVisible(true);
    }
    
    public static HashMap<String,Apartamento> carrega() throws SQLException{
        HashMap<String , Apartamento> novo = new HashMap<>();
        List<String> pins = ApartamentoDAO.list();
        for(String i  : pins){
            List<Morador> moradores = MoradorDAO.list_Apartamento(i);
            for(Morador j : moradores){
                HashMap<String,Float> dividas = DividasDAO.list_Morador(j.getMail());
                j.setLista_Dividas(dividas);
                
            }
            
            List<Despesa> despesas = DespesaDAO.list_Apartamento(i);
            for(Despesa f : despesas){
                Pagamento pagamento= PagamentoDAO.Pagamento_de_despesa(f.getId());
                f.setPagamento(pagamento);
                
            }
            HashMap<String,Morador> mapa_moradores = new HashMap<>();
            for(Morador u : moradores){
                mapa_moradores.put(u.getMail(), u.clone());
                
            }
            
             HashMap<Integer,Despesa> mapa_despesas = new HashMap<>();
            for(Despesa g : despesas){
                mapa_despesas.put(g.getId(), g.clone());
            }
            
            
            Apartamento apartamento = new Apartamento(i,mapa_moradores,mapa_despesas);
            novo.put(i, apartamento);
            
        }
        
        
        return novo;
    }
    
}