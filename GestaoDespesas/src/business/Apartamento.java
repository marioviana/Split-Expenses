package business;

import data.ApartamentoDAO;
import data.DespesaDAO;
import data.DividasDAO;
import data.MoradorDAO;
import data.Morador_has_DespesaDAO;
import data.PagamentoDAO;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Apartamento {
  // Atributos
  private String pin;
  private String mail;
  private HashMap<String, Morador> moradores;
  private HashMap<Integer, Despesa> despesas;


  // Construtores
  public Apartamento() {
      moradores = new HashMap<>();
      despesas = new HashMap<>();
  }
  
    public Apartamento(String pin) {
      this.pin=pin;
        try{
      ApartamentoDAO.save_Pin( pin);
     } catch (Exception e){
            e.printStackTrace();
      }
      
      moradores = new HashMap<>();
      despesas = new HashMap<>();
  }
    
    public Apartamento(Apartamento b) {
      this.pin=b.getPin();
      moradores = b.getMoradores();
      despesas = b.getDespesas();
  }
  
  public Apartamento (HashMap<String, Morador> moradores, HashMap<Integer, Despesa> despesas) {
    this.moradores = moradores;
    this.despesas = despesas;
  }
  
   public Apartamento (String pin,HashMap<String, Morador> moradores, HashMap<Integer, Despesa> despesas) {
    this.moradores = moradores;
    this.pin=pin;
    this.despesas = despesas;
  }

  // Getters e setters

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public HashMap<String, Morador> getMoradores() {
        return moradores;
    }

    public void setMoradores(HashMap<String, Morador> moradores) {
        this.moradores = moradores;
    }

    public HashMap<Integer, Despesa> getDespesas() {
        return despesas;
    }
    
    public HashMap<Integer, Despesa> getDespesas_copia() {
        HashMap<Integer, Despesa> novo = new HashMap<>();
        for(Map.Entry<Integer,Despesa> j : despesas.entrySet()){
            novo.put(j.getKey(),j.getValue().clone());
        }
        
        return novo;
    }

    public void setDespesas(HashMap<Integer, Despesa> despesas) {
        this.despesas = despesas;
    }

   public String getPin(){
       return pin;
   }
   
   public void setPin(String pin){
       this.pin=pin;
   }
  // Métodos
  public Morador getMorador(String mail) {
      return this.moradores.get(mail);
  }  
    
  public Morador getMorador_nome(String nome) {
      for(Map.Entry<String, Morador> entry : this.getMoradores().entrySet()){
          if(entry.getValue().getNome().equals(nome)) return entry.getValue();
      }
      return new Morador();
  }  
    
  public void registarMorador(String nome, String mail, String password, int iban, boolean admin) {
    Morador morador = new Morador(nome,mail, password,iban, 0,admin);
    this.moradores.put(mail, morador);
    
    try{
      MoradorDAO.save(morador, this.pin);
     } catch (Exception e){
            e.printStackTrace();
      }
  }

  public void removerMorador(String mail) {
    if (this.moradores.get(this.mail).getAdmin())
      this.moradores.remove(mail);
  }

  public void registarAdmin(String nome,String mail, String password, int iban) {
    registarMorador(nome,mail, password, iban , true);
    this.moradores.get(mail).setAdmin(true);
  }

  public void registarDespesa (float v ,Date p , String n, String d, int f, String t, Date data, int ent, int ref ){
     int id = this.despesas.size()+1;
    Despesa despesa = new Despesa(id, v, p, n, d, f, t, data, ent, ref);
    this.despesas.put(id, despesa);
    for (Map.Entry<String,Morador> m : this.moradores.entrySet()) {
        m.getValue().addDespesa(id);
    } 
    
       try{
      DespesaDAO.save(despesa,pin);
     } catch (Exception e){
            e.printStackTrace();
      }
       
          try{
      Morador_has_DespesaDAO.save(this.mail,despesa);
     } catch (Exception e){
            e.printStackTrace();
      }
    
    
  }
  

  public void removerDespesa(int id) {
    this.despesas.remove(id);
    
  }

  
  
  public void registarPagamento(Date data, int despesa) {
    Pagamento pagamento = new Pagamento(data);
    this.despesas.get(despesa).setPagamento(pagamento);
    this.despesas.get(despesa).setEstado(1);
    
         try{
      DespesaDAO.actualiza(this.despesas.get(despesa), this.pin);
     } catch (Exception e){
            e.printStackTrace();
      }
    String date_format = "yyyy-MM-dd";
    SimpleDateFormat sdf = new SimpleDateFormat(date_format);
    String prazo = sdf.format(data);
       
        java.sql.Date d = java.sql.Date.valueOf(prazo);
        
    
         try{
      PagamentoDAO.save(despesa, d);
     } catch (Exception e){
            e.printStackTrace();
      }
    
    float valor = this.despesas.get(despesa).getValor();
    this.moradores.get(this.mail).addBalanco(valor / this.moradores.size());
    Float bal = this.moradores.get(this.mail).getBalanco();
    String bal1 = bal.toString();
    
      try{
      MoradorDAO.actualiza(this.mail, bal1);
     } catch (Exception e){
            e.printStackTrace();
      }
  
        for(Map.Entry<String, Morador> entry : this.getMoradores().entrySet()){
        if(!(entry.getKey()).equals(this.mail)){
            entry.getValue().subBalanco(valor/this.moradores.size());
            if(entry.getValue().getLista_Dividas().containsKey(mail) ) {
                float actual = entry.getValue().getLista_Dividas().get(mail);
                entry.getValue().getLista_Dividas().remove(mail);
                float total = actual + ((valor) / this.moradores.size());
                entry.getValue().getLista_Dividas().put(mail, total);
                      try{
                        DividasDAO.actualiza(this.mail, entry.getKey() , total);
                    } catch (Exception e){
                     e.printStackTrace();
                    }
                      
                      try{
                        Float bal2 = entry.getValue().getBalanco();
                      
                        MoradorDAO.actualiza(entry.getValue().getMail(), bal2.toString());
                    } catch (Exception e){
                     e.printStackTrace();
                    }
                      
                
            }
            else{
                Float total = ((valor)/(this.moradores.size()));
                entry.getValue().getLista_Dividas().put(mail, total);
                  try{
                        DividasDAO.save(this.getMorador(mail), entry.getKey() , total);
                    } catch (Exception e){
                     e.printStackTrace();
                    }
                  try{
                        Float bal2 = entry.getValue().getBalanco();
                        MoradorDAO.actualiza(entry.getValue().getMail(), bal2.toString());
                    } catch (Exception e){
                     e.printStackTrace();
                    }
            }
        } 
    
            }
    
  }
  
  public void registarPagamento_Sozinho(Date data, int despesa) {
    Pagamento pagamento = new Pagamento(data);
    this.despesas.get(despesa).setPagamento(pagamento);
    this.despesas.get(despesa).setEstado(1);
    
         try{
      DespesaDAO.actualiza(this.despesas.get(despesa), this.pin);
     } catch (Exception e){
            e.printStackTrace();
      }
         
         Float bal = this.moradores.get(this.mail).getBalanco();
    String bal1 = bal.toString();
    
      try{
      MoradorDAO.actualiza(this.mail, bal1);
     } catch (Exception e){
            e.printStackTrace();
      }
      
    String date_format = "yyyy-MM-dd";
    SimpleDateFormat sdf = new SimpleDateFormat(date_format);
    String prazo = sdf.format(data);
       
        java.sql.Date d = java.sql.Date.valueOf(prazo);
        
    
         try{
      PagamentoDAO.save(despesa, d);
     } catch (Exception e){
            e.printStackTrace();
      }
    
    float valor = this.despesas.get(despesa).getValor();
    this.moradores.get(this.mail).addBalanco(valor );
  
   
    
  }
  
 public void pagamentoDivida(String nome , Float valor){
     this.moradores.get(this.mail).addBalanco(valor);
     Float bal = this.moradores.get(this.mail).getBalanco();
    String bal1 = bal.toString();
    
      try{
      MoradorDAO.actualiza(this.mail, bal1);
     } catch (Exception e){
            e.printStackTrace();
      }
     
     this.moradores.get(nome).subBalanco(valor);
     Float bal2 = this.moradores.get(nome).getBalanco();
    String bal3 = bal.toString();
    
      try{
      MoradorDAO.actualiza(nome, bal1);
     } catch (Exception e){
            e.printStackTrace();
      }
    this.moradores.get(mail).actualizaDividas(nome,valor);
    
    if(this.moradores.get(mail).getLista_Dividas().get(nome) == valor){
        try{
        DividasDAO.DeleteRow(nome);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    else{
           try{
        DividasDAO.actualiza(this.mail,nome, valor);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
 
  }
 
 
     
 public HashMap<Integer, Despesa> filterDataPos (){
     Date now  = new Date();
     HashMap<Integer, Despesa> novo = new HashMap<>();
     for(Map.Entry<Integer, Despesa> i : this.despesas.entrySet()){
         if (i.getValue().getPrazo().after(now)) novo.put(i.getKey(),i.getValue().clone());
     }
     return novo;
 }
 
 public HashMap<Integer, Despesa> filterDataPre (){
     Date now  = new Date();
     HashMap<Integer, Despesa> novo = new HashMap<>();
     for(Map.Entry<Integer, Despesa> i : this.despesas.entrySet()){
         if (i.getValue().getPrazo().before(now)) novo.put(i.getKey(),i.getValue().clone());
     }
     return novo;
 }
 
   public HashMap<Integer, Despesa> filter_em_Divida (){
     HashMap<Integer, Despesa> novo = new HashMap<>();
     for(Map.Entry<Integer, Despesa> i : this.despesas.entrySet()){
         if (i.getValue().getEstado()== 0) novo.put(i.getKey(),i.getValue());
     }
     return novo;
 }
 
   
   public HashMap<Integer, Despesa> filter_em_Dia (){
     HashMap<Integer, Despesa> novo = new HashMap<>();
     for(Map.Entry<Integer, Despesa> i : this.despesas.entrySet()){
         if (i.getValue().getEstado()== 1) novo.put(i.getKey(),i.getValue());
     }
     return novo;
 }
   
  public HashMap<Integer, Despesa> filterDataPre_Divida (){
     Date now  = new Date();
     HashMap<Integer, Despesa> novo = new HashMap<>();
     for(Map.Entry<Integer, Despesa> i : this.despesas.entrySet()){
         if (i.getValue().getPrazo().before(now) && i.getValue().getEstado()== 0) novo.put(i.getKey(),i.getValue());
     }
     return novo;
 }
  
 public HashMap<Integer, Despesa> filterDescricao(String des){
     
     HashMap<Integer, Despesa> novo = new HashMap<>();
     for(Map.Entry<Integer, Despesa> i : this.despesas.entrySet()){
         if (i.getValue().getDescricao().equals(des)) novo.put(i.getKey(),i.getValue());
     }
     return novo; 
 }

public Apartamento clone(){
    return new Apartamento(this);
}
  
  
}
